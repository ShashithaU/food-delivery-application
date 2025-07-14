package com.uok.order.controller;

import com.uok.order.dto.OrderDTO;
import com.uok.order.dto.OrderDTOFromFrontEnd;
import com.uok.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/saveOrder")
    public OrderDTO saveOrder(@RequestBody OrderDTOFromFrontEnd orderDTOFromFrontEnd) {
        return  orderService.saveOrder(orderDTOFromFrontEnd);

    }

}
