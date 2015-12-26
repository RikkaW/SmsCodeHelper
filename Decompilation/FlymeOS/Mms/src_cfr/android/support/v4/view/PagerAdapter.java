/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.DataSetObservable
 *  android.database.DataSetObserver
 *  android.os.Parcelable
 *  android.view.View
 *  android.view.ViewGroup
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private DataSetObservable mObservable = new DataSetObservable();

    public void destroyItem(View view, int n2, Object object) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(ViewGroup viewGroup, int n2, Object object) {
        this.destroyItem((View)viewGroup, n2, object);
    }

    public void finishUpdate(View view) {
    }

    public void finishUpdate(ViewGroup viewGroup) {
        this.finishUpdate((View)viewGroup);
    }

    public abstract int getCount();

    public int getItemPosition(Object object) {
        return -1;
    }

    public CharSequence getPageTitle(int n2) {
        return null;
    }

    public float getPageWidth(int n2) {
        return 1.0f;
    }

    public Object instantiateItem(View view, int n2) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object instantiateItem(ViewGroup viewGroup, int n2) {
        return this.instantiateItem((View)viewGroup, n2);
    }

    public abstract boolean isViewFromObject(View var1, Object var2);

    public void notifyDataSetChanged() {
        this.mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mObservable.registerObserver((Object)dataSetObserver);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void setPrimaryItem(View view, int n2, Object object) {
    }

    public void setPrimaryItem(ViewGroup viewGroup, int n2, Object object) {
        this.setPrimaryItem((View)viewGroup, n2, object);
    }

    public void startUpdate(View view) {
    }

    public void startUpdate(ViewGroup viewGroup) {
        this.startUpdate((View)viewGroup);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mObservable.unregisterObserver((Object)dataSetObserver);
    }
}

