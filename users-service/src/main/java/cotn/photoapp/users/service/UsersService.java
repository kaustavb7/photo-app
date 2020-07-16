package cotn.photoapp.users.service;

import cotn.photoapp.users.model.UserDetailsDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author kaustavbasu
 * @Date 7/1/20
 * @Time 7:27 PM
 */
public interface UsersService extends UserDetailsService {

    public UserDetailsDTO createUsers(UserDetailsDTO userDetailsDTO);
    public UserDetailsDTO getUserDetailsByEmail(String email);
}
