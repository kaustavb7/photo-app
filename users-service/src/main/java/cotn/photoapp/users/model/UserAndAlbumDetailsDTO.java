package cotn.photoapp.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author kaustavbasu
 * @Date 6/30/20
 * @Time 7:26 PM
 */
public class UserAndAlbumDetailsDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumDetailsDTO> albumDetailsDTOList;

    public UserAndAlbumDetailsDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AlbumDetailsDTO> getAlbumDetailsDTOList() {
        return albumDetailsDTOList;
    }

    public void setAlbumDetailsDTOList(List<AlbumDetailsDTO> albumDetailsDTOList) {
        this.albumDetailsDTOList = albumDetailsDTOList;
    }
}
