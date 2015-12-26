/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;

public class TaskExecutor {
    private static SerializedAsyncTaskProcessor mAsyncProcessor = new SerializedAsyncTaskProcessor(true, 20);

    public static void execute(SerializedAsyncTaskProcessor.SerializedAsyncTask serializedAsyncTask) {
        mAsyncProcessor.addNewTask(serializedAsyncTask);
    }

    public static void execute(SerializedAsyncTaskProcessor.SerializedAsyncTask serializedAsyncTask, long l) {
        mAsyncProcessor.addNewTaskWithDelayed(serializedAsyncTask, l);
    }

    public static void execute(final Runnable runnable) {
        mAsyncProcessor.addNewTask(new SerializedAsyncTaskProcessor.SerializedAsyncTask(){

            @Override
            public void process() {
                runnable.run();
            }
        });
    }

}

