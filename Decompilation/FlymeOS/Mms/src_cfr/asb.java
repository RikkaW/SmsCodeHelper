/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.DialogInterface;
import com.ted.android.message.BubbleUtils;

public class asb
implements DialogInterface.OnClickListener {
    private final /* synthetic */ String a;
    private final /* synthetic */ Context b;

    public asb(String string2, Context context) {
        this.a = string2;
        this.b = context;
    }

    public void onClick(DialogInterface object, int n2) {
        object = String.format((String)"http://search.teddymobile.cn/v1/apk.api?package=%s", (Object[])new Object[]{this.a});
        BubbleUtils.openUrl(this.b, (String)object);
    }
}

