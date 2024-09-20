package com.example.DummyProject.controller;


import com.example.DummyProject.exception.UserNotFoundException;
import com.example.DummyProject.model.User;
import com.example.DummyProject.repository.UserRepository;
import com.example.DummyProject.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    //get all users
    @GetMapping("/Users")
    public ResponseEntity<List<User>> filterAllUsers(String firstName,
    String lastName){

        List<User> users = userService.filterUsers(firstName, lastName);
        return ResponseEntity.ok(users);
//        return userRepository.findAll();
    }




    //create user rest api
    @PostMapping("/Users")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

   //get user by id rest api
    @GetMapping("/Users/{id}")
   public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()) throw new ObjectNotFoundException(user,"user not found with id"+id);
        return ResponseEntity.ok(user);
   }

  //update user rest api
    @PutMapping("/Users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userDetails) throws UserNotFoundException {
//        first retrieving the data
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("user not found with id") ;
//        now updating the data
        User currentUser= user.get();
        currentUser.setFirstName(userDetails.getFirstName());
        currentUser.setLastName(userDetails.getLastName());
        currentUser.setEmailId(userDetails.getEmailId());

        User updateUser = userRepository.save(currentUser);
        return ResponseEntity.ok(updateUser);
    }

//    delete user rest api
    @DeleteMapping("/Users/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("user not found with id") ;
        User currentUser= user.get();

        userRepository.delete(currentUser);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
