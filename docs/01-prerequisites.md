# Voraussetzungen

## Tools

Benötigt:

* Java 21
* Maven
* Node.js / npm
* AWS CLI
* AWS CDK CLI
* Git

## Versionen prüfen

```bash id="2l1nu4"
java -version
mvn -version
node -v
npm -v
aws --version
cdk --version
git --version
```

## CDK CLI installieren oder aktualisieren

```bash id="dp5iq7"
npm install -g aws-cdk@latest
```

Prüfen:

```bash id="40v4ys"
cdk --version
```

## AWS Login

Bei IAM Identity Center:

```bash id="vrb7gh"
aws sso login
```

Account prüfen:

```bash id="kdtsfd"
aws sts get-caller-identity
```

## Region setzen

```bash id="x4l5pd"
export AWS_REGION=eu-central-1
export CDK_DEFAULT_REGION=eu-central-1
```

## Benötigte AWS Rechte

Der Account muss Folgendes erstellen dürfen:

* CloudFormation Stacks
* IAM Roles und Policies
* Lambda Functions
* API Gateway REST APIs
* SQS Queues
* CloudWatch Log Groups

## CDK Bootstrap

Ein neuer AWS Account muss vor dem ersten `cdk deploy` gebootstrapped werden.

Bootstrap prüfen:

```bash
aws cloudformation describe-stacks \
  --stack-name CDKToolkit \
  --region eu-central-1
```

## Projekt prüfen

```bash id="hhrp4a"
mvn test


Erwartung:

```text id="s830aq"
BUILD SUCCESS
```

