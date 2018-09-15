package com.laver.config;



/**
 * Created by L on 2018/9/14.
 */
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()
//                .antMatchers("/user/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login-error");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//    }
//}
