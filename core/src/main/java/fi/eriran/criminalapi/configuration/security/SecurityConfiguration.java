package fi.eriran.criminalapi.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static fi.eriran.criminalapi.configuration.security.UserRole.INVESTIGATOR;
import static fi.eriran.criminalapi.configuration.security.UserRole.SUPPORT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//TODO: FIX THIS
                .authorizeRequests()
                .antMatchers("/", "/greeting")
                .permitAll()

                .antMatchers(HttpMethod.POST, "/graphql")
                .hasAuthority(UserPermission.CRIMINAL_READ.name())

                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("investigator")
                .password(passwordEncoder.encode("guy"))
                .authorities(INVESTIGATOR.getGrantedAuthorities())
                .build();

        UserDetails adminDetails = User.builder()
                .username("support")
                .password(passwordEncoder.encode("guy"))
                .authorities(SUPPORT.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                userDetails,
                adminDetails
        );
    }
}
