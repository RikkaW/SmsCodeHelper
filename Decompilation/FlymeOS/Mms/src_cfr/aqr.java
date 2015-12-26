/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.view.View
 *  android.view.Window
 *  android.widget.TextView
 *  java.lang.Object
 */
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class aqr {
    private final Context a;
    private final Window b;
    private final DialogInterface c;
    private TextView d;
    private CharSequence e;

    public aqr(Context context, DialogInterface dialogInterface, Window window) {
        this.a = context;
        this.b = window;
        this.c = dialogInterface;
    }

    static /* synthetic */ TextView a(aqr aqr2) {
        return aqr2.d;
    }

    public void a() {
        this.d = (TextView)this.b.findViewById(16908299);
        if (this.e != null) {
            this.d.post((Runnable)new aqs(this));
        }
    }

    public void a(CharSequence charSequence) {
        this.e = charSequence;
    }
}

