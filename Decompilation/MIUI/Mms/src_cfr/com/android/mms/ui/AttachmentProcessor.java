/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.app.DialogFragment
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Message
 *  android.os.Parcelable
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$Contacts
 *  android.provider.MediaStore
 *  android.provider.MediaStore$Audio
 *  android.provider.MediaStore$Audio$Media
 *  android.provider.MediaStore$Images
 *  android.provider.MediaStore$Images$Media
 *  android.provider.MediaStore$Video
 *  android.provider.MediaStore$Video$Media
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  android.webkit.MimeTypeMap
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TabWidget
 *  android.widget.TextView
 *  android.widget.Toast
 *  android.widget.ViewSwitcher
 *  com.google.android.mms.ContentType
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduPart
 *  java.io.File
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ProgressDialog
 *  miui.os.Build
 *  miui.widget.ScreenView
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.MmsConfig;
import com.android.mms.TempFileProvider;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.AudioModel;
import com.android.mms.model.ContactParser;
import com.android.mms.model.ImageModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.VideoModel;
import com.android.mms.ui.AttachmentScreenView;
import com.android.mms.ui.AttachmentTypeListAdapter;
import com.android.mms.ui.AttachmentView;
import com.android.mms.ui.MessageEditableActivityBase;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PhraseActivity;
import com.android.mms.ui.PhraseListAdapter;
import com.android.mms.ui.PickerActivity;
import com.android.mms.ui.SlideshowActivity;
import com.android.mms.ui.SlideshowEditActivity;
import com.android.mms.ui.StaticGridView;
import com.android.mms.util.SmileyParser;
import com.google.android.mms.ContentType;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;
import java.io.File;
import java.util.List;
import miui.app.ProgressDialog;
import miui.os.Build;
import miui.widget.ScreenView;

public class AttachmentProcessor {
    private static final int ATTACHMENT_PANEL_SCREEN_HEIGHT;
    private static final int[] ATTACHMENT_STATE_FOR_MMS;
    private static final int[] ATTACHMENT_STATE_FOR_SLIDESHOW;
    private static final String AUDIO_URI;
    private static final String IMAGE_URI;
    private static final String VIDEO_URI;
    private MessageEditableActivityBase mActivity;
    private ViewSwitcher mAttachmentPanel;
    private int mAttachmentPanelPreviousWidth = 0;
    private AttachmentTypeListAdapter mAttachmentTypeListAdapter;
    private AttachmentScreenView mAttachmentTypeScreens;
    private View[] mAttachmentTypes = new View[12];
    private int mCategoryStart;
    private PhraseListAdapter mPhraseListAdapter;
    private final MessageUtils.ResizeImageResultCallback mResizeImageCallback;
    private Animation mSlideLeftInAnimation;
    private Animation mSlideLeftOutAnimation;
    private Animation mSlideRightInAnimation;
    private Animation mSlideRightOutAnimation;
    private int mSmileyPanelPreviousWidth = 0;
    private ViewGroup mSmileyScreens;
    private TabWidget mSmileyTabWidget;

    static {
        VIDEO_URI = MediaStore.Video.Media.getContentUri((String)"external").toString();
        IMAGE_URI = MediaStore.Images.Media.getContentUri((String)"external").toString();
        AUDIO_URI = MediaStore.Audio.Media.getContentUri((String)"external").toString();
        ATTACHMENT_PANEL_SCREEN_HEIGHT = MmsApp.getApp().getResources().getDimensionPixelSize(2131427378);
        ATTACHMENT_STATE_FOR_SLIDESHOW = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0};
        ATTACHMENT_STATE_FOR_MMS = new int[]{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }

    public AttachmentProcessor(MessageEditableActivityBase messageEditableActivityBase) {
        this.mResizeImageCallback = new MessageUtils.ResizeImageResultCallback(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onResizeResult(PduPart pduPart, boolean bl) {
                int n;
                if (pduPart == null) {
                    AttachmentProcessor.this.handleAddAttachmentError(-1, 2131361995);
                    return;
                }
                MiuiPduPersister miuiPduPersister = MiuiPduPersister.getPduPersister((Context)AttachmentProcessor.this.mActivity);
                WorkingMessage workingMessage = AttachmentProcessor.this.mActivity.getWorkingMessage();
                Uri uri = workingMessage.saveAsMms(true);
                if (uri == null) {
                    n = -1;
                } else {
                    try {
                        pduPart = miuiPduPersister.persistPart(pduPart, ContentUris.parseId((Uri)uri));
                        n = workingMessage.setAttachment(1, (Uri)pduPart, bl);
                        Log.i((String)"AttachmentProcessor", (String)("ResizeImageResultCallback: dataUri=" + (Object)pduPart));
                    }
                    catch (MmsException var1_2) {
                        n = -1;
                    }
                }
                AttachmentProcessor.this.handleAddAttachmentError(n, 2131361995);
            }
        };
        this.mActivity = messageEditableActivityBase;
    }

    private void addAudio(Uri uri, boolean bl) {
        if (uri != null) {
            this.handleAddAttachmentError(this.mActivity.getWorkingMessage().setAttachment(3, uri, bl), 2131361994);
        }
    }

