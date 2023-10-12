package com.blogr.services;

import com.blogr.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, int userId);
    UserDTO getUserById(int userId);
    List<UserDTO> getAllUsers();
    void deleteUser(int userId);
}
