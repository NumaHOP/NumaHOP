package org.numahop.numahop.config;

import org.numahop.numahop.security.AjaxAuthenticationFailureHandler;
import org.numahop.numahop.security.AjaxAuthenticationSuccessHandler;
import org.numahop.numahop.security.AjaxLogoutSuccessHandler;
import org.numahop.numahop.security.Http401UnauthorizedEntryPoint;
import org.numahop.numahop.web.filter.CsrfCookieGeneratorFilter;
import org.numahop.numahop.web.rest.administration.security.AuthorizationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	// PathPattern n'autorise "**" qu'en fin de motif : l'ancien
	// "/scripts/**/*.{js,html}" devient "/scripts/**" (sans incidence, la regle finale
	// etant anyRequest().permitAll()).
	private static final PathPatternRequestMatcher.Builder PATH = PathPatternRequestMatcher.withDefaults();

	private final Environment env;

	private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

	private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

	private final AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

	private final Http401UnauthorizedEntryPoint authenticationEntryPoint;

	private final RememberMeServices rememberMeServices;

	static {
		// Pour que la session se propage vers les threads enfants
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

	public SecurityConfiguration(final Environment env,
			final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler,
			final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler,
			final AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler,
			final Http401UnauthorizedEntryPoint authenticationEntryPoint, final RememberMeServices rememberMeServices) {
		this.env = env;
		this.ajaxAuthenticationSuccessHandler = ajaxAuthenticationSuccessHandler;
		this.ajaxAuthenticationFailureHandler = ajaxAuthenticationFailureHandler;
		this.ajaxLogoutSuccessHandler = ajaxLogoutSuccessHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.rememberMeServices = rememberMeServices;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.ignoringRequestMatchers(PATH.matcher("/websocket/**")))
			.addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
			.exceptionHandling(c -> c.authenticationEntryPoint(authenticationEntryPoint))
			.rememberMe(c -> c.rememberMeServices(rememberMeServices)
				.rememberMeParameter("remember-me")
				.key(env.getProperty("jhipster.security.rememberme.key")))
			.formLogin(c -> c.loginProcessingUrl("/api/authentication")
				.successHandler(ajaxAuthenticationSuccessHandler)
				.failureHandler(ajaxAuthenticationFailureHandler)
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.permitAll())
			.logout(c -> c.logoutUrl("/api/logout")
				.logoutSuccessHandler(ajaxLogoutSuccessHandler)
				.deleteCookies("JSESSIONID", "hazelcast.sessionId")
				.permitAll())
			.headers(c -> c.frameOptions(FrameOptionsConfig::disable)
				.contentSecurityPolicy(contentSecurityPolicyConfig -> contentSecurityPolicyConfig.policyDirectives(
						"default-src 'self'; script-src 'self' 'unsafe-eval' 'unsafe-inline'; style-src 'self' 'unsafe-eval' 'unsafe-inline'; img-src 'self' data:;")))
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PATH.matcher("/api/authenticate"), PATH.matcher("/api/rest/reset"))
				.permitAll()
				.requestMatchers(PATH.matcher("/api/**"), PATH.matcher("/protected/**"))
				.authenticated()
				.requestMatchers(PATH.matcher("/api_int/**"))
				.hasRole(AuthorizationConstants.SUPER_ADMIN)
				.requestMatchers(PATH.matcher("/websocket/**"), PATH.matcher("/actuator/**"),
						PATH.matcher("/scripts/**"), PATH.matcher("/libs/**"), PATH.matcher("/i18n/**"),
						PATH.matcher("/assets/**"), PATH.matcher("/swagger-ui.html"))
				.permitAll()
				.anyRequest()
				.permitAll());
		return http.build();
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}

}
