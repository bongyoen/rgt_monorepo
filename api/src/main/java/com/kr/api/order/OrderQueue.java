package com.kr.api.order;

import com.kr.api.data.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class OrderQueue {
    private final BlockingQueue<OrderEntity> queue = new LinkedBlockingQueue<>();
    private final List<OrderEntity> OrderList = new ArrayList<>();

    public void addOrder(OrderEntity order) {
        queue.add(order);
        OrderList.add(order);
    }

    public OrderEntity takeOrder() throws InterruptedException {
        return queue.take();
    }

    public List<OrderEntity> getLast10Orders() {
        return OrderList.subList(Math.max(OrderList.size() - 10, 0), OrderList.size());
    }
}



