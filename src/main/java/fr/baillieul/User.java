package fr.baillieul;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class User implements Serializable {
    public User() {}

    public User(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }
    
    @Id @GeneratedValue
    private int id;

    @Column
    private String email;

    @Column
    private String password;
    
    @Column
    private String token;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

}
