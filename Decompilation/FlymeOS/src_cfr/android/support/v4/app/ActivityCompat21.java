/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.SharedElementCallback
 *  android.content.Context
 *  android.graphics.Matrix
 *  android.graphics.RectF
 *  android.os.Parcelable
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

class ActivityCompat21 {
    ActivityCompat21() {
    }

    private static SharedElementCallback createCallback(SharedElementCallback21 sharedElementCallback21) {
        SharedElementCallbackImpl sharedElementCallbackImpl = null;
        if (sharedElementCallback21 != null) {
            sharedElementCallbackImpl = new SharedElementCallbackImpl(sharedElementCallback21);
        }
        return sharedElementCallbackImpl;
    }

    public static void finishAfterTransition(Activity activity) {
        activity.finishAfterTransition();
    }

    public static void postponeEnterTransition(Activity activity) {
        activity.postponeEnterTransition();
    }

    public static void setEnterSharedElementCallback(Activity activity, SharedElementCallback21 sharedElementCallback21) {
        activity.setEnterSharedElementCallback(ActivityCompat21.createCallback(sharedElementCallback21));
    }

    public static void setExitSharedElementCallback(Activity activity, SharedElementCallback21 sharedElementCallback21) {
        activity.setExitSharedElementCallback(ActivityCompat21.createCallback(sharedElementCallback21));
    }

    public static void startPostponedEnterTransition(Activity activity) {
        activity.startPostponedEnterTransition();
    }

    public static abstract class SharedElementCallback21 {
        public abstract Parcelable onCaptureSharedElementSnapshot(View var1, Matrix var2, RectF var3);

        public abstract View onCreateSnapshotView(Context var1, Parcelable var2);

        public abstract void onMapSharedElements(List<String> var1, Map<String, View> var2);

        public abstract void onRejectSharedElements(List<View> var1);

        public abstract void onSharedElementEnd(List<String> var1, List<View> var2, List<View> var3);

        public abstract void onSharedElementStart(List<String> var1, List<View> var2, List<View> var3);
    }

    static class SharedElementCallbackImpl
    extends SharedElementCallback {
        private SharedElementCallback21 mCallback;

        public SharedElementCallbackImpl(SharedElementCallback21 sharedElementCallback21) {
            this.mCallback = sharedElementCallback21;
        }

        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
        }

        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.mCallback.onCreateSnapshotView(context, parcelable);
        }

        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            this.mCallback.onMapSharedElements(list, map);
        }

        public void onRejectSharedElements(List<View> list) {
            this.mCallback.onRejectSharedElements(list);
        }

        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            this.mCallback.onSharedElementEnd(list, list2, list3);
        }

        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            this.mCallback.onSharedElementStart(list, list2, list3);
        }
    }

}

