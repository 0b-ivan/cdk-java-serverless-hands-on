# Troubleshooting

## AWS Login fehlt

Fehlerbild:

```text
Unable to resolve AWS account
```

Fix:

```bash
ist die Leapp Session abgelaufen, dann erneut anmelden
oder garnicht aktiv?
Prüfen mit: aws sts get-caller-identity
```

## Falscher AWS Account

Prüfen:

```bash
aws sts get-caller-identity
```

Wenn der falsche Account aktiv ist:

```bash
Leapp Session in denn richtigen wechseln 
```

Danach erneut prüfen.

## Region fehlt

Fix:

```bash
export AWS_REGION=eu-central-1
export CDK_DEFAULT_REGION=eu-central-1
```

## CDK CLI zu alt

Fehlerbild:

```text
Cloud assembly schema version mismatch
```

Fix:

```bash
npm install -g aws-cdk@latest
cdk --version
```

## Java-Version falsch

Prüfen:

```bash
java -version
javac -version
```

Erwartung:

```text
21
```

Auf macOS setzen:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH="$JAVA_HOME/bin:$PATH"
```

Danach erneut prüfen.

## Maven Build schlägt fehl

```bash
mvn test
```

Typische Ursachen:

* falsche Java-Version
* kaputte Imports
* falscher Package-Name
* fehlende Datei nach Branch-Wechsel

Clean Build:

```bash
rm -rf target cdk.out
mvn test
```

## `cdk synth` schlägt fehl

Erst Build prüfen:

```bash
mvn test
```

Dann erneut:

```bash
cdk synth
```

Wenn Account/Region fehlt:

```bash
aws sts get-caller-identity
export AWS_REGION=eu-central-1
export CDK_DEFAULT_REGION=eu-central-1
```

## `cdk deploy` fragt wegen IAM

Meldung:

```text
"--require-approval" is enabled and stack includes security-sensitive updates
```

Das ist normal.

Im Workshop mit `y` bestätigen.

## API liefert 500

Logs prüfen:

```bash
aws logs tail "/aws/lambda/<MessageHandlerFunctionName>" \
  --since 10m \
  --region eu-central-1
```

Typische Ursachen:

* ungültiger JSON Body
* Lambda Runtime-Fehler
* Queue URL fehlt
* IAM Permission fehlt

## SQS zeigt keine Nachricht

Prüfen:

```bash
aws sqs get-queue-attributes \
  --queue-url "<MessageQueueUrl>" \
  --attribute-names ApproximateNumberOfMessages \
  --region eu-central-1
```

Hinweise:

* kurz warten und erneut prüfen
* `receive-message` macht Nachrichten temporär unsichtbar
* Visibility Timeout beachten

## Queue existiert bereits

Prüfen:

```bash
aws sqs get-queue-url \
  --queue-name "cdk-handson-message-queue" \
  --region eu-central-1
```

Wenn sie von einem alten Test stammt:

```bash
cdk destroy
```

## Stack existiert bereits

Prüfen:

```bash
aws cloudformation describe-stacks \
  --stack-name Prod-Serverless-HandsOn \
  --region eu-central-1
```

Löschen:

```bash
cdk destroy
```

## Nach Branch-Wechsel komische Fehler

Sauber neu bauen:

```bash
rm -rf target cdk.out
mvn test
cdk synth
```

## Letzter Ausweg

Aktuellen Branch prüfen:

```bash
git branch --show-current
git status
```

Zurück auf finalen Stand:

```bash
git checkout step/06-final
rm -rf target cdk.out
mvn test
cdk synth
```

