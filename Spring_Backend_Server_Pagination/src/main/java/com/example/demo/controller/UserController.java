package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.APIResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<APIResponse> addUser(
            @RequestBody UserEntity userEntity
    ){

        return ResponseEntity.ok(userService.addUser(userEntity));

    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<List<UserEntity>> fetchAllUsers(){

        return ResponseEntity.ok(userService.fetchAllUsers());

    }

    @GetMapping("/paginate/{pageNumber}/{pageSize}/{sortBy}/{direction}")
    public ResponseEntity<Page<UserEntity>> fetchUserInPages(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("sortBy") String sortBy,
            @PathVariable("direction") String direction
    ){

        return ResponseEntity.ok(userService.paginate(pageNumber, pageSize, sortBy, direction));

    }

    @DeleteMapping("/deleteUserByUserEmail/{userEmail}")
    public ResponseEntity<Boolean> deleteUserByUserEmail(
            @PathVariable("userEmail") String userEmail
    ){

        return ResponseEntity.ok(userService.deleteUserByUserEmail(userEmail));

    }

}
