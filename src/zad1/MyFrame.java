package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static JPanel weatherPanel;
    private static JPanel ratesPanel;
    private static JPanel NBPPanel;
    private static JFXPanel wikiPanel;

    public MyFrame() {
        createWeatherPanel();
        createRatesPanel();
        createNBPPanel();
        createWikiPanel();

        this.add(weatherPanel, BorderLayout.NORTH);
        this.add(ratesPanel, BorderLayout.WEST);
        this.add(NBPPanel, BorderLayout.EAST);
        this.add(wikiPanel, BorderLayout.CENTER);
    }

    public void createWeatherPanel() {
        weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.setPreferredSize(new Dimension(200, 200));
        weatherPanel.setBorder(BorderFactory.createTitledBorder("Weather"));
        JLabel weather = new JLabel("Weather: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("weather") + "weather".length() + 21,
                Main.getWeatherJson().indexOf("description") - 3));
        weatherPanel.add(weather);
        JLabel description = new JLabel("Description: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("description") + "description".length() + 3,
                Main.getWeatherJson().indexOf("icon") - 3));
        weatherPanel.add(description);
        JLabel temperature = new JLabel("Temperature: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("temp") + "temp".length() + 2,
                Main.getWeatherJson().indexOf("feel") - 2) + "°C");
        weatherPanel.add(temperature);
        JLabel feelsLIke = new JLabel("Feels like: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("feels_like") + "feels_like".length() + 2,
                Main.getWeatherJson().indexOf("temp_min") - 2) + "°C");
        weatherPanel.add(feelsLIke);
        JLabel minTemp = new JLabel("Min temperature: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("temp_min") + "temp_min".length() + 2,
                Main.getWeatherJson().indexOf("temp_max") - 2) + "°C");
        weatherPanel.add(minTemp);
        JLabel maxTemp = new JLabel("Max temperature: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("temp_max") + "temp_max".length() + 2,
                Main.getWeatherJson().indexOf("pressure") - 2));
        weatherPanel.add(maxTemp);
        JLabel pressure = new JLabel("Pressure: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("pressure") + "pressure".length() + 2,
                Main.getWeatherJson().indexOf("humidity") - 2) + "\n");
        weatherPanel.add(pressure);
        JLabel humidity = new JLabel("Humidity: " + Main.getWeatherJson().substring(Main.getWeatherJson().indexOf("humidity") + "humidity".length() + 2,
                Main.getWeatherJson().indexOf("visibility") - 3));
        weatherPanel.add(humidity);
    }

    public void createRatesPanel() {
        ratesPanel = new JPanel();
        ratesPanel.setLayout(new BoxLayout(ratesPanel, BoxLayout.Y_AXIS));
        ratesPanel.setPreferredSize(new Dimension(300, 100));
        ratesPanel.setBorder(BorderFactory.createTitledBorder("Rates"));

        JLabel rate = new JLabel("1 " + Main.getS().getCurrency() + " = " + Main.getRate1() + " " + Main.getS().getCode());
        ratesPanel.add(rate);
    }

    public void createNBPPanel() {
        NBPPanel = new JPanel();
        NBPPanel.setLayout(new BoxLayout(NBPPanel, BoxLayout.Y_AXIS));
        NBPPanel.setPreferredSize(new Dimension(300, 100));
        NBPPanel.setBorder(BorderFactory.createTitledBorder("NBP Rates"));

        JLabel nbp = new JLabel("Average exchange rate of " + Main.getS().getCode() + " to PLN: " + Main.getRate2());
        NBPPanel.add(nbp);
    }

    public void createWikiPanel() {
        wikiPanel = new JFXPanel();
        wikiPanel.setLayout(new BoxLayout(wikiPanel, BoxLayout.Y_AXIS));
        wikiPanel.setPreferredSize(new Dimension(1000, 600));
        wikiPanel.setBorder(BorderFactory.createTitledBorder("Wiki"));

        Platform.runLater(() -> {
            WebView webView = new WebView();
            wikiPanel.setScene(new Scene(webView));
            webView.getEngine().load(Main.getWiki());
        });
    }
}
