package ba.edu.ibu.job.search.platform.rest.controllers;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.service.UserService;
import ba.edu.ibu.job.search.platform.rest.dto.UserDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    /** Get all users */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /** Get a user by ID */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /** Add a user */
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    /**Update a user by ID*/
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    /**Delete a user*/
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**Filter users by email*/
    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<UserDTO> filterUser(@RequestParam String email) {
        return ResponseEntity.ok(userService.filterByEmail(email));
    }



    @GetMapping("/send-to-all")
    public String sendEmailToAllUsers(@RequestParam String message){
        return userService.sendEmailToAllUsers(message);
    }
}
