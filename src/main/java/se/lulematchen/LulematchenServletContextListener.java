package se.lulematchen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LulematchenServletContextListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new GameFetcher(), 0, 60, TimeUnit.SECONDS);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        scheduler.shutdownNow();
    }
}
