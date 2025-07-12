package com.uok.order.service;

import com.uok.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    RestTemplate restTemplate;

    @Mock
    OrderServiceTest orderServiceTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrder(){

    }
}
