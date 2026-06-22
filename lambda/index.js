exports.handler = async () => ({
    statusCode: 200,
    headers: {
        "content-type": "application/json"
    },
    body: JSON.stringify({
        message: "Hello from CDK Java Serverless Hands-on!"
    })
});
