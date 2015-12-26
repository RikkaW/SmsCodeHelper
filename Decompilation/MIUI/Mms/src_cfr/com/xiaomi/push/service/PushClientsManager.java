/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.concurrent.ConcurrentHashMap
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.ClientEventDispatcher;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PushClientsManager {
    private static PushClientsManager sInstance;
    private List<ClientChangeListener> clientChangeListeners = new ArrayList();
    private ConcurrentHashMap<String, HashMap<String, ClientLoginInfo>> mActiveClients = new ConcurrentHashMap();

    private PushClientsManager() {
    }

    public static PushClientsManager getInstance() {
        synchronized (PushClientsManager.class) {
            if (sInstance == null) {
                sInstance = new PushClientsManager();
            }
            PushClientsManager pushClientsManager = sInstance;
            return pushClientsManager;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getSmtpLocalPart(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        int n = string2.indexOf("@");
        String string3 = string2;
        if (n <= 0) return string3;
        return string2.substring(0, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addActiveClient(ClientLoginInfo iterator) {
        synchronized (this) {
            HashMap hashMap;
            HashMap hashMap2 = hashMap = (HashMap)this.mActiveClients.get((Object)iterator.chid);
            if (hashMap == null) {
                hashMap2 = new HashMap();
                this.mActiveClients.put((Object)iterator.chid, (Object)hashMap2);
            }
            hashMap2.put((Object)this.getSmtpLocalPart(iterator.userId), iterator);
            iterator = this.clientChangeListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onChange();
            }
            return;
        }
    }

    public void addClientChangeListener(ClientChangeListener clientChangeListener) {
        synchronized (this) {
            this.clientChangeListeners.add(clientChangeListener);
            return;
        }
    }

    public void deactivateAllClientByChid(String object) {
        synchronized (this) {
            HashMap hashMap = (HashMap)this.mActiveClients.get(object);
            if (hashMap != null) {
                hashMap.clear();
                this.mActiveClients.remove(object);
            }
            object = this.clientChangeListeners.iterator();
            while (object.hasNext()) {
                ((ClientChangeListener)object.next()).onChange();
            }
            return;
        }
    }

    public void deactivateClient(String object, String string2) {
        synchronized (this) {
            HashMap hashMap = (HashMap)this.mActiveClients.get(object);
            if (hashMap != null) {
                hashMap.remove((Object)this.getSmtpLocalPart(string2));
                if (hashMap.isEmpty()) {
                    this.mActiveClients.remove(object);
                }
            }
            object = this.clientChangeListeners.iterator();
            while (object.hasNext()) {
                ((ClientChangeListener)object.next()).onChange();
            }
            return;
        }
    }

    public int getActiveClientCount() {
        synchronized (this) {
            int n = this.mActiveClients.size();
            return n;
        }
    }

    public Collection<ClientLoginInfo> getAllClientLoginInfoByChid(String object) {
        synchronized (this) {
            if (!this.mActiveClients.containsKey(object)) {
                object = new ArrayList();
                return object;
            }
            object = ((HashMap)((HashMap)this.mActiveClients.get(object)).clone()).values();
        }
    }

    public ArrayList<ClientLoginInfo> getAllClients() {
        synchronized (this) {
            ArrayList arrayList;
            arrayList = new ArrayList();
            Iterator iterator = this.mActiveClients.values().iterator();
            while (iterator.hasNext()) {
                arrayList.addAll(((HashMap)iterator.next()).values());
            }
            return arrayList;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ClientLoginInfo getClientLoginInfoByChidAndUserId(String object, String string2) {
        synchronized (this) {
            block6 : {
                object = (HashMap)this.mActiveClients.get(object);
                if (object != null) break block6;
                return null;
            }
            object = (ClientLoginInfo)object.get((Object)this.getSmtpLocalPart(string2));
            return object;
        }
    }

    public void notifyConnectionFailed(Context object) {
        synchronized (this) {
            object = this.mActiveClients.values().iterator();
            while (object.hasNext()) {
                Iterator iterator = ((HashMap)object.next()).values().iterator();
                while (iterator.hasNext()) {
                    ((ClientLoginInfo)iterator.next()).setStatus(ClientStatus.unbind, 1, 3, null, null);
                }
            }
            return;
        }
    }

    public List<String> queryChannelIdByPackage(String string2) {
        synchronized (this) {
            ArrayList arrayList;
            arrayList = new ArrayList();
            Iterator iterator = this.mActiveClients.values().iterator();
            while (iterator.hasNext()) {
                for (ClientLoginInfo clientLoginInfo : ((HashMap)iterator.next()).values()) {
                    if (!string2.equals((Object)clientLoginInfo.pkgName)) continue;
                    arrayList.add(clientLoginInfo.chid);
                }
            }
            return arrayList;
        }
    }

    public void removeActiveClients() {
        synchronized (this) {
            this.mActiveClients.clear();
            return;
        }
    }

    public void removeAllClientChangeListeners() {
        synchronized (this) {
            this.clientChangeListeners.clear();
            return;
        }
    }

    public void resetAllClients(Context object, int n) {
        synchronized (this) {
            object = this.mActiveClients.values().iterator();
            while (object.hasNext()) {
                Iterator iterator = ((HashMap)object.next()).values().iterator();
                while (iterator.hasNext()) {
                    ((ClientLoginInfo)iterator.next()).setStatus(ClientStatus.unbind, 2, n, null, null);
                }
            }
            return;
        }
    }

    public static interface ClientChangeListener {
        public void onChange();
    }

    public static class ClientLoginInfo {
        public String authMethod;
        public String chid;
        public String clientExtra;
        public String cloudExtra;
        public Context context;
        private int currentRetrys = 0;
        public boolean kick;
        public ClientEventDispatcher mClientEventDispatcher;
        private XMPushService mPushService;
        public String pkgName;
        public String security;
        public String session;
        ClientStatus status = ClientStatus.unbind;
        private List<ClientStatusListener> statusChangeListeners = new ArrayList();
        private XMPushService.BindTimeoutJob timeOutJob;
        public String token;
        public String userId;

        public ClientLoginInfo(XMPushService xMPushService) {
            this.timeOutJob = new XMPushService.BindTimeoutJob(this);
            this.mPushService = xMPushService;
            this.addClientStatusListener(new ClientStatusListener(){

                @Override
                public void onChange(ClientStatus clientStatus, ClientStatus clientStatus2, int n) {
                    if (clientStatus2 == ClientStatus.binding) {
                        ClientLoginInfo.this.mPushService.executeJobDelayed(ClientLoginInfo.this.timeOutJob, 60000);
                        return;
                    }
                    ClientLoginInfo.this.mPushService.removeJobs(ClientLoginInfo.this.timeOutJob);
                }
            });
        }

        public void addClientStatusListener(ClientStatusListener clientStatusListener) {
            this.statusChangeListeners.add(clientStatusListener);
        }

        public String getDesc(int n) {
            switch (n) {
                default: {
                    return "unknown";
                }
                case 1: {
                    return "OPEN";
                }
                case 2: {
                    return "CLOSE";
                }
                case 3: 
            }
            return "KICK";
        }

        public long getNextRetryInterval() {
            return 1000 * ((long)(Math.random() * 20.0 - 10.0) + (long)((this.currentRetrys + 1) * 15));
        }

        public void removeClientStatusListener(ClientStatusListener clientStatusListener) {
            this.statusChangeListeners.remove(clientStatusListener);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void setStatus(ClientStatus clientStatus, int n, int n2, String string2, String string3) {
            boolean bl = true;
            Iterator<ClientStatusListener> iterator = this.statusChangeListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onChange(this.status, clientStatus, n2);
            }
            if (this.status != clientStatus) {
                MyLog.warn(String.format((String)"update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", (Object[])new Object[]{this.status, clientStatus, this.getDesc(n), PushConstants.getErrorDesc(n2), string2, string3, this.chid}));
                this.status = clientStatus;
            }
            if (this.mClientEventDispatcher == null) {
                MyLog.e("status changed while the client dispatcher is missing");
                return;
            }
            if (n == 2) {
                this.mClientEventDispatcher.notifyChannelClosed(this.context, this, n2);
                return;
            }
            if (n == 3) {
                this.mClientEventDispatcher.notifyKickedByServer(this.context, this, string3, string2);
                return;
            }
            if (n != 1) return;
            if (clientStatus == ClientStatus.binded) {
            } else {
                bl = false;
            }
            if (!bl) {
                if ("wait".equals((Object)string3)) {
                    ++this.currentRetrys;
                }
            } else if (bl) {
                this.currentRetrys = 0;
            }
            this.mClientEventDispatcher.notifyChannelOpenResult(this.context, this, bl, n2, string2);
        }

        public static interface ClientStatusListener {
            public void onChange(ClientStatus var1, ClientStatus var2, int var3);
        }

    }

    public static enum ClientStatus {
        unbind,
        binding,
        binded;
        

        private ClientStatus() {
        }
    }

}

