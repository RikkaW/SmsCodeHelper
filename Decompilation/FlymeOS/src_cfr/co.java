/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class co
extends dg
implements View.OnClickListener {
    HashMap<String, String> a = null;
    private boolean b = true;
    private View c;
    private View d;
    private View j = null;
    private View k = null;
    private TextView l = null;
    private TextView m = null;
    private int[] n = new int[2];

    public co(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, int n2, ViewGroup viewGroup, int n3) {
        super(activity, businessSmsMessage, xyCallBack, n2, viewGroup, n3);
    }

    private void a(int n2, int n3, int n4) {
        ContentUtil.setViewVisibility(this.d, n4);
        ContentUtil.setViewVisibility(this.j, n2);
        ContentUtil.setViewVisibility(this.k, n3);
        ContentUtil.setViewVisibility((View)this.l, n2);
        ContentUtil.setViewVisibility((View)this.m, n3);
        this.b();
        this.b((String)this.g.getValue("v_bt_bg"));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(JSONObject object) {
        if (this.a == null) {
            this.a = new HashMap();
        } else {
            this.a.clear();
        }
        this.a.put((Object)"simIndex", (Object)String.valueOf((int)this.g.simIndex));
        this.a.put((Object)"phoneNum", (Object)this.g.originatingAddress);
        this.a.put((Object)"content", (Object)this.g.getMessageBody());
        this.a.put((Object)"viewType", (Object)String.valueOf((int)this.g.viewType));
        JsonUtil.putJsonToMap((JSONObject)object, this.a);
        object = (String)JsonUtil.getValueFromJsonObject((JSONObject)object, "action_data");
        DuoquUtils.doAction(this.f, (String)object, this.a);
    }

    private void b(String string2) {
        String string3 = string2;
        if (StringUtils.isNull(string2)) {
            string3 = "#E6E6E6";
        }
        try {
            ViewManger.setViewBg((Context)this.f, this.e, string3, br.c.duoqu_bottom_rectangle, 0);
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    @Override
    public void a() {
        this.n[0] = Math.round((float)this.f.getResources().getDimension(br.b.bubble_bottom_two_height));
        this.n[1] = Math.round((float)this.f.getResources().getDimension(br.b.bubble_bottom_two_minheight));
        this.c = this.e.findViewById(br.d.duoqu_bottom_split_line);
        this.d = this.e.findViewById(br.d.duoqu_btn_split_line);
        this.j = this.e.findViewById(br.d.duoqu_btn_1);
        this.k = this.e.findViewById(br.d.duoqu_btn_2);
        this.l = (TextView)this.e.findViewById(br.d.duoqu_btn_text_1);
        this.m = (TextView)this.e.findViewById(br.d.duoqu_btn_text_2);
        this.j.setOnClickListener((View.OnClickListener)this);
        this.k.setOnClickListener((View.OnClickListener)this);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(BusinessSmsMessage object, boolean bl2) {
        this.g = object;
        Object object2 = object.getExtendParamValue("isClickAble");
        if (object2 == null || Boolean.valueOf((String)object2.toString()).booleanValue()) {
            this.a(true);
        } else {
            this.a(false);
        }
        object2 = (String)object.getValue("v_bt_text_color");
        String string2 = object.getImgNameByKey("v_hd_bg");
        object = object.getImgNameByKey("v_hd_bg_fir");
        if (!StringUtils.isNull((String)object2)) {
            object = object2;
        } else if (!StringUtils.isNull(string2)) {
            object = string2;
        }
        if (!StringUtils.isNull((String)object)) {
            ContentUtil.textSetColor(this.l, (String)object);
            ContentUtil.textSetColor(this.m, (String)object);
        }
    }

    /*
     * Exception decompiling
     */
    public void a(boolean var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[SWITCH]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b() {
        if (this.e == null || this.j == null) return;
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        if (this.j.getVisibility() == 0) {
            if (layoutParams == null) return;
            {
                layoutParams.height = this.n[0];
                this.e.setLayoutParams(layoutParams);
                return;
            }
        }
        if (layoutParams == null) {
            return;
        }
        layoutParams.height = this.n[1];
        this.e.setLayoutParams(layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onClick(View view) {
        try {
            JSONArray jSONArray = this.g.getActionJsonArray();
            if (view == this.j && jSONArray != null && jSONArray.length() > 0) {
                this.a(jSONArray.getJSONObject(0));
                return;
            }
            if (view != this.k || jSONArray == null || jSONArray.length() <= 1) return;
            {
                this.a(jSONArray.getJSONObject(1));
                return;
            }
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
        }
    }
}

