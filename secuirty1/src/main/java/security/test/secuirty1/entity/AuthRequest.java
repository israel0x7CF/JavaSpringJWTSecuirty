package security.test.secuirty1.entity;

import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
  
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest { 
  
    private String username; 
    private String password;
    
    
}