package com.kr.api.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderTestService orderTestService;

    public OrderController(OrderTestService orderTestService) {
        this.orderTestService = orderTestService;
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() {

        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PutMapping(value = "/putOrder")
    public ResponseEntity<?> putOrder(@RequestBody String orderStr) {

        String abc = orderTestService.OrderProcess();
        return new ResponseEntity<>("hi", HttpStatus.OK);
    }
}
