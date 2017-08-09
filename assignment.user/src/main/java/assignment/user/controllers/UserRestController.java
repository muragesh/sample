package assignment.user.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import assignment.user.models.User;
import assignment.user.repositories.UserLoginTokenRepository;
import assignment.user.repositories.UserRepository;
 @RestController
@RequestMapping("/users")
public class UserRestController {

        @Autowired
        private UserRepository repository;
        @Autowired
        private UserLoginTokenRepository tokenRepo;

        
        @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<Collection<User>> getAllUsers(){
            return new ResponseEntity<>((Collection<User>) repository.findAll(), HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.GET, value = "/{id}")
        public ResponseEntity<User> getUserWithId(@PathVariable Long id) {
            return new ResponseEntity<>(repository.findOne(id),HttpStatus.OK);
        }
 
        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> addUser(@RequestBody User input) {
            return new ResponseEntity<>(repository.save(input), HttpStatus.CREATED);
        }
}
