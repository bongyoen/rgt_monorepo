package com.kr.api.order;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kr.api.data.InMemoryMap;
import com.kr.api.data.OrderEntity;
import com.kr.api.order.dto.OrderCond;
import com.kr.api.order.dto.OrderRslt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class OrderPreServiceTest {

    @Mock
    private OrderQueue orderQueue;

    @Mock
    private InMemoryMap inMemoryMap;

    @InjectMocks
    private OrderPreService orderPreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderPreService = new OrderPreService(orderQueue, inMemoryMap);
    }

    @Test
    public void testOrderPre_Success() {
        OrderCond cond = new OrderCond();
        List<OrderCond.OrderCondEq> condEqList = new ArrayList<>();
        condEqList.add(new OrderCond.OrderCondEq("burger", 2));
        cond.setCondEqList(condEqList);

        OrderRslt result = orderPreService.orderPre(cond);

        assertTrue(result.getIsOk());
        assertEquals("주문이 완료되었습니다.", result.getMessage());
        verify(inMemoryMap, times(1)).backup();
        verify(inMemoryMap, times(1)).put(anyString(), any(OrderEntity.class));
        verify(orderQueue, times(1)).addOrder(any(OrderEntity.class));
    }

    @Test
    public void testOrderPre_Failure_InvalidMenu() {
        OrderCond cond = new OrderCond();
        List<OrderCond.OrderCondEq> condEqList = new ArrayList<>();
        condEqList.add(new OrderCond.OrderCondEq("", 2));
        cond.setCondEqList(condEqList);

        OrderRslt result = orderPreService.orderPre(cond);

        assertFalse(result.getIsOk());
        assertEquals("잘못된 상품입니다.", result.getMessage());
        verify(inMemoryMap, times(1)).backup();
        verify(inMemoryMap, times(1)).rollback();
    }

    @Test
    public void testOrderPre_Failure_InvalidQuantity() {
        OrderCond cond = new OrderCond();
        List<OrderCond.OrderCondEq> condEqList = new ArrayList<>();
        condEqList.add(new OrderCond.OrderCondEq("burger", 0));
        cond.setCondEqList(condEqList);

        OrderRslt result = orderPreService.orderPre(cond);

        assertFalse(result.getIsOk());
        assertEquals("잘못된 수량입니다.", result.getMessage());
        verify(inMemoryMap, times(1)).backup();
        verify(inMemoryMap, times(1)).rollback();
    }

    @Test
    public void testOrderPre_Failure_EmptyOrderList() {
        OrderCond cond = new OrderCond();
        cond.setCondEqList(new ArrayList<>());

        OrderRslt result = orderPreService.orderPre(cond);

        assertFalse(result.getIsOk());
        assertEquals("주문이 없습니다.", result.getMessage());
        verify(inMemoryMap, times(1)).backup();
        verify(inMemoryMap, times(1)).rollback();
    }
}
