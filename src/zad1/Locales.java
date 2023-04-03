package zad1;

public class Locales {

    private final String locale;
    private final String country;

    public Locales(String locale, String country) {
        this.locale = locale;
        this.country = country;
    }

    public String getLocale() {
        return locale;
    }

    public String getCountry() {
        return country;
    }
}
