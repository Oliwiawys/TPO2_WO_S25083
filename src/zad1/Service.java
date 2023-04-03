/**
 * @author Wysokińska Oliwia S25083
 */

package zad1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Service {
    private final String country;
    private final List<Codes> codes;
    private final List<Locales> locales;
    private String code;
    private String locale;
    private String currency;
    private String city;

    public Service(String country) {
        codes = new ArrayList<>();
        locales = new ArrayList<>();
        this.country = country;
        createCodesList();
        creatLocaleList();
        for (Codes value : codes) {
            if (value.getCountry().equals(country))
                code = value.getCode();
        }
        for (Locales value : locales) {
            if (value.getCountry().equals(country))
                locale = value.getLocale().substring(value.getLocale().indexOf("_") + 1);
        }
    }

    public void createCodesList() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Data/currencies.csv"));
            String line;
            String[] countries;
            while ((line = in.readLine()) != null) {
                countries = line.split(";");
                codes.add(new Codes(countries[0], countries[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatLocaleList() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Data/locale.csv"));
            String line;
            String[] countries;
            while ((line = in.readLine()) != null) {
                countries = line.split(";");
                locales.add(new Locales(countries[0], countries[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWeather(String city) {
        this.city = city;
        URL url;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + locale + "&APPID=485f7e0801f04adaf21a724a9ad0947a&units=metric");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String cont = "";
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
                cont += line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cont;
    }

    public Double getRateFor(String currency) {
        this.currency = currency;
        URL url;
        try {
            url = new URL("https://api.exchangerate.host/latest?base=" + currency + "&symbols=" + code);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String cont = "";
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
                cont += line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int index = cont.indexOf(code) + code.length() + 2;
        String rate = cont.substring(index, index + 8);
        return Double.parseDouble(rate);
    }

    public Double getNBPRate() {
        URL url;
        URL url1;
        String rate;
        if (country.equals("Poland"))
            rate = "0";
        else {
            try {
                url = new URL("https://static.nbp.pl/dane/kursy/xml/en/23a064en.xml");
                url1 = new URL("https://static.nbp.pl/dane/kursy/xml/en/23b013en.xml");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            String cont = "";
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null)
                    cont += line + "\n";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                in = new BufferedReader(new InputStreamReader(url1.openStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null)
                    cont += line + "\n";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int index = cont.indexOf(code) + code.length() + 2;
            rate = cont.substring(index, index + 6);
        }
        return Double.parseDouble(rate);
    }

    public String getWiki() {
        return "https://en.wikipedia.org/wiki/" + city;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }
}
