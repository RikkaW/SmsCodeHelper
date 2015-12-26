package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.provider.Settings.System;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
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
import com.android.mms.model.ContactParser;
import com.android.mms.model.MediaModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
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

public class AttachmentProcessor
{
  private static final int ATTACHMENT_PANEL_SCREEN_HEIGHT;
  private static final int[] ATTACHMENT_STATE_FOR_MMS = { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
  private static final int[] ATTACHMENT_STATE_FOR_SLIDESHOW;
  private static final String AUDIO_URI;
  private static final String IMAGE_URI;
  private static final String VIDEO_URI = MediaStore.Video.Media.getContentUri("external").toString();
  private MessageEditableActivityBase mActivity;
  private ViewSwitcher mAttachmentPanel;
  private int mAttachmentPanelPreviousWidth = 0;
  private AttachmentTypeListAdapter mAttachmentTypeListAdapter;
  private AttachmentScreenView mAttachmentTypeScreens;
  private View[] mAttachmentTypes = new View[12];
  private int mCategoryStart;
  private PhraseListAdapter mPhraseListAdapter;
  private final MessageUtils.ResizeImageResultCallback mResizeImageCallback = new MessageUtils.ResizeImageResultCallback()
  {
    public void onResizeResult(PduPart paramAnonymousPduPart, boolean paramAnonymousBoolean)
    {
      if (paramAnonymousPduPart == null)
      {
        handleAddAttachmentError(-1, 2131361995);
        return;
      }
      MiuiPduPersister localMiuiPduPersister = MiuiPduPersister.getPduPersister(mActivity);
      WorkingMessage localWorkingMessage = mActivity.getWorkingMessage();
      Uri localUri = localWorkingMessage.saveAsMms(true);
      int i;
      if (localUri == null) {
        i = -1;
      }
      for (;;)
      {
        handleAddAttachmentError(i, 2131361995);
        return;
        try
        {
          paramAnonymousPduPart = localMiuiPduPersister.persistPart(paramAnonymousPduPart, ContentUris.parseId(localUri));
          i = localWorkingMessage.setAttachment(1, paramAnonymousPduPart, paramAnonymousBoolean);
          Log.i("AttachmentProcessor", "ResizeImageResultCallback: dataUri=" + paramAnonymousPduPart);
        }
        catch (MmsException paramAnonymousPduPart)
        {
          i = -1;
        }
      }
    }
  };
  private Animation mSlideLeftInAnimation;
  private Animation mSlideLeftOutAnimation;
  private Animation mSlideRightInAnimation;
  private Animation mSlideRightOutAnimation;
  private int mSmileyPanelPreviousWidth = 0;
  private ViewGroup mSmileyScreens;
  private TabWidget mSmileyTabWidget;
  
  static
  {
    IMAGE_URI = MediaStore.Images.Media.getContentUri("external").toString();
    AUDIO_URI = MediaStore.Audio.Media.getContentUri("external").toString();
    ATTACHMENT_PANEL_SCREEN_HEIGHT = MmsApp.getApp().getResources().getDimensionPixelSize(2131427378);
    ATTACHMENT_STATE_FOR_SLIDESHOW = new int[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 };
  }
  
  public AttachmentProcessor(MessageEditableActivityBase paramMessageEditableActivityBase)
  {
    mActivity = paramMessageEditableActivityBase;
  }
  
  private void addAudio(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri != null) {
      handleAddAttachmentError(mActivity.getWorkingMessage().setAttachment(3, paramUri, paramBoolean), 2131361994);
    }
  }
  
  private void addImage(Uri paramUri, boolean paramBoolean)
  {
    Log.i("AttachmentProcessor", "addImage: append=" + paramBoolean + ", uri=" + paramUri);
    int i = mActivity.getWorkingMessage().setAttachment(1, paramUri, paramBoolean);
    if ((i == -4) || (i == -2))
    {
      Log.i("AttachmentProcessor", "addImage: resize image " + paramUri);
      MessageUtils.resizeImageAsync(mActivity, paramUri, mActivity.getHandler(), mResizeImageCallback, paramBoolean);
      return;
    }
    handleAddAttachmentError(i, 2131361995);
  }
  
