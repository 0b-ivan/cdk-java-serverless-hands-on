# cdk-java-serverless-hands-on

A minimal AWS CDK application in Java that deploys a serverless hello endpoint backed by AWS Lambda.

> [!WARNING]
> The sample stack exposes a public Lambda function URL for a simple hands-on flow. Add authentication before using this pattern in production.

## Prerequisites

- Java 17
- Maven
- Node.js

## Useful commands

- `mvn test` - compile and run the unit tests
- `npx aws-cdk synth` - synthesize the CloudFormation template
- `npx aws-cdk deploy` - deploy the stack to your configured AWS account