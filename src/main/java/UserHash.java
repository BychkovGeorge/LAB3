import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
public class UserHash {
    @Id
    @GeneratedValue
    private long id;

    private String login;

    private String hash;

    public UserHash(){}

    public UserHash(String newLogin, String newHash) {
        //this.id = (long) 235 * System.currentTimeMillis();
        this.login = newLogin;
        this.hash = newHash;
    }

    public Long getId(){
        return this.id;
    }

    public String getLogin(){
        return this.login;
    }

    public String getHash(){
        return this.hash;
    }

    public void setLogin(String newLogin){
        this.login = newLogin;
    }

    public void setHash(String newHash){
        this.hash = newHash;
    }
}
