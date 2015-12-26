/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ActivityNotFoundException
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Handler
 *  android.text.Layout
 *  android.text.Selection
 *  android.text.Spannable
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.text.style.URLSpan
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.android.mms.MmsApp;

public class ael
extends LinkMovementMethod {
    private static ael a;
    private boolean b = true;
    private ClickableSpan c;
    private TextView d;
    private Handler e = new Handler();
    private Runnable f;

    public ael() {
        this.f = new aem(this);
    }

    public static MovementMethod a() {
        if (a == null) {
            a = new ael();
        }
        return a;
    }

    static /* synthetic */ ClickableSpan a(ael ael2) {
        return ael2.c;
    }

    public static void a(ClickableSpan clickableSpan, TextView textView) {
        if (clickableSpan instanceof URLSpan) {
            if (wd.c(MmsApp.c().getContentResolver())) {
                wd.m((Context)MmsApp.c());
                return;
            }
            String string2 = ((URLSpan)clickableSpan).getURL();
            Log.i((String)"MmsLinkMovementMethod", (String)("customeClick tempURL = " + string2));
            if (string2 != null && kn.c(string2) && wd.a("com.autonavi.minimap", textView.getContext())) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(524288);
                intent.setPackage("com.autonavi.minimap");
                intent.setData(wd.l(string2));
                try {
                    textView.getContext().startActivity(intent);
                    return;
                }
                catch (ActivityNotFoundException var2_3) {
                    clickableSpan.onClick((View)textView);
                    return;
                }
            }
        }
        clickableSpan.onClick((View)textView);
    }

    static /* synthetic */ TextView b(ael ael2) {
        return ael2.d;
    }

    private void b(ClickableSpan clickableSpan, TextView textView) {
        this.c = clickableSpan;
        this.d = textView;
        this.e.postDelayed(this.f, 200);
    }

    public static void c() {
        if (a != null) {
            a = null;
        }
    }

    public void b() {
        this.e.removeCallbacks(this.f);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int n2 = motionEvent.getAction();
        if (n2 != 1) {
            if (n2 != 0) return super.onTouchEvent(textView, spannable, motionEvent);
        }
        int n3 = (int)motionEvent.getX();
        int n4 = (int)motionEvent.getY();
        int n5 = textView.getTotalPaddingLeft();
        int n6 = textView.getTotalPaddingTop();
        int n7 = textView.getScrollX();
        int n8 = textView.getScrollY();
        ClickableSpan[] arrclickableSpan = textView.getLayout();
        n3 = arrclickableSpan.getOffsetForHorizontal(arrclickableSpan.getLineForVertical(n4 - n6 + n8), (float)(n3 - n5 + n7));
        arrclickableSpan = (ClickableSpan[])spannable.getSpans(n3, n3, (Class)ClickableSpan.class);
        if (arrclickableSpan.length == 0) {
            Selection.removeSelection((Spannable)spannable);
            return super.onTouchEvent(textView, spannable, motionEvent);
        }
        if (n2 == 1) {
            this.b(arrclickableSpan[0], textView);
            return true;
        }
        if (n2 != 0) return true;
        Selection.setSelection((Spannable)spannable, (int)spannable.getSpanStart((Object)arrclickableSpan[0]), (int)spannable.getSpanEnd((Object)arrclickableSpan[0]));
        return true;
    }
}

