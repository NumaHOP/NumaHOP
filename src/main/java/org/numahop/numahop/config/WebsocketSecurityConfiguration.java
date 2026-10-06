package org.numahop.numahop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.security.messaging.context.SecurityContextChannelInterceptor;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Sécurise les messages WebSocket entrants.
 *
 * <p>
 * Remplace l'ancien {@code AbstractSecurityWebSocketMessageBrokerConfigurer} (déprécié,
 * supprimé dans Spring Security 6.5/7). Les intercepteurs de sécurité sont enregistrés
 * manuellement (contexte de sécurité + autorisation) sans l'intercepteur CSRF : cela
 * reproduit l'ancien comportement {@code sameOriginDisabled() == true}. L'annotation
 * {@code @EnableWebSocketSecurity} n'est volontairement pas utilisée car elle impose la
 * protection CSRF sur les messages WebSocket, non configurable à ce jour.
 */
@Configuration
public class WebsocketSecurityConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureClientInboundChannel(final ChannelRegistration registration) {
		registration.interceptors(new SecurityContextChannelInterceptor(),
				new AuthorizationChannelInterceptor(messageAuthorizationManager()));
	}

	private AuthorizationManager<Message<?>> messageAuthorizationManager() {
		return MessageMatcherDelegatingAuthorizationManager.builder()
			// message types other than MESSAGE and SUBSCRIBE
			.nullDestMatcher()
			.authenticated()
			// matches any destination that starts with /topic/
			.simpDestMatchers("/topic/**")
			.authenticated()
			// (i.e. cannot send messages directly to /topic/, /queue/)
			// (i.e. cannot subscribe to /topic/messages/* to get messages sent to
			// /topic/messages-user<id>)
			.simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE)
			.denyAll()
			// catch all
			.anyMessage()
			.denyAll()
			.build();
	}

}
