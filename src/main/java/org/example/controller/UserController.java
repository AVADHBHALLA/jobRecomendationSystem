package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.User;
import org.example.models.UserDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDto dto){
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            User user = userService.create(dto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        try{
            List<User> userList = userService.getAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> get(@PathVariable UUID userId){
        if(userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            User user = userService.get(userId);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Void> update(@PathVariable UUID userId, @RequestBody UserDto dto){
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            userService.update(userId, dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/deactivate/{userId}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID userId){
        if(userId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            userService.deactivate(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
