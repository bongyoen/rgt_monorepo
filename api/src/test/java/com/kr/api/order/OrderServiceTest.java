package com.kr.api.order;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kr.api.config.web_socket.MyWebSocketHandler;
import com.kr.api.data.InMemoryMap;
import com.kr.api.data.OrderEntity;
import com.kr.api.data.OrderMenuEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderServiceTest {

    @Mock
    private InMemoryMap inMemoryMap;

    @Mock
    private MyWebSocketHandler myWebSocketHandler;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(inMemoryMap);
    }

    @Test
    public void testPutOrder2() throws JsonProcessingException {
        OrderEntity order = new OrderEntity(1, OrderMenuEnum.valueOf("burger"), 2, LocalDate.now());
        orderService.putOrder2(order);
        String jsonString = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(order);
        verify(myWebSocketHandler, times(1)).broadcast(jsonString);
    }
}
