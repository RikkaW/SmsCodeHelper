package com.android.mms.model;

import android.drm.DrmManagerClient;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.parser.SmilXmlParser;
import com.google.android.mms.ContentType;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import miui.os.Build;
import org.w3c.dom.Node;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILMediaElement;
import org.w3c.dom.smil.SMILParElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRegionMediaElement;
import org.w3c.dom.smil.SMILRootLayoutElement;
import org.xml.sax.SAXException;

public class SmilHelper
{
  static void addMediaElementEventListeners(EventTarget paramEventTarget, MediaModel paramMediaModel)
  {
    paramEventTarget.addEventListener("SmilMediaStart", paramMediaModel, false);
    paramEventTarget.addEventListener("SmilMediaEnd", paramMediaModel, false);
    paramEventTarget.addEventListener("SmilMediaPause", paramMediaModel, false);
    paramEventTarget.addEventListener("SmilMediaSeek", paramMediaModel, false);
  }
  
  public static SMILParElement addPar(SMILDocument paramSMILDocument)
  {
    SMILParElement localSMILParElement = (SMILParElement)paramSMILDocument.createElement("par");
    localSMILParElement.setDur(8.0F);
    paramSMILDocument.getBody().appendChild(localSMILParElement);
    return localSMILParElement;
  }
  
  static void addParElementEventListeners(EventTarget paramEventTarget, SlideModel paramSlideModel)
  {
    paramEventTarget.addEventListener("SmilSlideStart", paramSlideModel, false);
    paramEventTarget.addEventListener("SmilSlideEnd", paramSlideModel, false);
  }
  
  public static SMILMediaElement createMediaElement(String paramString1, SMILDocument paramSMILDocument, String paramString2)
  {
    paramString1 = (SMILMediaElement)paramSMILDocument.createElement(paramString1);
    paramString1.setSrc(parseSrc(paramString2));
    return paramString1;
  }
  
