package com.miui.gallery.lib;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiuiGalleryUtils
{
  private static final String[] ALBUM_SHARE_BARCODE_URLS;
  private static final ArrayList<Pattern> ALBUM_SHARE_BARCODE_URL_PATTERNS;
  private static final ArrayList<Pattern> ALBUM_SHARE_BARCODE_URL_STRICT_PATTERNS;
  private static final String[] ALBUM_SHARE_INVITATION_URLS = { "http://mij.cc/[a-z]+/[a-zA-Z0-9\\-_]{16}#a", "http://mi1.cc/[a-zA-Z0-9\\-_]{16}#a", (String)null };
  private static final ArrayList<Pattern> ALBUM_SHARE_INVITATION_URL_PATTERNS;
  private static final ArrayList<Pattern> ALBUM_SHARE_INVITATION_URL_STRICT_PATTERNS;
  
  static
  {
    ALBUM_SHARE_BARCODE_URLS = ALBUM_SHARE_INVITATION_URLS;
    ALBUM_SHARE_INVITATION_URL_PATTERNS = Lists.newArrayList();
    ALBUM_SHARE_INVITATION_URL_STRICT_PATTERNS = Lists.newArrayList();
    ALBUM_SHARE_BARCODE_URL_PATTERNS = Lists.newArrayList();
    ALBUM_SHARE_BARCODE_URL_STRICT_PATTERNS = Lists.newArrayList();
    initPatterns(ALBUM_SHARE_INVITATION_URLS, ALBUM_SHARE_INVITATION_URL_PATTERNS, ALBUM_SHARE_INVITATION_URL_STRICT_PATTERNS);
    initPatterns(ALBUM_SHARE_BARCODE_URLS, ALBUM_SHARE_BARCODE_URL_PATTERNS, ALBUM_SHARE_BARCODE_URL_STRICT_PATTERNS);
  }
  
  private static String detectAlbumShareInvitationUrl(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    Matcher localMatcher;
    do
    {
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        return null;
        paramString = limitMaxLength(paramString, 1000);
        localIterator = ALBUM_SHARE_INVITATION_URL_PATTERNS.iterator();
      }
      localMatcher = ((Pattern)localIterator.next()).matcher(paramString);
    } while (!localMatcher.find());
    return localMatcher.group(0);
  }
  
  private static Intent getAlbumShareInvitationIntent(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    if (TextUtils.isEmpty(paramString1)) {
      throw new IllegalArgumentException("Url is empty");
    }
    paramContext = new Intent("com.miui.gallery.ACTION_ALBUM_SHARE_INVITATION");
    paramContext.setPackage("com.miui.gallery");
    paramContext.putExtra("invitation_url", paramString1);
    paramContext.putExtra("invitation_title", limitMaxLength(paramString2, 1000));
    paramContext.putExtra("invitation_content", limitMaxLength(paramString3, 1000));
    paramContext.putExtra("invitation_type", paramString4);
    paramContext.putExtra("invitation_opt", paramInt);
    return paramContext;
  }
  
  public static boolean handleAsAlbumShareInvitation(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    return handleAsAlbumShareInvitation(paramContext, paramString1, paramString2, paramString3, 5);
  }
  
  public static boolean handleAsAlbumShareInvitation(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt)
  {
    String str = detectAlbumShareInvitationUrl(paramString2);
    if (TextUtils.isEmpty(str)) {
      return false;
    }
    paramString1 = getAlbumShareInvitationIntent(paramContext, str, paramString1, paramString2, paramString3, paramInt);
    if (paramString1 == null) {
      return false;
    }
    paramContext.sendBroadcast(paramString1);
    return true;
  }
  
  private static void initPatterns(String[] paramArrayOfString, ArrayList<Pattern> paramArrayList1, ArrayList<Pattern> paramArrayList2)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (str != null)
      {
        paramArrayList1.add(Pattern.compile(str));
        paramArrayList2.add(Pattern.compile(String.format("^%s$", new Object[] { str })));
      }
      i += 1;
    }
  }
  
  private static String limitMaxLength(String paramString, int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Bad length, maxLen=" + paramInt);
    }
    String str;
    if (paramString == null) {
      str = null;
    }
    do
    {
      return str;
      str = paramString;
    } while (paramString.length() < paramInt);
    return paramString.substring(0, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.miui.gallery.lib.MiuiGalleryUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */