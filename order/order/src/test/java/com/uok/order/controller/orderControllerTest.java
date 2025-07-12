package com.uok.order.controller;

import com.uok.order.dto.OrderDTO;
import com.uok.order.dto.OrderDTOFromFrontEnd;
import com.uok.order.service.OrderService;
import com.uok.order.service.OrderServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class orderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder(){
        OrderDTO mockorderDTO = new OrderDTO();
        OrderDTOFromFrontEnd mockOrderDTOFromFrontEnd = new OrderDTOFromFrontEnd();
        when(orderService.saveOrder(mockOrderDTOFromFrontEnd)).thenReturn(mockorderDTO);

        OrderDTO response = orderController.saveOrder(mockOrderDTOFromFrontEnd);

        assertEquals(response,mockorderDTO);

    }


}
