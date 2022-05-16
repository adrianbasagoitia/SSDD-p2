package es.urjc.sd.database;
/* ********************************************************************* */

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.urjc.sd.p2.User;

/* ********************************************************************* */
/**
 * Class to populate the database when it will be used in local.
 */
@Component
@Profile("local")
public class DatabasePopulator {
    /* Attributes */
    /**
     * An UserRepository to work with the database.
     */
    @Autowired
    private UserRepository repo;

    /* ********************************************************************* */
    /* Methods */
    /**
     * Method to add 5 users to the database on start.
     */
    @PostConstruct
    public void PopulateDB(){
        String [] n1 = {"John", "Smith", ""};
        String [] n2 = {"Juan", "Ortiz", "Sanchez"};
        String [] n3 = {"María", "Pérez", "jimenez"};
        String [] n4 = {"Carl", "Gustavson", ""};
        String [] n5 = {"Lucia", "Blazquez", "Lucero"};
        User u1 = new User("qwerty", n1);
        User u2 = new User("azerty", n2);
        User u3 = new User("12345", n3);
        User u4 = new User("zjfbvkj", n4);
        User u5 = new User("re834r7u", n5);

        repo.save(u1.getIdentifier(), u1);
        repo.save(u2.getIdentifier(), u2);
        repo.save(u3.getIdentifier(), u3);
        repo.save(u4.getIdentifier(), u4);
        repo.save(u5.getIdentifier(), u5); 
    }

    
} // End class DatabasePopulator
