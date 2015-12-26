/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemSelectedListener
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;

class ListPopupWindow$3
implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ ListPopupWindow this$0;

    ListPopupWindow$3(ListPopupWindow listPopupWindow) {
        this.this$0 = listPopupWindow;
    }

    public void onItemSelected(AdapterView<?> object, View view, int n2, long l2) {
        if (n2 != -1 && (object = ListPopupWindow.access$600(this.this$0)) != null) {
            ListPopupWindow.DropDownListView.access$502((ListPopupWindow.DropDownListView)((Object)object), false);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}

