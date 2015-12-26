/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipData$Item
 *  android.content.ClipDescription
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.RemoteInputCompatBase;

class RemoteInputCompatJellybean {
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_LABEL = "label";
    private static final String KEY_RESULT_KEY = "resultKey";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";

    RemoteInputCompatJellybean() {
    }

    static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] intent, Intent intent2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        for (RemoteInputCompatBase.RemoteInput remoteInput : intent) {
            Object object = bundle.get(remoteInput.getResultKey());
            if (!(object instanceof CharSequence)) continue;
            bundle2.putCharSequence(remoteInput.getResultKey(), (CharSequence)object);
        }
        intent = new Intent();
        intent.putExtra("android.remoteinput.resultsData", bundle2);
        intent2.setClipData(ClipData.newIntent((CharSequence)"android.remoteinput.results", (Intent)intent));
    }

    static RemoteInputCompatBase.RemoteInput fromBundle(Bundle bundle, RemoteInputCompatBase.RemoteInput.Factory factory) {
        return factory.build(bundle.getString("resultKey"), bundle.getCharSequence("label"), bundle.getCharSequenceArray("choices"), bundle.getBoolean("allowFreeFormInput"), bundle.getBundle("extras"));
    }

    static RemoteInputCompatBase.RemoteInput[] fromBundleArray(Bundle[] arrbundle, RemoteInputCompatBase.RemoteInput.Factory factory) {
        if (arrbundle == null) {
            return null;
        }
        RemoteInputCompatBase.RemoteInput[] arrremoteInput = factory.newArray(arrbundle.length);
        for (int i2 = 0; i2 < arrbundle.length; ++i2) {
            arrremoteInput[i2] = RemoteInputCompatJellybean.fromBundle(arrbundle[i2], factory);
        }
        return arrremoteInput;
    }

    /*
     * Enabled aggressive block sorting
     */
    static Bundle getResultsFromIntent(Intent intent) {
        ClipDescription clipDescription;
        if ((intent = intent.getClipData()) == null || !(clipDescription = intent.getDescription()).hasMimeType("text/vnd.android.intent") || !clipDescription.getLabel().equals((Object)"android.remoteinput.results")) {
            return null;
        }
        return (Bundle)intent.getItemAt(0).getIntent().getExtras().getParcelable("android.remoteinput.resultsData");
    }

    static Bundle toBundle(RemoteInputCompatBase.RemoteInput remoteInput) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInput.getResultKey());
        bundle.putCharSequence("label", remoteInput.getLabel());
        bundle.putCharSequenceArray("choices", remoteInput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteInput.getExtras());
        return bundle;
    }

    static Bundle[] toBundleArray(RemoteInputCompatBase.RemoteInput[] arrremoteInput) {
        if (arrremoteInput == null) {
            return null;
        }
        Bundle[] arrbundle = new Bundle[arrremoteInput.length];
        for (int i2 = 0; i2 < arrremoteInput.length; ++i2) {
            arrbundle[i2] = RemoteInputCompatJellybean.toBundle(arrremoteInput[i2]);
        }
        return arrbundle;
    }
}

