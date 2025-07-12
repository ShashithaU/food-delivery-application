package com.uok.order.service;

import com.uok.order.dto.OrderDTO;
import com.uok.order.dto.OrderDTOFromFrontEnd;
import com.uok.order.dto.UserDTO;
import com.uok.order.entity.Order;
import com.uok.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {

private final OrderRepository orderRepository;
private final SequenceGenerator sequenceGenerator;
private final RestTemplate restTemplate;
private final ModelMapper modelMapper;


    public OrderDTO saveOrder(OrderDTOFromFrontEnd orderDTOFromFrontEnd) {
    Integer newOrderID =sequenceGenerator.generateNextOrderId();
    UserDTO userDTO = fetchUserDetailsFromUserId(orderDTOFromFrontEnd.getUserId());
    Order orderToBeSaved = new Order(newOrderID,orderDTOFromFrontEnd.getFoodItemsDTOList(),orderDTOFromFrontEnd.getRestaurant(),userDTO);
    orderRepository.save(orderToBeSaved);
    return modelMapper.map(orderToBeSaved, OrderDTO.class);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {

return restTemplate.getForObject("http://USERINFO/user/fetchUserById/"+userId,UserDTO.class);

    }


}
