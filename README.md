# Music Service

Scalable Spring Boot application using DynamoDB, DynamoDB streams, Kinesis, Lambda and CloudFormation.

# Requirements

- Java 17
- AWS CLI (Install guide)
- Docker (for local Lambda testing)
- AWS SAM CLI

<img src="https://raw.githubusercontent.com/manjillama/music-service/docs/architecture.png" />

# Getting Started

1. Clone and Import

   Import the project into your IDE of choice (e.g., IntelliJ, VSCode).

2. Set Up AWS CLI (for local access)

   ```bash
   aws configure
   ```

   Ensure credentials and default region are properly configured.

# Configuration

1. Rename the sample config file:

   ```bash
   cp music-api/src/main/resources/application-dev.example.yml music-api/src/main/resources/application-dev.yml
   ```

2. Edit `application-dev.yml` and update AWS region and any other necessary values.

# Running Locally

**Run music-api**

Via terminal:

```bash
mvn clean install
java -DENV=dev -jar music-api/target/music-api-0.0.1-SNAPSHOT.jar
```

Or, directly from your IDE. Make sure to pass ENV=dev environment variable when running locally.

# Testing DynamoDB Streams → Lambda Locally

This project includes a Lambda (music-kinesis-adapter) that listens to DynamoDB Streams. You can test this flow locally
using AWS SAM.

## Prerequisites

- AWS SAM CLI installed
- Docker running

## Local Testing Flow

1. Define the function and event source in template.yaml (see music-kinesis-adapter/template.yaml).
2. Create a test stream event, e.g., event.json (sample included).
3. Invoke the Lambda locally from the music-kinesis-adapter directory:
   ```bash
   sam build
   sam local invoke MusicKinesisAdapter --event event.json
   ```

### Understanding template.yml

template.yml can serve two purposes depending on how it’s used:

**Local Simulation**

```bash
sam local invoke
sam local start-api
```

- Runs Lambda/API Gateway locally using Docker
- Useful for testing integrations before deploying

**Real AWS Deployment**

```bash
sam build
sam deploy --guided
```

- Packages and deploys your resources (Lambda, DynamoDB, etc.) to AWS via CloudFormation
- Supports IAM roles, environment variables, event sources, etc.

Changes to template.yml will affect production deployment unless used exclusively for local testing.