package edu.manjiltamang.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

public class KinesisAdapterHandler implements RequestHandler<DynamodbEvent, Void> {
    @Override
    public Void handleRequest(DynamodbEvent event, Context context) {
        // TODO: Extract records from event and publish to Kinesis
        System.out.println("DynamoDB streams event: " + event);
        System.out.println("Context: " + context);
        return null;
    }
}
