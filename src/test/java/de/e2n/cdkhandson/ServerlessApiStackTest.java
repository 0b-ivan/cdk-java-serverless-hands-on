package de.e2n.cdkhandson;

import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;

import java.util.Map;

public class ServerlessApiStackTest {

    @Test
    public void stackContainsSqsQueue() {
        App app = new App();

        ServerlessApiStack stack = new ServerlessApiStack(app, "TestServerlessApiStack");

        Template template = Template.fromStack(stack);

        template.hasResourceProperties("AWS::SQS::Queue", Map.of(
                "VisibilityTimeout", 30,
                "MessageRetentionPeriod", 345600,
                "SqsManagedSseEnabled", true
        ));
    }
}