package fhi360.covid.controller;

import fhi360.covid.model.UserHealthData;
import fhi360.covid.repository.UserHealthDataRepository;
import fhi360.covid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserHealthDataController {

    @Autowired
    UserHealthDataRepository userHealthDataRepository;

    @Autowired
    UserRepository usersRepository;


    @GetMapping("userHealthData")
    List<UserHealthData> getAllUserHealthData() {
        return userHealthDataRepository.findAll();
    }

    @PostMapping("userHealthData")
    public UserHealthData createUserHealthData(@Valid UserHealthData userHealthData) {
        return userHealthDataRepository.save(userHealthData);
    }


    @GetMapping("userHealthData/{userId}")
    List<UserHealthData> getUserHealthDataUserId(@PathVariable Long userId) {
        return userHealthDataRepository.findUserHealthDataByUserId(userId);
    }

}