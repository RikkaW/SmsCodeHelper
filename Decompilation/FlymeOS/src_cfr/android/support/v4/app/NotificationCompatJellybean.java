/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$BigPictureStyle
 *  android.app.Notification$BigTextStyle
 *  android.app.Notification$Builder
 *  android.app.Notification$InboxStyle
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.util.Log
 *  android.util.SparseArray
 *  android.widget.RemoteViews
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.BundleUtil;
import android.support.v4.app.NotificationBuilderWithActions;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.RemoteInputCompatBase;
import android.support.v4.app.RemoteInputCompatJellybean;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class NotificationCompatJellybean {
    static final String EXTRA_ACTION_EXTRAS = "android.support.actionExtras";
    static final String EXTRA_GROUP_KEY = "android.support.groupKey";
    static final String EXTRA_GROUP_SUMMARY = "android.support.isGroupSummary";
    static final String EXTRA_LOCAL_ONLY = "android.support.localOnly";
    static final String EXTRA_REMOTE_INPUTS = "android.support.remoteInputs";
    static final String EXTRA_SORT_KEY = "android.support.sortKey";
    static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class<?> sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock;
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock;

    static {
        sExtrasLock = new Object();
        sActionsLock = new Object();
    }

    NotificationCompatJellybean() {
    }

    public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean bl2, CharSequence charSequence2, Bitmap bitmap, Bitmap bitmap2, boolean bl3) {
        notificationBuilderWithBuilderAccessor = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(charSequence).bigPicture(bitmap);
        if (bl3) {
            notificationBuilderWithBuilderAccessor.bigLargeIcon(bitmap2);
        }
        if (bl2) {
            notificationBuilderWithBuilderAccessor.setSummaryText(charSequence2);
        }
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean bl2, CharSequence charSequence2, CharSequence charSequence3) {
        notificationBuilderWithBuilderAccessor = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(charSequence).bigText(charSequence3);
        if (bl2) {
            notificationBuilderWithBuilderAccessor.setSummaryText(charSequence2);
        }
    }

    public static void addInboxStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence object, boolean bl2, CharSequence charSequence, ArrayList<CharSequence> arrayList) {
        notificationBuilderWithBuilderAccessor = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle((CharSequence)object);
        if (bl2) {
            notificationBuilderWithBuilderAccessor.setSummaryText(charSequence);
        }
        object = arrayList.iterator();
        while (object.hasNext()) {
            notificationBuilderWithBuilderAccessor.addLine((CharSequence)object.next());
        }
    }

    public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> list) {
        SparseArray sparseArray = null;
        int n2 = list.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            Bundle bundle = list.get(i2);
            SparseArray sparseArray2 = sparseArray;
            if (bundle != null) {
                sparseArray2 = sparseArray;
                if (sparseArray == null) {
                    sparseArray2 = new SparseArray();
                }
                sparseArray2.put(i2, (Object)bundle);
            }
            sparseArray = sparseArray2;
        }
        return sparseArray;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean ensureActionReflectionReadyLocked() {
        boolean bl2 = true;
        if (sActionsAccessFailed) {
            return false;
        }
        try {
            if (sActionsField == null) {
                sActionClass = Class.forName((String)"android.app.Notification$Action");
                sActionIconField = sActionClass.getDeclaredField("icon");
                sActionTitleField = sActionClass.getDeclaredField("title");
                sActionIntentField = sActionClass.getDeclaredField("actionIntent");
                sActionsField = Notification.class.getDeclaredField("actions");
                sActionsField.setAccessible(true);
            }
        }
        catch (ClassNotFoundException var1_1) {
            Log.e((String)"NotificationCompat", (String)"Unable to access notification actions", (Throwable)var1_1);
            sActionsAccessFailed = true;
        }
        catch (NoSuchFieldException var1_2) {
            Log.e((String)"NotificationCompat", (String)"Unable to access notification actions", (Throwable)var1_2);
            sActionsAccessFailed = true;
        }
        if (sActionsAccessFailed) return false;
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static NotificationCompatBase.Action getAction(Notification object, int n2, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory2) {
        Object object2 = sActionsLock;
        synchronized (object2) {
            try {
                Object object3 = NotificationCompatJellybean.getActionObjectsLocked((Notification)object)[n2];
                object = NotificationCompatJellybean.getExtras((Notification)object);
                object = object != null && (object = object.getSparseParcelableArray("android.support.actionExtras")) != null ? (Bundle)object.get(n2) : null;
                return NotificationCompatJellybean.readAction(factory, factory2, sActionIconField.getInt(object3), (CharSequence)sActionTitleField.get(object3), (PendingIntent)sActionIntentField.get(object3), (Bundle)object);
            }
            catch (IllegalAccessException var0_1) {
                Log.e((String)"NotificationCompat", (String)"Unable to access notification actions", (Throwable)var0_1);
                sActionsAccessFailed = true;
                return null;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int getActionCount(Notification arrobject) {
        Object object = sActionsLock;
        synchronized (object) {
            arrobject = NotificationCompatJellybean.getActionObjectsLocked((Notification)arrobject);
            if (arrobject == null) return 0;
            return arrobject.length;
        }
    }

    private static NotificationCompatBase.Action getActionFromBundle(Bundle bundle, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory2) {
        return factory.build(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent)bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "remoteInputs"), factory2));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Object[] getActionObjectsLocked(Notification arrobject) {
        Object object = sActionsLock;
        synchronized (object) {
            if (!NotificationCompatJellybean.ensureActionReflectionReadyLocked()) {
                return null;
            }
            try {
                return (Object[])sActionsField.get((Object)arrobject);
            }
            catch (IllegalAccessException var0_1) {
                Log.e((String)"NotificationCompat", (String)"Unable to access notification actions", (Throwable)var0_1);
                sActionsAccessFailed = true;
                return null;
            }
        }
    }

    public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> arrayList, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory2) {
        if (arrayList == null) {
            return null;
        }
        NotificationCompatBase.Action[] arraction = factory.newArray(arrayList.size());
        for (int i2 = 0; i2 < arraction.length; ++i2) {
            arraction[i2] = NotificationCompatJellybean.getActionFromBundle((Bundle)arrayList.get(i2), factory, factory2);
        }
        return arraction;
    }

    private static Bundle getBundleForAction(NotificationCompatBase.Action action) {
        Bundle bundle = new Bundle();
        bundle.putInt("icon", action.getIcon());
        bundle.putCharSequence("title", action.getTitle());
        bundle.putParcelable("actionIntent", (Parcelable)action.getActionIntent());
        bundle.putBundle("extras", action.getExtras());
        bundle.putParcelableArray("remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        return bundle;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static Bundle getExtras(Notification var0) {
        var3_3 = NotificationCompatJellybean.sExtrasLock;
        // MONITORENTER : var3_3
        if (NotificationCompatJellybean.sExtrasFieldAccessFailed) {
            // MONITOREXIT : var3_3
            return null;
        }
        try {
            if (NotificationCompatJellybean.sExtrasField == null) {
                var1_4 = Notification.class.getDeclaredField("extras");
                if (!Bundle.class.isAssignableFrom(var1_4.getType())) {
                    Log.e((String)"NotificationCompat", (String)"Notification.extras field is not of type Bundle");
                    NotificationCompatJellybean.sExtrasFieldAccessFailed = true;
                    // MONITOREXIT : var3_3
                    return null;
                }
                var1_4.setAccessible(true);
                NotificationCompatJellybean.sExtrasField = var1_4;
            }
            var1_4 = var2_5 = (Bundle)NotificationCompatJellybean.sExtrasField.get((Object)var0);
            if (var2_5 == null) {
                var1_4 = new Bundle();
                NotificationCompatJellybean.sExtrasField.set((Object)var0, (Object)var1_4);
            }
            // MONITOREXIT : var3_3
            return var1_4;
        }
        catch (IllegalAccessException var0_1) {
            Log.e((String)"NotificationCompat", (String)"Unable to access notification extras", (Throwable)var0_1);
            ** GOTO lbl27
            catch (NoSuchFieldException var0_2) {
                Log.e((String)"NotificationCompat", (String)"Unable to access notification extras", (Throwable)var0_2);
            }
lbl27: // 2 sources:
            NotificationCompatJellybean.sExtrasFieldAccessFailed = true;
            // MONITOREXIT : var3_3
            return null;
        }
    }

    public static String getGroup(Notification notification) {
        return NotificationCompatJellybean.getExtras(notification).getString("android.support.groupKey");
    }

    public static boolean getLocalOnly(Notification notification) {
        return NotificationCompatJellybean.getExtras(notification).getBoolean("android.support.localOnly");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompatBase.Action[] arraction) {
        if (arraction == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(arraction.length);
        int n2 = arraction.length;
        int n3 = 0;
        do {
            ArrayList arrayList2 = arrayList;
            if (n3 >= n2) return arrayList2;
            arrayList.add((Object)NotificationCompatJellybean.getBundleForAction(arraction[n3]));
            ++n3;
        } while (true);
    }

    public static String getSortKey(Notification notification) {
        return NotificationCompatJellybean.getExtras(notification).getString("android.support.sortKey");
    }

    public static boolean isGroupSummary(Notification notification) {
        return NotificationCompatJellybean.getExtras(notification).getBoolean("android.support.isGroupSummary");
    }

    public static NotificationCompatBase.Action readAction(NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory2, int n2, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        RemoteInputCompatBase.RemoteInput[] arrremoteInput = null;
        if (bundle != null) {
            arrremoteInput = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.remoteInputs"), factory2);
        }
        return factory.build(n2, charSequence, pendingIntent, bundle, arrremoteInput);
    }

    public static Bundle writeActionAndGetExtras(Notification.Builder builder, NotificationCompatBase.Action action) {
        builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
        builder = new Bundle(action.getExtras());
        if (action.getRemoteInputs() != null) {
            builder.putParcelableArray("android.support.remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        }
        return builder;
    }

    public static class Builder
    implements NotificationBuilderWithActions,
    NotificationBuilderWithBuilderAccessor {
        private Notification.Builder b;
        private List<Bundle> mActionExtrasList = new ArrayList();
        private final Bundle mExtras;

        /*
         * Enabled aggressive block sorting
         */
        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int n2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int n3, int n4, boolean bl2, boolean bl3, int n5, CharSequence charSequence4, boolean bl4, Bundle bundle, String string2, boolean bl5, String string3) {
            context = new Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean bl6 = (notification.flags & 2) != 0;
            context = context.setOngoing(bl6);
            bl6 = (notification.flags & 8) != 0;
            context = context.setOnlyAlertOnce(bl6);
            bl6 = (notification.flags & 16) != 0;
            context = context.setAutoCancel(bl6).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            bl6 = (notification.flags & 128) != 0;
            this.b = context.setFullScreenIntent(pendingIntent2, bl6).setLargeIcon(bitmap).setNumber(n2).setUsesChronometer(bl3).setPriority(n5).setProgress(n3, n4, bl2);
            this.mExtras = new Bundle();
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            if (bl4) {
                this.mExtras.putBoolean("android.support.localOnly", true);
            }
            if (string2 != null) {
                this.mExtras.putString("android.support.groupKey", string2);
                if (bl5) {
                    this.mExtras.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.mExtras.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (string3 != null) {
                this.mExtras.putString("android.support.sortKey", string3);
            }
        }

        @Override
        public void addAction(NotificationCompatBase.Action action) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, action));
        }

        @Override
        public Notification build() {
            Notification notification = this.b.build();
            SparseArray<Bundle> sparseArray = NotificationCompatJellybean.getExtras(notification);
            Bundle bundle = new Bundle(this.mExtras);
            for (String string2 : this.mExtras.keySet()) {
                if (!sparseArray.containsKey(string2)) continue;
                bundle.remove(string2);
            }
            sparseArray.putAll(bundle);
            sparseArray = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (sparseArray != null) {
                NotificationCompatJellybean.getExtras(notification).putSparseParcelableArray("android.support.actionExtras", sparseArray);
            }
            return notification;
        }

        @Override
        public Notification.Builder getBuilder() {
            return this.b;
        }
    }

}

