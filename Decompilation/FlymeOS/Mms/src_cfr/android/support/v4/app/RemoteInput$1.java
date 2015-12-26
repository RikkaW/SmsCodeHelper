/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.RemoteInputCompatBase;

final class RemoteInput$1
implements RemoteInputCompatBase.RemoteInput.Factory {
    RemoteInput$1() {
    }

    @Override
    public RemoteInput build(String string2, CharSequence charSequence, CharSequence[] arrcharSequence, boolean bl2, Bundle bundle) {
        return new RemoteInput(string2, charSequence, arrcharSequence, bl2, bundle);
    }

    public RemoteInput[] newArray(int n2) {
        return new RemoteInput[n2];
    }
}

