package Users;
import java.io.Serializable;

public abstract class User implements Serializable {
    private String password;
    private String email;
    private String name;
    private final int id;

    public User(String name, String email, String password, int id){
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public int getUserId(){
        return this.id;
    }
    public String getUserName(){
        return this.name;
    }
    public String getUserEmail(){
        return this.email;
    }
    public String getUserPassword(){
        return this.password;
    }

    private void setName(String newName){
        this.name = newName;
    }
    private void setEmail(String newEmail){
        this.email = newEmail;
    }
    private void setPassword(String newPassword){
        this.password = newPassword;
    }

    public void updateUser(String newName, String newEmail, String newPassword){
        if(!newName.isBlank()){
            setName(newName);
        }
        if(!newEmail.isBlank()){
            setEmail(newEmail);
        }
        if(!newPassword.isBlank()){
            setPassword(newPassword);
        }
    }
}
