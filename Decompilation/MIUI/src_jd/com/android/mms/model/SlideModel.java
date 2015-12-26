package com.android.mms.model;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import miui.os.Build;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class SlideModel
  extends Model
  implements List<MediaModel>, EventListener
{
  private MediaModel mAudio;
  private boolean mCanAddAudio = true;
  private boolean mCanAddImage = true;
  private boolean mCanAddVideo = true;
  private int mDuration;
  private short mFill;
  private MediaModel mImage;
  private final ArrayList<MediaModel> mMedia = new ArrayList();
  private SlideshowModel mParent;
  private int mSlideSize;
  private MediaModel mText;
  private MediaModel mVideo;
  private boolean mVisible = true;
  
  public SlideModel(int paramInt, SlideshowModel paramSlideshowModel)
  {
    mDuration = paramInt;
    mParent = paramSlideshowModel;
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mSlideSize = 150;
    }
  }
  
  public SlideModel(int paramInt, ArrayList<MediaModel> paramArrayList)
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mSlideSize = 150;
    }
    mDuration = paramInt;
    paramInt = 0;
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      MediaModel localMediaModel = (MediaModel)paramArrayList.next();
      internalAdd(localMediaModel);
      if (!Build.IS_CM_CUSTOMIZATION_TEST)
      {
        int i = localMediaModel.getDuration();
        if (i > paramInt) {
          paramInt = i;
        }
      }
    }
    updateDuration(paramInt);
  }
  
  public SlideModel(SlideshowModel paramSlideshowModel)
  {
    this(5000, paramSlideshowModel);
  }
  
  private void internalAdd(MediaModel paramMediaModel)
    throws IllegalStateException
  {
    if (paramMediaModel == null) {}
    do
    {
      return;
      if (paramMediaModel.isText())
      {
        String str = paramMediaModel.getContentType();
        if ((TextUtils.isEmpty(str)) || ("text/plain".equals(str)) || ("text/html".equals(str)))
        {
          internalAddOrReplace(mText, paramMediaModel);
          mText = paramMediaModel;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramMediaModel.getContentType() + " isn't supported (as text)");
        return;
      }
      if (paramMediaModel.isImage())
      {
        if (mCanAddImage)
        {
          internalAddOrReplace(mImage, paramMediaModel);
          mImage = paramMediaModel;
          mCanAddVideo = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramMediaModel.getContentType() + " - can't add image in this state");
        return;
      }
      if (paramMediaModel.isAudio())
      {
        if (mCanAddAudio)
        {
          internalAddOrReplace(mAudio, paramMediaModel);
          mAudio = paramMediaModel;
          mCanAddVideo = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramMediaModel.getContentType() + " - can't add audio in this state");
        return;
      }
    } while (!paramMediaModel.isVideo());
    if (mCanAddVideo)
    {
      internalAddOrReplace(mVideo, paramMediaModel);
      mVideo = paramMediaModel;
      mCanAddImage = false;
      mCanAddAudio = false;
      return;
    }
    Log.w("Mms/slideshow", "[SlideModel] content type " + paramMediaModel.getContentType() + " - can't add video in this state");
  }
  
  private void internalAddOrReplace(MediaModel paramMediaModel1, MediaModel paramMediaModel2)
  {
    int j = 0;
    if (paramMediaModel2.getMediaResizable()) {}
    for (int i = 0; paramMediaModel1 == null; i = paramMediaModel2.getMediaSize())
    {
      if (mParent != null) {
        mParent.checkMessageSize(i);
      }
      mMedia.add(paramMediaModel2);
      increaseSlideSize(i);
      increaseMessageSize(i);
      paramMediaModel1 = mModelChangedObservers.iterator();
      while (paramMediaModel1.hasNext()) {
        paramMediaModel2.registerModelChangedObserver((IModelChangedObserver)paramMediaModel1.next());
      }
    }
    if (paramMediaModel1.getMediaResizable())
    {
      label98:
      if (i <= j) {
        break label171;
      }
      if (mParent != null) {
        mParent.checkMessageSize(i - j);
      }
      increaseSlideSize(i - j);
      increaseMessageSize(i - j);
    }
    for (;;)
    {
      mMedia.set(mMedia.indexOf(paramMediaModel1), paramMediaModel2);
      paramMediaModel1.unregisterAllModelChangedObservers();
      break;
      j = paramMediaModel1.getMediaSize();
      break label98;
      label171:
      decreaseSlideSize(j - i);
      decreaseMessageSize(j - i);
    }
  }
  
  private boolean internalRemove(Object paramObject)
  {
    boolean bool = false;
    int i = 0;
    if (mMedia.remove(paramObject))
    {
      if (!(paramObject instanceof TextModel)) {
        break label58;
      }
      mText = null;
      if (!((MediaModel)paramObject).getMediaResizable()) {
        break label123;
      }
    }
    for (;;)
    {
      decreaseSlideSize(i);
      decreaseMessageSize(i);
      ((Model)paramObject).unregisterAllModelChangedObservers();
      bool = true;
      return bool;
      label58:
      if ((paramObject instanceof ImageModel))
      {
        mImage = null;
        mCanAddVideo = true;
        break;
      }
      if ((paramObject instanceof AudioModel))
      {
        mAudio = null;
        mCanAddVideo = true;
        break;
      }
      if (!(paramObject instanceof VideoModel)) {
        break;
      }
      mVideo = null;
      mCanAddImage = true;
      mCanAddAudio = true;
      break;
      label123:
      i = ((MediaModel)paramObject).getMediaSize();
    }
  }
  
  public void add(int paramInt, MediaModel paramMediaModel)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean add(MediaModel paramMediaModel)
  {
    internalAdd(paramMediaModel);
    notifyModelChanged(true);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection<? extends MediaModel> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean addAll(Collection<? extends MediaModel> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public void checkMessageSize(int paramInt)
  {
    if ((mImage == null) && (mParent != null)) {
      mParent.checkMessageSize(paramInt);
    }
    for (;;)
    {
      return;
      if (mImage.getMediaResizable()) {}
      for (int i = 0; (paramInt > i) && (mParent != null); i = mImage.getMediaSize())
      {
        mParent.checkMessageSize(paramInt - i);
        return;
      }
    }
  }
  
  public void clear()
  {
    if (mMedia.size() > 0)
    {
      Iterator localIterator = mMedia.iterator();
      while (localIterator.hasNext())
      {
        MediaModel localMediaModel = (MediaModel)localIterator.next();
        localMediaModel.unregisterAllModelChangedObservers();
        int i = localMediaModel.getMediaSize();
        decreaseSlideSize(i);
        decreaseMessageSize(i);
      }
      mMedia.clear();
      mText = null;
      mImage = null;
      mAudio = null;
      mVideo = null;
      mCanAddImage = true;
      mCanAddAudio = true;
      mCanAddVideo = true;
      notifyModelChanged(true);
    }
  }
  
  public boolean contains(Object paramObject)
  {
    return mMedia.contains(paramObject);
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return mMedia.containsAll(paramCollection);
  }
  
  public void decreaseMessageSize(int paramInt)
  {
    if ((paramInt > 0) && (mParent != null))
    {
      int i = mParent.getCurrentMessageSize() - paramInt;
      paramInt = i;
      if (i < 0) {
        paramInt = 0;
      }
      mParent.setCurrentMessageSize(paramInt);
    }
  }
  
  public void decreaseSlideSize(int paramInt)
  {
    if (paramInt > 0)
    {
      mSlideSize -= paramInt;
      if (mSlideSize < 0) {
        mSlideSize = 0;
      }
    }
  }
  
  public MediaModel get(int paramInt)
  {
    if (mMedia.size() == 0) {
      return null;
    }
    return (MediaModel)mMedia.get(paramInt);
  }
  
  public AudioModel getAudio()
  {
    return (AudioModel)mAudio;
  }
  
  public int getDuration()
  {
    return mDuration;
  }
  
  public ImageModel getImage()
  {
    return (ImageModel)mImage;
  }
  
  public int getSlideSize()
  {
    return mSlideSize;
  }
  
  public TextModel getText()
  {
    return (TextModel)mText;
  }
  
  public VideoModel getVideo()
  {
    return (VideoModel)mVideo;
  }
  
  public void handleEvent(Event paramEvent)
  {
    if (paramEvent.getType().equals("SmilSlideStart")) {
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
  
  public boolean hasAudio()
  {
    return mAudio != null;
  }
  
  public boolean hasImage()
  {
    return mImage != null;
  }
  
  public boolean hasText()
  {
    return mText != null;
  }
  
  public boolean hasVideo()
  {
    return mVideo != null;
  }
  
  public void increaseMessageSize(int paramInt)
  {
    if ((paramInt > 0) && (mParent != null))
    {
      int i = mParent.getCurrentMessageSize();
      mParent.setCurrentMessageSize(i + paramInt);
    }
  }
  
  public void increaseSlideSize(int paramInt)
  {
    if (paramInt > 0) {
      mSlideSize += paramInt;
    }
  }
  
  public int indexOf(Object paramObject)
  {
    return mMedia.indexOf(paramObject);
  }
  
  public boolean isEmpty()
  {
    return mMedia.isEmpty();
  }
  
  public boolean isVisible()
  {
    return mVisible;
  }
  
  public Iterator<MediaModel> iterator()
  {
    return mMedia.iterator();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return mMedia.lastIndexOf(paramObject);
  }
  
  public ListIterator<MediaModel> listIterator()
  {
    return mMedia.listIterator();
  }
  
  public ListIterator<MediaModel> listIterator(int paramInt)
  {
    return mMedia.listIterator(paramInt);
  }
  
  protected void registerModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver)
  {
    Iterator localIterator = mMedia.iterator();
    while (localIterator.hasNext()) {
      ((MediaModel)localIterator.next()).registerModelChangedObserver(paramIModelChangedObserver);
    }
  }
  
  public MediaModel remove(int paramInt)
  {
    MediaModel localMediaModel = (MediaModel)mMedia.get(paramInt);
    if ((localMediaModel != null) && (internalRemove(localMediaModel))) {
      notifyModelChanged(true);
    }
    return localMediaModel;
  }
  
  public boolean remove(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof MediaModel)) && (internalRemove(paramObject)))
    {
      notifyModelChanged(true);
      return true;
    }
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean removeAudio()
  {
    boolean bool = remove(mAudio);
    resetDuration();
    return bool;
  }
  
  public boolean removeImage()
  {
    return remove(mImage);
  }
  
  public boolean removeText()
  {
    return remove(mText);
  }
  
  public boolean removeVideo()
  {
    boolean bool = remove(mVideo);
    resetDuration();
    return bool;
  }
  
  public void resetDuration()
  {
    if ((!hasAudio()) && (!hasVideo())) {
      mDuration = 5000;
    }
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public MediaModel set(int paramInt, MediaModel paramMediaModel)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public void setDuration(int paramInt)
  {
    mDuration = paramInt;
    notifyModelChanged(true);
  }
  
  public void setFill(short paramShort)
  {
    mFill = paramShort;
    notifyModelChanged(true);
  }
  
  public void setParent(SlideshowModel paramSlideshowModel)
  {
    mParent = paramSlideshowModel;
  }
  
  public int size()
  {
    return mMedia.size();
  }
  
  public List<MediaModel> subList(int paramInt1, int paramInt2)
  {
    return mMedia.subList(paramInt1, paramInt2);
  }
  
  public Object[] toArray()
  {
    return mMedia.toArray();
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return mMedia.toArray(paramArrayOfT);
  }
  
  protected void unregisterAllModelChangedObserversInDescendants()
  {
    Iterator localIterator = mMedia.iterator();
    while (localIterator.hasNext()) {
      ((MediaModel)localIterator.next()).unregisterAllModelChangedObservers();
    }
  }
  
  protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver paramIModelChangedObserver)
  {
    Iterator localIterator = mMedia.iterator();
    while (localIterator.hasNext()) {
      ((MediaModel)localIterator.next()).unregisterModelChangedObserver(paramIModelChangedObserver);
    }
  }
  
  public void updateDuration(int paramInt)
  {
    if (paramInt <= 0) {}
    while ((paramInt <= mDuration) && (mDuration != 5000)) {
      return;
    }
    mDuration = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.SlideModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */