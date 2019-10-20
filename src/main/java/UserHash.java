import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
public class UserHash {
    @Id
    @GeneratedValue
    private Integer id;

    private String login;

    private String hash;

    public UserHash(){}

    public UserHash(String newLogin, String newHash) {
        this.login = newLogin;
        this.hash = newHash;
    }

    public Integer getId(){
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
