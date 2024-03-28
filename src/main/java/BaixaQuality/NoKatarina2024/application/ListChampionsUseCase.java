package BaixaQuality.NoKatarina2024.application;

import java.util.List;

import domain.model.Champions;
import ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository Repository) {

    public List<Champions> findAll() {
        return Repository.findall();
    }

}
