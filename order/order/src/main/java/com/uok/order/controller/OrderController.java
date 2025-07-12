package com.uok.order.controller;

import com.uok.order.dto.OrderDTO;
import com.uok.order.dto.OrderDTOFromFrontEnd;
import com.uok.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/saveOrder")
    public OrderDTO saveOrder(@RequestBody OrderDTOFromFrontEnd orderDTOFromFrontEnd) {
        return  orderService.saveOrder(orderDTOFromFrontEnd);

    }

}