  private void addVCard(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri != null) {
      handleAddAttachmentError(mActivity.getWorkingMessage().setAttachment(4, paramUri, paramBoolean), 2131362248);
    }
  }
  
  private void addVideo(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri != null) {
      handleAddAttachmentError(mActivity.getWorkingMessage().setAttachment(2, paramUri, paramBoolean), 2131361996);
    }
  }
  
  private void arrangeAttachmentTypeScreens(int paramInt)
  {
    int i1 = getInt(2131558400);
    int i2 = getInt(2131558401);
    int i3 = getDimen(2131427380);
    int i = getDimen(2131427383);
    int i4 = i1 * i2;
    int i5 = (mAttachmentTypeListAdapter.getCount() - 1) / i4 + 1;
    int i6 = i5 + 1;
    Object localObject = new FrameLayout.LayoutParams(-2, -2, 1);
    topMargin = (i3 * i2 + ATTACHMENT_PANEL_SCREEN_HEIGHT - i >> 1);
    mAttachmentTypeScreens.setSeekBarPosition((FrameLayout.LayoutParams)localObject);
    mAttachmentTypeScreens.removeAllScreens();
    arrangePhraseListScreen();
    i = 0;
    boolean bool1 = MessageUtils.shouldShowFestival(mActivity);
    boolean bool2 = shouldShowSmiley();
    int k = 1;
    while (k < i6)
    {
      localObject = new StaticGridView(mActivity, i2, i1, paramInt / i1, i3);
      int m = 0;
      int j;
      for (;;)
      {
        final int n;
        if (m < i4)
        {
          n = i;
          j = n;
          if (!bool2)
          {
            j = n;
            if (i >= 0) {
              j = n + 1;
            }
          }
          n = j;
          if (!bool1)
          {
            n = j;
            if (j >= 5) {
              n = j + 1;
            }
          }
          if (n < mAttachmentTypeListAdapter.getCount()) {}
        }
        else
        {
          j = i;
          if (k != i6 - 1) {
            break;
          }
          for (;;)
          {
            j = i;
            if (i >= i4 * i5) {
              break;
            }
            ((StaticGridView)localObject).addView(mAttachmentTypeListAdapter.inflateEmptyView());
            i += 1;
          }
        }
        View localView = mAttachmentTypeListAdapter.getView(n, null, null);
        localView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            onAttachmentTypeClick(n);
          }
        });
        ((StaticGridView)localObject).addView(localView);
        mAttachmentTypes[n] = localView;
        i += 1;
        m += 1;
      }
      mAttachmentTypeScreens.addView((View)localObject);
      k += 1;
      i = j;
    }
    mAttachmentTypeScreens.setCurrentScreen(1);
    updateAttachmentTypeStates();
  }
  
  private void arrangePhraseListScreen()
  {
    int i = getInt(2131558401);
    int j = getDimen(2131427380);
    ListView localListView = new ListView(mActivity);
    Object localObject = View.inflate(mActivity, 2130968683, null);
    ((View)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(mActivity, PhraseActivity.class);
        paramAnonymousView.setAction("android.intent.action.PICK_ACTIVITY");
        mActivity.startActivityForResult(paramAnonymousView, 113);
      }
    });
    localListView.addFooterView((View)localObject);
    localListView.setAdapter(mPhraseListAdapter);
    mPhraseListAdapter.load();
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (String)mPhraseListAdapter.getItem(paramAnonymousInt);
        mActivity.insertPhraseSms(paramAnonymousAdapterView);
      }
    });
    localObject = new FrameLayout.LayoutParams(-1, i * j);
    mAttachmentTypeScreens.addView(localListView, (ViewGroup.LayoutParams)localObject);
  }
  
  private void arrangeSmileyScreens(int paramInt)
  {
    int k = getInt(2131558402);
    int m = getInt(2131558403);
    int n = getDimen(2131427379);
    int i1 = getDimen(2131427381);
    int i2 = getDimen(2131427383);
    int i = mCategoryStart;
    while (i < SmileyParser.CATEGORY_COUNT)
    {
      ScreenView localScreenView = (ScreenView)mSmileyScreens.getChildAt(i);
      int i3 = (SmileyParser.getSmileyCount(i) - 1) / (k * m);
      localScreenView.setLayoutParams(new FrameLayout.LayoutParams(-1, n));
      Object localObject = new FrameLayout.LayoutParams(-2, -2, 49);
      topMargin = (i1 * m + n - i2 >> 1);
      localScreenView.setSeekBarPosition((FrameLayout.LayoutParams)localObject);
      localScreenView.removeAllScreens();
      int j = 0;
      while (j < i3 + 1)
      {
        localObject = new StaticGridView(mActivity, m, k, paramInt / k, i1);
        ((StaticGridView)localObject).setInitializer(new SmileyGridInitializer(i, j, m, k));
        localScreenView.addView((View)localObject);
        j += 1;
      }
      localScreenView.setCurrentScreen(0);
      i += 1;
    }
  }
  
  private void backspace()
  {
    EditText localEditText = mActivity.getEditMessageFocus();
    localEditText.onKeyDown(67, new KeyEvent(0, 67));
    localEditText.onKeyUp(67, new KeyEvent(1, 67));
  }
  
  public static long computeAttachmentSizeLimit(SlideshowModel paramSlideshowModel, int paramInt)
  {
    long l2 = MmsConfig.getMaxMessageSize() - 1024;
    long l1 = l2;
    if (paramSlideshowModel != null) {
      l1 = l2 - paramSlideshowModel.getCurrentMessageSize() + paramInt;
    }
    return l1;
  }
  
  private void editSlideshow()
  {
    new AsyncTask()
    {
      protected Uri doInBackground(Void... paramAnonymousVarArgs)
      {
        return mActivity.getWorkingMessage().saveAsMms(false);
      }
      
      protected void onPostExecute(Uri paramAnonymousUri)
      {
        Intent localIntent = new Intent(mActivity, SlideshowEditActivity.class);
        localIntent.setData(paramAnonymousUri);
        mActivity.startActivityForResult(localIntent, 106);
        paramAnonymousUri = (DialogFragment)mActivity.getFragmentManager().findFragmentByTag("edit_progress_dialog");
        if (paramAnonymousUri != null) {
          paramAnonymousUri.dismissAllowingStateLoss();
        }
      }
      
      protected void onPreExecute()
      {
        FragmentTransaction localFragmentTransaction = mActivity.getFragmentManager().beginTransaction();
        AttachmentProcessor.ProgressDialogFragment localProgressDialogFragment = new AttachmentProcessor.ProgressDialogFragment();
        localProgressDialogFragment.setCancelable(false);
        localProgressDialogFragment.show(localFragmentTransaction, "edit_progress_dialog");
      }
    }.execute(new Void[0]);
  }
  
  private int getDimen(int paramInt)
  {
    return mActivity.getResources().getDimensionPixelSize(paramInt);
  }
  
  private int getInt(int paramInt)
  {
    return mActivity.getResources().getInteger(paramInt);
  }
  
  private void gotoSmileyPanel(boolean paramBoolean)
  {
    if (!isOnAttachmentPanel()) {
      return;
    }
    if (mSmileyTabWidget == null)
    {
      mActivity.findViewById(2131820675).setVisibility(0);
      initSmileyPanelView(mActivity);
    }
    if (paramBoolean)
    {
      if (mSlideLeftInAnimation == null) {
        mSlideLeftInAnimation = AnimationUtils.loadAnimation(mActivity, 2131034122);
      }
      if (mSlideLeftOutAnimation == null) {
        mSlideLeftOutAnimation = AnimationUtils.loadAnimation(mActivity, 2131034123);
      }
      mAttachmentPanel.setInAnimation(mSlideLeftInAnimation);
      mAttachmentPanel.setOutAnimation(mSlideLeftOutAnimation);
    }
    for (;;)
    {
      mAttachmentPanel.showPrevious();
      return;
      mAttachmentPanel.setInAnimation(null);
      mAttachmentPanel.setOutAnimation(null);
    }
  }
  
  private void initAttachmentPanelView(MessageEditableActivityBase paramMessageEditableActivityBase)
  {
    mAttachmentTypeScreens = ((AttachmentScreenView)paramMessageEditableActivityBase.findViewById(2131820674));
    mAttachmentTypeScreens.setScreenTransitionType(9);
    mAttachmentTypeListAdapter = new AttachmentTypeListAdapter(paramMessageEditableActivityBase);
    mPhraseListAdapter = new PhraseListAdapter(paramMessageEditableActivityBase, 2130968686);
    mAttachmentPanel = ((ViewSwitcher)paramMessageEditableActivityBase.findViewById(2131820673));
    if (paramMessageEditableActivityBase.getResources().getBoolean(2131492864)) {}
    for (int i = 0;; i = 3)
    {
      mCategoryStart = i;
      return;
    }
  }
  
  private void initSmileyPanelView(MessageEditableActivityBase paramMessageEditableActivityBase)
  {
    mSmileyTabWidget = ((TabWidget)paramMessageEditableActivityBase.findViewById(2131820713));
    mSmileyScreens = ((ViewGroup)paramMessageEditableActivityBase.findViewById(2131820707));
    final int i = mCategoryStart;
    while (i < SmileyParser.CATEGORY_COUNT)
    {
      mSmileyTabWidget.getChildAt(i).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AttachmentProcessor.this.selectSmileyTab(i);
        }
      });
      i += 1;
    }
    i = 0;
    while (i < mCategoryStart)
    {
      mSmileyTabWidget.getChildAt(i).setVisibility(8);
      i += 1;
    }
    paramMessageEditableActivityBase = paramMessageEditableActivityBase.findViewById(2131820714);
    paramMessageEditableActivityBase.setClickable(true);
    paramMessageEditableActivityBase.setOnTouchListener(new BackTouchListener(null));
    selectSmileyTab(mCategoryStart);
  }
  
  private void loadSmileyGridView(StaticGridView paramStaticGridView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    int j;
    if (i < paramInt3 * paramInt4)
    {
      j = i + paramInt2 * paramInt3 * paramInt4;
      if (j < SmileyParser.getSmileyCount(paramInt1)) {}
    }
    else
    {
      return;
    }
    final String str = SmileyParser.getSmileyString(paramInt1, j);
    ViewGroup localViewGroup = (ViewGroup)mActivity.getLayoutInflater().inflate(2130968712, null);
    TextView localTextView = (TextView)localViewGroup.findViewById(2131820889);
    localTextView.setText(str);
    switch (paramInt1)
    {
    }
    for (;;)
    {
      localViewGroup.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = mActivity.getEditMessageFocus();
          int i = paramAnonymousView.getSelectionStart();
          paramAnonymousView.getText().insert(i, str);
        }
      });
      paramStaticGridView.addView(localViewGroup);
      i += 1;
      break;
      localTextView.setTextSize(0, localTextView.getTextSize() * 3.0F);
      continue;
      if (str.length() < 7)
      {
        localTextView.setTextSize(0, localTextView.getTextSize() * 1.3F);
        continue;
        localTextView.setTextSize(0, localTextView.getTextSize() * 2.0F);
      }
    }
  }
  
  private void onAudioMenuClick()
  {
    String str1 = mActivity.getResources().getString(2131361980);
    String str2 = mActivity.getResources().getString(2131362232);
    DialogInterface.OnClickListener local7 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          paramAnonymousInt = 0;
          paramAnonymousDialogInterface = mActivity.getWorkingMessage().getSlideshow();
          if (paramAnonymousDialogInterface != null) {
            paramAnonymousInt = paramAnonymousDialogInterface.get(0).getSlideSize();
          }
          long l = AttachmentProcessor.computeAttachmentSizeLimit(paramAnonymousDialogInterface, paramAnonymousInt);
          if (l > 0L)
          {
            MessageUtils.recordSound(mActivity, 105, l);
            return;
          }
          Toast.makeText(mActivity, mActivity.getString(2131362147), 0).show();
          return;
        }
        MessageUtils.selectAudio(mActivity, 104);
      }
    };
    new AlertDialog.Builder(mActivity).setIconAttribute(16843605).setCancelable(true).setItems(new String[] { str1, str2 }, local7).show();
  }
  
  private void onVideoMenuClick()
  {
    String str1 = mActivity.getResources().getString(2131361978);
    String str2 = mActivity.getResources().getString(2131362233);
    DialogInterface.OnClickListener local6 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          paramAnonymousInt = 0;
          paramAnonymousDialogInterface = mActivity.getWorkingMessage().getSlideshow();
          if (paramAnonymousDialogInterface != null) {
            paramAnonymousInt = paramAnonymousDialogInterface.get(0).getSlideSize();
          }
          long l = AttachmentProcessor.computeAttachmentSizeLimit(paramAnonymousDialogInterface, paramAnonymousInt);
          if (l > 0L)
          {
            MessageUtils.recordVideo(mActivity, 103, l);
            return;
          }
          Toast.makeText(mActivity, mActivity.getString(2131361852), 0).show();
          return;
        }
        MessageUtils.selectVideo(mActivity, 102);
      }
    };
    new AlertDialog.Builder(mActivity).setIconAttribute(16843605).setCancelable(true).setItems(new String[] { str1, str2 }, local6).show();
  }
  
  private void resetAttachmentPanelToGridView()
  {
    if ((mAttachmentTypeScreens.getScreenCount() > 0) && (mAttachmentTypeScreens.getCurrentScreenIndex() < 1)) {
      mAttachmentTypeScreens.setCurrentScreen(1);
    }
  }
  
  private void selectSmileyTab(int paramInt)
  {
    mSmileyTabWidget.setCurrentTab(paramInt);
    int i = 0;
    if (i < mSmileyScreens.getChildCount())
    {
      View localView = mSmileyScreens.getChildAt(i);
      if (paramInt == i) {}
      for (int j = 0;; j = 8)
      {
        localView.setVisibility(j);
        i += 1;
        break;
      }
    }
  }
  
  private boolean shouldShowSmiley()
  {
    return (Build.checkRegion("TW")) || (Build.checkRegion("HK")) || (!Build.IS_INTERNATIONAL_BUILD);
  }
  
  private void viewAttachment()
  {
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        Object localObject1 = mActivity.getWorkingMessage();
        paramAnonymousVarArgs = ((WorkingMessage)localObject1).saveAsMms(false);
        Object localObject2 = ((WorkingMessage)localObject1).getSlideshow();
        if ((localObject2 == null) || (((SlideshowModel)localObject2).size() == 0)) {}
        for (;;)
        {
          return null;
          if (!((WorkingMessage)localObject1).hasSlideshow())
          {
            paramAnonymousVarArgs = ((SlideshowModel)localObject2).get(0);
            if (paramAnonymousVarArgs.hasImage()) {
              paramAnonymousVarArgs = paramAnonymousVarArgs.getImage();
            }
          }
          while (paramAnonymousVarArgs != null)
          {
            localObject1 = new Intent("android.intent.action.VIEW");
            ((Intent)localObject1).addFlags(1);
            localObject2 = paramAnonymousVarArgs.getContentType();
            ((Intent)localObject1).setDataAndType(paramAnonymousVarArgs.getUri(), (String)localObject2);
            paramAnonymousVarArgs = paramAnonymousVarArgs.getSrc();
            if (!TextUtils.isEmpty(paramAnonymousVarArgs)) {
              ((Intent)localObject1).putExtra("display_name", paramAnonymousVarArgs);
            }
            mActivity.startActivity((Intent)localObject1);
            return null;
            if (paramAnonymousVarArgs.hasVideo())
            {
              paramAnonymousVarArgs = paramAnonymousVarArgs.getVideo();
            }
            else if (paramAnonymousVarArgs.hasAudio())
            {
              paramAnonymousVarArgs = paramAnonymousVarArgs.getAudio();
              continue;
              localObject1 = new Intent(mActivity, SlideshowActivity.class);
              ((Intent)localObject1).setData(paramAnonymousVarArgs);
              mActivity.startActivity((Intent)localObject1);
              return null;
            }
            else
            {
              paramAnonymousVarArgs = null;
            }
          }
        }
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        paramAnonymousVoid = (DialogFragment)mActivity.getFragmentManager().findFragmentByTag("view_progress_dialog");
        if (paramAnonymousVoid != null) {
          paramAnonymousVoid.dismissAllowingStateLoss();
        }
      }
      
      protected void onPreExecute()
      {
        FragmentTransaction localFragmentTransaction = mActivity.getFragmentManager().beginTransaction();
        AttachmentProcessor.ProgressDialogFragment localProgressDialogFragment = new AttachmentProcessor.ProgressDialogFragment();
        localProgressDialogFragment.setCancelable(false);
        localProgressDialogFragment.show(localFragmentTransaction, "view_progress_dialog");
      }
    }.execute(new Void[0]);
  }
  
  public void addAttachment(String paramString, Uri paramUri, boolean paramBoolean)
  {
    int i = 0;
    if (paramUri != null)
    {
      if ((paramString == null) || ("*/*".equals(paramString))) {
        i = 1;
      }
      String str = paramString;
      if (i != 0)
      {
        if (!paramUri.toString().startsWith(IMAGE_URI)) {
          break label65;
        }
        str = "image/*";
      }
      while (ContentType.isImageType(str))
      {
        addImage(paramUri, paramBoolean);
        return;
        label65:
        if (paramUri.toString().startsWith(VIDEO_URI))
        {
          str = "video/*";
        }
        else if (paramUri.toString().startsWith(AUDIO_URI))
        {
          str = "audio/*";
        }
        else
        {
          str = paramString;
          if (paramUri.toString().startsWith("file://"))
          {
            paramUri.getLastPathSegment();
            paramString = MimeTypeMap.getFileExtensionFromUrl(paramUri.toString());
            if (paramString == null) {}
            for (str = null;; str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString.toLowerCase())) {
              break;
            }
          }
        }
      }
      if (ContentType.isVideoType(str))
      {
        addVideo(paramUri, paramBoolean);
        return;
      }
      if ((ContentType.isAudioType(str)) || ("application/ogg".equalsIgnoreCase(str)))
      {
        addAudio(paramUri, paramBoolean);
        return;
      }
      if ("text/x-vCard".equalsIgnoreCase(str))
      {
        addVCard(paramUri, paramBoolean);
        return;
      }
      handleAddAttachmentError(-3, 2131361997);
      return;
    }
    LogTag.error("error: attachment uri is null", new Object[0]);
  }
  
  public void disableAttachmentPanel()
  {
    if (mAttachmentPanel != null) {
      mAttachmentPanel.setVisibility(8);
    }
  }
  
  public void enableAttachmentPanel()
  {
    if (mAttachmentPanel == null)
    {
      mActivity.findViewById(2131820802).setVisibility(0);
      initAttachmentPanelView(mActivity);
    }
    gotoAttachmentPanel(false);
    resetAttachmentPanelToGridView();
    mAttachmentPanel.setVisibility(0);
  }
  
  public int getAttachmentPanelHeight(int paramInt1, int paramInt2)
  {
    if (!isAttachmentPanelVisible()) {
      return paramInt2;
    }
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    if (mAttachmentPanelPreviousWidth == 0)
    {
      mAttachmentPanelPreviousWidth = paramInt1;
      arrangeAttachmentTypeScreens(paramInt1);
    }
    if ((isSmileyPanelVisible()) && (mSmileyPanelPreviousWidth == 0))
    {
      mSmileyPanelPreviousWidth = paramInt1;
      arrangeSmileyScreens(paramInt1);
    }
    return ATTACHMENT_PANEL_SCREEN_HEIGHT;
  }
  
  public void gotoAttachmentPanel(boolean paramBoolean)
  {
    if (isOnAttachmentPanel()) {
      return;
    }
    if (paramBoolean)
    {
      if (mSlideRightInAnimation == null) {
        mSlideRightInAnimation = AnimationUtils.loadAnimation(mActivity, 2131034124);
      }
      if (mSlideRightOutAnimation == null) {
        mSlideRightOutAnimation = AnimationUtils.loadAnimation(mActivity, 2131034125);
      }
      mAttachmentPanel.setInAnimation(mSlideRightInAnimation);
      mAttachmentPanel.setOutAnimation(mSlideRightOutAnimation);
    }
    for (;;)
    {
      mAttachmentPanel.showNext();
      return;
      mAttachmentPanel.setInAnimation(null);
      mAttachmentPanel.setOutAnimation(null);
    }
  }
  
  public void handleAddAttachmentError(final int paramInt1, final int paramInt2)
  {
    if (paramInt1 == 0) {
      return;
    }
    mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = mActivity.getResources();
        String str2 = ((Resources)localObject).getString(paramInt2);
        String str1;
        switch (paramInt1)
        {
        default: 
          throw new IllegalArgumentException("unknown error " + paramInt1);
        case -1: 
          localObject = ((Resources)localObject).getString(2131361853, new Object[] { str2 });
          Toast.makeText(mActivity, (CharSequence)localObject, 0).show();
          return;
        case -3: 
          str1 = ((Resources)localObject).getString(2131361847, new Object[] { str2 });
          localObject = ((Resources)localObject).getString(2131361848, new Object[] { str2 });
        }
        for (;;)
        {
          MessageUtils.showErrorDialog(mActivity, str1, (String)localObject);
          return;
          str1 = ((Resources)localObject).getString(2131361849, new Object[] { str2 });
          localObject = ((Resources)localObject).getString(2131361853, new Object[] { str2 });
          continue;
          str1 = ((Resources)localObject).getString(2131361854);
          localObject = ((Resources)localObject).getString(2131361855);
        }
      }
    });
  }
  
  public void handleAttachentMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    case 2131820875: 
      editSlideshow();
      return;
    case 2131820550: 
    case 2131820666: 
    case 2131820874: 
    case 2131820896: 
      viewAttachment();
      return;
    case 2131820667: 
      onAttachmentTypeClick(3);
      return;
    case 2131820897: 
      onAttachmentTypeClick(10);
      return;
    case 2131820551: 
      onAttachmentTypeClick(9);
      return;
    }
    mActivity.getWorkingMessage().removeAttachment(true);
  }
  
  public boolean isAttachmentPanelVisible()
  {
    return (mAttachmentPanel != null) && (mAttachmentPanel.getVisibility() != 8);
  }
  
  public boolean isOnAttachmentPanel()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mAttachmentPanel != null)
    {
      bool1 = bool2;
      if (mAttachmentPanel.getDisplayedChild() == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isSmileyPanelVisible()
  {
    return (mSmileyTabWidget != null) && (mSmileyTabWidget.getVisibility() != 8);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    final Object localObject1 = mActivity.getWorkingMessage();
    if (((WorkingMessage)localObject1).isFakeMmsForDraft()) {
      ((WorkingMessage)localObject1).removeFakeMmsForDraft();
    }
    if (paramInt2 != -1) {
      return;
    }
    switch (paramInt1)
    {
    case 107: 
    case 108: 
    case 110: 
    default: 
      paramIntent = mActivity;
      if ((!mActivity.isVisible(mActivity.mTextEditor)) && (!mActivity.isVisible(mActivity.mSubjectTextEditor))) {
        break;
      }
    }
    for (boolean bool = true;; bool = false)
    {
      paramIntent.delayedShowSoftKeyboard(bool);
      return;
      if (paramIntent == null) {
        break;
      }
      localObject1 = mActivity.getWorkingMessage();
      if (localObject1 == null) {
        break;
      }
      ((WorkingMessage)localObject1).loadFromUri(paramIntent.getData(), false);
      mActivity.getAttachmentView().update((WorkingMessage)localObject1);
      mActivity.drawBottomPanel();
      updateAttachmentTypeStates();
      break;
      addImage(Uri.fromFile(new File(TempFileProvider.getScrapPath(mActivity))), false);
      break;
      if (paramIntent == null) {
        break;
      }
      addImage(paramIntent.getData(), false);
      break;
      if (paramIntent == null) {
        break;
      }
      addVideo(paramIntent.getData(), false);
      break;
      paramIntent = (Uri)paramIntent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
      if (Settings.System.DEFAULT_RINGTONE_URI.equals(paramIntent)) {
        break;
      }
      addAudio(paramIntent, false);
      break;
      if (paramIntent == null) {
        break;
      }
      addAudio(paramIntent.getData(), false);
      break;
      if (paramIntent == null) {
        break;
      }
      localObject1 = mActivity.getTextEditor().getText();
      final Object localObject2 = new StringBuilder();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        String str = localObject1.toString().trim();
        if ((!TextUtils.isEmpty(str)) && (str.charAt(str.length() - 1) != '\r')) {
          ((StringBuilder)localObject2).append("\n");
        }
      }
      ((StringBuilder)localObject2).append(ContactParser.getContactParser(mActivity).getContactFromIntent(paramIntent));
      ((Editable)localObject1).insert(mActivity.getTextEditor().getSelectionStart(), (CharSequence)localObject2);
      break;
      paramIntent = Uri.encode((String)paramIntent.getData().getPathSegments().get(2));
      addVCard(ContactsContract.Contacts.CONTENT_VCARD_URI.buildUpon().appendPath(paramIntent).appendQueryParameter("nophoto", "true").build(), false);
      break;
      if (mPhraseListAdapter == null) {
        break;
      }
      mPhraseListAdapter.load();
      break;
      if (paramIntent == null) {
        break;
      }
      localObject1 = (Uri)paramIntent.getParcelableExtra("msg_uri");
      localObject2 = paramIntent.getStringExtra("subject");
      if (localObject1 != null)
      {
        paramIntent = mActivity.getWorkingMessage();
        if ((paramIntent != null) && (!paramIntent.isWorthSaving()) && (!mActivity.containChenghu()))
        {
          paramIntent.loadFromUri((Uri)localObject1, true);
          paramIntent.setSubject((CharSequence)localObject2, false);
          mActivity.getAttachmentView().update(paramIntent);
          mActivity.drawTopPanel();
          mActivity.drawBottomPanel();
          updateAttachmentTypeStates();
          return;
        }
        paramIntent = new AlertDialog.Builder(mActivity);
        paramIntent.setMessage(2131362255);
        paramIntent.setPositiveButton(17039379, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            WorkingMessage localWorkingMessage = mActivity.getWorkingMessage();
            if (localWorkingMessage != null)
            {
              localWorkingMessage.removeAttachment(false);
              mActivity.getTextEditor().setText("");
              localWorkingMessage.loadFromUri(localObject1, true, true);
              localWorkingMessage.setSubject(localObject2, true);
              mActivity.getAttachmentView().update(localWorkingMessage);
              mActivity.drawTopPanel();
              mActivity.drawBottomPanel();
              updateAttachmentTypeStates();
            }
            paramAnonymousDialogInterface.dismiss();
          }
        });
        paramIntent.setNegativeButton(17039360, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.dismiss();
          }
        });
        paramIntent.show();
        return;
      }
      if (paramIntent == null) {
        break;
      }
      localObject1 = mActivity.getTextEditor();
      ((EditText)localObject1).getText().insert(((EditText)localObject1).getSelectionStart(), paramIntent.getStringExtra("android.intent.extra.shortcut.NAME"));
      break;
    }
  }
  
  public void onAttachmentTypeClick(int paramInt)
  {
    Intent localIntent;
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      gotoSmileyPanel(true);
      return;
    case 1: 
      if (MessageUtils.isSendingContactByVCard(mActivity.mSharedPrefs))
      {
        localIntent = new Intent("android.intent.action.PICK");
        localIntent.setType("vnd.android.cursor.dir/contact");
        localIntent.setPackage(MessageUtils.getContactsPackageName());
        mActivity.startActivityForResult(localIntent, 114);
        return;
      }
      localIntent = new Intent("android.intent.action.PICK");
      localIntent.setType("vnd.android.cursor.dir/contact_pick_single");
      localIntent.putExtra("android.intent.extra.include_unknown_numbers", true);
      localIntent.setPackage(MessageUtils.getContactsPackageName());
      mActivity.startActivityForResult(localIntent, 109);
      return;
    case 2: 
      mActivity.insertChenghu();
      return;
    case 3: 
      mActivity.confirmRemovingChenghu(new Runnable()
      {
        public void run()
        {
          MessageUtils.selectImage(mActivity, 100);
        }
      }, null);
      return;
    case 4: 
      mActivity.confirmRemovingChenghu(new Runnable()
      {
        public void run()
        {
          Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
          localIntent.putExtra("output", TempFileProvider.SCRAP_CONTENT_URI);
          mActivity.startActivityForResult(localIntent, 101);
        }
      }, null);
      return;
    case 6: 
      localIntent = new Intent(mActivity, PickerActivity.class);
      localIntent.setAction("android.intent.action.PICK");
      localIntent.putExtra("pick_type", 0);
      mActivity.startActivityForResult(localIntent, 112);
      return;
    case 10: 
      mActivity.confirmRemovingChenghu(new Runnable()
      {
        public void run()
        {
          AttachmentProcessor.this.onVideoMenuClick();
        }
      }, null);
      return;
    case 5: 
      localIntent = new Intent(mActivity, PickerActivity.class);
      localIntent.setAction("android.intent.action.PICK");
      localIntent.putExtra("pick_type", 1);
      mActivity.startActivityForResult(localIntent, 111);
      return;
    case 9: 
      mActivity.confirmRemovingChenghu(new Runnable()
      {
        public void run()
        {
          AttachmentProcessor.this.onAudioMenuClick();
        }
      }, null);
      return;
    case 11: 
      mActivity.confirmRemovingChenghu(new Runnable()
      {
        public void run()
        {
          AttachmentProcessor.this.editSlideshow();
        }
      }, null);
      return;
    case 7: 
      if (mActivity.getWorkingMessage().getTimeToSend() == 0L)
      {
        mActivity.setTiming();
        return;
      }
      mActivity.cancelTiming();
      return;
    }
    mActivity.confirmRemovingChenghu(new Runnable()
    {
      public void run()
      {
        mActivity.toggleSubject();
      }
    }, null);
  }
  
  public void updateAttachmentTypeStates()
  {
    int i;
    View localView;
    boolean bool;
    if (mActivity.getWorkingMessage().hasSlideshow())
    {
      i = 0;
      if (i < mAttachmentTypes.length)
      {
        if (mAttachmentTypes[i] != null)
        {
          localView = mAttachmentTypes[i];
          if (ATTACHMENT_STATE_FOR_SLIDESHOW[i] <= 0) {
            break label62;
          }
        }
        label62:
        for (bool = true;; bool = false)
        {
          localView.setEnabled(bool);
          i += 1;
          break;
        }
      }
    }
    else if (mActivity.getWorkingMessage().requiresMms())
    {
      i = 0;
      if (i < mAttachmentTypes.length)
      {
        if (mAttachmentTypes[i] != null)
        {
          localView = mAttachmentTypes[i];
          if (ATTACHMENT_STATE_FOR_MMS[i] <= 0) {
            break label129;
          }
        }
        label129:
        for (bool = true;; bool = false)
        {
          localView.setEnabled(bool);
          i += 1;
          break;
        }
      }
    }
    else
    {
      i = 0;
      while (i < mAttachmentTypes.length)
      {
        if (mAttachmentTypes[i] != null) {
          mAttachmentTypes[i].setEnabled(true);
        }
        i += 1;
      }
    }
  }
  
  private class BackTouchListener
    implements View.OnTouchListener
  {
    private int mCurrentMessageToken = 0;
    private boolean mIsInside = false;
    
    private BackTouchListener() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      switch (paramMotionEvent.getActionMasked())
      {
      default: 
        return false;
      case 0: 
        AttachmentProcessor.this.backspace();
        mCurrentMessageToken += 1;
        mIsInside = true;
        mActivity.mHandler.postDelayed(new BackspaceExecutor(mCurrentMessageToken), 500L);
        return false;
      case 1: 
      case 3: 
        mCurrentMessageToken += 1;
        return false;
      }
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if ((i >= 0) && (i < paramView.getWidth()) && (j >= 0) && (j < paramView.getHeight()))
      {
        mIsInside = true;
        return false;
      }
      mIsInside = false;
      return false;
    }
    
    private class BackspaceExecutor
      implements Runnable
    {
      private int mMessageToken;
      
      BackspaceExecutor(int paramInt)
      {
        mMessageToken = paramInt;
      }
      
      public void run()
      {
        if (mMessageToken == mCurrentMessageToken)
        {
          mActivity.mHandler.postDelayed(this, 100L);
          if (mIsInside) {
            AttachmentProcessor.this.backspace();
          }
        }
      }
    }
  }
  
  public static class ProgressDialogFragment
    extends DialogFragment
  {
    public Dialog onCreateDialog(Bundle paramBundle)
    {
      paramBundle = getActivity();
      ProgressDialog localProgressDialog = new ProgressDialog(paramBundle);
      localProgressDialog.setMessage(paramBundle.getString(2131362161));
      return localProgressDialog;
    }
  }
  
  private class SmileyGridInitializer
    implements StaticGridView.Initializer
  {
    private int mCategory;
    private int mColumnCount;
    private int mRowCount;
    private int mScreenIndex;
    
    SmileyGridInitializer(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      mCategory = paramInt1;
      mScreenIndex = paramInt2;
      mRowCount = paramInt3;
      mColumnCount = paramInt4;
    }
    
    public void onInitialize(StaticGridView paramStaticGridView)
    {
      AttachmentProcessor.this.loadSmileyGridView(paramStaticGridView, mCategory, mScreenIndex, mRowCount, mColumnCount);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */