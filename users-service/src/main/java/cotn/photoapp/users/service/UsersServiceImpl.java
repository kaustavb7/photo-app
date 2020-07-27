package cotn.photoapp.users.service;

import cotn.photoapp.users.configuration.AlbumServiceClient;
import cotn.photoapp.users.entity.Users;
import cotn.photoapp.users.model.AlbumDetailsDTO;
import cotn.photoapp.users.model.UserDetailsDTO;
import cotn.photoapp.users.repository.UsersRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author kaustavbasu
 * @Date 7/1/20
 * @Time 7:28 PM
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    Environment environment;
    @Autowired
    AlbumServiceClient albumServiceClient;

    Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public UserDetailsDTO createUsers(UserDetailsDTO userDetailsDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userDetailsDTO.setUserId(UUID.randomUUID().toString());
        userDetailsDTO.setEncPwd(bCryptPasswordEncoder.encode(userDetailsDTO.getPassword()));
        Users users = modelMapper.map(userDetailsDTO, Users.class);
        usersRepository.save(users);
        return userDetailsDTO;
    }

    @Override
    public UserDetailsDTO getUserDetailsByEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        if (users != null) {
            return new ModelMapper().map(users, UserDetailsDTO.class);
        } else throw new UsernameNotFoundException(email);
    }

    @Override
    public UserDetailsDTO getUserDetailsByUserId(String userId) {
        Users users = usersRepository.findByUserId(userId);
        if (users != null) {
            logger.info("Before album ms called");
            List<AlbumDetailsDTO> albumsList = albumServiceClient.getAlbums(userId);
            logger.info("After album ms called");
            UserDetailsDTO userDetailsDTO = new ModelMapper().map(users, UserDetailsDTO.class);
            userDetailsDTO.setAlbumDetailsDTOList(albumsList);
            return userDetailsDTO;

        } else throw new UsernameNotFoundException(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmail(username);
        if (users != null) {
            return new User(users.getEmail(), users.getEncPwd(), true, true, true, true, new ArrayList<>());
        } else throw new UsernameNotFoundException(username);
    }
}
