package com.blogs.config;

import com.blogs.audit.AuthenticationAuditor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationAuditor authenticationAuditor;


    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .headers()
                .disable()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessHandler(authenticationAuditor)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .and()
                .httpBasic()
                .authenticationEntryPoint(entryPoint())
                .and()
                .anonymous().disable()
                .headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().regexMatchers("^(?!\\/api\\/.*)");
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BasicAuthenticationEntryPoint entryPoint () {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
