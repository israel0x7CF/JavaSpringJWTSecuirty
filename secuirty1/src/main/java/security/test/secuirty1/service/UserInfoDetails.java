package security.test.secuirty1.service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import security.test.secuirty1.entity.UserInfo;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UserInfoDetails implements UserDetails
{
    private String name;
    private String Password;
    private List<GrantedAuthority> authorites;

    public UserInfoDetails(UserInfo userInfo){
        this.name = userInfo.getName();
        this.authorites = Arrays.stream(userInfo.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorites;
    }
    @Override
    public String getUsername(){
        return this.name;
    }
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override 
    public String getPassword() {
        
        return this.Password;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }
}  

