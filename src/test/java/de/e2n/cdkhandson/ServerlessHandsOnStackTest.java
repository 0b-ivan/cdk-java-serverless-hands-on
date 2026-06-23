package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constants.Constants;
import de.e2n.cdkhandson.model.HandsOnEnvironment;
import de.e2n.cdkhandson.model.HandsOnProps;
import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.assertions.Template;

import java.util.Map;

class ServerlessHandsOnStackTest {

    @Test
    void stackShouldContainBasicOutputs() {
        var app = new App();

        var props = new HandsOnProps(
                HandsOnEnvironment.Production,
                Environment.builder()
                        .account("123456789012")
                        .region(Constants.DEFAULT_REGION)
                        .build(),
                Constants.PRODUCTION_STACK_NAME,
                Constants.PROJECT_NAME,
                Constants.OWNER,
                Constants.MESSAGE_QUEUE_NAME);

        var stack = new ServerlessHandsOnStack(app, "TestStack", props);

        var template = Template.fromStack(stack);

        template.hasOutput("ProjectName", Map.of(
                "Value", Constants.PROJECT_NAME
        ));

        template.hasOutput("Owner", Map.of(
                "Value", Constants.OWNER
        ));

        template.hasOutput("HandsOnEnvironment", Map.of(
                "Value", HandsOnEnvironment.Production.name()
        ));
    }
}
