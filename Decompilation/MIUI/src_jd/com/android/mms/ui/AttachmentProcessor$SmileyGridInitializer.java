package com.android.mms.ui;

class AttachmentProcessor$SmileyGridInitializer
  implements StaticGridView.Initializer
{
  private int mCategory;
  private int mColumnCount;
  private int mRowCount;
  private int mScreenIndex;
  
  AttachmentProcessor$SmileyGridInitializer(AttachmentProcessor paramAttachmentProcessor, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mCategory = paramInt1;
    mScreenIndex = paramInt2;
    mRowCount = paramInt3;
    mColumnCount = paramInt4;
  }
  
  public void onInitialize(StaticGridView paramStaticGridView)
  {
    AttachmentProcessor.access$700(this$0, paramStaticGridView, mCategory, mScreenIndex, mRowCount, mColumnCount);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.SmileyGridInitializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */