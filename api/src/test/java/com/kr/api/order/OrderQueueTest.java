package com.kr.api.order;

import static org.junit.jupiter.api.Assertions.*;

import com.kr.api.data.OrderEntity;
import com.kr.api.data.OrderMenuEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class OrderQueueTest {

    private OrderQueue orderQueue;

    @BeforeEach
    public void setUp() {
        orderQueue = new OrderQueue();
    }

    @Test
    public void testAddOrder() {
        OrderEntity order = new OrderEntity(1, OrderMenuEnum.valueOf("burger"), 2, LocalDate.now());
        orderQueue.addOrder(order);

        assertEquals(1, orderQueue.getLast10Orders().size());
        assertEquals(order, orderQueue.getLast10Orders().get(0));
    }

    @Test
    public void testTakeOrder() throws InterruptedException {
        OrderEntity order = new OrderEntity(1, OrderMenuEnum.valueOf("burger"), 2, LocalDate.now());
        orderQueue.addOrder(order);

        OrderEntity takenOrder = orderQueue.takeOrder();
        assertEquals(order, takenOrder);
    }

    @Test
    public void testGetLast10Orders() {
        for (int i = 1; i <= 15; i++) {
            OrderEntity order = new OrderEntity(i, OrderMenuEnum.valueOf("burger"), 2, LocalDate.now());
            orderQueue.addOrder(order);
        }

        List<OrderEntity> last10Orders = orderQueue.getLast10Orders();
        assertEquals(10, last10Orders.size());
        assertEquals(6, last10Orders.get(0).getOrderNo());
        assertEquals(15, last10Orders.get(9).getOrderNo());
    }
}
