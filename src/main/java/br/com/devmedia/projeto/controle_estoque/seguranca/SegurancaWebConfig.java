package br.com.devmedia.projeto.controle_estoque.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class SegurancaWebConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() 
            	.antMatchers("/foundation-6/**").permitAll() 
            	.antMatchers("/", "/produtos/**").hasRole("ADMIN") 
                .anyRequest().authenticated()
                .and() 
            .formLogin() 
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .defaultSuccessUrl("/")
                .and()  
            .logout()
                .permitAll(); 
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.jdbcAuthentication().dataSource(this.dataSource)
		.usersByUsernameQuery(
				"SELECT username, password, enabled FROM \"user\" WHERE username = ?")
		.authoritiesByUsernameQuery(
				"SELECT username, role FROM user_role WHERE username = ?")
		.passwordEncoder(new BCryptPasswordEncoder());
  
    }
}