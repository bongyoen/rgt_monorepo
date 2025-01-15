package com.kr.api.order;

import com.kr.api.data.InMemoryMap;
import com.kr.api.data.OrderEntity;
import com.kr.api.data.OrderMenuEnum;
import com.kr.api.order.dto.OrderCond;
import com.kr.api.order.dto.OrderRslt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class OrderPreService {
    private final OrderQueue orderQueue;
    private final InMemoryMap inMemoryMap;
    private final AtomicLong sequence;

    public OrderPreService(OrderQueue orderQueue, InMemoryMap inMemoryMap) {
        this.orderQueue = orderQueue;
        this.inMemoryMap = inMemoryMap;
        this.sequence = new AtomicLong();
    }

    public OrderRslt orderPre(OrderCond cond) {
        inMemoryMap.backup();
        try {
            return OrderProcess(cond);
        } catch (Exception e) {
            inMemoryMap.rollback();
            log.error(e.getMessage());
            return new OrderRslt(e.getMessage(), false);
        }
    }

    private OrderRslt OrderProcess(OrderCond cond) {
        OrderMenuEnum menu;
        LocalDate date = LocalDate.now();
        List<OrderEntity> orderList = new ArrayList<>();

        if (cond.getCondEqList().isEmpty()) {
            throw new RuntimeException("주문이 없습니다.");
        }

        for (OrderCond.OrderCondEq orderCondEq : cond.getCondEqList()) {

            if (orderCondEq.getMenu() == null || orderCondEq.getMenu().isBlank()) {
                throw new RuntimeException("잘못된 상품입니다.");
            }
            if (orderCondEq.getQntty() == null || orderCondEq.getQntty() < 1) {
                throw new RuntimeException("잘못된 수량입니다.");
            }

            try {
                menu = OrderMenuEnum.valueOf(orderCondEq.getMenu());
            } catch (Exception e) {
                throw new RuntimeException("없는 상품입니다.");
            }

            long orderId = sequence.incrementAndGet();

            OrderEntity orderEntity = new OrderEntity((int) orderId, menu, orderCondEq.getQntty(), date);
            inMemoryMap.put(String.valueOf(orderId), orderEntity);
            orderList.add(orderEntity);
        }

        for (OrderEntity order : orderList) {
            orderQueue.addOrder(order);
        }

        return new OrderRslt("주문이 완료되었습니다.", true);
    }
}
