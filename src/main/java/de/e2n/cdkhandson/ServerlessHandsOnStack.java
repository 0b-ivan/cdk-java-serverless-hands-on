package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constructs.MessageQueueConstruct;
import de.e2n.cdkhandson.model.HandsOnProps;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.Tags;
import software.constructs.Construct;

public class ServerlessHandsOnStack extends Stack {

    public ServerlessHandsOnStack(
            final Construct scope,
            final String id,
            final HandsOnProps props) {
        super(scope, id, props);

        Tagging.applyDefaultTags(this, props);

        var messageQueueConstruct = new MessageQueueConstruct(
                this,
                "MessageQueueConstruct",
                props.getMessageQueueName());

        var messageQueue = messageQueueConstruct.getMessageQueue();

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
