/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.AsyncTask
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONArray
 */
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;

public class cm
extends dg {
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private int F = 0;
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private TextView w;
    private ImageView x;
    private ImageView y;
    private ImageView z;

    public cm(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, int n2, ViewGroup viewGroup, int n3) {
        super(activity, businessSmsMessage, xyCallBack, n2, viewGroup, n3);
    }

    static /* synthetic */ void a(cm cm2, String string2, String string3, String string4) {
        cm2.a(string2, string3, string4);
    }

    private void a(String string2, int n2) {
        String string3 = string2;
        if (StringUtils.isNull(string2)) {
            string3 = "#E6E6E6";
        }
        try {
            ViewManger.setViewBg((Context)this.f, this.e, string3, n2, 0);
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    private void a(String string2, String string3, String string4) {
        if (StringUtils.isNull(string2) || StringUtils.isNull(string4) || string4.equals((Object)string2)) {
            this.a.setVisibility(8);
            this.b.setVisibility(8);
            this.x.setVisibility(8);
            this.j.setTextSize(20.0f);
            string2 = (String)this.g.getValue("v_hd_bg");
            string3 = (String)this.g.getValue("v_hd_bg_fir");
            string4 = this.j;
            if (StringUtils.isNull(string2)) {
                string2 = string3;
            }
            ContentUtil.setTextColor((TextView)string4, string2);
            return;
        }
        this.a(this.a, this.b, null, string2, string3, true);
    }

    @Override
    public void a() {
        this.a = (TextView)this.e.findViewById(br.d.duoqu_call_username);
        this.b = (TextView)this.e.findViewById(br.d.duoqu_call_username_title);
        this.c = (TextView)this.e.findViewById(br.d.duoqu_number_frequency);
        this.d = (TextView)this.e.findViewById(br.d.duoqu_comingcall_number_title);
        this.l = (TextView)this.e.findViewById(br.d.duoqu_call_time);
        this.m = (TextView)this.e.findViewById(br.d.duoqu_call_time_title);
        this.n = (TextView)this.e.findViewById(br.d.duoqu_lastcall_time);
        this.o = (TextView)this.e.findViewById(br.d.duoqu_lastcall_time_title);
        this.p = (TextView)this.e.findViewById(br.d.duoqu_call_from);
        this.q = (TextView)this.e.findViewById(br.d.duoqu_callfrom_time_title);
        this.r = (TextView)this.e.findViewById(br.d.duoqu_call_state);
        this.s = (TextView)this.e.findViewById(br.d.duoqu_call_state_title);
        this.t = (TextView)this.e.findViewById(br.d.duoqu_calling_state);
        this.u = (TextView)this.e.findViewById(br.d.duoqu_calling_state_title);
        this.v = (TextView)this.e.findViewById(br.d.duoqu_callingfrom_time);
        this.w = (TextView)this.e.findViewById(br.d.duoqu_callingfrom_time_title);
        this.j = (TextView)this.e.findViewById(br.d.duoqu_call_number);
        this.k = (TextView)this.e.findViewById(br.d.duoqu_call_number_title);
        this.x = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_1);
        this.y = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_2);
        this.z = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_3);
        this.A = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_4);
        this.B = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_5);
        this.C = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_6);
        this.D = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_7);
        this.E = (ImageView)this.e.findViewById(br.d.duoqu_dotted_split_8);
    }

    public void a(int n2) {
        if (!StringUtils.isNull((String)this.g.getValue("v_by_bg"))) {
            this.a((String)this.g.getValue("v_by_bg"), n2);
            return;
        }
        if (!StringUtils.isNull((String)this.g.getValue("v_by_bg_sec"))) {
            this.a((String)this.g.getValue("v_by_bg_sec"), n2);
            return;
        }
        this.a("", n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(TextView textView, TextView textView2, ImageView imageView, String string2, String string3, boolean bl2) {
        if (bl2 || !StringUtils.isNull(string2) && this.F < 4) {
            ++this.F;
            textView.setVisibility(0);
            textView2.setVisibility(0);
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            ContentUtil.setText(textView, string2, null);
            ContentUtil.setTextColor(textView, string3);
            return;
        } else {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            if (imageView == null) return;
            {
                imageView.setVisibility(8);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(BusinessSmsMessage businessSmsMessage, boolean bl2) {
        this.g = businessSmsMessage;
        this.F = 0;
        ContentUtil.setViewVisibility(this.e, 0);
        String string2 = (String)businessSmsMessage.getValue("view_side_phone_num");
        String string3 = (String)businessSmsMessage.getValue("view_frequency");
        String string4 = (String)businessSmsMessage.getValue("view_call_time");
        String string5 = (String)businessSmsMessage.getValue("view_lastcall_time");
        String string6 = (String)businessSmsMessage.getValue("view_sideattribution");
        String string7 = (String)businessSmsMessage.getValue("view_ownstate");
        String string8 = (String)businessSmsMessage.getValue("view_sidestate");
        String string9 = (String)businessSmsMessage.getValue("view_calling_time");
        this.a(this.j, this.k, this.x, string2, null, false);
        this.a(this.c, this.d, this.y, string3, null, false);
        this.a(this.l, this.m, this.z, string4, null, false);
        this.a(this.n, this.o, this.A, string5, null, false);
        this.a(this.p, this.q, this.B, string6, null, false);
        this.a(this.r, this.s, this.C, string7, null, false);
        this.a(this.t, this.u, this.D, string8, null, false);
        this.a(this.v, this.w, this.E, string9, null, false);
        string2 = this.g.getActionJsonArray();
        int n2 = 0;
        if (string2 == null || (n2 = string2.length()) == 0) {
            this.a(br.c.duoqu_bottom_rectangle);
        } else {
            this.a(br.c.duoqu_bottom_rectangle_0);
        }
        string2 = businessSmsMessage.getImgNameByKey("v_by_l_color");
        string3 = businessSmsMessage.getImgNameByKey("v_by_r_color");
        ContentUtil.isTextSetColor(this.b, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.d, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.m, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.o, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.q, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.s, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.u, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.w, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.k, string2, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(this.a, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.c, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.j, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.l, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.n, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.p, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.r, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.t, string3, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(this.v, string3, br.a.duoqu_calls_r);
        string2 = (String)this.g.getValue("smart_call_number_text_color");
        if (string2 != null) {
            this.a((String)this.g.getValue("smart_call_number_text"), string2, (String)this.g.getValue("view_side_phone_num"));
            return;
        }
        new cn(this, businessSmsMessage).execute(new Object[]{this.g});
    }
}

