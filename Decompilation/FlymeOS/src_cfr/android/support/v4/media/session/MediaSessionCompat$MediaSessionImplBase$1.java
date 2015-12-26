/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.ParcelableVolumeInfo;

class MediaSessionCompat$MediaSessionImplBase$1
extends VolumeProviderCompat.Callback {
    final /* synthetic */ MediaSessionCompat.MediaSessionImplBase this$0;

    MediaSessionCompat$MediaSessionImplBase$1(MediaSessionCompat.MediaSessionImplBase mediaSessionImplBase) {
        this.this$0 = mediaSessionImplBase;
    }

    @Override
    public void onVolumeChanged(VolumeProviderCompat object) {
        if (MediaSessionCompat.MediaSessionImplBase.access$200(this.this$0) != object) {
            return;
        }
        object = new ParcelableVolumeInfo(MediaSessionCompat.MediaSessionImplBase.access$300(this.this$0), MediaSessionCompat.MediaSessionImplBase.access$400(this.this$0), object.getVolumeControl(), object.getMaxVolume(), object.getCurrentVolume());
        MediaSessionCompat.MediaSessionImplBase.access$500(this.this$0, (ParcelableVolumeInfo)object);
    }
}

