/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.RemoteInput$1;
import android.support.v4.app.RemoteInputCompatApi20;
import android.support.v4.app.RemoteInputCompatBase;
import android.support.v4.app.RemoteInputCompatJellybean;
import android.util.Log;

public class RemoteInput
extends RemoteInputCompatBase.RemoteInput {
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final RemoteInputCompatBase.RemoteInput.Factory FACTORY;
    private static final Impl IMPL;
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormInput;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    /*
     * Enabled aggressive block sorting
     */
    static {
        IMPL = Build.VERSION.SDK_INT >= 20 ? new ImplApi20() : (Build.VERSION.SDK_INT >= 16 ? new ImplJellybean() : new ImplBase());
        FACTORY = new RemoteInput$1();
    }

    RemoteInput(String string2, CharSequence charSequence, CharSequence[] arrcharSequence, boolean bl2, Bundle bundle) {
        this.mResultKey = string2;
        this.mLabel = charSequence;
        this.mChoices = arrcharSequence;
        this.mAllowFreeFormInput = bl2;
        this.mExtras = bundle;
    }

    public static void addResultsToIntent(RemoteInput[] arrremoteInput, Intent intent, Bundle bundle) {
        IMPL.addResultsToIntent(arrremoteInput, intent, bundle);
    }

    public static Bundle getResultsFromIntent(Intent intent) {
        return IMPL.getResultsFromIntent(intent);
    }

    @Override
    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormInput;
    }

    @Override
    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    @Override
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Override
    public CharSequence getLabel() {
        return this.mLabel;
    }

    @Override
    public String getResultKey() {
        return this.mResultKey;
    }

    public static final class Builder {
        private boolean mAllowFreeFormInput = true;
        private CharSequence[] mChoices;
        private Bundle mExtras = new Bundle();
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(String string2) {
            if (string2 == null) {
                throw new IllegalArgumentException("Result key can't be null");
            }
            this.mResultKey = string2;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            return this;
        }

        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormInput, this.mExtras);
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public Builder setAllowFreeFormInput(boolean bl2) {
            this.mAllowFreeFormInput = bl2;
            return this;
        }

        public Builder setChoices(CharSequence[] arrcharSequence) {
            this.mChoices = arrcharSequence;
            return this;
        }

        public Builder setLabel(CharSequence charSequence) {
            this.mLabel = charSequence;
            return this;
        }
    }

    static interface Impl {
        public void addResultsToIntent(RemoteInput[] var1, Intent var2, Bundle var3);

        public Bundle getResultsFromIntent(Intent var1);
    }

    static class ImplApi20
    implements Impl {
        ImplApi20() {
        }

        @Override
        public void addResultsToIntent(RemoteInput[] arrremoteInput, Intent intent, Bundle bundle) {
            RemoteInputCompatApi20.addResultsToIntent(arrremoteInput, intent, bundle);
        }

        @Override
        public Bundle getResultsFromIntent(Intent intent) {
            return RemoteInputCompatApi20.getResultsFromIntent(intent);
        }
    }

    static class ImplBase
    implements Impl {
        ImplBase() {
        }

        @Override
        public void addResultsToIntent(RemoteInput[] arrremoteInput, Intent intent, Bundle bundle) {
            Log.w((String)"RemoteInput", (String)"RemoteInput is only supported from API Level 16");
        }

        @Override
        public Bundle getResultsFromIntent(Intent intent) {
            Log.w((String)"RemoteInput", (String)"RemoteInput is only supported from API Level 16");
            return null;
        }
    }

    static class ImplJellybean
    implements Impl {
        ImplJellybean() {
        }

        @Override
        public void addResultsToIntent(RemoteInput[] arrremoteInput, Intent intent, Bundle bundle) {
            RemoteInputCompatJellybean.addResultsToIntent(arrremoteInput, intent, bundle);
        }

        @Override
        public Bundle getResultsFromIntent(Intent intent) {
            return RemoteInputCompatJellybean.getResultsFromIntent(intent);
        }
    }

}

