package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;


public class UrlCheckRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (urlCheck.getCreatedAt() == null) {
                urlCheck.setCreatedAt(LocalDateTime.now());
            }
            preparedStatement.setLong(1, urlCheck.getUrlId());
            preparedStatement.setInt(2, urlCheck.getStatusCode());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getTitle());
            preparedStatement.setString(5, urlCheck.getDescription());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(urlCheck.getCreatedAt()));
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Не сформирован ID");
            }
        }
    }

    public static List<UrlCheck> find(Long urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, urlId);
            var resultSet = stmt.executeQuery();

            var results = new ArrayList<UrlCheck>();
            var currentListId = 1L;

            while (resultSet.next()) {
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var urlCheck = new UrlCheck(statusCode, h1, title, description, createdAt);
                urlCheck.setId(currentListId);

                currentListId++;
                results.add(urlCheck);
            }
            return results;
        }
    }

    public static Optional<UrlCheck> findLatestCheck(Long urlId) throws SQLException {
        var sql = "SELECT status_code, created_at FROM url_checks WHERE url_id = ? ORDER BY id DESC LIMIT 1";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, urlId);
            var resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                var statusCode = resultSet.getInt("status_code");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var check = new UrlCheck(statusCode, createdAt);
                return Optional.of(check);
            }
            return Optional.empty();
        }
    }

    public static Map<Long, UrlCheck> findLatestChecksByUrlIds(List<Long> urlIds) throws SQLException {
        if (urlIds.isEmpty()) {
            return new HashMap<>();
        }
        String inClause = urlIds.stream().map(id -> "?").collect(Collectors.joining(", "));
        String sql = "SELECT DISTINCT ON (url_id) * FROM url_checks WHERE url_id IN (" + inClause + ") "
                + "ORDER BY url_id, id DESC";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            int idx = 1;
            for (Long id : urlIds) {
                stmt.setLong(idx++, id);
            }
            var resultSet = stmt.executeQuery();
            var result = new HashMap<Long, UrlCheck>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var urlId = resultSet.getLong("url_id");
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var check = new UrlCheck(statusCode, h1, title, description, createdAt);
                check.setId(id);
                check.setUrlId(urlId);
                check.setCreatedAt(createdAt);
                result.put(urlId, check);
            }
            return result;
        }
    }
}
