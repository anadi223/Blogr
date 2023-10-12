package com.blogr.controllers;

import com.blogr.payloads.ApiResponse;
import com.blogr.payloads.UserDTO;
import com.blogr.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }
    //POST - create User
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
    UserDTO createdUserDto = userService.createUser(userDTO);
    return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }



    //PUT - update user
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable int userId){
        UserDTO updatedUser = userService.updateUser(userDTO,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);
    }

    //DELETE delete user
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
    //GET user get
    @GetMapping("/getUser/{userId}")
    public UserDTO getUser(@PathVariable int userId){
        return userService.getUserById(userId);
    }
    @GetMapping("/getUser")
    public List<UserDTO> getAllUser(){
        return userService.getAllUsers();
    }
}
