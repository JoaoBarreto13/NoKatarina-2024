package BaixaQuality.NoKatarina2024.application;

import domain.exception.BaixaQuality.NoKatarina2024.domain.exception.ChampionNotFoundException;
import domain.model.Champions;
import ports.ChampionsRepository;

public record AskChampionsUseCase(ChampionsRepository repository, GeminiAIService geminiAIService) {

    public String askChampion(Long championId, String question) {

        Champions champion = repository.findbyid(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String prompt = champion.generateContextByQuestion(question);

        return geminiAIService.generateContent(prompt);

    }

}
