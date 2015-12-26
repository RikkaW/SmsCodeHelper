package com.android.mms.ui;

public class SmsTextSizeAdjust$SmsOnScaleListener
  extends ScaleDetector.SimpleOnScaleListener
{
  public SmsTextSizeAdjust$SmsOnScaleListener(SmsTextSizeAdjust paramSmsTextSizeAdjust, float paramFloat1, float paramFloat2)
  {
    super(paramFloat1, paramFloat2);
  }
  
  protected void performChangeText(float paramFloat)
  {
    if (SmsTextSizeAdjust.access$000(this$0) != paramFloat)
    {
      SmsTextSizeAdjust.access$002(this$0, paramFloat);
      if (SmsTextSizeAdjust.access$100(this$0) != null) {
        SmsTextSizeAdjust.access$100(this$0).setTextSize(paramFloat);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SmsTextSizeAdjust.SmsOnScaleListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */