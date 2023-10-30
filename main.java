public class SqlQueryBuilder {

    public String buildUpdateObjectPropertiesQuery() {
        return "UPDATE t_objectproperties SET value = :startTime " +
                "WHERE property = 'lastUpdated' " +
                "AND object_id = (SELECT object_id FROM t_object WHERE ea_guid = :parentGUID)";
    }
}


 public void updateObjectProperties(String startTime, String parentGUID) {
        String sql = sqlQueryBuilder.buildUpdateObjectPropertiesQuery();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("startTime", startTime)
                .addValue("parentGUID", parentGUID);

        jdbcTemplate.update(sql, params);
    }
