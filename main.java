try {
    String objectID = jdbcTemplate.queryForObject(query, new RowMapper<String>() {
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("column_name");
        }
    });
} catch (EmptyResultDataAccessException e) {
    // Handle the case when no results are found
    // For example, set objectID to a default value or throw an exception
}
