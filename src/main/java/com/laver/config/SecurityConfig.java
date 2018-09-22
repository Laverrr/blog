package com.laver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by L on 2018/9/14.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启动安全方法
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String KEY = "laver.com";

    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
    }

    @Bean//没看懂为什么要加这个注解 手误?
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //暂时开放所有网址的访问权限
                .antMatchers("/css/**","/js/**","/fonts/**","/index","/404").permitAll()
//                .antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                //通过表单登陆 无需再controller里面写requestMapping 直接通过post方法访问/login即可
                //在登陆页面的表单中 每个input框的id和name应该还是固定的 必须遵循spring security的规范 此处不确定 但目前看来是这样
                //退出登录的url是/logout 也是需要从表单通过post方法提交
                .formLogin()
                .loginPage("/login").failureUrl("/login-error")
                .and().rememberMe().key(KEY)
                .and().exceptionHandling().accessDeniedPage("/404");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
}
