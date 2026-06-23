# Agenda

Dauer: ca. 60 Minuten

## Ziel

Wir bauen eine kleine Serverless-Anwendung mit AWS CDK in Java.

```text
API Gateway
  POST /messages
    -> Lambda
      -> SQS
```

## Ablauf

|      Zeit | Thema                           |
|----------:| ------------------------------- |
|   0–5 min | Zielbild und Architektur        |
|  5–10 min | CDK App, Stack und Props        |
| 10–20 min | SQS Queue                       |
| 20–30 min | Resource Tagging                |
| 30–45 min | Lambda und IAM Grant            |
| 45–50 min | API Gateway und End-to-End-Test |
| 50–60 min | Cleanup und Fragen              |

## Inhalte

* CDK App in Java
* Stack-Struktur
* eigene Constructs
* zentrale Constants
* Props-Modell
* SQS Queue
* Tags
* Lambda Function
* API Gateway
* IAM Grants
* `cdk synth`
* `cdk diff`
* `cdk deploy`
* `cdk destroy`

## Ergebnis

Am Ende kann ein Request per `curl` gesendet werden:

```bash
curl -i -X POST "<MessageApiUrl>" \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello from CDK Hands-on"}'
```

Erwartete Antwort:

```json
{"status":"queued","message":"Hello from CDK Hands-on"}
```
