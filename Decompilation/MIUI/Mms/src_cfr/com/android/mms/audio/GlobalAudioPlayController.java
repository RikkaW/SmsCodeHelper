/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.android.mms.audio;

import com.android.mms.ui.MessageListItem;
import java.util.List;

public class GlobalAudioPlayController {
    private static GlobalAudioPlayController sInstance;
    private List<MessageListItem> mPlayList;

    public static GlobalAudioPlayController get() {
        if (sInstance == null) {
            sInstance = new GlobalAudioPlayController();
        }
        return sInstance;
    }

    public void onStopPlay() {
        MessageListItem messageListItem;
        if (this.mPlayList != null && this.mPlayList.size() > 0 && (messageListItem = this.mPlayList.remove(0)) != null) {
            messageListItem.onMessageListItemClick();
        }
    }
}