    private void addImage(Uri uri, boolean bl) {
        Log.i((String)"AttachmentProcessor", (String)("addImage: append=" + bl + ", uri=" + (Object)uri));
        int n = this.mActivity.getWorkingMessage().setAttachment(1, uri, bl);
        if (n == -4 || n == -2) {
            Log.i((String)"AttachmentProcessor", (String)("addImage: resize image " + (Object)uri));
            MessageUtils.resizeImageAsync((Context)this.mActivity, uri, this.mActivity.getHandler(), this.mResizeImageCallback, bl);
            return;
        }
        this.handleAddAttachmentError(n, 2131361995);
    }

    private void addVCard(Uri uri, boolean bl) {
        if (uri != null) {
            this.handleAddAttachmentError(this.mActivity.getWorkingMessage().setAttachment(4, uri, bl), 2131362248);
        }
    }

    private void addVideo(Uri uri, boolean bl) {
        if (uri != null) {
            this.handleAddAttachmentError(this.mActivity.getWorkingMessage().setAttachment(2, uri, bl), 2131361996);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void arrangeAttachmentTypeScreens(int var1_1) {
        var7_2 = this.getInt(2131558400);
        var8_3 = this.getInt(2131558401);
        var9_4 = this.getDimen(2131427380);
        var2_5 = this.getDimen(2131427383);
        var10_6 = var7_2 * var8_3;
        var11_7 = (this.mAttachmentTypeListAdapter.getCount() - 1) / var10_6 + 1;
        var12_8 = var11_7 + 1;
        var15_9 = new FrameLayout.LayoutParams(-2, -2, 1);
        var15_9.topMargin = var9_4 * var8_3 + AttachmentProcessor.ATTACHMENT_PANEL_SCREEN_HEIGHT - var2_5 >> 1;
        this.mAttachmentTypeScreens.setSeekBarPosition((FrameLayout.LayoutParams)var15_9);
        this.mAttachmentTypeScreens.removeAllScreens();
        this.arrangePhraseListScreen();
        var2_5 = 0;
        var13_10 = MessageUtils.shouldShowFestival((Context)this.mActivity);
        var14_11 = this.shouldShowSmiley();
        var4_12 = 1;
        do {
            if (var4_12 >= var12_8) {
                this.mAttachmentTypeScreens.setCurrentScreen(1);
                this.updateAttachmentTypeStates();
                return;
            }
            var15_9 = new StaticGridView((Context)this.mActivity, var8_3, var7_2, var1_1 / var7_2, var9_4);
            var5_14 = 0;
            block1 : do {
                if (var5_14 >= var10_6) ** GOTO lbl-1000
                var3_13 = var6_15 = var2_5;
                if (!var14_11) {
                    var3_13 = var6_15;
                    if (var2_5 >= 0) {
                        var3_13 = var6_15 + 1;
                    }
                }
                var6_15 = var3_13;
                if (!var13_10) {
                    var6_15 = var3_13;
                    if (var3_13 >= 5) {
                        var6_15 = var3_13 + 1;
                    }
                }
                if (var6_15 >= this.mAttachmentTypeListAdapter.getCount()) lbl-1000: // 2 sources:
                {
                    var3_13 = var2_5;
                    if (var4_12 != var12_8 - 1) break;
                    do {
                        var3_13 = var2_5;
                        if (var2_5 >= var10_6 * var11_7) break block1;
                        var15_9.addView(this.mAttachmentTypeListAdapter.inflateEmptyView());
                        ++var2_5;
                    } while (true);
                }
                var16_16 = this.mAttachmentTypeListAdapter.getView(var6_15, null, null);
                var16_16.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        AttachmentProcessor.this.onAttachmentTypeClick(var6_15);
                    }
                });
                var15_9.addView(var16_16);
                this.mAttachmentTypes[var6_15] = var16_16;
                ++var2_5;
                ++var5_14;
            } while (true);
            this.mAttachmentTypeScreens.addView((View)var15_9);
            ++var4_12;
            var2_5 = var3_13;
        } while (true);
    }

    private void arrangePhraseListScreen() {
        int n = this.getInt(2131558401);
        int n2 = this.getDimen(2131427380);
        ListView listView = new ListView((Context)this.mActivity);
        View view = View.inflate((Context)this.mActivity, (int)2130968683, (ViewGroup)null);
        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                view = new Intent((Context)AttachmentProcessor.this.mActivity, (Class)PhraseActivity.class);
                view.setAction("android.intent.action.PICK_ACTIVITY");
                AttachmentProcessor.this.mActivity.startActivityForResult((Intent)view, 113);
            }
        });
        listView.addFooterView(view);
        listView.setAdapter((ListAdapter)this.mPhraseListAdapter);
        this.mPhraseListAdapter.load();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> object, View view, int n, long l) {
                object = (String)AttachmentProcessor.this.mPhraseListAdapter.getItem(n);
                AttachmentProcessor.this.mActivity.insertPhraseSms((String)object);
            }
        });
        view = new FrameLayout.LayoutParams(-1, n * n2);
        this.mAttachmentTypeScreens.addView((View)listView, (ViewGroup.LayoutParams)view);
    }

    private void arrangeSmileyScreens(int n) {
        int n2 = this.getInt(2131558402);
        int n3 = this.getInt(2131558403);
        int n4 = this.getDimen(2131427379);
        int n5 = this.getDimen(2131427381);
        int n6 = this.getDimen(2131427383);
        for (int i = this.mCategoryStart; i < SmileyParser.CATEGORY_COUNT; ++i) {
            ScreenView screenView = (ScreenView)this.mSmileyScreens.getChildAt(i);
            int n7 = (SmileyParser.getSmileyCount(i) - 1) / (n2 * n3);
            screenView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, n4));
            Object object = new FrameLayout.LayoutParams(-2, -2, 49);
            object.topMargin = n5 * n3 + n4 - n6 >> 1;
            screenView.setSeekBarPosition((FrameLayout.LayoutParams)object);
            screenView.removeAllScreens();
            for (int j = 0; j < n7 + 1; ++j) {
                object = new StaticGridView((Context)this.mActivity, n3, n2, n / n2, n5);
                object.setInitializer(new SmileyGridInitializer(i, j, n3, n2));
                screenView.addView((View)object);
            }
            screenView.setCurrentScreen(0);
        }
    }

    private void backspace() {
        EditText editText = this.mActivity.getEditMessageFocus();
        editText.onKeyDown(67, new KeyEvent(0, 67));
        editText.onKeyUp(67, new KeyEvent(1, 67));
    }

    public static long computeAttachmentSizeLimit(SlideshowModel slideshowModel, int n) {
        long l;
        long l2 = l = (long)(MmsConfig.getMaxMessageSize() - 1024);
        if (slideshowModel != null) {
            l2 = l - (long)slideshowModel.getCurrentMessageSize() + (long)n;
        }
        return l2;
    }

    private void editSlideshow() {
        new AsyncTask<Void, Void, Uri>(){

            protected /* varargs */ Uri doInBackground(Void ... arrvoid) {
                return AttachmentProcessor.this.mActivity.getWorkingMessage().saveAsMms(false);
            }

            protected void onPostExecute(Uri uri) {
                Intent intent = new Intent((Context)AttachmentProcessor.this.mActivity, (Class)SlideshowEditActivity.class);
                intent.setData(uri);
                AttachmentProcessor.this.mActivity.startActivityForResult(intent, 106);
                uri = (DialogFragment)AttachmentProcessor.this.mActivity.getFragmentManager().findFragmentByTag("edit_progress_dialog");
                if (uri != null) {
                    uri.dismissAllowingStateLoss();
                }
            }

            protected void onPreExecute() {
                FragmentTransaction fragmentTransaction = AttachmentProcessor.this.mActivity.getFragmentManager().beginTransaction();
                ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
                progressDialogFragment.setCancelable(false);
                progressDialogFragment.show(fragmentTransaction, "edit_progress_dialog");
            }
        }.execute((Object[])new Void[0]);
    }

    private int getDimen(int n) {
        return this.mActivity.getResources().getDimensionPixelSize(n);
    }

    private int getInt(int n) {
        return this.mActivity.getResources().getInteger(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void gotoSmileyPanel(boolean bl) {
        if (!this.isOnAttachmentPanel()) {
            return;
        }
        if (this.mSmileyTabWidget == null) {
            this.mActivity.findViewById(2131820675).setVisibility(0);
            this.initSmileyPanelView(this.mActivity);
        }
        if (bl) {
            if (this.mSlideLeftInAnimation == null) {
                this.mSlideLeftInAnimation = AnimationUtils.loadAnimation((Context)this.mActivity, (int)2131034122);
            }
            if (this.mSlideLeftOutAnimation == null) {
                this.mSlideLeftOutAnimation = AnimationUtils.loadAnimation((Context)this.mActivity, (int)2131034123);
            }
            this.mAttachmentPanel.setInAnimation(this.mSlideLeftInAnimation);
            this.mAttachmentPanel.setOutAnimation(this.mSlideLeftOutAnimation);
        } else {
            this.mAttachmentPanel.setInAnimation(null);
            this.mAttachmentPanel.setOutAnimation(null);
        }
        this.mAttachmentPanel.showPrevious();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initAttachmentPanelView(MessageEditableActivityBase messageEditableActivityBase) {
        this.mAttachmentTypeScreens = (AttachmentScreenView)messageEditableActivityBase.findViewById(2131820674);
        this.mAttachmentTypeScreens.setScreenTransitionType(9);
        this.mAttachmentTypeListAdapter = new AttachmentTypeListAdapter((Context)messageEditableActivityBase);
        this.mPhraseListAdapter = new PhraseListAdapter((Context)messageEditableActivityBase, 2130968686);
        this.mAttachmentPanel = (ViewSwitcher)messageEditableActivityBase.findViewById(2131820673);
        int n = messageEditableActivityBase.getResources().getBoolean(2131492864) ? 0 : 3;
        this.mCategoryStart = n;
    }

    private void initSmileyPanelView(MessageEditableActivityBase messageEditableActivityBase) {
        int n;
        this.mSmileyTabWidget = (TabWidget)messageEditableActivityBase.findViewById(2131820713);
        this.mSmileyScreens = (ViewGroup)messageEditableActivityBase.findViewById(2131820707);
        for (n = this.mCategoryStart; n < SmileyParser.CATEGORY_COUNT; ++n) {
            this.mSmileyTabWidget.getChildAt(n).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    AttachmentProcessor.this.selectSmileyTab(n);
                }
            });
        }
        for (n = 0; n < this.mCategoryStart; ++n) {
            this.mSmileyTabWidget.getChildAt(n).setVisibility(8);
        }
        messageEditableActivityBase = messageEditableActivityBase.findViewById(2131820714);
        messageEditableActivityBase.setClickable(true);
        messageEditableActivityBase.setOnTouchListener((View.OnTouchListener)new BackTouchListener());
        this.selectSmileyTab(this.mCategoryStart);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void loadSmileyGridView(StaticGridView var1_1, int var2_2, int var3_3, int var4_4, int var5_5) {
        var6_6 = 0;
        while (var6_6 < var4_4 * var5_5) {
            var7_7 = var6_6 + var3_3 * var4_4 * var5_5;
            if (var7_7 >= SmileyParser.getSmileyCount(var2_2)) {
                return;
            }
            var8_8 = SmileyParser.getSmileyString(var2_2, var7_7);
            var9_9 = (ViewGroup)this.mActivity.getLayoutInflater().inflate(2130968712, null);
            var10_10 = (TextView)var9_9.findViewById(2131820889);
            var10_10.setText((CharSequence)var8_8);
            switch (var2_2) {
                case 0: 
                case 1: 
                case 2: {
                    var10_10.setTextSize(0, var10_10.getTextSize() * 3.0f);
                    ** break;
                }
                case 3: {
                    if (var8_8.length() < 7) {
                        var10_10.setTextSize(0, var10_10.getTextSize() * 1.3f);
                    }
                }
lbl17: // 5 sources:
                default: {
                    ** GOTO lbl21
                }
                case 4: 
            }
            var10_10.setTextSize(0, var10_10.getTextSize() * 2.0f);
lbl21: // 2 sources:
            var9_9.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    view = AttachmentProcessor.this.mActivity.getEditMessageFocus();
                    int n = view.getSelectionStart();
                    view.getText().insert(n, (CharSequence)var8_8);
                }
            });
            var1_1.addView((View)var9_9);
            ++var6_6;
        }
    }

    private void onAudioMenuClick() {
        String string = this.mActivity.getResources().getString(2131361980);
        String string2 = this.mActivity.getResources().getString(2131362232);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface object, int n) {
                object.dismiss();
                switch (n) {
                    default: {
                        return;
                    }
                    case 0: {
                        long l;
                        n = 0;
                        object = AttachmentProcessor.this.mActivity.getWorkingMessage().getSlideshow();
                        if (object != null) {
                            n = object.get(0).getSlideSize();
                        }
                        if ((l = AttachmentProcessor.computeAttachmentSizeLimit((SlideshowModel)object, n)) > 0) {
                            MessageUtils.recordSound((Context)AttachmentProcessor.this.mActivity, 105, l);
                            return;
                        }
                        Toast.makeText((Context)AttachmentProcessor.this.mActivity, (CharSequence)AttachmentProcessor.this.mActivity.getString(2131362147), (int)0).show();
                        return;
                    }
                    case 1: 
                }
                MessageUtils.selectAudio((Context)AttachmentProcessor.this.mActivity, 104);
            }
        };
        new AlertDialog.Builder((Context)this.mActivity).setIconAttribute(16843605).setCancelable(true).setItems((CharSequence[])new String[]{string, string2}, onClickListener).show();
    }

    private void onVideoMenuClick() {
        String string = this.mActivity.getResources().getString(2131361978);
        String string2 = this.mActivity.getResources().getString(2131362233);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface object, int n) {
                object.dismiss();
                switch (n) {
                    default: {
                        return;
                    }
                    case 0: {
                        long l;
                        n = 0;
                        object = AttachmentProcessor.this.mActivity.getWorkingMessage().getSlideshow();
                        if (object != null) {
                            n = object.get(0).getSlideSize();
                        }
                        if ((l = AttachmentProcessor.computeAttachmentSizeLimit((SlideshowModel)object, n)) > 0) {
                            MessageUtils.recordVideo((Context)AttachmentProcessor.this.mActivity, 103, l);
                            return;
                        }
                        Toast.makeText((Context)AttachmentProcessor.this.mActivity, (CharSequence)AttachmentProcessor.this.mActivity.getString(2131361852), (int)0).show();
                        return;
                    }
                    case 1: 
                }
                MessageUtils.selectVideo((Context)AttachmentProcessor.this.mActivity, 102);
            }
        };
        new AlertDialog.Builder((Context)this.mActivity).setIconAttribute(16843605).setCancelable(true).setItems((CharSequence[])new String[]{string, string2}, onClickListener).show();
    }

    private void resetAttachmentPanelToGridView() {
        if (this.mAttachmentTypeScreens.getScreenCount() > 0 && this.mAttachmentTypeScreens.getCurrentScreenIndex() < 1) {
            this.mAttachmentTypeScreens.setCurrentScreen(1);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void selectSmileyTab(int n) {
        this.mSmileyTabWidget.setCurrentTab(n);
        int n2 = 0;
        while (n2 < this.mSmileyScreens.getChildCount()) {
            View view = this.mSmileyScreens.getChildAt(n2);
            int n3 = n == n2 ? 0 : 8;
            view.setVisibility(n3);
            ++n2;
        }
    }

    private boolean shouldShowSmiley() {
        if (Build.checkRegion((String)"TW") || Build.checkRegion((String)"HK") || !Build.IS_INTERNATIONAL_BUILD) {
            return true;
        }
        return false;
    }

    private void viewAttachment() {
        new AsyncTask<Void, Void, Void>(){

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            protected /* varargs */ Void doInBackground(Void ... object) {
                WorkingMessage workingMessage = AttachmentProcessor.this.mActivity.getWorkingMessage();
                object = workingMessage.saveAsMms(false);
                Object object2 = workingMessage.getSlideshow();
                if (object2 == null) return null;
                if (object2.size() == 0) {
                    return null;
                }
                if (workingMessage.hasSlideshow()) {
                    workingMessage = new Intent((Context)AttachmentProcessor.this.mActivity, (Class)SlideshowActivity.class);
                    workingMessage.setData((Uri)object);
                    AttachmentProcessor.this.mActivity.startActivity((Intent)workingMessage);
                    return null;
                }
                object = object2.get(0);
                if (object.hasImage()) {
                    object = object.getImage();
                } else if (object.hasVideo()) {
                    object = object.getVideo();
                } else {
                    if (!object.hasAudio()) return null;
                    object = object.getAudio();
                }
                if (object == null) return null;
                workingMessage = new Intent("android.intent.action.VIEW");
                workingMessage.addFlags(1);
                object2 = object.getContentType();
                workingMessage.setDataAndType(object.getUri(), (String)object2);
                object = object.getSrc();
                if (!TextUtils.isEmpty((CharSequence)object)) {
                    workingMessage.putExtra("display_name", (String)object);
                }
                AttachmentProcessor.this.mActivity.startActivity((Intent)workingMessage);
                return null;
            }

            protected void onPostExecute(Void void_) {
                void_ = (DialogFragment)AttachmentProcessor.this.mActivity.getFragmentManager().findFragmentByTag("view_progress_dialog");
                if (void_ != null) {
                    void_.dismissAllowingStateLoss();
                }
            }

            protected void onPreExecute() {
                FragmentTransaction fragmentTransaction = AttachmentProcessor.this.mActivity.getFragmentManager().beginTransaction();
                ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
                progressDialogFragment.setCancelable(false);
                progressDialogFragment.show(fragmentTransaction, "view_progress_dialog");
            }
        }.execute((Object[])new Void[0]);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void addAttachment(String string, Uri uri, boolean bl) {
        boolean bl2 = false;
        if (uri == null) {
            LogTag.error("error: attachment uri is null", new Object[0]);
            return;
        }
        if (string == null || "*/*".equals((Object)string)) {
            bl2 = true;
        }
        String string2 = string;
        if (bl2) {
            if (uri.toString().startsWith(IMAGE_URI)) {
                string2 = "image/*";
            } else if (uri.toString().startsWith(VIDEO_URI)) {
                string2 = "video/*";
            } else if (uri.toString().startsWith(AUDIO_URI)) {
                string2 = "audio/*";
            } else {
                string2 = string;
                if (uri.toString().startsWith("file://")) {
                    uri.getLastPathSegment();
                    string = MimeTypeMap.getFileExtensionFromUrl((String)uri.toString());
                    string2 = string == null ? null : MimeTypeMap.getSingleton().getMimeTypeFromExtension(string.toLowerCase());
                }
            }
        }
        if (ContentType.isImageType((String)string2)) {
            this.addImage(uri, bl);
            return;
        }
        if (ContentType.isVideoType((String)string2)) {
            this.addVideo(uri, bl);
            return;
        }
        if (ContentType.isAudioType((String)string2) || "application/ogg".equalsIgnoreCase(string2)) {
            this.addAudio(uri, bl);
            return;
        }
        if ("text/x-vCard".equalsIgnoreCase(string2)) {
            this.addVCard(uri, bl);
            return;
        }
        this.handleAddAttachmentError(-3, 2131361997);
    }

    public void disableAttachmentPanel() {
        if (this.mAttachmentPanel != null) {
            this.mAttachmentPanel.setVisibility(8);
        }
    }

    public void enableAttachmentPanel() {
        if (this.mAttachmentPanel == null) {
            this.mActivity.findViewById(2131820802).setVisibility(0);
            this.initAttachmentPanelView(this.mActivity);
        }
        this.gotoAttachmentPanel(false);
        this.resetAttachmentPanelToGridView();
        this.mAttachmentPanel.setVisibility(0);
    }

    public int getAttachmentPanelHeight(int n, int n2) {
        if (!this.isAttachmentPanelVisible()) {
            return n2;
        }
        n = View.MeasureSpec.getSize((int)n);
        if (this.mAttachmentPanelPreviousWidth == 0) {
            this.mAttachmentPanelPreviousWidth = n;
            this.arrangeAttachmentTypeScreens(n);
        }
        if (this.isSmileyPanelVisible() && this.mSmileyPanelPreviousWidth == 0) {
            this.mSmileyPanelPreviousWidth = n;
            this.arrangeSmileyScreens(n);
        }
        return ATTACHMENT_PANEL_SCREEN_HEIGHT;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void gotoAttachmentPanel(boolean bl) {
        if (this.isOnAttachmentPanel()) {
            return;
        }
        if (bl) {
            if (this.mSlideRightInAnimation == null) {
                this.mSlideRightInAnimation = AnimationUtils.loadAnimation((Context)this.mActivity, (int)2131034124);
            }
            if (this.mSlideRightOutAnimation == null) {
                this.mSlideRightOutAnimation = AnimationUtils.loadAnimation((Context)this.mActivity, (int)2131034125);
            }
            this.mAttachmentPanel.setInAnimation(this.mSlideRightInAnimation);
            this.mAttachmentPanel.setOutAnimation(this.mSlideRightOutAnimation);
        } else {
            this.mAttachmentPanel.setInAnimation(null);
            this.mAttachmentPanel.setOutAnimation(null);
        }
        this.mAttachmentPanel.showNext();
    }

    public void handleAddAttachmentError(final int n, final int n2) {
        if (n == 0) {
            return;
        }
        this.mActivity.runOnUiThread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                String string;
                Object object = AttachmentProcessor.this.mActivity.getResources();
                String string2 = object.getString(n2);
                switch (n) {
                    default: {
                        throw new IllegalArgumentException("unknown error " + n);
                    }
                    case -1: {
                        object = object.getString(2131361853, new Object[]{string2});
                        Toast.makeText((Context)AttachmentProcessor.this.mActivity, (CharSequence)object, (int)0).show();
                        return;
                    }
                    case -3: {
                        string = object.getString(2131361847, new Object[]{string2});
                        object = object.getString(2131361848, new Object[]{string2});
                        break;
                    }
                    case -2: {
                        string = object.getString(2131361849, new Object[]{string2});
                        object = object.getString(2131361853, new Object[]{string2});
                        break;
                    }
                    case -4: {
                        string = object.getString(2131361854);
                        object = object.getString(2131361855);
                    }
                }
                MessageUtils.showErrorDialog((Context)AttachmentProcessor.this.mActivity, string, (String)object);
            }
        });
    }

    public void handleAttachentMessage(Message message) {
        switch (message.what) {
            default: {
                return;
            }
            case 2131820875: {
                this.editSlideshow();
                return;
            }
            case 2131820550: 
            case 2131820666: 
            case 2131820874: 
            case 2131820896: {
                this.viewAttachment();
                return;
            }
            case 2131820667: {
                this.onAttachmentTypeClick(3);
                return;
            }
            case 2131820897: {
                this.onAttachmentTypeClick(10);
                return;
            }
            case 2131820551: {
                this.onAttachmentTypeClick(9);
                return;
            }
            case 2131820552: 
            case 2131820668: 
            case 2131820876: 
            case 2131820895: 
            case 2131820898: 
        }
        this.mActivity.getWorkingMessage().removeAttachment(true);
    }

    public boolean isAttachmentPanelVisible() {
        if (this.mAttachmentPanel != null && this.mAttachmentPanel.getVisibility() != 8) {
            return true;
        }
        return false;
    }

    public boolean isOnAttachmentPanel() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.mAttachmentPanel != null) {
            bl2 = bl;
            if (this.mAttachmentPanel.getDisplayedChild() == 0) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public boolean isSmileyPanelVisible() {
        if (this.mSmileyTabWidget != null && this.mSmileyTabWidget.getVisibility() != 8) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onActivityResult(int n, int n2, Intent object) {
        WorkingMessage workingMessage = this.mActivity.getWorkingMessage();
        if (workingMessage.isFakeMmsForDraft()) {
            workingMessage.removeFakeMmsForDraft();
        }
        if (n2 != -1) {
            return;
        }
        switch (n) {
            case 106: {
                if (object == null || (workingMessage = this.mActivity.getWorkingMessage()) == null) break;
                workingMessage.loadFromUri(object.getData(), false);
                this.mActivity.getAttachmentView().update(workingMessage);
                this.mActivity.drawBottomPanel();
                this.updateAttachmentTypeStates();
                break;
            }
            case 101: {
                this.addImage(Uri.fromFile((File)new File(TempFileProvider.getScrapPath((Context)this.mActivity))), false);
                break;
            }
            case 100: {
                if (object == null) break;
                this.addImage(object.getData(), false);
                break;
            }
            case 102: 
            case 103: {
                if (object == null) break;
                this.addVideo(object.getData(), false);
                break;
            }
            case 104: {
                if (Settings.System.DEFAULT_RINGTONE_URI.equals(object = (Uri)object.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI"))) break;
                this.addAudio((Uri)object, false);
                break;
            }
            case 105: {
                if (object == null) break;
                this.addAudio(object.getData(), false);
                break;
            }
            case 109: {
                String string;
                if (object == null) break;
                workingMessage = this.mActivity.getTextEditor().getText();
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty((CharSequence)((Object)workingMessage)) && !TextUtils.isEmpty((CharSequence)(string = workingMessage.toString().trim())) && string.charAt(string.length() - 1) != '\r') {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(ContactParser.getContactParser((Context)this.mActivity).getContactFromIntent((Intent)object));
                workingMessage.insert(this.mActivity.getTextEditor().getSelectionStart(), (CharSequence)stringBuilder);
                break;
            }
            case 114: {
                object = Uri.encode((String)((String)object.getData().getPathSegments().get(2)));
                this.addVCard(ContactsContract.Contacts.CONTENT_VCARD_URI.buildUpon().appendPath((String)object).appendQueryParameter("nophoto", "true").build(), false);
                break;
            }
            case 113: {
                if (this.mPhraseListAdapter == null) break;
                this.mPhraseListAdapter.load();
                break;
            }
            case 112: {
                if (object == null) break;
                workingMessage = (Uri)object.getParcelableExtra("msg_uri");
                String string = object.getStringExtra("subject");
                if (workingMessage != null) {
                    object = this.mActivity.getWorkingMessage();
                    if (object != null && !object.isWorthSaving() && !this.mActivity.containChenghu()) {
                        object.loadFromUri((Uri)workingMessage, true);
                        object.setSubject(string, false);
                        this.mActivity.getAttachmentView().update((WorkingMessage)object);
                        this.mActivity.drawTopPanel();
                        this.mActivity.drawBottomPanel();
                        this.updateAttachmentTypeStates();
                        return;
                    }
                    object = new AlertDialog.Builder((Context)this.mActivity);
                    object.setMessage(2131362255);
                    object.setPositiveButton(17039379, new DialogInterface.OnClickListener((Uri)workingMessage, string){
                        final /* synthetic */ String val$subject;
                        final /* synthetic */ Uri val$uri;

                        public void onClick(DialogInterface dialogInterface, int n) {
                            WorkingMessage workingMessage = AttachmentProcessor.this.mActivity.getWorkingMessage();
                            if (workingMessage != null) {
                                workingMessage.removeAttachment(false);
                                AttachmentProcessor.this.mActivity.getTextEditor().setText((CharSequence)"");
                                workingMessage.loadFromUri(this.val$uri, true, true);
                                workingMessage.setSubject(this.val$subject, true);
                                AttachmentProcessor.this.mActivity.getAttachmentView().update(workingMessage);
                                AttachmentProcessor.this.mActivity.drawTopPanel();
                                AttachmentProcessor.this.mActivity.drawBottomPanel();
                                AttachmentProcessor.this.updateAttachmentTypeStates();
                            }
                            dialogInterface.dismiss();
                        }
                    });
                    object.setNegativeButton(17039360, new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int n) {
                            dialogInterface.dismiss();
                        }
                    });
                    object.show();
                    return;
                }
            }
            case 111: {
                if (object == null) break;
                workingMessage = this.mActivity.getTextEditor();
                workingMessage.getText().insert(workingMessage.getSelectionStart(), (CharSequence)object.getStringExtra("android.intent.extra.shortcut.NAME"));
            }
        }
        object = this.mActivity;
        boolean bl = this.mActivity.isVisible((View)this.mActivity.mTextEditor) || this.mActivity.isVisible((View)this.mActivity.mSubjectTextEditor);
        object.delayedShowSoftKeyboard(bl);
    }

    public void onAttachmentTypeClick(int n) {
        switch (n) {
            default: {
                return;
            }
            case 0: {
                this.gotoSmileyPanel(true);
                return;
            }
            case 1: {
                if (MessageUtils.isSendingContactByVCard(this.mActivity.mSharedPrefs)) {
                    Intent intent = new Intent("android.intent.action.PICK");
                    intent.setType("vnd.android.cursor.dir/contact");
                    intent.setPackage(MessageUtils.getContactsPackageName());
                    this.mActivity.startActivityForResult(intent, 114);
                    return;
                }
                Intent intent = new Intent("android.intent.action.PICK");
                intent.setType("vnd.android.cursor.dir/contact_pick_single");
                intent.putExtra("android.intent.extra.include_unknown_numbers", true);
                intent.setPackage(MessageUtils.getContactsPackageName());
                this.mActivity.startActivityForResult(intent, 109);
                return;
            }
            case 2: {
                this.mActivity.insertChenghu();
                return;
            }
            case 3: {
                this.mActivity.confirmRemovingChenghu(new Runnable(){

                    @Override
                    public void run() {
                        MessageUtils.selectImage((Context)AttachmentProcessor.this.mActivity, 100);
                    }
                }, null);
                return;
            }
            case 4: {
                this.mActivity.confirmRemovingChenghu(new Runnable(){

                    @Override
                    public void run() {
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra("output", (Parcelable)TempFileProvider.SCRAP_CONTENT_URI);
                        AttachmentProcessor.this.mActivity.startActivityForResult(intent, 101);
                    }
                }, null);
                return;
            }
            case 6: {
                Intent intent = new Intent((Context)this.mActivity, (Class)PickerActivity.class);
                intent.setAction("android.intent.action.PICK");
                intent.putExtra("pick_type", 0);
                this.mActivity.startActivityForResult(intent, 112);
                return;
            }
            case 10: {
                this.mActivity.confirmRemovingChenghu(new Runnable(){

                    @Override
                    public void run() {
                        AttachmentProcessor.this.onVideoMenuClick();
                    }
                }, null);
                return;
            }
            case 5: {
                Intent intent = new Intent((Context)this.mActivity, (Class)PickerActivity.class);
                intent.setAction("android.intent.action.PICK");
                intent.putExtra("pick_type", 1);
                this.mActivity.startActivityForResult(intent, 111);
                return;
            }
            case 9: {
                this.mActivity.confirmRemovingChenghu(new Runnable(){

                    @Override
                    public void run() {
                        AttachmentProcessor.this.onAudioMenuClick();
                    }
                }, null);
                return;
            }
            case 11: {
                this.mActivity.confirmRemovingChenghu(new Runnable(){

                    @Override
                    public void run() {
                        AttachmentProcessor.this.editSlideshow();
                    }
                }, null);
                return;
            }
            case 7: {
                if (this.mActivity.getWorkingMessage().getTimeToSend() == 0) {
                    this.mActivity.setTiming();
                    return;
                }
                this.mActivity.cancelTiming();
                return;
            }
            case 8: 
        }
        this.mActivity.confirmRemovingChenghu(new Runnable(){

            @Override
            public void run() {
                AttachmentProcessor.this.mActivity.toggleSubject();
            }
        }, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void updateAttachmentTypeStates() {
        if (!this.mActivity.getWorkingMessage().hasSlideshow()) {
            if (!this.mActivity.getWorkingMessage().requiresMms()) {
                int n = 0;
                while (n < this.mAttachmentTypes.length) {
                    if (this.mAttachmentTypes[n] != null) {
                        this.mAttachmentTypes[n].setEnabled(true);
                    }
                    ++n;
                }
                return;
            }
        } else {
            int n = 0;
            while (n < this.mAttachmentTypes.length) {
                if (this.mAttachmentTypes[n] != null) {
                    View view = this.mAttachmentTypes[n];
                    boolean bl = ATTACHMENT_STATE_FOR_SLIDESHOW[n] > 0;
                    view.setEnabled(bl);
                }
                ++n;
            }
            return;
        }
        int n = 0;
        while (n < this.mAttachmentTypes.length) {
            if (this.mAttachmentTypes[n] != null) {
                View view = this.mAttachmentTypes[n];
                boolean bl = ATTACHMENT_STATE_FOR_MMS[n] > 0;
                view.setEnabled(bl);
            }
            ++n;
        }
    }

    private class BackTouchListener
    implements View.OnTouchListener {
        private int mCurrentMessageToken;
        private boolean mIsInside;

        private BackTouchListener() {
            this.mCurrentMessageToken = 0;
            this.mIsInside = false;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getActionMasked()) {
                default: {
                    return false;
                }
                case 0: {
                    AttachmentProcessor.this.backspace();
                    ++this.mCurrentMessageToken;
                    this.mIsInside = true;
                    AttachmentProcessor.access$100((AttachmentProcessor)AttachmentProcessor.this).mHandler.postDelayed((Runnable)new BackspaceExecutor(this.mCurrentMessageToken), 500);
                    return false;
                }
                case 1: 
                case 3: {
                    ++this.mCurrentMessageToken;
                    return false;
                }
                case 2: 
            }
            int n = (int)motionEvent.getX();
            int n2 = (int)motionEvent.getY();
            if (n >= 0 && n < view.getWidth() && n2 >= 0 && n2 < view.getHeight()) {
                this.mIsInside = true;
                return false;
            }
            this.mIsInside = false;
            return false;
        }

        private class BackspaceExecutor
        implements Runnable {
            private int mMessageToken;

            BackspaceExecutor(int n) {
                this.mMessageToken = n;
            }

            @Override
            public void run() {
                if (this.mMessageToken == BackTouchListener.this.mCurrentMessageToken) {
                    AttachmentProcessor.access$100((AttachmentProcessor)AttachmentProcessor.this).mHandler.postDelayed((Runnable)this, 100);
                    if (BackTouchListener.this.mIsInside) {
                        AttachmentProcessor.this.backspace();
                    }
                }
            }
        }

    }

    public static class ProgressDialogFragment
    extends DialogFragment {
        public Dialog onCreateDialog(Bundle bundle) {
            bundle = this.getActivity();
            ProgressDialog progressDialog = new ProgressDialog((Context)bundle);
            progressDialog.setMessage((CharSequence)bundle.getString(2131362161));
            return progressDialog;
        }
    }

    private class SmileyGridInitializer
    implements StaticGridView.Initializer {
        private int mCategory;
        private int mColumnCount;
        private int mRowCount;
        private int mScreenIndex;

        SmileyGridInitializer(int n, int n2, int n3, int n4) {
            this.mCategory = n;
            this.mScreenIndex = n2;
            this.mRowCount = n3;
            this.mColumnCount = n4;
        }

        @Override
        public void onInitialize(StaticGridView staticGridView) {
            AttachmentProcessor.this.loadSmileyGridView(staticGridView, this.mCategory, this.mScreenIndex, this.mRowCount, this.mColumnCount);
        }
    }

}

