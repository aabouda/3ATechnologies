package com.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.dao.UserRepository;
import com.dao.UserRolesRepository;
import com.entities.User;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        System.out.println("user : "+name);
        System.out.println("Password : "+password);
        
        User user = userRepository.findByUserName(name);  
        
        System.out.println("User : "+user);
        
        if(user != null && password.equals(user.getPassword())){
        	List<String> userRoles=userRolesRepository.findRoleByUserName(name);
            
            if(userRoles != null && !userRoles.isEmpty()){
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for(String role : userRoles){
                	grantedAuthorities.add(new SimpleGrantedAuthority(role));
                }

                System.out.println("Access granted with role : "+userRoles);
                return new UsernamePasswordAuthenticationToken(name,password,grantedAuthorities);
            }else{
                throw new BadCredentialsException("You don't have any role");
            }
        }else{
        	throw new BadCredentialsException("Unable to auth against Provider. Please verify your credentials");
        }
        
        
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
