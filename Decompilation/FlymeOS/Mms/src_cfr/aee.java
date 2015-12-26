/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.ObjectAnimator
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.view.MotionEvent
 *  android.view.VelocityTracker
 *  android.view.animation.LinearInterpolator
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.animation.LinearInterpolator;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.view.MessagePopupGroupView;

public class aee {
    public static float a;
    public static float b;
    private static LinearInterpolator c;
    private float d = 100.0f;
    private int e = 200;
    private int f = 400;
    private int g = 2000;
    private float h = 0.0f;
    private float i;
    private a j;
    private int k;
    private VelocityTracker l;
    private float m;
    private volatile boolean n;
    private MessagePopupGroupView.a o;
    private boolean p;
    private float q;

    static {
        c = new LinearInterpolator();
        a = 0.35f;
        b = 0.0f;
    }

    public aee(int n2, a a2, float f2, float f3) {
        this.j = a2;
        this.k = n2;
        this.l = VelocityTracker.obtain();
        this.q = f2;
        this.i = f3 / this.q;
    }

    static /* synthetic */ float a(aee aee2, MessagePopupGroupView.a a2) {
        return aee2.c(a2);
    }

    private float a(VelocityTracker velocityTracker) {
        if (this.k == 0) {
            return velocityTracker.getXVelocity();
        }
        return velocityTracker.getYVelocity();
    }

    private float a(MessagePopupGroupView.a a2) {
        if (this.k == 0) {
            return a2.c();
        }
        return a2.d();
    }

