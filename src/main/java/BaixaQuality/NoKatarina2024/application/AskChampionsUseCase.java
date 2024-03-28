package BaixaQuality.NoKatarina2024.application;

import java.util.List;

import domain.exception.BaixaQuality.NoKatarina2024.domain.exception.ChampionNotFoundException;
import domain.model.Champions;
import ports.ChampionsRepository;

public record AskChampionsUseCase(ChampionsRepository Repository) {

    public String askChampion(Long championId, String Question) {  

        Champions Champions = Repository.findbyid(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String ChampionContext = Champions.generateContextByQuestion(Question);

        return ChampionContext;

    }

}
