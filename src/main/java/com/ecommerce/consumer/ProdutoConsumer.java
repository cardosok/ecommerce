package com.ecommerce.consumer;

import com.ecommerce.entity.ProdutoEntity;
import com.ecommerce.service.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdutoConsumer {

    @Value("${topic.name.consumer")
    private String topicName;

    @Autowired
    private ProdutoService produtoService;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "l7ms02t4-consumers")
    public void consume(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Tópico: {}", topicName);
        log.info("Chave: {}", payload.key());
        log.info("Obj: {}", payload.value());

        ObjectMapper objectMapper = new ObjectMapper();
        ProdutoEntity produto = objectMapper.readValue(payload.value(), ProdutoEntity.class);

        produtoService.save(produto);
    }

}
