package study.testproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션 ->  SpringSecurityFilterChain이 동작하여 URL 필터가 적용됨
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.requestMatchers(new AntPathRequestMatcher("/**"))
				.permitAll()
				.and()
				// /h2-console/**
				// -> CSRF(Cross-Site Request Forgery)해제
				.csrf()
				.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
				.and()
				// H2 콘솔의 화면이 frame 구조로 작성되었기 때문이다. 스프링 시큐리티는 사이트의 콘텐츠가 다른 사이트에 포함되지 않도록 하기 위해
				// X-Frame-Options 헤더값을 사용하여 이를 방지
				// -> X-Frame-Options 헤더값을 sameorigin으로 설정
				.headers()
				.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
		return http.build();
	}

}
