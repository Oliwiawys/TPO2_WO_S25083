/**
 * @author Wysokińska Oliwia S25083
 */

package zad1;

import javax.swing.*;

public class Main {
    private static String weatherJson;
    private static double rate1;
    private static double rate2;
    private static String wiki;
    private static Service s;

    public static void main(String[] args) {
        s = new Service("Italy");
        weatherJson = s.getWeather("Rome");
        rate1 = s.getRateFor("PLN");
        rate2 = s.getNBPRate();
        wiki = s.getWiki();

        SwingUtilities.invokeLater(
                () -> {
                    JFrame jFrame = new MyFrame();
                    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jFrame.setTitle("S_WEBCLIENTS");
                    jFrame.setVisible(true);
                    jFrame.pack();
                }
        );
    }

    public static String getWeatherJson() {
        return weatherJson;
    }

    public static double getRate1() {
        return rate1;
    }

    public static double getRate2() {
        return rate2;
    }

    public static Service getS() {
        return s;
    }

    public static String getWiki() {
        return wiki;
    }
}