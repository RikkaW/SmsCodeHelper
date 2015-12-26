/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.util.Log
 *  android.widget.Toast
 *  com.google.android.mms.MmsException
 *  java.lang.Object
 *  java.lang.String
 *  miui.os.Build
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.model.AudioModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
import com.google.android.mms.MmsException;
import miui.os.Build;

public class SlideshowEditor {
    private final Context mContext;
    private final SlideshowModel mModel;

    public SlideshowEditor(Context context, SlideshowModel slideshowModel) {
        this.mContext = context;
        this.mModel = slideshowModel;
    }

    public boolean addNewSlide() {
        return this.addNewSlide(this.mModel.size());
    }

    public boolean addNewSlide(int n) {
        int n2 = this.mModel.size();
        if (n2 < 20) {
            try {
                this.mModel.checkMessageSize(150);
            }
            catch (ExceedMessageSizeException var3_4) {
                Toast.makeText((Context)this.mContext, (CharSequence)this.mContext.getResources().getString(2131361851), (int)0).show();
                return false;
            }
            SlideModel slideModel = new SlideModel(this.mModel);
            slideModel.add(new TextModel(this.mContext, "text/plain", "text_" + n2 + ".txt", this.mModel.getLayout().getTextRegion()));
            this.mModel.add(n, slideModel);
            return true;
        }
        Log.w((String)"Mms:slideshow", (String)"The limitation of the number of slides is reached.");
        return false;
    }

    public void changeAudio(int n, Uri object) throws MmsException {
        if (object == null) {
            throw new MmsException("new Audio url is null!");
        }
        object = new AudioModel(this.mContext, (Uri)object);
        SlideModel slideModel = this.mModel.get(n);
        slideModel.add((MediaModel)object);
        slideModel.updateDuration(object.getDuration());
    }

    public void changeDuration(int n, int n2) {
        if (n2 >= 0) {
            this.mModel.get(n).setDuration(n2);
        }
    }

    public void changeImage(int n, Uri uri) throws MmsException {
        if (uri == null) {
            throw new MmsException("new Image url is null!");
        }
        this.mModel.get(n).add(new ImageModel(this.mContext, uri, this.mModel.getLayout().getImageRegion()));
    }

    public void changeLayout(int n) {
        this.mModel.getLayout().changeTo(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void changeText(int n, String string2) {
        if (string2 == null) return;
        {
            SlideModel slideModel = this.mModel.get(n);
            TextModel textModel = slideModel.getText();
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                if (textModel != null && string2.equals((Object)textModel.getText())) return;
                {
                    slideModel.add(new TextModel(this.mContext, "text/plain", "text_" + n + ".txt", 106, string2.getBytes(), this.mModel.getLayout().getTextRegion()));
                    return;
                }
            } else {
                if (textModel == null) {
                    textModel = new TextModel(this.mContext, "text/plain", "text_" + n + ".txt", this.mModel.getLayout().getTextRegion());
                    textModel.setText(string2);
                    slideModel.add(textModel);
                    return;
                }
                if (string2.equals((Object)textModel.getText())) return;
                {
                    textModel.setText(string2);
                    return;
                }
            }
        }
    }

    public void changeVideo(int n, Uri object) throws MmsException {
        if (object == null) {
            throw new MmsException("new Video url is null!");
        }
        object = new VideoModel(this.mContext, (Uri)object, this.mModel.getLayout().getImageRegion());
        SlideModel slideModel = this.mModel.get(n);
        slideModel.add((MediaModel)object);
        slideModel.updateDuration(object.getDuration());
    }

    public void checkMessageSize(int n, int n2) {
        this.mModel.get(n).checkMessageSize(n2);
    }

    public void moveSlideDown(int n) {
        this.mModel.add(n + 1, this.mModel.remove(n));
    }

    public void moveSlideUp(int n) {
        this.mModel.add(n - 1, this.mModel.remove(n));
    }

    public void removeAllSlides() {
        while (this.mModel.size() > 0) {
            this.removeSlide(0);
        }
    }

    public boolean removeAudio(int n) {
        return this.mModel.get(n).removeAudio();
    }

    public boolean removeImage(int n) {
        return this.mModel.get(n).removeImage();
    }

    public void removeSlide(int n) {
        this.mModel.remove(n);
    }

    public boolean removeVideo(int n) {
        return this.mModel.get(n).removeVideo();
    }
}

