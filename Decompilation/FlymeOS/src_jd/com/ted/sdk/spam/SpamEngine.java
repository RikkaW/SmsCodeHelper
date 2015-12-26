package com.ted.sdk.spam;

import android.content.Context;
import com.ted.android.contacts.block.SpamMsgEngine;

public class SpamEngine
{
  public static void init(Context paramContext)
  {
    SpamMsgEngine.c(paramContext);
  }
  
  public static final boolean isSpam(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    return SpamMsgEngine.a(paramContext, paramString1, paramString2, paramString3);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.spam.SpamEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */