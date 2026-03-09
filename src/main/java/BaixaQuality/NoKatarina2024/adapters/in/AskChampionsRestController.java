package BaixaQuality.NoKatarina2024.adapters.in;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BaixaQuality.NoKatarina2024.application.AskChampionsUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Campeões", description = "Endpoints do dominio de campeões")
@RestController
@RequestMapping("/champions")
public record AskChampionsRestController(AskChampionsUseCase useCase) {

    @PostMapping("/{ChampionsId}/ask")
    public AskChampionsResponse askChampion(
        @PathVariable("ChampionsId")Long championsId,
        @RequestBody AskChampionsRequest request) {
         
       String answer = useCase.askChampion(championsId, request.Question());
        
            return new AskChampionsResponse(answer);
    }

    public record AskChampionsRequest(String Question) { }
    public record AskChampionsResponse(String answer) { }

}
