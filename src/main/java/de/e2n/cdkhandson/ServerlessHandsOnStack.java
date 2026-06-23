package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constructs.ApiGatewayConstruct;
import de.e2n.cdkhandson.constructs.ApiLambdaConstruct;
import de.e2n.cdkhandson.constructs.MessageQueueConstruct;
import de.e2n.cdkhandson.model.HandsOnProps;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

public class ServerlessHandsOnStack extends Stack {

    private final HandsOnProps props;

    private MessageQueueConstruct messageQueueConstruct;
    private Queue messageQueue;

    private ApiLambdaConstruct apiLambdaConstruct;
    private Function messageHandlerFunction;

    private ApiGatewayConstruct apiGatewayConstruct;
    private RestApi restApi;

    public ServerlessHandsOnStack(
            final Construct scope,
            final String id,
            final HandsOnProps props) {
        super(scope, id, props);
        this.props = props;

        Tagging.applyDefaultTags(this, props);

        messageQueue("MessageQueueConstruct");
        apiLambda("ApiLambdaConstruct");
        apiGateway("ApiGatewayConstruct");
        outputs();
    }

    private void messageQueue(final String id) {
        messageQueueConstruct = new MessageQueueConstruct(
                this,
                id,
                props.getMessageQueueName());

        messageQueue = messageQueueConstruct.getMessageQueue();
    }

    private void apiLambda(final String id) {
        if (messageQueue == null) {
            throw new IllegalStateException("messageQueue is null");
        }

        apiLambdaConstruct = new ApiLambdaConstruct(
                this,
                id,
                messageQueue);

        messageHandlerFunction = apiLambdaConstruct.getMessageHandlerFunction();
    }

    private void apiGateway(final String id) {
        if (messageHandlerFunction == null) {
            throw new IllegalStateException("messageHandlerFunction is null");
        }

        apiGatewayConstruct = new ApiGatewayConstruct(
                this,
                id,
                messageHandlerFunction);

        restApi = apiGatewayConstruct.getRestApi();
    }

    private void outputs() {
        if (messageQueue == null) {
            throw new IllegalStateException("messageQueue is null");
        }
        if (messageHandlerFunction == null) {
            throw new IllegalStateException("messageHandlerFunction is null");
        }
        if (restApi == null) {
            throw new IllegalStateException("restApi is null");
        }

        apiOutputs();
        lambdaOutputs();
        queueOutputs();
    }

    private void apiOutputs() {
        CfnOutput.Builder.create(this, "MessageApiUrl")
                .description("URL of the API Gateway messages endpoint")
                .value(restApi.getUrl() + "messages")
                .build();
    }

    private void lambdaOutputs() {
        CfnOutput.Builder.create(this, "MessageHandlerFunctionName")
                .description("Name of the Lambda function handling API messages")
                .value(messageHandlerFunction.getFunctionName())
                .build();
    }

    private void queueOutputs() {
        CfnOutput.Builder.create(this, "MessageQueueUrl")
                .description("URL of the SQS message queue")
                .value(messageQueue.getQueueUrl())
                .build();

        CfnOutput.Builder.create(this, "MessageQueueName")
                .description("Name of the SQS message queue")
                .value(messageQueue.getQueueName())
                .build();

        CfnOutput.Builder.create(this, "MessageQueueArn")
                .description("ARN of the SQS message queue")
                .value(messageQueue.getQueueArn())
                .build();
    }
}
