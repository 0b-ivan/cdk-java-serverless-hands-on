package de.e2n.cdkhandson.constructs;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.logs.LogGroup;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

import java.util.Map;

public class ApiLambdaConstruct extends Construct {

    private final Function messageHandlerFunction;

    public ApiLambdaConstruct(
            final Construct scope,
            final String id,
            final Queue messageQueue) {
        super(scope, id);

        var messageHandlerLogGroup = LogGroup.Builder.create(this, "MessageHandlerFunctionLogGroup")
                .retention(RetentionDays.ONE_WEEK)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();

        this.messageHandlerFunction = Function.Builder.create(this, "MessageHandlerFunction")
                .runtime(Runtime.NODEJS_22_X)
                .handler("index.handler")
                .timeout(Duration.seconds(10))
                .logGroup(messageHandlerLogGroup)
                .environment(Map.of(
                        "QUEUE_URL", messageQueue.getQueueUrl()
                ))
                .code(Code.fromInline("""
                        const { SQSClient, SendMessageCommand } = require("@aws-sdk/client-sqs");

                        const sqsClient = new SQSClient({});

                        exports.handler = async (event) => {
                          console.log("Received event:", JSON.stringify(event));

                          const body = typeof event.body === "string"
                            ? JSON.parse(event.body || "{}")
                            : event.body || {};

                          const message = body.message || "Hello from CDK";

                          await sqsClient.send(new SendMessageCommand({
                            QueueUrl: process.env.QUEUE_URL,
                            MessageBody: JSON.stringify({
                              message,
                              receivedAt: new Date().toISOString()
                            })
                          }));

                          return {
                            statusCode: 200,
                            headers: {
                              "Content-Type": "application/json"
                            },
                            body: JSON.stringify({
                              status: "queued",
                              message
                            })
                          };
                        };
                        """))
                .build();

        messageQueue.grantSendMessages(this.messageHandlerFunction);
    }

    public Function getMessageHandlerFunction() {
        return messageHandlerFunction;
    }
}
