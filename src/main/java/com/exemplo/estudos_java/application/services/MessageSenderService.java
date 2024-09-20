package com.exemplo.estudos_java.application.services;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.exemplo.estudos_java.config.ServiceBusProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessageSenderService {

    @Autowired
    private ServiceBusProperties serviceBusProperties;

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    public void sendMessage(String queueName, String messageContent) {
        try (ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient()) {

            var message = new ServiceBusMessage(messageContent);
            senderClient.sendMessage(message);

            System.out.println("Mensagem enviada com sucesso para a fila: " + queueName);
        }
    }
}
