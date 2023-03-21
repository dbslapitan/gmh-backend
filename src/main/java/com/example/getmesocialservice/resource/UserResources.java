package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.InvalidTokenException;
import com.example.getmesocialservice.exception.NameIsRootException;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.service.FirebaseService;
import com.example.getmesocialservice.service.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserResources {

    @Autowired
    private UserService userService;
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public User createUser(@RequestBody @Valid User user, @RequestHeader(name = "idToken") String idToken) throws NameIsRootException, IOException, FirebaseAuthException, InvalidTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if(firebaseUser != null){
            if(user.getName().equalsIgnoreCase("root")){
                throw new NameIsRootException();
            }
            return userService.createUser(user);
        }
        return null;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId).get();
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException, InvalidTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if(firebaseUser != null){
            return userService.updateUser(user);
        }
        return null;
    }

    @DeleteMapping
    public void deleteUser(@RequestParam(name = "userId") String userId, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException, InvalidTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            userService.deleteUser(userId);
        }
    }
}
