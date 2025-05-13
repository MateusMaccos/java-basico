package dio.boards.persistence.migration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import liquibase.resource.ClassLoaderResourceAccessor;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;

import static dio.boards.persistence.config.ConnectionConfig.getConnection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MigrationStrategy {

    private final Connection connection;

    public void executeMigration(){
        var originalOut = System.out;
        var originalErr = System.err;
        try(var fos = new FileOutputStream("liquibase.log")){
            System.setOut(new PrintStream(fos));
            System.setErr(new PrintStream(fos));
            try(
                    var connection = getConnection();
                    var jdbcConnection = new JdbcConnection(connection);
            ){
                var liquibase = new Liquibase(
                        "/db/changelog/db.changelog-master.yml",
                        new ClassLoaderResourceAccessor(),
                        jdbcConnection);
                liquibase.update();
                liquibase.close();
            } catch (SQLException | LiquibaseException e) {
                e.printStackTrace();
                System.setErr(originalErr);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }

}
