package fhi360.covid.controller;

import fhi360.covid.dto.UserDto;
import fhi360.covid.exception.PasscodeExistsException;
import fhi360.covid.exception.ResourceNotFoundException;
import fhi360.covid.model.Users;
import fhi360.covid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("users")
    List<Users> getUsers() {
        return userRepository.findAll();
    }


    //get user by Id
    @GetMapping("user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }

    //create user
    @PostMapping("user")
    public ResponseEntity<Object> createUser(@Valid Users users) throws PasscodeExistsException {
        Users usersPasscode = userRepository.findByPasscode(users.getPasscode());
        if (usersPasscode != null) {
            throw new PasscodeExistsException(String.format("User with Passcode %s already exist", users.getPasscode()));
        }
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setUserType("User");
        return new ResponseEntity<>(userRepository.save(users), HttpStatus.CREATED);
    }

    @PatchMapping("user/{id}")
    public Users updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        System.out.println("Update User with ID = " + id + "...");
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        users.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        final Users updatedUser = userRepository.save(users);
        System.out.println("Updated User " + updatedUser);
        return userRepository.save(updatedUser);
    }

    @PutMapping("user/{id}")
    public Users updateUsers(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        System.out.println("Update User with ID = " + id + "...");
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        users.setPassword(userDto.getPassword());
        final Users updatedUser = userRepository.save(users);
        System.out.println("Updated User " + updatedUser);
        return userRepository.save(updatedUser);
    }

    //delete user
    @DeleteMapping("user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}