package fr.xebia.extremestartup.logreader;

import java.sql.*;

public class LogsToDb {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:logs", "SA", "")) {
            createSchema(connection);
            LogReader.readLogs(LogsToDb.class.getResourceAsStream("log-xke.txt"), new LogCallback() {
                @Override
                public void newLog(int logNumber, Log newLog) {
                    insertLog(logNumber, newLog, connection);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createSchema(Connection connection) throws SQLException {
        Statement schemaStatement = connection.createStatement();

        schemaStatement.addBatch("CREATE TABLE IF NOT EXISTS player (" +
                "url VARCHAR NOT NULL PRIMARY KEY, " +
                "name VARCHAR)");
        schemaStatement.addBatch("DELETE FROM player");

        schemaStatement.addBatch("CREATE TABLE IF NOT EXISTS log (" +
                "id INTEGER NOT NULL, " +
                "playerUrl VARCHAR NOT NULL, " +
                "questionId VARCHAR NOT NULL, " +
                "questionType VARCHAR NOT NULL, " +
                "question VARCHAR NOT NULL, " +
                "answer VARCHAR, " +
                "status VARCHAR NOT NULL, " +
                "score INTEGER NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (playerUrl) REFERENCES player (url))");
        schemaStatement.addBatch("TRUNCATE TABLE log");

        schemaStatement.executeBatch();
        connection.setAutoCommit(false);
    }

    private static void insertLog(int logNumber, Log newLog, Connection connection) {
        try {
            PreparedStatement isPlayerNotExistsQuery = connection.prepareStatement("SELECT COUNT(*) FROM player WHERE url = ?");
            isPlayerNotExistsQuery.setString(1, newLog.getPlayer().getPlayerURL());
            ResultSet resultSet = isPlayerNotExistsQuery.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                PreparedStatement insertNewPlayer = connection.prepareStatement("INSERT INTO player VALUES (?, ?)");
                insertNewPlayer.setString(1, newLog.getPlayer().getPlayerURL());
                insertNewPlayer.setString(2, newLog.getPlayer().getPlayerName());
                insertNewPlayer.execute();
            }

            PreparedStatement insertNewLog = connection.prepareStatement("INSERT INTO log VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertNewLog.setInt(1, logNumber);
            insertNewLog.setString(2, newLog.getPlayer().getPlayerURL());
            insertNewLog.setString(3, newLog.getQuestionId());
            insertNewLog.setString(4, newLog.getQuestionType().name());
            insertNewLog.setString(5, newLog.getQuestion());
            insertNewLog.setString(6, newLog.getAnswer());
            insertNewLog.setString(7, newLog.getStatus().name());
            insertNewLog.setInt(8, newLog.getScore());
            insertNewLog.execute();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackFailedException) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        }
    }

}
