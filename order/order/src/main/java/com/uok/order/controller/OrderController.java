package com.uok.order.controller;

import com.uok.order.dto.OrderDTO;
import com.uok.order.dto.OrderDTOFromFrontEnd;
import com.uok.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Order Management")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/saveOrder")
    public OrderDTO saveOrder(@RequestBody OrderDTOFromFrontEnd orderDTOFromFrontEnd) {
        return  orderService.saveOrder(orderDTOFromFrontEnd);

    }

}
