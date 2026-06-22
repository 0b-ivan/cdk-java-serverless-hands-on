package de.e2n.cdkhandson.constructs;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.sqs.QueueEncryption;
import software.constructs.Construct;

public class MessageQueueConstruct extends Construct {

    private final Queue messageQueue;

    public MessageQueueConstruct(
            final Construct scope,
            final String id,
            final String queueName) {
        super(scope, id);

        this.messageQueue = Queue.Builder.create(this, "MessageQueue")
                .queueName(queueName)
                .visibilityTimeout(Duration.seconds(30))
                .retentionPeriod(Duration.days(4))
                .encryption(QueueEncryption.SQS_MANAGED)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();
    }

    public Queue getMessageQueue() {
        return messageQueue;
    }
}
