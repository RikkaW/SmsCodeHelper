/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.session.PlaybackState
 *  android.media.session.PlaybackState$Builder
 *  android.media.session.PlaybackState$CustomAction
 *  android.media.session.PlaybackState$CustomAction$Builder
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

class PlaybackStateCompatApi21 {
    PlaybackStateCompatApi21() {
    }

    public static long getActions(Object object) {
        return ((PlaybackState)object).getActions();
    }

    public static long getActiveQueueItemId(Object object) {
        return ((PlaybackState)object).getActiveQueueItemId();
    }

    public static long getBufferedPosition(Object object) {
        return ((PlaybackState)object).getBufferedPosition();
    }

    public static List<Object> getCustomActions(Object object) {
        return ((PlaybackState)object).getCustomActions();
    }

    public static CharSequence getErrorMessage(Object object) {
        return ((PlaybackState)object).getErrorMessage();
    }

    public static long getLastPositionUpdateTime(Object object) {
        return ((PlaybackState)object).getLastPositionUpdateTime();
    }

    public static float getPlaybackSpeed(Object object) {
        return ((PlaybackState)object).getPlaybackSpeed();
    }

    public static long getPosition(Object object) {
        return ((PlaybackState)object).getPosition();
    }

    public static int getState(Object object) {
        return ((PlaybackState)object).getState();
    }

    public static Object newInstance(int n2, long l2, long l3, float f2, long l4, CharSequence object, long l5, List<Object> list, long l6) {
        PlaybackState.Builder builder = new PlaybackState.Builder();
        builder.setState(n2, l2, f2, l5);
        builder.setBufferedPosition(l3);
        builder.setActions(l4);
        builder.setErrorMessage((CharSequence)object);
        object = list.iterator();
        while (object.hasNext()) {
            builder.addCustomAction((PlaybackState.CustomAction)object.next());
        }
        builder.setActiveQueueItemId(l6);
        return builder.build();
    }

    static final class CustomAction {
        CustomAction() {
        }

        public static String getAction(Object object) {
            return ((PlaybackState.CustomAction)object).getAction();
        }

        public static Bundle getExtras(Object object) {
            return ((PlaybackState.CustomAction)object).getExtras();
        }

        public static int getIcon(Object object) {
            return ((PlaybackState.CustomAction)object).getIcon();
        }

        public static CharSequence getName(Object object) {
            return ((PlaybackState.CustomAction)object).getName();
        }

        public static Object newInstance(String string2, CharSequence charSequence, int n2, Bundle bundle) {
            string2 = new PlaybackState.CustomAction.Builder(string2, charSequence, n2);
            string2.setExtras(bundle);
            return string2.build();
        }
    }

}

