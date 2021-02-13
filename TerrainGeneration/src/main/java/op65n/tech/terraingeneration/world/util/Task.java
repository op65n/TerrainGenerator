package op65n.tech.terraingeneration.world.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Task {

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
            .setNameFormat("TerrainGeneration-[%d]")
            .setUncaughtExceptionHandler((thread, throwable) ->
                    System.out.printf("Caught an exception on Thread: %s, Throwable: %s", thread.getId(), throwable.getMessage())
            )
            .build());

    public static ExecutorService getService() {
        return SERVICE;
    }
}
