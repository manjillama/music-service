## Requirements

- Java 17
- DynamoDB

## Setup

Import this project in your IDE.

### Configuration

Change `application-dev.example.yml` to `application-dev.yml` and update configuration.

### Run locally

Run music-api using your IDE or run

```sh
mvn clean install
java -jar music-api/target/music-api-0.0.1-SNAPSHOT.jar
```

### Testing DynamoDB Streams → Lambda Locally

Use AWS SAM (Serverless Application Model)

AWS SAM lets you simulate DynamoDB Streams and invoke your Lambda locally.

**Prerequisites:**

- Install:
- AWS SAM CLI
- Docker (SAM runs Lambdas in Docker)

**Local Testing Flow**

1. Define a SAM template (template.yaml). See `template.yaml` file
2. Create a sample DynamoDB stream event. See `event.json` file
3. Invoke your Lambda locally - From `music-kinesis-adapter` dir run:
   ```sh
    sam build
    sam local invoke MusicKinesisAdapter --event event.json
    ```
