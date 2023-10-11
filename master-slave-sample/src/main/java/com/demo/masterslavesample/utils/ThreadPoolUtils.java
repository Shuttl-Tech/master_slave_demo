package com.demo.masterslavesample.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {
    public static ExecutorService DASHBOARD_ALERTS_EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(12);

}
