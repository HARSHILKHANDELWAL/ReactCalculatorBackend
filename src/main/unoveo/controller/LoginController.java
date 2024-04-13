package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login() {
        System.out.println("into the login route");
        return "login dgdfgdfg";
    }
    @GetMapping("/index")
    public String index() {
        return "index1";
    }

    @GetMapping("/user/profile")
    public String profile(){
        return "profile";
    }
    @GetMapping("/admin/profile")
    public String adminProfile(){
        return "admin";
    }

    @PostMapping("/Calculator")
//    @ResponseBody
    public String ccc(){
        return "calculator";
    }


    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(){
        SecurityContextHolder.clearContext();
        return new ResponseEntity<String>("Logout Successfully!", HttpStatus.OK);
    }


}
