package cn.adam.website.paintingphotographylifewebserver.security.config;

import cn.adam.website.paintingphotographylifewebserver.security.filter.VerifyImageCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private VerifyImageCodeFilter verifyImageCodeFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verifyImageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/")

                .and()
                .authorizeRequests()
                .mvcMatchers("/", "/index", "/verify/image").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
