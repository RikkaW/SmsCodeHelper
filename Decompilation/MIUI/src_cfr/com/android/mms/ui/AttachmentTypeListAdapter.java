/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;

public class AttachmentTypeListAdapter
extends BaseAdapter {
    public static AttachmentTypeItem[] mTypeItems = new AttachmentTypeItem[]{new AttachmentTypeItem(2131362045, 2130837687, true), new AttachmentTypeItem(2131362046, 2130837661, true), new AttachmentTypeItem(2131362047, 2130837669, false), new AttachmentTypeItem(2131361975, 2130837673, true), new AttachmentTypeItem(2131361976, 2130837699, true), new AttachmentTypeItem(2131362048, 2130837665, true), new AttachmentTypeItem(2131362091, 2130837677, true), new AttachmentTypeItem(2131362148, 2130837703, true), new AttachmentTypeItem(2131362133, 2130837695, true), new AttachmentTypeItem(2131361979, 2130837691, true), new AttachmentTypeItem(2131361977, 2130837707, true), new AttachmentTypeItem(2131361981, 2130837682, true)};
    private Context mContext;
    private LayoutInflater mInflater;

    public AttachmentTypeListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    }

    public int getCount() {
        return mTypeItems.length;
    }

    public Object getItem(int n) {
        return null;
    }

    public long getItemId(int n) {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(2130968578, viewGroup, false);
        }
        viewGroup = (TextView)view.findViewById(2131820548);
        ImageView imageView = (ImageView)view.findViewById(2131820547);
        viewGroup.setText(AttachmentTypeListAdapter.mTypeItems[n].nameRes);
        imageView.setImageDrawable(AttachmentTypeListAdapter.mTypeItems[n].iconRes);
        return view;
    }

    public View inflateEmptyView() {
        return this.mInflater.inflate(2130968578, null, false);
    }

    public static class AttachmentTypeItem {
        public Drawable iconRes;
        public boolean jump;
        public int nameRes;

        public AttachmentTypeItem(int n, int n2, boolean bl) {
            this.nameRes = n;
            this.iconRes = MmsApp.getApp().getResources().getDrawable(n2);
            this.jump = bl;
        }
    }

}

