# Cleanup

## Ziel

Nach dem Workshop sollten keine Test-Ressourcen im AWS Account übrig bleiben -> Kosten!!.

Zu löschen sind:

* CloudFormation Stack
* API Gateway
* Lambda
* IAM Role / Policy
* SQS Queue
* CloudWatch Log Group

## 1. Stack löschen

```bash id="effgqo"
cdk destroy
```

Bestätigen:

```text id="3z2jck"
Are you sure you want to delete: Prod-Serverless-HandsOn (y/n) y
```

Erwartung:

```text id="a5ul11"
Prod-Serverless-HandsOn: destroyed
```

## 2. Queue prüfen

```bash id="i582rp"
aws sqs get-queue-url \
  --queue-name "cdk-handson-message-queue" \
  --region eu-central-1
```

Erwartung:

```text id="8ol3xp"
AWS.SimpleQueueService.NonExistentQueue
```

## 3. Log Groups prüfen

```bash id="4kqjfp"
aws logs describe-log-groups \
  --log-group-name-prefix "/aws/lambda/Prod-Serverless-HandsOn" \
  --region eu-central-1
```

Erwartung:

```json id="juoqi7"
{
  "logGroups": []
}
```

## 4. Stack prüfen

```bash id="rwqoyc"
aws cloudformation describe-stacks \
  --stack-name Prod-Serverless-HandsOn \
  --region eu-central-1
```

Erwartung:

```text id="u95w4r"
Stack with id Prod-Serverless-HandsOn does not exist
```

## 5. Lokale Artefakte löschen

```bash id="v7m2lc"
rm -rf target cdk.out
```

## 6. Git prüfen

```bash id="n286h7"
git status
```

Erwartung:

```text id="yqpf4n"
nothing to commit, working tree clean
```

## Optional: manuelles Löschen der Log Group

Nur falls eine Log Group stehen bleibt:

```bash id="dlrb5g"
aws logs delete-log-group \
  --log-group-name "/aws/lambda/<MessageHandlerFunctionName>" \
  --region eu-central-1
```

## Kostenhinweis

Nach jedem Testlauf ausführen:

```bash id="0hzi6y"
cdk destroy
```

Keine Ressourcen stehen lassen.

