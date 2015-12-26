package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.RegionModel;
import com.google.android.mms.ContentType;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.util.SqliteWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import miui.os.Build;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimplePduDoc
{
  private static final String[] PART_PROJECTION = { "_id", "chset", "cd", "cid", "cl", "ct", "fn", "name", "text" };
  private Map<String, SimplePduPart> mCidParts = new HashMap();
  private Integer mCompleteSize;
  private ContentResolver mContentResolver;
  private Context mContext;
  private SimplePduPart mDocPart;
  private int mDocType = -1;
  private EncodedStringValue mFrom;
  private long mId;
  private boolean mIsSlideShow = false;
  private LayoutModel mLayoutModel;
  private ArrayList<SimplePduPage> mPages;
  private ArrayList<SimplePduPart> mParts = new ArrayList();
  private Map<String, SimplePduPart> mSrcParts = new HashMap();
  private Uri mUri;
  
  public SimplePduDoc(Context paramContext)
  {
    mContext = paramContext;
    mContentResolver = paramContext.getContentResolver();
    if ((Build.IS_CM_CUSTOMIZATION) || (Build.IS_CU_CUSTOMIZATION)) {
      mLayoutModel = new LayoutModel();
    }
  }
  
  private void loadAddress()
  {
    Cursor localCursor = SqliteWrapper.query(mContext, mContentResolver, Uri.parse("content://mms/" + mId + "/addr"), new String[] { "address", "charset", "type" }, null, null, null);
    if (localCursor != null) {}
    for (;;)
    {
      try
      {
        if (localCursor.moveToNext())
        {
          String str = localCursor.getString(0);
          if (TextUtils.isEmpty(str)) {
            continue;
          }
          switch (localCursor.getInt(2))
          {
          case 137: 
            mFrom = new EncodedStringValue(localCursor.getInt(1), MiuiPduPersister.getBytes(str));
            continue;
          }
        }
      }
      finally
      {
        localCursor.close();
      }
      return;
    }
  }
  
  private boolean loadAttachments()
  {
    boolean bool = false;
    if (!mParts.isEmpty())
    {
      mPages = new ArrayList();
      if ((loadAttachments(mPages, mParts)) || (mPages.size() > 1)) {
        bool = true;
      }
      mIsSlideShow = bool;
      return true;
    }
    return false;
  }
  
  private boolean loadAttachments(ArrayList<SimplePduPage> paramArrayList, ArrayList<SimplePduPart> paramArrayList1)
  {
    int k = 0;
    int m = 0;
    Object localObject = null;
    int j = 0;
    int i = 0;
    Iterator localIterator = paramArrayList1.iterator();
    paramArrayList1 = (ArrayList<SimplePduPart>)localObject;
    while (localIterator.hasNext())
    {
      SimplePduPart localSimplePduPart = (SimplePduPart)localIterator.next();
      int i1;
      int n;
      if (paramArrayList1 != null)
      {
        i1 = i;
        n = j;
        localObject = paramArrayList1;
        if (i != 0)
        {
          i1 = i;
          n = j;
          localObject = paramArrayList1;
          if (j == 0) {}
        }
      }
      else
      {
        localObject = new SimplePduPage();
        paramArrayList.add(localObject);
        n = 0;
        i1 = 0;
      }
      int i2 = localSimplePduPart.getAttachmentType();
      i = i1;
      j = n;
      paramArrayList1 = (ArrayList<SimplePduPart>)localObject;
      if (i2 != -1)
      {
        ((SimplePduPage)localObject).addPart(localSimplePduPart);
        if (i2 == 0)
        {
          j = 1;
          k += 1;
          i = i1;
          paramArrayList1 = (ArrayList<SimplePduPart>)localObject;
        }
        else
        {
          i = 1;
          m += 1;
          j = n;
          paramArrayList1 = (ArrayList<SimplePduPart>)localObject;
        }
      }
    }
    return (k > 1) || (m > 1);
  }
  
  private byte[] loadByteArray(Cursor paramCursor, int paramInt)
  {
    paramCursor = paramCursor.getString(paramInt);
    if (paramCursor != null) {
      return MiuiPduPersister.getBytes(paramCursor);
    }
    return null;
  }
  
  private Integer loadInt(Cursor paramCursor, int paramInt)
  {
    try
    {
      paramInt = paramCursor.getInt(paramInt);
      return Integer.valueOf(paramInt);
    }
    catch (NumberFormatException paramCursor) {}
    return null;
  }
  
  private void loadParts()
    throws MmsException
  {
    Cursor localCursor = SqliteWrapper.query(mContext, mContentResolver, Uri.parse("content://mms/" + mId + "/part"), PART_PROJECTION, null, null, null);
    if (localCursor != null) {}
    label581:
    label771:
    do
    {
      for (;;)
      {
        DrmManagerClient localDrmManagerClient;
        SimplePduPart localSimplePduPart;
        try
        {
          int i = localCursor.getCount();
          if (i == 0) {
            return;
          }
          localDrmManagerClient = MmsApp.getDrmManagerClient();
          mSrcParts.clear();
          mCidParts.clear();
          if (!localCursor.moveToNext()) {
            break label771;
          }
          localSimplePduPart = new SimplePduPart(mContext);
          Object localObject1 = loadInt(localCursor, 1);
          if (localObject1 != null) {
            localSimplePduPart.setCharset(((Integer)localObject1).intValue());
          }
          localObject1 = loadByteArray(localCursor, 2);
          if (localObject1 != null) {
            localSimplePduPart.setContentDisposition((byte[])localObject1);
          }
          localObject1 = loadByteArray(localCursor, 3);
          if (localObject1 != null) {
            localSimplePduPart.setContentId((byte[])localObject1);
          }
          localObject1 = loadByteArray(localCursor, 4);
          if (localObject1 != null) {
            localSimplePduPart.setContentLocation((byte[])localObject1);
          }
          localObject4 = loadByteArray(localCursor, 5);
          if (localObject4 == null)
          {
            localObject1 = null;
            if (localObject4 == null) {
              break;
            }
            localSimplePduPart.setContentType((byte[])localObject4);
            if (!Arrays.equals((byte[])localObject4, "application/smil".getBytes())) {
              break label581;
            }
            mDocPart = localSimplePduPart;
            mDocType = 0;
            localObject4 = loadByteArray(localCursor, 6);
            if (localObject4 != null) {
              localSimplePduPart.setFilename((byte[])localObject4);
            }
            localObject4 = loadByteArray(localCursor, 7);
            if (localObject4 != null) {
              localSimplePduPart.setName((byte[])localObject4);
            }
            long l = localCursor.getLong(0);
            localSimplePduPart.setDataUri(Uri.parse("content://mms/part/" + l));
            if (("text/plain".equals(localObject1)) || ("application/smil".equals(localObject1)) || ("text/html".equals(localObject1)))
            {
              localObject1 = localCursor.getString(8);
              if (localObject1 != null)
              {
                localObject1 = new EncodedStringValue((String)localObject1).getTextString();
                localObject4 = new ByteArrayOutputStream();
                ((ByteArrayOutputStream)localObject4).write((byte[])localObject1, 0, localObject1.length);
                localSimplePduPart.setData(((ByteArrayOutputStream)localObject4).toByteArray());
              }
            }
            if (localSimplePduPart.getAttachmentType() == -1) {
              continue;
            }
            if (localSimplePduPart.getContentLocation() != null) {
              mSrcParts.put(new String(localSimplePduPart.getContentLocation()), localSimplePduPart);
            }
            if (localSimplePduPart.getContentId() != null)
            {
              localObject1 = new String(localSimplePduPart.getContentId());
              if (((String)localObject1).length() > 2)
              {
                localObject1 = ((String)localObject1).substring(1, ((String)localObject1).length() - 1);
                mCidParts.put(localObject1, localSimplePduPart);
              }
            }
            mParts.add(localSimplePduPart);
            continue;
          }
          localObject3 = MiuiPduPersister.toIsoString((byte[])localObject4);
        }
        finally
        {
          if (localCursor != null) {
            localCursor.close();
          }
        }
        continue;
        Object localObject4 = localObject3;
        if (ContentType.isDrmType((String)localObject3)) {
          localObject4 = localDrmManagerClient.getOriginalMimeType(localSimplePduPart.getDataUri().getPath());
        }
        Object localObject3 = localObject4;
        if (localObject4 != null) {
          if ("text/x-vCard".equalsIgnoreCase((String)localObject4))
          {
            localSimplePduPart.setAttachmentType(4);
            localObject3 = localObject4;
          }
          else if ((ContentType.isTextType((String)localObject4)) || ("application/vnd.wap.xhtml+xml".equalsIgnoreCase((String)localObject4)))
          {
            localSimplePduPart.setAttachmentType(0);
            localObject3 = localObject4;
          }
          else if (ContentType.isImageType((String)localObject4))
          {
            localSimplePduPart.setAttachmentType(1);
            localObject3 = localObject4;
          }
          else if ((ContentType.isAudioType((String)localObject4)) || ("application/ogg".equalsIgnoreCase((String)localObject4)))
          {
            localSimplePduPart.setAttachmentType(3);
            localObject3 = localObject4;
          }
          else if (ContentType.isVideoType((String)localObject4))
          {
            localSimplePduPart.setAttachmentType(2);
            localObject3 = localObject4;
          }
          else
          {
            localSimplePduPart.setAttachmentType(5);
            localObject3 = localObject4;
          }
        }
      }
      throw new MmsException("Content-Type must be set.");
    } while (localCursor == null);
    localCursor.close();
  }
  
  private boolean loadSlides()
  {
    if (mDocType != 0) {
      return false;
    }
    mPages = new ArrayList();
    Object localObject1 = new SlideHandler(mParts);
    for (;;)
    {
      try
      {
        Object localObject2 = SAXParserFactory.newInstance().newSAXParser();
        Object localObject3 = mDocPart.getData();
        if (localObject3 != null) {
          ((SAXParser)localObject2).parse(new ByteArrayInputStream((byte[])localObject3), (DefaultHandler)localObject1);
        }
        bool2 = ((SlideHandler)localObject1).isSlideshow();
        localObject1 = ((SlideHandler)localObject1).getRestParts();
        bool1 = bool2;
        if (localObject1 != null)
        {
          bool1 = bool2;
          if (!((ArrayList)localObject1).isEmpty())
          {
            if ((!loadAttachments(mPages, (ArrayList)localObject1)) && (!bool2)) {
              break label286;
            }
            bool2 = true;
            bool1 = bool2;
            if (mParts.size() == 2)
            {
              bool1 = bool2;
              if (mPages.size() == 2)
              {
                localObject1 = (SimplePduPart)mParts.get(0);
                localObject2 = (SimplePduPart)mParts.get(1);
                int i = ((SimplePduPart)localObject1).getAttachmentType();
                int j = ((SimplePduPart)localObject2).getAttachmentType();
                if (((i == 0) && (j != 0)) || ((j == 0) && (i != 0)))
                {
                  mPages.clear();
                  localObject3 = new SimplePduPage();
                  ((SimplePduPage)localObject3).addPart((SimplePduPart)localObject1);
                  ((SimplePduPage)localObject3).addPart((SimplePduPart)localObject2);
                  mPages.add(localObject3);
                }
                bool1 = false;
              }
            }
          }
        }
        if ((!bool1) && (mPages.size() <= 1)) {
          break label292;
        }
        bool1 = true;
        mIsSlideShow = bool1;
        return true;
      }
      catch (SAXException localSAXException)
      {
        return false;
      }
      catch (ParserConfigurationException localParserConfigurationException)
      {
        return false;
      }
      catch (IOException localIOException)
      {
        return false;
      }
      label286:
      boolean bool2 = false;
      continue;
      label292:
      boolean bool1 = false;
    }
  }
  
  public boolean canShow()
  {
    boolean bool2 = false;
    int i = getPageAppearanceType(0);
    boolean bool1;
    if (!isSlideShow())
    {
      bool1 = bool2;
      if (i != -1)
      {
        bool1 = bool2;
        if (i == 0) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public int getCompleteSize()
  {
    if (mCompleteSize == null)
    {
      mCompleteSize = Integer.valueOf(0);
      if ((mDocPart != null) && (mDocPart.loadData())) {
        mCompleteSize = Integer.valueOf(mCompleteSize.intValue() + mDocPart.getData().length);
      }
      Iterator localIterator = mParts.iterator();
      while (localIterator.hasNext())
      {
        SimplePduPart localSimplePduPart = (SimplePduPart)localIterator.next();
        if (localSimplePduPart.loadData()) {
          mCompleteSize = Integer.valueOf(mCompleteSize.intValue() + localSimplePduPart.getData().length);
        }
      }
    }
    return mCompleteSize.intValue();
  }
  
  public SimplePduPage getPage(int paramInt)
  {
    if ((mPages != null) && (paramInt < getPageSize())) {
      return (SimplePduPage)mPages.get(paramInt);
    }
    return null;
  }
  
  public SimplePduPart getPageAppearancePart(int paramInt)
  {
    if ((mPages != null) && (paramInt < getPageSize())) {
      return getPage(paramInt).getPageAppearancePart();
    }
    return null;
  }
  
  public int getPageAppearanceType(int paramInt)
  {
    SimplePduPart localSimplePduPart = getPageAppearancePart(paramInt);
    if (localSimplePduPart != null) {
      return localSimplePduPart.getAttachmentType();
    }
    return -1;
  }
  
  public int getPageSize()
  {
    if (mPages != null) {
      return mPages.size();
    }
    return 0;
  }
  
  public Uri getUri()
  {
    return mUri;
  }
  
  public boolean isLayoutImageBottom()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mLayoutModel != null)
    {
      bool1 = bool2;
      if (mLayoutModel.getImageRegion().getTop() > mLayoutModel.getTextRegion().getTop()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isSlideShow()
  {
    return mIsSlideShow;
  }
  
  public boolean load(Uri paramUri)
  {
    boolean bool2 = false;
    mUri = paramUri;
    mId = ContentUris.parseId(paramUri);
    loadAddress();
    for (;;)
    {
      try
      {
        loadParts();
        if (mDocType == 0)
        {
          if (!loadSlides()) {
            loadAttachments();
          }
          boolean bool1 = bool2;
          if (mPages != null)
          {
            bool1 = bool2;
            if (!mPages.isEmpty()) {
              bool1 = true;
            }
          }
          return bool1;
        }
      }
      catch (MmsException paramUri)
      {
        return false;
      }
      loadAttachments();
    }
  }
  
  private class SlideHandler
    extends DefaultHandler
  {
    private SimplePduPage mCurrPage;
    private ArrayList<SimplePduPart> mPartsRecord;
    private boolean mStartPart = false;
    private int mediaCount;
    private int textCount;
    
    public SlideHandler()
    {
      Object localObject;
      mPartsRecord = ((ArrayList)((ArrayList)localObject).clone());
      textCount = 0;
      mediaCount = 0;
    }
    
    private void parseRegion(Attributes paramAttributes)
    {
      String str2 = paramAttributes.getValue("id");
      String str1 = paramAttributes.getValue("top");
      int i = 0;
      if (TextUtils.isEmpty(str1)) {}
      label132:
      label163:
      do
      {
        for (;;)
        {
          return;
          if (str1.endsWith("px")) {
            paramAttributes = str1.substring(0, str1.indexOf("px"));
          }
          for (;;)
          {
            if (TextUtils.isEmpty(paramAttributes)) {
              break label132;
            }
            try
            {
              int j = Integer.parseInt(paramAttributes);
              i = j;
            }
            catch (Exception paramAttributes)
            {
              for (;;)
              {
                Log.e("Mms/SimplePduDoc", "parse number error! " + paramAttributes);
              }
            }
            if (!"Image".equals(str2)) {
              break label163;
            }
            if (mLayoutModel == null) {
              break;
            }
            mLayoutModel.getImageRegion().setTop(i);
            return;
            paramAttributes = str1;
            if (str1.endsWith("%")) {
              paramAttributes = str1.substring(0, str1.length() - 1);
            }
          }
        }
      } while ((!"Text".equals(str2)) || (mLayoutModel == null));
      mLayoutModel.getTextRegion().setTop(i);
    }
    
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2) {}
    
    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      if ("par".equals(paramString2))
      {
        mStartPart = false;
        if (mCurrPage != null)
        {
          if (((mCurrPage.getPartSize() != 0) && (!mCurrPage.isEmpty())) || (Build.IS_CM_CUSTOMIZATION_TEST)) {
            mPages.add(mCurrPage);
          }
          mCurrPage = null;
        }
      }
    }
    
    public ArrayList<SimplePduPart> getRestParts()
    {
      return mPartsRecord;
    }
    
    public boolean isSlideshow()
    {
      return (textCount > 1) || (mediaCount > 1) || (mPages.size() > 1);
    }
    
    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      if ("par".equals(paramString2))
      {
        mStartPart = true;
        mCurrPage = new SimplePduPage();
      }
      label185:
      label214:
      do
      {
        int j;
        for (;;)
        {
          return;
          if (!mStartPart) {
            break label214;
          }
          paramString1 = paramAttributes.getValue("src");
          if (paramString1 != null)
          {
            if (paramString1.startsWith("cid:")) {
              paramString1 = paramString1.substring("cid:".length());
            }
            for (paramString1 = (SimplePduPart)mCidParts.get(paramString1);; paramString1 = (SimplePduPart)mSrcParts.get(paramString1))
            {
              if (paramString1 == null) {
                break label185;
              }
              j = paramString1.getAttachmentType();
              if (j == -1) {
                break;
              }
              mCurrPage.addPart(paramString1);
              int i = mPartsRecord.size() - 1;
              while (i >= 0)
              {
                if (Arrays.equals(((SimplePduPart)mPartsRecord.get(i)).getContentLocation(), paramString1.getContentLocation())) {
                  mPartsRecord.remove(i);
                }
                i -= 1;
              }
            }
          }
        }
        if (j == 0)
        {
          textCount += 1;
          return;
        }
        mediaCount += 1;
        return;
      } while ((!"region".equals(paramString2)) || ((!Build.IS_CM_CUSTOMIZATION) && (!Build.IS_CU_CUSTOMIZATION)));
      parseRegion(paramAttributes);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimplePduDoc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */