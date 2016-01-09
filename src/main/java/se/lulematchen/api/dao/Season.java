package se.lulematchen.api.dao;

public class Season {
    private String season;

    private Season(String season) {
        this.season = season;
    }

    public static Season fromString(String season) {
        return new Season(season);
    }

    public static Season fromInt(int season) {
        return new Season(Integer.toString(season));
    }

    public String valueOf() {
        return season;
    }
}
