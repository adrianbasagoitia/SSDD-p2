package es.urjc.sd.p2;

/* ********************************************************************* */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;

/* ********************************************************************* */
/**
 * Class to model an user object in the API Rest.
 */
@Entity
public class User {
    /* Attributes */
    /**
     * Unique identifier of an user. </p>
     * Primary key of the table in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int identifier;

    /**
     * Unique identifier of the user in the system. This may look like a "nick" in a social media, where only a user can name himself of a way for example "@john". 
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int login;
    
    /**
     * The password of an user to access the system.
     */
    private String password;

    /** 
     * Attribute to contain the full name of an user. Array of String. Due to disparities of alphabets and naming conventions around the globe, it's difficult to "standarize" a naming convention.                     </p>  
     * The convention that this attribute will follow it's the traditional spanish naming convention: Name and two surnames.                  </p>
     * But the surnames may be empty or null.                           </p>
     * The characters allowed will be the letters of the latin alphabet, so the names in different alphabets like Cyrillic, chinese, japanese,... must be "translated" into the latin alphabet.
     */
    private String[] name = new String[3];

    /**
     * Attribute to store when an user was registered in the system.</p>
     * Format: YYYY/MM/DD => Year, Month, Day.                      </p>
     * Will be asignated automatically when the user is registered. </p>
     * It will store the actual date IN THE SERVER.
     */
    private LocalDate registerDate;

    /**
     * Attribute to indicate if an user is active in the system. It will function as a "soft" delete in the system.                    </p>
     *  - true: Active User.                                       </p>
     *  - false: Inactive user a.k.a user "deleted".               </p>
     * Set to Active automatically on creation.
     */

    private boolean status;

    /**
     * Attribute to indicate the actual amount of money in the user account.</p>
     * Set automatically on creation at 0€.                                 </p>
     * It will be limited to only two decimals.
     */
    private float money;

    /* ********************************************************************* */
    /* Builders */
    /**
     * Necessary builder to load from Database.
     */
    public User(){

    }

    /**
     * Default builder for an object user.
     * @param pass The password to access the system.
     * @param name The name of the user.
     */
    public User(String pass, String[] name){
        // Identifier and login created automatically.
        this.password = pass;
        this.name = name;
        this.registerDate = LocalDate.now();
        this.status = true;
        this.money = 0;
    }

    /* ********************************************************************* */
    /* Get & Set*/
    /**
     * Getter for identifier attribute.
     * @return the identifier of the user.
     */
    public Integer getIdentifier(){
        return this.identifier;
    }

    // No setter needed for identifier attribute, it can not be modified.


    /**
     * Getter for login attribute.
     * @return the login of the user.
     */
    public Integer getLogin(){
        return this.login;
    }

    /**
     * Setter for login attribute.
     * @param log the new login for the user.
     */
    public void setLogin(int log){
        this.login = log;
    }


    /**
     * Getter for password attribute.
     * @return the password of the user.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Setter for password attribute.
     * @param pass the new password for the user.
     */
    public void setPassword(String pass){
        this.password = pass;
    }


    /**
     * Getter for name attribute.
     * @return the name of the user.
     */
    public String[] getName(){
        return this.name;
    }

    /**
     * Setter for name attribute.
     * @param n the new name of the user.
     */
    public void setName(String[] n){
        this.name = n;
    }


    /**
     * Getter for register date attribute.
     * @return the date when the user was registered in the system.
     */
    public LocalDate getRegisterDate(){
        return this.registerDate;
    }

    // No setter needed for registerDate, it can not be modified.


    /**
     * Getter for status attribute.
     * @return the status of the user in the system.
     */
    public Boolean getStatus(){
        return this.status;
    }

    /**
     * Setter for status attribute.
     * @param s The new status of the user.
     */
    public void setStatus(Boolean s){
        this.status = s;
    }

    
    /**
     * Getter for money attribute.
     * @return the money of the user in his/her account.
     */
    public Float getMoney(){
        return this.money;
    }

    /**
     * Setter for money attribute.
     * @param m The new amount of money in the user account.
     */
    public void setMoney(float m){
        this.money = m;
    }

    /* ********************************************************************* */
    /* Methods */
    /**
     * Method to return an user object in json format using an string.
     * **** It is necessary to return in JSON format??? ****
     */
    public String toString(){
        return 
        "{\n"+
            "\"identifier\": "+ this.getIdentifier() +",\n"+
            "\"login\": "+ this.getLogin() +",\n"+
            "\"password\": \""+ this.getPassword() +"\",\n"+
            "\"name\": \""+ this.toStringName() +"\",\n"+
            "\"registerDate\": "+ this.getRegisterDate() +",\n"+
            "\"status\": "+ this.getStatus() +",\n"+
            "\"money\": "+ this.getMoney() +",\n"+
        "}";
    }

    /**
     * Method to put the name of an user from an array of String to an String.
     * ****Either it's necessary or an stupidity****.
     * @return The name of the user in a String.
     */
    public String toStringName(){
        name = this.getName();
        String res = name[0];

        if(name[1] != null){
            res = res + " " + name[1];
        }
        if(name[2] != null){
            res = res + " " + name[2];
        }

        return res;
    }
} // End class User