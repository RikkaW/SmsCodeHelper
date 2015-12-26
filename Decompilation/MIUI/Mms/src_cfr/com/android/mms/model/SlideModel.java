/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  miui.os.Build
 */
package com.android.mms.model;

import android.text.TextUtils;
import android.util.Log;
import com.android.mms.model.AudioModel;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.ImageModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.Model;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
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
implements List<MediaModel>,
EventListener {
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

    public SlideModel(int n, SlideshowModel slideshowModel) {
        this.mDuration = n;
        this.mParent = slideshowModel;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mSlideSize = 150;
        }
    }

    public SlideModel(int n, ArrayList<MediaModel> object) {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mSlideSize = 150;
        }
        this.mDuration = n;
        n = 0;
        object = object.iterator();
        while (object.hasNext()) {
            int n2;
            MediaModel mediaModel = (MediaModel)object.next();
            this.internalAdd(mediaModel);
            if (Build.IS_CM_CUSTOMIZATION_TEST || (n2 = mediaModel.getDuration()) <= n) continue;
            n = n2;
        }
        this.updateDuration(n);
    }

    public SlideModel(SlideshowModel slideshowModel) {
        this(5000, slideshowModel);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void internalAdd(MediaModel mediaModel) throws IllegalStateException {
        if (mediaModel == null) {
            return;
        }
        if (mediaModel.isText()) {
            String string = mediaModel.getContentType();
            if (!(TextUtils.isEmpty((CharSequence)string) || "text/plain".equals((Object)string) || "text/html".equals((Object)string))) {
                Log.w((String)"Mms/slideshow", (String)("[SlideModel] content type " + mediaModel.getContentType() + " isn't supported (as text)"));
                return;
            }
            this.internalAddOrReplace(this.mText, mediaModel);
            this.mText = mediaModel;
            return;
        }
        if (mediaModel.isImage()) {
            if (this.mCanAddImage) {
                this.internalAddOrReplace(this.mImage, mediaModel);
                this.mImage = mediaModel;
                this.mCanAddVideo = false;
                return;
            }
            Log.w((String)"Mms/slideshow", (String)("[SlideModel] content type " + mediaModel.getContentType() + " - can't add image in this state"));
            return;
        }
        if (mediaModel.isAudio()) {
            if (this.mCanAddAudio) {
                this.internalAddOrReplace(this.mAudio, mediaModel);
                this.mAudio = mediaModel;
                this.mCanAddVideo = false;
                return;
            }
            Log.w((String)"Mms/slideshow", (String)("[SlideModel] content type " + mediaModel.getContentType() + " - can't add audio in this state"));
            return;
        }
        if (!mediaModel.isVideo()) return;
        if (this.mCanAddVideo) {
            this.internalAddOrReplace(this.mVideo, mediaModel);
            this.mVideo = mediaModel;
            this.mCanAddImage = false;
            this.mCanAddAudio = false;
            return;
        }
        Log.w((String)"Mms/slideshow", (String)("[SlideModel] content type " + mediaModel.getContentType() + " - can't add video in this state"));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void internalAddOrReplace(MediaModel object, MediaModel mediaModel) {
        int n = 0;
        int n2 = mediaModel.getMediaResizable() ? 0 : mediaModel.getMediaSize();
        if (object == null) {
            if (this.mParent != null) {
                this.mParent.checkMessageSize(n2);
            }
            this.mMedia.add((Object)mediaModel);
            this.increaseSlideSize(n2);
            this.increaseMessageSize(n2);
        } else {
            if (!object.getMediaResizable()) {
                n = object.getMediaSize();
            }
            if (n2 > n) {
                if (this.mParent != null) {
                    this.mParent.checkMessageSize(n2 - n);
                }
                this.increaseSlideSize(n2 - n);
                this.increaseMessageSize(n2 - n);
            } else {
                this.decreaseSlideSize(n - n2);
                this.decreaseMessageSize(n - n2);
            }
            this.mMedia.set(this.mMedia.indexOf(object), (Object)mediaModel);
            object.unregisterAllModelChangedObservers();
        }
        object = this.mModelChangedObservers.iterator();
        while (object.hasNext()) {
            mediaModel.registerModelChangedObserver((IModelChangedObserver)object.next());
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean internalRemove(Object object) {
        boolean bl = false;
        int n = 0;
        if (!this.mMedia.remove(object)) return bl;
        if (object instanceof TextModel) {
            this.mText = null;
        } else if (object instanceof ImageModel) {
            this.mImage = null;
            this.mCanAddVideo = true;
        } else if (object instanceof AudioModel) {
            this.mAudio = null;
            this.mCanAddVideo = true;
        } else if (object instanceof VideoModel) {
            this.mVideo = null;
            this.mCanAddImage = true;
            this.mCanAddAudio = true;
        }
        if (!((MediaModel)object).getMediaResizable()) {
            n = ((MediaModel)object).getMediaSize();
        }
        this.decreaseSlideSize(n);
        this.decreaseMessageSize(n);
        ((Model)object).unregisterAllModelChangedObservers();
        return true;
    }

    @Override
    public void add(int n, MediaModel mediaModel) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public boolean add(MediaModel mediaModel) {
        this.internalAdd(mediaModel);
        this.notifyModelChanged(true);
        return true;
    }

    @Override
    public boolean addAll(int n, Collection<? extends MediaModel> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public boolean addAll(Collection<? extends MediaModel> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /*
     * Enabled aggressive block sorting
     */
    public void checkMessageSize(int n) {
        if (this.mImage == null && this.mParent != null) {
            this.mParent.checkMessageSize(n);
            return;
        } else {
            int n2 = this.mImage.getMediaResizable() ? 0 : this.mImage.getMediaSize();
            if (n <= n2) return;
            if (this.mParent == null) return;
            {
                this.mParent.checkMessageSize(n - n2);
                return;
            }
        }
    }

    @Override
    public void clear() {
        if (this.mMedia.size() > 0) {
            for (MediaModel mediaModel : this.mMedia) {
                mediaModel.unregisterAllModelChangedObservers();
                int n = mediaModel.getMediaSize();
                this.decreaseSlideSize(n);
                this.decreaseMessageSize(n);
            }
            this.mMedia.clear();
            this.mText = null;
            this.mImage = null;
            this.mAudio = null;
            this.mVideo = null;
            this.mCanAddImage = true;
            this.mCanAddAudio = true;
            this.mCanAddVideo = true;
            this.notifyModelChanged(true);
        }
    }

    @Override
    public boolean contains(Object object) {
        return this.mMedia.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.mMedia.containsAll(collection);
    }

    public void decreaseMessageSize(int n) {
        if (n > 0 && this.mParent != null) {
            int n2;
            n = n2 = this.mParent.getCurrentMessageSize() - n;
            if (n2 < 0) {
                n = 0;
            }
            this.mParent.setCurrentMessageSize(n);
        }
    }

    public void decreaseSlideSize(int n) {
        if (n > 0) {
            this.mSlideSize -= n;
            if (this.mSlideSize < 0) {
                this.mSlideSize = 0;
            }
        }
    }

    @Override
    public MediaModel get(int n) {
        if (this.mMedia.size() == 0) {
            return null;
        }
        return (MediaModel)this.mMedia.get(n);
    }

    public AudioModel getAudio() {
        return (AudioModel)this.mAudio;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public ImageModel getImage() {
        return (ImageModel)this.mImage;
    }

    public int getSlideSize() {
        return this.mSlideSize;
    }

    public TextModel getText() {
        return (TextModel)this.mText;
    }

    public VideoModel getVideo() {
        return (VideoModel)this.mVideo;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleEvent(Event event) {
        if (event.getType().equals((Object)"SmilSlideStart")) {
            this.mVisible = true;
        } else if (this.mFill != 1) {
            this.mVisible = false;
        }
        this.notifyModelChanged(false);
    }

    public boolean hasAudio() {
        if (this.mAudio != null) {
            return true;
        }
        return false;
    }

    public boolean hasImage() {
        if (this.mImage != null) {
            return true;
        }
        return false;
    }

    public boolean hasText() {
        if (this.mText != null) {
            return true;
        }
        return false;
    }

    public boolean hasVideo() {
        if (this.mVideo != null) {
            return true;
        }
        return false;
    }

    public void increaseMessageSize(int n) {
        if (n > 0 && this.mParent != null) {
            int n2 = this.mParent.getCurrentMessageSize();
            this.mParent.setCurrentMessageSize(n2 + n);
        }
    }

    public void increaseSlideSize(int n) {
        if (n > 0) {
            this.mSlideSize += n;
        }
    }

    @Override
    public int indexOf(Object object) {
        return this.mMedia.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return this.mMedia.isEmpty();
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    @Override
    public Iterator<MediaModel> iterator() {
        return this.mMedia.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return this.mMedia.lastIndexOf(object);
    }

    @Override
    public ListIterator<MediaModel> listIterator() {
        return this.mMedia.listIterator();
    }

    @Override
    public ListIterator<MediaModel> listIterator(int n) {
        return this.mMedia.listIterator(n);
    }

    @Override
    protected void registerModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
        Iterator iterator = this.mMedia.iterator();
        while (iterator.hasNext()) {
            ((MediaModel)iterator.next()).registerModelChangedObserver(iModelChangedObserver);
        }
    }

    @Override
    public MediaModel remove(int n) {
        MediaModel mediaModel = (MediaModel)this.mMedia.get(n);
        if (mediaModel != null && this.internalRemove(mediaModel)) {
            this.notifyModelChanged(true);
        }
        return mediaModel;
    }

    @Override
    public boolean remove(Object object) {
        if (object != null && object instanceof MediaModel && this.internalRemove(object)) {
            this.notifyModelChanged(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    public boolean removeAudio() {
        boolean bl = this.remove(this.mAudio);
        this.resetDuration();
        return bl;
    }

    public boolean removeImage() {
        return this.remove(this.mImage);
    }

    public boolean removeText() {
        return this.remove(this.mText);
    }

    public boolean removeVideo() {
        boolean bl = this.remove(this.mVideo);
        this.resetDuration();
        return bl;
    }

    public void resetDuration() {
        if (!this.hasAudio() && !this.hasVideo()) {
            this.mDuration = 5000;
        }
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public MediaModel set(int n, MediaModel mediaModel) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    public void setDuration(int n) {
        this.mDuration = n;
        this.notifyModelChanged(true);
    }

    public void setFill(short s) {
        this.mFill = s;
        this.notifyModelChanged(true);
    }

    public void setParent(SlideshowModel slideshowModel) {
        this.mParent = slideshowModel;
    }

    @Override
    public int size() {
        return this.mMedia.size();
    }

    @Override
    public List<MediaModel> subList(int n, int n2) {
        return this.mMedia.subList(n, n2);
    }

    @Override
    public Object[] toArray() {
        return this.mMedia.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arrT) {
        return this.mMedia.toArray((Object[])arrT);
    }

    @Override
    protected void unregisterAllModelChangedObserversInDescendants() {
        Iterator iterator = this.mMedia.iterator();
        while (iterator.hasNext()) {
            ((MediaModel)iterator.next()).unregisterAllModelChangedObservers();
        }
    }

    @Override
    protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
        Iterator iterator = this.mMedia.iterator();
        while (iterator.hasNext()) {
            ((MediaModel)iterator.next()).unregisterModelChangedObserver(iModelChangedObserver);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void updateDuration(int n) {
        if (n <= 0 || n <= this.mDuration && this.mDuration != 5000) {
            return;
        }
        this.mDuration = n;
    }
}

