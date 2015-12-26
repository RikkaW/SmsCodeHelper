/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.android.mms.ui.ViewInterface;
import java.util.Map;

public interface SlideViewInterface
extends ViewInterface {
    public void pauseAudio();

    public void pauseVideo();

    public void seekAudio(int var1);

    public void seekVideo(int var1);

    public void setAudio(Uri var1, String var2, Map<String, ?> var3);

    public void setAudioDuration(int var1);

    public void setImage(String var1, Drawable var2);

    public void setImageRegionFit(String var1);

    public void setImageVisibility(boolean var1);

    public void setText(String var1, String var2);

    public void setTextVisibility(boolean var1);

    public void setVideo(String var1, Uri var2);

    public void setVideoVisibility(boolean var1);

    public void startAudio();

    public void startVideo();

    public void stopAudio();

    public void stopVideo();
}

