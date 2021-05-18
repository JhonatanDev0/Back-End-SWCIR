package com.swcir.swcirsystem.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.swcir.swcirsystem.Models.User;
import com.swcir.swcirsystem.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jsonusers")
public class JSONUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/find/all")
    public Iterable<User> getAllUsers(){
        Iterable<User> listUsers = this.userRepository.findAll();

        if (listUsers == null) {
            throw new EmptyResultDataAccessException("Nenhum usuario encontrado", 1);
        }

        return listUsers;
    }

    @GetMapping(path="/find/{userId}")
    public User getUserById(@PathVariable int userId){
        
        User userRecovered = new User();

        try {
            userRecovered = this.userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            throw new EmptyResultDataAccessException("Nao existe usuario com o ID informado", 1);
        }
        return userRecovered;
    }

    @PostMapping("/add")
        public ResponseEntity<User> create(@RequestBody User user) 
            throws URISyntaxException {
            User createdUser = userRepository.save(user);
            if (createdUser == null) {
                return ResponseEntity.notFound().build();
            } else {
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getUserId())
                .toUri();

                return ResponseEntity.created(uri)
                .body(createdUser);
            }
        }

    
        //CORRIGIR O PUTMAPPING
    @PutMapping("/edit/{id}")
        public ResponseEntity<User> update(@RequestBody User user, @PathVariable int userId) {
            User updatedUser = this.getUserById(userId);
            if (updatedUser == null) {
                return ResponseEntity.notFound().build();
            } else {
                updatedUser = userRepository.save(updatedUser);
                return ResponseEntity.ok(updatedUser);
            }
}

    @DeleteMapping(path="/delete/{userId}")
        public String deleteUserById(@PathVariable int userId){

            User userToBeDeleted = new User();
            userToBeDeleted = this.getUserById(userId);

            if(userToBeDeleted == null){
                throw new EmptyResultDataAccessException("Usuario nao encontrado", 1);
            }

            userRepository.deleteById(userId);
            return "User " + userId + " has been deleted.";
        }
        
}