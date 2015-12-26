/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import com.ted.android.message.BubbleUtils;

public class asc
implements DialogInterface.OnClickListener {
    private final /* synthetic */ String[] a;
    private final /* synthetic */ Activity b;

    public asc(String[] arrstring, Activity activity) {
        this.a = arrstring;
        this.b = activity;
    }

    public void onClick(DialogInterface object, int n2) {
        object = this.a[n2];
        BubbleUtils.setClipboard((Context)this.b, (String)object);
        BubbleUtils.access$0((Context)this.b, "\u5df2\u590d\u5236");
    }
}

