package de.e2n.cdkhandson.constants;

public class Constants {

    public static final String DEFAULT_REGION = "eu-central-1";

    /*
     * Für das Hands-on bewusst als Platzhalter.
     * Im Workshop kann jeder Teilnehmer hier seine eigene Account-ID eintragen
     * oder wir lesen sie später über CDK Context / Environment.
     */
    public static final String WORKSHOP_ACCOUNT_ID = "111111111111";

    public static final String MANAGED_BY = "aws-cdk";
    public static final String COST_CENTER = "training";
    public static final String REPOSITORY = "cdk-java-serverless-hands-on";
    public static final String APPLICATION = "serverless-hands-on";
    public static final String TTL = "1d";

    public static final String PROJECT_NAME = "cdk-java-serverless-hands-on";
    public static final String WORKSHOP_NAME = "cdk-java";
    public static final String OWNER = "developer";

    public static final String PRODUCTION_STACK_NAME = "Prod-Serverless-HandsOn";
    public static final String STAGING_STACK_NAME = "Staging-Serverless-HandsOn";

    public static final String MESSAGE_QUEUE_NAME = "cdk-handson-message-queue";

    private Constants() {
        // Utility class
    }
}
