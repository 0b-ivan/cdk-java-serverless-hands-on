package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constants.Constants;
import de.e2n.cdkhandson.model.HandsOnEnvironment;
import de.e2n.cdkhandson.model.HandsOnProps;
import software.amazon.awscdk.App;

public class CdkApp {

    public static void main(final String[] args) {
        App app = new App();

        var productionProps = new HandsOnProps(
                HandsOnEnvironment.Production,
                null,
                Constants.PRODUCTION_STACK_NAME,
                Constants.PROJECT_NAME,
                Constants.OWNER,
                Constants.MESSAGE_QUEUE_NAME);

        new ServerlessHandsOnStack(app, "Prod-Serverless-HandsOn", productionProps);

        app.synth();
    }
}