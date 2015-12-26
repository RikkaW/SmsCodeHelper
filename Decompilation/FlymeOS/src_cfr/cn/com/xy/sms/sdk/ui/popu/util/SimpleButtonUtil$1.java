/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.app.Activity;
import android.view.View;
import cn.com.xy.sms.sdk.ui.popu.util.SimpleButtonUtil;
import java.util.HashMap;
import org.json.JSONObject;

final class SimpleButtonUtil$1
implements View.OnClickListener {
    final /* synthetic */ JSONObject val$actionMap;
    final /* synthetic */ HashMap val$extend;
    final /* synthetic */ Activity val$mContext;

    SimpleButtonUtil$1(Activity activity, JSONObject jSONObject, HashMap hashMap) {
        this.val$mContext = activity;
        this.val$actionMap = jSONObject;
        this.val$extend = hashMap;
    }

    public void onClick(View view) {
        SimpleButtonUtil.doAction(this.val$mContext, this.val$actionMap, this.val$extend);
    }
}

