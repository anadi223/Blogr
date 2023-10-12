package com.blogr.services;

import com.blogr.entities.User;
import com.blogr.exceptions.ResourceNotFoundException;
import com.blogr.payloads.UserDTO;
import com.blogr.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService{

    private UserRepo repo;

    UserServiceImpl(UserRepo repo){
        this.repo = repo;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = dtoToUser(userDto);
        User savedUser = repo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int userId) {
        Optional<User> user = repo.findById(userId);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setName(userDTO.getName());
            user1.setEmail(userDTO.getEmail());
            user1.setPassword(userDTO.getPassword());
            user1.setAbout(userDTO.getAbout());
            User updatedUser = repo.save(user1);
            return userToDto(updatedUser);
        }else{
            throw new ResourceNotFoundException("User","User ID", userId);
        }
    }

    @Override
    public UserDTO getUserById(int userId) {
        Optional<User> user = repo.findById(userId);
        if(user.isPresent()){
            return userToDto(user.get());
        }
        else{
            throw new ResourceNotFoundException("User Not Found","With User ID ", userId);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repo.findAll();
        return users.stream().map(user -> userToDto(user)).toList();
    }

    @Override
    public void deleteUser(int userId) {
      User user =  repo.findById(userId).orElseThrow(()->
              new ResourceNotFoundException("User Not Found","With User ID ", userId));
      repo.delete(user);
    }



    private User dtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO userToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAbout(user.getAbout());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
