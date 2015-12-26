/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.os.Handler;
import android.os.Message;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;

final class XySdkUtil$1
extends Handler {
    XySdkUtil$1() {
    }

    public void handleMessage(Message message) {
        ParseSmsToBubbleUtil.beforeHandParseReceiveSms(500, 3);
    }
}

