# CDK Java Serverless Hands-on

## Ziel

Wir bauen gemeinsam eine kleine Serverless-Architektur:

```text
curl / Client
  -> API Gateway
  -> Lambda
  -> SQS
```

Am Ende können wir per HTTP Request eine Nachricht an eine API senden.
Die Lambda verarbeitet den Request und schreibt die Nachricht in eine SQS Queue.

## Inhalte

* CDK App in Java
* Stack, Constructs, Props und Constants
* SQS Queue
* Resource Tagging
* Lambda Function
* IAM Grants
* API Gateway
* Tests
* Deployment
* End-to-End-Test
* Cleanup

## Voraussetzungen

Benötigt werden:

* Java 17
* Maven
* Node.js / npm
* AWS CLI
* AWS CDK CLI
* Git
* AWS Account mit passenden Rechten

Versionen prüfen:

```bash
java -version
mvn -version
node -v
npm -v
aws --version
cdk --version
git --version
```

AWS Login prüfen:

```bash
aws sts get-caller-identity
```

CDK CLI aktualisieren:

```bash
npm install -g aws-cdk@latest
```

## Repository klonen

```bash
git clone https://github.com/0b-ivan/cdk-java-serverless-hands-on.git
cd cdk-java-serverless-hands-on
```

## Branches

| Branch                     | Inhalt             |
| -------------------------- | ------------------ |
| `step/00-cdk-init`         | CDK Basisprojekt   |
| `step/01-e2n-structure`    | Projektstruktur    |
| `step/02-sqs-queue`        | SQS Queue          |
| `step/03-resource-tagging` | zentrale Tags      |
| `step/04-lambda`           | Lambda + SQS Grant |
| `step/05-api-gateway`      | API Gateway        |
| `step/06-final`            | finaler Stand      |

Branch wechseln:

```bash
git checkout step/06-final
```

## Projektstruktur

```text
src/main/java/de/e2n/cdkhandson/
├── CdkApp.java
├── ServerlessHandsOnStack.java
├── Tagging.java
├── constants/
│   ├── Constants.java
│   └── TagKeys.java
├── constructs/
│   ├── MessageQueueConstruct.java
│   ├── ApiLambdaConstruct.java
│   └── ApiGatewayConstruct.java
└── model/
    ├── HandsOnEnvironment.java
    └── HandsOnProps.java
```

## Standardablauf

Tests ausführen:

```bash
mvn test
```

CloudFormation Template erzeugen:

```bash
cdk synth
```

Änderungen anzeigen:

```bash
cdk diff
```

Stack deployen:

```bash
cdk deploy
```

Stack löschen:

```bash
cdk destroy
```

## Deployment testen

Nach `cdk deploy` gibt CDK Outputs aus.

Wichtig sind:

* `MessageApiUrl`
* `MessageQueueUrl`
* `MessageHandlerFunctionName`

API testen:

```bash
curl -i -X POST "<MessageApiUrl>" \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello from CDK Hands-on"}'
```

Erwartete Antwort:

```json
{"status":"queued","message":"Hello from CDK Hands-on"}
```

SQS prüfen:

```bash
aws sqs receive-message \
  --queue-url "<MessageQueueUrl>" \
  --max-number-of-messages 1 \
  --region eu-central-1
```

Lambda Logs prüfen:

```bash
aws logs tail "/aws/lambda/<MessageHandlerFunctionName>" \
  --since 10m \
  --region eu-central-1
```

## Cleanup

Nach dem Workshop immer aufräumen:

```bash
cdk destroy
```

Danach prüfen, ob Log Groups entfernt wurden:

```bash
aws logs describe-log-groups \
  --log-group-name-prefix "/aws/lambda/Prod-Serverless-HandsOn" \
  --region eu-central-1
```

Erwartung:

```json
{
  "logGroups": []
}
```

## Weitere Dokumentation

Die Detaildokumente liegen unter `docs/`:

| Datei                          | Inhalt          |
| ------------------------------ | --------------- |
| `docs/00-agenda.md`            | Ablauf          |
| `docs/01-prerequisites.md`     | Voraussetzungen |
| `docs/02-workshop-flow.md`     | Schrittfolge    |
| `docs/03-test-and-validate.md` | Testen          |
| `docs/04-troubleshooting.md`   | Fehlerhilfe     |
| `docs/05-cleanup.md`           | Cleanup         |
