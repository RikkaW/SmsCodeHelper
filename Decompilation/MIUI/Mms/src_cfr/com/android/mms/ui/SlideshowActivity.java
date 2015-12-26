/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ActivityNotFoundException
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.graphics.drawable.Drawable
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.preference.PreferenceManager
 *  android.text.TextUtils
 *  android.text.style.URLSpan
 *  android.util.Log
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnDoubleTapListener
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.widget.BaseAdapter
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.MediaController
 *  android.widget.MediaController$MediaPlayerControl
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.util.SqliteWrapper
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.app.Activity
 *  miui.app.OnStatusBarChangeListener
 *  miui.os.Build
 *  miui.view.menu.ContextMenuDialog
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.dom.AttrImpl;
import com.android.mms.dom.smil.SmilPlayer;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.Model;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.SmilHelper;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PresenterFactory;
import com.android.mms.ui.SimplePduDoc;
import com.android.mms.ui.SimplePduPage;
import com.android.mms.ui.SimplePduPart;
import com.android.mms.ui.SlideView;
import com.android.mms.ui.SlideshowHeader;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.google.android.mms.MmsException;
import com.google.android.mms.util.SqliteWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import miui.app.OnStatusBarChangeListener;
import miui.os.Build;
import miui.view.menu.ContextMenuDialog;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;

