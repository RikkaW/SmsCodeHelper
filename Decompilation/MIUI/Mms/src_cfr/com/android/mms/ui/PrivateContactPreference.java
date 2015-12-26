/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  java.lang.Object
 *  java.lang.String
 *  miui.preference.ValuePreference
 */
package com.android.mms.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.mms.data.Contact;
import miui.preference.ValuePreference;

public class PrivateContactPreference
extends ValuePreference {
    private Contact mContact;
    private Context mContext;
    private ImageView mDeleteBtn;
    View.OnClickListener mDeleteClickListener;
    private Handler mHandler = new Handler();
    private long mId;
    private OnClickDeleteBtnListener mOnClickDeleteBtnListener;
    private ImageView mPhotoView;

    public PrivateContactPreference(Context context) {
        this(context, null);
    }

    public PrivateContactPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDeleteClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                if (PrivateContactPreference.this.mOnClickDeleteBtnListener != null) {
                    PrivateContactPreference.this.mOnClickDeleteBtnListener.onClick(PrivateContactPreference.this.mId, PrivateContactPreference.this.mContact);
                }
            }
        };
        this.mContext = context;
        this.setLayoutResource(2130968691);
    }

    protected void onBindView(View view) {
        super.onBindView(view);
        this.mPhotoView.setVisibility(0);
        if (this.mContact.existsInDatabase() || this.mContact.isYellowPageNumber()) {
            Contact.loadPhoto(this.mPhotoView, this.mContact);
            return;
        }
        this.mPhotoView.setImageResource(285343796);
    }

    protected View onCreateView(ViewGroup viewGroup) {
        viewGroup = super.onCreateView(viewGroup);
        this.mPhotoView = (ImageView)viewGroup.findViewById(16908294);
        this.mDeleteBtn = (ImageView)viewGroup.findViewById(2131820848);
        this.mDeleteBtn.setClickable(true);
        this.mDeleteBtn.setFocusable(true);
        this.mDeleteBtn.setOnClickListener(this.mDeleteClickListener);
        return viewGroup;
    }

    public void refreshShowInfo() {
        this.mHandler.post(new Runnable(){

            @Override
            public void run() {
                if (PrivateContactPreference.this.mContact != null) {
                    PrivateContactPreference.this.setTitle((CharSequence)PrivateContactPreference.this.mContact.getName());
                    PrivateContactPreference.this.setSummary((CharSequence)PrivateContactPreference.this.mContact.getNumber());
                }
            }
        });
    }

    public void setOnDeleteBtnClickListener(OnClickDeleteBtnListener onClickDeleteBtnListener) {
        this.mOnClickDeleteBtnListener = onClickDeleteBtnListener;
    }

    public void setShowInfo(long l, Contact contact) {
        this.mId = l;
        this.mContact = contact;
        this.refreshShowInfo();
    }

    public static interface OnClickDeleteBtnListener {
        public void onClick(long var1, Contact var3);
    }

}

