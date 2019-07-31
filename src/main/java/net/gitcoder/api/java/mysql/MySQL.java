package net.gitcoder.api.java.mysql;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.Builder;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.*;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class MySQL {

    private final ExecutorService QUERY_EXECUTOR = Executors.newCachedThreadPool(
            new ThreadFactoryBuilder()
                    .setNameFormat("database-thread #%s")
                    .setDaemon(true)
                    .build());

    private Connection connection;
    private final MysqlDataSource dataSource = new MysqlDataSource();


    /**
     * Конструктор для подключения к бд.
     * @param host - имя хоста.
     * @param user - логин.
     * @param password - пароль.
     * @param database - ДБ.
     */
    @Builder(builderMethodName = "newBuilder", builderClassName = "MySqlBuilder", buildMethodName = "create")

    public MySQL(String host,
                 String user,
                 String password,
                 String database) {

        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(host);
        dataSource.setDatabaseName(database);
        dataSource.setPort(3306);

        try {
            dataSource.setAutoReconnect(true);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Переподключение миски.
     */
    private void refreshConnection() {
        try {
            if (connection != null && !connection.isClosed() && connection.isValid(1000)) {
                return;
            }

            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка с MysQL обратитесь к GitCoder'y vk.com/gitcoder");
        }
    }

    /**
     * Выполнения query запроса в базу данных.
     * @param sql - запрос.
     * @param handler - результат.
     * @param objects - обьект.
     * @param <T> - generics.
     * @return
     */
    public <T> T executeQuery(String sql, ResponseHandler<ResultSet, T> handler, Object... objects) {
        return executeQuery(false, sql, handler, objects);
    }

    public <T> T executeQuery(boolean async, String sql, ResponseHandler<ResultSet, T> handler, Object... objects) {
        Callable<T> callable = () -> {
            try (PreparedStatement preparedStatement = createStatement(sql, objects)) {
                refreshConnection();
                ResultSet rs = preparedStatement.executeQuery();
                return handler.handleResponse(rs);
            } catch (Exception e) {
                Bukkit.getLogger().info(String.format("Прозошла ошибка при исполнении запроса (%s) - %s", sql, e.getLocalizedMessage()));
                return null;
            }
        };

        Future<T> future = asyncTask(callable);

        if(async) {
            return null;
        }

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Выполнение execute запроса в базу данных.
     * @param sql - запрос.
     * @param objects - обьект.
     */
    public void execute(String sql, Object... objects) {
        Callable<Integer> callable = () -> {
            try (PreparedStatement ps = createStatement(sql, objects)) {
                refreshConnection();
                ps.execute();
            } catch (Exception e) {
                Bukkit.getLogger().info(String.format("Прозошла ошибка при исполнении запроса (%s) - %s", sql, e.getLocalizedMessage()));
            }

            return 0;
        };

        asyncTask(callable);
    }

    /**
     * Создание statement.
     * @param query - запрос.
     * @param objects - обьект.
     * @return
     * @throws SQLException
     */
    private PreparedStatement createStatement(String query, Object... objects) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.NO_GENERATED_KEYS);

        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);
            }
        }
        return ps;
    }

    private <T> Future<T> asyncTask(Callable<T> callable) {
        return QUERY_EXECUTOR.submit(callable);
    }

}
