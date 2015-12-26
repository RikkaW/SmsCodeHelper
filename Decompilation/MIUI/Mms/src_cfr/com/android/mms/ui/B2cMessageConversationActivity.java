/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.Button
 */
package com.android.mms.ui;

import android.content.res.Resources;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.mms.MmsApp;
import com.android.mms.ui.SingleRecipientConversationActivity;

public class B2cMessageConversationActivity
extends SingleRecipientConversationActivity {
    private static int sEditContainerMarginLeft = MmsApp.getApp().getResources().getDimensionPixelSize(2131427487);
    private View mSimButtonLeftView;

    @Override
    protected void handleShowSecurityAlert(Cursor cursor) {
    }

    @Override
    protected void initResourceRefs() {
        super.initResourceRefs();
        this.mSwitchBtn.setVisibility(8);
        ((ViewGroup.MarginLayoutParams)this.mEditorContainer.getLayoutParams()).leftMargin = sEditContainerMarginLeft;
        if (this.mSimButtonContainer != null) {
            this.mSimButtonLeftView = this.findViewById(2131820697);
            this.mSimButtonLeftView.setVisibility(8);
            ((ViewGroup.MarginLayoutParams)this.mSendButton1.getLayoutParams()).leftMargin = sEditContainerMarginLeft;
        }
    }
}

