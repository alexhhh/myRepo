package com.ikon.alexx.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration 
@ImportResource({"classpath:security-context.xml"})
public class WebSecurityConfig { 
}
	
 
	
	//extends WebSecurityConfigurerAdapter {
 
 //   protected void configure(HttpSecurity http) throws Exception {
  //      http
   //      
        //    .authorizeRequests() //.anyRequest().permitAll()
            //.and()  
          //  .antMatchers("/","/user").permitAll().anyRequest().anonymous()   ;
              //  .anyRequest().authenticated()
              //  .and();
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
             //   .and().antMatcher("/").permitAll();
//            .logout()
          //      .permitAll();
 //   }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("password").roles("MESTER");
//    }
 