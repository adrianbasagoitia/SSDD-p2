package es.urjc.sd.controller;

/* ********************************************************************* */

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.urjc.sd.p2.User;

/* ********************************************************************* */
/**
 * This class will be the controller of the user objects in the API Rest.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /* Attributes */
    /**
     * Service to link with the database.
     */
    @Autowired
    private UserService userservice;

    /* ********************************************************************* */
    /* Builder */

    /* ********************************************************************* */
    /* Get & Set */

    /* ********************************************************************* */
    /* **********************      CRUD Methods       ********************** */
    /**
     * Method to return all the users in the system.
     * @return A collection with all the users inside.
     */
    @GetMapping("/")
    public List<User> getUsers(){
        return userservice.findAll();
    }

    
    /**
     * Method to search a user in the database.             </p>
     * If the user it is found, return ok with the user.    </p>
     * In any other case return an error.
     * @param identifier The identifier of the desired user.
     * @return A response entity either succesfull and with the user inside, or an error.
     */
    @GetMapping("/{identifier}")
    public ResponseEntity<User> getUser(@PathVariable int identifier){
        Optional<User> opt = userservice.findByIdentifier(identifier);

        if(opt.isPresent()){
            User aux = opt.get();
            return ResponseEntity.ok(aux);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Method to add a new user to the database of the API Rest
     * @param u The new user to be added to the database.
     * @return A response entity with the URI of the newly user inside the body of the response entity.
     */
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User u){
        userservice.save(u.getIdentifier(), u);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getIdentifier()).toUri();

        return ResponseEntity.created(location).body(u);
    }


    /**
     * Method to modify all the possible attributes in an user object.
     * The method will work with two different users. The original, represented
     * by the identifier, and a new one containing the field modified, the 
     * user u.
     * @param identifier The identifier of the user.
     * @param u An user object filled with the modified parameters.
     * @return A response entity with the user modified in the body of the request, or an error.
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<User> modifyUser(@PathVariable int identifier, @RequestBody Integer login, @RequestBody String pass, @RequestBody String[] n, @RequestBody Boolean s, @RequestBody Float m){
      Optional<User> opt = userservice.findByIdentifier(identifier);

      if(opt.isPresent()){
          User aux = opt.get();

          aux.setLogin(login);
          aux.setPassword(pass);
          aux.setName(n);
          aux.setStatus(s);
          aux.setMoney(m);
  
          userservice.save(aux.getIdentifier(), aux);
          return ResponseEntity.ok(aux);
      }else{
          return ResponseEntity.notFound().build();
      } // End If/Else
    } // End modifyUser


    /**
     * Method to "delete" an user from the database. In fact only change the attribute status to false.
     * @param identifier The identifier of the user.
     * @return A response entity with the user "deleted" in the body, or an error.
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<User> deleteUser(@PathVariable int identifier){
        Optional<User> opt = userservice.findByIdentifier(identifier);
        if(opt.isPresent()){
            User aux = opt.get();
            aux.setStatus(false);
            userservice.save(identifier, aux);
            return ResponseEntity.ok(aux);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /* ********************************************************************* */
    /* **********************     Bycicle Reserve     ********************** */

} // End class Controller
