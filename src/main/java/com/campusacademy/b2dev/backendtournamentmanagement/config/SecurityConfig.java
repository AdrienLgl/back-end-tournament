package com.campusacademy.b2dev.backendtournamentmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("visiteur").password(this.passwordEncoder.encode("root"))
                .roles("VISITOR").and().withUser("organisateur").password(this.passwordEncoder.encode("root"))
                .roles("ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and().formLogin().and().logout().deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).and().authorizeRequests()
                .antMatchers("/login", "/swagger-resources/**", "/swagger-ui/**", "/v2/api-docs", "/webjars/**")
                .permitAll().antMatchers(HttpMethod.PUT).hasAnyAuthority("ROLE_ADMIN").antMatchers(HttpMethod.POST)
                .hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/players/**").hasAnyRole("VISITOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN").anyRequest().authenticated();
    }

}
