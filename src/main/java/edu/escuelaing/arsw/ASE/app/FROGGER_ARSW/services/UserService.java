package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.services;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.User;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing user-related operations.
 * Provides methods to save a user, find all users, and find a user by name.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a user to the repository.
     *
     * @param user The user to save.
     * @return The saved user.
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return A list of all users.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds a user by their name.
     *
     * @param name The name of the user to find.
     * @return The user with the specified name, or null if no such user exists.
     */
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
