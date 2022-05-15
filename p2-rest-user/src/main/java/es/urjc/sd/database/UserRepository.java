package es.urjc.sd.database;

/* ********************************************************************* */

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import es.urjc.sd.p2.User;

/* ********************************************************************* */
/**
 * Interface to make use of the database with User objects
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    /**
     * Query to search all the users in the database.
     * @param identifier Primary key of the table in the database.
     * @return A list with all the users inside.
     */
    List<User> findAll(int identifier);

    /**
     * Query to find an user by it's Identifier field.
     * The user maybe does not exist, because of that must be optional.
     * @param identifier Primary key of the table in the database.
     * @return An user object.
     */
    Optional<User> findByIdentifier(int identifier);

    /**
     * Query to add a new user or modify an existing one in the database.
     * This query will be used when we want to "delete" an user, in fact it's only a modification of the field status.
     * Response entity to check that all go fine.
     * @param identifier Primary key of the table in the database.
     */
    ResponseEntity<User> save(int identifier, User u); // Maybe the argument must be an user not an identifier or both.
    // void save(int identifier);
    
} // End interface UserRepository
