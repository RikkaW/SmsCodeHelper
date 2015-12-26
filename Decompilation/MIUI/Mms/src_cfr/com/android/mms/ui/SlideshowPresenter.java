/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.media.MediaDrmException
 *  android.net.Uri
 *  android.os.Handler
 *  android.util.Log
 *  android.widget.Toast
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaDrmException;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.model.AudioModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.Model;
import com.android.mms.model.RegionMediaModel;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
import com.android.mms.ui.AdaptableSlideViewInterface;
import com.android.mms.ui.Presenter;
import com.android.mms.ui.SlideViewInterface;
import com.android.mms.ui.ViewInterface;
import java.util.Iterator;
import java.util.Map;

public class SlideshowPresenter
extends Presenter {
    protected final Handler mHandler = new Handler();
    protected float mHeightTransformRatio;
    protected int mLocation;
    protected final int mSlideNumber;
    private final AdaptableSlideViewInterface.OnSizeChangedListener mViewSizeChangedListener;
    protected float mWidthTransformRatio;

    public SlideshowPresenter(Context context, ViewInterface viewInterface, Model model) {
        super(context, viewInterface, model);
        this.mViewSizeChangedListener = new AdaptableSlideViewInterface.OnSizeChangedListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void onSizeChanged(int n, int n2) {
                LayoutModel layoutModel = ((SlideshowModel)SlideshowPresenter.this.mModel).getLayout();
                SlideshowPresenter.this.mWidthTransformRatio = SlideshowPresenter.this.getWidthTransformRatio(n, layoutModel.getLayoutWidth());
                SlideshowPresenter.this.mHeightTransformRatio = SlideshowPresenter.this.getHeightTransformRatio(n2, layoutModel.getLayoutHeight());
                float f2 = SlideshowPresenter.this.mWidthTransformRatio > SlideshowPresenter.this.mHeightTransformRatio ? SlideshowPresenter.this.mWidthTransformRatio : SlideshowPresenter.this.mHeightTransformRatio;
                SlideshowPresenter.this.mWidthTransformRatio = f2;
                SlideshowPresenter.this.mHeightTransformRatio = f2;
            }
        };
        this.mLocation = 0;
        this.mSlideNumber = ((SlideshowModel)this.mModel).size();
        if (viewInterface instanceof AdaptableSlideViewInterface) {
            ((AdaptableSlideViewInterface)viewInterface).setOnSizeChangedListener(this.mViewSizeChangedListener);
        }
    }

    private float getHeightTransformRatio(int n, int n2) {
        if (n > 0) {
            return (float)n2 / (float)n;
        }
        return 1.0f;
    }

    private float getWidthTransformRatio(int n, int n2) {
        if (n > 0) {
            return (float)n2 / (float)n;
        }
        return 1.0f;
    }

    private int transformHeight(int n) {
        return (int)((float)n / this.mHeightTransformRatio);
    }

    private int transformWidth(int n) {
        return (int)((float)n / this.mWidthTransformRatio);
    }

    public void goForward() {
        if (this.mLocation < this.mSlideNumber - 1) {
            ++this.mLocation;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onModelChanged(final Model model, final boolean bl) {
        final SlideViewInterface slideViewInterface = (SlideViewInterface)this.mView;
        if (model instanceof SlideshowModel) return;
        if (model instanceof SlideModel) {
            if (((SlideModel)model).isVisible()) {
                this.mHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        SlideshowPresenter.this.presentSlide(slideViewInterface, (SlideModel)model);
                    }
                });
                return;
            }
            this.mHandler.post(new Runnable(){

                @Override
                public void run() {
                    SlideshowPresenter.this.goForward();
                }
            });
            return;
        }
        if (model instanceof MediaModel) {
            if (model instanceof RegionMediaModel) {
                this.mHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            SlideshowPresenter.this.presentRegionMedia(slideViewInterface, (RegionMediaModel)model, bl);
                            return;
                        }
                        catch (MediaDrmException var1_1) {
                            Log.e((String)"SlideshowPresenter", (String)var1_1.getMessage(), (Throwable)var1_1);
                            Toast.makeText((Context)SlideshowPresenter.this.mContext, (CharSequence)SlideshowPresenter.this.mContext.getString(2131361819), (int)0).show();
                            return;
                        }
                    }
                });
                return;
            }
            if (!((MediaModel)model).isAudio()) return;
            {
                this.mHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            SlideshowPresenter.this.presentAudio(slideViewInterface, (AudioModel)model, bl);
                            return;
                        }
                        catch (MediaDrmException var1_1) {
                            Log.e((String)"SlideshowPresenter", (String)var1_1.getMessage(), (Throwable)var1_1);
                            Toast.makeText((Context)SlideshowPresenter.this.mContext, (CharSequence)SlideshowPresenter.this.mContext.getString(2131361819), (int)0).show();
                            return;
                        }
                    }
                });
                return;
            }
        }
        if (model instanceof RegionModel) return;
    }

    @Override
    public void present() {
        this.presentSlide((SlideViewInterface)this.mView, ((SlideshowModel)this.mModel).get(this.mLocation));
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void presentAudio(SlideViewInterface slideViewInterface, AudioModel audioModel, boolean bl) throws MediaDrmException {
        MediaModel.MediaAction mediaAction;
        if (bl) {
            slideViewInterface.setAudio(audioModel.getUri(), audioModel.getSrc(), audioModel.getExtras());
            slideViewInterface.setAudioDuration(audioModel.getDuration() / 1000);
        }
        if ((mediaAction = audioModel.getCurrentAction()) == MediaModel.MediaAction.START) {
            slideViewInterface.startAudio();
            return;
        } else {
            if (mediaAction == MediaModel.MediaAction.PAUSE) {
                slideViewInterface.pauseAudio();
                return;
            }
            if (mediaAction == MediaModel.MediaAction.STOP) {
                slideViewInterface.stopAudio();
                return;
            }
            if (mediaAction != MediaModel.MediaAction.SEEK) return;
            {
                slideViewInterface.seekAudio(audioModel.getSeekTo());
                return;
            }
        }
    }

    protected void presentImage(SlideViewInterface slideViewInterface, ImageModel imageModel, RegionModel regionModel, boolean bl) throws MediaDrmException {
        if (bl) {
            slideViewInterface.setImage(imageModel.getSrc(), imageModel.getDrawable());
        }
        if (slideViewInterface instanceof AdaptableSlideViewInterface) {
            ((AdaptableSlideViewInterface)slideViewInterface).setImageRegion(this.transformWidth(regionModel.getLeft()), this.transformHeight(regionModel.getTop()), this.transformWidth(regionModel.getWidth()), this.transformHeight(regionModel.getHeight()));
        }
        slideViewInterface.setImageRegionFit(regionModel.getFit());
        slideViewInterface.setImageVisibility(imageModel.isVisible());
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void presentRegionMedia(SlideViewInterface slideViewInterface, RegionMediaModel regionMediaModel, boolean bl) throws MediaDrmException {
        RegionModel regionModel = regionMediaModel.getRegion();
        if (regionMediaModel.isText()) {
            this.presentText(slideViewInterface, (TextModel)regionMediaModel, regionModel, bl);
            return;
        } else {
            if (regionMediaModel.isImage()) {
                this.presentImage(slideViewInterface, (ImageModel)regionMediaModel, regionModel, bl);
                return;
            }
            if (!regionMediaModel.isVideo()) return;
            {
                this.presentVideo(slideViewInterface, (VideoModel)regionMediaModel, regionModel, bl);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void presentSlide(SlideViewInterface slideViewInterface, SlideModel object) {
        slideViewInterface.reset();
        try {
            object = object.iterator();
            while (object.hasNext()) {
                MediaModel mediaModel = (MediaModel)object.next();
                if (mediaModel instanceof RegionMediaModel) {
                    this.presentRegionMedia(slideViewInterface, (RegionMediaModel)mediaModel, true);
                    continue;
                }
                if (!mediaModel.isAudio()) continue;
                this.presentAudio(slideViewInterface, (AudioModel)mediaModel, true);
            }
            return;
        }
        catch (MediaDrmException var1_2) {
            Log.e((String)"SlideshowPresenter", (String)var1_2.getMessage(), (Throwable)var1_2);
            Toast.makeText((Context)this.mContext, (CharSequence)this.mContext.getString(2131361819), (int)0).show();
        }
    }

    protected void presentText(SlideViewInterface slideViewInterface, TextModel textModel, RegionModel regionModel, boolean bl) {
        if (bl) {
            slideViewInterface.setText(textModel.getSrc(), textModel.getText());
        }
        if (slideViewInterface instanceof AdaptableSlideViewInterface) {
            ((AdaptableSlideViewInterface)slideViewInterface).setTextRegion(this.transformWidth(regionModel.getLeft()), this.transformHeight(regionModel.getTop()), this.transformWidth(regionModel.getWidth()), this.transformHeight(regionModel.getHeight()));
        }
        slideViewInterface.setTextVisibility(textModel.isVisible());
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void presentVideo(SlideViewInterface slideViewInterface, VideoModel videoModel, RegionModel object, boolean bl) throws MediaDrmException {
        if (bl) {
            slideViewInterface.setVideo(videoModel.getSrc(), videoModel.getUri());
        }
        if (slideViewInterface instanceof AdaptableSlideViewInterface) {
            ((AdaptableSlideViewInterface)slideViewInterface).setVideoRegion(this.transformWidth(object.getLeft()), this.transformHeight(object.getTop()), this.transformWidth(object.getWidth()), this.transformHeight(object.getHeight()));
        }
        slideViewInterface.setVideoVisibility(videoModel.isVisible());
        object = videoModel.getCurrentAction();
        if (object == MediaModel.MediaAction.START) {
            slideViewInterface.startVideo();
            return;
        } else {
            if (object == MediaModel.MediaAction.PAUSE) {
                slideViewInterface.pauseVideo();
                return;
            }
            if (object == MediaModel.MediaAction.STOP) {
                slideViewInterface.stopVideo();
                return;
            }
            if (object != MediaModel.MediaAction.SEEK) return;
            {
                slideViewInterface.seekVideo(videoModel.getSeekTo());
                return;
            }
        }
    }

    public void setLocation(int n) {
        this.mLocation = n;
    }

}

