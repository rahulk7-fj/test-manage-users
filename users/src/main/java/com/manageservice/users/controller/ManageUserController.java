package com.manageservice.users.controller;

import com.manageservice.users.business.ManageUserBusiness;
import com.manageservice.users.model.ManageUserRequest;
import com.manageservice.users.model.ResponseWrapper;
import com.manageservice.users.model.User;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigInteger;

@RestController
@RequestScope
public class ManageUserController {

    @Autowired
    private ManageUserBusiness business;

    @GetMapping(value = "/test")
    public String test(@RequestParam(value = "name", defaultValue = "world") String name){
        return String.format("Hello %s!",name);
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper> create(
            @Valid @RequestBody ManageUserRequest manageUserRequest,
            @RequestHeader(name = "x-api-trackingid", required = true) String trackingId
            ){
        var userID = business.create(trackingId, manageUserRequest);
        var responeWrapper = ResponseWrapper.builder().id(userID.toString()).build();
        return new ResponseEntity<>(responeWrapper, HttpStatus.OK);
    }

    @GetMapping(value= "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable(name="id") BigInteger id){
      var userData = business.getUserData(id);
      var userWrapper = User.builder().username(userData.getUsername())
              .firstname(userData.getFirstname()).lastname(userData.getLastname())
              .user_email(userData.getUser_email()).user_phone_num(userData.getUser_phone_num())
              .build();
      return new ResponseEntity<User>(userWrapper, HttpStatus.OK);
    }


}
