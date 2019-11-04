package cn.adam.website.paintingphotographylifewebserver.security.config;

import cn.adam.website.paintingphotographylifewebserver.security.filter.VerifyFilter;
import cn.adam.website.paintingphotographylifewebserver.security.modle.VerifyConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private VerifyFilter[] verifyFilters;
    @Autowired
    private VerifyConfigurationProperties verifyConfigurationProperties;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        for (VerifyFilter verifyFilter : verifyFilters) {
            http.addFilterBefore(verifyFilter, UsernamePasswordAuthenticationFilter.class);
//            System.out.println(Arrays.toString(verifyFilter.getVerifyUrls()));
        }
        http.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/")

                .and()
                .authorizeRequests()
                .mvcMatchers(verifyConfigurationProperties.getPermitUrls()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
