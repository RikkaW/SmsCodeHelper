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
import com.android.mms.view.MessageListItem;
import com.android.mms.view.MmsFoldableTextView;

public class adc
implements ActionMode.Callback {
    final /* synthetic */ MessageListItem a;

    public adc(MessageListItem messageListItem) {
        this.a = messageListItem;
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

