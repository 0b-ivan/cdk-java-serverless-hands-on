# Test and Validate

## Ziel

Prüfen, ob der Stack funktioniert:

## 1. Deploy ausführen

Auf finalen Stand wechseln:

```bash
git checkout step/06-final
```

Build prüfen:

```bash
mvn test
cdk diff
cdk deploy
```

IAM-Nachfrage mit `y` bestätigen.

## 2. Outputs notieren

Nach dem Deploy sind diese Outputs wichtig:

```text
MessageApiUrl
MessageQueueUrl
MessageHandlerFunctionName
```

## 3. API testen

```bash
curl -i -X POST "<MessageApiUrl>" \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello from CDK Hands-on"}'
```

Erwartung:

```http
HTTP/2 200
```

Body:

```json
{"status":"queued","message":"Hello from CDK Hands-on"}
```

## 4. SQS Queue prüfen

```bash
aws sqs get-queue-attributes \
  --queue-url "<MessageQueueUrl>" \
  --attribute-names ApproximateNumberOfMessages \
  --region eu-central-1
```

Erwartung:

```json
{
  "Attributes": {
    "ApproximateNumberOfMessages": "1"
  }
}
```

## 5. Nachricht lesen

```bash
aws sqs receive-message \
  --queue-url "<MessageQueueUrl>" \
  --max-number-of-messages 1 \
  --region eu-central-1
```

Erwartung im Body:

```json
{
  "message": "Hello from CDK Hands-on",
  "receivedAt": "..."
}
```

Hinweis: `receive-message` löscht die Nachricht nicht. Sie wird nur kurz unsichtbar.

## 6. Lambda Logs prüfen

```bash
aws logs tail "/aws/lambda/<MessageHandlerFunctionName>" \
  --since 10m \
  --region eu-central-1
```

Erwartung:

```text
START RequestId
INFO Received event
END RequestId
REPORT RequestId
```

## 7. Ergebnis

Wenn alle Punkte funktionieren:

```text
API Gateway -> Lambda -> SQS erfolgreich getestet
```

