package com.algaworks.example.product.infra.client;

import com.algaworks.example.product.api.ProductReviewClient;
import com.algaworks.example.product.api.ReviewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductReviewClientImpl implements ProductReviewClient {

	private final Logger logger = LoggerFactory.getLogger(ProductReviewClientImpl.class);
	private final RestTemplate restTemplate;
	
	private final String apiHost = UriComponentsBuilder
			.fromHttpUrl("http://localhost:8082")
			.encode()
			.toUriString();
	
	public ProductReviewClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ReviewModel> findByProduct(Long productId) {
		final Map<String, Object> parametros = new HashMap<>();
		parametros.put("productId", productId);

		logger.debug("Buscando avaliações");
		final ReviewModel[] avaliacoes;
		final var path = "/products/" + productId + "/reviews";
		final var requestUrl = apiHost + path;

		try {
			avaliacoes = restTemplate.getForObject(requestUrl, ReviewModel[].class, parametros);
		} catch (Exception e) {
			logger.error("Erro ao buscar avaliações");
			throw e;
		}

		return Arrays.asList(avaliacoes);
	}
}
