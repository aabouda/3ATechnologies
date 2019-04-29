package  com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.entities.ConnexionDB;

@Configuration
public class AuthenticationProviderConfig {
	@Bean(name = "dataSource")
	public static DriverManagerDataSource dataSource() {
		ConnexionDB connexionDB = new ConnexionDB();
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl(connexionDB.getPath());
	    driverManagerDataSource.setUsername(connexionDB.getLoginDB());
	    driverManagerDataSource.setPassword(connexionDB.getPwDB());
	    return driverManagerDataSource;
	}
    
    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSource());
    	jdbcImpl.setUsersByUsernameQuery("select username,password, enabled from users where username=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select b.username, a.role from user_roles a, users b where b.username=? and a.userid=b.userid");
    	return jdbcImpl;
    }
}
