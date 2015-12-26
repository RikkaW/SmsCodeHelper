/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.view.animation.Interpolator
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.WeakHashMap
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompatICS;
import android.support.v4.view.ViewPropertyAnimatorCompatJB;
import android.support.v4.view.ViewPropertyAnimatorCompatJellybeanMr2;
import android.support.v4.view.ViewPropertyAnimatorCompatKK;
import android.support.v4.view.ViewPropertyAnimatorCompatLollipop;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ViewPropertyAnimatorCompat {
    static final ViewPropertyAnimatorCompatImpl IMPL;
    static final int LISTENER_TAG_ID = 2113929216;
    private static final String TAG = "ViewAnimatorCompat";
    private Runnable mEndAction = null;
    private int mOldLayerType = -1;
    private Runnable mStartAction = null;
    private WeakReference<View> mView;

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 21 ? new LollipopViewPropertyAnimatorCompatImpl() : (n2 >= 19 ? new KitKatViewPropertyAnimatorCompatImpl() : (n2 >= 18 ? new JBMr2ViewPropertyAnimatorCompatImpl() : (n2 >= 16 ? new JBViewPropertyAnimatorCompatImpl() : (n2 >= 14 ? new ICSViewPropertyAnimatorCompatImpl() : new BaseViewPropertyAnimatorCompatImpl()))));
    }

    ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference<View>(view);
    }

    public ViewPropertyAnimatorCompat alpha(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.alpha(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.alphaBy(this, view, f2);
        }
        return this;
    }

    public void cancel() {
        View view = this.mView.get();
        if (view != null) {
            IMPL.cancel(this, view);
        }
    }

    public long getDuration() {
        View view = this.mView.get();
        if (view != null) {
            return IMPL.getDuration(this, view);
        }
        return 0;
    }

    public Interpolator getInterpolator() {
        View view = this.mView.get();
        if (view != null) {
            return IMPL.getInterpolator(this, view);
        }
        return null;
    }

    public long getStartDelay() {
        View view = this.mView.get();
        if (view != null) {
            return IMPL.getStartDelay(this, view);
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat rotation(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.rotation(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.rotationBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.rotationX(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.rotationXBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.rotationY(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.rotationYBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.scaleX(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.scaleXBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.scaleY(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.scaleYBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long l2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.setDuration(this, view, l2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.setInterpolator(this, view, interpolator2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.setListener(this, view, viewPropertyAnimatorListener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long l2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.setStartDelay(this, view, l2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.setUpdateListener(this, view, viewPropertyAnimatorUpdateListener);
        }
        return this;
    }

    public void start() {
        View view = this.mView.get();
        if (view != null) {
            IMPL.start(this, view);
        }
    }

    public ViewPropertyAnimatorCompat translationX(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.translationX(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.translationXBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.translationY(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.translationYBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.translationZ(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.translationZBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.withEndAction(this, view, runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer() {
        View view = this.mView.get();
        if (view != null) {
            IMPL.withLayer(this, view);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.withStartAction(this, view, runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat x(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.x(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.xBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat y(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.y(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.yBy(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat z(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.z(this, view, f2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f2) {
        View view = this.mView.get();
        if (view != null) {
            IMPL.zBy(this, view, f2);
        }
        return this;
    }

    static class BaseViewPropertyAnimatorCompatImpl
    implements ViewPropertyAnimatorCompatImpl {
        WeakHashMap<View, Runnable> mStarterMap = null;

        BaseViewPropertyAnimatorCompatImpl() {
        }

        /*
         * Enabled aggressive block sorting
         */
        private void postStartMessage(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            Runnable runnable = this.mStarterMap != null ? (Runnable)this.mStarterMap.get((Object)view) : null;
            Runnable runnable2 = runnable;
            if (runnable == null) {
                runnable2 = new Starter(viewPropertyAnimatorCompat, view);
                if (this.mStarterMap == null) {
                    this.mStarterMap = new WeakHashMap();
                }
                this.mStarterMap.put((Object)view, (Object)runnable2);
            }
            view.removeCallbacks(runnable2);
            view.post(runnable2);
        }

        private void removeStartMessage(View view) {
            Runnable runnable;
            if (this.mStarterMap != null && (runnable = (Runnable)this.mStarterMap.get((Object)view)) != null) {
                view.removeCallbacks(runnable);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void startAnimation(ViewPropertyAnimatorCompat object, View view) {
            Object object2 = view.getTag(2113929216);
            object2 = object2 instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener)object2 : null;
            Runnable runnable = ((ViewPropertyAnimatorCompat)object).mStartAction;
            object = ((ViewPropertyAnimatorCompat)object).mEndAction;
            if (runnable != null) {
                runnable.run();
            }
            if (object2 != null) {
                object2.onAnimationStart(view);
                object2.onAnimationEnd(view);
            }
            if (object != null) {
                object.run();
            }
            if (this.mStarterMap != null) {
                this.mStarterMap.remove((Object)view);
            }
        }

        @Override
        public void alpha(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void alphaBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void cancel(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public long getDuration(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return 0;
        }

        @Override
        public Interpolator getInterpolator(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return null;
        }

        @Override
        public long getStartDelay(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return 0;
        }

        @Override
        public void rotation(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void rotationBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void rotationX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void rotationXBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void rotationY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void rotationYBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void scaleX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void scaleXBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void scaleY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void scaleYBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void setDuration(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long l2) {
        }

        @Override
        public void setInterpolator(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Interpolator interpolator2) {
        }

        @Override
        public void setListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
        }

        @Override
        public void setStartDelay(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long l2) {
        }

        @Override
        public void setUpdateListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        }

        @Override
        public void start(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            this.removeStartMessage(view);
            this.startAnimation(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void translationX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void translationXBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void translationY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void translationYBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void translationZ(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
        }

        @Override
        public void translationZBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
        }

        @Override
        public void withEndAction(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Runnable runnable) {
            viewPropertyAnimatorCompat.mEndAction = runnable;
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void withLayer(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
        }

        @Override
        public void withStartAction(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Runnable runnable) {
            viewPropertyAnimatorCompat.mStartAction = runnable;
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void x(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void xBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void y(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void yBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }

        @Override
        public void z(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
        }

        @Override
        public void zBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
        }

        class Starter
        implements Runnable {
            WeakReference<View> mViewRef;
            ViewPropertyAnimatorCompat mVpa;

            private Starter(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
                this.mViewRef = new WeakReference<View>(view);
                this.mVpa = viewPropertyAnimatorCompat;
            }

            @Override
            public void run() {
                View view = this.mViewRef.get();
                if (view != null) {
                    BaseViewPropertyAnimatorCompatImpl.this.startAnimation(this.mVpa, view);
                }
            }
        }

    }

    static class ICSViewPropertyAnimatorCompatImpl
    extends BaseViewPropertyAnimatorCompatImpl {
        WeakHashMap<View, Integer> mLayerMap = null;

        ICSViewPropertyAnimatorCompatImpl() {
        }

        @Override
        public void alpha(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.alpha(view, f2);
        }

        @Override
        public void alphaBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.alphaBy(view, f2);
        }

        @Override
        public void cancel(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            ViewPropertyAnimatorCompatICS.cancel(view);
        }

        @Override
        public long getDuration(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return ViewPropertyAnimatorCompatICS.getDuration(view);
        }

        @Override
        public long getStartDelay(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return ViewPropertyAnimatorCompatICS.getStartDelay(view);
        }

        @Override
        public void rotation(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.rotation(view, f2);
        }

        @Override
        public void rotationBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.rotationBy(view, f2);
        }

        @Override
        public void rotationX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.rotationX(view, f2);
        }

        @Override
        public void rotationXBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.rotationXBy(view, f2);
        }

        @Override
        public void rotationY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.rotationY(view, f2);
        }

        @Override
        public void rotationYBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.rotationYBy(view, f2);
        }

        @Override
        public void scaleX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.scaleX(view, f2);
        }

        @Override
        public void scaleXBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.scaleXBy(view, f2);
        }

        @Override
        public void scaleY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.scaleY(view, f2);
        }

        @Override
        public void scaleYBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.scaleYBy(view, f2);
        }

        @Override
        public void setDuration(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long l2) {
            ViewPropertyAnimatorCompatICS.setDuration(view, l2);
        }

        @Override
        public void setInterpolator(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Interpolator interpolator2) {
            ViewPropertyAnimatorCompatICS.setInterpolator(view, interpolator2);
        }

        @Override
        public void setListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }

        @Override
        public void setStartDelay(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long l2) {
            ViewPropertyAnimatorCompatICS.setStartDelay(view, l2);
        }

        @Override
        public void start(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            ViewPropertyAnimatorCompatICS.start(view);
        }

        @Override
        public void translationX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.translationX(view, f2);
        }

        @Override
        public void translationXBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.translationXBy(view, f2);
        }

        @Override
        public void translationY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.translationY(view, f2);
        }

        @Override
        public void translationYBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.translationYBy(view, f2);
        }

        @Override
        public void withEndAction(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Runnable runnable) {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
            viewPropertyAnimatorCompat.mEndAction = runnable;
        }

        @Override
        public void withLayer(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            viewPropertyAnimatorCompat.mOldLayerType = ViewCompat.getLayerType(view);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }

        @Override
        public void withStartAction(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Runnable runnable) {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
            viewPropertyAnimatorCompat.mStartAction = runnable;
        }

        @Override
        public void x(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.x(view, f2);
        }

        @Override
        public void xBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.xBy(view, f2);
        }

        @Override
        public void y(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.y(view, f2);
        }

        @Override
        public void yBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatICS.yBy(view, f2);
        }

        static class MyVpaListener
        implements ViewPropertyAnimatorListener {
            ViewPropertyAnimatorCompat mVpa;

            MyVpaListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
                this.mVpa = viewPropertyAnimatorCompat;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public void onAnimationCancel(View view) {
                Object object = view.getTag(2113929216);
                if (!(object instanceof ViewPropertyAnimatorListener)) return;
                if ((object = (ViewPropertyAnimatorListener)object) == null) return;
                object.onAnimationCancel(view);
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public void onAnimationEnd(View view) {
                Object object;
                if (this.mVpa.mOldLayerType >= 0) {
                    ViewCompat.setLayerType(view, this.mVpa.mOldLayerType, null);
                    this.mVpa.mOldLayerType = -1;
                }
                if (this.mVpa.mEndAction != null) {
                    this.mVpa.mEndAction.run();
                }
                if (!((object = view.getTag(2113929216)) instanceof ViewPropertyAnimatorListener)) return;
                if ((object = (ViewPropertyAnimatorListener)object) == null) return;
                object.onAnimationEnd(view);
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public void onAnimationStart(View view) {
                Object object;
                if (this.mVpa.mOldLayerType >= 0) {
                    ViewCompat.setLayerType(view, 2, null);
                }
                if (this.mVpa.mStartAction != null) {
                    this.mVpa.mStartAction.run();
                }
                if (!((object = view.getTag(2113929216)) instanceof ViewPropertyAnimatorListener)) return;
                if ((object = (ViewPropertyAnimatorListener)object) == null) return;
                object.onAnimationStart(view);
            }
        }

    }

    static class JBMr2ViewPropertyAnimatorCompatImpl
    extends JBViewPropertyAnimatorCompatImpl {
        JBMr2ViewPropertyAnimatorCompatImpl() {
        }

        @Override
        public Interpolator getInterpolator(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return ViewPropertyAnimatorCompatJellybeanMr2.getInterpolator(view);
        }
    }

    static class JBViewPropertyAnimatorCompatImpl
    extends ICSViewPropertyAnimatorCompatImpl {
        JBViewPropertyAnimatorCompatImpl() {
        }

        @Override
        public void setListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            ViewPropertyAnimatorCompatJB.setListener(view, viewPropertyAnimatorListener);
        }

        @Override
        public void withEndAction(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Runnable runnable) {
            ViewPropertyAnimatorCompatJB.withEndAction(view, runnable);
        }

        @Override
        public void withLayer(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            ViewPropertyAnimatorCompatJB.withLayer(view);
        }

        @Override
        public void withStartAction(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Runnable runnable) {
            ViewPropertyAnimatorCompatJB.withStartAction(view, runnable);
        }
    }

    static class KitKatViewPropertyAnimatorCompatImpl
    extends JBMr2ViewPropertyAnimatorCompatImpl {
        KitKatViewPropertyAnimatorCompatImpl() {
        }

        @Override
        public void setUpdateListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
            ViewPropertyAnimatorCompatKK.setUpdateListener(view, viewPropertyAnimatorUpdateListener);
        }
    }

    static class LollipopViewPropertyAnimatorCompatImpl
    extends KitKatViewPropertyAnimatorCompatImpl {
        LollipopViewPropertyAnimatorCompatImpl() {
        }

        @Override
        public void translationZ(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatLollipop.translationZ(view, f2);
        }

        @Override
        public void translationZBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatLollipop.translationZBy(view, f2);
        }

        @Override
        public void z(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatLollipop.z(view, f2);
        }

        @Override
        public void zBy(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f2) {
            ViewPropertyAnimatorCompatLollipop.zBy(view, f2);
        }
    }

    static interface ViewPropertyAnimatorCompatImpl {
        public void alpha(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void alphaBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void cancel(ViewPropertyAnimatorCompat var1, View var2);

        public long getDuration(ViewPropertyAnimatorCompat var1, View var2);

        public Interpolator getInterpolator(ViewPropertyAnimatorCompat var1, View var2);

        public long getStartDelay(ViewPropertyAnimatorCompat var1, View var2);

        public void rotation(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void rotationBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void rotationX(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void rotationXBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void rotationY(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void rotationYBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void scaleX(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void scaleXBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void scaleY(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void scaleYBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void setDuration(ViewPropertyAnimatorCompat var1, View var2, long var3);

        public void setInterpolator(ViewPropertyAnimatorCompat var1, View var2, Interpolator var3);

        public void setListener(ViewPropertyAnimatorCompat var1, View var2, ViewPropertyAnimatorListener var3);

        public void setStartDelay(ViewPropertyAnimatorCompat var1, View var2, long var3);

        public void setUpdateListener(ViewPropertyAnimatorCompat var1, View var2, ViewPropertyAnimatorUpdateListener var3);

        public void start(ViewPropertyAnimatorCompat var1, View var2);

        public void translationX(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void translationXBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void translationY(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void translationYBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void translationZ(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void translationZBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void withEndAction(ViewPropertyAnimatorCompat var1, View var2, Runnable var3);

        public void withLayer(ViewPropertyAnimatorCompat var1, View var2);

        public void withStartAction(ViewPropertyAnimatorCompat var1, View var2, Runnable var3);

        public void x(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void xBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void y(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void yBy(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void z(ViewPropertyAnimatorCompat var1, View var2, float var3);

        public void zBy(ViewPropertyAnimatorCompat var1, View var2, float var3);
    }

}

