package cotn.photoapp.users.controller;

import cotn.photoapp.users.model.UserAndAlbumDetailsDTO;
import cotn.photoapp.users.model.UserDetailsDTO;
import cotn.photoapp.users.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kaustavbasu
 * @Date 6/29/20
 * @Time 9:37 PM
 */

@RestController
@RequestMapping("/users-ms")
public class UsersController {

    @Autowired
    Environment environment;
    @Autowired
    UsersService usersService;

    @GetMapping
    public String statusCheck()
    {

        return "Status is UP at port: " + environment.getProperty("local.server.port")
                + " token:- " +environment.getProperty("token.secret");
    }

    @PostMapping
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody UserDetailsDTO userDetailsDTO)
    {
        userDetailsDTO=usersService.createUsers(userDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDTO);
    }

    @GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserAndAlbumDetailsDTO> getUser(@PathVariable("userId") String userId) {

        UserDetailsDTO userDto = usersService.getUserDetailsByUserId(userId);
        UserAndAlbumDetailsDTO returnValue = new ModelMapper().map(userDto, UserAndAlbumDetailsDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
