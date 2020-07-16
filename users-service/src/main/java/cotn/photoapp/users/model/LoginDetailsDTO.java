package cotn.photoapp.users.model;

/**
 * @author kaustavbasu
 * @Date 7/8/20
 * @Time 10:10 PM
 */
public class LoginDetailsDTO {

    private String email;
    private String password;

    public LoginDetailsDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
