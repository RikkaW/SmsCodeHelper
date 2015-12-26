package com.android.mms.ui;

import java.util.ArrayList;
import java.util.Iterator;

public class SimplePduPage
{
  private ArrayList<SimplePduPart> mParts = new ArrayList();
  
  public void addPart(SimplePduPart paramSimplePduPart)
  {
    if (paramSimplePduPart != null) {
      mParts.add(paramSimplePduPart);
    }
  }
  
  public SimplePduPart getPageAppearancePart()
  {
    Object localObject = null;
    Iterator localIterator = mParts.iterator();
    while (localIterator.hasNext())
    {
      SimplePduPart localSimplePduPart = (SimplePduPart)localIterator.next();
      if (localSimplePduPart.getAttachmentType() == 0) {
        localObject = localSimplePduPart;
      } else if ((localSimplePduPart.getAttachmentType() == 3) || (localSimplePduPart.getAttachmentType() == 2) || (localSimplePduPart.getAttachmentType() == 1) || (localSimplePduPart.getAttachmentType() == 4) || (localSimplePduPart.getAttachmentType() == 5)) {
        return localSimplePduPart;
      }
    }
    return (SimplePduPart)localObject;
  }
  
  public SimplePduPart getPart(int paramInt)
  {
    if (paramInt < mParts.size()) {
      return (SimplePduPart)mParts.get(paramInt);
    }
    return null;
  }
  
  public int getPartSize()
  {
    return mParts.size();
  }
  
  public String getText()
  {
    SimplePduPart localSimplePduPart = getTypedPart(0);
    if (localSimplePduPart != null) {
      return localSimplePduPart.getText();
    }
    return null;
  }
  
  public SimplePduPart getTypedPart(int paramInt)
  {
    Iterator localIterator = mParts.iterator();
    while (localIterator.hasNext())
    {
      SimplePduPart localSimplePduPart = (SimplePduPart)localIterator.next();
      if (localSimplePduPart.getAttachmentType() == paramInt) {
        return localSimplePduPart;
      }
    }
    return null;
  }
  
  public boolean isEmpty()
  {
    return mParts.isEmpty();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimplePduPage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */