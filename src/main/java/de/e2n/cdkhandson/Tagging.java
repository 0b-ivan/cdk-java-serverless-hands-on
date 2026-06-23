package de.e2n.cdkhandson;

import de.e2n.cdkhandson.constants.Constants;
import de.e2n.cdkhandson.constants.TagKeys;
import de.e2n.cdkhandson.model.HandsOnProps;
import software.amazon.awscdk.Tags;
import software.constructs.IConstruct;

public class Tagging {

    public static void applyDefaultTags(final IConstruct scope, final HandsOnProps props) {
        Tags.of(scope).add(TagKeys.PROJECT, props.getProjectName());
        Tags.of(scope).add(TagKeys.ENVIRONMENT, props.getHandsOnEnvironment().name());
        Tags.of(scope).add(TagKeys.OWNER, props.getOwner());
        Tags.of(scope).add(TagKeys.MANAGED_BY, Constants.MANAGED_BY);
        Tags.of(scope).add(TagKeys.WORKSHOP, Constants.WORKSHOP_NAME);
        Tags.of(scope).add(TagKeys.COST_CENTER, Constants.COST_CENTER);
        Tags.of(scope).add(TagKeys.REPOSITORY, Constants.REPOSITORY);
        Tags.of(scope).add(TagKeys.APPLICATION, Constants.APPLICATION);
        Tags.of(scope).add(TagKeys.TTL, Constants.TTL);
    }

    private Tagging() {
        // Utility class
    }
}
