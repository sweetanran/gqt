package com.cheer.security;

import com.cheer.entity.MallAdminEntity;
import com.cheer.entity.MallShopEntity;
import com.cheer.enums.AdminRoleEnum;
import com.cheer.service.MallAdminService;
import com.cheer.service.MallShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security配置
 *
 * @author cheer
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MallAdminService mallAdminService;

    @Autowired
    private MallShopService mallShopService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private RestLogoutSuccessHandler restLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //解决不能加载iframe的问题
        http.headers().frameOptions().disable();

        http.formLogin()
                //自定义登录页
                .loginProcessingUrl("/login")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/category/update/sort")// 对登录要允许匿名访问
                .permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated()
                .and()
                .csrf()
                .disable();

        // 设置session
        http.sessionManagement().and().sessionManagement()
                .maximumSessions(10)
                .sessionRegistry(getSessionRegistry());

        //添加自定义未授权和未登录结果返回
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler);

        //添加注销接口
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            MallAdminEntity admin = mallAdminService.getByUsername(username);
            if (admin == null) {
                throw new UsernameNotFoundException("");
            }

            if (admin.getRole().equals(AdminRoleEnum.SHOP.getCode())) {
                MallShopEntity shopEntity = mallShopService.getByPhone(admin.getUsername());
                if (shopEntity != null) {
                    return new CustomUserDetails(admin, shopEntity.getId());
                }
            }

            return new CustomUserDetails(admin);
        };
    }

    @Bean
    public SessionRegistry getSessionRegistry() {
        return new SessionRegistryImpl();
    }

}
