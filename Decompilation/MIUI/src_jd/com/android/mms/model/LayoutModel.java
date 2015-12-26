package com.android.mms.model;

import android.util.Log;
import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import java.util.ArrayList;
import java.util.Iterator;
import miui.os.Build;

public class LayoutModel
  extends Model
{
  private RegionModel mImageRegion;
  private LayoutParameters mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
  private int mLayoutType = 0;
  private ArrayList<RegionModel> mNonStdRegions;
  private RegionModel mRootLayout;
  private RegionModel mTextRegion;
  
  public LayoutModel()
  {
    createDefaultRootLayout();
    createDefaultImageRegion();
    createDefaultTextRegion();
  }
  
  public LayoutModel(RegionModel paramRegionModel, ArrayList<RegionModel> paramArrayList)
  {
    mRootLayout = paramRegionModel;
    mNonStdRegions = new ArrayList();
    paramRegionModel = paramArrayList.iterator();
    while (paramRegionModel.hasNext())
    {
      paramArrayList = (RegionModel)paramRegionModel.next();
      String str = paramArrayList.getRegionId();
      if (str.equals("Image")) {
        mImageRegion = paramArrayList;
      } else if (str.equals("Text")) {
        mTextRegion = paramArrayList;
      } else {
        mNonStdRegions.add(paramArrayList);
      }
    }
    validateLayouts();
  }
  
  private void createDefaultImageRegion()
  {
    if (mRootLayout == null) {
      throw new IllegalStateException("Root-Layout uninitialized.");
    }
    mImageRegion = new RegionModel("Image", 0, 0, mRootLayout.getWidth(), mLayoutParams.getImageHeight());
  }
  
  private void createDefaultRootLayout()
  {
    mRootLayout = new RegionModel(null, 0, 0, mLayoutParams.getWidth(), mLayoutParams.getHeight());
  }
  
  private void createDefaultTextRegion()
  {
    if (mRootLayout == null) {
      throw new IllegalStateException("Root-Layout uninitialized.");
    }
    mTextRegion = new RegionModel("Text", 0, mLayoutParams.getImageHeight(), mRootLayout.getWidth(), mLayoutParams.getTextHeight());
  }
  
  private void validateLayouts()
  {
    if (mRootLayout == null) {
      createDefaultRootLayout();
    }
    if (mImageRegion == null) {
      createDefaultImageRegion();
    }
    if (mTextRegion == null) {
      createDefaultTextRegion();
    }
    valideteLayoutType();
  }
  
  private void valideteLayoutType()
  {
    if ((Build.IS_CM_CUSTOMIZATION) || (Build.IS_CU_CUSTOMIZATION))
    {
      if (mImageRegion.getTop() > mTextRegion.getTop()) {
        mLayoutType = 1;
      }
    }
    else {
      return;
    }
    mLayoutType = 0;
  }
  
  public void changeTo(int paramInt)
  {
    if (mRootLayout == null) {
      throw new IllegalStateException("Root-Layout uninitialized.");
    }
    if (mLayoutParams == null) {
      mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
    }
    if (mLayoutType != paramInt) {}
    switch (paramInt)
    {
    default: 
      Log.w("Mms/slideshow", "Unknown layout type: " + paramInt);
      return;
    case 0: 
      mImageRegion.setTop(0);
      mTextRegion.setTop(mLayoutParams.getImageHeight());
      mLayoutType = paramInt;
      notifyModelChanged(true);
      return;
    }
    mImageRegion.setTop(mLayoutParams.getTextHeight());
    mTextRegion.setTop(0);
    mLayoutType = paramInt;
    notifyModelChanged(true);
  }
  
  public RegionModel findRegionById(String paramString)
  {
    if ("Image".equals(paramString)) {
      return mImageRegion;
    }
    if ("Text".equals(paramString)) {
      return mTextRegion;
    }
    Iterator localIterator = mNonStdRegions.iterator();
    while (localIterator.hasNext())
    {
      RegionModel localRegionModel = (RegionModel)localIterator.next();
      if (localRegionModel.getRegionId().equals(paramString)) {
        return localRegionModel;
      }
    }
    return null;
  }
  
  public String getBackgroundColor()
  {
    return mRootLayout.getBackgroundColor();
  }
  
  public RegionModel getImageRegion()
  {
    return mImageRegion;
  }
  
  public int getLayoutHeight()
  {
    return mRootLayout.getHeight();
  }
  
  public int getLayoutWidth()
  {
    return mRootLayout.getWidth();
  }
  
  public ArrayList<RegionModel> getRegions()
  {
    ArrayList localArrayList = new ArrayList();
    if (mImageRegion != null) {
      localArrayList.add(mImageRegion);
    }
    if (mTextRegion != null) {
      localArrayList.add(mTextRegion);
    }
    return localArrayList;
  }
  
  public RegionModel getTextRegion()
  {
    return mTextRegion;
  }
  
  protected void registerModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver)
  {
    if (mRootLayout != null) {
      mRootLayout.registerModelChangedObserver(paramIModelChangedObserver);
    }
    if (mImageRegion != null) {
      mImageRegion.registerModelChangedObserver(paramIModelChangedObserver);
    }
    if (mTextRegion != null) {
      mTextRegion.registerModelChangedObserver(paramIModelChangedObserver);
    }
  }
  
  protected void unregisterAllModelChangedObserversInDescendants()
  {
    if (mRootLayout != null) {
      mRootLayout.unregisterAllModelChangedObservers();
    }
    if (mImageRegion != null) {
      mImageRegion.unregisterAllModelChangedObservers();
    }
    if (mTextRegion != null) {
      mTextRegion.unregisterAllModelChangedObservers();
    }
  }
  
  protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver)
  {
    if (mRootLayout != null) {
      mRootLayout.unregisterModelChangedObserver(paramIModelChangedObserver);
    }
    if (mImageRegion != null) {
      mImageRegion.unregisterModelChangedObserver(paramIModelChangedObserver);
    }
    if (mTextRegion != null) {
      mTextRegion.unregisterModelChangedObserver(paramIModelChangedObserver);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.LayoutModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */