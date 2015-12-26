/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.AsyncQueryHandler
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Message
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.Gravity
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.view.MessagePopupGroupView;

public class aej
implements MessagePopupGroupView.a {
    public static WindowManager a = null;
    public static WindowManager.LayoutParams b = null;
    int c = 17;
    private a d = a.b;
    private final int e = (Integer)aau.a("android.view.MeizuLayoutParams", "TYPE_MMS_POP_WINDOW");
    private MessagePopupGroupView f;
    private volatile boolean g = false;
    private MessagePopupService h;
    private AsyncQueryHandler i;
    private MessagePopupService.b j;
    private boolean k = true;

    public aej(MessagePopupService messagePopupService, AsyncQueryHandler asyncQueryHandler) {
        this.h = messagePopupService;
        this.i = asyncQueryHandler;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(MessagePopupService.b b2, boolean bl2) {
        if (bl2 && this.j != null) {
            this.h.b(20000);
            if (this.k()) {
                this.h.a(this.j);
                this.a(false);
            } else {
                MessagingNotification.a((Context)this.h, this.j.b, false, false);
            }
        }
        this.j = b2;
        this.f.a(b2);
    }

    private void b(MessagePopupService.b b2) {
        this.a(b2, true);
    }

    private final void m() {
        WindowManager.LayoutParams layoutParams = aej.b = new WindowManager.LayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.flags = 520;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        layoutParams.type = this.e;
        layoutParams.setTitle((CharSequence)"MessagePopup");
        aau.a(layoutParams, 2048);
    }

    private final void n() {
        if (this.f != null) {
            return;
        }
        this.h.setTheme(2131624160);
        this.f = (MessagePopupGroupView)LayoutInflater.from((Context)this.h).inflate(2130968753, null);
        this.f.setGroupViewCallback(this.h);
        this.f.setAnimatorObjectCallback(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void o() {
        int n2;
        Context context = this.f.getContext().getApplicationContext();
        a = context == null ? (WindowManager)this.f.getContext().getSystemService("window") : (WindowManager)context.getSystemService("window");
        if (this.f.getParent() != null) {
            a.removeView((View)this.f);
        }
        a.addView((View)this.f, (ViewGroup.LayoutParams)b);
        this.a(this.j, false);
        context = this.f.getContext().getResources().getConfiguration();
        aej.b.gravity = n2 = Gravity.getAbsoluteGravity((int)this.c, (int)context.getLayoutDirection());
        MessagePopupService.a(false, 2, "PopupMessageService.MessagePopupWindowHelper", "prepareShowView-->wmParams.gravity = " + n2);
        if ((n2 & 7) == 7) {
            aej.b.horizontalWeight = 1.0f;
        }
        if ((n2 & 112) == 112) {
            aej.b.verticalWeight = 1.0f;
        }
        aej.b.type = this.e;
        aej.b.flags = 512;
        aej.b.x = this.h.getResources().getDimensionPixelSize(2131559378);
        aej.b.y = 0;
    }

    private void p() {
        this.f.d();
        aej.b.x = this.h.getResources().getDimensionPixelSize(2131559378);
        aej.b.y = 0;
        aej.b.gravity = this.c;
        a.updateViewLayout((View)this.f, (ViewGroup.LayoutParams)b);
    }

    private void q() {
        if (zv.a) {
            if (this.j != null) {
                Log.i((String)"MessagePopupWindowHelper", (String)("setIsCurrentSlotOnBind mMessageBean.mSlotId = " + this.j.o + ", mMessageBean.mImsi = " + this.j.p));
                this.j.q = zv.a(zv.c((Context)this.h), this.j.p);
            }
            return;
        }
        this.j.q = true;
    }

    @Override
    public int a() {
        return this.h.getResources().getDimensionPixelSize(2131559377);
    }

    @Override
    public void a(float f2) {
        if (this.f == null) {
            return;
        }
        aej.b.x = (int)f2;
        a.updateViewLayout((View)this.f, (ViewGroup.LayoutParams)b);
    }

    public void a(long l2, String string2) {
        if (this.j == null || l2 != this.j.b) {
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.j.m = true;
        }
        this.f.a(l2, string2);
    }

    @Override
    public void a(Configuration configuration) {
        this.p();
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void a(MessagePopupService.b b2) {
        this.h.a(true);
        boolean bl2 = this.j != null && this.j.a() != b2.a();
        this.j = b2;
        this.q();
        if (b == null) {
            this.m();
        } else if (this.g && this.f != null) {
            if (bl2) {
                this.p();
            }
            this.b(b2);
            return;
        }
        if (this.f == null) {
            this.n();
        }
        this.o();
        b2 = Message.obtain((Handler)this.i);
        b2.what = 6;
        b2.obj = this.j;
        b2.sendToTarget();
        MessagePopupService.a(true, 2, "PopupMessageService.MessagePopupWindowHelper", "show popup window-->");
        this.i.removeMessages(3);
        this.f.getContext().getResources().getConfiguration();
        aej.b.softInputMode = 19;
        a.updateViewLayout((View)this.f, (ViewGroup.LayoutParams)b);
        this.g = true;
        this.h.b();
    }

    public void a(boolean bl2) {
        if (this.f == null || this.f.getVisibility() == 8) {
            return;
        }
        this.f.setUserTouch(bl2);
    }

    public void a(boolean bl2, String string2) {
        if (this.g && this.f != null) {
            this.q();
            this.f.a(this.j, bl2, string2);
        }
    }

    public void a(boolean bl2, boolean bl3, String string2) {
        if (bl2) {
            this.h.a(this.j);
        }
        boolean bl4 = this.k();
        MessagePopupService.a(true, 2, "PopupMessageService.MessagePopupWindowHelper", "preparehideView(), markAsReadBeforeHidden = " + bl2 + ", autoHidden = " + bl3 + ", hasUserTouch = " + bl4 + ", method = " + string2 + ", " + (Object)((Object)this.d));
        this.i.removeMessages(3);
        if (this.f != null) {
            if (this.f.getParent() != null) {
                a.removeView((View)this.f);
            }
            this.f = null;
        }
        if (b != null) {
            aej.b.alpha = 1.0f;
        }
        this.d = a.b;
        this.g = false;
        this.h.a(this.j, bl3, bl4, bl2, string2);
        this.j = null;
    }

    @Override
    public int b() {
        return this.h.getResources().getDimensionPixelSize(2131559337);
    }

    @Override
    public void b(float f2) {
        if (this.f == null) {
            return;
        }
        aej.b.y = (int)f2;
        a.updateViewLayout((View)this.f, (ViewGroup.LayoutParams)b);
    }

    @Override
    public float c() {
        return aej.b.x;
    }

    @Override
    public void c(float f2) {
        if (this.f == null) {
            aej.b.alpha = 1.0f;
            return;
        }
        aej.b.alpha = f2;
        a.updateViewLayout((View)this.f, (ViewGroup.LayoutParams)b);
    }

    @Override
    public float d() {
        return aej.b.y;
    }

    @Override
    public void e() {
        if (this.i != null && this.g) {
            this.i.removeMessages(3);
        }
    }

    @Override
    public void f() {
        if (this.i != null && this.g) {
            this.h.b(20000);
        }
    }

    public final boolean g() {
        if (this.f != null && this.f.getVisibility() == 0 && this.g) {
            return true;
        }
        return false;
    }

    public boolean h() {
        if (this.f != null && this.d == a.b) {
            return true;
        }
        return false;
    }

    public final MessagePopupService.b i() {
        if (this.f == null || this.f.getVisibility() == 8) {
            this.j = null;
        }
        return this.j;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final String j() {
        if (!this.h() || this.f == null || this.f.getVisibility() == 8) {
            return null;
        }
        return this.f.getEditText();
    }

    public boolean k() {
        if (this.f == null || this.f.getVisibility() == 8) {
            return false;
        }
        return this.f.a();
    }

    public void l() {
        if (this.g && this.f != null) {
            this.q();
            this.f.a(this.j.j, this.j.q, this.j.e);
        }
    }

    public static enum a {
        a,
        b;
        

        private a() {
        }
    }

}

