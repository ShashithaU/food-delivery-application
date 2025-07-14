package com.uok.userinfo.controller;

import com.uok.userinfo.dto.UserDTO;
import com.uok.userinfo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin
@Tag(name = "User Management")
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUserById/{id}")
    public ResponseEntity<UserDTO> fetchUserDetailsById(@PathVariable Integer id){
    return userService.fetchUserDetailsById(id);
    }


}
