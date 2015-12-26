/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.text.Editable
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.SubMenu
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.Window
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.Toast
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.PduPart
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.app.ActionBar
 *  miui.app.Activity
 *  miui.os.Build
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.MmsConfig;
import com.android.mms.ResolutionException;
import com.android.mms.TempFileProvider;
import com.android.mms.UnsupportContentTypeException;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.ui.AttachmentProcessor;
import com.android.mms.ui.BasicSlideEditorView;
import com.android.mms.ui.EditSlideDurationActivity;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SlideshowEditor;
import com.android.mms.ui.SlideshowPresenter;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.ui.ViewInterface;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;

public class SlideEditorActivity
extends Activity
implements ISmsTextSizeAdjustHost {
    private ActionBar mActionBar;
    private Context mContext;
    private boolean mDataLoaded = false;
    private boolean mDirty;
    private ImageView mDone;
    private final View.OnClickListener mDoneClickListener;
    private Handler mHandler;
    private InputMethodManager mInputMethodManager;
    private AsyncTask<Void, Void, Void> mLoadDataTask;
    private final IModelChangedObserver mModelChangedObserver;
    private ImageView mNextSlide;
    private String mOldText;
    private final View.OnClickListener mOnNavigateBackward;
    private final View.OnClickListener mOnNavigateForward;
    private final View.OnClickListener mOnRemoveSlide;
    private final View.OnClickListener mOnReplaceImage;
    private final BasicSlideEditorView.OnTextChangedListener mOnTextChangedListener;
    private int mPosition;
    private ImageView mPreSlide;
    private SlideshowPresenter mPresenter;
    private ImageView mRemoveSlide;
    private final MessageUtils.ResizeImageResultCallback mResizeImageCallback;
    private BasicSlideEditorView mSlideView;
    private SlideshowEditor mSlideshowEditor;
    private SlideshowModel mSlideshowModel;
    private EditText mTextEditor;
    private Uri mUri;

    public SlideEditorActivity() {
        this.mModelChangedObserver = new IModelChangedObserver(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onModelChanged(Model object, boolean bl) {
                object = SlideEditorActivity.this;
                synchronized (object) {
                    SlideEditorActivity.this.mDirty = true;
                }
                SlideEditorActivity.this.setResult(-1);
            }
        };
        this.mOnRemoveSlide = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                if (!SlideEditorActivity.this.mDataLoaded) {
                    Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
                    return;
                } else {
                    if (SlideEditorActivity.this.mPosition < 0 || SlideEditorActivity.this.mPosition >= SlideEditorActivity.this.mSlideshowModel.size()) return;
                    {
                        SlideEditorActivity.this.mSlideshowEditor.removeSlide(SlideEditorActivity.this.mPosition);
                        int n = SlideEditorActivity.this.mSlideshowModel.size();
                        if (n <= 0) {
                            SlideEditorActivity.this.finish();
                            return;
                        }
                        if (SlideEditorActivity.this.mPosition >= n) {
                            SlideEditorActivity.access$810(SlideEditorActivity.this);
                        }
                        SlideEditorActivity.this.showCurrentSlide();
                        return;
                    }
                }
            }
        };
        this.mOnTextChangedListener = new BasicSlideEditorView.OnTextChangedListener(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onTextChanged(String string2) {
                if (!SlideEditorActivity.this.mDataLoaded) {
                    Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
                    return;
                }
                if (SlideEditorActivity.this.isFinishing()) return;
                try {
                    SlideEditorActivity.this.mSlideshowEditor.changeText(SlideEditorActivity.this.mPosition, string2);
                    if (!Build.IS_CM_CUSTOMIZATION_TEST) return;
                    {
                        SlideEditorActivity.this.mOldText = string2;
                        return;
                    }
                }
                catch (ExceedMessageSizeException var1_2) {
                    if (!Build.IS_CM_CUSTOMIZATION_TEST) return;
                    Toast.makeText((Context)SlideEditorActivity.this, (int)2131361878, (int)0).show();
                    SlideEditorActivity.this.mTextEditor.setText((CharSequence)SlideEditorActivity.this.mOldText);
                    SlideEditorActivity.this.mTextEditor.setSelection(SlideEditorActivity.this.mOldText.length());
                    return;
                }
            }
        };
        this.mOnReplaceImage = new View.OnClickListener(){

            public void onClick(View object) {
                if (!SlideEditorActivity.this.mDataLoaded) {
                    Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
                }
                if ((object = SlideEditorActivity.this.mSlideshowModel.get(SlideEditorActivity.this.mPosition)) != null && object.hasVideo()) {
                    Toast.makeText((Context)SlideEditorActivity.this, (int)2131361871, (int)0).show();
                    return;
                }
                object = new Intent("android.intent.action.GET_CONTENT", null);
                object.setType("image/*");
                SlideEditorActivity.this.startActivityForResult((Intent)object, 1);
            }
        };
        this.mOnNavigateBackward = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                if (!SlideEditorActivity.this.mDataLoaded) {
                    Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
                    return;
                } else {
                    if (SlideEditorActivity.this.mPosition <= 0) return;
                    {
                        SlideEditorActivity.access$810(SlideEditorActivity.this);
                        SlideEditorActivity.this.showCurrentSlide();
                        return;
                    }
                }
            }
        };
        this.mOnNavigateForward = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                if (!SlideEditorActivity.this.mDataLoaded) {
                    Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
                    return;
                } else {
                    if (SlideEditorActivity.this.mPosition >= SlideEditorActivity.this.mSlideshowModel.size() - 1) return;
                    {
                        SlideEditorActivity.access$808(SlideEditorActivity.this);
                        SlideEditorActivity.this.showCurrentSlide();
                        return;
                    }
                }
            }
        };
        this.mDoneClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                if (!SlideEditorActivity.this.mDataLoaded) {
                    Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
                    return;
                }
                view = new Intent();
                view.putExtra("done", true);
                SlideEditorActivity.this.setResult(-1, (Intent)view);
                SlideEditorActivity.this.finish();
            }
        };
        this.mResizeImageCallback = new MessageUtils.ResizeImageResultCallback(){

            @Override
            public void onResizeResult(PduPart pduPart, boolean bl) {
                SlideEditorActivity slideEditorActivity = SlideEditorActivity.this;
                if (pduPart == null) {
                    Toast.makeText((Context)SlideEditorActivity.this, (CharSequence)SlideEditorActivity.this.getResourcesString(2131361853, SlideEditorActivity.this.getPictureString()), (int)0).show();
                    return;
                }
                try {
                    SlideEditorActivity.this.mSlideshowEditor.checkMessageSize(SlideEditorActivity.this.mPosition, pduPart.getData().length);
                    long l = ContentUris.parseId((Uri)SlideEditorActivity.this.mUri);
                    pduPart = MiuiPduPersister.getPduPersister((Context)slideEditorActivity).persistPart(pduPart, l);
                    SlideEditorActivity.this.mSlideshowEditor.changeImage(SlideEditorActivity.this.mPosition, (Uri)pduPart);
                    return;
                }
                catch (MmsException var1_2) {
                    SlideEditorActivity.this.notifyUser("add picture failed");
                    Toast.makeText((Context)SlideEditorActivity.this, (CharSequence)SlideEditorActivity.this.getResourcesString(2131361853, SlideEditorActivity.this.getPictureString()), (int)0).show();
                    return;
                }
                catch (UnsupportContentTypeException var1_3) {
                    MessageUtils.showErrorDialog((Context)SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361847, SlideEditorActivity.this.getPictureString()), SlideEditorActivity.this.getResourcesString(2131361848, SlideEditorActivity.this.getPictureString()));
                    return;
                }
                catch (ResolutionException var1_4) {
                    MessageUtils.showErrorDialog((Context)SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361854), SlideEditorActivity.this.getResourcesString(2131361855));
                    return;
                }
                catch (ExceedMessageSizeException var1_5) {
                    MessageUtils.showErrorDialog((Context)SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361849), SlideEditorActivity.this.getResourcesString(2131361853, SlideEditorActivity.this.getPictureString()));
                    return;
                }
            }
        };
    }

    static /* synthetic */ int access$808(SlideEditorActivity slideEditorActivity) {
        int n = slideEditorActivity.mPosition;
        slideEditorActivity.mPosition = n + 1;
        return n;
    }

    static /* synthetic */ int access$810(SlideEditorActivity slideEditorActivity) {
        int n = slideEditorActivity.mPosition;
        slideEditorActivity.mPosition = n - 1;
        return n;
    }

    private void enablePreAndNextButton() {
        int n = this.mSlideshowModel.size();
        if (n == 1) {
            this.mPreSlide.setEnabled(false);
            this.mNextSlide.setEnabled(false);
            return;
        }
        if (this.mPosition == 0) {
            this.mPreSlide.setEnabled(false);
            this.mNextSlide.setEnabled(true);
            return;
        }
        if (this.mPosition == n - 1) {
            this.mPreSlide.setEnabled(true);
            this.mNextSlide.setEnabled(false);
            return;
        }
        this.mPreSlide.setEnabled(true);
        this.mNextSlide.setEnabled(true);
    }

    private String getAudioString() {
        return this.getResourcesString(2131361994);
    }

    private Handler getHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        return this.mHandler;
    }

    private String getPictureString() {
        return this.getResourcesString(2131361995);
    }

    private String getResourcesString(int n) {
        return this.getResources().getString(n);
    }

    private String getResourcesString(int n, String string2) {
        return this.getResources().getString(n, new Object[]{string2});
    }

    private String getVideoString() {
        return this.getResourcesString(2131361996);
    }

    private void hideSoftKeyboard() {
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager)this.getSystemService("input_method");
        }
        this.mInputMethodManager.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }

    private void initActivityState(Bundle bundle, Intent intent) {
        if (bundle != null) {
            this.mUri = (Uri)bundle.getParcelable("message_uri");
            this.mPosition = bundle.getInt("slide_index", 0);
            return;
        }
        this.mUri = intent.getData();
        this.mPosition = intent.getIntExtra("slide_index", 0);
    }

    private void notifyUser(String string2) {
    }

    private void previewSlideshow() {
        if (!this.mDataLoaded) {
            Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
            return;
        }
        this.syncData();
        MessageUtils.viewMmsMessageAttachment((Context)this, this.mUri, this.mSlideshowModel);
    }

    private void showCurrentSlide() {
        this.mPresenter.setLocation(this.mPosition);
        this.mPresenter.present();
        this.updateTitle();
        this.enablePreAndNextButton();
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mOldText = this.mTextEditor.getText().toString();
        }
    }

    private void showDurationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setIcon(2130837762);
        String string2 = this.getResources().getString(2131361883);
        builder.setTitle((CharSequence)(string2 + (this.mPosition + 1) + "/" + this.mSlideshowModel.size()));
        builder.setItems(2131230735, new DialogInterface.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(DialogInterface dialogInterface, int n) {
                if (n >= 0 && n < 10) {
                    SlideEditorActivity.this.mSlideshowEditor.changeDuration(SlideEditorActivity.this.mPosition, (n + 1) * 1000);
                } else {
                    Intent intent = new Intent((Context)SlideEditorActivity.this, (Class)EditSlideDurationActivity.class);
                    intent.putExtra("slide_index", SlideEditorActivity.this.mPosition);
                    intent.putExtra("slide_total", SlideEditorActivity.this.mSlideshowModel.size());
                    intent.putExtra("dur", SlideEditorActivity.this.mSlideshowModel.get(SlideEditorActivity.this.mPosition).getDuration() / 1000);
                    SlideEditorActivity.this.startActivityForResult(intent, 6);
                }
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void showLayoutSelectorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setIcon(2130837763);
        String string2 = this.getResources().getString(2131361884);
        builder.setTitle((CharSequence)(string2 + (this.mPosition + 1) + "/" + this.mSlideshowModel.size()));
        string2 = this.getResources().getString(2131361983);
        String string3 = this.getResources().getString(2131361982);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(DialogInterface dialogInterface, int n) {
                switch (n) {
                    case 0: {
                        SlideEditorActivity.this.mSlideshowEditor.changeLayout(1);
                    }
                    default: {
                        break;
                    }
                    case 1: {
                        SlideEditorActivity.this.mSlideshowEditor.changeLayout(0);
                    }
                }
                dialogInterface.dismiss();
            }
        };
        builder.setItems((CharSequence[])new String[]{string2, string3}, onClickListener);
        builder.show();
    }

    private void showSoftKeyboard() {
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager)this.getSystemService("input_method");
        }
        this.mInputMethodManager.showSoftInput((View)this.mTextEditor, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void syncData() {
        if (!this.mDataLoaded) {
            Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
            return;
        }
        synchronized (this) {
            boolean bl = this.mDirty;
            if (bl) {
                try {
                    PduBody pduBody = this.mSlideshowModel.toPduBody();
                    MiuiPduPersister.getPduPersister((Context)this).updateParts(this.mUri, pduBody);
                    this.mSlideshowModel.sync(pduBody);
                }
                catch (MmsException var2_3) {
                    Log.e((String)"SlideEditorActivity", (String)("Cannot update the message: " + (Object)this.mUri), (Throwable)var2_3);
                }
                this.mDirty = false;
            }
            return;
        }
    }

    private void updateTitle() {
        if (this.mDataLoaded) {
            this.mActionBar.setTitle((CharSequence)this.getString(2131361829, new Object[]{this.mPosition + 1, this.mSlideshowModel.size()}));
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl;
        boolean bl2 = bl = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(motionEvent);
        if (!bl) {
            bl2 = super.dispatchTouchEvent(motionEvent);
        }
        return bl2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected void onActivityResult(int var1_1, int var2_2, Intent var3_3) {
        if (var2_2 != -1) {
            return;
        }
        switch (var1_1) {
            default: {
                return;
            }
            case 0: {
                this.mSlideshowEditor.changeText(this.mPosition, var3_3.getAction());
                return;
            }
            case 2: {
                var5_19 = null;
                var4_20 = null;
                try {
                    var3_3 = TempFileProvider.renameScrapFile(".jpg", Integer.toString((int)this.mPosition), (Context)this);
                    if (var3_3 != null) ** GOTO lbl17
                    var1_1 = 1;
                    ** GOTO lbl24
lbl17: // 1 sources:
                    var4_20 = var3_3;
                    var5_19 = var3_3;
                    this.mSlideshowEditor.changeImage(this.mPosition, (Uri)var3_3);
                    return;
                }
                catch (MmsException var3_4) {
                    Log.e((String)"SlideEditorActivity", (String)"add image failed", (Throwable)var3_4);
                    var1_1 = 1;
                }
lbl24: // 2 sources:
                if (var1_1 == 0) return;
                this.notifyUser("add picture failed");
                Toast.makeText((Context)this, (CharSequence)this.getResourcesString(2131361853, this.getPictureString()), (int)0).show();
                return;
                catch (UnsupportContentTypeException var3_5) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361847, this.getPictureString()), this.getResourcesString(2131361848, this.getPictureString()));
                    return;
                }
                catch (ResolutionException var3_6) {
                    MessageUtils.resizeImageAsync((Context)this, (Uri)var4_20, this.getHandler(), this.mResizeImageCallback, false);
                    return;
                }
                catch (ExceedMessageSizeException var3_7) {
                    MessageUtils.resizeImageAsync((Context)this, (Uri)var5_19, this.getHandler(), this.mResizeImageCallback, false);
                    return;
                }
            }
            case 1: {
                try {
                    this.mSlideshowEditor.changeImage(this.mPosition, var3_3.getData());
                    return;
                }
                catch (MmsException var3_8) {
                    Log.e((String)"SlideEditorActivity", (String)"add image failed", (Throwable)var3_8);
                    this.notifyUser("add picture failed");
                    Toast.makeText((Context)this, (CharSequence)this.getResourcesString(2131361853, this.getPictureString()), (int)0).show();
                    return;
                }
                catch (UnsupportContentTypeException var3_9) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361847, this.getPictureString()), this.getResourcesString(2131361848, this.getPictureString()));
                    return;
                }
                catch (ResolutionException var4_21) {
                    MessageUtils.resizeImageAsync((Context)this, var3_3.getData(), this.getHandler(), this.mResizeImageCallback, false);
                    return;
                }
                catch (ExceedMessageSizeException var4_22) {
                    MessageUtils.resizeImageAsync((Context)this, var3_3.getData(), this.getHandler(), this.mResizeImageCallback, false);
                    return;
                }
            }
            case 3: 
            case 4: {
                if (var1_1 == 3) {
                    if ((var3_3 = (Uri)var3_3.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI")) == null) return;
                    if (Settings.System.DEFAULT_RINGTONE_URI.equals((Object)var3_3) != false) return;
                } else {
                    var3_3 = var3_3.getData();
                }
                try {
                    this.mSlideshowEditor.changeAudio(this.mPosition, (Uri)var3_3);
                    return;
                }
                catch (MmsException var3_10) {
                    Log.e((String)"SlideEditorActivity", (String)"add audio failed", (Throwable)var3_10);
                    this.notifyUser("add music failed");
                    Toast.makeText((Context)this, (CharSequence)this.getResourcesString(2131361853, this.getAudioString()), (int)0).show();
                    return;
                }
                catch (UnsupportContentTypeException var3_11) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361847, this.getAudioString()), this.getResourcesString(2131361848, this.getAudioString()));
                    return;
                }
                catch (ExceedMessageSizeException var3_12) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361849), this.getResourcesString(2131361853, this.getAudioString()));
                    return;
                }
            }
            case 7: {
                try {
                    this.mSlideshowEditor.changeVideo(this.mPosition, var3_3.getData());
                    return;
                }
                catch (MmsException var3_13) {
                    Log.e((String)"SlideEditorActivity", (String)"add video failed", (Throwable)var3_13);
                    this.notifyUser("add video failed");
                    Toast.makeText((Context)this, (CharSequence)this.getResourcesString(2131361853, this.getVideoString()), (int)0).show();
                    return;
                }
                catch (UnsupportContentTypeException var3_14) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361847, this.getVideoString()), this.getResourcesString(2131361848, this.getVideoString()));
                    return;
                }
                catch (ExceedMessageSizeException var3_15) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361849), this.getResourcesString(2131361853, this.getVideoString()));
                    return;
                }
            }
            case 5: {
                try {
                    this.mSlideshowEditor.changeVideo(this.mPosition, var3_3.getData());
                    return;
                }
                catch (MmsException var3_16) {
                    Log.e((String)"SlideEditorActivity", (String)"add video failed", (Throwable)var3_16);
                    this.notifyUser("add video failed");
                    Toast.makeText((Context)this, (CharSequence)this.getResourcesString(2131361853, this.getVideoString()), (int)0).show();
                    return;
                }
                catch (UnsupportContentTypeException var3_17) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361847, this.getVideoString()), this.getResourcesString(2131361848, this.getVideoString()));
                    return;
                }
                catch (ExceedMessageSizeException var3_18) {
                    MessageUtils.showErrorDialog((Context)this, this.getResourcesString(2131361849), this.getResourcesString(2131361853, this.getVideoString()));
                    return;
                }
            }
            case 6: 
        }
        this.mSlideshowEditor.changeDuration(this.mPosition, Integer.valueOf((String)var3_3.getAction()) * 1000);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.setContentView(2130968603);
        this.mActionBar = this.getActionBar();
        this.mSlideView = (BasicSlideEditorView)this.findViewById(2131820616);
        this.mSlideView.setOnTextChangedListener(this.mOnTextChangedListener);
        this.mPreSlide = (ImageView)this.findViewById(2131820625);
        this.mPreSlide.setOnClickListener(this.mOnNavigateBackward);
        this.mNextSlide = (ImageView)this.findViewById(2131820626);
        this.mNextSlide.setOnClickListener(this.mOnNavigateForward);
        this.mSlideView.setImageViewOnClickListener(this.mOnReplaceImage);
        this.mRemoveSlide = (ImageView)this.findViewById(2131820627);
        this.mRemoveSlide.setOnClickListener(this.mOnRemoveSlide);
        this.mTextEditor = (EditText)this.findViewById(2131820623);
        this.mTextEditor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MmsConfig.getMaxTextLimit())});
        this.mOldText = "";
        this.mDone = (ImageView)this.findViewById(2131820624);
        this.mDone.setOnClickListener(this.mDoneClickListener);
        this.initActivityState(bundle, this.getIntent());
        if (this.mLoadDataTask == null) {
            this.mLoadDataTask = new AsyncTask<Void, Void, Void>(){

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                protected /* varargs */ Void doInBackground(Void ... arrvoid) {
                    try {
                        SlideEditorActivity.this.mSlideshowModel = SlideshowModel.createFromMessageUri(SlideEditorActivity.this.mContext, SlideEditorActivity.this.mUri);
                        do {
                            return null;
                            break;
                        } while (true);
                    }
                    catch (MmsException var1_2) {
                        Log.e((String)"SlideEditorActivity", (String)"Create SlideshowModel failed!", (Throwable)var1_2);
                        SlideEditorActivity.this.finish();
                        return null;
                    }
                }

                /*
                 * Enabled aggressive block sorting
                 */
                protected void onPostExecute(Void void_) {
                    SlideEditorActivity.this.mDataLoaded = true;
                    if (SlideEditorActivity.this.mSlideshowModel == null || SlideEditorActivity.this.mSlideshowModel.size() == 0) {
                        Log.e((String)"SlideEditorActivity", (String)"Loaded slideshow is empty; can't edit nothingness, exiting.");
                        SlideEditorActivity.this.finish();
                        return;
                    }
                    SlideEditorActivity.this.mSlideshowModel.registerModelChangedObserver(SlideEditorActivity.this.mModelChangedObserver);
                    SlideEditorActivity.this.mSlideshowEditor = new SlideshowEditor(SlideEditorActivity.this.mContext, SlideEditorActivity.this.mSlideshowModel);
                    SlideEditorActivity.this.mPresenter = new SlideshowPresenter(SlideEditorActivity.this.mContext, SlideEditorActivity.this.mSlideView, SlideEditorActivity.this.mSlideshowModel);
                    if (SlideEditorActivity.this.mPosition >= SlideEditorActivity.this.mSlideshowModel.size()) {
                        SlideEditorActivity.this.mPosition = Math.max((int)0, (int)(SlideEditorActivity.this.mSlideshowModel.size() - 1));
                    } else if (SlideEditorActivity.this.mPosition < 0) {
                        SlideEditorActivity.this.mPosition = 0;
                    }
                    SlideEditorActivity.this.showCurrentSlide();
                }

                protected void onPreExecute() {
                    SlideEditorActivity.this.mDataLoaded = false;
                    SlideEditorActivity.this.mSlideshowModel = null;
                }
            };
        } else {
            this.mLoadDataTask.cancel(false);
        }
        this.mLoadDataTask.execute((Object[])new Void[0]);
        this.getHandler().postDelayed(new Runnable(){

            @Override
            public void run() {
                SlideEditorActivity.this.mTextEditor.requestFocus();
                SlideEditorActivity.this.showSoftKeyboard();
            }
        }, 500);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mSlideshowModel != null) {
            this.mSlideshowModel.unregisterModelChangedObserver(this.mModelChangedObserver);
        }
        if (this.mLoadDataTask != null) {
            this.mLoadDataTask.cancel(false);
        }
        this.mLoadDataTask = null;
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

    /*
     * Enabled aggressive block sorting
     */
    public boolean onOptionsItemSelected(MenuItem object) {
        if (!this.mDataLoaded) {
            Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
            return true;
        }
        switch (object.getItemId()) {
            default: {
                return true;
            }
            case 0: {
                object = this.mSlideshowModel.get(this.mPosition);
                if (object == null) return true;
                {
                    object.removeText();
                    return true;
                }
            }
            case 11: {
                this.previewSlideshow();
                return true;
            }
            case 1: {
                object = new Intent("android.intent.action.GET_CONTENT", null);
                object.setType("image/*");
                this.startActivityForResult((Intent)object, 1);
                return true;
            }
            case 2: {
                object = new Intent("android.media.action.IMAGE_CAPTURE");
                object.putExtra("output", (Parcelable)TempFileProvider.SCRAP_CONTENT_URI);
                this.startActivityForResult((Intent)object, 2);
                return true;
            }
            case 3: {
                this.mSlideshowEditor.removeImage(this.mPosition);
                return true;
            }
            case 4: {
                MessageUtils.selectAudio((Context)this, 3);
                return true;
            }
            case 12: {
                MessageUtils.recordSound((Context)this, 4, AttachmentProcessor.computeAttachmentSizeLimit(this.mSlideshowModel, 0));
                return true;
            }
            case 5: {
                this.mSlideshowEditor.removeAudio(this.mPosition);
                return true;
            }
            case 6: {
                object = new Intent("android.intent.action.GET_CONTENT");
                object.setType("video/*");
                object.putExtra("android.intent.extra.LOCAL_ONLY", true);
                this.startActivityForResult((Intent)object, 5);
                return true;
            }
            case 14: {
                long l = AttachmentProcessor.computeAttachmentSizeLimit(this.mSlideshowModel, 0);
                if (l > 0) {
                    MessageUtils.recordVideo((Context)this, 7, l);
                    return true;
                }
                Toast.makeText((Context)this, (CharSequence)this.getString(2131361852), (int)0).show();
                return true;
            }
            case 8: {
                this.mSlideshowEditor.removeVideo(this.mPosition);
                return true;
            }
            case 15: {
                if (this.mSlideshowEditor.addNewSlide(this.mPosition)) {
                    this.showCurrentSlide();
                    return true;
                }
                Toast.makeText((Context)this, (int)2131361870, (int)0).show();
                return true;
            }
            case 7: {
                ++this.mPosition;
                if (this.mSlideshowEditor.addNewSlide(this.mPosition)) {
                    this.showCurrentSlide();
                    return true;
                }
                --this.mPosition;
                Toast.makeText((Context)this, (int)2131361870, (int)0).show();
                return true;
            }
            case 9: {
                if (!Build.IS_CM_CUSTOMIZATION && !Build.IS_CU_CUSTOMIZATION) return true;
                {
                    this.showLayoutSelectorDialog();
                    return true;
                }
            }
            case 10: {
                this.showDurationDialog();
                return true;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        this.syncData();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.isFinishing()) return false;
        if (!this.mDataLoaded) {
            return false;
        }
        menu.clear();
        this.hideSoftKeyboard();
        SlideModel slideModel = this.mSlideshowModel.get(this.mPosition);
        if (slideModel == null) return false;
        menu.add(0, 11, 0, 2131361880);
        if (slideModel.hasText() && !TextUtils.isEmpty((CharSequence)slideModel.getText().getText())) {
            menu.add(0, 0, 0, 2131361831).setIcon(2130837748);
        }
        if (slideModel.hasImage()) {
            menu.add(0, 3, 0, 2131361833).setIcon(2130837746);
        } else if (!slideModel.hasVideo()) {
            menu.add(0, 1, 0, 2131361832).setIcon(2130837745);
            menu.add(0, 2, 0, 2131361976).setIcon(2130837745);
        }
        if (slideModel.hasAudio()) {
            menu.add(0, 5, 0, 2131361835).setIcon(2130837747);
        } else if (!slideModel.hasVideo()) {
            if (MmsConfig.getAllowAttachAudio()) {
                SubMenu subMenu = menu.addSubMenu(0, 13, 0, 2131361834).setIcon(2130837727);
                subMenu.add(0, 4, 0, 2131361979);
                subMenu.add(0, 12, 0, 2131361980);
            } else {
                menu.add(0, 12, 0, 2131361980).setIcon(2130837727);
            }
        }
        if (slideModel.hasVideo()) {
            menu.add(0, 8, 0, 2131361837).setIcon(2130837749);
        } else if (!slideModel.hasAudio() && !slideModel.hasImage()) {
            menu.add(0, 6, 0, 2131361836).setIcon(2130837744);
            menu.add(0, 14, 0, 2131361978).setIcon(2130837744);
        }
        if (this.mSlideshowModel.size() < 20) {
            menu.add(0, 15, 0, 2131362249).setIcon(2130837726);
            menu.add(0, 7, 0, 2131361826).setIcon(2130837726);
        }
        menu.add(0, 10, 0, (CharSequence)this.getResources().getString(2131361882).replace((CharSequence)"%s", (CharSequence)String.valueOf((int)(slideModel.getDuration() / 1000)))).setIcon(2130837733);
        if (!Build.IS_CM_CUSTOMIZATION) {
            if (!Build.IS_CU_CUSTOMIZATION) return true;
        }
        menu.add(0, 9, 0, 2131361884);
        return true;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!this.mDataLoaded) {
            Log.v((String)"SlideEditorActivity", (String)"mDataLoaded is false");
            return;
        }
        bundle.putInt("slide_index", this.mPosition);
        bundle.putParcelable("message_uri", (Parcelable)this.mUri);
    }

    protected void onStart() {
        super.onStart();
        SmsTextSizeAdjust.getInstance().init(this, (android.app.Activity)this);
        SmsTextSizeAdjust.getInstance().refresh();
    }

    protected void onStop() {
        super.onStop();
        SmsTextSizeAdjust.clear(this);
    }

    @Override
    public void setTextSize(float f2) {
        if (this.mTextEditor != null) {
            this.mTextEditor.setTextSize(0, f2);
        }
    }

}

