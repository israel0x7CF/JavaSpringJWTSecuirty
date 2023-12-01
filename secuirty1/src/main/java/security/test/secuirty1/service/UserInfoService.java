package security.test.secuirty1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import security.test.secuirty1.entity.UserInfo;
import security.test.secuirty1.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository uRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<UserInfo> userdetails = this.uRepository.findByName(username);

        return userdetails.map(UserInfoDetails::new).orElseThrow(()-> new UsernameNotFoundException("UserName not found"));

    }
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        this.uRepository.save(userInfo);
        return "User Saved Successfully !";
    }
    
}
