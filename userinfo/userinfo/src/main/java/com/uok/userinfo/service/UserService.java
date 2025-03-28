package com.uok.userinfo.service;

import com.uok.userinfo.dto.UserDTO;
import com.uok.userinfo.entity.User;
import com.uok.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO addUser(UserDTO userDTO) {
         userRepository.save(modelMapper.map(userDTO, User.class));
            return userDTO;
    }


    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
        Optional<User> fetchUser = userRepository.findById(userId);
        if(fetchUser.isPresent())
            return new ResponseEntity<>(modelMapper.map(fetchUser.get(),UserDTO.class), HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            
        }

    }