  private static SMILDocument createSmilDocument(SlideshowModel paramSlideshowModel)
  {
    SmilDocumentImpl localSmilDocumentImpl = new SmilDocumentImpl();
    Object localObject1 = (SMILElement)localSmilDocumentImpl.createElement("smil");
    localSmilDocumentImpl.appendChild((Node)localObject1);
    Object localObject2 = (SMILElement)localSmilDocumentImpl.createElement("head");
    ((SMILElement)localObject1).appendChild((Node)localObject2);
    SMILLayoutElement localSMILLayoutElement = (SMILLayoutElement)localSmilDocumentImpl.createElement("layout");
    ((SMILElement)localObject2).appendChild(localSMILLayoutElement);
    localObject2 = (SMILRootLayoutElement)localSmilDocumentImpl.createElement("root-layout");
    Object localObject3 = paramSlideshowModel.getLayout();
    ((SMILRootLayoutElement)localObject2).setWidth(((LayoutModel)localObject3).getLayoutWidth());
    ((SMILRootLayoutElement)localObject2).setHeight(((LayoutModel)localObject3).getLayoutHeight());
    Object localObject4 = ((LayoutModel)localObject3).getBackgroundColor();
    if (!TextUtils.isEmpty((CharSequence)localObject4)) {
      ((SMILRootLayoutElement)localObject2).setBackgroundColor((String)localObject4);
    }
    localSMILLayoutElement.appendChild((Node)localObject2);
    localObject3 = ((LayoutModel)localObject3).getRegions();
    localObject2 = new ArrayList();
    localObject3 = ((ArrayList)localObject3).iterator();
    Object localObject5;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (RegionModel)((Iterator)localObject3).next();
      localObject5 = (SMILRegionElement)localSmilDocumentImpl.createElement("region");
      ((SMILRegionElement)localObject5).setId(((RegionModel)localObject4).getRegionId());
      ((SMILRegionElement)localObject5).setLeft(((RegionModel)localObject4).getLeft());
      ((SMILRegionElement)localObject5).setTop(((RegionModel)localObject4).getTop());
      ((SMILRegionElement)localObject5).setWidth(((RegionModel)localObject4).getWidth());
      ((SMILRegionElement)localObject5).setHeight(((RegionModel)localObject4).getHeight());
      ((SMILRegionElement)localObject5).setFit(((RegionModel)localObject4).getFit());
      ((ArrayList)localObject2).add(localObject5);
    }
    ((SMILElement)localObject1).appendChild((SMILElement)localSmilDocumentImpl.createElement("body"));
    localObject3 = paramSlideshowModel.iterator();
    while (((Iterator)localObject3).hasNext())
    {
      paramSlideshowModel = (SlideModel)((Iterator)localObject3).next();
      boolean bool2 = false;
      boolean bool1 = false;
      localObject4 = addPar(localSmilDocumentImpl);
      ((SMILParElement)localObject4).setDur(paramSlideshowModel.getDuration() / 1000.0F);
      addParElementEventListeners((EventTarget)localObject4, paramSlideshowModel);
      localObject5 = paramSlideshowModel.iterator();
      while (((Iterator)localObject5).hasNext())
      {
        MediaModel localMediaModel = (MediaModel)((Iterator)localObject5).next();
        localObject1 = localMediaModel.getSrc();
        int i = ((String)localObject1).lastIndexOf("/");
        paramSlideshowModel = (SlideshowModel)localObject1;
        if (-1 != i)
        {
          paramSlideshowModel = (SlideshowModel)localObject1;
          if (i < ((String)localObject1).length()) {
            paramSlideshowModel = ((String)localObject1).substring(i + 1);
          }
        }
        if ((localMediaModel instanceof TextModel))
        {
          if (!TextUtils.isEmpty(((TextModel)localMediaModel).getText()))
          {
            paramSlideshowModel = createMediaElement("text", localSmilDocumentImpl, paramSlideshowModel);
            bool2 = setRegion((SMILRegionMediaElement)paramSlideshowModel, (ArrayList)localObject2, localSMILLayoutElement, "Text", bool2);
          }
        }
        else
        {
          for (;;)
          {
            if (!Build.IS_CM_CUSTOMIZATION_TEST)
            {
              i = localMediaModel.getBegin();
              if (i != 0) {
                paramSlideshowModel.setAttribute("begin", String.valueOf(i / 1000));
              }
              i = localMediaModel.getDuration();
              if (i != 0) {
                paramSlideshowModel.setDur(i / 1000.0F);
              }
            }
            ((SMILParElement)localObject4).appendChild(paramSlideshowModel);
            addMediaElementEventListeners((EventTarget)paramSlideshowModel, localMediaModel);
            break;
            if ((localMediaModel instanceof ImageModel))
            {
              paramSlideshowModel = createMediaElement("img", localSmilDocumentImpl, paramSlideshowModel);
              bool1 = setRegion((SMILRegionMediaElement)paramSlideshowModel, (ArrayList)localObject2, localSMILLayoutElement, "Image", bool1);
            }
            else if ((localMediaModel instanceof VideoModel))
            {
              paramSlideshowModel = createMediaElement("video", localSmilDocumentImpl, paramSlideshowModel);
              bool1 = setRegion((SMILRegionMediaElement)paramSlideshowModel, (ArrayList)localObject2, localSMILLayoutElement, "Image", bool1);
            }
            else
            {
              if (!(localMediaModel instanceof AudioModel)) {
                break label670;
              }
              paramSlideshowModel = createMediaElement("audio", localSmilDocumentImpl, paramSlideshowModel);
            }
          }
          label670:
          Log.w("Mms/smil", "Unsupport media: " + localMediaModel);
        }
      }
    }
    return localSmilDocumentImpl;
  }
  
  private static SMILDocument createSmilDocument(PduBody paramPduBody)
  {
    SmilDocumentImpl localSmilDocumentImpl = new SmilDocumentImpl();
    Object localObject1 = (SMILElement)localSmilDocumentImpl.createElement("smil");
    ((SMILElement)localObject1).setAttribute("xmlns", "http://www.w3.org/2001/SMIL20/Language");
    localSmilDocumentImpl.appendChild((Node)localObject1);
    Object localObject2 = (SMILElement)localSmilDocumentImpl.createElement("head");
    ((SMILElement)localObject1).appendChild((Node)localObject2);
    ((SMILElement)localObject2).appendChild((SMILLayoutElement)localSmilDocumentImpl.createElement("layout"));
    ((SMILElement)localObject1).appendChild((SMILElement)localSmilDocumentImpl.createElement("body"));
    localObject2 = addPar(localSmilDocumentImpl);
    int i1 = paramPduBody.getPartsNum();
    if (i1 == 0) {
      return localSmilDocumentImpl;
    }
    DrmManagerClient localDrmManagerClient = MmsApp.getDrmManagerClient();
    int k = 0;
    int i = 0;
    int n = 0;
    label143:
    int m;
    int j;
    PduPart localPduPart;
    if (n < i1)
    {
      if (localObject2 != null)
      {
        m = i;
        j = k;
        localObject1 = localObject2;
        if (i != 0)
        {
          m = i;
          j = k;
          localObject1 = localObject2;
          if (k == 0) {}
        }
      }
      else
      {
        localObject1 = addPar(localSmilDocumentImpl);
        j = 0;
        m = 0;
      }
      localPduPart = paramPduBody.getPart(n);
      String str = new String(localPduPart.getContentType());
      localObject2 = str;
      if (ContentType.isDrmType(str)) {
        localObject2 = localDrmManagerClient.getOriginalMimeType(localPduPart.getDataUri().getPath());
      }
      if ((!((String)localObject2).equals("text/plain")) && (!((String)localObject2).equalsIgnoreCase("application/vnd.wap.xhtml+xml")) && (!((String)localObject2).equals("text/html"))) {
        break label315;
      }
      ((SMILParElement)localObject1).appendChild(createMediaElement("text", localSmilDocumentImpl, localPduPart.generateLocation()));
      j = 1;
      i = m;
    }
    for (;;)
    {
      n += 1;
      k = j;
      localObject2 = localObject1;
      break label143;
      break;
      label315:
      if (ContentType.isImageType((String)localObject2))
      {
        ((SMILParElement)localObject1).appendChild(createMediaElement("img", localSmilDocumentImpl, localPduPart.generateLocation()));
        i = 1;
      }
      else if (ContentType.isVideoType((String)localObject2))
      {
        ((SMILParElement)localObject1).appendChild(createMediaElement("video", localSmilDocumentImpl, localPduPart.generateLocation()));
        i = 1;
      }
      else if (ContentType.isAudioType((String)localObject2))
      {
        ((SMILParElement)localObject1).appendChild(createMediaElement("audio", localSmilDocumentImpl, localPduPart.generateLocation()));
        i = 1;
      }
      else
      {
        Log.w("Mms/smil", "unsupport media type");
        i = m;
      }
    }
  }
  
  public static String escapeXML(String paramString)
  {
    return paramString.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
  }
  
  private static SMILRegionElement findRegionElementById(ArrayList<SMILRegionElement> paramArrayList, String paramString)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      SMILRegionElement localSMILRegionElement = (SMILRegionElement)paramArrayList.next();
      if (localSMILRegionElement.getId().equals(paramString)) {
        return localSMILRegionElement;
      }
    }
    return null;
  }
  
  private static PduPart findSmilPart(PduBody paramPduBody)
  {
    int j = paramPduBody.getPartsNum();
    int i = 0;
    while (i < j)
    {
      PduPart localPduPart = paramPduBody.getPart(i);
      if (Arrays.equals(localPduPart.getContentType(), "application/smil".getBytes())) {
        return localPduPart;
      }
      i += 1;
    }
    return null;
  }
  
  public static SMILDocument getDocument(SlideshowModel paramSlideshowModel)
  {
    return createSmilDocument(paramSlideshowModel);
  }
  
  public static SMILDocument getDocument(PduBody paramPduBody)
  {
    Object localObject = findSmilPart(paramPduBody);
    SMILDocument localSMILDocument = null;
    if (localObject != null) {
      localSMILDocument = getSmilDocument((PduPart)localObject);
    }
    localObject = localSMILDocument;
    if (localSMILDocument == null) {
      localObject = createSmilDocument(paramPduBody);
    }
    return (SMILDocument)localObject;
  }
  
  private static SMILDocument getSmilDocument(PduPart paramPduPart)
  {
    try
    {
      paramPduPart = paramPduPart.getData();
      if (paramPduPart != null)
      {
        paramPduPart = new ByteArrayInputStream(paramPduPart);
        paramPduPart = validate(new SmilXmlParser().parse(paramPduPart));
        return paramPduPart;
      }
    }
    catch (IOException paramPduPart)
    {
      Log.e("Mms/smil", "Failed to parse SMIL document.", paramPduPart);
      return null;
    }
    catch (SAXException paramPduPart)
    {
      for (;;)
      {
        Log.e("Mms/smil", "Failed to parse SMIL document.", paramPduPart);
      }
    }
    catch (MmsException paramPduPart)
    {
      for (;;)
      {
        Log.e("Mms/smil", "Failed to parse SMIL document.", paramPduPart);
      }
    }
  }
  
  private static String parseSrc(String paramString)
  {
    String str1 = paramString;
    if (paramString.startsWith("cid:"))
    {
      String str2 = paramString.substring("cid:".length());
      str1 = paramString;
      if (str2.startsWith("<"))
      {
        str1 = paramString;
        if (str2.endsWith(">")) {
          str1 = "cid:" + str2.substring(1, str2.length() - 1);
        }
      }
    }
    return escapeXML(str1);
  }
  
  private static boolean setRegion(SMILRegionMediaElement paramSMILRegionMediaElement, ArrayList<SMILRegionElement> paramArrayList, SMILLayoutElement paramSMILLayoutElement, String paramString, boolean paramBoolean)
  {
    paramArrayList = findRegionElementById(paramArrayList, paramString);
    if ((!paramBoolean) && (paramArrayList != null))
    {
      paramSMILRegionMediaElement.setRegion(paramArrayList);
      paramSMILLayoutElement.appendChild(paramArrayList);
      return true;
    }
    return false;
  }
  
  private static SMILDocument validate(SMILDocument paramSMILDocument)
  {
    return paramSMILDocument;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.SmilHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */