/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnAttachStateChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.TranslateAnimation
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.PopupWindow
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.internal.widget.MzSlidePopupWindow$1;
import android.support.v7.internal.widget.MzSlidePopupWindow$DismissAnimator$1;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class MzSlidePopupWindow
extends AppCompatPopupWindow
implements View.OnAttachStateChangeListener {
    private static final int SLIDE_ANIMATION_DURATION = 200;
    private static final String TAG = "MzSlidePopupWindow";
    private boolean isDetachedFromWindow;
    private Drawable mBackground;
    private View mContentView;
    private Context mContext;
    private DismissAnimator mDismissAnimator;
    private int[] mDrawingLocation;
    private boolean mOnTop;
    private int[] mScreenLocation;

    public MzSlidePopupWindow(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        this.mDismissAnimator = new DismissAnimator(null);
        this.mDrawingLocation = new int[2];
        this.mScreenLocation = new int[2];
        this.mContext = context;
        this.mBackground = this.getBackground();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(Integer.MIN_VALUE));
        this.setSupperMzClippingEnabled(true);
    }

    private boolean findPosition(View view, int n2, int n3, int n4) {
        n2 = view.getHeight();
        view.getLocationInWindow(this.mDrawingLocation);
        view.getLocationOnScreen(this.mScreenLocation);
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        if (rect.bottom - this.mScreenLocation[1] - n2 - n3 < this.mScreenLocation[1] - n3 - rect.top) {
            return true;
        }
        return false;
    }

    private void preparePopup() {
        this.setWidth(this.mContext.getResources().getDisplayMetrics().widthPixels);
        this.setWindowLayoutMode(-1, -1);
        if (this.mOnTop) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)this.mContentView.getLayoutParams();
            layoutParams.gravity = 80;
            this.mContentView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        }
        this.mContentView.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new MzSlidePopupWindow$1(this));
    }

    public void dismiss() {
        this.dismiss(true);
    }

    public void dismiss(boolean bl2) {
        if (bl2) {
            if (this.isShowing() && !this.mDismissAnimator.isRunning()) {
                this.mDismissAnimator.start();
            }
            return;
        }
        super.dismiss();
    }

    public View getContentView() {
        return super.getContentView();
    }

    public void onViewAttachedToWindow(View view) {
        this.isDetachedFromWindow = false;
    }

    public void onViewDetachedFromWindow(View view) {
        this.isDetachedFromWindow = true;
    }

    public void setContentView(View object) {
        if (this.isShowing()) {
            return;
        }
        this.mContentView = object;
        if (this.mContext == null && this.mContentView != null) {
            this.mContext = this.mContentView.getContext();
        }
        object = null;
        if (this.mContentView != null) {
            this.mContentView.setBackgroundDrawable(this.mBackground);
            object = new SlidePopupViewContainer(this.mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            object.addView(this.mContentView, (ViewGroup.LayoutParams)layoutParams);
            object.addOnAttachStateChangeListener((View.OnAttachStateChangeListener)this);
        }
        super.setContentView((View)object);
    }

    public void setSupperMzClippingEnabled(boolean bl2) {
        try {
            Method method = PopupWindow.class.getDeclaredMethod("setMzClippingEnabled", new Class[]{Boolean.TYPE});
            method.setAccessible(true);
            method.invoke((Object)this, new Object[]{bl2});
            return;
        }
        catch (Exception var2_3) {
            return;
        }
    }

    @Override
    public void showAsDropDown(View view, int n2, int n3, int n4) {
        this.mOnTop = this.findPosition(view, n2, n3, n4);
        this.preparePopup();
        super.showAsDropDown(view, n2, n3, n4);
    }

    public void supperDismiss() {
        if (!this.isDetachedFromWindow) {
            super.dismiss();
        }
    }

    @Override
    public void update(View view, int n2, int n3, int n4, int n5) {
        super.update(view, n2, n3, -1, -1);
    }

    class DismissAnimator {
        private boolean isRunning;
        private Animation mAnimation;

        private DismissAnimator() {
        }

        /* synthetic */ DismissAnimator(MzSlidePopupWindow$1 mzSlidePopupWindow$1) {
            this();
        }

        static /* synthetic */ boolean access$302(DismissAnimator dismissAnimator, boolean bl2) {
            dismissAnimator.isRunning = bl2;
            return bl2;
        }

        void DismissAnimator() {
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void start() {
            int n2 = MzSlidePopupWindow.this.mOnTop ? MzSlidePopupWindow.this.mContentView.getMeasuredHeight() : - MzSlidePopupWindow.this.mContentView.getMeasuredHeight();
            this.mAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float)n2);
            this.mAnimation.setAnimationListener((Animation.AnimationListener)new MzSlidePopupWindow$DismissAnimator$1(this));
            this.mAnimation.setDuration(200);
            MzSlidePopupWindow.this.mContentView.startAnimation(this.mAnimation);
            this.isRunning = true;
        }
    }

    class SlidePopupViewContainer
    extends FrameLayout {
        private static final String TAG = "MzSlidePopupWindow.SlidePopupViewContainer";

        public SlidePopupViewContainer(Context context) {
            super(context);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 4) {
                MzSlidePopupWindow.this.dismiss(true);
                return true;
            }
            return super.onTouchEvent(motionEvent);
        }

        public void sendAccessibilityEvent(int n2) {
            if (MzSlidePopupWindow.this.mContentView != null) {
                MzSlidePopupWindow.this.mContentView.sendAccessibilityEvent(n2);
                return;
            }
            super.sendAccessibilityEvent(n2);
        }
    }

}

