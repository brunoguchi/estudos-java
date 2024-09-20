package com.exemplo.estudos_java.application.services;

import com.azure.messaging.servicebus.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueListenerService1 {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${azure.servicebus.queueName1}")
    private String queueName;

    private ServiceBusProcessorClient processorClient;

    @PostConstruct
    public void startListening() {
        processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(this::processMessage)
                .processError(this::processError)
                .buildProcessorClient();

        processorClient.start();
        System.out.println("Ouvindo mensagens da fila: " + queueName);
    }

    private void processMessage(ServiceBusReceivedMessageContext serviceBusReceivedMessageContext) {
        var message = serviceBusReceivedMessageContext.getMessage();
        System.out.println("Fila 1 - Mensagem recebida: " + message.getBody().toString());
    }

    private void processError(ServiceBusErrorContext errorContext) {
        System.out.println("Erro ao processar mensagem da fila 1: " + errorContext.getException().getMessage());
    }

    @PreDestroy
    public void stopListening() {
        if (processorClient != null) {
            processorClient.stop();
            System.out.println("Parando a escuta de mensagens da fila: " + queueName);
        }
    }
}
