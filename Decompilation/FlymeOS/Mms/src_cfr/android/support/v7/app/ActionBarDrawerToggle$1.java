/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package android.support.v7.app;

import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

class ActionBarDrawerToggle$1
implements View.OnClickListener {
    final /* synthetic */ ActionBarDrawerToggle this$0;

    ActionBarDrawerToggle$1(ActionBarDrawerToggle actionBarDrawerToggle) {
        this.this$0 = actionBarDrawerToggle;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        if (ActionBarDrawerToggle.access$000(this.this$0)) {
            ActionBarDrawerToggle.access$100(this.this$0);
            return;
        } else {
            if (ActionBarDrawerToggle.access$200(this.this$0) == null) return;
            {
                ActionBarDrawerToggle.access$200(this.this$0).onClick(view);
                return;
            }
        }
    }
}

