package com.example.DummyProject.service;

import com.example.DummyProject.model.User;
import com.example.DummyProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> filterUsers(String firstName, String lastName){
        if (firstName!= null && !firstName.isBlank() && lastName != null
                && !lastName.isBlank()){
            return userRepository.findByFirstNameAndLastName(firstName,lastName);
        } else if (lastName != null && !lastName.isEmpty()) {
            return userRepository.findByLastNameContaining(lastName);
        }else if (firstName != null && !firstName.isEmpty()) {
        return userRepository.findByFirstNameContaining(firstName);
        } else{
            return userRepository.findAll();
        }
    }
}
