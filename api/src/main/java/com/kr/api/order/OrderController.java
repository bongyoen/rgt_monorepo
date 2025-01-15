package com.kr.api.order;

import com.kr.api.cmmn.ApiResponse;
import com.kr.api.order.dto.OrderCond;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    private final OrderQueue orderQueue;
    private final OrderPreService preService;

    public OrderController(OrderService orderService, OrderQueue orderQueue, OrderPreService preService) {
        this.orderQueue = orderQueue;
        this.preService = preService;
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PutMapping(value = "/putOrder")
    public ResponseEntity<ApiResponse> putOrder(@RequestBody OrderCond cond) {
        try {
            return new ResponseEntity<>(new ApiResponse(true, preService.orderPre(cond).getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/connSw")
    public ResponseEntity<ApiResponse> connSw() {
        return new ResponseEntity<>(new ApiResponse(true, "ok", orderQueue.getLast10Orders()), HttpStatus.OK);
    }
}
