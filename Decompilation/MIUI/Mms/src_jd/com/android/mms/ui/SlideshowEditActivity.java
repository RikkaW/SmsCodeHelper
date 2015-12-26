package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.google.android.mms.MmsException;
import miui.app.ListActivity;

public class SlideshowEditActivity
  extends ListActivity
{
  private View mAddSlideItem;
  private boolean mDirty;
  private ListView mList;
  private final IModelChangedObserver mModelChangedObserver = new IModelChangedObserver()
  {
    public void onModelChanged(Model arg1, boolean paramAnonymousBoolean)
    {
      synchronized (SlideshowEditActivity.this)
      {
        SlideshowEditActivity.access$002(SlideshowEditActivity.this, true);
        setResult(-1, mResultIntent);
        SlideshowEditActivity.this.adjustAddSlideVisibility();
        return;
      }
    }
  };
  private Intent mResultIntent;
  private SlideListAdapter mSlideListAdapter;
  private SlideshowEditor mSlideshowEditor = null;
  private SlideshowModel mSlideshowModel = null;
  private Bundle mState;
  private Uri mUri;
  
  private void addNewSlide()
  {
    if (mSlideshowEditor.addNewSlide())
    {
      mSlideListAdapter.notifyDataSetChanged();
      mList.requestFocus();
      mList.setSelection(mSlideshowModel.size() - 1);
      return;
    }
    Toast.makeText(this, 2131361870, 0).show();
  }
  
  private void adjustAddSlideVisibility()
  {
    if (mSlideshowModel.size() >= 20) {
      if (mList.getFooterViewsCount() > 0) {
        mList.removeFooterView(mAddSlideItem);
      }
    }
    while (mList.getFooterViewsCount() > 0) {
      return;
    }
    mList.addFooterView(mAddSlideItem);
  }
  
  private void cleanupSlideshowModel()
  {
    if (mSlideshowModel != null)
    {
      mSlideshowModel.unregisterModelChangedObserver(mModelChangedObserver);
      mSlideshowModel = null;
    }
  }
  
  private View createAddSlideItem()
  {
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130968711, null);
    ((TextView)localView.findViewById(2131820882)).setText(2131361826);
    TextView localTextView = (TextView)localView.findViewById(2131820884);
    localTextView.setText(2131361827);
    localTextView.setVisibility(0);
    ((ImageView)localView.findViewById(2131820881)).setImageResource(2130837685);
    return localView;
  }
  
  private void initSlideList()
    throws MmsException
  {
    cleanupSlideshowModel();
    mSlideshowModel = SlideshowModel.createFromMessageUri(this, mUri);
    mSlideshowModel.registerModelChangedObserver(mModelChangedObserver);
    mSlideshowEditor = new SlideshowEditor(this, mSlideshowModel);
    mSlideListAdapter = new SlideListAdapter(this, 2130968711, mSlideshowModel);
    mList.setAdapter(mSlideListAdapter);
  }
  
  private void openSlide(int paramInt)
  {
    Intent localIntent = new Intent(this, SlideEditorActivity.class);
    localIntent.setData(mUri);
    localIntent.putExtra("slide_index", paramInt);
    startActivityForResult(localIntent, 6);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {
      return;
    }
    switch (paramInt1)
    {
    default: 
      return;
    }
    try
    {
      mDirty = true;
      setResult(-1, mResultIntent);
      if ((paramIntent != null) && (paramIntent.getBooleanExtra("done", false)))
      {
        finish();
        return;
      }
    }
    finally {}
    try
    {
      initSlideList();
      adjustAddSlideVisibility();
      return;
    }
    catch (MmsException paramIntent)
    {
      Log.e("SlideshowEditActivity", "Failed to initialize the slide-list.", paramIntent);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mList = getListView();
    mList.setDivider(null);
    mAddSlideItem = createAddSlideItem();
    if (paramBundle != null) {
      mState = paramBundle.getBundle("state");
    }
    if (mState != null) {}
    for (mUri = Uri.parse(mState.getString("message_uri")); mUri == null; mUri = getIntent().getData())
    {
      Log.e("SlideshowEditActivity", "Cannot startup activity, null Uri.");
      finish();
      return;
    }
    mResultIntent = new Intent();
    mResultIntent.setData(mUri);
    try
    {
      initSlideList();
      adjustAddSlideVisibility();
      return;
    }
    catch (MmsException paramBundle)
    {
      Log.e("SlideshowEditActivity", "Failed to initialize the slide-list.", paramBundle);
      finish();
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    cleanupSlideshowModel();
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
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    if (paramView == mAddSlideItem)
    {
      addNewSlide();
      return;
    }
    openSlide(paramInt);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = mList.getSelectedItemPosition();
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      if ((i > 0) && (i < mSlideshowModel.size()))
      {
        mSlideshowEditor.moveSlideUp(i);
        mSlideListAdapter.notifyDataSetChanged();
        mList.setSelection(i - 1);
        continue;
        if ((i >= 0) && (i < mSlideshowModel.size() - 1))
        {
          mSlideshowEditor.moveSlideDown(i);
          mSlideListAdapter.notifyDataSetChanged();
          mList.setSelection(i + 1);
          continue;
          if ((i >= 0) && (i < mSlideshowModel.size()))
          {
            mSlideshowEditor.removeSlide(i);
            mSlideListAdapter.notifyDataSetChanged();
            continue;
            addNewSlide();
            continue;
            mSlideshowEditor.removeAllSlides();
            mSlideListAdapter.notifyDataSetChanged();
            finish();
            continue;
            finish();
          }
        }
      }
    }
  }
  
  /* Error */
  protected void onPause()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 319	miui/app/ListActivity:onPause	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 47	com/android/mms/ui/SlideshowEditActivity:mDirty	Z
    //   10: istore_1
    //   11: iload_1
    //   12: ifeq +31 -> 43
    //   15: aload_0
    //   16: getfield 35	com/android/mms/ui/SlideshowEditActivity:mSlideshowModel	Lcom/android/mms/model/SlideshowModel;
    //   19: invokevirtual 323	com/android/mms/model/SlideshowModel:toPduBody	()Lcom/google/android/mms/pdu/PduBody;
    //   22: astore_2
    //   23: aload_0
    //   24: invokestatic 329	com/google/android/mms/pdu/MiuiPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/google/android/mms/pdu/MiuiPduPersister;
    //   27: aload_0
    //   28: getfield 158	com/android/mms/ui/SlideshowEditActivity:mUri	Landroid/net/Uri;
    //   31: aload_2
    //   32: invokevirtual 333	com/google/android/mms/pdu/MiuiPduPersister:updateParts	(Landroid/net/Uri;Lcom/google/android/mms/pdu/PduBody;)V
    //   35: aload_0
    //   36: getfield 35	com/android/mms/ui/SlideshowEditActivity:mSlideshowModel	Lcom/android/mms/model/SlideshowModel;
    //   39: aload_2
    //   40: invokevirtual 337	com/android/mms/model/SlideshowModel:sync	(Lcom/google/android/mms/pdu/PduBody;)V
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_2
    //   47: ldc -39
    //   49: new 339	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 340	java/lang/StringBuilder:<init>	()V
    //   56: ldc_w 342
    //   59: invokevirtual 346	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_0
    //   63: getfield 158	com/android/mms/ui/SlideshowEditActivity:mUri	Landroid/net/Uri;
    //   66: invokevirtual 349	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 353	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: aload_2
    //   73: invokestatic 225	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   76: pop
    //   77: goto -34 -> 43
    //   80: astore_2
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_2
    //   84: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	this	SlideshowEditActivity
    //   10	2	1	bool	boolean
    //   22	18	2	localPduBody	com.google.android.mms.pdu.PduBody
    //   46	27	2	localMmsException	MmsException
    //   80	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   15	43	46	com/google/android/mms/MmsException
    //   6	11	80	finally
    //   15	43	80	finally
    //   43	45	80	finally
    //   47	77	80	finally
    //   81	83	80	finally
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    MenuItem localMenuItem;
    if (mSlideshowModel != null) {
      if (mSlideshowModel.size() < 20)
      {
        localMenuItem = paramMenu.add(0, 3, 0, 2131361826).setIcon(2130837726);
        if (mSlideshowModel.size() >= 20) {
          break label90;
        }
      }
    }
    label90:
    for (boolean bool = true;; bool = false)
    {
      localMenuItem.setEnabled(bool);
      paramMenu.add(0, 4, 0, 2131361828).setIcon(2130837732);
      return true;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (mState != null) {
      mList.setSelection(mState.getInt("slide_index", 0));
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    mState = new Bundle();
    if (mList.getSelectedItemPosition() >= 0) {
      mState.putInt("slide_index", mList.getSelectedItemPosition());
    }
    if (mUri != null) {
      mState.putString("message_uri", mUri.toString());
    }
    paramBundle.putBundle("state", mState);
  }
  
  private static class SlideListAdapter
    extends ArrayAdapter<SlideModel>
  {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final int mResource;
    private final SlideshowModel mSlideshow;
    
    public SlideListAdapter(Context paramContext, int paramInt, SlideshowModel paramSlideshowModel)
    {
      super(paramInt, paramSlideshowModel);
      mContext = paramContext;
      mResource = paramInt;
      mInflater = LayoutInflater.from(paramContext);
      mSlideshow = paramSlideshowModel;
    }
    
    private View createViewFromResource(int paramInt1, View paramView, int paramInt2)
    {
      paramView = (SlideListItemView)mInflater.inflate(paramInt2, null);
      ((TextView)paramView.findViewById(2131820882)).setText(mContext.getString(2131362019, new Object[] { Integer.valueOf(paramInt1 + 1) }));
      paramInt2 = ((SlideModel)getItem(paramInt1)).getDuration() / 1000;
      ((TextView)paramView.findViewById(2131820883)).setText(mContext.getResources().getQuantityString(2131623937, paramInt2, new Object[] { Integer.valueOf(paramInt2) }));
      SlideshowPresenter localSlideshowPresenter = new SlideshowPresenter(mContext, paramView, mSlideshow);
      ((SlideshowPresenter)localSlideshowPresenter).setLocation(paramInt1);
      localSlideshowPresenter.present();
      return paramView;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      return createViewFromResource(paramInt, paramView, mResource);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowEditActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */