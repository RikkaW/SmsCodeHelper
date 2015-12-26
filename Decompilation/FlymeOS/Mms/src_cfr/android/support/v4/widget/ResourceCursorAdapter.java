/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ResourceCursorAdapter
extends CursorAdapter {
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;

    @Deprecated
    public ResourceCursorAdapter(Context context, int n2, Cursor cursor) {
        super(context, cursor);
        this.mDropDownLayout = n2;
        this.mLayout = n2;
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context context, int n2, Cursor cursor, int n3) {
        super(context, cursor, n3);
        this.mDropDownLayout = n2;
        this.mLayout = n2;
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context context, int n2, Cursor cursor, boolean bl2) {
        super(context, cursor, bl2);
        this.mDropDownLayout = n2;
        this.mLayout = n2;
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    @Override
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mDropDownLayout, viewGroup, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mLayout, viewGroup, false);
    }

    public void setDropDownViewResource(int n2) {
        this.mDropDownLayout = n2;
    }

    public void setViewResource(int n2) {
        this.mLayout = n2;
    }
}

