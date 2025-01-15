package com.kr.api.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kr.api.config.web_socket.MyWebSocketHandler;
import com.kr.api.data.InMemoryMap;
import com.kr.api.data.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderService(InMemoryMap inMemoryMap) {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void putOrder2(OrderEntity order) {
        try {
            String jsonString = objectMapper.writeValueAsString(order);
            MyWebSocketHandler.broadcast(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
