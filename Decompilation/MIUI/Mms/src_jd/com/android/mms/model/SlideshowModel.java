package com.android.mms.model;

import android.content.ContentUris;
import android.content.Context;
import android.media.MediaDrmException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.dom.smil.parser.SmilXmlSerializer;
import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import com.google.android.mms.ContentType;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.MultimediaMessagePdu;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import miui.os.Build;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILMediaElement;
import org.w3c.dom.smil.SMILParElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SlideshowModel
  extends Model
  implements List<SlideModel>, IModelChangedObserver
{
  private final ArrayList<FileAttachmentModel> mAttachFiles;
  private Context mContext;
  private int mCurrentMessageSize;
  private SMILDocument mDocumentCache;
  private final LayoutModel mLayout;
  private PduBody mPduBodyCache;
  private final ArrayList<SlideModel> mSlides;
  private int mTotalMessageSize;
  
  private SlideshowModel(Context paramContext)
  {
    mLayout = new LayoutModel();
    mSlides = new ArrayList();
    mAttachFiles = new ArrayList();
    mContext = paramContext;
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mCurrentMessageSize = 110;
    }
  }
  
  private SlideshowModel(LayoutModel paramLayoutModel, ArrayList<SlideModel> paramArrayList, ArrayList<FileAttachmentModel> paramArrayList1, SMILDocument paramSMILDocument, PduBody paramPduBody, Context paramContext)
  {
    mLayout = paramLayoutModel;
    mSlides = paramArrayList;
    mAttachFiles = paramArrayList1;
    mContext = paramContext;
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mCurrentMessageSize = 110;
    }
    mDocumentCache = paramSMILDocument;
    mPduBodyCache = paramPduBody;
    paramLayoutModel = mSlides.iterator();
    while (paramLayoutModel.hasNext())
    {
      paramArrayList = (SlideModel)paramLayoutModel.next();
      increaseMessageSize(paramArrayList.getSlideSize());
      paramArrayList.setParent(this);
    }
    paramLayoutModel = mAttachFiles.iterator();
    while (paramLayoutModel.hasNext()) {
      increaseMessageSize(((FileAttachmentModel)paramLayoutModel.next()).getAttachSize());
    }
  }
  
  public static SlideshowModel createFromMessageUri(Context paramContext, Uri paramUri)
    throws MmsException
  {
    return createFromPduBody(paramContext, getPduBody(paramContext, paramUri));
  }
  
  public static SlideshowModel createFromPduBody(Context paramContext, PduBody paramPduBody)
    throws MmsException
  {
    SMILDocument localSMILDocument = SmilHelper.getDocument(paramPduBody);
    Object localObject3 = localSMILDocument.getLayout();
    Object localObject1 = ((SMILLayoutElement)localObject3).getRootLayout();
    int j = ((SMILRootLayoutElement)localObject1).getWidth();
    int k = ((SMILRootLayoutElement)localObject1).getHeight();
    if (j != 0)
    {
      i = k;
      if (k != 0) {}
    }
    else
    {
      j = LayoutManager.getInstance().getLayoutParameters().getWidth();
      i = LayoutManager.getInstance().getLayoutParameters().getHeight();
      ((SMILRootLayoutElement)localObject1).setWidth(j);
      ((SMILRootLayoutElement)localObject1).setHeight(i);
    }
    localObject1 = new RegionModel(null, 0, 0, j, i);
    Object localObject2 = new ArrayList();
    localObject3 = ((SMILLayoutElement)localObject3).getRegions();
    j = ((NodeList)localObject3).getLength();
    int i = 0;
    Object localObject4;
    while (i < j)
    {
      localObject4 = (SMILRegionElement)((NodeList)localObject3).item(i);
      ((ArrayList)localObject2).add(new RegionModel(((SMILRegionElement)localObject4).getId(), ((SMILRegionElement)localObject4).getFit(), ((SMILRegionElement)localObject4).getLeft(), ((SMILRegionElement)localObject4).getTop(), ((SMILRegionElement)localObject4).getWidth(), ((SMILRegionElement)localObject4).getHeight(), ((SMILRegionElement)localObject4).getBackgroundColor()));
      i += 1;
    }
    localObject2 = new LayoutModel((RegionModel)localObject1, (ArrayList)localObject2);
    localObject1 = localSMILDocument.getBody().getChildNodes();
    int n = ((NodeList)localObject1).getLength();
    localObject3 = new ArrayList(n);
    i = 0;
    j = 0;
    Object localObject5;
    int i1;
    Object localObject6;
    if (j < n)
    {
      localObject4 = (SMILParElement)((NodeList)localObject1).item(j);
      localObject5 = ((SMILParElement)localObject4).getChildNodes();
      i1 = ((NodeList)localObject5).getLength();
      localObject6 = new ArrayList(i1);
      k = 0;
    }
    for (;;)
    {
      Object localObject7;
      Object localObject8;
      if (k < i1)
      {
        SMILMediaElement localSMILMediaElement = (SMILMediaElement)((NodeList)localObject5).item(k);
        for (;;)
        {
          try
          {
            localObject7 = MediaModelFactory.getMediaModel(paramContext, localSMILMediaElement, (LayoutModel)localObject2, paramPduBody);
            if (!MmsConfig.getSlideDurationEnabled())
            {
              m = ((MediaModel)localObject7).getDuration();
              f = ((SMILParElement)localObject4).getDur();
              if (f == 0.0F)
              {
                m = MmsConfig.getMinimumSlideElementDuration() * 1000;
                ((MediaModel)localObject7).setDuration(m);
              }
              if (m / 1000 != f)
              {
                localObject8 = localSMILMediaElement.getTagName();
                if ((!ContentType.isVideoType(mContentType)) && (!((String)localObject8).equals("video")) && (!ContentType.isAudioType(mContentType)) && (!((String)localObject8).equals("audio"))) {
                  continue;
                }
                ((SMILParElement)localObject4).setDur(m / 1000.0F + 1.0F);
              }
            }
          }
          catch (MediaDrmException localMediaDrmException)
          {
            Log.e("Mms/slideshow", localMediaDrmException.getMessage(), localMediaDrmException);
            break label1113;
            if ((int)f == 0) {
              continue;
            }
            m = (int)f;
            ((MediaModel)localObject7).setDuration(m * 1000);
            continue;
          }
          catch (IOException localIOException)
          {
            Log.e("Mms/slideshow", localIOException.getMessage(), localIOException);
            break label1113;
            float f = m / 1000.0F;
            ((SMILParElement)localObject4).setDur(f);
            continue;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Log.e("Mms/slideshow", localIllegalArgumentException.getMessage(), localIllegalArgumentException);
          }
          SmilHelper.addMediaElementEventListeners((EventTarget)localSMILMediaElement, (MediaModel)localObject7);
          ((ArrayList)localObject6).add(localObject7);
          m = i + ((MediaModel)localObject7).getMediaSize();
          i = m;
          break label1113;
          if (m / 1000 >= f) {
            continue;
          }
          ((MediaModel)localObject7).setDuration((int)f * 1000);
        }
      }
      localObject5 = new SlideModel((int)(((SMILParElement)localObject4).getDur() * 1000.0F), (ArrayList)localObject6);
      ((SlideModel)localObject5).setFill(((SMILParElement)localObject4).getFill());
      SmilHelper.addParElementEventListeners((EventTarget)localObject4, (SlideModel)localObject5);
      ((ArrayList)localObject3).add(localObject5);
      j += 1;
      break;
      int m = paramPduBody.getPartsNum();
      localObject4 = new ArrayList();
      j = 0;
      k = i;
      if (j < m)
      {
        localObject5 = paramPduBody.getPart(j);
        localObject1 = ((PduPart)localObject5).getContentLocation();
        byte[] arrayOfByte = ((PduPart)localObject5).getName();
        localObject7 = ((PduPart)localObject5).getContentId();
        localObject8 = ((PduPart)localObject5).getFilename();
        localObject6 = ((PduPart)localObject5).getData();
        if (localObject1 != null)
        {
          localObject1 = new String((byte[])localObject1);
          label797:
          if (!FileAttachmentModel.isVCard((PduPart)localObject5)) {
            break label945;
          }
          localObject1 = new VCardModel(paramContext, "text/x-vCard", (String)localObject1, ((PduPart)localObject5).getDataUri());
          ((ArrayList)localObject4).add(localObject1);
          i = k + ((FileAttachmentModel)localObject1).getAttachSize();
        }
        for (;;)
        {
          j += 1;
          k = i;
          break;
          if (arrayOfByte != null)
          {
            localObject1 = new String(arrayOfByte);
            break label797;
          }
          if (localObject7 != null)
          {
            localObject1 = new String((byte[])localObject7);
            break label797;
          }
          if (localObject8 != null)
          {
            localObject1 = new String((byte[])localObject8);
            break label797;
          }
          LogTag.error("Cannot decide file name for part " + j, new Object[0]);
          i = k;
          continue;
          label945:
          if ((localObject6 != null) && (new String((byte[])localObject6).startsWith("BEGIN:VCARD")))
          {
            i = k;
            if (new String((byte[])localObject6).startsWith("BEGIN:VCARD"))
            {
              localObject1 = new VCardModel(paramContext, "text/x-vCard", (String)localObject1 + ".vcf", ((PduPart)localObject5).getDataUri());
              ((ArrayList)localObject4).add(localObject1);
              i = k + ((FileAttachmentModel)localObject1).getAttachSize();
            }
          }
          else
          {
            LogTag.error("Unrecognized attachment part " + j, new Object[0]);
            i = k;
          }
        }
      }
      paramContext = new SlideshowModel((LayoutModel)localObject2, (ArrayList)localObject3, (ArrayList)localObject4, localSMILDocument, paramPduBody, paramContext);
      mTotalMessageSize = k;
      paramContext.registerModelChangedObserver(paramContext);
      return paramContext;
      label1113:
      k += 1;
    }
  }
  
  public static SlideshowModel createNew(Context paramContext)
  {
    return new SlideshowModel(paramContext);
  }
  
  private String getLocationFromSrc(String paramString)
  {
    String str;
    if (paramString == null)
    {
      str = null;
      return str;
    }
    if (paramString.startsWith("cid:")) {
      paramString = paramString.substring("cid:".length());
    }
    for (;;)
    {
      int i = paramString.lastIndexOf("/");
      str = paramString;
      if (-1 == i) {
        break;
      }
      str = paramString;
      if (i >= paramString.length()) {
        break;
      }
      return paramString.substring(i + 1);
    }
  }
  
  public static PduBody getPduBody(Context paramContext, Uri paramUri)
    throws MmsException
  {
    paramContext = MiuiPduPersister.getPduPersister(paramContext).load(paramUri);
    int i = paramContext.getMessageType();
    if ((i == 128) || (i == 132)) {
      return ((MultimediaMessagePdu)paramContext).getBody();
    }
    throw new MmsException();
  }
  
  private PduBody makePduBody(SMILDocument paramSMILDocument)
  {
    return makePduBody(paramSMILDocument, false);
  }
  
  private PduBody makePduBody(SMILDocument paramSMILDocument, boolean paramBoolean)
  {
    PduBody localPduBody = new PduBody();
    int i = 0;
    Object localObject2;
    Object localObject3;
    while (i < mSlides.size())
    {
      localObject2 = (SlideModel)mSlides.get(i);
      int j = 0;
      while (j < ((SlideModel)localObject2).size())
      {
        localObject3 = ((SlideModel)localObject2).get(j);
        PduPart localPduPart = new PduPart();
        if (((MediaModel)localObject3).isText())
        {
          localObject1 = (TextModel)localObject3;
          if (TextUtils.isEmpty(((TextModel)localObject1).getText())) {
            j += 1;
          } else {
            localPduPart.setCharset(((TextModel)localObject1).getCharset());
          }
        }
        else
        {
          localPduPart.setContentType(((MediaModel)localObject3).getContentType().getBytes());
          String str = ((MediaModel)localObject3).getSrc();
          localObject1 = getLocationFromSrc(str);
          localPduPart.setContentLocation(((String)localObject1).getBytes());
          if (str.startsWith("cid:"))
          {
            localPduPart.setContentId(((String)localObject1).getBytes());
            if (!((MediaModel)localObject3).isText()) {
              break label247;
            }
            localPduPart.setData(((TextModel)localObject3).getText().getBytes());
          }
          for (;;)
          {
            localPduBody.addPart(localPduPart);
            break;
            int k = ((String)localObject1).lastIndexOf(".");
            if (k == -1) {}
            for (;;)
            {
              localPduPart.setContentId(((String)localObject1).getBytes());
              break;
              localObject1 = ((String)localObject1).substring(0, k);
            }
            label247:
            if ((((MediaModel)localObject3).isImage()) || (((MediaModel)localObject3).isVideo()) || (((MediaModel)localObject3).isAudio())) {
              localPduPart.setDataUri(((MediaModel)localObject3).getUri());
            } else {
              Log.w("Mms/slideshow", "Unsupport media: " + localObject3);
            }
          }
        }
      }
      i += 1;
    }
    Object localObject1 = new ByteArrayOutputStream();
    SmilXmlSerializer.serialize(paramSMILDocument, (OutputStream)localObject1);
    paramSMILDocument = new PduPart();
    paramSMILDocument.setContentId("smil".getBytes());
    paramSMILDocument.setContentLocation("smil.xml".getBytes());
    paramSMILDocument.setContentType("application/smil".getBytes());
    paramSMILDocument.setData(((ByteArrayOutputStream)localObject1).toByteArray());
    localPduBody.addPart(0, paramSMILDocument);
    localObject1 = mAttachFiles.iterator();
    if (((Iterator)localObject1).hasNext())
    {
      localObject2 = (FileAttachmentModel)((Iterator)localObject1).next();
      localObject3 = new PduPart();
      ((PduPart)localObject3).setContentType(((FileAttachmentModel)localObject2).getContentType().toLowerCase().getBytes());
      paramSMILDocument = ((FileAttachmentModel)localObject2).getSrc();
      paramBoolean = paramSMILDocument.startsWith("cid:");
      if (paramBoolean)
      {
        paramSMILDocument = paramSMILDocument.substring("cid:".length());
        label476:
        ((PduPart)localObject3).setContentLocation(paramSMILDocument.getBytes());
        if (!paramBoolean) {
          break label556;
        }
        ((PduPart)localObject3).setContentId(paramSMILDocument.getBytes());
        ((PduPart)localObject3).setName(((FileAttachmentModel)localObject2).getSrc().getBytes());
        ((PduPart)localObject3).setFilename(((FileAttachmentModel)localObject2).getSrc().getBytes());
        if (!((FileAttachmentModel)localObject2).isVCard()) {
          break label591;
        }
        ((PduPart)localObject3).setDataUri(((FileAttachmentModel)localObject2).getUri());
      }
      for (;;)
      {
        localPduBody.addPart((PduPart)localObject3);
        break;
        break label476;
        label556:
        i = paramSMILDocument.lastIndexOf(".");
        if (i == -1) {}
        for (;;)
        {
          ((PduPart)localObject3).setContentId(paramSMILDocument.getBytes());
          break;
          paramSMILDocument = paramSMILDocument.substring(0, i);
        }
        label591:
        LogTag.warn("Unsupport file attachment: %s", new Object[] { localObject2 });
      }
    }
    return localPduBody;
  }
  
  public void add(int paramInt, SlideModel paramSlideModel)
  {
    if (paramSlideModel != null)
    {
      int i = paramSlideModel.getSlideSize();
      checkMessageSize(i);
      mSlides.add(paramInt, paramSlideModel);
      increaseMessageSize(i);
      paramSlideModel.registerModelChangedObserver(this);
      Iterator localIterator = mModelChangedObservers.iterator();
      while (localIterator.hasNext()) {
        paramSlideModel.registerModelChangedObserver((IModelChangedObserver)localIterator.next());
      }
      notifyModelChanged(true);
    }
  }
  
  public boolean add(SlideModel paramSlideModel)
  {
    if (paramSlideModel != null)
    {
      int i = paramSlideModel.getSlideSize();
      checkMessageSize(i);
      mSlides.add(paramSlideModel);
      increaseMessageSize(i);
      paramSlideModel.registerModelChangedObserver(this);
      Iterator localIterator = mModelChangedObservers.iterator();
      while (localIterator.hasNext()) {
        paramSlideModel.registerModelChangedObserver((IModelChangedObserver)localIterator.next());
      }
      notifyModelChanged(true);
      return true;
    }
    return false;
  }
  
  public boolean addAll(int paramInt, Collection<? extends SlideModel> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean addAll(Collection<? extends SlideModel> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean addFileAttachment(FileAttachmentModel paramFileAttachmentModel)
  {
    if (paramFileAttachmentModel != null)
    {
      int i = paramFileAttachmentModel.getAttachSize();
      checkMessageSize(i);
      mAttachFiles.add(paramFileAttachmentModel);
      increaseMessageSize(i);
      paramFileAttachmentModel.registerModelChangedObserver(this);
      Iterator localIterator = mModelChangedObservers.iterator();
      while (localIterator.hasNext()) {
        paramFileAttachmentModel.registerModelChangedObserver((IModelChangedObserver)localIterator.next());
      }
      notifyModelChanged(true);
      return true;
    }
    return false;
  }
  
  public void checkMessageSize(int paramInt)
    throws ContentRestrictionException
  {
    ContentRestrictionFactory.getContentRestriction().checkMessageSize(mCurrentMessageSize, paramInt, mContext.getContentResolver());
  }
  
  public void clear()
  {
    if (mSlides.size() > 0)
    {
      Iterator localIterator1 = mSlides.iterator();
      while (localIterator1.hasNext())
      {
        SlideModel localSlideModel = (SlideModel)localIterator1.next();
        localSlideModel.unregisterModelChangedObserver(this);
        Iterator localIterator2 = mModelChangedObservers.iterator();
        while (localIterator2.hasNext()) {
          localSlideModel.unregisterModelChangedObserver((IModelChangedObserver)localIterator2.next());
        }
      }
      mCurrentMessageSize = 0;
      if (Build.IS_CM_CUSTOMIZATION_TEST) {
        mCurrentMessageSize = 110;
      }
      mSlides.clear();
      notifyModelChanged(true);
    }
  }
  
  public boolean contains(Object paramObject)
  {
    return mSlides.contains(paramObject);
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return mSlides.containsAll(paramCollection);
  }
  
  public void decreaseMessageSize(int paramInt)
  {
    if (paramInt > 0) {
      mCurrentMessageSize -= paramInt;
    }
  }
  
  public void finalResize(Uri paramUri)
    throws MmsException, ExceedMessageSizeException
  {
    int j = 0;
    int i = 0;
    Object localObject = mAttachFiles.iterator();
    while (((Iterator)localObject).hasNext()) {
      i += ((FileAttachmentModel)((Iterator)localObject).next()).getAttachSize();
    }
    localObject = mSlides.iterator();
    int m = i;
    Iterator localIterator;
    MediaModel localMediaModel;
    if (((Iterator)localObject).hasNext())
    {
      localIterator = ((SlideModel)((Iterator)localObject).next()).iterator();
      int k = j;
      i = m;
      for (;;)
      {
        m = i;
        j = k;
        if (!localIterator.hasNext()) {
          break;
        }
        localMediaModel = (MediaModel)localIterator.next();
        if (localMediaModel.getMediaResizable()) {
          k += 1;
        } else {
          i += localMediaModel.getMediaSize();
        }
      }
    }
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/slideshow", "finalResize: original message size: " + getCurrentMessageSize() + " getMaxMessageSize: " + MmsConfig.getMaxMessageSize() + " fixedSizeTotal: " + m);
    }
    if (j > 0)
    {
      i = MmsConfig.getMaxMessageSize() - m - 1024;
      if (i <= 0) {
        throw new ExceedMessageSizeException("No room for pictures");
      }
      long l = ContentUris.parseId(paramUri);
      i /= j;
      localObject = mSlides.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localIterator = ((SlideModel)((Iterator)localObject).next()).iterator();
        while (localIterator.hasNext())
        {
          localMediaModel = (MediaModel)localIterator.next();
          if (localMediaModel.getMediaResizable()) {
            localMediaModel.resizeMedia(i, l);
          }
        }
      }
      i = 0;
      localObject = mSlides.iterator();
      if (((Iterator)localObject).hasNext())
      {
        localIterator = ((SlideModel)((Iterator)localObject).next()).iterator();
        j = i;
        for (;;)
        {
          i = j;
          if (!localIterator.hasNext()) {
            break;
          }
          j += ((MediaModel)localIterator.next()).getMediaSize();
        }
      }
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/slideshow", "finalResize: new message size: " + i);
      }
      if (i > MmsConfig.getMaxMessageSize()) {
        throw new ExceedMessageSizeException("After compressing pictures, message too big");
      }
      setCurrentMessageSize(i);
      onModelChanged(this, true);
      localObject = toPduBody();
      MiuiPduPersister.getPduPersister(mContext).updateParts(paramUri, (PduBody)localObject);
    }
  }
  
  public SlideModel get(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < mSlides.size())) {
      return (SlideModel)mSlides.get(paramInt);
    }
    return null;
  }
  
  public ArrayList<FileAttachmentModel> getAttachFiles()
  {
    return mAttachFiles;
  }
  
  public int getCurrentMessageSize()
  {
    return mCurrentMessageSize;
  }
  
  public LayoutModel getLayout()
  {
    return mLayout;
  }
  
  public void increaseMessageSize(int paramInt)
  {
    if (paramInt > 0) {
      mCurrentMessageSize += paramInt;
    }
  }
  
  public int indexOf(Object paramObject)
  {
    return mSlides.indexOf(paramObject);
  }
  
  public boolean isEmpty()
  {
    return mSlides.isEmpty();
  }
  
  public boolean isSimple()
  {
    if (size() != 1) {}
    SlideModel localSlideModel;
    do
    {
      return false;
      localSlideModel = get(0);
    } while ((!(localSlideModel.hasImage() ^ localSlideModel.hasVideo())) || (localSlideModel.hasAudio()));
    return true;
  }
  
  public Iterator<SlideModel> iterator()
  {
    return mSlides.iterator();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return mSlides.lastIndexOf(paramObject);
  }
  
  public ListIterator<SlideModel> listIterator()
  {
    return mSlides.listIterator();
  }
  
  public ListIterator<SlideModel> listIterator(int paramInt)
  {
    return mSlides.listIterator(paramInt);
  }
  
  public void onModelChanged(Model paramModel, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mDocumentCache = null;
      mPduBodyCache = null;
    }
  }
  
  public void prepareForSend()
  {
    if (size() == 1)
    {
      TextModel localTextModel = get(0).getText();
      if (localTextModel != null) {
        localTextModel.cloneText();
      }
    }
  }
  
  protected void registerModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver)
  {
    mLayout.registerModelChangedObserver(paramIModelChangedObserver);
    Iterator localIterator = mSlides.iterator();
    while (localIterator.hasNext()) {
      ((SlideModel)localIterator.next()).registerModelChangedObserver(paramIModelChangedObserver);
    }
  }
  
  public SlideModel remove(int paramInt)
  {
    SlideModel localSlideModel = (SlideModel)mSlides.remove(paramInt);
    if (localSlideModel != null)
    {
      decreaseMessageSize(localSlideModel.getSlideSize());
      localSlideModel.unregisterAllModelChangedObservers();
      notifyModelChanged(true);
    }
    return localSlideModel;
  }
  
  public boolean remove(Object paramObject)
  {
    if ((paramObject != null) && (mSlides.remove(paramObject)))
    {
      paramObject = (SlideModel)paramObject;
      decreaseMessageSize(((SlideModel)paramObject).getSlideSize());
      ((SlideModel)paramObject).unregisterAllModelChangedObservers();
      notifyModelChanged(true);
      return true;
    }
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public void removeAllAttachFiles()
  {
    int i = mAttachFiles.size() - 1;
    while (i >= 0)
    {
      removeAttachFile(i);
      i -= 1;
    }
  }
  
  public FileAttachmentModel removeAttachFile(int paramInt)
  {
    FileAttachmentModel localFileAttachmentModel = (FileAttachmentModel)mAttachFiles.remove(paramInt);
    if (localFileAttachmentModel != null)
    {
      decreaseMessageSize(localFileAttachmentModel.getAttachSize());
      localFileAttachmentModel.unregisterAllModelChangedObservers();
      notifyModelChanged(true);
    }
    return localFileAttachmentModel;
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public SlideModel set(int paramInt, SlideModel paramSlideModel)
  {
    SlideModel localSlideModel = (SlideModel)mSlides.get(paramInt);
    int i;
    int j;
    if (paramSlideModel != null)
    {
      i = 0;
      j = paramSlideModel.getSlideSize();
      if (localSlideModel != null) {
        i = localSlideModel.getSlideSize();
      }
      if (j <= i) {
        break label127;
      }
      checkMessageSize(j - i);
      increaseMessageSize(j - i);
    }
    for (;;)
    {
      localSlideModel = (SlideModel)mSlides.set(paramInt, paramSlideModel);
      if (localSlideModel != null) {
        localSlideModel.unregisterAllModelChangedObservers();
      }
      if (paramSlideModel == null) {
        break;
      }
      paramSlideModel.registerModelChangedObserver(this);
      Iterator localIterator = mModelChangedObservers.iterator();
      while (localIterator.hasNext()) {
        paramSlideModel.registerModelChangedObserver((IModelChangedObserver)localIterator.next());
      }
      label127:
      decreaseMessageSize(i - j);
    }
    notifyModelChanged(true);
    return localSlideModel;
  }
  
  public void setCurrentMessageSize(int paramInt)
  {
    mCurrentMessageSize = paramInt;
  }
  
  public int size()
  {
    return mSlides.size();
  }
  
  public int sizeOfFilesAttach()
  {
    if (mAttachFiles == null) {
      return 0;
    }
    return mAttachFiles.size();
  }
  
  public List<SlideModel> subList(int paramInt1, int paramInt2)
  {
    return mSlides.subList(paramInt1, paramInt2);
  }
  
  public void sync(PduBody paramPduBody)
  {
    Iterator localIterator = mSlides.iterator();
    Object localObject1;
    Object localObject2;
    while (localIterator.hasNext())
    {
      localObject1 = ((SlideModel)localIterator.next()).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (MediaModel)((Iterator)localObject1).next();
        PduPart localPduPart = paramPduBody.getPartByContentLocation(getLocationFromSrc(((MediaModel)localObject2).getSrc()));
        if (localPduPart != null) {
          ((MediaModel)localObject2).setUri(localPduPart.getDataUri());
        }
      }
    }
    localIterator = mAttachFiles.iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (FileAttachmentModel)localIterator.next();
      localObject2 = paramPduBody.getPartByContentLocation(((FileAttachmentModel)localObject1).getSrc());
      if (localObject2 != null)
      {
        ((FileAttachmentModel)localObject1).setUri(((PduPart)localObject2).getDataUri());
        ((FileAttachmentModel)localObject1).setData(((PduPart)localObject2).getData());
      }
    }
  }
  
  public Object[] toArray()
  {
    return mSlides.toArray();
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return mSlides.toArray(paramArrayOfT);
  }
  
  public PduBody toPduBody()
  {
    if (mPduBodyCache == null)
    {
      mDocumentCache = SmilHelper.getDocument(this);
      mPduBodyCache = makePduBody(mDocumentCache);
    }
    return mPduBodyCache;
  }
  
  protected void unregisterAllModelChangedObserversInDescendants()
  {
    mLayout.unregisterAllModelChangedObservers();
    Iterator localIterator = mSlides.iterator();
    while (localIterator.hasNext()) {
      ((SlideModel)localIterator.next()).unregisterAllModelChangedObservers();
    }
  }
  
  protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver)
  {
    mLayout.unregisterModelChangedObserver(paramIModelChangedObserver);
    Iterator localIterator = mSlides.iterator();
    while (localIterator.hasNext()) {
      ((SlideModel)localIterator.next()).unregisterModelChangedObserver(paramIModelChangedObserver);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.SlideshowModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */