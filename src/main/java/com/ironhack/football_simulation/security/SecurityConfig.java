package com.ironhack.football_simulation.security;

import com.ironhack.football_simulation.security.filters.CustomAuthenticationFilter;
import com.ironhack.football_simulation.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity // indicates it is a security config class using spring web security
@RequiredArgsConstructor
public class SecurityConfig {

    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    private final UserDetailsService userDetailsService;

    // Autowired instance of the AuthenticationManagerBuilder (provided by Spring Security)
    private final AuthenticationManagerBuilder authManagerBuilder;


    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());

        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");


        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/season/run").permitAll()
                        .requestMatchers("/api/season/results").permitAll()
                        .requestMatchers("/api/season/standings").permitAll()
                        .requestMatchers("/api/clubs").permitAll()
                        .requestMatchers("/api/clubs/*").permitAll()  // For individual club access
                        .requestMatchers("/api/clubs/*/players").permitAll()
                        .requestMatchers("/api/clubs/*/coaches").permitAll()
                        .requestMatchers("/api/people/players").permitAll()
                        .requestMatchers("/api/people/players/*").permitAll()  // Individual players
                        .requestMatchers("/api/people/coaches").permitAll()
                        .requestMatchers("/api/people/coaches/*").permitAll()  // Individual coaches
                        .requestMatchers("/api/matches").permitAll()
                        .requestMatchers("/api/matches/results").permitAll()
                        .requestMatchers("/api/matches/standings").permitAll()
                        .requestMatchers("/api/season/play-match/*/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/clubs").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/people/players").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/people/coaches").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/matches").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/people/players/*").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/people/players/*").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/people/coaches/*").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated());

        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);

        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}
