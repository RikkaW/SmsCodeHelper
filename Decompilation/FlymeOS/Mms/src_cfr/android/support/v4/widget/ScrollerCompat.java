/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.animation.Interpolator
 *  android.widget.Scroller
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.ScrollerCompatGingerbread;
import android.support.v4.widget.ScrollerCompatIcs;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ScrollerCompat {
    static final int CHASE_FRAME_TIME = 16;
    private static final String TAG = "ScrollerCompat";
    ScrollerCompatImpl mImpl;
    Object mScroller;

    /*
     * Enabled aggressive block sorting
     */
    private ScrollerCompat(int n2, Context context, Interpolator interpolator2) {
        this.mImpl = n2 >= 14 ? new ScrollerCompatImplIcs() : (n2 >= 9 ? new ScrollerCompatImplGingerbread() : new ScrollerCompatImplBase());
        this.mScroller = this.mImpl.createScroller(context, interpolator2);
    }

    ScrollerCompat(Context context, Interpolator interpolator2) {
        this(Build.VERSION.SDK_INT, context, interpolator2);
    }

    public static ScrollerCompat create(Context context) {
        return ScrollerCompat.create(context, null);
    }

    public static ScrollerCompat create(Context context, Interpolator interpolator2) {
        return new ScrollerCompat(context, interpolator2);
    }

    public void abortAnimation() {
        this.mImpl.abortAnimation(this.mScroller);
    }

    public boolean computeScrollOffset() {
        return this.mImpl.computeScrollOffset(this.mScroller);
    }

    public void fling(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        this.mImpl.fling(this.mScroller, n2, n3, n4, n5, n6, n7, n8, n9);
    }

    public void fling(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
        this.mImpl.fling(this.mScroller, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);
    }

    public float getCurrVelocity() {
        return this.mImpl.getCurrVelocity(this.mScroller);
    }

    public int getCurrX() {
        return this.mImpl.getCurrX(this.mScroller);
    }

    public int getCurrY() {
        return this.mImpl.getCurrY(this.mScroller);
    }

    public int getFinalX() {
        return this.mImpl.getFinalX(this.mScroller);
    }

    public int getFinalY() {
        return this.mImpl.getFinalY(this.mScroller);
    }

    public boolean isFinished() {
        return this.mImpl.isFinished(this.mScroller);
    }

    public boolean isOverScrolled() {
        return this.mImpl.isOverScrolled(this.mScroller);
    }

    public void notifyHorizontalEdgeReached(int n2, int n3, int n4) {
        this.mImpl.notifyHorizontalEdgeReached(this.mScroller, n2, n3, n4);
    }

    public void notifyVerticalEdgeReached(int n2, int n3, int n4) {
        this.mImpl.notifyVerticalEdgeReached(this.mScroller, n2, n3, n4);
    }

    public void startScroll(int n2, int n3, int n4, int n5) {
        this.mImpl.startScroll(this.mScroller, n2, n3, n4, n5);
    }

    public void startScroll(int n2, int n3, int n4, int n5, int n6) {
        this.mImpl.startScroll(this.mScroller, n2, n3, n4, n5, n6);
    }

    static interface ScrollerCompatImpl {
        public void abortAnimation(Object var1);

        public boolean computeScrollOffset(Object var1);

        public Object createScroller(Context var1, Interpolator var2);

        public void fling(Object var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9);

        public void fling(Object var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11);

        public float getCurrVelocity(Object var1);

        public int getCurrX(Object var1);

        public int getCurrY(Object var1);

        public int getFinalX(Object var1);

        public int getFinalY(Object var1);

        public boolean isFinished(Object var1);

        public boolean isOverScrolled(Object var1);

        public void notifyHorizontalEdgeReached(Object var1, int var2, int var3, int var4);

        public void notifyVerticalEdgeReached(Object var1, int var2, int var3, int var4);

        public void startScroll(Object var1, int var2, int var3, int var4, int var5);

        public void startScroll(Object var1, int var2, int var3, int var4, int var5, int var6);
    }

    static class ScrollerCompatImplBase
    implements ScrollerCompatImpl {
        ScrollerCompatImplBase() {
        }

        @Override
        public void abortAnimation(Object object) {
            ((Scroller)object).abortAnimation();
        }

        @Override
        public boolean computeScrollOffset(Object object) {
            return ((Scroller)object).computeScrollOffset();
        }

        @Override
        public Object createScroller(Context context, Interpolator interpolator2) {
            if (interpolator2 != null) {
                return new Scroller(context, interpolator2);
            }
            return new Scroller(context);
        }

        @Override
        public void fling(Object object, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
            ((Scroller)object).fling(n2, n3, n4, n5, n6, n7, n8, n9);
        }

        @Override
        public void fling(Object object, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
            ((Scroller)object).fling(n2, n3, n4, n5, n6, n7, n8, n9);
        }

        @Override
        public float getCurrVelocity(Object object) {
            return 0.0f;
        }

        @Override
        public int getCurrX(Object object) {
            return ((Scroller)object).getCurrX();
        }

        @Override
        public int getCurrY(Object object) {
            return ((Scroller)object).getCurrY();
        }

        @Override
        public int getFinalX(Object object) {
            return ((Scroller)object).getFinalX();
        }

        @Override
        public int getFinalY(Object object) {
            return ((Scroller)object).getFinalY();
        }

        @Override
        public boolean isFinished(Object object) {
            return ((Scroller)object).isFinished();
        }

        @Override
        public boolean isOverScrolled(Object object) {
            return false;
        }

        @Override
        public void notifyHorizontalEdgeReached(Object object, int n2, int n3, int n4) {
        }

        @Override
        public void notifyVerticalEdgeReached(Object object, int n2, int n3, int n4) {
        }

        @Override
        public void startScroll(Object object, int n2, int n3, int n4, int n5) {
            ((Scroller)object).startScroll(n2, n3, n4, n5);
        }

        @Override
        public void startScroll(Object object, int n2, int n3, int n4, int n5, int n6) {
            ((Scroller)object).startScroll(n2, n3, n4, n5, n6);
        }
    }

    static class ScrollerCompatImplGingerbread
    implements ScrollerCompatImpl {
        ScrollerCompatImplGingerbread() {
        }

        @Override
        public void abortAnimation(Object object) {
            ScrollerCompatGingerbread.abortAnimation(object);
        }

        @Override
        public boolean computeScrollOffset(Object object) {
            return ScrollerCompatGingerbread.computeScrollOffset(object);
        }

        @Override
        public Object createScroller(Context context, Interpolator interpolator2) {
            return ScrollerCompatGingerbread.createScroller(context, interpolator2);
        }

        @Override
        public void fling(Object object, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
            ScrollerCompatGingerbread.fling(object, n2, n3, n4, n5, n6, n7, n8, n9);
        }

        @Override
        public void fling(Object object, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
            ScrollerCompatGingerbread.fling(object, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);
        }

        @Override
        public float getCurrVelocity(Object object) {
            return 0.0f;
        }

        @Override
        public int getCurrX(Object object) {
            return ScrollerCompatGingerbread.getCurrX(object);
        }

        @Override
        public int getCurrY(Object object) {
            return ScrollerCompatGingerbread.getCurrY(object);
        }

        @Override
        public int getFinalX(Object object) {
            return ScrollerCompatGingerbread.getFinalX(object);
        }

        @Override
        public int getFinalY(Object object) {
            return ScrollerCompatGingerbread.getFinalY(object);
        }

        @Override
        public boolean isFinished(Object object) {
            return ScrollerCompatGingerbread.isFinished(object);
        }

        @Override
        public boolean isOverScrolled(Object object) {
            return ScrollerCompatGingerbread.isOverScrolled(object);
        }

        @Override
        public void notifyHorizontalEdgeReached(Object object, int n2, int n3, int n4) {
            ScrollerCompatGingerbread.notifyHorizontalEdgeReached(object, n2, n3, n4);
        }

        @Override
        public void notifyVerticalEdgeReached(Object object, int n2, int n3, int n4) {
            ScrollerCompatGingerbread.notifyVerticalEdgeReached(object, n2, n3, n4);
        }

        @Override
        public void startScroll(Object object, int n2, int n3, int n4, int n5) {
            ScrollerCompatGingerbread.startScroll(object, n2, n3, n4, n5);
        }

        @Override
        public void startScroll(Object object, int n2, int n3, int n4, int n5, int n6) {
            ScrollerCompatGingerbread.startScroll(object, n2, n3, n4, n5, n6);
        }
    }

    static class ScrollerCompatImplIcs
    extends ScrollerCompatImplGingerbread {
        ScrollerCompatImplIcs() {
        }

        @Override
        public float getCurrVelocity(Object object) {
            return ScrollerCompatIcs.getCurrVelocity(object);
        }
    }

}

