package de.e2n.cdkhandson.model;

import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class HandsOnProps implements StackProps {

    private final HandsOnEnvironment handsOnEnvironment;
    private final Environment env;
    private final String stackName;
    private final String projectName;
    private final String owner;
    private final String messageQueueName;

    public HandsOnProps(
            HandsOnEnvironment handsOnEnvironment,
            Environment env,
            String stackName,
            String projectName,
            String owner,
            String messageQueueName) {
        this.handsOnEnvironment = handsOnEnvironment;
        this.env = env;
        this.stackName = stackName;
        this.projectName = projectName;
        this.owner = owner;
        this.messageQueueName = messageQueueName;
    }

    public HandsOnEnvironment getHandsOnEnvironment() {
        return handsOnEnvironment;
    }

    @Override
    public Environment getEnv() {
        return env;
    }

    @Override
    public String getStackName() {
        return stackName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getOwner() {
        return owner;
    }

    public String getMessageQueueName() {
        return messageQueueName;
    }
}
