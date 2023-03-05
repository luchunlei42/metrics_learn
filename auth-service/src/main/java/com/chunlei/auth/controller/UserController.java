package com.chunlei.auth.controller;

import com.chunlei.auth.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public Principal getUser(Principal principal){
        return principal;
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping
    public void createUser(@RequestBody User user){
        //TODO
    }
}
