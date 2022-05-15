package es.urjc.sd.controller;

/* ********************************************************************* */

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.urjc.sd.database.UserRepository;
import es.urjc.sd.p2.User;

/* ********************************************************************* */
/**
 * This class will be the link with the database of the API Rest.
 */
@Service
public class UserService {
    /* Attributes */    
    /**
     * An UserRepository instance to communicate with the database.
     */
    private UserRepository repo;

    /* ********************************************************************* */
    /* Builder */
    public UserService(){

    }

    /* ********************************************************************* */
    /* Get & Set */

    /* ********************************************************************* */
    /* Methods */
    /**
     * Query to request an object user from it's identifier.
     * @param identifier The identifier of the desider user.
     * @return THe object user.
     */
    public Optional<User> findByIdentifier(int identifier){
        return repo.findByIdentifier(identifier);
    }

    /**
     * Query to request all users of the system.
     * @return A collection with all the users inside.
     */
    public List<User> findAll(){
        return repo.findAll();
    }

    /**
     * Query to add a new user in the database.     </p>
     * Also works editing an user in the database.  </p>
     * And finally also works "deleting" an user, only change the value of an attribute.
     * @param u The user to add/modify.
     */
    public ResponseEntity<User> save(int identifier, User u){
        return repo.save(identifier, u);
    }  

} // End class UserService
