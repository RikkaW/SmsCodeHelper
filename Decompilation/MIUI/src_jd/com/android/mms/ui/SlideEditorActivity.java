package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;

public class SlideEditorActivity
  extends Activity
  implements ISmsTextSizeAdjustHost
{
  private ActionBar mActionBar;
  private Context mContext;
  private boolean mDataLoaded = false;
  private boolean mDirty;
  private ImageView mDone;
  private final View.OnClickListener mDoneClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (!mDataLoaded)
      {
        Log.v("SlideEditorActivity", "mDataLoaded is false");
        return;
      }
      paramAnonymousView = new Intent();
      paramAnonymousView.putExtra("done", true);
      setResult(-1, paramAnonymousView);
      finish();
    }
  };
  private Handler mHandler;
  private InputMethodManager mInputMethodManager;
  private AsyncTask<Void, Void, Void> mLoadDataTask;
  private final IModelChangedObserver mModelChangedObserver = new IModelChangedObserver()
  {
    public void onModelChanged(Model arg1, boolean paramAnonymousBoolean)
    {
      synchronized (SlideEditorActivity.this)
      {
        SlideEditorActivity.access$1202(SlideEditorActivity.this, true);
        setResult(-1);
        return;
      }
    }
  };
  private ImageView mNextSlide;
  private String mOldText;
  private final View.OnClickListener mOnNavigateBackward = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (!mDataLoaded) {
        Log.v("SlideEditorActivity", "mDataLoaded is false");
      }
      while (mPosition <= 0) {
        return;
      }
      SlideEditorActivity.access$810(SlideEditorActivity.this);
      SlideEditorActivity.this.showCurrentSlide();
    }
  };
  private final View.OnClickListener mOnNavigateForward = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (!mDataLoaded) {
        Log.v("SlideEditorActivity", "mDataLoaded is false");
      }
      while (mPosition >= mSlideshowModel.size() - 1) {
        return;
      }
      SlideEditorActivity.access$808(SlideEditorActivity.this);
      SlideEditorActivity.this.showCurrentSlide();
    }
  };
  private final View.OnClickListener mOnRemoveSlide = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (!mDataLoaded) {
        Log.v("SlideEditorActivity", "mDataLoaded is false");
      }
      while ((mPosition < 0) || (mPosition >= mSlideshowModel.size())) {
        return;
      }
      mSlideshowEditor.removeSlide(mPosition);
      int i = mSlideshowModel.size();
      if (i > 0)
      {
        if (mPosition >= i) {
          SlideEditorActivity.access$810(SlideEditorActivity.this);
        }
        SlideEditorActivity.this.showCurrentSlide();
        return;
      }
      finish();
    }
  };
  private final View.OnClickListener mOnReplaceImage = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (!mDataLoaded) {
        Log.v("SlideEditorActivity", "mDataLoaded is false");
      }
      paramAnonymousView = mSlideshowModel.get(mPosition);
      if ((paramAnonymousView != null) && (paramAnonymousView.hasVideo()))
      {
        Toast.makeText(SlideEditorActivity.this, 2131361871, 0).show();
        return;
      }
      paramAnonymousView = new Intent("android.intent.action.GET_CONTENT", null);
      paramAnonymousView.setType("image/*");
      startActivityForResult(paramAnonymousView, 1);
    }
  };
  private final BasicSlideEditorView.OnTextChangedListener mOnTextChangedListener = new BasicSlideEditorView.OnTextChangedListener()
  {
    public void onTextChanged(String paramAnonymousString)
    {
      if (!mDataLoaded) {}
      do
      {
        Log.v("SlideEditorActivity", "mDataLoaded is false");
        for (;;)
        {
          return;
          if (!isFinishing()) {
            try
            {
              mSlideshowEditor.changeText(mPosition, paramAnonymousString);
              if (Build.IS_CM_CUSTOMIZATION_TEST)
              {
                SlideEditorActivity.access$1302(SlideEditorActivity.this, paramAnonymousString);
                return;
              }
            }
            catch (ExceedMessageSizeException paramAnonymousString) {}
          }
        }
      } while (!Build.IS_CM_CUSTOMIZATION_TEST);
      Toast.makeText(SlideEditorActivity.this, 2131361878, 0).show();
      mTextEditor.setText(mOldText);
      mTextEditor.setSelection(mOldText.length());
    }
  };
  private int mPosition;
  private ImageView mPreSlide;
  private SlideshowPresenter mPresenter;
  private ImageView mRemoveSlide;
  private final MessageUtils.ResizeImageResultCallback mResizeImageCallback = new MessageUtils.ResizeImageResultCallback()
  {
    public void onResizeResult(PduPart paramAnonymousPduPart, boolean paramAnonymousBoolean)
    {
      SlideEditorActivity localSlideEditorActivity = SlideEditorActivity.this;
      if (paramAnonymousPduPart == null)
      {
        Toast.makeText(SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361853, SlideEditorActivity.access$1400(SlideEditorActivity.this)), 0).show();
        return;
      }
      try
      {
        mSlideshowEditor.checkMessageSize(mPosition, paramAnonymousPduPart.getData().length);
        long l = ContentUris.parseId(mUri);
        paramAnonymousPduPart = MiuiPduPersister.getPduPersister(localSlideEditorActivity).persistPart(paramAnonymousPduPart, l);
        mSlideshowEditor.changeImage(mPosition, paramAnonymousPduPart);
        return;
      }
      catch (MmsException paramAnonymousPduPart)
      {
        SlideEditorActivity.this.notifyUser("add picture failed");
        Toast.makeText(SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361853, SlideEditorActivity.access$1400(SlideEditorActivity.this)), 0).show();
        return;
      }
      catch (UnsupportContentTypeException paramAnonymousPduPart)
      {
        MessageUtils.showErrorDialog(SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361847, SlideEditorActivity.access$1400(SlideEditorActivity.this)), SlideEditorActivity.this.getResourcesString(2131361848, SlideEditorActivity.access$1400(SlideEditorActivity.this)));
        return;
      }
      catch (ResolutionException paramAnonymousPduPart)
      {
        MessageUtils.showErrorDialog(SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361854), SlideEditorActivity.this.getResourcesString(2131361855));
        return;
      }
      catch (ExceedMessageSizeException paramAnonymousPduPart)
      {
        MessageUtils.showErrorDialog(SlideEditorActivity.this, SlideEditorActivity.this.getResourcesString(2131361849), SlideEditorActivity.this.getResourcesString(2131361853, SlideEditorActivity.access$1400(SlideEditorActivity.this)));
      }
    }
  };
  private BasicSlideEditorView mSlideView;
  private SlideshowEditor mSlideshowEditor;
  private SlideshowModel mSlideshowModel;
  private EditText mTextEditor;
  private Uri mUri;
  
  private void enablePreAndNextButton()
  {
    int i = mSlideshowModel.size();
    if (i == 1)
    {
      mPreSlide.setEnabled(false);
      mNextSlide.setEnabled(false);
      return;
    }
    if (mPosition == 0)
    {
      mPreSlide.setEnabled(false);
      mNextSlide.setEnabled(true);
      return;
    }
    if (mPosition == i - 1)
    {
      mPreSlide.setEnabled(true);
      mNextSlide.setEnabled(false);
      return;
    }
    mPreSlide.setEnabled(true);
    mNextSlide.setEnabled(true);
  }
  
  private String getAudioString()
  {
    return getResourcesString(2131361994);
  }
  
  private Handler getHandler()
  {
    if (mHandler == null) {
      mHandler = new Handler();
    }
    return mHandler;
  }
  
  private String getPictureString()
  {
    return getResourcesString(2131361995);
  }
  
  private String getResourcesString(int paramInt)
  {
    return getResources().getString(paramInt);
  }
  
  private String getResourcesString(int paramInt, String paramString)
  {
    return getResources().getString(paramInt, new Object[] { paramString });
  }
  
  private String getVideoString()
  {
    return getResourcesString(2131361996);
  }
  
  private void hideSoftKeyboard()
  {
    if (mInputMethodManager == null) {
      mInputMethodManager = ((InputMethodManager)getSystemService("input_method"));
    }
    mInputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
  }
  
  private void initActivityState(Bundle paramBundle, Intent paramIntent)
  {
    if (paramBundle != null)
    {
      mUri = ((Uri)paramBundle.getParcelable("message_uri"));
      mPosition = paramBundle.getInt("slide_index", 0);
      return;
    }
    mUri = paramIntent.getData();
    mPosition = paramIntent.getIntExtra("slide_index", 0);
  }
  
  private void notifyUser(String paramString) {}
  
  private void previewSlideshow()
  {
    if (!mDataLoaded)
    {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
      return;
    }
    syncData();
    MessageUtils.viewMmsMessageAttachment(this, mUri, mSlideshowModel);
  }
  
  private void showCurrentSlide()
  {
    mPresenter.setLocation(mPosition);
    mPresenter.present();
    updateTitle();
    enablePreAndNextButton();
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mOldText = mTextEditor.getText().toString();
    }
  }
  
  private void showDurationDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(2130837762);
    String str = getResources().getString(2131361883);
    localBuilder.setTitle(str + (mPosition + 1) + "/" + mSlideshowModel.size());
    localBuilder.setItems(2131230735, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if ((paramAnonymousInt >= 0) && (paramAnonymousInt < 10)) {
          mSlideshowEditor.changeDuration(mPosition, (paramAnonymousInt + 1) * 1000);
        }
        for (;;)
        {
          paramAnonymousDialogInterface.dismiss();
          return;
          Intent localIntent = new Intent(SlideEditorActivity.this, EditSlideDurationActivity.class);
          localIntent.putExtra("slide_index", mPosition);
          localIntent.putExtra("slide_total", mSlideshowModel.size());
          localIntent.putExtra("dur", mSlideshowModel.get(mPosition).getDuration() / 1000);
          startActivityForResult(localIntent, 6);
        }
      }
    });
    localBuilder.show();
  }
  
  private void showLayoutSelectorDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(2130837763);
    String str1 = getResources().getString(2131361884);
    localBuilder.setTitle(str1 + (mPosition + 1) + "/" + mSlideshowModel.size());
    str1 = getResources().getString(2131361983);
    String str2 = getResources().getString(2131361982);
    DialogInterface.OnClickListener local11 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        }
        for (;;)
        {
          paramAnonymousDialogInterface.dismiss();
          return;
          mSlideshowEditor.changeLayout(1);
          continue;
          mSlideshowEditor.changeLayout(0);
        }
      }
    };
    localBuilder.setItems(new String[] { str1, str2 }, local11);
    localBuilder.show();
  }
  
  private void showSoftKeyboard()
  {
    if (mInputMethodManager == null) {
      mInputMethodManager = ((InputMethodManager)getSystemService("input_method"));
    }
    mInputMethodManager.showSoftInput(mTextEditor, 0);
  }
  
  /* Error */
  private void syncData()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 83	com/android/mms/ui/SlideEditorActivity:mDataLoaded	Z
    //   4: ifne +14 -> 18
    //   7: ldc_w 299
    //   10: ldc_w 301
    //   13: invokestatic 307	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: return
    //   18: aload_0
    //   19: monitorenter
    //   20: aload_0
    //   21: getfield 131	com/android/mms/ui/SlideEditorActivity:mDirty	Z
    //   24: istore_1
    //   25: iload_1
    //   26: ifeq +36 -> 62
    //   29: aload_0
    //   30: getfield 118	com/android/mms/ui/SlideEditorActivity:mSlideshowModel	Lcom/android/mms/model/SlideshowModel;
    //   33: invokevirtual 404	com/android/mms/model/SlideshowModel:toPduBody	()Lcom/google/android/mms/pdu/PduBody;
    //   36: astore_2
    //   37: aload_0
    //   38: invokestatic 410	com/google/android/mms/pdu/MiuiPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/google/android/mms/pdu/MiuiPduPersister;
    //   41: aload_0
    //   42: getfield 167	com/android/mms/ui/SlideEditorActivity:mUri	Landroid/net/Uri;
    //   45: aload_2
    //   46: invokevirtual 414	com/google/android/mms/pdu/MiuiPduPersister:updateParts	(Landroid/net/Uri;Lcom/google/android/mms/pdu/PduBody;)V
    //   49: aload_0
    //   50: getfield 118	com/android/mms/ui/SlideEditorActivity:mSlideshowModel	Lcom/android/mms/model/SlideshowModel;
    //   53: aload_2
    //   54: invokevirtual 418	com/android/mms/model/SlideshowModel:sync	(Lcom/google/android/mms/pdu/PduBody;)V
    //   57: aload_0
    //   58: iconst_0
    //   59: putfield 131	com/android/mms/ui/SlideEditorActivity:mDirty	Z
    //   62: aload_0
    //   63: monitorexit
    //   64: return
    //   65: astore_2
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_2
    //   69: athrow
    //   70: astore_2
    //   71: ldc_w 299
    //   74: new 358	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 359	java/lang/StringBuilder:<init>	()V
    //   81: ldc_w 420
    //   84: invokevirtual 363	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload_0
    //   88: getfield 167	com/android/mms/ui/SlideEditorActivity:mUri	Landroid/net/Uri;
    //   91: invokevirtual 423	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 369	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: aload_2
    //   98: invokestatic 427	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   101: pop
    //   102: goto -45 -> 57
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	SlideEditorActivity
    //   24	2	1	bool	boolean
    //   36	18	2	localPduBody	com.google.android.mms.pdu.PduBody
    //   65	4	2	localObject	Object
    //   70	28	2	localMmsException	MmsException
    // Exception table:
    //   from	to	target	type
    //   20	25	65	finally
    //   29	57	65	finally
    //   57	62	65	finally
    //   62	64	65	finally
    //   66	68	65	finally
    //   71	102	65	finally
    //   29	57	70	com/google/android/mms/MmsException
  }
  
  private void updateTitle()
  {
    if (mDataLoaded) {
      mActionBar.setTitle(getString(2131361829, new Object[] { Integer.valueOf(mPosition + 1), Integer.valueOf(mSlideshowModel.size()) }));
    }
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(paramMotionEvent);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = super.dispatchTouchEvent(paramMotionEvent);
    }
    return bool1;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {}
    do
    {
      return;
      Intent localIntent2;
      Intent localIntent1;
      switch (paramInt1)
      {
      default: 
        return;
      case 0: 
        mSlideshowEditor.changeText(mPosition, paramIntent.getAction());
        return;
      case 2: 
        localIntent2 = null;
        localIntent1 = null;
        for (;;)
        {
          try
          {
            paramIntent = TempFileProvider.renameScrapFile(".jpg", Integer.toString(mPosition), this);
            if (paramIntent != null) {
              continue;
            }
            paramInt1 = 1;
          }
          catch (MmsException paramIntent)
          {
            Log.e("SlideEditorActivity", "add image failed", paramIntent);
            paramInt1 = 1;
            continue;
          }
          catch (UnsupportContentTypeException paramIntent)
          {
            MessageUtils.showErrorDialog(this, getResourcesString(2131361847, getPictureString()), getResourcesString(2131361848, getPictureString()));
            paramInt1 = 0;
            continue;
          }
          catch (ResolutionException paramIntent)
          {
            MessageUtils.resizeImageAsync(this, localIntent1, getHandler(), mResizeImageCallback, false);
            paramInt1 = 0;
            continue;
          }
          catch (ExceedMessageSizeException paramIntent)
          {
            MessageUtils.resizeImageAsync(this, localIntent2, getHandler(), mResizeImageCallback, false);
            continue;
          }
          if (paramInt1 == 0) {
            break;
          }
          notifyUser("add picture failed");
          Toast.makeText(this, getResourcesString(2131361853, getPictureString()), 0).show();
          return;
          localIntent1 = paramIntent;
          localIntent2 = paramIntent;
          mSlideshowEditor.changeImage(mPosition, paramIntent);
          paramInt1 = 0;
        }
      case 1: 
        try
        {
          mSlideshowEditor.changeImage(mPosition, paramIntent.getData());
          return;
        }
        catch (MmsException paramIntent)
        {
          Log.e("SlideEditorActivity", "add image failed", paramIntent);
          notifyUser("add picture failed");
          Toast.makeText(this, getResourcesString(2131361853, getPictureString()), 0).show();
          return;
        }
        catch (UnsupportContentTypeException paramIntent)
        {
          MessageUtils.showErrorDialog(this, getResourcesString(2131361847, getPictureString()), getResourcesString(2131361848, getPictureString()));
          return;
        }
        catch (ResolutionException localResolutionException)
        {
          MessageUtils.resizeImageAsync(this, paramIntent.getData(), getHandler(), mResizeImageCallback, false);
          return;
        }
        catch (ExceedMessageSizeException localExceedMessageSizeException)
        {
          MessageUtils.resizeImageAsync(this, paramIntent.getData(), getHandler(), mResizeImageCallback, false);
          return;
        }
      case 3: 
      case 4: 
        if (paramInt1 != 3) {
          break label444;
        }
        paramIntent = (Uri)paramIntent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
      }
    } while ((paramIntent == null) || (Settings.System.DEFAULT_RINGTONE_URI.equals(paramIntent)));
    try
    {
      mSlideshowEditor.changeAudio(mPosition, paramIntent);
      return;
    }
    catch (MmsException paramIntent)
    {
      for (;;)
      {
        Log.e("SlideEditorActivity", "add audio failed", paramIntent);
        notifyUser("add music failed");
        Toast.makeText(this, getResourcesString(2131361853, getAudioString()), 0).show();
        return;
        paramIntent = paramIntent.getData();
      }
    }
    catch (UnsupportContentTypeException paramIntent)
    {
      MessageUtils.showErrorDialog(this, getResourcesString(2131361847, getAudioString()), getResourcesString(2131361848, getAudioString()));
      return;
    }
    catch (ExceedMessageSizeException paramIntent)
    {
      label444:
      MessageUtils.showErrorDialog(this, getResourcesString(2131361849), getResourcesString(2131361853, getAudioString()));
      return;
    }
    try
    {
      mSlideshowEditor.changeVideo(mPosition, paramIntent.getData());
      return;
    }
    catch (MmsException paramIntent)
    {
      Log.e("SlideEditorActivity", "add video failed", paramIntent);
      notifyUser("add video failed");
      Toast.makeText(this, getResourcesString(2131361853, getVideoString()), 0).show();
      return;
    }
    catch (UnsupportContentTypeException paramIntent)
    {
      MessageUtils.showErrorDialog(this, getResourcesString(2131361847, getVideoString()), getResourcesString(2131361848, getVideoString()));
      return;
    }
    catch (ExceedMessageSizeException paramIntent)
    {
      MessageUtils.showErrorDialog(this, getResourcesString(2131361849), getResourcesString(2131361853, getVideoString()));
      return;
    }
    try
    {
      mSlideshowEditor.changeVideo(mPosition, paramIntent.getData());
      return;
    }
    catch (MmsException paramIntent)
    {
      Log.e("SlideEditorActivity", "add video failed", paramIntent);
      notifyUser("add video failed");
      Toast.makeText(this, getResourcesString(2131361853, getVideoString()), 0).show();
      return;
    }
    catch (UnsupportContentTypeException paramIntent)
    {
      MessageUtils.showErrorDialog(this, getResourcesString(2131361847, getVideoString()), getResourcesString(2131361848, getVideoString()));
      return;
    }
    catch (ExceedMessageSizeException paramIntent)
    {
      MessageUtils.showErrorDialog(this, getResourcesString(2131361849), getResourcesString(2131361853, getVideoString()));
      return;
    }
    mSlideshowEditor.changeDuration(mPosition, Integer.valueOf(paramIntent.getAction()).intValue() * 1000);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    setContentView(2130968603);
    mActionBar = getActionBar();
    mSlideView = ((BasicSlideEditorView)findViewById(2131820616));
    mSlideView.setOnTextChangedListener(mOnTextChangedListener);
    mPreSlide = ((ImageView)findViewById(2131820625));
    mPreSlide.setOnClickListener(mOnNavigateBackward);
    mNextSlide = ((ImageView)findViewById(2131820626));
    mNextSlide.setOnClickListener(mOnNavigateForward);
    mSlideView.setImageViewOnClickListener(mOnReplaceImage);
    mRemoveSlide = ((ImageView)findViewById(2131820627));
    mRemoveSlide.setOnClickListener(mOnRemoveSlide);
    mTextEditor = ((EditText)findViewById(2131820623));
    mTextEditor.setFilters(new InputFilter[] { new InputFilter.LengthFilter(MmsConfig.getMaxTextLimit()) });
    mOldText = "";
    mDone = ((ImageView)findViewById(2131820624));
    mDone.setOnClickListener(mDoneClickListener);
    initActivityState(paramBundle, getIntent());
    if (mLoadDataTask == null) {
      mLoadDataTask = new AsyncTask()
      {
        protected Void doInBackground(Void... paramAnonymousVarArgs)
        {
          try
          {
            SlideEditorActivity.access$102(SlideEditorActivity.this, SlideshowModel.createFromMessageUri(mContext, mUri));
            return null;
          }
          catch (MmsException paramAnonymousVarArgs)
          {
            for (;;)
            {
              Log.e("SlideEditorActivity", "Create SlideshowModel failed!", paramAnonymousVarArgs);
              finish();
            }
          }
        }
        
        protected void onPostExecute(Void paramAnonymousVoid)
        {
          SlideEditorActivity.access$002(SlideEditorActivity.this, true);
          if ((mSlideshowModel == null) || (mSlideshowModel.size() == 0))
          {
            Log.e("SlideEditorActivity", "Loaded slideshow is empty; can't edit nothingness, exiting.");
            finish();
            return;
          }
          mSlideshowModel.registerModelChangedObserver(mModelChangedObserver);
          SlideEditorActivity.access$502(SlideEditorActivity.this, new SlideshowEditor(mContext, mSlideshowModel));
          SlideEditorActivity.access$602(SlideEditorActivity.this, new SlideshowPresenter(mContext, mSlideView, mSlideshowModel));
          if (mPosition >= mSlideshowModel.size()) {
            SlideEditorActivity.access$802(SlideEditorActivity.this, Math.max(0, mSlideshowModel.size() - 1));
          }
          for (;;)
          {
            SlideEditorActivity.this.showCurrentSlide();
            return;
            if (mPosition < 0) {
              SlideEditorActivity.access$802(SlideEditorActivity.this, 0);
            }
          }
        }
        
        protected void onPreExecute()
        {
          SlideEditorActivity.access$002(SlideEditorActivity.this, false);
          SlideEditorActivity.access$102(SlideEditorActivity.this, null);
        }
      };
    }
    for (;;)
    {
      mLoadDataTask.execute(new Void[0]);
      getHandler().postDelayed(new Runnable()
      {
        public void run()
        {
          mTextEditor.requestFocus();
          SlideEditorActivity.this.showSoftKeyboard();
        }
      }, 500L);
      return;
      mLoadDataTask.cancel(false);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (mSlideshowModel != null) {
      mSlideshowModel.unregisterModelChangedObserver(mModelChangedObserver);
    }
    if (mLoadDataTask != null) {
      mLoadDataTask.cancel(false);
    }
    mLoadDataTask = null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    MessageUtils.launchMessagePreference(this);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!mDataLoaded) {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
    }
    do
    {
      do
      {
        return true;
        switch (paramMenuItem.getItemId())
        {
        default: 
          return true;
        case 0: 
          paramMenuItem = mSlideshowModel.get(mPosition);
        }
      } while (paramMenuItem == null);
      paramMenuItem.removeText();
      return true;
      previewSlideshow();
      return true;
      paramMenuItem = new Intent("android.intent.action.GET_CONTENT", null);
      paramMenuItem.setType("image/*");
      startActivityForResult(paramMenuItem, 1);
      return true;
      paramMenuItem = new Intent("android.media.action.IMAGE_CAPTURE");
      paramMenuItem.putExtra("output", TempFileProvider.SCRAP_CONTENT_URI);
      startActivityForResult(paramMenuItem, 2);
      return true;
      mSlideshowEditor.removeImage(mPosition);
      return true;
      MessageUtils.selectAudio(this, 3);
      return true;
      MessageUtils.recordSound(this, 4, AttachmentProcessor.computeAttachmentSizeLimit(mSlideshowModel, 0));
      return true;
      mSlideshowEditor.removeAudio(mPosition);
      return true;
      paramMenuItem = new Intent("android.intent.action.GET_CONTENT");
      paramMenuItem.setType("video/*");
      paramMenuItem.putExtra("android.intent.extra.LOCAL_ONLY", true);
      startActivityForResult(paramMenuItem, 5);
      return true;
      long l = AttachmentProcessor.computeAttachmentSizeLimit(mSlideshowModel, 0);
      if (l > 0L)
      {
        MessageUtils.recordVideo(this, 7, l);
        return true;
      }
      Toast.makeText(this, getString(2131361852), 0).show();
      return true;
      mSlideshowEditor.removeVideo(mPosition);
      return true;
      if (mSlideshowEditor.addNewSlide(mPosition))
      {
        showCurrentSlide();
        return true;
      }
      Toast.makeText(this, 2131361870, 0).show();
      return true;
      mPosition += 1;
      if (mSlideshowEditor.addNewSlide(mPosition))
      {
        showCurrentSlide();
        return true;
      }
      mPosition -= 1;
      Toast.makeText(this, 2131361870, 0).show();
      return true;
    } while ((!Build.IS_CM_CUSTOMIZATION) && (!Build.IS_CU_CUSTOMIZATION));
    showLayoutSelectorDialog();
    return true;
    showDurationDialog();
    return true;
    finish();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    syncData();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if ((isFinishing()) || (!mDataLoaded)) {}
    SlideModel localSlideModel;
    do
    {
      return false;
      paramMenu.clear();
      hideSoftKeyboard();
      localSlideModel = mSlideshowModel.get(mPosition);
    } while (localSlideModel == null);
    paramMenu.add(0, 11, 0, 2131361880);
    if ((localSlideModel.hasText()) && (!TextUtils.isEmpty(localSlideModel.getText().getText()))) {
      paramMenu.add(0, 0, 0, 2131361831).setIcon(2130837748);
    }
    if (localSlideModel.hasImage())
    {
      paramMenu.add(0, 3, 0, 2131361833).setIcon(2130837746);
      if (!localSlideModel.hasAudio()) {
        break label364;
      }
      paramMenu.add(0, 5, 0, 2131361835).setIcon(2130837747);
      label153:
      if (!localSlideModel.hasVideo()) {
        break label454;
      }
      paramMenu.add(0, 8, 0, 2131361837).setIcon(2130837749);
    }
    for (;;)
    {
      if (mSlideshowModel.size() < 20)
      {
        paramMenu.add(0, 15, 0, 2131362249).setIcon(2130837726);
        paramMenu.add(0, 7, 0, 2131361826).setIcon(2130837726);
      }
      paramMenu.add(0, 10, 0, getResources().getString(2131361882).replace("%s", String.valueOf(localSlideModel.getDuration() / 1000))).setIcon(2130837733);
      if ((Build.IS_CM_CUSTOMIZATION) || (Build.IS_CU_CUSTOMIZATION)) {
        paramMenu.add(0, 9, 0, 2131361884);
      }
      return true;
      if (localSlideModel.hasVideo()) {
        break;
      }
      paramMenu.add(0, 1, 0, 2131361832).setIcon(2130837745);
      paramMenu.add(0, 2, 0, 2131361976).setIcon(2130837745);
      break;
      label364:
      if (localSlideModel.hasVideo()) {
        break label153;
      }
      if (MmsConfig.getAllowAttachAudio())
      {
        SubMenu localSubMenu = paramMenu.addSubMenu(0, 13, 0, 2131361834).setIcon(2130837727);
        localSubMenu.add(0, 4, 0, 2131361979);
        localSubMenu.add(0, 12, 0, 2131361980);
        break label153;
      }
      paramMenu.add(0, 12, 0, 2131361980).setIcon(2130837727);
      break label153;
      label454:
      if ((!localSlideModel.hasAudio()) && (!localSlideModel.hasImage()))
      {
        paramMenu.add(0, 6, 0, 2131361836).setIcon(2130837744);
        paramMenu.add(0, 14, 0, 2131361978).setIcon(2130837744);
      }
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (!mDataLoaded)
    {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
      return;
    }
    paramBundle.putInt("slide_index", mPosition);
    paramBundle.putParcelable("message_uri", mUri);
  }
  
  protected void onStart()
  {
    super.onStart();
    SmsTextSizeAdjust.getInstance().init(this, this);
    SmsTextSizeAdjust.getInstance().refresh();
  }
  
  protected void onStop()
  {
    super.onStop();
    SmsTextSizeAdjust.clear(this);
  }
  
  public void setTextSize(float paramFloat)
  {
    if (mTextEditor != null) {
      mTextEditor.setTextSize(0, paramFloat);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */