package com.myorg;

import software.amazon.awscdk.App;

public class CdkJavaServerlessHandsOnApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkJavaServerlessHandsOnStack(app, "CdkJavaServerlessHandsOnStack");

        app.synth();
    }
}
