package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@ApplicationScoped
public class SqsProducer {

    private final SqsClient sqsClient;
    
    @ConfigProperty(name = "quarkus.sqs.queue.url")
    String queueUrl;

    public SqsProducer(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(String message) {
        SendMessageRequest request = SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .messageBody(message)
            .build();

        sqsClient.sendMessage(request);
    }
}
