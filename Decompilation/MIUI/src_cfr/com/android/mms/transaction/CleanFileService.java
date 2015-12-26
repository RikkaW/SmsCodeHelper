/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Bundle
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioHelper;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import java.io.File;

public class CleanFileService
extends IntentService {
    public CleanFileService() {
        super("FileService");
    }

    private void cleanFile(String string) {
        Context context;
        block5 : {
            Object object;
            String string2 = string.replace((CharSequence)"/", (CharSequence)"\\/");
            context = this.getBaseContext();
            ContentResolver contentResolver = this.getBaseContext().getContentResolver();
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)(object = Telephony.Mms.CONTENT_URI), (String[])new String[]{"mx_extension"}, (String)"mx_extension like ?", (String[])new String[]{string2 = "%" + string2 + "%"}, (String)null)) != null) {
                if (context.moveToFirst() || !(contentResolver = new File(string = AudioHelper.getAudioDir() + "/" + string)).exists()) break block5;
                object = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
                if (object.isPlaying(string)) {
                    object.stop();
                }
                contentResolver.delete();
            }
        }
        return;
        finally {
            context.close();
        }
    }

    protected void onHandleIntent(Intent arrstring) {
        arrstring = arrstring.getExtras().getStringArray("paths");
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            this.cleanFile(arrstring[i]);
        }
    }
}

