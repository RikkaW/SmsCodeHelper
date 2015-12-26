/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.session.PlaybackState
 *  android.media.session.PlaybackState$Builder
 *  android.media.session.PlaybackState$CustomAction
 *  android.os.Bundle
 *  java.lang.Object
 */
package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

class PlaybackStateCompatApi22 {
    PlaybackStateCompatApi22() {
    }

    public static Bundle getExtras(Object object) {
        return ((PlaybackState)object).getExtras();
    }

    public static Object newInstance(int n2, long l2, long l3, float f2, long l4, CharSequence object, long l5, List<Object> list, long l6, Bundle bundle) {
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
        builder.setExtras(bundle);
        return builder.build();
    }
}

