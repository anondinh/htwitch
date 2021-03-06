package org.dinhware.bot.adapter.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dinhware.bot.adapter.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by: Niklas
 * Date: 20.01.2018
 * Alias: Dinh
 * Time: 12:32
 */

public class AdapterExecutor {

    private static Logger LOGGER = LogManager.getLogger(AdapterExecutor.class);
    private static ExecutorService executorService;

    static {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 50, 120L, TimeUnit.SECONDS, new SynchronousQueue<>());
        executorService.allowCoreThreadTimeOut(true);
        AdapterExecutor.executorService = executorService;
        LOGGER.info(executorService.toString());
    }

    public static void submit(Listener listener, String[] arguments, String line) {
        executorService.submit(() -> {
            Map<String, String> tags = new HashMap<>();
            if (line.startsWith("@")) {
                for (String split : arguments[0].substring(1).split(";")) {
                    String[] pair = split.split("=");
                    tags.put(pair[0], pair.length < 2 ? null : pair[1]);
                }
            }
            try {
                listener.dispatch(tags, arguments, line);
            } catch (Throwable throwable) {
                LOGGER.error(throwable.toString());
            }
        });
    }
}
