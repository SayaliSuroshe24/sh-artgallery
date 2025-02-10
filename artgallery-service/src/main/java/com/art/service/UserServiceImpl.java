package com.art.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.art.dao.UserDao;
import com.art.dto.UserDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.User;
import com.art.pojos.User.Role;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userRepository;

    @Autowired
    private ModelMapper mapper;
    
    

    @Override
    public List<UserDto> getAllUsers() {
 return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addUser (UserDto userDTO) {
        User user = mapper.map(userDTO, User.class);
        User savedUser  = userRepository.save(user);
        return "New User added with id " + savedUser.getUserId();
    }

    @Override
    public String deleteUser (Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "User  deleted";
        }
        throw new ResourceNotFoundException("Invalid User ID!");
    }

    @Override
    public UserDto getUserDetails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid User ID"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public String updateUser (Long userId, UserDto userDTO) {
        if (userRepository.existsById(userId)) {
            User user = mapper.map(userDTO, User.class);
            user.setUserId(userId);
            userRepository.save(user);
            return "Update success";
        }
        throw new ResourceNotFoundException("User  doesn't exist!");
    }
    
    
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        // Encrypt password before saving
       String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodedPassword);
        user.setPhoneNo(userDto.getPhoneNo());
        user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public UserDto loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        // Check if password matches
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials!");
        }

        return mapper.map(user, UserDto.class); // Return user details on successful login
    }
    
    
   
    
}