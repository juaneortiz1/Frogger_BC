package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.repository;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing User entities in MongoDB.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds a user by their username.
     *
     * @param name The username to search for.
     * @return The user with the specified username.
     */
    User findByName(String name);
}