public class SlideshowActivity
extends miui.app.Activity
implements GestureDetector.OnDoubleTapListener,
GestureDetector.OnGestureListener,
ISmsTextSizeAdjustHost,
EventListener {
    private static final String[] MMS_PROJECT = new String[]{"sub", "sub_cs", "date", "date_ms_part", "date_sent", "timed"};
    private FlatshowAdapter mFlatshowAdapter;
    private ListView mFlatshowListView;
    private GestureDetector mGestureDetector;
    private Handler mHandler;
    private String mHighlight;
    private MediaController mMediaController;
    private SlideView mSlideView;
    private SlideshowHeader mSlideshowHeader;
    private SMILDocument mSmilDoc;
    private SmilPlayer mSmilPlayer;
    private int mStatusBarHeight = 0;

    private void gainFocus() {
        ((AudioManager)this.getSystemService("audio")).requestAudioFocus(null, 3, 1);
    }

    private void initMediaController() {
        this.mMediaController = new MediaController((Context)this, false);
        this.mMediaController.setMediaPlayer((MediaController.MediaPlayerControl)new SmilPlayerController(this.mSmilPlayer));
        this.mMediaController.setAnchorView(this.findViewById(2131820655));
        this.mMediaController.setPrevNextListeners(new View.OnClickListener(){

            public void onClick(View view) {
                SlideshowActivity.this.mSmilPlayer.next();
            }
        }, new View.OnClickListener(){

            public void onClick(View view) {
                SlideshowActivity.this.mSmilPlayer.prev();
            }
        });
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static final boolean isMMSConformance(SMILDocument var0) {
        if ((var0 = var0.getHead()) == null) {
            return false;
        }
        if ((var0 = var0.getChildNodes()) == null) return false;
        if (var0.getLength() != 1) {
            return false;
        }
        if ((var0 = var0.item(0)) == null) return false;
        if (!"layout".equals((Object)var0.getNodeName())) {
            return false;
        }
        if ((var0 = var0.getChildNodes()) == null) {
            return false;
        }
        var3_1 = var0.getLength();
        if (var3_1 <= 0) {
            return false;
        }
        var1_2 = 0;
        block0 : do {
            if (var1_2 >= var3_1) return true;
            var4_4 = var0.item(var1_2);
            if (var4_4 == null) {
                return false;
            }
            var5_5 = var4_4.getNodeName();
            if ("root-layout".equals(var5_5)) ** GOTO lbl-1000
            if ("region".equals(var5_5) == false) return false;
            var4_4 = var4_4.getAttributes();
            var2_3 = 0;
            do {
                if (var2_3 >= var4_4.getLength()) lbl-1000: // 2 sources:
                {
                    ++var1_2;
                    continue block0;
                }
                var5_5 = var4_4.item(var2_3);
                if (var5_5 == null) {
                    return false;
                }
                var6_6 = var5_5.getNodeName();
                if (!("left".equals((Object)var6_6) || "top".equals((Object)var6_6) || "height".equals((Object)var6_6) || "width".equals((Object)var6_6) || "fit".equals((Object)var6_6))) {
                    if ("id".equals((Object)var6_6) == false) return false;
                    if (var5_5 instanceof AttrImpl == false) return false;
                    if (!"Text".equals(var5_5 = ((AttrImpl)var5_5).getValue()) && !"Image".equals(var5_5)) {
                        return false;
                    }
                }
                ++var2_3;
            } while (true);
            break;
        } while (true);
    }

    private void onFlatShowCreate() {
        this.getWindow().setFlags(8388608, 8388608);
        final Uri uri = this.getIntent().getData();
        this.setContentView(2130968619);
        this.setSubjectAndDate(uri);
        ((Button)this.findViewById(2131820651)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SlideshowActivity.this.finish();
            }
        });
        View view = this.findViewById(2131820654);
        if (Build.IS_CM_CUSTOMIZATION_TEST || Build.IS_CU_CUSTOMIZATION_TEST) {
            view.setVisibility(0);
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    view = new Intent((Context)SlideshowActivity.this, (Class)SlideshowActivity.class);
                    view.setData(uri);
                    view.putExtra("useslide", true);
                    SlideshowActivity.this.startActivity((Intent)view);
                }
            });
        }
        this.mFlatshowListView = (ListView)this.findViewById(2131820655);
        view = this.getLayoutInflater().inflate(2130968621, null);
        this.mFlatshowListView.addHeaderView(view);
        this.mFlatshowAdapter = new FlatshowAdapter(uri);
        this.mFlatshowListView.setAdapter((ListAdapter)this.mFlatshowAdapter);
        this.mSlideshowHeader = (SlideshowHeader)this.findViewById(2131820650);
        this.setOnStatusBarChangeListener(new OnStatusBarChangeListener(){

            public void onStatusBarHeightChange(int n) {
                if (SlideshowActivity.this.mStatusBarHeight == n) {
                    return;
                }
                SlideshowActivity.this.mSlideshowHeader.setStatusBarHeight(n);
                SlideshowActivity.this.mSlideshowHeader.setPadding(SlideshowActivity.this.mSlideshowHeader.getPaddingLeft(), SlideshowActivity.this.mSlideshowHeader.getPaddingTop() - SlideshowActivity.this.mStatusBarHeight + n, SlideshowActivity.this.mSlideshowHeader.getPaddingRight(), SlideshowActivity.this.mSlideshowHeader.getPaddingBottom());
                SlideshowActivity.this.mStatusBarHeight = n;
            }
        });
    }

    private void onSlideShowCreate() {
        this.setContentView(2130968708);
        Object object = this.getIntent().getData();
        try {
            object = SlideshowModel.createFromMessageUri((Context)this, (Uri)object);
        }
        catch (MmsException var1_2) {
            Log.e((String)"SlideshowActivity", (String)"Cannot present the slide show.", (Throwable)var1_2);
            this.finish();
            return;
        }
        this.mSlideView = (SlideView)this.findViewById(2131820655);
        PresenterFactory.getPresenter("SlideshowPresenter", (Context)this, this.mSlideView, (Model)object);
        this.gainFocus();
        this.mHandler.post(new Runnable((SlideshowModel)object){
            final /* synthetic */ SlideshowModel val$model;

            private boolean isRotating() {
                if (SlideshowActivity.this.mSmilPlayer.isPausedState() || SlideshowActivity.this.mSmilPlayer.isPlayingState() || SlideshowActivity.this.mSmilPlayer.isPlayedState()) {
                    return true;
                }
                return false;
            }

            @Override
            public void run() {
                SlideshowActivity.this.mSmilPlayer = SmilPlayer.getPlayer();
                SlideshowActivity.this.initMediaController();
                SlideshowActivity.this.mSlideView.setMediaController(SlideshowActivity.this.mMediaController);
                SlideshowActivity.this.mSmilDoc = SmilHelper.getDocument(this.val$model);
                if (SlideshowActivity.isMMSConformance(SlideshowActivity.this.mSmilDoc)) {
                    int n = 0;
                    int n2 = 0;
                    int n3 = 0;
                    int n4 = 0;
                    int n5 = 0;
                    int n6 = 0;
                    Model model = this.val$model.getLayout();
                    int n7 = n5;
                    int n8 = n6;
                    if (model != null) {
                        RegionModel regionModel = model.getImageRegion();
                        if (regionModel != null) {
                            n2 = regionModel.getLeft();
                            n4 = regionModel.getTop();
                        }
                        model = model.getTextRegion();
                        n = n2;
                        n3 = n4;
                        n7 = n5;
                        n8 = n6;
                        if (model != null) {
                            n7 = model.getLeft();
                            n8 = model.getTop();
                            n3 = n4;
                            n = n2;
                        }
                    }
                    SlideshowActivity.this.mSlideView.enableMMSConformanceMode(n7, n8, n, n3);
                }
                ((EventTarget)((Object)SlideshowActivity.this.mSmilDoc)).addEventListener("SimlDocumentEnd", SlideshowActivity.this, false);
                SlideshowActivity.this.mSmilPlayer.init(SlideshowActivity.this.mSmilDoc);
                if (this.isRotating()) {
                    SlideshowActivity.this.mSmilPlayer.reload();
                    return;
                }
                SlideshowActivity.this.mSmilPlayer.play();
            }
        });
    }

    private void pauseAndHideController() {
        if (this.mMediaController != null) {
            this.mMediaController.hide();
        }
        if (this.mSmilPlayer != null) {
            this.mSmilPlayer.pause();
        }
    }

    private void resetTextView() {
        if (this.mFlatshowListView != null && this.mFlatshowListView.getVisibility() != 8) {
            this.mFlatshowListView.requestFocus();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void setSubjectAndDate(Uri object) {
        Cursor cursor;
        block14 : {
            TextView textView;
            Object object2;
            block13 : {
                object2 = (TextView)this.findViewById(2131820652);
                textView = (TextView)this.findViewById(2131820653);
                cursor = SqliteWrapper.query((Context)this, (ContentResolver)this.getContentResolver(), (Uri)object, (String[])MMS_PROJECT, (String)null, (String[])null, (String)null);
                if (cursor == null) {
                    object2.setText(2131362002);
                    textView.setText((CharSequence)"");
                    return;
                }
                try {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        object = MessageUtils.extractEncStrFromCursor(cursor, 0, 1);
                        if (!TextUtils.isEmpty((CharSequence)object)) {
                            MessageUtils.showTextWithHighlight((TextView)object2, (String)object, this.mHighlight, 2131689541);
                        } else {
                            object2.setText(2131362002);
                        }
                        object = PreferenceManager.getDefaultSharedPreferences((Context)this);
                        long l = cursor.getLong(2) * 1000 + cursor.getLong(3);
                        long l2 = cursor.getLong(4) * 1000;
                        long l3 = cursor.getLong(5);
                        long l4 = l;
                        if (l2 > 0) {
                            l4 = l;
                            if ("1".equals((Object)MessageUtils.getDateType((SharedPreferences)object))) {
                                l4 = l2;
                            }
                        }
                        object = object2 = MessageUtils.getPreciseTimeStamp(l4, true);
                        if (object2 != null) {
                            object = object2;
                            if (l3 > 0) {
                                object = this.getResources().getString(2131362154, new Object[]{object2});
                            }
                        }
                        break block13;
                    }
                    object2.setText(2131362002);
                    textView.setText((CharSequence)"");
                    break block14;
                }
                catch (Throwable var1_2) {
                    cursor.close();
                    throw var1_2;
                }
            }
            object2 = object;
            if (object == null) {
                object2 = "";
            }
            textView.setText((CharSequence)object2);
        }
        cursor.close();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl;
        boolean bl2 = bl = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(motionEvent);
        if (bl) return bl2;
        if (this.mGestureDetector != null) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        try {
            return super.dispatchTouchEvent(motionEvent);
        }
        catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText((Context)this, (CharSequence)this.getString(2131362136), (int)0).show();
            return true;
        }
    }

    @Override
    public void handleEvent(final Event event) {
        this.mHandler.post(new Runnable(){

            @Override
            public void run() {
                if (event.getType().equals((Object)"SimlDocumentEnd")) {
                    SlideshowActivity.this.finish();
                }
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MessageUtils.loadFontSizeConfiguration((Context)this);
        this.mHandler = new Handler();
        bundle = this.getIntent();
        this.mHighlight = bundle.getStringExtra("highlight");
        boolean bl = bundle.getBooleanExtra("useslide", false);
        if ((Build.IS_CM_CUSTOMIZATION_TEST || Build.IS_CU_CUSTOMIZATION_TEST) && bl) {
            this.onSlideShowCreate();
            return;
        }
        this.onFlatShowCreate();
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        this.pauseAndHideController();
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                if (this.mSmilPlayer == null) return super.onKeyDown(n, keyEvent);
                if (this.mMediaController == null) return super.onKeyDown(n, keyEvent);
                this.mMediaController.show(0);
            }
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 24: 
            case 25: 
            case 164: {
                return super.onKeyDown(n, keyEvent);
            }
            case 4: 
            case 82: 
        }
        if (this.mSmilPlayer == null) return super.onKeyDown(n, keyEvent);
        if (!this.mSmilPlayer.isPausedState() && !this.mSmilPlayer.isPlayingState()) {
            if (!this.mSmilPlayer.isPlayedState()) return super.onKeyDown(n, keyEvent);
        }
        this.mSmilPlayer.stop();
        return super.onKeyDown(n, keyEvent);
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onMenuOpened(int n, Menu menu) {
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (this.mSmilDoc != null) {
            ((EventTarget)((Object)this.mSmilDoc)).removeEventListener("SimlDocumentEnd", this, false);
        }
        if (this.mSmilPlayer != null) {
            this.mSmilPlayer.pause();
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (Math.abs((float)f3) > 10.0f) {
            this.pauseAndHideController();
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.mMediaController == null) return false;
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
            return false;
        }
        this.mMediaController.show(0);
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    protected void onStart() {
        super.onStart();
        SmsTextSizeAdjust.getInstance().init(this, (Activity)this);
        SmsTextSizeAdjust.getInstance().refresh();
        this.resetTextView();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onStop() {
        super.onStop();
        if (this.mSmilPlayer != null) {
            if (this.isFinishing()) {
                this.mSmilPlayer.stop();
            } else {
                this.mSmilPlayer.stopWhenReload();
            }
            if (this.mMediaController != null) {
                this.mMediaController.hide();
            }
        }
        SmsTextSizeAdjust.clear(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mSmilPlayer != null && this.mMediaController != null) {
            this.mMediaController.show();
        }
        return false;
    }

    @Override
    public void setTextSize(float f2) {
        if (this.mFlatshowAdapter != null) {
            this.mFlatshowAdapter.setTextSize(f2);
        }
        if (this.mFlatshowListView != null && this.mFlatshowListView.getVisibility() == 0) {
            int n = this.mFlatshowListView.getCount();
            for (int i = 0; i < n; ++i) {
                View view = this.mFlatshowListView.getChildAt(i);
                if (view == null || !(view instanceof TextView)) continue;
                ((TextView)view).setTextSize(0, f2);
            }
        }
        if (this.mSlideView != null) {
            this.mSlideView.setTextSize(f2);
        }
    }

    private class FlatshowAdapter
    extends BaseAdapter {
        private SimplePduDoc mDoc;
        private ArrayList<Object> mElements;
        private String mMsgId;
        private float mTextSize;

        public FlatshowAdapter(Uri uri) {
            this.mElements = new ArrayList();
            this.mDoc = new SimplePduDoc((Context)SlideshowActivity.this);
            this.mDoc.load(uri);
            this.mMsgId = uri.getLastPathSegment();
            if (Build.IS_CM_CUSTOMIZATION || Build.IS_CU_CUSTOMIZATION) {
                this.loadElementsWithLayout();
                return;
            }
            this.loadElements();
        }

        private void addAudioNameView(LinearLayout linearLayout, TextView textView) {
            if (Build.IS_CM_CUSTOMIZATION_TEST && textView != null && linearLayout.getChildCount() == 1) {
                linearLayout.addView((View)textView);
            }
        }

        private TextView getAudioNameView(LinearLayout linearLayout) {
            if (Build.IS_CM_CUSTOMIZATION_TEST && linearLayout.getChildCount() > 1) {
                return (TextView)linearLayout.getChildAt(1);
            }
            return null;
        }

        private void loadElements() {
            for (int i = 0; i < this.mDoc.getPageSize(); ++i) {
                SimplePduPage simplePduPage = this.mDoc.getPage(i);
                if (simplePduPage != null) {
                    for (int j = 0; j < simplePduPage.getPartSize(); ++j) {
                        SimplePduPart simplePduPart = simplePduPage.getPart(j);
                        this.mElements.add((Object)simplePduPart);
                    }
                }
                this.mElements.add((Object)(i + 1));
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void loadElementsWithLayout() {
            boolean bl = this.mDoc.isLayoutImageBottom();
            ArrayList arrayList = new ArrayList();
            int n = 0;
            while (n < this.mDoc.getPageSize()) {
                SimplePduPage simplePduPage = this.mDoc.getPage(n);
                if (simplePduPage != null) {
                    for (int i = 0; i < simplePduPage.getPartSize(); ++i) {
                        SimplePduPart simplePduPart = simplePduPage.getPart(i);
                        if (bl && simplePduPart.getAttachmentType() == 0) {
                            this.mElements.add((Object)simplePduPart);
                            continue;
                        }
                        if (!(bl || simplePduPart.getAttachmentType() != 1 && simplePduPart.getAttachmentType() != 2)) {
                            this.mElements.add((Object)simplePduPart);
                            continue;
                        }
                        arrayList.add((Object)simplePduPart);
                    }
                    if (arrayList.size() > 0) {
                        this.mElements.addAll((Collection)arrayList);
                    }
                    arrayList.clear();
                }
                this.mElements.add((Object)(n + 1));
                ++n;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private TextView setAudioName(TextView textView, String string2, LayoutInflater layoutInflater) {
            if (!Build.IS_CM_CUSTOMIZATION_TEST) return textView;
            TextView textView2 = textView;
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                if (textView == null) {
                    textView2 = (TextView)layoutInflater.inflate(2130968622, null);
                }
                textView2.setVisibility(0);
                textView2.setText((CharSequence)string2);
                return textView2;
            }
            textView = textView2;
            if (textView2 == null) return textView;
            textView2.setVisibility(8);
            return textView2;
        }

        public int getCount() {
            return this.mElements.size();
        }

        public Object getItem(int n) {
            return this.mElements.get(n);
        }

        public long getItemId(int n) {
            return n;
        }

        public int getItemViewType(int n) {
            Object object = this.mElements.get(n);
            if (object instanceof Integer) {
                return 0;
            }
            if (object instanceof SimplePduPart) {
                if (((SimplePduPart)((Object)object)).getAttachmentType() == 0) {
                    return 1;
                }
                return 2;
            }
            throw new IllegalStateException("Cannot recognize flatshow view type");
        }

        /*
         * Enabled aggressive block sorting
         */
        public View getView(int n, View view, ViewGroup object) {
            object = this.mElements.get(n);
            LayoutInflater layoutInflater = LayoutInflater.from((Context)SlideshowActivity.this);
            if (object instanceof Integer) {
                n = (Integer)object;
                if (view == null) {
                    view = layoutInflater.inflate(2130968620, null, false);
                }
                int n2 = SlideshowActivity.this.getResources().getDimensionPixelOffset(2131427341);
                int n3 = SlideshowActivity.this.getResources().getDimensionPixelOffset(2131427342);
                view.setPadding(n2, n3, n2, n3);
                ((TextView)view.findViewById(2131820656)).setText((CharSequence)String.format((String)SlideshowActivity.this.getString(2131362134), (Object[])new Object[]{n}));
                return view;
            }
            if (!(object instanceof SimplePduPart)) {
                return null;
            }
            n = SlideshowActivity.this.getResources().getDimensionPixelOffset(2131427341);
            int n4 = SlideshowActivity.this.getResources().getDimensionPixelOffset(2131427342);
            final SimplePduPart simplePduPart = (SimplePduPart)((Object)object);
            LinearLayout linearLayout = null;
            Drawable drawable2 = null;
            ImageView imageView = null;
            Object object2 = null;
            TextView textView = null;
            switch (simplePduPart.getAttachmentType()) {
                default: {
                    object2 = linearLayout;
                    object = drawable2;
                    break;
                }
                case 0: {
                    object = view != null ? (TextView)view : (TextView)layoutInflater.inflate(2130968622, null);
                    MessageUtils.showTextWithHighlight((TextView)object, simplePduPart.getText(), SlideshowActivity.this.mHighlight, 2131689520);
                    object.setTextSize(0, this.mTextSize);
                    if (Build.IS_CM_CUSTOMIZATION) {
                        object.setLinksClickable(false);
                        object.setTextIsSelectable(false);
                        object.setOnClickListener(new View.OnClickListener(){

                            public void onClick(View object) {
                                ContextMenuDialog contextMenuDialog = new ContextMenuDialog((Context)SlideshowActivity.this);
                                object = ((TextView)object).getUrls();
                                HashSet hashSet = new HashSet();
                                if (object.length == 1) {
                                    object = MessageUtils.getUriInfo(object[0].getURL());
                                    MessageUtils.performUriOperation((Context)SlideshowActivity.this, (MessageUtils.UriInfo)object, null);
                                    return;
                                }
                                for (int i = 0; i < object.length; ++i) {
                                    Object object2 = object[i].getURL();
                                    if (hashSet.contains(object2)) continue;
                                    hashSet.add(object2);
                                    object2 = MessageUtils.getUriInfo((String)object2);
                                    contextMenuDialog.addMenuItem(object2.title, new Runnable((MessageUtils.UriInfo)object2){
                                        final /* synthetic */ MessageUtils.UriInfo val$info;

                                        @Override
                                        public void run() {
                                            MessageUtils.performUriOperation((Context)SlideshowActivity.this, this.val$info, null);
                                        }
                                    });
                                }
                                contextMenuDialog.setTitle(2131362018);
                                contextMenuDialog.show();
                            }

                        });
                    } else {
                        object.setTextIsSelectable(true);
                        object.setLinksClickable(true);
                    }
                    object2 = object;
                    object = drawable2;
                    break;
                }
                case 1: {
                    imageView = view != null ? (ImageView)((LinearLayout)view).getChildAt(0) : new ImageView((Context)SlideshowActivity.this);
                    object = simplePduPart.getImageNoCache(300, 300);
                    object2 = linearLayout;
                    break;
                }
                case 3: {
                    object = null;
                    if (Build.IS_CM_CUSTOMIZATION_TEST) {
                        object = simplePduPart.getPduPartName();
                    }
                    if (view != null) {
                        object2 = (LinearLayout)view;
                        imageView = (ImageView)object2.getChildAt(0);
                        object2 = this.getAudioNameView((LinearLayout)object2);
                    } else {
                        imageView = new ImageView((Context)SlideshowActivity.this);
                    }
                    drawable2 = SlideshowActivity.this.getResources().getDrawable(2130837620);
                    textView = this.setAudioName((TextView)object2, (String)object, layoutInflater);
                    object = drawable2;
                    object2 = linearLayout;
                    break;
                }
                case 2: {
                    imageView = view != null ? (ImageView)((LinearLayout)view).getChildAt(0) : new ImageView((Context)SlideshowActivity.this);
                    object = SlideshowActivity.this.getResources().getDrawable(2130837634);
                    object2 = linearLayout;
                    break;
                }
                case 4: {
                    imageView = view != null ? (ImageView)((LinearLayout)view).getChildAt(0) : new ImageView((Context)SlideshowActivity.this);
                    object = SlideshowActivity.this.getResources().getDrawable(2130837633);
                    object2 = linearLayout;
                    break;
                }
                case 5: {
                    imageView = view != null ? (ImageView)((LinearLayout)view).getChildAt(0) : new ImageView((Context)SlideshowActivity.this);
                    object = SlideshowActivity.this.getResources().getDrawable(2130837624);
                    object2 = linearLayout;
                }
            }
            if (imageView != null) {
                MessageUtils.setAttachmentImage(imageView, (Drawable)object, true);
                imageView.setClickable(true);
                imageView.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        try {
                            SlideshowActivity.this.startActivity(simplePduPart.getIntent());
                            return;
                        }
                        catch (ActivityNotFoundException var1_2) {
                            Toast.makeText((Context)SlideshowActivity.this, (CharSequence)SlideshowActivity.this.getString(2131362136), (int)0).show();
                            return;
                        }
                    }
                });
                imageView.setOnLongClickListener(new View.OnLongClickListener(){

                    public boolean onLongClick(View view) {
                        view = new ContextMenuDialog((Context)SlideshowActivity.this);
                        view.addMenuItem(2131362012, new Runnable(){

                            /*
                             * Enabled aggressive block sorting
                             */
                            @Override
                            public void run() {
                                String string2 = MessageUtils.saveMmsPartToDisk((Context)SlideshowActivity.this, simplePduPart, FlatshowAdapter.this.mMsgId);
                                AlertDialog.Builder builder = new AlertDialog.Builder((Context)SlideshowActivity.this);
                                builder.setTitle(2131362012);
                                builder.setIconAttribute(16843605);
                                if (string2 != null) {
                                    builder.setMessage((CharSequence)SlideshowActivity.this.getString(2131362013, new Object[]{string2}));
                                } else {
                                    builder.setMessage(2131362014);
                                }
                                builder.setNeutralButton(17039370, null);
                                builder.show();
                            }
                        });
                        view.show();
                        return true;
                    }

                });
                if (object != null) {
                    int n5 = object.getIntrinsicHeight();
                    int n6 = object.getIntrinsicWidth();
                    if (n6 <= 200) {
                        imageView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(n6 * 3 / 2, n5 * 3 / 2));
                    } else if (n6 > 300) {
                        imageView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(300, n5 * 300 / n6));
                    } else {
                        imageView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
                    }
                }
                if (view == null) {
                    object2 = new LinearLayout((Context)SlideshowActivity.this);
                    ((LinearLayout)object2).setGravity(3);
                    ((LinearLayout)object2).setOrientation(1);
                    ((LinearLayout)object2).addView((View)imageView);
                    this.addAudioNameView((LinearLayout)object2, textView);
                } else if (textView == null) {
                    object = this.getAudioNameView((LinearLayout)view);
                    object2 = view;
                    if (object != null) {
                        object.setVisibility(8);
                        object2 = view;
                    }
                } else {
                    this.addAudioNameView((LinearLayout)view, textView);
                    object2 = view;
                }
            }
            if (object2 != null) {
                object2.setPadding(n, n4, n, n4);
            }
            return object2;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public void setTextSize(float f2) {
            this.mTextSize = f2;
        }

    }

    private class SmilPlayerController
    implements MediaController.MediaPlayerControl {
        private final SmilPlayer mPlayer;

        public SmilPlayerController(SmilPlayer smilPlayer) {
            this.mPlayer = smilPlayer;
        }

        public boolean canPause() {
            return true;
        }

        public boolean canSeekBackward() {
            return true;
        }

        public boolean canSeekForward() {
            return true;
        }

        public int getAudioSessionId() {
            return 0;
        }

        public int getBufferPercentage() {
            return 100;
        }

        public int getCurrentPosition() {
            return this.mPlayer.getCurrentPosition();
        }

        public int getDuration() {
            return this.mPlayer.getDuration();
        }

        public boolean isPlaying() {
            if (this.mPlayer != null) {
                return this.mPlayer.isPlayingState();
            }
            return false;
        }

        public void pause() {
            if (this.mPlayer != null) {
                this.mPlayer.pause();
            }
        }

        public void seekTo(int n) {
        }

        public void start() {
            if (this.mPlayer != null) {
                this.mPlayer.start();
            }
        }
    }

}

