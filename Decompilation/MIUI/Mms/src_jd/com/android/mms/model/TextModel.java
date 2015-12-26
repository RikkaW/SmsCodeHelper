package com.android.mms.model;

import android.content.Context;
import android.util.Log;
import com.google.android.mms.pdu.CharacterSets;
import java.io.UnsupportedEncodingException;
import org.w3c.dom.events.Event;

public class TextModel
  extends RegionMediaModel
{
  private final int mCharset;
  private CharSequence mText;
  
  public TextModel(Context paramContext, String paramString1, String paramString2, int paramInt, byte[] paramArrayOfByte, RegionModel paramRegionModel) {}
  
  public TextModel(Context paramContext, String paramString1, String paramString2, RegionModel paramRegionModel)
  {
    this(paramContext, paramString1, paramString2, 106, new byte[0], paramRegionModel);
  }
  
  private CharSequence extractTextFromData(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      try
      {
        if (mCharset == 0) {
          return new String(paramArrayOfByte);
        }
        String str = new String(paramArrayOfByte, CharacterSets.getMimeName(mCharset));
        return str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Log.e("Mms/text", "Unsupported encoding: " + mCharset, localUnsupportedEncodingException);
        return new String(paramArrayOfByte);
      }
    }
    return "";
  }
  
  public void cloneText()
  {
    if (mText != null) {}
    for (String str = mText.toString();; str = "")
    {
      mText = new String(str);
      return;
    }
  }
  
  public int getCharset()
  {
    return mCharset;
  }
  
  public String getText()
  {
    if (mText == null) {
      mText = extractTextFromData(getData());
    }
    if (!(mText instanceof String)) {
      mText = mText.toString();
    }
    return mText.toString();
  }
  
  public void handleEvent(Event paramEvent)
  {
    if (paramEvent.getType().equals("SmilMediaStart")) {
      mVisible = true;
    }
    for (;;)
    {
      notifyModelChanged(false);
      return;
      if (mFill != 1) {
        mVisible = false;
      }
    }
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    mText = paramCharSequence;
    notifyModelChanged(true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.TextModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */