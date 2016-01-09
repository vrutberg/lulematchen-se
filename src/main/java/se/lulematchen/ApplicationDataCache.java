package se.lulematchen;

import se.lulematchen.api.dao.GameList;

import java.util.concurrent.ConcurrentHashMap;

public class ApplicationDataCache {
    private static ApplicationDataCache instance = null;

    private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    public GameList getGames() {
        if (!cache.containsKey("GAMES")) {
            return new GameList();
        }

        return (GameList)cache.get("GAMES");
    }

    public void putGames(GameList games) {
        cache.put("GAMES", games);
    }

    public static ApplicationDataCache getInstance() {
        if (instance == null) {
            instance = new ApplicationDataCache();
        }

        return instance;
    }
}
