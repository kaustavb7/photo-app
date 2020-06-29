package cotn.photoapp.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public String statusCheck()
    {
        return "Status is UP at port: " + environment.getProperty("local.server.port");
    }
}
