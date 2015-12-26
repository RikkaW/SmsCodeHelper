/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.LogTag;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.B2cMessageConversationActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MultipleRecipientsConversationActivity;
import com.android.mms.ui.NewMessageActivity;
import com.android.mms.ui.NewMessagePopupActivity;
import com.android.mms.ui.SingleRecipientConversationActivity;

public class ComposeMessageRouterActivity
extends Activity {
    public static Intent createIntent(Context context, long l) {
        Intent intent = new Intent();
        ComposeMessageRouterActivity.prepareIntent(intent, l);
        ComposeMessageRouterActivity.processIntent(context, intent);
        return intent;
    }

    public static Uri createSearchResultDataUri(long l, long l2, String string2, String string3) {
        Uri.Builder builder = new Uri.Builder().scheme("sms").appendPath("conversation").appendPath(String.valueOf((long)l)).appendQueryParameter("select_id", String.valueOf((long)l2));
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            builder.appendQueryParameter("highlight_text", string2);
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            builder.appendQueryParameter("body_substitution", MessageUtils.getSnippet(string3, string2, 30, 10)).build();
        }
        return builder.build();
    }

    public static Uri createViewOrginDataUri(long l, long l2) {
        return ComposeMessageRouterActivity.createSearchResultDataUri(l, l2, null, null);
    }

    public static void prepareIntent(Intent intent, long l) {
        if (l > 0) {
            intent.putExtra("thread_id", l);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void processIntent(Context context, Intent intent) {
        boolean bl;
        Conversation conversation = null;
        long l = intent.getLongExtra("thread_id", -1);
        if (l != -1) {
            conversation = Conversation.get(l);
            bl = false;
        } else {
            Object object = intent.getData();
            if (object != null) {
                conversation = Conversation.get((Uri)object);
            } else {
                object = intent.getStringExtra("address");
                if (!TextUtils.isEmpty((CharSequence)object)) {
                    conversation = Conversation.get(ContactList.getByNumbers((String)object, true));
                }
            }
            bl = conversation != null ? conversation.isPrivate() : false;
        }
        if (conversation != null) {
            l = conversation.getThreadId();
            int n = conversation.getMessageCount();
            int n2 = conversation.getRecipients() == null ? 0 : conversation.getRecipients().size();
            LogTag.debug(String.format((String)"routing: threadId=%d, messageCount=%d, recipientCount=%d", (Object[])new Object[]{l, n, n2}), new Object[0]);
        } else {
            LogTag.debug("routing: conv=null", new Object[0]);
        }
        if (conversation == null || conversation.getMessageCount() == 0 || conversation.getRecipients() == null || conversation.getRecipients().size() == 0 || conversation.getThreadId() == 0 || bl) {
            if (bl) {
                intent.putExtra("private_recipient", true);
            }
            intent.setClass(context, (Class)NewMessageActivity.class);
            return;
        }
        ComposeMessageRouterActivity.prepareIntent(intent, conversation.getThreadId());
        if (conversation.getRecipients().size() > 1) {
            intent.setClass(context, (Class)MultipleRecipientsConversationActivity.class);
            return;
        }
        if (conversation.isB2c()) {
            intent.setClass(context, (Class)B2cMessageConversationActivity.class);
            return;
        }
        intent.setClass(context, (Class)SingleRecipientConversationActivity.class);
    }

    public static void route(Context context, Intent intent) {
        ComposeMessageRouterActivity.processIntent(context, intent);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        NewMessagePopupActivity.dismiss((Context)this);
        bundle = this.getIntent();
        bundle.putExtra("from_activity", "ComposeMessageRouterActivity");
        ComposeMessageRouterActivity.route((Context)this, (Intent)bundle);
        this.finish();
        this.overridePendingTransition(0, 0);
    }
}

