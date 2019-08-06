package net.gitcoder.api.bukkit.handler;

import net.gitcoder.api.bukkit.GitAPI;
import net.gitcoder.api.java.mysql.MySQL;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public final class DataSQLHandler {

    public final String name;

    private final MySQL database = GitAPI.getInstance().getDatabase();

    private final Map<String, Integer> stats = new HashMap<>();

    /**
     * Конструктор, создавающий новый обьект даты для статы.
     * @param name - имя игрока.
     */
    public DataSQLHandler(String name) {
        this.name = name;
    }

    /**
     * Добавление временной статистики в мапу.
     * @param field - имя.
     * @param data - количество.
     */
    public void addTempStat(String field, int data) {
        stats.put(field, stats.get(field) + data);
    }

    /**
     * Получение временной статистики по имени.
     * @param field - имя статистики.
     * @return - стату.
     */
    public int getTempStat(String field) {
        return stats.getOrDefault(field, 0);
    }

    /**
     * Выполненние запроса в миску со статистикой игрока.
     * @param field - файл.
     * @param table - таблица.
     * @param column - колонна.
     * @param data - количество.
     */
    public void addStat(String field, String table, String column, int data) {
        database.execute("");
    }

    /**
     * Получение статистики игрока из миски.
     * @param field - файл.
     * @param table - таблца.
     * @param column - колонна.
     * @return - стату.
     */
    public int getStat(String field, String table, String column) {
         database.executeQuery("", resultSet -> resultSet.getInt(column), name.toLowerCase());

         return 0;
    }
}
