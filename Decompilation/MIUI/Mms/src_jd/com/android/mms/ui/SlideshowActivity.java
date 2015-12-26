package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.dom.AttrImpl;
import com.android.mms.dom.smil.SmilPlayer;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.Model;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.SmilHelper;
import com.google.android.mms.MmsException;
import com.google.android.mms.util.SqliteWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import miui.app.Activity;
import miui.app.OnStatusBarChangeListener;
import miui.os.Build;
import miui.view.menu.ContextMenuDialog;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;

public class SlideshowActivity
  extends Activity
  implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, ISmsTextSizeAdjustHost, EventListener
{
  private static final String[] MMS_PROJECT = { "sub", "sub_cs", "date", "date_ms_part", "date_sent", "timed" };
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
  
  private void gainFocus()
  {
    ((AudioManager)getSystemService("audio")).requestAudioFocus(null, 3, 1);
  }
  
  private void initMediaController()
  {
    mMediaController = new MediaController(this, false);
    mMediaController.setMediaPlayer(new SmilPlayerController(mSmilPlayer));
    mMediaController.setAnchorView(findViewById(2131820655));
    mMediaController.setPrevNextListeners(new View.OnClickListener()new View.OnClickListener
    {
      public void onClick(View paramAnonymousView)
      {
        mSmilPlayer.next();
      }
    }, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mSmilPlayer.prev();
      }
    });
  }
  
  private static final boolean isMMSConformance(SMILDocument paramSMILDocument)
  {
    paramSMILDocument = paramSMILDocument.getHead();
    if (paramSMILDocument == null) {
      return false;
    }
    paramSMILDocument = paramSMILDocument.getChildNodes();
    if ((paramSMILDocument == null) || (paramSMILDocument.getLength() != 1)) {
      return false;
    }
    paramSMILDocument = paramSMILDocument.item(0);
    if ((paramSMILDocument == null) || (!"layout".equals(paramSMILDocument.getNodeName()))) {
      return false;
    }
    paramSMILDocument = paramSMILDocument.getChildNodes();
    if (paramSMILDocument == null) {
      return false;
    }
    int k = paramSMILDocument.getLength();
    if (k <= 0) {
      return false;
    }
    int i = 0;
    while (i < k)
    {
      Object localObject1 = paramSMILDocument.item(i);
      if (localObject1 == null) {
        return false;
      }
      Object localObject2 = ((Node)localObject1).getNodeName();
      if ("root-layout".equals(localObject2))
      {
        i += 1;
      }
      else
      {
        if ("region".equals(localObject2))
        {
          localObject1 = ((Node)localObject1).getAttributes();
          int j = 0;
          label160:
          String str;
          if (j < ((NamedNodeMap)localObject1).getLength())
          {
            localObject2 = ((NamedNodeMap)localObject1).item(j);
            if (localObject2 == null) {
              return false;
            }
            str = ((Node)localObject2).getNodeName();
            if ((!"left".equals(str)) && (!"top".equals(str)) && (!"height".equals(str)) && (!"width".equals(str)) && (!"fit".equals(str))) {
              break label254;
            }
          }
          label254:
          do
          {
            j += 1;
            break label160;
            break;
            if (!"id".equals(str)) {
              break label306;
            }
            if (!(localObject2 instanceof AttrImpl)) {
              break label304;
            }
            localObject2 = ((AttrImpl)localObject2).getValue();
          } while (("Text".equals(localObject2)) || ("Image".equals(localObject2)));
          return false;
          label304:
          return false;
          label306:
          return false;
        }
        return false;
      }
    }
    return true;
  }
  
  private void onFlatShowCreate()
  {
    getWindow().setFlags(8388608, 8388608);
    final Uri localUri = getIntent().getData();
    setContentView(2130968619);
    setSubjectAndDate(localUri);
    ((Button)findViewById(2131820651)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        finish();
      }
    });
    View localView = findViewById(2131820654);
    if ((Build.IS_CM_CUSTOMIZATION_TEST) || (Build.IS_CU_CUSTOMIZATION_TEST))
    {
      localView.setVisibility(0);
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(SlideshowActivity.this, SlideshowActivity.class);
          paramAnonymousView.setData(localUri);
          paramAnonymousView.putExtra("useslide", true);
          startActivity(paramAnonymousView);
        }
      });
    }
    mFlatshowListView = ((ListView)findViewById(2131820655));
    localView = getLayoutInflater().inflate(2130968621, null);
    mFlatshowListView.addHeaderView(localView);
    mFlatshowAdapter = new FlatshowAdapter(localUri);
    mFlatshowListView.setAdapter(mFlatshowAdapter);
    mSlideshowHeader = ((SlideshowHeader)findViewById(2131820650));
    setOnStatusBarChangeListener(new OnStatusBarChangeListener()
    {
      public void onStatusBarHeightChange(int paramAnonymousInt)
      {
        if (mStatusBarHeight == paramAnonymousInt) {
          return;
        }
        mSlideshowHeader.setStatusBarHeight(paramAnonymousInt);
        mSlideshowHeader.setPadding(mSlideshowHeader.getPaddingLeft(), mSlideshowHeader.getPaddingTop() - mStatusBarHeight + paramAnonymousInt, mSlideshowHeader.getPaddingRight(), mSlideshowHeader.getPaddingBottom());
        SlideshowActivity.access$202(SlideshowActivity.this, paramAnonymousInt);
      }
    });
  }
  
  private void onSlideShowCreate()
  {
    setContentView(2130968708);
    final Object localObject = getIntent().getData();
    try
    {
      localObject = SlideshowModel.createFromMessageUri(this, (Uri)localObject);
      mSlideView = ((SlideView)findViewById(2131820655));
      PresenterFactory.getPresenter("SlideshowPresenter", this, mSlideView, (Model)localObject);
      gainFocus();
      mHandler.post(new Runnable()
      {
        private boolean isRotating()
        {
          return (mSmilPlayer.isPausedState()) || (mSmilPlayer.isPlayingState()) || (mSmilPlayer.isPlayedState());
        }
        
        public void run()
        {
          SlideshowActivity.access$402(SlideshowActivity.this, SmilPlayer.getPlayer());
          SlideshowActivity.this.initMediaController();
          mSlideView.setMediaController(mMediaController);
          SlideshowActivity.access$802(SlideshowActivity.this, SmilHelper.getDocument(localObject));
          if (SlideshowActivity.isMMSConformance(mSmilDoc))
          {
            int k = 0;
            int i = 0;
            int n = 0;
            int j = 0;
            int i2 = 0;
            int i3 = 0;
            Object localObject = localObject.getLayout();
            int i1 = i2;
            int m = i3;
            if (localObject != null)
            {
              RegionModel localRegionModel = ((LayoutModel)localObject).getImageRegion();
              if (localRegionModel != null)
              {
                i = localRegionModel.getLeft();
                j = localRegionModel.getTop();
              }
              localObject = ((LayoutModel)localObject).getTextRegion();
              k = i;
              n = j;
              i1 = i2;
              m = i3;
              if (localObject != null)
              {
                i1 = ((RegionModel)localObject).getLeft();
                m = ((RegionModel)localObject).getTop();
                n = j;
                k = i;
              }
            }
            mSlideView.enableMMSConformanceMode(i1, m, k, n);
          }
          ((EventTarget)mSmilDoc).addEventListener("SimlDocumentEnd", SlideshowActivity.this, false);
          mSmilPlayer.init(mSmilDoc);
          if (isRotating())
          {
            mSmilPlayer.reload();
            return;
          }
          mSmilPlayer.play();
        }
      });
      return;
    }
    catch (MmsException localMmsException)
    {
      Log.e("SlideshowActivity", "Cannot present the slide show.", localMmsException);
      finish();
    }
  }
  
  private void pauseAndHideController()
  {
    if (mMediaController != null) {
      mMediaController.hide();
    }
    if (mSmilPlayer != null) {
      mSmilPlayer.pause();
    }
  }
  
  private void resetTextView()
  {
    if ((mFlatshowListView != null) && (mFlatshowListView.getVisibility() != 8)) {
      mFlatshowListView.requestFocus();
    }
  }
  
  private void setSubjectAndDate(Uri paramUri)
  {
    Object localObject = (TextView)findViewById(2131820652);
    TextView localTextView = (TextView)findViewById(2131820653);
    Cursor localCursor = SqliteWrapper.query(this, getContentResolver(), paramUri, MMS_PROJECT, null, null, null);
    if (localCursor != null) {}
    for (;;)
    {
      try
      {
        if (localCursor.getCount() > 0)
        {
          localCursor.moveToFirst();
          paramUri = MessageUtils.extractEncStrFromCursor(localCursor, 0, 1);
          if (!TextUtils.isEmpty(paramUri))
          {
            MessageUtils.showTextWithHighlight((TextView)localObject, paramUri, mHighlight, 2131689541);
            paramUri = PreferenceManager.getDefaultSharedPreferences(this);
            long l2 = localCursor.getLong(2) * 1000L + localCursor.getLong(3);
            long l3 = localCursor.getLong(4) * 1000L;
            long l4 = localCursor.getLong(5);
            long l1 = l2;
            if (l3 > 0L)
            {
              l1 = l2;
              if ("1".equals(MessageUtils.getDateType(paramUri))) {
                l1 = l3;
              }
            }
            localObject = MessageUtils.getPreciseTimeStamp(l1, true);
            paramUri = (Uri)localObject;
            if (localObject == null) {
              break label293;
            }
            paramUri = (Uri)localObject;
            if (l4 <= 0L) {
              break label293;
            }
            paramUri = getResources().getString(2131362154, new Object[] { localObject });
            break label293;
            localTextView.setText((CharSequence)localObject);
            return;
          }
          ((TextView)localObject).setText(2131362002);
          continue;
        }
        ((TextView)localObject).setText(2131362002);
      }
      finally
      {
        localCursor.close();
      }
      localTextView.setText("");
      continue;
      ((TextView)localObject).setText(2131362002);
      localTextView.setText("");
      return;
      label293:
      localObject = paramUri;
      if (paramUri == null) {
        localObject = "";
      }
    }
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(paramMotionEvent);
    boolean bool1 = bool2;
    if (!bool2) {
      if (mGestureDetector != null) {
        mGestureDetector.onTouchEvent(paramMotionEvent);
      }
    }
    try
    {
      bool1 = super.dispatchTouchEvent(paramMotionEvent);
      return bool1;
    }
    catch (ActivityNotFoundException paramMotionEvent)
    {
      Toast.makeText(this, getString(2131362136), 0).show();
    }
    return true;
  }
  
  public void handleEvent(final Event paramEvent)
  {
    mHandler.post(new Runnable()
    {
      public void run()
      {
        if (paramEvent.getType().equals("SimlDocumentEnd")) {
          finish();
        }
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MessageUtils.loadFontSizeConfiguration(this);
    mHandler = new Handler();
    paramBundle = getIntent();
    mHighlight = paramBundle.getStringExtra("highlight");
    boolean bool = paramBundle.getBooleanExtra("useslide", false);
    if (((Build.IS_CM_CUSTOMIZATION_TEST) || (Build.IS_CU_CUSTOMIZATION_TEST)) && (bool))
    {
      onSlideShowCreate();
      return;
    }
    onFlatShowCreate();
  }
  
  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  public boolean onDown(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    pauseAndHideController();
    return false;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      if ((mSmilPlayer != null) && (mMediaController != null)) {
        mMediaController.show(0);
      }
      break;
    }
    for (;;)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      if ((mSmilPlayer != null) && ((mSmilPlayer.isPausedState()) || (mSmilPlayer.isPlayingState()) || (mSmilPlayer.isPlayedState()))) {
        mSmilPlayer.stop();
      }
    }
  }
  
  public void onLongPress(MotionEvent paramMotionEvent) {}
  
  public boolean onMenuOpened(int paramInt, Menu paramMenu)
  {
    return false;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    finish();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    if (mSmilDoc != null) {
      ((EventTarget)mSmilDoc).removeEventListener("SimlDocumentEnd", this, false);
    }
    if (mSmilPlayer != null) {
      mSmilPlayer.pause();
    }
  }
  
  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if (Math.abs(paramFloat2) > 10.0F) {
      pauseAndHideController();
    }
    return false;
  }
  
  public void onShowPress(MotionEvent paramMotionEvent) {}
  
  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    if (mMediaController != null)
    {
      if (mMediaController.isShowing()) {
        mMediaController.hide();
      }
    }
    else {
      return false;
    }
    mMediaController.show(0);
    return false;
  }
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  protected void onStart()
  {
    super.onStart();
    SmsTextSizeAdjust.getInstance().init(this, this);
    SmsTextSizeAdjust.getInstance().refresh();
    resetTextView();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (mSmilPlayer != null)
    {
      if (!isFinishing()) {
        break label44;
      }
      mSmilPlayer.stop();
    }
    for (;;)
    {
      if (mMediaController != null) {
        mMediaController.hide();
      }
      SmsTextSizeAdjust.clear(this);
      return;
      label44:
      mSmilPlayer.stopWhenReload();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mSmilPlayer != null) && (mMediaController != null)) {
      mMediaController.show();
    }
    return false;
  }
  
  public void setTextSize(float paramFloat)
  {
    if (mFlatshowAdapter != null) {
      mFlatshowAdapter.setTextSize(paramFloat);
    }
    if ((mFlatshowListView != null) && (mFlatshowListView.getVisibility() == 0))
    {
      int j = mFlatshowListView.getCount();
      int i = 0;
      while (i < j)
      {
        View localView = mFlatshowListView.getChildAt(i);
        if ((localView != null) && ((localView instanceof TextView))) {
          ((TextView)localView).setTextSize(0, paramFloat);
        }
        i += 1;
      }
    }
    if (mSlideView != null) {
      mSlideView.setTextSize(paramFloat);
    }
  }
  
  private class FlatshowAdapter
    extends BaseAdapter
  {
    private SimplePduDoc mDoc = new SimplePduDoc(SlideshowActivity.this);
    private ArrayList<Object> mElements = new ArrayList();
    private String mMsgId;
    private float mTextSize;
    
    public FlatshowAdapter(Uri paramUri)
    {
      mDoc.load(paramUri);
      mMsgId = paramUri.getLastPathSegment();
      if ((Build.IS_CM_CUSTOMIZATION) || (Build.IS_CU_CUSTOMIZATION))
      {
        loadElementsWithLayout();
        return;
      }
      loadElements();
    }
    
    private void addAudioNameView(LinearLayout paramLinearLayout, TextView paramTextView)
    {
      if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramTextView != null) && (paramLinearLayout.getChildCount() == 1)) {
        paramLinearLayout.addView(paramTextView);
      }
    }
    
    private TextView getAudioNameView(LinearLayout paramLinearLayout)
    {
      if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramLinearLayout.getChildCount() > 1)) {
        return (TextView)paramLinearLayout.getChildAt(1);
      }
      return null;
    }
    
    private void loadElements()
    {
      int i = 0;
      while (i < mDoc.getPageSize())
      {
        SimplePduPage localSimplePduPage = mDoc.getPage(i);
        if (localSimplePduPage != null)
        {
          int j = 0;
          while (j < localSimplePduPage.getPartSize())
          {
            SimplePduPart localSimplePduPart = localSimplePduPage.getPart(j);
            mElements.add(localSimplePduPart);
            j += 1;
          }
        }
        mElements.add(Integer.valueOf(i + 1));
        i += 1;
      }
    }
    
    private void loadElementsWithLayout()
    {
      boolean bool = mDoc.isLayoutImageBottom();
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (i < mDoc.getPageSize())
      {
        SimplePduPage localSimplePduPage = mDoc.getPage(i);
        if (localSimplePduPage != null)
        {
          int j = 0;
          if (j < localSimplePduPage.getPartSize())
          {
            SimplePduPart localSimplePduPart = localSimplePduPage.getPart(j);
            if ((bool) && (localSimplePduPart.getAttachmentType() == 0)) {
              mElements.add(localSimplePduPart);
            }
            for (;;)
            {
              j += 1;
              break;
              if ((!bool) && ((localSimplePduPart.getAttachmentType() == 1) || (localSimplePduPart.getAttachmentType() == 2))) {
                mElements.add(localSimplePduPart);
              } else {
                localArrayList.add(localSimplePduPart);
              }
            }
          }
          if (localArrayList.size() > 0) {
            mElements.addAll(localArrayList);
          }
          localArrayList.clear();
        }
        mElements.add(Integer.valueOf(i + 1));
        i += 1;
      }
    }
    
    private TextView setAudioName(TextView paramTextView, String paramString, LayoutInflater paramLayoutInflater)
    {
      if (Build.IS_CM_CUSTOMIZATION_TEST)
      {
        TextView localTextView = paramTextView;
        if (!TextUtils.isEmpty(paramString))
        {
          if (paramTextView == null) {
            localTextView = (TextView)paramLayoutInflater.inflate(2130968622, null);
          }
          localTextView.setVisibility(0);
          localTextView.setText(paramString);
          paramTextView = localTextView;
        }
        do
        {
          return paramTextView;
          paramTextView = localTextView;
        } while (localTextView == null);
        localTextView.setVisibility(8);
        return localTextView;
      }
      return paramTextView;
    }
    
    public int getCount()
    {
      return mElements.size();
    }
    
    public Object getItem(int paramInt)
    {
      return mElements.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public int getItemViewType(int paramInt)
    {
      Object localObject = mElements.get(paramInt);
      if ((localObject instanceof Integer)) {
        return 0;
      }
      if ((localObject instanceof SimplePduPart))
      {
        if (((SimplePduPart)localObject).getAttachmentType() == 0) {
          return 1;
        }
        return 2;
      }
      throw new IllegalStateException("Cannot recognize flatshow view type");
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = mElements.get(paramInt);
      LayoutInflater localLayoutInflater = LayoutInflater.from(SlideshowActivity.this);
      int i;
      int j;
      if ((paramViewGroup instanceof Integer))
      {
        paramInt = ((Integer)paramViewGroup).intValue();
        if (paramView != null) {}
        for (;;)
        {
          i = getResources().getDimensionPixelOffset(2131427341);
          j = getResources().getDimensionPixelOffset(2131427342);
          paramView.setPadding(i, j, i, j);
          ((TextView)paramView.findViewById(2131820656)).setText(String.format(getString(2131362134), new Object[] { Integer.valueOf(paramInt) }));
          return paramView;
          paramView = localLayoutInflater.inflate(2130968620, null, false);
        }
      }
      if ((paramViewGroup instanceof SimplePduPart))
      {
        paramInt = getResources().getDimensionPixelOffset(2131427341);
        i = getResources().getDimensionPixelOffset(2131427342);
        final SimplePduPart localSimplePduPart = (SimplePduPart)paramViewGroup;
        Object localObject2 = null;
        Drawable localDrawable = null;
        ImageView localImageView = null;
        Object localObject1 = null;
        TextView localTextView = null;
        int k;
        switch (localSimplePduPart.getAttachmentType())
        {
        default: 
          localObject1 = localObject2;
          paramViewGroup = localDrawable;
          if (localImageView != null)
          {
            MessageUtils.setAttachmentImage(localImageView, paramViewGroup, true);
            localImageView.setClickable(true);
            localImageView.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                try
                {
                  startActivity(localSimplePduPart.getIntent());
                  return;
                }
                catch (ActivityNotFoundException paramAnonymousView)
                {
                  Toast.makeText(SlideshowActivity.this, getString(2131362136), 0).show();
                }
              }
            });
            localImageView.setOnLongClickListener(new View.OnLongClickListener()
            {
              public boolean onLongClick(View paramAnonymousView)
              {
                paramAnonymousView = new ContextMenuDialog(SlideshowActivity.this);
                paramAnonymousView.addMenuItem(2131362012, new Runnable()
                {
                  public void run()
                  {
                    String str = MessageUtils.saveMmsPartToDisk(SlideshowActivity.this, val$part, mMsgId);
                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(SlideshowActivity.this);
                    localBuilder.setTitle(2131362012);
                    localBuilder.setIconAttribute(16843605);
                    if (str != null) {
                      localBuilder.setMessage(getString(2131362013, new Object[] { str }));
                    }
                    for (;;)
                    {
                      localBuilder.setNeutralButton(17039370, null);
                      localBuilder.show();
                      return;
                      localBuilder.setMessage(2131362014);
                    }
                  }
                });
                paramAnonymousView.show();
                return true;
              }
            });
            if (paramViewGroup != null)
            {
              j = paramViewGroup.getIntrinsicHeight();
              k = paramViewGroup.getIntrinsicWidth();
              if (k > 200) {
                break label813;
              }
              localImageView.setLayoutParams(new LinearLayout.LayoutParams(k * 3 / 2, j * 3 / 2));
            }
            label327:
            if (paramView != null) {
              break label867;
            }
            localObject1 = new LinearLayout(SlideshowActivity.this);
            ((LinearLayout)localObject1).setGravity(3);
            ((LinearLayout)localObject1).setOrientation(1);
            ((LinearLayout)localObject1).addView(localImageView);
            addAudioNameView((LinearLayout)localObject1, localTextView);
          }
          break;
        }
        for (;;)
        {
          if (localObject1 != null) {
            ((View)localObject1).setPadding(paramInt, i, paramInt, i);
          }
          return (View)localObject1;
          if (paramView != null)
          {
            paramViewGroup = (TextView)paramView;
            label411:
            MessageUtils.showTextWithHighlight(paramViewGroup, localSimplePduPart.getText(), mHighlight, 2131689520);
            paramViewGroup.setTextSize(0, mTextSize);
            if (!Build.IS_CM_CUSTOMIZATION) {
              break label491;
            }
            paramViewGroup.setLinksClickable(false);
            paramViewGroup.setTextIsSelectable(false);
            paramViewGroup.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                ContextMenuDialog localContextMenuDialog = new ContextMenuDialog(SlideshowActivity.this);
                paramAnonymousView = ((TextView)paramAnonymousView).getUrls();
                HashSet localHashSet = new HashSet();
                if (paramAnonymousView.length == 1)
                {
                  paramAnonymousView = MessageUtils.getUriInfo(paramAnonymousView[0].getURL());
                  MessageUtils.performUriOperation(SlideshowActivity.this, paramAnonymousView, null);
                  return;
                }
                int i = 0;
                while (i < paramAnonymousView.length)
                {
                  final Object localObject = paramAnonymousView[i].getURL();
                  if (!localHashSet.contains(localObject))
                  {
                    localHashSet.add(localObject);
                    localObject = MessageUtils.getUriInfo((String)localObject);
                    localContextMenuDialog.addMenuItem(title, new Runnable()
                    {
                      public void run()
                      {
                        MessageUtils.performUriOperation(SlideshowActivity.this, localObject, null);
                      }
                    });
                  }
                  i += 1;
                }
                localContextMenuDialog.setTitle(2131362018);
                localContextMenuDialog.show();
              }
            });
          }
          for (;;)
          {
            localObject1 = paramViewGroup;
            paramViewGroup = localDrawable;
            break;
            paramViewGroup = (TextView)localLayoutInflater.inflate(2130968622, null);
            break label411;
            label491:
            paramViewGroup.setTextIsSelectable(true);
            paramViewGroup.setLinksClickable(true);
          }
          if (paramView != null) {}
          for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(SlideshowActivity.this))
          {
            paramViewGroup = localSimplePduPart.getImageNoCache(300, 300);
            localObject1 = localObject2;
            break;
          }
          paramViewGroup = null;
          if (Build.IS_CM_CUSTOMIZATION_TEST) {
            paramViewGroup = localSimplePduPart.getPduPartName();
          }
          if (paramView != null)
          {
            localObject1 = (LinearLayout)paramView;
            localImageView = (ImageView)((LinearLayout)localObject1).getChildAt(0);
            localObject1 = getAudioNameView((LinearLayout)localObject1);
          }
          for (;;)
          {
            localDrawable = getResources().getDrawable(2130837620);
            localTextView = setAudioName((TextView)localObject1, paramViewGroup, localLayoutInflater);
            paramViewGroup = localDrawable;
            localObject1 = localObject2;
            break;
            localImageView = new ImageView(SlideshowActivity.this);
          }
          if (paramView != null) {}
          for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(SlideshowActivity.this))
          {
            paramViewGroup = getResources().getDrawable(2130837634);
            localObject1 = localObject2;
            break;
          }
          if (paramView != null) {}
          for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(SlideshowActivity.this))
          {
            paramViewGroup = getResources().getDrawable(2130837633);
            localObject1 = localObject2;
            break;
          }
          if (paramView != null) {}
          for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(SlideshowActivity.this))
          {
            paramViewGroup = getResources().getDrawable(2130837624);
            localObject1 = localObject2;
            break;
          }
          label813:
          if (k > 300)
          {
            localImageView.setLayoutParams(new LinearLayout.LayoutParams(300, j * 300 / k));
            break label327;
          }
          localImageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
          break label327;
          label867:
          if (localTextView == null)
          {
            paramViewGroup = getAudioNameView((LinearLayout)paramView);
            localObject1 = paramView;
            if (paramViewGroup != null)
            {
              paramViewGroup.setVisibility(8);
              localObject1 = paramView;
            }
          }
          else
          {
            addAudioNameView((LinearLayout)paramView, localTextView);
            localObject1 = paramView;
          }
        }
      }
      return null;
    }
    
    public int getViewTypeCount()
    {
      return 3;
    }
    
    public void setTextSize(float paramFloat)
    {
      mTextSize = paramFloat;
    }
  }
  
  private class SmilPlayerController
    implements MediaController.MediaPlayerControl
  {
    private final SmilPlayer mPlayer;
    
    public SmilPlayerController(SmilPlayer paramSmilPlayer)
    {
      mPlayer = paramSmilPlayer;
    }
    
    public boolean canPause()
    {
      return true;
    }
    
    public boolean canSeekBackward()
    {
      return true;
    }
    
    public boolean canSeekForward()
    {
      return true;
    }
    
    public int getAudioSessionId()
    {
      return 0;
    }
    
    public int getBufferPercentage()
    {
      return 100;
    }
    
    public int getCurrentPosition()
    {
      return mPlayer.getCurrentPosition();
    }
    
    public int getDuration()
    {
      return mPlayer.getDuration();
    }
    
    public boolean isPlaying()
    {
      if (mPlayer != null) {
        return mPlayer.isPlayingState();
      }
      return false;
    }
    
    public void pause()
    {
      if (mPlayer != null) {
        mPlayer.pause();
      }
    }
    
    public void seekTo(int paramInt) {}
    
    public void start()
    {
      if (mPlayer != null) {
        mPlayer.start();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */