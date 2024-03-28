package BaixaQuality.NoKatarina2024.adapters.in;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BaixaQuality.NoKatarina2024.application.ListChampionsUseCase;
import domain.model.Champions;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Campeões", description = "Endpoints do dominio de campeões")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

    @GetMapping
    public List<Champions> findAllChampions(){
        return useCase.findAll();
    }

}
