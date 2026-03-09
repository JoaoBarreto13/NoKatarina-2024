package BaixaQuality.NoKatarina2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import BaixaQuality.NoKatarina2024.application.AskChampionsUseCase;
import BaixaQuality.NoKatarina2024.application.GeminiAIService;
import BaixaQuality.NoKatarina2024.application.ListChampionsUseCase;
import ports.ChampionsRepository;

@SpringBootApplication
public class NoKatarina2024Application {

	public static void main(String[] args) {
		SpringApplication.run(NoKatarina2024Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}

	@Bean
	public AskChampionsUseCase provideAskChampionsUseCase(ChampionsRepository repository, GeminiAIService geminiAIService) {
		return new AskChampionsUseCase(repository, geminiAIService);
	}

}