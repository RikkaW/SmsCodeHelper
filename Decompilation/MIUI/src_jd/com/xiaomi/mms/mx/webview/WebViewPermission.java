package com.xiaomi.mms.mx.webview;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewPermission
{
  public static boolean canUseAlertDialog(int paramInt)
  {
    return (paramInt & 0x1000) == 4096;
  }
  
  public static boolean canUseMiTalk(int paramInt)
  {
    return (paramInt & 0x4000) == 16384;
  }
  
  public static void settingWebView(WebView paramWebView, int paramInt)
  {
    boolean bool2 = true;
    paramWebView = paramWebView.getSettings();
    if ((paramInt & 0x1) == 1)
    {
      bool1 = true;
      paramWebView.setJavaScriptEnabled(bool1);
      if ((paramInt & 0x2) != 2) {
        break label191;
      }
      bool1 = true;
      label30:
      paramWebView.setDomStorageEnabled(bool1);
      if ((paramInt & 0x4) != 4) {
        break label196;
      }
      bool1 = true;
      label44:
      paramWebView.setDatabaseEnabled(bool1);
      if ((paramInt & 0x8) != 8) {
        break label201;
      }
      bool1 = true;
      label60:
      paramWebView.setAppCacheEnabled(bool1);
      if ((paramInt & 0x10) != 16) {
        break label206;
      }
      bool1 = true;
      label76:
      paramWebView.setSaveFormData(bool1);
      if ((paramInt & 0x20) != 32) {
        break label211;
      }
      bool1 = true;
      label92:
      paramWebView.setSupportZoom(bool1);
      if ((paramInt & 0x40) != 64) {
        break label216;
      }
      bool1 = true;
      label108:
      paramWebView.setAllowContentAccess(bool1);
      if ((paramInt & 0x80) != 128) {
        break label221;
      }
      bool1 = true;
      label126:
      paramWebView.setAllowFileAccess(bool1);
      if ((paramInt & 0x100) != 256) {
        break label226;
      }
      bool1 = true;
      label144:
      paramWebView.setJavaScriptCanOpenWindowsAutomatically(bool1);
      if ((paramInt & 0x200) != 512) {
        break label231;
      }
      bool1 = true;
      label162:
      paramWebView.setSavePassword(bool1);
      if ((paramInt & 0x800) != 2048) {
        break label236;
      }
    }
    label191:
    label196:
    label201:
    label206:
    label211:
    label216:
    label221:
    label226:
    label231:
    label236:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      paramWebView.setGeolocationEnabled(bool1);
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label30;
      bool1 = false;
      break label44;
      bool1 = false;
      break label60;
      bool1 = false;
      break label76;
      bool1 = false;
      break label92;
      bool1 = false;
      break label108;
      bool1 = false;
      break label126;
      bool1 = false;
      break label144;
      bool1 = false;
      break label162;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.WebViewPermission
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */