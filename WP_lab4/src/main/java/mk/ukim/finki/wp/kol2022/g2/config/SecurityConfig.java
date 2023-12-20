package mk.ukim.finki.wp.kol2022.g2.config;

import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public SecurityConfig(PasswordEncoder passwordEncoder, StudentRepository studentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/h2").permitAll()
                .antMatchers("/students/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/students", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        Student admin = studentRepository.findByEmail("student.1@wp.finki.ukim.mk");
        Student user = studentRepository.findByEmail("student.10@wp.finki.ukim.mk");
        UserDetails adminUser = User.builder()
                .username(admin.getEmail())
                .password(passwordEncoder.encode("emp1"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(adminUser);
    }
}