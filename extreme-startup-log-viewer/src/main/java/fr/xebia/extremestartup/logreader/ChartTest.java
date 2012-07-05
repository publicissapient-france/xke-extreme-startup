package fr.xebia.extremestartup.logreader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ChartTest extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Extreme Startup At Xebia France - 2012-07-05");
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Number of requests");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Extreme Startup At Xebia France - 2012-07-05");

        Map<Player, XYChart.Series> series = new HashMap<>();
        int i = 0;

        for (Log log : LogReader.readLogs(ChartTest.class.getResourceAsStream("log-xke.txt"))) {
            if (!series.containsKey(log.getPlayer())) {
                XYChart.Series<Object, Object> newSerie = new XYChart.Series<>();
                series.put(log.getPlayer(), newSerie);
                newSerie.setName(log.getPlayer().getPlayerName());
            }
            series.get(log.getPlayer()).getData().add(new XYChart.Data<>(i++, log.getScore()));
        }

        Scene scene = new Scene(lineChart, 1340, 800);

        for (Map.Entry<Player, XYChart.Series> playerSeriesEntry : series.entrySet()) {
            lineChart.getData().add(playerSeriesEntry.getValue());
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
