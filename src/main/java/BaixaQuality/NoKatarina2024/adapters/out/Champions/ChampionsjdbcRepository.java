package BaixaQuality.NoKatarina2024.adapters.out.Champions;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.model.Champions;
import ports.ChampionsRepository;

@Repository
public class ChampionsjdbcRepository implements ChampionsRepository {

    private final JdbcTemplate jbdcTemplate;
    private final RowMapper<Champions> rowMapper;
    
    public ChampionsjdbcRepository(JdbcTemplate jbdcTemplate) {
       this.jbdcTemplate = jbdcTemplate;
       this.rowMapper = (rs, rowNum) -> new Champions(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("lore"),
                rs.getString("image_Url")
                

       );
    }

    @Override
    public List<Champions> findall() {

        return jbdcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
    }

    @Override
    public Optional<Champions> findbyid(Long id) {
        String sql = "SELECT * FROM CHAMPIONS WHERE ID = ?";
        List<Champions> champions = jbdcTemplate.query(sql, rowMapper, id);
        return champions.stream().findFirst();
    }

}
