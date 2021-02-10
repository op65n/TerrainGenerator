package op65n.tech.terraingenerator.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariDataSource hikariDataSource;

    public static Connection connection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public static void init() {
        final Properties properties = new Properties();
        properties.setProperty("dataSource.cachePrepStmts", "true");
        properties.setProperty("dataSource.prepStmtCacheSize", "250");
        properties.setProperty("dataSource.prepStmtCacheSqlLimit",  "2048");
        properties.setProperty("dataSource.useServerPrepStmts",  "true");
        properties.setProperty("dataSource.useLocalSessionState",  "true");
        properties.setProperty("dataSource.rewriteBatchedStatements",  "true");
        properties.setProperty("dataSource.cacheResultSetMetadata",  "true");
        properties.setProperty("dataSource.cacheServerConfiguration",  "true");
        properties.setProperty("dataSource.elideSetAutoCommits",  "true");
        properties.setProperty("dataSource.maintainTimeStats", "false");
        final HikariConfig hikariConfig = new HikariConfig(properties);

        hikariConfig.setPoolName("KrusicsHikariPool");

        final String jdbc = "mariadb";
        final String ip = "127.0.0.1";
        final String port = "3306";
        final String database = "krusics_secret_database";
        hikariConfig.setJdbcUrl("jdbc:" + jdbc + "://" + ip + ":" + port + "/" + database);

        hikariConfig.setUsername("username");
        hikariConfig.setPassword("passwd");

        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");

        DataSource.hikariDataSource = new HikariDataSource(hikariConfig);
    }

}
