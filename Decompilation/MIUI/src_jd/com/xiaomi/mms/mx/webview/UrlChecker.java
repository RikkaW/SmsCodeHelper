package com.xiaomi.mms.mx.webview;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class UrlChecker
{
  private List<String> mBlackDomains = new ArrayList();
  private List<String> mWhiteDomains = new ArrayList();
  
  public UrlChecker()
  {
    mWhiteDomains.add("www.baidu.com");
    mWhiteDomains.add("3g.miliao.com");
    mBlackDomains.add("www.360.com");
  }
  
  public int getPermissionOfUrl(String paramString)
  {
    int j = 63;
    int i = j;
    if (!TextUtils.isEmpty(paramString))
    {
      i = j;
      if (paramString.startsWith("miliao://")) {
        i = 63 + '䀀';
      }
    }
    return i;
  }
  
  public boolean isInWhiteList(String paramString)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.UrlChecker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */