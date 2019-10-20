import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
public class UserPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String password;

    public UserPassword(){}

    public UserPassword(String newLogin, String newPassword) {
        this.login = newLogin;
        this.password = newPassword;
    }

    public Integer getId(){
        return this.id;
    }

    public String getLogin(){
        return this.login;
    }

    public String getPassword(){
        return this.password;
    }

    public void setLogin(String newLogin){
        this.login = newLogin;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }
}
