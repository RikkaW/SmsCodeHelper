/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.ResultReceiver
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media.session;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompatApi14;

class MediaSessionCompat$MediaSessionImplBase$2
implements MediaSessionCompatApi14.Callback {
    final /* synthetic */ MediaSessionCompat.MediaSessionImplBase this$0;
    final /* synthetic */ MediaSessionCompat.Callback val$callback;

    MediaSessionCompat$MediaSessionImplBase$2(MediaSessionCompat.MediaSessionImplBase mediaSessionImplBase, MediaSessionCompat.Callback callback) {
        this.this$0 = mediaSessionImplBase;
        this.val$callback = callback;
    }

    @Override
    public void onCommand(String string2, Bundle bundle, ResultReceiver resultReceiver) {
        this.val$callback.onCommand(string2, bundle, resultReceiver);
    }

    @Override
    public void onFastForward() {
        this.val$callback.onFastForward();
    }

    @Override
    public boolean onMediaButtonEvent(Intent intent) {
        return this.val$callback.onMediaButtonEvent(intent);
    }

    @Override
    public void onPause() {
        this.val$callback.onPause();
    }

    @Override
    public void onPlay() {
        this.val$callback.onPlay();
    }

    @Override
    public void onRewind() {
        this.val$callback.onRewind();
    }

    @Override
    public void onSeekTo(long l2) {
        this.val$callback.onSeekTo(l2);
    }

    @Override
    public void onSetRating(Object object) {
        this.val$callback.onSetRating(RatingCompat.fromRating(object));
    }

    @Override
    public void onSkipToNext() {
        this.val$callback.onSkipToNext();
    }

    @Override
    public void onSkipToPrevious() {
        this.val$callback.onSkipToPrevious();
    }

    @Override
    public void onStop() {
        this.val$callback.onStop();
    }
}

