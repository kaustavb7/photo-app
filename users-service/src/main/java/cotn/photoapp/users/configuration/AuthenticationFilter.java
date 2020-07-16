package cotn.photoapp.users.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import cotn.photoapp.users.model.LoginDetailsDTO;
import cotn.photoapp.users.model.UserDetailsDTO;
import cotn.photoapp.users.service.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author kaustavbasu
 * @Date 7/8/20
 * @Time 11:25 PM
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UsersService usersService;
    private Environment environment;

    public AuthenticationFilter(UsersService usersService, Environment environment, AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            LoginDetailsDTO loginDetailsDTO = new ObjectMapper().readValue(req.getInputStream(), LoginDetailsDTO.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    loginDetailsDTO.getEmail(),
                    loginDetailsDTO.getPassword(),
                    new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDetailsDTO userDetailsDTO=usersService.getUserDetailsByEmail(userName);

        String token = Jwts.builder()
                .setSubject(userDetailsDTO.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetailsDTO.getUserId());

    }
}
