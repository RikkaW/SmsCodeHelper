package com.android.mms.model;

import android.content.Context;
import android.media.MediaDrmException;
import android.util.Log;
import com.android.mms.MmsConfig;
import com.google.android.mms.ContentType;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import java.io.IOException;
import org.w3c.dom.smil.SMILMediaElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRegionMediaElement;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public class MediaModelFactory
{
  private static MediaModel createEmptyTextModel(Context paramContext, RegionModel paramRegionModel)
    throws IOException
  {
    return new TextModel(paramContext, "text/plain", null, paramRegionModel);
  }
  
  private static PduPart findPart(PduBody paramPduBody, String paramString)
  {
    Object localObject = null;
    String str = paramString;
    if (paramString != null)
    {
      paramString = unescapeXML(paramString);
      if (!paramString.startsWith("cid:")) {
        break label67;
      }
      localObject = paramPduBody.getPartByContentId("<" + paramString.substring("cid:".length()) + ">");
      str = paramString;
    }
    while (localObject != null)
    {
      return (PduPart)localObject;
      label67:
      PduPart localPduPart = paramPduBody.getPartByName(paramString);
      localObject = localPduPart;
      str = paramString;
      if (localPduPart == null)
      {
        localPduPart = paramPduBody.getPartByFileName(paramString);
        localObject = localPduPart;
        str = paramString;
        if (localPduPart == null)
        {
          localObject = paramPduBody.getPartByContentLocation(paramString);
          str = paramString;
        }
      }
    }
    paramPduBody = findPartWithoutSuffix(paramPduBody, str);
    if (paramPduBody != null) {
      return paramPduBody;
    }
    throw new IllegalArgumentException("No part found for the model.");
  }
  
  private static PduPart findPartWithoutSuffix(PduBody paramPduBody, String paramString)
  {
    Object localObject2 = null;
    int i = 0;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      String str = paramString;
      if (paramString.startsWith("cid:"))
      {
        str = paramString.substring("cid:".length());
        i = 1;
      }
      int j = str.lastIndexOf(".");
      localObject1 = localObject2;
      if (j != -1)
      {
        paramString = str.substring(0, j);
        if (i == 0) {
          break label100;
        }
        localObject1 = paramPduBody.getPartByContentId("<" + paramString + ">");
      }
    }
    return (PduPart)localObject1;
    label100:
    return paramPduBody.getPartByContentLocation(paramString);
  }
  
  private static MediaModel getGenericMediaModel(Context paramContext, String paramString1, String paramString2, SMILMediaElement paramSMILMediaElement, PduPart paramPduPart, RegionModel paramRegionModel)
    throws MediaDrmException, IOException, MmsException
  {
    Object localObject = paramPduPart.getContentType();
    if (localObject == null) {
      throw new IllegalArgumentException("Content-Type of the part may not be null.");
    }
    localObject = new String((byte[])localObject);
    if (paramString1.equals("text")) {
      paramContext = new TextModel(paramContext, (String)localObject, paramString2, paramPduPart.getCharset(), paramPduPart.getData(), paramRegionModel);
    }
    for (;;)
    {
      int i = 0;
      paramString2 = paramSMILMediaElement.getBegin();
      int j = i;
      if (paramString2 != null)
      {
        j = i;
        if (paramString2.getLength() > 0) {
          j = (int)(paramString2.item(0).getResolvedOffset() * 1000.0D);
        }
      }
      paramContext.setBegin(j);
      int k = (int)(paramSMILMediaElement.getDur() * 1000.0F);
      i = k;
      if (k <= 0)
      {
        paramString2 = paramSMILMediaElement.getEnd();
        i = k;
        if (paramString2 != null)
        {
          i = k;
          if (paramString2.getLength() > 0)
          {
            paramString2 = paramString2.item(0);
            i = k;
            if (paramString2.getTimeType() != 0)
            {
              j = (int)(paramString2.getResolvedOffset() * 1000.0D) - j;
              i = j;
              if (j == 0) {
                if (!(paramContext instanceof AudioModel))
                {
                  i = j;
                  if (!(paramContext instanceof VideoModel)) {}
                }
                else
                {
                  j = MmsConfig.getMinimumSlideElementDuration();
                  i = j;
                  if (Log.isLoggable("Mms:app", 2))
                  {
                    Log.d("Mms:media", "[MediaModelFactory] compute new duration for " + paramString1 + ", duration=" + j);
                    i = j;
                  }
                }
              }
            }
          }
        }
      }
      paramContext.setDuration(i);
      if (MmsConfig.getSlideDurationEnabled()) {
        break label597;
      }
      paramContext.setFill((short)1);
      return paramContext;
      if (paramString1.equals("img"))
      {
        paramContext = new ImageModel(paramContext, (String)localObject, paramString2, paramPduPart.getDataUri(), paramRegionModel);
      }
      else if (paramString1.equals("video"))
      {
        paramContext = new VideoModel(paramContext, (String)localObject, paramString2, paramPduPart.getDataUri(), paramRegionModel);
      }
      else if (paramString1.equals("audio"))
      {
        paramContext = new AudioModel(paramContext, (String)localObject, paramString2, paramPduPart.getDataUri());
      }
      else
      {
        if (!paramString1.equals("ref")) {
          break;
        }
        if (ContentType.isTextType((String)localObject))
        {
          paramContext = new TextModel(paramContext, (String)localObject, paramString2, paramPduPart.getCharset(), paramPduPart.getData(), paramRegionModel);
        }
        else if (ContentType.isImageType((String)localObject))
        {
          paramContext = new ImageModel(paramContext, (String)localObject, paramString2, paramPduPart.getDataUri(), paramRegionModel);
        }
        else if (ContentType.isVideoType((String)localObject))
        {
          paramContext = new VideoModel(paramContext, (String)localObject, paramString2, paramPduPart.getDataUri(), paramRegionModel);
        }
        else if (ContentType.isAudioType((String)localObject))
        {
          paramContext = new AudioModel(paramContext, (String)localObject, paramString2, paramPduPart.getDataUri());
        }
        else
        {
          Log.d("Mms:media", "[MediaModelFactory] getGenericMediaModel Unsupported Content-Type: " + (String)localObject);
          paramContext = createEmptyTextModel(paramContext, paramRegionModel);
        }
      }
    }
    throw new IllegalArgumentException("Unsupported TAG: " + paramString1);
    label597:
    paramContext.setFill(paramSMILMediaElement.getFill());
    return paramContext;
  }
  
  public static MediaModel getMediaModel(Context paramContext, SMILMediaElement paramSMILMediaElement, LayoutModel paramLayoutModel, PduBody paramPduBody)
    throws MediaDrmException, IOException, IllegalArgumentException, MmsException
  {
    String str1 = paramSMILMediaElement.getTagName();
    String str2 = paramSMILMediaElement.getSrc();
    paramPduBody = findPart(paramPduBody, str2);
    if ((paramSMILMediaElement instanceof SMILRegionMediaElement)) {
      return getRegionMediaModel(paramContext, str1, str2, (SMILRegionMediaElement)paramSMILMediaElement, paramLayoutModel, paramPduBody);
    }
    return getGenericMediaModel(paramContext, str1, str2, paramSMILMediaElement, paramPduBody, null);
  }
  
  private static MediaModel getRegionMediaModel(Context paramContext, String paramString1, String paramString2, SMILRegionMediaElement paramSMILRegionMediaElement, LayoutModel paramLayoutModel, PduPart paramPduPart)
    throws MediaDrmException, IOException, MmsException
  {
    Object localObject = paramSMILRegionMediaElement.getRegion();
    if (localObject != null)
    {
      paramLayoutModel = paramLayoutModel.findRegionById(((SMILRegionElement)localObject).getId());
      if (paramLayoutModel != null) {
        return getGenericMediaModel(paramContext, paramString1, paramString2, paramSMILRegionMediaElement, paramPduPart, paramLayoutModel);
      }
    }
    else
    {
      if (paramString1.equals("text")) {}
      for (localObject = "Text";; localObject = "Image")
      {
        paramLayoutModel = paramLayoutModel.findRegionById((String)localObject);
        if (paramLayoutModel == null) {
          break;
        }
        return getGenericMediaModel(paramContext, paramString1, paramString2, paramSMILRegionMediaElement, paramPduPart, paramLayoutModel);
      }
    }
    throw new IllegalArgumentException("Region not found or bad region ID.");
  }
  
  private static String unescapeXML(String paramString)
  {
    return paramString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&amp;", "&");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.MediaModelFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */