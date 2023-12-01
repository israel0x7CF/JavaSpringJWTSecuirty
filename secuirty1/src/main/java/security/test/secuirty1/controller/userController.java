package security.test.secuirty1.controller;



import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import security.test.secuirty1.entity.AuthRequest;
import security.test.secuirty1.entity.UserInfo;
import security.test.secuirty1.service.JwtService;
import security.test.secuirty1.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin
@RequestMapping("/auth")
public class userController 
 { 
  
    @Autowired
    private UserInfoService service; 
  
    @Autowired
    private JwtService jwtService; 
  
    @Autowired
    private AuthenticationManager authenticationManager; 
    Logger logger = LoggerFactory.getLogger(userController.class);
    @GetMapping("/welcome") 
    public String welcome() { 
        return "Welcome this endpoint is not secure"; 
    } 
  
    @PostMapping("/addNewUser") 
    public String addNewUser(@RequestBody UserInfo userInfo) { 
        return service.addUser(userInfo); 
    } 
  
    @GetMapping("/user/userProfile") 
    @PreAuthorize("hasAuthority('ROLE_USER')") 
    public String userProfile() { 
        return "Welcome to User Profile"; 
    } 
  
    @GetMapping("/admin/adminProfile") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminProfile() { 
        return "Welcome to Admin Profile"; 
    } 
  
    @PostMapping("/generatetoken") 
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        
        logger.info(authRequest.getUsername());
        logger.info(authRequest.getPassword());
        try{

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            if (authentication.isAuthenticated()) { 
                logger.info("Here we go Success !");
                return jwtService.generateToken(authRequest.getUsername()); 
            } else { 
                logger.info("Here we go failed !");
                throw new UsernameNotFoundException("invalid user request !"); 
            } 
        }catch(Exception e){
            logger.info(e.getMessage());
            // e.printStackTrace();
            return e.getMessage();
        }
    } 

    
    
  
} 
