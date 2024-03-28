package BaixaQuality.NoKatarina2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BaixaQuality.NoKatarina2024.application.AskChampionsUseCase;
import BaixaQuality.NoKatarina2024.application.ListChampionsUseCase;
import ports.ChampionsRepository;

@SpringBootApplication
public class NoKatarina2024Application {

	public static void main(String[] args) {
		SpringApplication.run(NoKatarina2024Application.class, args); }
	
     @Bean
     public ListChampionsUseCase providelListChampionsUseCase(ChampionsRepository Repository) {
     return new ListChampionsUseCase(Repository);
	}

     @Bean
     public AskChampionsUseCase provideAskChampionsUseCase(ChampionsRepository Repository) {
     return new AskChampionsUseCase(Repository);
	}


}