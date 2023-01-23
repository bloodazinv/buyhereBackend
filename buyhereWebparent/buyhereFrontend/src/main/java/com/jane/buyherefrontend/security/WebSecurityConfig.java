/**
 * FileName: WebSecurityConfig
 * Author: jane
 * Date: 2023/1/14 16:16
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.security;


import com.jane.buyherefrontend.security.oauth.CustomerOAuth2UserService;
import com.jane.buyherefrontend.security.oauth.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomerOAuth2UserService oAuth2UserService;
    private OAuth2LoginSuccessHandler oauth2LoginHandler;
    private DatabaseLoginSuccessHandler databaseLoginHandler;

    @Autowired
    public WebSecurityConfig(CustomerOAuth2UserService oAuth2UserService, OAuth2LoginSuccessHandler oauth2LoginHandler,
                             DatabaseLoginSuccessHandler databaseLoginHandler) {
        super();
        this.oAuth2UserService = oAuth2UserService;
        this.oauth2LoginHandler = oauth2LoginHandler;
        this.databaseLoginHandler = databaseLoginHandler;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/account_details", "/update_account_details", "/orders/**",
                        "/cart" , "/address_book/**", "/reviews/**",
                        "/checkout", "/place_order", "/process_paypal_order",
                        "/write_review/**", "/post_review", "/customer/questions/**",
                        "/post_question/**", "/ask_question/**")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .successHandler(databaseLoginHandler)
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(oauth2LoginHandler)
                .and()
                .logout().permitAll()
                .and()
                .rememberMe()
        ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**","/css/**");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
