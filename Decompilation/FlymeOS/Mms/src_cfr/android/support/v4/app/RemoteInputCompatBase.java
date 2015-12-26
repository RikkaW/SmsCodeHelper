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

class RemoteInputCompatBase {
    RemoteInputCompatBase() {
    }

    public static abstract class RemoteInput {
        protected abstract boolean getAllowFreeFormInput();

        protected abstract CharSequence[] getChoices();

        protected abstract Bundle getExtras();

        protected abstract CharSequence getLabel();

        protected abstract String getResultKey();

        public static interface Factory {
            public RemoteInput build(String var1, CharSequence var2, CharSequence[] var3, boolean var4, Bundle var5);

            public RemoteInput[] newArray(int var1);
        }

    }

}