    static /* synthetic */ a a(aee aee2) {
        return aee2.j;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private ObjectAnimator a(MessagePopupGroupView.a a2, float f2) {
        String string2;
        if (this.k == 0) {
            string2 = "translationX";
            do {
                return ObjectAnimator.ofFloat((Object)a2, (String)string2, (float[])new float[]{f2});
                break;
            } while (true);
        }
        string2 = "translationY";
        return ObjectAnimator.ofFloat((Object)a2, (String)string2, (float[])new float[]{f2});
    }

    private float b(VelocityTracker velocityTracker) {
        if (this.k == 0) {
            return velocityTracker.getYVelocity();
        }
        return velocityTracker.getXVelocity();
    }

    private float b(MessagePopupGroupView.a a2) {
        if (this.k == 0) {
            return a2.a();
        }
        return a2.b();
    }

    private void b(MessagePopupGroupView.a a2, float f2) {
        if (this.k == 0) {
            a2.a(f2);
            return;
        }
        a2.b(f2);
    }

    private float c(MotionEvent motionEvent) {
        if (this.k == 0) {
            return motionEvent.getRawX();
        }
        return motionEvent.getRawY();
    }

    /*
     * Enabled aggressive block sorting
     */
    private float c(MessagePopupGroupView.a a2) {
        float f2 = 1.0f;
        float f3 = this.b(a2);
        float f4 = 0.5f * f3;
        float f5 = this.a(a2);
        if (f5 >= b * f3) {
            f2 = 1.0f - (f5 - f3 * b) / f4;
            return Math.max((float)this.h, (float)f2);
        }
        if (f5 >= (1.0f - b) * f3) return Math.max((float)this.h, (float)f2);
        f2 = 1.0f + (f3 * b + f5) / f4;
        return Math.max((float)this.h, (float)f2);
    }

    public void a(float f2) {
        this.q = f2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean a(MotionEvent var1_1) {
        switch (var1_1.getAction()) {
            case 0: {
                this.n = false;
                this.o = this.j.getAnimatorObject();
                this.l.clear();
                if (this.o == null) return this.n;
                this.p = this.j.b();
                this.l.addMovement(var1_1);
                this.m = this.c(var1_1);
                MessagePopupService.a(false, 2, "MessagePopupService.SwipeHelper", "onInterceptTouchEvent()->Action_Dwon, mInitialTouchPos = " + this.m + ", mPagingTouchSlop = " + this.i);
                ** break;
            }
            case 2: {
                if (this.o == null) return this.n;
                this.l.addMovement(var1_1);
                if (Math.abs((float)(this.c(var1_1) - this.m)) <= this.i) return this.n;
                this.j.a(this.o);
                this.n = true;
                this.m = this.c(var1_1) - this.a(this.o);
                MessagePopupService.a(false, 2, "MessagePopupService.SwipeHelper", "onInterceptTouchEvent()->Action_Move_mDragging, mInitialTouchPos = " + this.m + ", mPagingTouchSlop = " + this.i);
            }
lbl20: // 3 sources:
            default: {
                return this.n;
            }
            case 1: 
            case 3: 
        }
        this.n = false;
        this.o = null;
        return this.n;
    }

    public void b(float f2) {
        this.i = f2 / this.q;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean b(MotionEvent var1_1) {
        var9_2 = false;
        if (!this.n) {
            return false;
        }
        this.l.addMovement(var1_1);
        switch (var1_1.getAction()) {
            case 2: 
            case 4: {
                if (this.o == null) return true;
                var2_3 = this.c(var1_1) - this.m;
                if (!this.j.b()) {
                    var4_5 = this.b(this.o);
                    var3_7 = 1024.0f;
                    var2_3 = Math.abs((float)var2_3) >= var4_5 ? (var2_3 > 0.0f ? var3_7 : - 1024.0f) : 1024.0f * (float)Math.sin((double)((double)(var2_3 / var4_5) * 1.5707963267948966));
                }
                this.b(this.o, var2_3);
                if (this.p == false) return true;
                this.o.c(this.c(this.o));
            }
            default: {
                return true;
            }
            case 1: 
            case 3: 
        }
        if (this.o == null) return true;
        var2_4 = this.g;
        var3_8 = this.q;
        this.l.computeCurrentVelocity(1000, var2_4 * var3_8);
        var3_8 = this.d;
        var4_6 = this.q;
        var2_4 = this.a(this.l);
        var5_9 = this.b(this.l);
        var6_10 = Math.abs((float)this.a(this.o)) > aee.a * this.b(this.o);
        if (Math.abs((float)var2_4) <= var4_6 * var3_8 || Math.abs((float)var2_4) <= Math.abs((float)var5_9)) ** GOTO lbl-1000
        var7_11 = var2_4 > 0.0f;
        var8_12 = this.a(this.o) > 0.0f;
        if (var7_11 == var8_12) {
            var7_11 = true;
        } else lbl-1000: // 2 sources:
        {
            var7_11 = false;
        }
        var8_12 = var9_2;
        if (!this.j.b()) ** GOTO lbl41
        if (var7_11) ** GOTO lbl-1000
        var8_12 = var9_2;
        if (var6_10) lbl-1000: // 2 sources:
        {
            var8_12 = true;
        }
lbl41: // 4 sources:
        if (!var8_12) {
            this.j.b(this.o);
            this.d(var2_4);
            return true;
        }
        if (!var7_11) {
            var2_4 = 0.0f;
        }
        this.c(var2_4);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c(float f2) {
        MessagePopupGroupView.a a2 = this.j.getAnimatorObject();
        boolean bl2 = this.j.b();
        float f3 = f2 < 0.0f || f2 == 0.0f && this.a(a2) < 0.0f || f2 == 0.0f && this.a(a2) == 0.0f && this.k == 1 ? - this.b(a2) : this.b(a2);
        int n2 = this.f;
        n2 = f2 != 0.0f ? Math.min((int)n2, (int)((int)(Math.abs((float)(f3 - this.a(a2))) * 1000.0f / Math.abs((float)f2)))) : this.e;
        ObjectAnimator objectAnimator = this.a(a2, f3);
        objectAnimator.setInterpolator((TimeInterpolator)c);
        objectAnimator.setDuration((long)n2);
        objectAnimator.addListener((Animator.AnimatorListener)new aef(this));
        objectAnimator.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new aeg(this, bl2, a2));
        objectAnimator.start();
    }

    public void d(float f2) {
        MessagePopupGroupView.a a2 = this.j.getAnimatorObject();
        boolean bl2 = this.j.b();
        ObjectAnimator objectAnimator = this.a(a2, 0.0f);
        objectAnimator.setInterpolator((TimeInterpolator)c);
        objectAnimator.setDuration((long)150);
        objectAnimator.addListener((Animator.AnimatorListener)new aeh(this, a2));
        objectAnimator.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new aei(this, bl2, a2));
        objectAnimator.start();
    }

    public static interface a {
        public void a(MessagePopupGroupView.a var1);

        public void b(MessagePopupGroupView.a var1);

        public boolean b();

        public void c();

        public MessagePopupGroupView.a getAnimatorObject();
    }

}

