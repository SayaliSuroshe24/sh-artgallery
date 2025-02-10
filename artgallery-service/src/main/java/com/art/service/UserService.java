package com.art.service;

import java.util.List;

import com.art.dto.UserDto;

public interface UserService {
    List<UserDto> getAllUsers();
    String addUser(UserDto userDTO);
    String deleteUser(Long userId);
   UserDto getUserDetails(Long userId);
    String updateUser(Long userId, UserDto userDTO);
    String registerUser(UserDto userDto);
    UserDto loginUser(String email, String password); 
}