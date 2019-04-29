package com.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.entities.User;

public class CustomUserDetails  implements UserDetails {	
	
	private static final long serialVersionUID = 1L;
	private List<String> userRoles;
	   private User user;
 
	 
	public CustomUserDetails(User user,List<String> userRoles){
		this.user = user;
		this.userRoles=userRoles;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roles=StringUtils.collectionToCommaDelimitedString(userRoles);			
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}



	

	
	  public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }


		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return user.getPassword();
		}


		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return user.getUserName();
		}

}
		 

 
