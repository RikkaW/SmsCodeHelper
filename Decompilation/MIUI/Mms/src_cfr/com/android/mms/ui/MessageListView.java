/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.ClipboardManager
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.text.ClipboardManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListItem;
import com.android.mms.util.EditableListView;

public class MessageListView
extends EditableListView {
    private boolean mAllowTranscriptionOnResize = true;

    public MessageListView(Context context) {
        super(context);
    }

    public MessageListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyShortcut(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyShortcut(n, keyEvent);
            }
            case 31: {
                Object object = (MessageListItem)this.getSelectedView();
                if (object == null || (object = object.getMessageItem()) == null || !object.isSms()) return super.onKeyShortcut(n, keyEvent);
                ((ClipboardManager)this.getContext().getSystemService("clipboard")).setText((CharSequence)object.getBody());
                return true;
            }
        }
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mAllowTranscriptionOnResize) {
            this.moveToEnd();
        }
    }

    public void setAllowTranscriptOnResize(boolean bl) {
        this.mAllowTranscriptionOnResize = bl;
    }
}

