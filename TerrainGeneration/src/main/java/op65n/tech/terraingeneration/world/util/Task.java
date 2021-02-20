package op65n.tech.terraingeneration.world.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("UnusedReturnValue")
public final class Task {

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
            .setNameFormat("TerrainGeneration-[%d]")
            .setUncaughtExceptionHandler((thread, throwable) ->
                    System.out.printf("Caught an exception on Thread: %s, Throwable: %s", thread.getId(), throwable.getMessage())
            )
            .build());

    public static Future<?> submit(final Runnable runnable) {
        return SERVICE.submit(runnable);
    }

}
