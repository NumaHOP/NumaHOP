package org.numahop.numahop.web.rest.administration;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.NoOpResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Controller permettant de rediriger les appels vers /actuator/health pour ne pas exposer
 * directement /actuator et avoir un accès authentifié
 */
@RestController
@RequestMapping(value = "/api/rest/health")
public class HealthController {

	private final RestTemplate restTemplate;

	private final String healthUrl;

	public HealthController(final RestTemplateBuilder restTemplateBuilder,
			@Value("${server.port}") final int serverPort) {
		this.healthUrl = "http://localhost:" + serverPort + "/actuator/health";
		// On ne veut jamais d'erreur : le statut renvoyé par /actuator/health est relayé
		// tel quel.
		this.restTemplate = restTemplateBuilder.errorHandler(new NoOpResponseErrorHandler()).build();
	}

	@GetMapping
	@ResponseBody
	public String mirrorRest(final HttpServletRequest request) throws URISyntaxException {
		return restTemplate.getForEntity(healthUrl, String.class).getBody();
	}

}
