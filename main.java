import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.Date;

public class DataExtractorDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SqlQueryBuilder sqlQueryBuilder;

    public DataExtractorDao(NamedParameterJdbcTemplate jdbcTemplate, SqlQueryBuilder sqlQueryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlQueryBuilder = sqlQueryBuilder;
    }

    public void updateElements(Date startTime, String parentGUID) {
        String sql = sqlQueryBuilder.buildUpdateElementsQuery();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("startTime", startTime)
                .addValue("parentGUID", parentGUID);

        jdbcTemplate.update(sql, params);
    }
}
