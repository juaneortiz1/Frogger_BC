package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user in the system.
 * Mapped to the "users" collection in MongoDB.
 */
@Document(collection = "users")
public class User {

    @Id
    private String name;

    private String pass;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name The username.
     * @param pass The password.
     */
    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the username.
     *
     * @param name The username to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password.
     *
     * @return The password.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the password.
     *
     * @param pass The password to set.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", pass=" + pass + "]";
    }
}
