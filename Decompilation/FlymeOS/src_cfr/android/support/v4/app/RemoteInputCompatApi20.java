/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.RemoteInput
 *  android.app.RemoteInput$Builder
 *  android.content.Intent
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase;

class RemoteInputCompatApi20 {
    RemoteInputCompatApi20() {
    }

    static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] arrremoteInput, Intent intent, Bundle bundle) {
        RemoteInput.addResultsToIntent((RemoteInput[])RemoteInputCompatApi20.fromCompat(arrremoteInput), (Intent)intent, (Bundle)bundle);
    }

    static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput[] arrremoteInput) {
        if (arrremoteInput == null) {
            return null;
        }
        RemoteInput[] arrremoteInput2 = new RemoteInput[arrremoteInput.length];
        for (int i2 = 0; i2 < arrremoteInput.length; ++i2) {
            RemoteInputCompatBase.RemoteInput remoteInput = arrremoteInput[i2];
            arrremoteInput2[i2] = new RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build();
        }
        return arrremoteInput2;
    }

    static Bundle getResultsFromIntent(Intent intent) {
        return RemoteInput.getResultsFromIntent((Intent)intent);
    }

    static RemoteInputCompatBase.RemoteInput[] toCompat(RemoteInput[] arrremoteInput, RemoteInputCompatBase.RemoteInput.Factory factory) {
        if (arrremoteInput == null) {
            return null;
        }
        RemoteInputCompatBase.RemoteInput[] arrremoteInput2 = factory.newArray(arrremoteInput.length);
        for (int i2 = 0; i2 < arrremoteInput.length; ++i2) {
            RemoteInput remoteInput = arrremoteInput[i2];
            arrremoteInput2[i2] = factory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras());
        }
        return arrremoteInput2;
    }
}

