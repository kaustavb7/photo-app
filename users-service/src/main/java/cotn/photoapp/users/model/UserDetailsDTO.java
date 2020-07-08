package cotn.photoapp.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author kaustavbasu
 * @Date 6/30/20
 * @Time 7:26 PM
 */
public class UserDetailsDTO {

    private String userId;
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    private String password;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    private String encPwd;

    public UserDetailsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEncPwd() {
        return encPwd;
    }

    public void setEncPwd(String encPwd) {
        this.encPwd = encPwd;
    }
}
