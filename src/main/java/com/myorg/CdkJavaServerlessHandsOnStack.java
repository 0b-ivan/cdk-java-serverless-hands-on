package com.myorg;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionUrl;
import software.amazon.awscdk.services.lambda.FunctionUrlOptions;
import software.amazon.awscdk.services.lambda.FunctionUrlAuthType;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

public class CdkJavaServerlessHandsOnStack extends Stack {
    public CdkJavaServerlessHandsOnStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkJavaServerlessHandsOnStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Function helloFunction = Function.Builder.create(this, "HelloFunction")
                .runtime(Runtime.NODEJS_20_X)
                .handler("index.handler")
                .code(Code.fromAsset("lambda"))
                .timeout(Duration.seconds(10))
                .build();

        FunctionUrl helloFunctionUrl = helloFunction.addFunctionUrl(FunctionUrlOptions.builder()
                .authType(FunctionUrlAuthType.NONE)
                .build());

        CfnOutput.Builder.create(this, "HelloFunctionUrl")
                // This sample intentionally uses a public URL to keep the hands-on flow minimal.
                .value(helloFunctionUrl.getUrl())
                .build();
    }
}
