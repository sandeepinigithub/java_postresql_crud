package com.sandeep.java_postresql_crud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
	
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getStudent() {
        return userService.getStudents();
    }

    @PostMapping
    public void registerUser(@RequestBody User user) {
        userService.addNewStudent(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteStudent(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        userService.updateStudent(userId, name, email);
    }	

}
