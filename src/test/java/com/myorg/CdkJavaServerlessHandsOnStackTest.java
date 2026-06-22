package com.myorg;

import java.util.Map;

import org.junit.jupiter.api.Test;

import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;

class CdkJavaServerlessHandsOnStackTest {
    @Test
    void createsHelloLambdaWithPublicFunctionUrl() {
        App app = new App();
        CdkJavaServerlessHandsOnStack stack = new CdkJavaServerlessHandsOnStack(app, "test");

        Template template = Template.fromStack(stack);

        template.resourceCountIs("AWS::Lambda::Function", 1);
        template.hasResourceProperties("AWS::Lambda::Function", Map.of(
                "Handler", "index.handler",
                "Runtime", "nodejs20.x",
                "Timeout", 10
        ));
        template.hasResourceProperties("AWS::Lambda::Url", Map.of(
                "AuthType", "NONE"
        ));
    }
}
