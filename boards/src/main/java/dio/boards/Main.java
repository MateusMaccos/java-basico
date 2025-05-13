package dio.boards;

import static dio.boards.persistence.config.ConnectionConfig.getConnection;

import java.sql.SQLException;

import dio.boards.persistence.migration.MigrationStrategy;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        }

    }
}
