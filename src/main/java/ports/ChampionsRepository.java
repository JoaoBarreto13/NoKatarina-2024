package ports;

import java.util.List;
import java.util.Optional;

import domain.model.Champions;

public interface ChampionsRepository {
    List<Champions> findall();
  

    Optional<Champions> findbyid(Long id);
}
