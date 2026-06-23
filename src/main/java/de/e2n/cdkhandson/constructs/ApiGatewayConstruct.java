package de.e2n.cdkhandson.constructs;

import software.amazon.awscdk.services.apigateway.CorsOptions;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.MethodOptions;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.apigateway.StageOptions;
import software.amazon.awscdk.services.lambda.Function;
import software.constructs.Construct;

import java.util.List;

public class ApiGatewayConstruct extends Construct {

    private final RestApi restApi;

    public ApiGatewayConstruct(
            final Construct scope,
            final String id,
            final Function messageHandlerFunction) {
        super(scope, id);

        this.restApi = RestApi.Builder.create(this, "MessageApi")
                .restApiName("cdk-handson-message-api")
                .description("API Gateway for the CDK Java Serverless Hands-on")
                .deployOptions(StageOptions.builder()
                        .stageName("prod")
                        .build())
                .defaultCorsPreflightOptions(CorsOptions.builder()
                        .allowOrigins(List.of("*"))
                        .allowMethods(List.of("POST", "OPTIONS"))
                        .allowHeaders(List.of("Content-Type"))
                        .build())
                .build();

        var messagesResource = this.restApi.getRoot().addResource("messages");

        messagesResource.addMethod(
                "POST",
                new LambdaIntegration(messageHandlerFunction),
                MethodOptions.builder().build());
    }

    public RestApi getRestApi() {
        return restApi;
    }
}