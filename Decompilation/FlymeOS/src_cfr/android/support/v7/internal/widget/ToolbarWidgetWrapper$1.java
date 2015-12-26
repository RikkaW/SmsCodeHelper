/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.view.MenuItem;
import android.view.View;

class ToolbarWidgetWrapper$1
implements View.OnClickListener {
    final ActionMenuItem mNavItem;
    final /* synthetic */ ToolbarWidgetWrapper this$0;

    ToolbarWidgetWrapper$1(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        this.this$0 = toolbarWidgetWrapper;
        this.mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.access$000(this.this$0).getContext(), 0, 16908332, 0, 0, ToolbarWidgetWrapper.access$100(this.this$0));
    }

    public void onClick(View view) {
        if (ToolbarWidgetWrapper.access$200(this.this$0) != null && ToolbarWidgetWrapper.access$300(this.this$0)) {
            ToolbarWidgetWrapper.access$200(this.this$0).onMenuItemSelected(0, (MenuItem)this.mNavItem);
        }
    }
}

