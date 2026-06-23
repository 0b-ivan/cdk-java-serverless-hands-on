# Workshop Flow

## Start

Auf `main` liegen README und Doku.

Die Code-Stände liegen in den Step-Branches.

```bash id="ievno0"
git checkout step/00-cdk-init
```

## Branch-Prinzip

Jeder Branch ist ein fertiger Stand nach einem Workshop-Schritt.

| Branch                     | Schritt       |
| -------------------------- | ------------- |
| `step/00-cdk-init`         | Basis         |
| `step/01-e2n-structure`    | Struktur      |
| `step/02-sqs-queue`        | SQS           |
| `step/03-resource-tagging` | Tags          |
| `step/04-lambda`           | Lambda        |
| `step/05-api-gateway`      | API Gateway   |
| `step/06-final`            | finaler Stand |

## Schritt 0: CDK Basis

```bash id="elgo2s"
git checkout step/00-cdk-init
mvn test
cdk synth
```

Ziel:

* Projekt baut
* CDK App startet
* Template wird erzeugt

## Schritt 1: Projektstruktur

```bash id="y5qwy6"
git checkout step/01-e2n-structure
mvn test
cdk synth
```

Ziel:

* `CdkApp`
* `ServerlessHandsOnStack`
* `Constants`
* `HandsOnProps`
* `HandsOnEnvironment`

Noch keine Infrastruktur-Ressourcen.

## Schritt 2: SQS Queue

```bash id="i530wp"
git checkout step/02-sqs-queue
mvn test
cdk synth
```

Ziel:

* `MessageQueueConstruct`
* SQS Queue
* Outputs für Queue URL, Name, ARN

## Schritt 3: Resource Tagging

```bash id="z4td4r"
git checkout step/03-resource-tagging
mvn test
cdk synth
```

Ziel:

* `TagKeys`
* `Tagging`
* zentrale Tags auf Stack-Ebene

## Schritt 4: Lambda

```bash id="vxpr4e"
git checkout step/04-lambda
mvn test
cdk synth
```

Ziel:

* `ApiLambdaConstruct`
* Lambda Function
* `QUEUE_URL` als Environment Variable
* `messageQueue.grantSendMessages(...)`

## Schritt 5: API Gateway

```bash id="2f8bdb"
git checkout step/05-api-gateway
mvn test
cdk synth
```

Ziel:

* `ApiGatewayConstruct`
* REST API
* `POST /messages`
* Lambda Proxy Integration

## Schritt 6: Final Deploy

```bash id="3gz59a"
git checkout step/06-final
mvn test
cdk diff
cdk deploy
```

Ziel:

* vollständiger Stack
* API testen
* SQS prüfen
* Logs prüfen
* Cleanup testen

## Vergleichen

Unterschied zwischen zwei Steps anzeigen:

```bash id="p4wnhq"
git diff step/03-resource-tagging..step/04-lambda
```

Nur Dateinamen anzeigen:

```bash id="ew98pa"
git diff --name-only step/03-resource-tagging..step/04-lambda
```

## Zurück zu main

```bash id="y3f33b"
git checkout main
```

