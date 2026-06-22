package de.e2n.cdkhandson;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Tags;
import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.sqs.QueueEncryption;
import software.constructs.Construct;
import software.amazon.awscdk.Duration;

public class ServerlessApiStack extends Stack {

    public ServerlessApiStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ServerlessApiStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Tags.of(this).add("Project", "cdk-java-serverless-hands-on");
        Tags.of(this).add("Workshop", "cdk-java");
        Tags.of(this).add("Owner", "participant");

        Queue messageQueue = Queue.Builder.create(this, "MessageQueue")
                .queueName("cdk-handson-message-queue")
                .visibilityTimeout(Duration.seconds(30))
                .retentionPeriod(Duration.days(4))
                .encryption(QueueEncryption.SQS_MANAGED)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();

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