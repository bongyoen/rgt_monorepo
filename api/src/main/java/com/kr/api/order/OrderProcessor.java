package com.kr.api.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kr.api.data.OrderEntity;
import com.kr.api.order.OrderQueue;
import com.kr.api.order.OrderService;
import com.kr.api.order.dto.OrderCond;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class OrderProcessor {

    private final OrderQueue orderQueue;
    private final OrderService orderService;
    private final ExecutorService executorService;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public OrderProcessor(OrderQueue orderQueue, OrderService orderService) {
        this.orderQueue = orderQueue;
        this.orderService = orderService;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    @PostConstruct
    public void init() {
        startProcessing();
    }

    public void startProcessing() {

        for (int i = 0; i < ((ThreadPoolExecutor) executorService).getCorePoolSize(); i++) {
            executorService.submit(() -> {
                while (running.get()) {
                    try {
                        OrderEntity order = orderQueue.takeOrder();
                        orderService.putOrder2(order);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    public void stopProcessing() {
        running.set(false);
        executorService.shutdownNow();
    }

    // 테스트 전용 메서드
    public void processSingleOrder() throws InterruptedException {
        System.out.println("Starting order processing...");

        OrderEntity order = orderQueue.takeOrder();
        orderService.putOrder2(order);
    }

}
