package cotn.photoapp.users.configuration;

import cotn.photoapp.users.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author kaustavbasu
 * @Date 7/1/20
 * @Time 8:57 PM
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UsersService usersService;
    @Autowired
    Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
                .and()
                .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService,environment,authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }
}
