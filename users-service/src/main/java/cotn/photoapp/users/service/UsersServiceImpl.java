package cotn.photoapp.users.service;

import cotn.photoapp.users.entity.Users;
import cotn.photoapp.users.model.UserDetailsDTO;
import cotn.photoapp.users.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author kaustavbasu
 * @Date 7/1/20
 * @Time 7:28 PM
 */
@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetailsDTO createUsers(UserDetailsDTO userDetailsDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userDetailsDTO.setUserId(UUID.randomUUID().toString());
        userDetailsDTO.setEncPwd(bCryptPasswordEncoder.encode(userDetailsDTO.getPassword()));
        Users users=modelMapper.map(userDetailsDTO, Users.class);
        usersRepository.save(users);
        return userDetailsDTO;
    }
}
