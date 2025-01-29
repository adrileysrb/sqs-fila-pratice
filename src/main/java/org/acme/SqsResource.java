package org.acme;

import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sqs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SqsResource {

    @Inject
    SqsProducer producer;

    @POST
    @Path("/send")
    public Response sendMessage(MessageDTO request) {
        producer.sendMessage(request.getMessage());
        return Response.ok("Mensagem enviada para SQS!").build();
    }

    public static class MessageDTO {
        public String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
