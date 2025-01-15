package com.kr.api.order;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kr.api.data.InMemoryMap;
import com.kr.api.data.OrderEntity;
import com.kr.api.data.OrderMenuEnum;
import com.kr.api.order.*;
import com.kr.api.order.dto.OrderCond;
import com.kr.api.order.dto.OrderRslt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderIntegrationTest {

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderPreService orderPreService;

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private OrderQueue orderQueue;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InMemoryMap inMemoryMap;

    @BeforeEach
    public void setUp() {
        inMemoryMap.clear(); // 테스트 전 초기화
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
        assertEquals(1, orderQueue.getLast10Orders().size());
        assertEquals("burger", orderQueue.getLast10Orders().get(0).getMenu().name());
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
    }

    @Test
    public void testOrderPre_Failure_EmptyOrderList() {
        OrderCond cond = new OrderCond();
        cond.setCondEqList(new ArrayList<>());

        OrderRslt result = orderPreService.orderPre(cond);

        assertFalse(result.getIsOk());
        assertEquals("주문이 없습니다.", result.getMessage());
    }
}
