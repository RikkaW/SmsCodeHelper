/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.PendingIntent
 *  android.app.RemoteInput
 *  android.app.RemoteInput$Builder
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.widget.RemoteViews
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationBuilderWithActions;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompatApi20;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.RemoteInputCompatBase;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

class NotificationCompatApi21 {
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ON_READ = "on_read";
    private static final String KEY_ON_REPLY = "on_reply";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_REMOTE_INPUT = "remote_input";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";

    NotificationCompatApi21() {
    }

    private static RemoteInput fromCompatRemoteInput(RemoteInputCompatBase.RemoteInput remoteInput) {
        return new RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build();
    }

    static Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation unreadConversation) {
        Parcelable[] arrparcelable = null;
        int n2 = 0;
        if (unreadConversation == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        Object object = arrparcelable;
        if (unreadConversation.getParticipants() != null) {
            object = arrparcelable;
            if (unreadConversation.getParticipants().length > 1) {
                object = unreadConversation.getParticipants()[0];
            }
        }
        arrparcelable = new Parcelable[unreadConversation.getMessages().length];
        while (n2 < arrparcelable.length) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("text", unreadConversation.getMessages()[n2]);
            bundle2.putString("author", (String)object);
            arrparcelable[n2] = bundle2;
            ++n2;
        }
        bundle.putParcelableArray("messages", arrparcelable);
        object = unreadConversation.getRemoteInput();
        if (object != null) {
            bundle.putParcelable("remote_input", (Parcelable)NotificationCompatApi21.fromCompatRemoteInput((RemoteInputCompatBase.RemoteInput)object));
        }
        bundle.putParcelable("on_reply", (Parcelable)unreadConversation.getReplyPendingIntent());
        bundle.putParcelable("on_read", (Parcelable)unreadConversation.getReadPendingIntent());
        bundle.putStringArray("participants", unreadConversation.getParticipants());
        bundle.putLong("timestamp", unreadConversation.getLatestTimestamp());
        return bundle;
    }

    public static String getCategory(Notification notification) {
        return notification.category;
    }

    /*
     * Enabled aggressive block sorting
     */
    static NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle bundle, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory object) {
        String[] arrstring;
        boolean bl2 = false;
        if (bundle == null) {
            return null;
        }
        PendingIntent pendingIntent = bundle.getParcelableArray("messages");
        if (pendingIntent == null) {
            arrstring = null;
        } else {
            boolean bl3;
            block8 : {
                arrstring = new String[pendingIntent.length];
                for (int i2 = 0; i2 < arrstring.length; ++i2) {
                    if (!(pendingIntent[i2] instanceof Bundle)) {
                        bl3 = bl2;
                    } else {
                        arrstring[i2] = ((Bundle)pendingIntent[i2]).getString("text");
                        bl3 = bl2;
                        if (arrstring[i2] != null) {
                            continue;
                        }
                    }
                    break block8;
                }
                bl3 = true;
            }
            if (!bl3) return null;
        }
        pendingIntent = (PendingIntent)bundle.getParcelable("on_read");
        PendingIntent pendingIntent2 = (PendingIntent)bundle.getParcelable("on_reply");
        RemoteInput remoteInput = (RemoteInput)bundle.getParcelable("remote_input");
        String[] arrstring2 = bundle.getStringArray("participants");
        if (arrstring2 == null) return null;
        if (arrstring2.length != 1) return null;
        if (remoteInput != null) {
            object = NotificationCompatApi21.toCompatRemoteInput(remoteInput, (RemoteInputCompatBase.RemoteInput.Factory)object);
            return factory.build(arrstring, (RemoteInputCompatBase.RemoteInput)object, pendingIntent2, pendingIntent, arrstring2, bundle.getLong("timestamp"));
        }
        object = null;
        return factory.build(arrstring, (RemoteInputCompatBase.RemoteInput)object, pendingIntent2, pendingIntent, arrstring2, bundle.getLong("timestamp"));
    }

    private static RemoteInputCompatBase.RemoteInput toCompatRemoteInput(RemoteInput remoteInput, RemoteInputCompatBase.RemoteInput.Factory factory) {
        return factory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras());
    }

    public static class Builder
    implements NotificationBuilderWithActions,
    NotificationBuilderWithBuilderAccessor {
        private Notification.Builder b;

        /*
         * Enabled aggressive block sorting
         */
        public Builder(Context object, Notification object2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int n2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int n3, int n4, boolean bl2, boolean bl3, boolean bl4, int n5, CharSequence charSequence4, boolean bl5, String string2, ArrayList<String> arrayList, Bundle bundle, int n6, int n7, Notification notification, String string3, boolean bl6, String string4) {
            object = new Notification.Builder((Context)object).setWhen(object2.when).setShowWhen(bl3).setSmallIcon(object2.icon, object2.iconLevel).setContent(object2.contentView).setTicker(object2.tickerText, remoteViews).setSound(object2.sound, object2.audioStreamType).setVibrate(object2.vibrate).setLights(object2.ledARGB, object2.ledOnMS, object2.ledOffMS);
            bl3 = (object2.flags & 2) != 0;
            object = object.setOngoing(bl3);
            bl3 = (object2.flags & 8) != 0;
            object = object.setOnlyAlertOnce(bl3);
            bl3 = (object2.flags & 16) != 0;
            object = object.setAutoCancel(bl3).setDefaults(object2.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(object2.deleteIntent);
            bl3 = (object2.flags & 128) != 0;
            this.b = object.setFullScreenIntent(pendingIntent2, bl3).setLargeIcon(bitmap).setNumber(n2).setUsesChronometer(bl4).setPriority(n5).setProgress(n3, n4, bl2).setLocalOnly(bl5).setExtras(bundle).setGroup(string3).setGroupSummary(bl6).setSortKey(string4).setCategory(string2).setColor(n6).setVisibility(n7).setPublicVersion(notification);
            object = arrayList.iterator();
            while (object.hasNext()) {
                object2 = (String)object.next();
                this.b.addPerson((String)object2);
            }
            return;
        }

        @Override
        public void addAction(NotificationCompatBase.Action action) {
            NotificationCompatApi20.addAction(this.b, action);
        }

        @Override
        public Notification build() {
            return this.b.build();
        }

        @Override
        public Notification.Builder getBuilder() {
            return this.b;
        }
    }

}

