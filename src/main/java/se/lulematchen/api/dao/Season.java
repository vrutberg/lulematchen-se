package se.lulematchen.api.dao;

public class Season {
    private final String season;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Season)) return false;

        Season season1 = (Season) o;

        return season.equals(season1.season);
    }

    @Override
    public int hashCode() {
        return season.hashCode();
    }
}
