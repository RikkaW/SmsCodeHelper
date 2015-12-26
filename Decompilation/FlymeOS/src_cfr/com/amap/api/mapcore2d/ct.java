/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.mapcore2d.cb;
import com.amap.api.mapcore2d.cu;
import com.amap.api.mapcore2d.cw;
import com.amap.api.maps2d.model.Tile;
import com.amap.api.maps2d.model.TileProvider;

public class ct
extends cu {
    private TileProvider b = null;

    public ct(Context context, int n2, int n3) {
        super(context, n2, n3);
        this.a(context);
    }

    private void a(Context context) {
        this.b(context);
    }

    private void b(Context context) {
        if ((context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !context.isConnectedOrConnecting()) {
            cw.a("ImageFetcher", "checkConnection - no connection found", 112);
        }
    }

    private Bitmap c(cb.a a2) {
        cw.a("ImageFetcher", "processBitmap - " + a2, 111);
        Object var2_2 = null;
        Tile tile = this.b.getTile(a2.a, a2.b, a2.c);
        a2 = var2_2;
        if (tile != null) {
            a2 = var2_2;
            if (tile != TileProvider.NO_TILE) {
                a2 = BitmapFactory.decodeByteArray((byte[])tile.data, (int)0, (int)tile.data.length);
            }
        }
        return a2;
    }

    @Override
    protected Bitmap a(Object object) {
        return this.c((cb.a)object);
    }

    public void a(TileProvider tileProvider) {
        this.b = tileProvider;
    }
}

