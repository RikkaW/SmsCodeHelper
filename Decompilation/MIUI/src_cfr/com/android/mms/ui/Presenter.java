/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 */
package com.android.mms.ui;

import android.content.Context;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;
import com.android.mms.ui.ViewInterface;

public abstract class Presenter
implements IModelChangedObserver {
    protected final Context mContext;
    protected Model mModel;
    protected ViewInterface mView;

    public Presenter(Context context, ViewInterface viewInterface, Model model) {
        this.mContext = context;
        this.mView = viewInterface;
        this.mModel = model;
        this.mModel.registerModelChangedObserver(this);
    }

    public abstract void present();
}

