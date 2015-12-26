/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONArray
 */
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import com.android.mms.view.MessageListItemSms;
import com.android.mms.view.MmsFoldableTextView;
import org.json.JSONArray;

public class adr
implements SdkCallBack {
    final /* synthetic */ vv a;
    final /* synthetic */ MessageListItemSms b;

    public adr(MessageListItemSms messageListItemSms, vv vv2) {
        this.b = messageListItemSms;
        this.a = vv2;
    }

    @Override
    public /* varargs */ void execute(Object ... arrobject) {
        if (arrobject == null || arrobject.length == 0) {
            MessageListItemSms.a(this.b);
            return;
        }
        Log.d((String)"duoqu_xiaoyuan", (String)("getSimpleBubbleData obj[0]: " + arrobject[0] + " obj[1]:" + arrobject[1] + " obj[2]:" + arrobject[2]));
        int n2 = (Integer)arrobject[0];
        if (arrobject.length > 2) {
            String string2 = (String)arrobject[2];
            String string3 = (String)this.b.z.getTag();
            if (StringUtils.isNull(string3) || StringUtils.isNull(string2) || !string3.equals((Object)string2)) {
                MessageListItemSms.a(this.b);
                return;
            }
        }
        switch (n2) {
            default: {
                return;
            }
            case -4: {
                this.b.z();
                return;
            }
            case -2: 
            case -1: {
                this.b.z();
                return;
            }
            case 0: {
                MessageListItemSms.a(this.b, new MessageListItemSms.a(this.b, this.a.e, (JSONArray)arrobject[1]));
                if (MessageListItemSms.b(this.b) != null && MessageListItemSms.b((MessageListItemSms)this.b).g > 0) {
                    this.b.a(MessageListItemSms.b(this.b));
                    return;
                }
                this.b.z();
                return;
            }
            case 1: 
        }
        ((Activity)this.b.U).runOnUiThread((Runnable)new ads(this, arrobject));
    }
}

