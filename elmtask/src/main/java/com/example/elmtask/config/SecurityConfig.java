package com.example.elmtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //all the requests need authentication
        //(get-all-services) request is allowed only for admin user [This is to show that we can give authorization based on role]
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/service/getall")
                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        //disabling cross-site request forgery as we will send requests from different domains
        http
                .csrf().disable();

        return http.build();
    }

    //the users we have: user & admin
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("P@ssw0rd")
                .roles("USER")
                .build();

        UserDetails admin = users
                .username("admin")
                .password("P@ssw0rd")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }
}
