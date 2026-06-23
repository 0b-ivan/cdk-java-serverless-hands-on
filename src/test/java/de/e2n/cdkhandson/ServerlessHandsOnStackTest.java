package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constants.Constants;
import de.e2n.cdkhandson.model.HandsOnEnvironment;
import de.e2n.cdkhandson.model.HandsOnProps;
import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;

import java.util.Map;

public class ServerlessHandsOnStackTest {

    @Test
    public void stackContainsSqsQueue() {
        App app = new App();

        var testProps = new HandsOnProps(
                HandsOnEnvironment.Staging,
                null,
                "Test-Serverless-HandsOn",
                Constants.PROJECT_NAME,
                Constants.OWNER,
                Constants.MESSAGE_QUEUE_NAME);

        ServerlessHandsOnStack stack = new ServerlessHandsOnStack(
                app,
                "TestServerlessHandsOnStack",
                testProps);

        Template template = Template.fromStack(stack);

        template.hasResourceProperties("AWS::SQS::Queue", Map.of(
                "VisibilityTimeout", 30,
                "MessageRetentionPeriod", 345600,
                "SqsManagedSseEnabled", true
        ));
    }
}
