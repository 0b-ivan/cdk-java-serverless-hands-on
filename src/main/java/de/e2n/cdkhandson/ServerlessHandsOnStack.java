package de.e2n.cdkhandson;

import de.e2n.cdkhandson.model.HandsOnProps;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.constructs.Construct;

public class ServerlessHandsOnStack extends Stack {

    public ServerlessHandsOnStack(
            final Construct scope,
            final String id,
            final HandsOnProps props) {
        super(scope, id, props);

        CfnOutput.Builder.create(this, "ProjectName")
                .description("Name of the workshop project")
                .value(props.getProjectName())
                .build();

        CfnOutput.Builder.create(this, "Owner")
                .description("Owner of the workshop stack")
                .value(props.getOwner())
                .build();

        CfnOutput.Builder.create(this, "HandsOnEnvironment")
                .description("Environment of the workshop stack")
                .value(props.getHandsOnEnvironment().name())
                .build();
    }
}
