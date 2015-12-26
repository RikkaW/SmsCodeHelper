/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabaseCorruptException
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.CursorAdapter
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.collect.Lists
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 */
package com.android.mms.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.FestivalDatabase;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;

public class FestivalSmsListAdapter
extends CursorAdapter {
    private long mCategoryId;
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Integer> mOrder = Lists.newArrayList();

    public FestivalSmsListAdapter(Context context, long l) {
        super(context, null);
        this.mContext = context;
        this.mCategoryId = l;
        this.mInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    }

    private void internalRequery() {
        FestivalDatabase festivalDatabase = FestivalDatabase.getInstance();
        String string2 = "category_id=" + this.mCategoryId;
        festivalDatabase = festivalDatabase.query("messages", new String[]{"_id", "text"}, string2, null, null, null, null);
        this.mOrder.clear();
        if (festivalDatabase != null) {
            for (int i = 0; i < festivalDatabase.getCount(); ++i) {
                this.mOrder.add((Object)i);
            }
        }
        this.changeCursor((Cursor)festivalDatabase);
    }

    public void bindView(View view, Context object, Cursor cursor) {
        object = this.getItemMessage(cursor.getPosition());
        ((TextView)view.getTag()).setText((CharSequence)object);
    }

    protected void close() {
        this.changeCursor(null);
        this.mOrder.clear();
    }

    public int getCount() {
        return this.mOrder.size();
    }

    public Object getItem(int n) {
        return super.getItem(((Integer)this.mOrder.get(n)).intValue());
    }

    public String getItemMessage(int n) {
        Cursor cursor = (Cursor)this.getItem(n);
        if (cursor == null) {
            return null;
        }
        return cursor.getString(1);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        context = this.mInflater.inflate(2130968616, null, false);
        context.setTag((Object)context.findViewById(2131820645));
        return context;
    }

    public void requery() {
        try {
            this.internalRequery();
            return;
        }
        catch (SQLiteDatabaseCorruptException var1_1) {
            Toast.makeText((Context)this.mContext, (int)2131362061, (int)1).show();
            return;
        }
    }

    public void shuffle() {
        Collections.shuffle(this.mOrder);
        this.notifyDataSetChanged();
    }
}

