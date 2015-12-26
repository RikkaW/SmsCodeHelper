/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  miui.push.CommonPacketExtension
 *  miui.push.Message
 *  miui.push.Presence
 *  miui.push.PushConstants
 *  miui.push.XMPPError
 */
package com.xiaomi.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.transaction.MxStatusService;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageService;
import com.xiaomi.mms.transaction.MxResendService;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.push.CommonPacketExtension;
import miui.push.Message;
import miui.push.Presence;
import miui.push.PushConstants;
import miui.push.XMPPError;

public class MxPushMessageReceiver
extends BroadcastReceiver {
    private static long mKickCountStartTime;
    private static int mKickedCount;
    private static int mTokenInvalidCount;

    static {
        mTokenInvalidCount = 0;
        mKickedCount = 0;
        mKickCountStartTime = 0;
    }

    private void updateKickedCount() {
        if (System.currentTimeMillis() - mKickCountStartTime > 600000) {
            mKickedCount = 0;
            mKickCountStartTime = System.currentTimeMillis();
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onReceive(Context var1_1, Intent var2_2) {
        var5_3 = null;
        var6_4 = var2_2.getAction();
        if (!TextUtils.equals((CharSequence)var2_2.getStringExtra("ext_chid"), (CharSequence)"3")) {
            return;
        }
        this.updateKickedCount();
        if (!"com.xiaomi.push.new_msg".equals(var6_4)) ** GOTO lbl75
        var6_4 = new Message(var2_2.getBundleExtra("ext_packet"));
        var2_2 = var6_4.getBody();
        var7_5 = var6_4.getBodyEncoding();
        var9_6 = var6_4.getExtension("sent", null);
        var8_7 = var6_4.getExtension("received", null);
        if (TextUtils.isEmpty((CharSequence)var2_2)) ** GOTO lbl46
        var5_3 = var6_4.getExtension("xmthread", null);
        if (var5_3 == null) {
            MyLog.w("MxPushMessageReceiver", "receive msg without thread ext");
            return;
        }
        var8_7 = var5_3.getAttributeValue("type");
        var9_6 = var5_3.getText();
        if (!TextUtils.isEmpty((CharSequence)var8_7) && !TextUtils.isEmpty((CharSequence)var9_6)) {
            var10_8 = var6_4.getExtension("attachment");
            var5_3 = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_MX_RECEIVED");
            var5_3.putExtra("fromType", (String)var8_7);
            var5_3.putExtra("address", (String)var9_6);
            var5_3.putExtra("body", (String)var2_2);
            var5_3.putExtra("encoding", var7_5);
            if (var10_8 != null) {
                var5_3.putExtra("attachment", var10_8.toBundle().getBundle("attributes"));
            }
            var7_5 = var6_4.getExtension("multi-from", null);
            var2_2 = var5_3;
            if (var7_5 != null) {
                var2_2 = var5_3;
                if (!TextUtils.isEmpty((CharSequence)var7_5.getText())) {
                    var5_3.putExtra("b2c_number", var7_5.getText());
                    var2_2 = var6_4.getExtension("unicast-expire", null);
                    var7_5 = var6_4.getExtension("unicast-display", null);
                    if (var2_2 != null) {
                        var5_3.putExtra("b2c_ttl", var2_2.getText());
                    }
                    var2_2 = var5_3;
                    if (var7_5 != null) {
                        var5_3.putExtra("b2c_display_name", var7_5.getText());
                        var2_2 = var5_3;
                    }
                }
            }
        } else {
            MyLog.w("MxPushMessageReceiver", "fromType or address is empty");
            return;
lbl46: // 1 sources:
            if (var9_6 != null) {
                var7_5 = var9_6.getAttributeValue("id");
                var2_2 = var5_3;
                if (!TextUtils.isEmpty((CharSequence)var7_5)) {
                    var2_2 = var5_3;
                    if (TextUtils.isDigitsOnly((CharSequence)var7_5)) {
                        var2_2 = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_MX_SENT");
                        var2_2.putExtra("msgId", Long.parseLong((String)var7_5));
                    }
                }
            } else {
                var2_2 = var5_3;
                if (var8_7 != null) {
                    var7_5 = var8_7.getAttributeValue("id");
                    if (TextUtils.isEmpty((CharSequence)var7_5) || !TextUtils.isDigitsOnly((CharSequence)var7_5)) {
                        MyLog.w("MxPushMessageReceiver", "receive delivered ack with illegal id: " + var7_5);
                        var2_2 = var5_3;
                    } else {
                        var2_2 = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_MX_DELIVERED");
                        var2_2.putExtra("msgId", Long.parseLong((String)var7_5));
                        if (var6_4.getError() != null) {
                            var2_2.putExtra("error", true);
                        } else {
                            MxPushMessageReceiver.mKickedCount = 0;
                        }
                    }
                }
            }
        }
        if (var2_2 == null) return;
        var2_2.putExtra("from", var6_4.getFrom());
        var2_2.putExtra("to", var6_4.getTo());
        var2_2.putExtra("packetId", var6_4.getPacketID());
        var2_2.setPackage(var1_1.getPackageName());
        MxMessageService.beginStartingService(var1_1, (Intent)var2_2);
        return;
lbl75: // 1 sources:
        if ("com.xiaomi.push.new_pres".equals(var6_4)) {
            var5_3 = new Presence(var2_2.getBundleExtra("ext_packet"));
            var4_9 = var5_3.isAvailable();
            var6_4 = MxMessageLogicHelper.getSimpleMid(var5_3.getFrom());
            var2_2 = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_PRESENCE");
            var2_2.putExtra("from", var5_3.getFrom());
            var2_2.putExtra("to", var5_3.getTo());
            var2_2.putExtra("packetId", var5_3.getPacketID());
            var2_2.putExtra("mid", (String)var6_4);
            var2_2.putExtra("available", var4_9);
            var5_3 = var5_3.getExtension("client_attrs");
            if (var5_3 != null) {
                var2_2.putExtra("client_attrs", var5_3.getText());
            }
            var2_2.setPackage(var1_1.getPackageName());
            MxMessageService.beginStartingService(var1_1, (Intent)var2_2);
            return;
        }
        if ("com.xiaomi.push.channel_closed".equals(var6_4)) {
            if ((var2_2 = var2_2.getStringExtra(PushConstants.EXTRA_USER_ID)) == null) {
                MyLog.e("MxPushMessageReceiver", "receive channel close packet without toId");
                return;
            }
            var5_3 = PushSession.getInstance(var1_1);
            var3_10 = var5_3.getSimIdByMid(MxMessageLogicHelper.getSimpleMid((String)var2_2));
            if (var3_10 < 0) {
                var5_3.resetAllStatus(var1_1, PushSession.Status.DISCONNECTED);
                MyLog.w("MxPushMessageReceiver", "simIndex not ready for channel close event");
                return;
            }
            var5_3.setStatus(var1_1, var3_10, PushSession.Status.DISCONNECTED);
            return;
        }
        if ("com.xiaomi.push.channel_opened".equals(var6_4)) {
            var5_3 = var2_2.getStringExtra(PushConstants.EXTRA_USER_ID);
            if (var5_3 == null) {
                MyLog.e("MxPushMessageReceiver", "receive channel open without toId");
                return;
            }
            var6_4 = PushSession.getInstance(var1_1);
            var3_11 = var6_4.getSimIdByMid(MxMessageLogicHelper.getSimpleMid((String)var5_3));
            if (var3_11 < 0) {
                MyLog.w("MxPushMessageReceiver", "simIndex not ready for channel open event");
                var6_4.resetAllStatus(var1_1, PushSession.Status.DISCONNECTED);
                return;
            }
            if (var2_2.getBooleanExtra("ext_succeeded", false)) {
                if (var6_4.getStatus(var3_11) == PushSession.Status.CONNECTED) return;
                var6_4.setStatus(var1_1, var3_11, PushSession.Status.CONNECTED);
                MxPushMessageReceiver.mTokenInvalidCount = 0;
                MxTaskService.queryPendingAddresses(var1_1);
                if (MxPushMessageReceiver.mKickedCount < 3) {
                    var2_2 = new Intent(var1_1, (Class)MxResendService.class);
                    var2_2.putExtra(MSimUtils.SLOT_ID, var3_11);
                    var1_1.startService((Intent)var2_2);
                }
                MxStatusService.queryPendingPresence(var1_1);
                return;
            }
            var6_4.setStatus(var1_1, var3_11, PushSession.Status.DISCONNECTED);
            var2_2 = var2_2.getStringExtra("ext_reason_msg");
            if (!"invalid-sig".equals(var2_2) && !"invalid-token".equals(var2_2)) {
                if ("token-expired".equals(var2_2) == false) return;
            }
            MxActivateService.invalidMxToken(var1_1, var3_11);
            if (++MxPushMessageReceiver.mTokenInvalidCount < 3) {
                MxActivateService.enableMx(var1_1, var3_11, true, false);
                return;
            }
            MyLog.w("MxPushMessageReceiver", "max token try time reaches, abort try");
            return;
        }
        if ("com.xiaomi.push.service_started".equals(var6_4)) {
            MxActivateService.enableAll(var1_1, false);
            return;
        }
        if ("com.xiaomi.push.kicked".equals(var6_4) == false) return;
        ++MxPushMessageReceiver.mKickedCount;
        var6_4 = var2_2.getStringExtra(PushConstants.EXTRA_USER_ID);
        if (var6_4 == null) {
            MyLog.e("MxPushMessageReceiver", "kicked by server without toId");
            return;
        }
        var5_3 = PushSession.getInstance(var1_1);
        var3_12 = var5_3.getSimIdByMid(MxMessageLogicHelper.getSimpleMid((String)var6_4));
        var6_4 = var2_2.getStringExtra("ext_kick_type");
        var2_2 = var2_2.getStringExtra("ext_kick_reason");
        MyLog.d("MxPushMessageReceiver", "kicked by server: " + (String)var6_4 + ", " + "reason: " + (String)var2_2);
        if ("auth".equals(var6_4) || "modify".equals(var6_4) && "invalid-pid".equals(var2_2)) {
            if (var3_12 < 0) {
                var5_3.resetAllStatus(var1_1, PushSession.Status.DISCONNECTED);
                MxActivateService.enableAll(var1_1, false);
                return;
            }
            var5_3.setStatus(var1_1, var3_12, PushSession.Status.DISCONNECTED);
            MxActivateService.enableMx(var1_1, var3_12, true, false);
            return;
        }
        if (!"wait".equals(var6_4)) {
            if ("cancel".equals(var6_4) == false) return;
        }
        if (var3_12 < 0) {
            var5_3.resetAllStatus(var1_1, PushSession.Status.DISCONNECTED);
            return;
        }
        var5_3.setStatus(var1_1, var3_12, PushSession.Status.DISCONNECTED);
    }
}

