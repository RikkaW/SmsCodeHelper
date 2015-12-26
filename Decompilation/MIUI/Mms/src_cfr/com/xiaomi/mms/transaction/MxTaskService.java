/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashSet
 *  miui.push.Presence
 *  miui.push.Presence$Type
 *  miui.push.ServiceClient
 */
package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.net.CloudRequestExecutor;
import com.xiaomi.mms.net.CloudRequestFactory;
import com.xiaomi.mms.net.SimpleRequest;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.push.Presence;
import miui.push.ServiceClient;

public class MxTaskService
extends IntentService {
    private static final Set<String> sPendingAddress = new HashSet();
    private final Object REQ_LOCK = new Object();
    private SimpleRequest mRequest;

    public MxTaskService() {
        super("MxTaskService");
    }

    private void queryPendingAddresses() {
        Iterator<String> iterator = sPendingAddress.iterator();
        while (iterator.hasNext()) {
            if (!this.queryPresence(iterator.next())) continue;
            iterator.remove();
        }
    }

    public static void queryPendingAddresses(Context context) {
        Intent intent = new Intent(context, (Class)MxTaskService.class);
        intent.setAction("com.xiaomi.mms.mx.ACTION_QUERY_PENDING_PRESENCE");
        context.startService(intent);
    }

    private boolean queryPresence(String string2) {
        Object object = PushSession.getInstance((Context)this);
        int n = object.getConnectedSimIndex();
        if (n < 0) {
            MyLog.d("MxTaskService", "Push is not connected, bail");
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)(object = object.getMyFullMid(n)))) {
            MyLog.w("MxTaskService", "push channel not ready, skip query presence");
            return false;
        }
        Presence presence = new Presence(Presence.Type.probe);
        presence.setChannelId("3");
        presence.setFrom((String)object);
        presence.setTo(MxMessageLogicHelper.constructFullRecipientId(string2));
        return ServiceClient.getInstance((Context)this).sendPresence(presence);
    }

    public static void queryStatus(Context context, String string2) {
        Intent intent = new Intent(context, (Class)MxTaskService.class);
        intent.setAction("com.xiaomi.mms.mx.ACTION_QUERY_PRESENCE");
        intent.putExtra("extra_address", string2);
        context.startService(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onDestroy() {
        Object object = this.REQ_LOCK;
        synchronized (object) {
            if (this.mRequest != null) {
                this.mRequest.close();
            }
        }
        super.onDestroy();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onHandleIntent(Intent object) {
        if (object == null) return;
        Object object2 = object.getAction();
        if ("com.xiaomi.mms.mx.ACTION_QUERY_PRESENCE".equals(object2)) {
            String string2;
            if (!MxActivateService.isMxEnabled((Context)this) || TextUtils.isEmpty((CharSequence)(string2 = Contact.get(object.getStringExtra("extra_address")).getMxPhoneNumber()))) return;
            object2 = MxIdCache.get(string2, true);
            object = null;
            if (object2 != null) {
                object = object2.getMId();
            }
            object2 = object;
            if (object == null) {
                object2 = this.REQ_LOCK;
                synchronized (object2) {
                    this.mRequest = CloudRequestFactory.newGetUserIdRequest(string2);
                }
                try {
                    object = object2 = CloudRequestExecutor.getUid(this.mRequest);
                }
                catch (IOException var2_3) {
                    var2_3.printStackTrace();
                }
                catch (InvalidResponseException var2_4) {
                    var2_4.printStackTrace();
                }
                object2 = object;
                if (object != null) {
                    MxIdCache.put(string2, (String)object);
                    MidPhoneMap.put((String)object, string2);
                    object2 = object;
                }
            }
            if (object2 == null) {
                MyLog.w("MxTaskService", "error when get mid");
                return;
            }
            if (!MxActivateService.isMxEnabled((Context)this)) return;
            {
                sPendingAddress.add((String)object2);
                this.queryPendingAddresses();
                return;
            }
        }
        if (!"com.xiaomi.mms.mx.ACTION_QUERY_PENDING_PRESENCE".equals(object2)) {
            return;
        }
        this.queryPendingAddresses();
    }
}

