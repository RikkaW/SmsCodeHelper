/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$Data
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  miui.telephony.PhoneNumberUtils
 *  miui.yellowpage.YellowPageUtils
 */
package com.android.mms.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.ui.B2cMessageConversationActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.AddressUtils;
import com.xiaomi.mms.utils.B2cMessageUtils;
import miui.telephony.PhoneNumberUtils;
import miui.yellowpage.YellowPageUtils;

public class SingleRecipientConversationHeader
extends RelativeLayout {
    private View.OnClickListener mCallClickListener;
    private ImageView mCallContact;
    private Contact mContact;
    private View mFetionPrefix;
    private TextView mFromView;
    private View mHomeView;
    private View mMainTextView;
    private TextView mPhoneLocation;
    private TextView mPhoneTag;
    private ImageView mShowContact;
    private View.OnClickListener mVoipClickListener;
    private boolean mVoipStyle;

    public SingleRecipientConversationHeader(Context context) {
        super(context);
        this.mVoipClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                SingleRecipientConversationHeader.this.onVoipCall();
            }
        };
        this.mCallClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                SingleRecipientConversationHeader.this.onMenuItemIdCall();
            }
        };
        this.mContext = context;
    }

    public SingleRecipientConversationHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mVoipClickListener = new ;
        this.mCallClickListener = new ;
        this.mContext = context;
    }

    private Intent getYellowPageDetailIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse((String)("yellowpage://details?sid=" + this.mContact.getYellowPageId())));
        intent.putExtra("source", "sms_conv_menu");
        return intent;
    }

    private boolean isB2cContact() {
        if (this.mContact != null && B2cMessageUtils.isB2cNumber(this.mContact)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void onMenuItemIdCall() {
        String string2;
        if (this.mContact == null) return;
        if (this.mContact.existsInDatabase()) {
            string2 = Uri.withAppendedPath((Uri)ContactsContract.Data.CONTENT_URI, (String)String.valueOf((long)this.mContact.getContactMethodId()));
            Intent intent = new Intent("android.intent.action.CALL_PRIVILEGED");
            if (string2 == null) {
                LogTag.verbose("onMenuItemIdCall uri is null", new Object[0]);
                return;
            }
            intent.setDataAndType((Uri)string2, "vnd.android.cursor.item/phone_v2");
            string2 = intent;
            if (!TextUtils.isEmpty((CharSequence)this.mContact.getName())) {
                intent.putExtra("android.phone.extra.CONTACT_NAME", this.mContact.getName());
                string2 = intent;
            }
        } else {
            Intent intent;
            string2 = this.mContact.getNumber();
            if (TextUtils.isEmpty((CharSequence)string2)) {
                LogTag.verbose("onMenuItemIdCall number is empty", new Object[0]);
                return;
            }
            string2 = intent = new Intent("android.intent.action.CALL_PRIVILEGED", Uri.parse((String)("tel:" + string2)));
            if (!TextUtils.isEmpty((CharSequence)this.mContact.getName())) {
                intent.putExtra("android.phone.extra.CONTACT_NAME", this.mContact.getName());
                string2 = intent;
            }
        }
        string2.setPackage(MessageUtils.getPhonePackageName());
        this.mContext.startActivity((Intent)string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void onMenuItemIdContactLook() {
        if (this.mContact == null) return;
        long l = this.mContact.getPersonId();
        if (this.mContact.isB2cNumber()) {
            Intent intent = B2cMessageUtils.getB2cContactDetailIntent(this.mContact.getNumber());
            this.mContext.startActivity(intent);
            return;
        }
        if (this.mContact.isYellowPageNumber()) {
            Intent intent = this.getYellowPageDetailIntent();
            this.mContext.startActivity(intent);
            return;
        }
        if (l > 0) {
            Uri uri = this.mContact.getUri();
            if (uri == null) {
                LogTag.verbose("onMenuItemIdContactLook uri is null", new Object[0]);
                return;
            }
            uri = new Intent("android.intent.action.VIEW", uri);
            uri.putExtra("ignoreDefaultUpBehavior", true);
            uri.setPackage(MessageUtils.getContactsPackageName());
            this.mContext.startActivity((Intent)uri);
            return;
        }
        if (this.mContact.isEmail()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("data1", this.mContact.getNumber());
            intent.setPackage(MessageUtils.getContactsPackageName());
            this.mContext.startActivity(intent);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("number", this.mContact.getNumber());
        intent.setPackage(MessageUtils.getContactsPackageName());
        this.mContext.startActivity(intent);
    }

    private void onVoipCall() {
        if (this.mContact != null) {
            Intent intent = new Intent("com.miui.voip.action.MAKE_VOIP_CALL");
            intent.setPackage("com.miui.voip");
            intent.putExtra("extra_make_voip_call_from", 3);
            intent.putExtra("extra_contact_name", this.mContact.getName());
            intent.putExtra("extra_numbers", new String[]{this.mContact.getNumber()});
            this.mContext.startService(intent);
        }
    }

    private void setUpB2cContactInfo() {
        this.mFromView.setText((CharSequence)this.mContact.getName());
        this.mPhoneLocation.setText(2131362400);
        this.mPhoneLocation.setVisibility(0);
        this.mPhoneTag.setVisibility(8);
        this.mFetionPrefix.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setUpContactInfo() {
        int n = 0;
        String string2 = this.mContact.getName();
        String string3 = this.mContact.getNumber();
        String string4 = this.mContact.getDisplayTag();
        String string5 = PhoneNumberUtils.parseTelocationString((Context)this.mContext, (CharSequence)string3);
        if (string2.equals((Object)string3)) {
            if (!TextUtils.isEmpty((CharSequence)string5)) {
                this.mPhoneLocation.setText((CharSequence)string5);
                this.mPhoneLocation.setVisibility(0);
            } else {
                this.mPhoneLocation.setVisibility(8);
            }
            if (!TextUtils.isEmpty((CharSequence)string4)) {
                if (!TextUtils.isEmpty((CharSequence)string5)) {
                    this.mPhoneTag.setText((CharSequence)(" | " + string4));
                } else {
                    this.mPhoneTag.setText((CharSequence)string4);
                }
                this.mPhoneTag.setVisibility(0);
            } else {
                this.mPhoneTag.setVisibility(8);
            }
            this.mFromView.setText((CharSequence)string3);
        } else {
            this.mFromView.setText((CharSequence)string2);
            if (!TextUtils.isEmpty((CharSequence)string5)) {
                this.mPhoneLocation.setText((CharSequence)(string3 + " " + string5));
            } else {
                this.mPhoneLocation.setText((CharSequence)string3);
            }
            this.mPhoneLocation.setVisibility(0);
            this.mPhoneTag.setVisibility(8);
        }
        string2 = this.mFetionPrefix;
        if (!AddressUtils.isFetionNumber(string3)) {
            n = 8;
        }
        string2.setVisibility(n);
    }

    private void updateYellowPageNumber() {
        if (this.mContact != null) {
            YellowPageUtils.updatePhoneInfo((Context)this.mContext, (String)this.mContact.getNumber());
        }
    }

    public void hideCallMenu() {
        this.mCallContact.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHomeView = this.findViewById(2131820651);
        this.mMainTextView = this.findViewById(2131820867);
        this.mPhoneLocation = (TextView)this.findViewById(2131820870);
        this.mPhoneTag = (TextView)this.findViewById(2131820871);
        this.mFromView = (TextView)this.findViewById(2131820586);
        this.mFetionPrefix = this.findViewById(2131820585);
        this.mCallContact = (ImageView)this.findViewById(2131820872);
        this.mShowContact = (ImageView)this.findViewById(2131820873);
        this.mShowContact.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingleRecipientConversationHeader.this.onMenuItemIdContactLook();
            }
        });
        if (this.mContext instanceof B2cMessageConversationActivity) {
            this.mShowContact.setVisibility(8);
        } else {
            this.mShowContact.setVisibility(0);
        }
        this.mCallContact.setVisibility(0);
        this.updateState();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        if (this.mPhoneTag.getVisibility() == 8 && this.mPhoneLocation.getVisibility() == 8) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.mHomeView.getLayoutParams();
            layoutParams.addRule(6, 2131820868);
            this.mHomeView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            n = this.mHomeView.getTop() + (this.mHomeView.getMeasuredHeight() - this.mMainTextView.getMeasuredHeight()) / 2;
            this.mMainTextView.setTop(n);
            this.mMainTextView.setBottom(this.mMainTextView.getMeasuredHeight() + n);
            return;
        } else {
            n2 = Math.max((int)(this.mMainTextView.getTop() + (this.mMainTextView.getMeasuredHeight() - this.mHomeView.getMeasuredHeight()) / 2), (int)0);
            this.mHomeView.layout(n, n2, this.mHomeView.getMeasuredWidth() + n, this.mHomeView.getMeasuredHeight() + n2);
            if (this.mPhoneLocation.getVisibility() != 8) return;
            {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.mPhoneTag.getLayoutParams();
                layoutParams.addRule(5, 2131820867);
                this.mPhoneTag.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
                return;
            }
        }
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        n = this.mMainTextView.getMeasuredWidth();
        if (this.mPhoneTag.getVisibility() == 0 && this.mPhoneLocation.getVisibility() == 0) {
            this.mPhoneTag.measure(View.MeasureSpec.makeMeasureSpec((int)(n / 2), (int)Integer.MIN_VALUE), 0);
            n2 = this.mPhoneTag.getMeasuredWidth();
            this.mPhoneLocation.measure(View.MeasureSpec.makeMeasureSpec((int)(n - n2), (int)Integer.MIN_VALUE), 0);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void updateState() {
        if (this.mCallContact == null) return;
        if (this.mVoipStyle) {
            this.mCallContact.setImageResource(2130837715);
            this.mCallContact.setOnClickListener(this.mVoipClickListener);
            return;
        }
        this.mCallContact.setImageResource(2130837714);
        this.mCallContact.setOnClickListener(this.mCallClickListener);
    }

    public void updateState(boolean bl) {
        this.mVoipStyle = bl;
        this.updateState();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void updateTitle(ContactList contactList) {
        if (contactList == null || contactList.size() == 0) {
            LogTag.verbose("updateTitle mContact is null", new Object[0]);
            return;
        }
        this.mContact = (Contact)contactList.get(0);
        if (this.isB2cContact()) {
            this.setUpB2cContactInfo();
            contactList = this.mShowContact;
            int n = this.mContact.isYellowPageB2cNumber() ? 0 : 8;
            contactList.setVisibility(n);
        } else {
            this.setUpContactInfo();
        }
        this.updateYellowPageNumber();
    }

}

