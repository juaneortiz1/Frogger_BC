package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.controllers;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto.UserDTO;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.User;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling user-related operations.
 * Provides endpoints to create a new user and to retrieve a list of all users.
 */
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for the controller.
     *
     * @param userService The service that handles user logic.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user in the system.
     *
     * @param userDTO The object containing the details of the user to be created.
     * @return HTTP response indicating the success of the operation.
     */
    @PostMapping("/creates")
    public ResponseEntity<String> creates(@RequestBody UserDTO userDTO) {
        System.out.println("Received request to create user with username: " + userDTO.getUsername());
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        userService.save(user);
        System.out.println("Registry Succeeded with " + userDTO.getUsername());
        return ResponseEntity.ok("User created successfully");
    }

    /**
     * Retrieves a list of all registered users in the system.
     *
     * @return A list of users.
     */
    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
