package board.JPAboard.config;

import board.JPAboard.handler.MyAccessDeniedHandler;
import board.JPAboard.handler.MyAuthenticationEntryPoint;
import board.JPAboard.handler.MyLoginSuccessHandler;
import board.JPAboard.handler.MyLogoutSuccessHandler;
import board.JPAboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;

    //로그인하지 않은 유저들만 접근 가능한 URL
    private static final String[] anonymousUserUrl = {"/users/login","/users/join"};

    //로그인한 유저들만 접근 가능한 URL
    private static final String[] authenticatedUserUrl = {"/boards/**/**/edit","/board/**/**/delete","likes/**","/users/myPage/**","/users/edit","users/delete"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        return httpSecurity
                .csrf((csrfConfig)->
                        csrfConfig.disable()
                )
                .authorizeHttpRequests((authorizeRequests)->
                        authorizeRequests
                                .requestMatchers(anonymousUserUrl).anonymous()
                                //authenticatedUserUrl에 대해서는 로그인을 요구
                                .requestMatchers(authenticatedUserUrl).authenticated()
                                //해당 url에 대해서는 해당 권한을 갖고 있어야 접근 가능
                                .requestMatchers("/boards/greeting/write").hasAnyAuthority("BRONZE","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/boards/greeting").hasAnyAuthority("BRONZE","ADMIN")
                                .requestMatchers("/boards/free/write").hasAnyAuthority("SILVER","GOLD","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/boards/free").hasAnyAuthority("SILVER","GOLD","ADMIN")
                                .requestMatchers("/boards/gold/**").hasAnyAuthority("GOLD","ADMIN")
                                .requestMatchers("/users/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/comments/**").hasAnyAuthority("BRONZE","SILVER","GOLD","ADMIN")
                                //나머지 요청에 대해서는 로그인을 요구하지 않음
                                .anyRequest().permitAll()
                )
                .exceptionHandling((exceptionConfig)->
                        exceptionConfig
                                .accessDeniedHandler(new MyAccessDeniedHandler(userRepository)) //인가 실패
                                .authenticationEntryPoint(new MyAuthenticationEntryPoint()) //인증 실패
                )
                .formLogin((formLogin)->
                        formLogin
                                //로그인 페이지를 제공하는 url을 설정
                                .loginPage("/users/login")
                                //로그인에 사용될 id
                                .usernameParameter("loginId")
                                //로그인에 사용될 password
                                .passwordParameter("password")
                                //로그인 성공시 실행될 handler
                                .successHandler(new MyLoginSuccessHandler(userRepository))
                                //로그인 실패시 redirect 될 URL => 실패 메세지 출력
                                .failureUrl("/users/login?fail")

                )
                .logout((logoutConfig)->
                        logoutConfig
                                .logoutUrl("/users/logout")
                                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                )
                .build();


    }
}
