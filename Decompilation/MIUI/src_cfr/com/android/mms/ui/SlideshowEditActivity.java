/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ArrayAdapter
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduBody
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.app.ListActivity
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SlideEditorActivity;
import com.android.mms.ui.SlideListItemView;
import com.android.mms.ui.SlideshowEditor;
import com.android.mms.ui.SlideshowPresenter;
import com.android.mms.ui.ViewInterface;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduBody;
import java.util.List;
import miui.app.ListActivity;

public class SlideshowEditActivity
extends ListActivity {
    private View mAddSlideItem;
    private boolean mDirty;
    private ListView mList;
    private final IModelChangedObserver mModelChangedObserver;
    private Intent mResultIntent;
    private SlideListAdapter mSlideListAdapter;
    private SlideshowEditor mSlideshowEditor = null;
    private SlideshowModel mSlideshowModel = null;
    private Bundle mState;
    private Uri mUri;

    public SlideshowEditActivity() {
        this.mModelChangedObserver = new IModelChangedObserver(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onModelChanged(Model object, boolean bl) {
                object = SlideshowEditActivity.this;
                synchronized (object) {
                    SlideshowEditActivity.this.mDirty = true;
                }
                SlideshowEditActivity.this.setResult(-1, SlideshowEditActivity.this.mResultIntent);
                SlideshowEditActivity.this.adjustAddSlideVisibility();
            }
        };
    }

    private void addNewSlide() {
        if (this.mSlideshowEditor.addNewSlide()) {
            this.mSlideListAdapter.notifyDataSetChanged();
            this.mList.requestFocus();
            this.mList.setSelection(this.mSlideshowModel.size() - 1);
            return;
        }
        Toast.makeText((Context)this, (int)2131361870, (int)0).show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void adjustAddSlideVisibility() {
        if (this.mSlideshowModel.size() >= 20) {
            if (this.mList.getFooterViewsCount() <= 0) return;
            {
                this.mList.removeFooterView(this.mAddSlideItem);
                return;
            }
        } else {
            if (this.mList.getFooterViewsCount() > 0) return;
            {
                this.mList.addFooterView(this.mAddSlideItem);
                return;
            }
        }
    }

    private void cleanupSlideshowModel() {
        if (this.mSlideshowModel != null) {
            this.mSlideshowModel.unregisterModelChangedObserver(this.mModelChangedObserver);
            this.mSlideshowModel = null;
        }
    }

    private View createAddSlideItem() {
        View view = ((LayoutInflater)this.getSystemService("layout_inflater")).inflate(2130968711, null);
        ((TextView)view.findViewById(2131820882)).setText(2131361826);
        TextView textView = (TextView)view.findViewById(2131820884);
        textView.setText(2131361827);
        textView.setVisibility(0);
        ((ImageView)view.findViewById(2131820881)).setImageResource(2130837685);
        return view;
    }

    private void initSlideList() throws MmsException {
        this.cleanupSlideshowModel();
        this.mSlideshowModel = SlideshowModel.createFromMessageUri((Context)this, this.mUri);
        this.mSlideshowModel.registerModelChangedObserver(this.mModelChangedObserver);
        this.mSlideshowEditor = new SlideshowEditor((Context)this, this.mSlideshowModel);
        this.mSlideListAdapter = new SlideListAdapter((Context)this, 2130968711, this.mSlideshowModel);
        this.mList.setAdapter((ListAdapter)this.mSlideListAdapter);
    }

    private void openSlide(int n) {
        Intent intent = new Intent((Context)this, (Class)SlideEditorActivity.class);
        intent.setData(this.mUri);
        intent.putExtra("slide_index", n);
        this.startActivityForResult(intent, 6);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onActivityResult(int n, int n2, Intent intent) {
        if (n2 != -1) {
            return;
        }
        switch (n) {
            default: {
                return;
            }
            case 6: 
        }
        synchronized (this) {
            this.mDirty = true;
        }
        this.setResult(-1, this.mResultIntent);
        if (intent != null && intent.getBooleanExtra("done", false)) {
            this.finish();
            return;
        }
        try {
            this.initSlideList();
            this.adjustAddSlideVisibility();
            return;
        }
        catch (MmsException var3_4) {
            Log.e((String)"SlideshowEditActivity", (String)"Failed to initialize the slide-list.", (Throwable)var3_4);
            this.finish();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mList = this.getListView();
        this.mList.setDivider(null);
        this.mAddSlideItem = this.createAddSlideItem();
        if (bundle != null) {
            this.mState = bundle.getBundle("state");
        }
        this.mUri = this.mState != null ? Uri.parse((String)this.mState.getString("message_uri")) : this.getIntent().getData();
        if (this.mUri == null) {
            Log.e((String)"SlideshowEditActivity", (String)"Cannot startup activity, null Uri.");
            this.finish();
            return;
        }
        this.mResultIntent = new Intent();
        this.mResultIntent.setData(this.mUri);
        try {
            this.initSlideList();
            this.adjustAddSlideVisibility();
            return;
        }
        catch (MmsException var1_2) {
            Log.e((String)"SlideshowEditActivity", (String)"Failed to initialize the slide-list.", (Throwable)var1_2);
            this.finish();
            return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.cleanupSlideshowModel();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyDown(n, keyEvent);
            }
            case 82: 
        }
        MessageUtils.launchMessagePreference((Context)this);
        return true;
    }

    protected void onListItemClick(ListView listView, View view, int n, long l) {
        if (view == this.mAddSlideItem) {
            this.addNewSlide();
            return;
        }
        this.openSlide(n);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onOptionsItemSelected(MenuItem var1_1) {
        var2_2 = this.mList.getSelectedItemPosition();
        switch (var1_1.getItemId()) {
            case 0: {
                if (var2_2 <= 0) return true;
                if (var2_2 >= this.mSlideshowModel.size()) return true;
                this.mSlideshowEditor.moveSlideUp(var2_2);
                this.mSlideListAdapter.notifyDataSetChanged();
                this.mList.setSelection(var2_2 - 1);
                ** break;
            }
            case 1: {
                if (var2_2 < 0) return true;
                if (var2_2 >= this.mSlideshowModel.size() - 1) return true;
                this.mSlideshowEditor.moveSlideDown(var2_2);
                this.mSlideListAdapter.notifyDataSetChanged();
                this.mList.setSelection(var2_2 + 1);
                ** break;
            }
            case 2: {
                if (var2_2 < 0) return true;
                if (var2_2 >= this.mSlideshowModel.size()) return true;
                this.mSlideshowEditor.removeSlide(var2_2);
                this.mSlideListAdapter.notifyDataSetChanged();
                ** break;
            }
            case 3: {
                this.addNewSlide();
                ** break;
            }
            case 4: {
                this.mSlideshowEditor.removeAllSlides();
                this.mSlideListAdapter.notifyDataSetChanged();
                this.finish();
            }
lbl30: // 6 sources:
            default: {
                return true;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onPause() {
        super.onPause();
        synchronized (this) {
            boolean bl = this.mDirty;
            if (bl) {
                try {
                    PduBody pduBody = this.mSlideshowModel.toPduBody();
                    MiuiPduPersister.getPduPersister((Context)this).updateParts(this.mUri, pduBody);
                    this.mSlideshowModel.sync(pduBody);
                }
                catch (MmsException var2_3) {
                    Log.e((String)"SlideshowEditActivity", (String)("Cannot update the message: " + (Object)this.mUri), (Throwable)var2_3);
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (this.mSlideshowModel != null) {
            if (this.mSlideshowModel.size() < 20) {
                MenuItem menuItem = menu.add(0, 3, 0, 2131361826).setIcon(2130837726);
                boolean bl = this.mSlideshowModel.size() < 20;
                menuItem.setEnabled(bl);
            }
            menu.add(0, 4, 0, 2131361828).setIcon(2130837732);
        }
        return true;
    }

    protected void onResume() {
        super.onResume();
        if (this.mState != null) {
            this.mList.setSelection(this.mState.getInt("slide_index", 0));
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mState = new Bundle();
        if (this.mList.getSelectedItemPosition() >= 0) {
            this.mState.putInt("slide_index", this.mList.getSelectedItemPosition());
        }
        if (this.mUri != null) {
            this.mState.putString("message_uri", this.mUri.toString());
        }
        bundle.putBundle("state", this.mState);
    }

    private static class SlideListAdapter
    extends ArrayAdapter<SlideModel> {
        private final Context mContext;
        private final LayoutInflater mInflater;
        private final int mResource;
        private final SlideshowModel mSlideshow;

        public SlideListAdapter(Context context, int n, SlideshowModel slideshowModel) {
            super(context, n, (List)slideshowModel);
            this.mContext = context;
            this.mResource = n;
            this.mInflater = LayoutInflater.from((Context)context);
            this.mSlideshow = slideshowModel;
        }

        private View createViewFromResource(int n, View object, int n2) {
            object = (SlideListItemView)this.mInflater.inflate(n2, null);
            ((TextView)object.findViewById(2131820882)).setText((CharSequence)this.mContext.getString(2131362019, new Object[]{n + 1}));
            n2 = ((SlideModel)this.getItem(n)).getDuration() / 1000;
            ((TextView)object.findViewById(2131820883)).setText((CharSequence)this.mContext.getResources().getQuantityString(2131623937, n2, new Object[]{n2}));
            SlideshowPresenter slideshowPresenter = new SlideshowPresenter(this.mContext, (ViewInterface)object, this.mSlideshow);
            slideshowPresenter.setLocation(n);
            slideshowPresenter.present();
            return object;
        }

        public View getView(int n, View view, ViewGroup viewGroup) {
            return this.createViewFromResource(n, view, this.mResource);
        }
    }

}

