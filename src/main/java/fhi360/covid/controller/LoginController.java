package fhi360.covid.controller;

import fhi360.covid.dto.UserLoginDto;
import fhi360.covid.exception.ResourceNotFoundException;
import fhi360.covid.response.ApiResponse;
import fhi360.covid.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    UsersService usersService;

    //user login api
    @PostMapping("/login")
    public ApiResponse login(@Valid UserLoginDto loginDto) throws ResourceNotFoundException {
        return usersService.login(loginDto);
    }
}