/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.media.AudioManager
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;

public class MediaSessionCompatApi8 {
    public static void registerMediaButtonEventReceiver(Context context, ComponentName componentName) {
        ((AudioManager)context.getSystemService("audio")).registerMediaButtonEventReceiver(componentName);
    }

    public static void unregisterMediaButtonEventReceiver(Context context, ComponentName componentName) {
        ((AudioManager)context.getSystemService("audio")).unregisterMediaButtonEventReceiver(componentName);
    }
}

