/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.Menu
 *  android.view.MenuItem
 *  java.lang.Object
 */
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.android.mms.view.MessageListItemSms;
import com.android.mms.view.MmsFoldableTextView;

public class adp
implements ActionMode.Callback {
    final /* synthetic */ MessageListItemSms a;

    public adp(MessageListItemSms messageListItemSms) {
        this.a = messageListItemSms;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        if (this.a.z == null) {
            return;
        }
        this.a.x();
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }
}

