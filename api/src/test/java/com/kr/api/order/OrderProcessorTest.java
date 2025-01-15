package com.kr.api.order;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kr.api.data.OrderEntity;
import com.kr.api.data.OrderMenuEnum;
import com.kr.api.order.OrderQueue;
import com.kr.api.order.OrderService;
import com.kr.api.order.OrderProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class OrderProcessorTest {

    @Mock
    private OrderQueue orderQueue;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderProcessor orderProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderProcessor = new OrderProcessor(orderQueue, orderService);
    }

    @Test
    public void testProcessSingleOrder() throws InterruptedException, JsonProcessingException {
        OrderEntity order = new OrderEntity(1, OrderMenuEnum.valueOf("burger"), 2, LocalDate.now());
        when(orderQueue.takeOrder()).thenReturn(order);

        orderProcessor.processSingleOrder();

        verify(orderService, times(1)).putOrder2(order);
    }
}
