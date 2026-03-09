package BaixaQuality.NoKatarina2024.application;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Service
public class GeminiAIService {

    private static final Logger logger = LoggerFactory.getLogger(GeminiAIService.class);

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiUrl;

    public GeminiAIService(
            RestTemplate restTemplate,
            @Value("${gemini.api.key:}") String apiKey,
            @Value("${gemini.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public String generateContent(String prompt) {
        if (apiKey == null || apiKey.isBlank()) {
            logger.warn("Gemini API key não configurada. Defina a propriedade 'gemini.api.key' ou a variável de ambiente GEMINI_API_KEY.");
            return "Serviço de IA não disponível: chave de API do Gemini não configurada.";
        }

        try {
            String url = apiUrl + "?key=" + apiKey;

            Map<String, Object> request = Map.of(
                "contents", List.of(
                    Map.of("parts", List.of(
                        Map.of("text", prompt)
                    ))
                )
            );

            GeminiResponse response = restTemplate.postForObject(url, request, GeminiResponse.class);

            if (response != null
                    && response.candidates() != null
                    && !response.candidates().isEmpty()
                    && response.candidates().get(0).content() != null
                    && response.candidates().get(0).content().parts() != null
                    && !response.candidates().get(0).content().parts().isEmpty()) {
                return response.candidates().get(0).content().parts().get(0).text();
            }

            return "Não foi possível obter uma resposta da IA.";
        } catch (Exception e) {
            logger.error("Erro ao chamar a API do Gemini", e);
            return "Erro ao consultar a IA. Tente novamente mais tarde.";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GeminiResponse(List<Candidate> candidates) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Candidate(Content content) { }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Content(List<Part> parts) { }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Part(String text) { }
    }
}
