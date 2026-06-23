package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constructs.MessageQueueConstruct;
import de.e2n.cdkhandson.model.HandsOnProps;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

public class ServerlessHandsOnStack extends Stack {

    private final HandsOnProps props;

    private MessageQueueConstruct messageQueueConstruct;
    private Queue messageQueue;

    public ServerlessHandsOnStack(
            final Construct scope,
            final String id,
            final HandsOnProps props) {
        super(scope, id, props);
        this.props = props;

        messageQueue("MessageQueueConstruct");
        outputs();
    }

    private void messageQueue(final String id) {
        messageQueueConstruct = new MessageQueueConstruct(
                this,
                id,
                props.getMessageQueueName());

        messageQueue = messageQueueConstruct.getMessageQueue();
    }

    private void outputs() {
        if (messageQueue == null) {
            throw new IllegalStateException("messageQueue is null");
        }

        queueOutputs();
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
