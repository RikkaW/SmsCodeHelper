/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import java.util.Collection;

public class MultipleRecipientsConversationHeader
extends RelativeLayout
implements View.OnClickListener {
    private ContactList mContactList;
    private final Context mContext;
    private Button mHomeBtn;
    private MultiRecipientContactInfoListAdapter mMultiRecipientContactInfoListAdapter;
    private TextView mTitle;
    private ImageView mTitleArrow;
    private View mTitleBar;
    private TextView mTitleCount;
    private PopupWindow mTitlePopupWindow;

    public MultipleRecipientsConversationHeader(Context context) {
        super(context);
        this.mContext = context;
    }

    public MultipleRecipientsConversationHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void showTitleContactInfo(boolean bl) {
        if (bl) {
            if (this.mTitlePopupWindow == null) {
                this.mMultiRecipientContactInfoListAdapter = new MultiRecipientContactInfoListAdapter(this.mContext);
                View view = LayoutInflater.from((Context)this.mContext).inflate(2130968668, null);
                ((ListView)view.findViewById(2131820796)).setAdapter((ListAdapter)this.mMultiRecipientContactInfoListAdapter);
                this.mMultiRecipientContactInfoListAdapter.setContactList(this.mContactList);
                this.mTitlePopupWindow = new PopupWindow(view, -1, -2, true);
                this.mTitlePopupWindow.setOutsideTouchable(true);
                this.mTitlePopupWindow.setBackgroundDrawable((Drawable)new ColorDrawable());
                this.mTitlePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener(){

                    public void onDismiss() {
                        MultipleRecipientsConversationHeader.this.mTitleArrow.setImageResource(2130837967);
                    }
                });
            }
            this.mTitlePopupWindow.showAsDropDown(this.mTitleBar, 0, 0);
            this.mTitleArrow.setImageResource(2130837964);
            return;
        } else {
            if (this.mTitlePopupWindow == null) return;
            {
                this.mTitlePopupWindow.dismiss();
                return;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onClick(View view) {
        if (view != this.mTitleBar) return;
        if (this.mTitlePopupWindow == null || !this.mTitlePopupWindow.isShowing()) {
            this.showTitleContactInfo(true);
            return;
        }
        this.showTitleContactInfo(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitleBar = this;
        this.mTitleBar.setOnClickListener((View.OnClickListener)this);
        this.mHomeBtn = (Button)this.findViewById(2131820651);
        this.mTitle = (TextView)this.findViewById(2131820800);
        this.mTitleCount = (TextView)this.findViewById(2131820801);
        this.mTitleArrow = (ImageView)this.findViewById(2131820799);
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        n2 = Math.max((int)(this.mTitle.getTop() + (this.mTitle.getMeasuredHeight() - this.mHomeBtn.getMeasuredHeight()) / 2), (int)0);
        this.mHomeBtn.layout(n, n2, this.mHomeBtn.getMeasuredWidth() + n, this.mHomeBtn.getMeasuredHeight() + n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void updateTitle(ContactList contactList) {
        this.mContactList = contactList;
        String string2 = "";
        int n = 0;
        String string3 = string2;
        if (contactList != null) {
            int n2;
            n = n2 = contactList.size();
            string3 = string2;
            switch (n2) {
                default: {
                    string3 = contactList.formatNames(", ");
                    n = n2;
                }
                case 0: {
                    break;
                }
                case 1: {
                    string3 = ((Contact)contactList.get(0)).getNameAndNumber();
                    n = n2;
                }
            }
        }
        if (this.mMultiRecipientContactInfoListAdapter != null) {
            this.mMultiRecipientContactInfoListAdapter.notifyDataSetChanged();
        }
        this.mTitle.setText((CharSequence)string3);
        if (n > 0) {
            this.mTitleCount.setText((CharSequence)this.mContext.getString(2131362054, new Object[]{n}));
        }
    }

    private class MultiRecipientContactInfoItem
    extends RelativeLayout {
        private TextView mNameView;
        private TextView mNumberView;

        public MultiRecipientContactInfoItem(Context context) {
            super(context);
            MultiRecipientContactInfoItem.inflate((Context)context, (int)2130968669, (ViewGroup)this);
            this.mNameView = (TextView)this.findViewById(2131820752);
            this.mNumberView = (TextView)this.findViewById(2131820753);
        }

        public void bind(Contact contact) {
            this.mNameView.setText((CharSequence)contact.getName());
            this.mNumberView.setText((CharSequence)contact.getNumber());
            if (contact.getName().equals((Object)contact.getNumber())) {
                this.mNumberView.setVisibility(8);
                return;
            }
            this.mNumberView.setVisibility(0);
        }
    }

    private class MultiRecipientContactInfoListAdapter
    extends BaseAdapter {
        private ContactList mContactList;
        private Context mContext;

        public MultiRecipientContactInfoListAdapter(Context context) {
            this.mContext = context;
            this.mContactList = new ContactList();
        }

        public int getCount() {
            return this.mContactList.size();
        }

        public Contact getItem(int n) {
            return (Contact)this.mContactList.get(n);
        }

        public long getItemId(int n) {
            return n;
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getView(int n, View object, ViewGroup viewGroup) {
            object = object == null || !(object instanceof MultiRecipientContactInfoItem) ? new MultiRecipientContactInfoItem(this.mContext) : (MultiRecipientContactInfoItem)((Object)object);
            object.bind(this.getItem(n));
            return object;
        }

        void setContactList(ContactList contactList) {
            if (contactList != null) {
                this.mContactList.addAll((Collection<? extends Contact>)((Object)contactList));
            }
        }
    }

}

