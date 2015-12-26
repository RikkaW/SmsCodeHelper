/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.media.MediaMetadataRetriever
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Message
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.PopupWindow
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  miui.os.Build
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.AudioModel;
import com.android.mms.model.FileAttachmentModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.VideoModel;
import com.android.mms.ui.FloatPanelView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.ThumbnailView;
import java.util.ArrayList;
import miui.os.Build;

public class AttachmentView
extends FrameLayout {
    private static int[] EDIT_BUTTONS = new int[]{2131820666, 2131820667, 2131820668, 2131820896, 2131820897, 2131820898, 2131820550, 2131820551, 2131820552, 2131820874, 2131820875, 2131820876, 2131820895};
    private String mAudioName;
    private TextView mAudioNameView;
    private Drawable mDefaultThumbnailMaskDrawable;
    private Handler mHandler;
    private ThumbnailView mImageView;
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
    private FloatPanelView mPanel;
    private ImageView mPlayIcon;
    private PopupWindow mPopupWindow;
    private SlideshowModel mSlideshow;
    private Drawable mThumbnailTransparentDrawable;

    public AttachmentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener(){

            public void onGlobalLayout() {
                AttachmentView.this.mHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        if (AttachmentView.this.mPopupWindow != null && AttachmentView.this.mPopupWindow.isShowing()) {
                            AttachmentView.this.showPopup();
                        }
                    }
                });
            }

        };
        this.init(context);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private Bitmap createVideoThumbnail(Uri uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.getContext(), uri);
        uri = mediaMetadataRetriever.getFrameAtTime(-1);
        mediaMetadataRetriever.release();
        return uri;
        {
            catch (RuntimeException runtimeException) {
                return uri;
            }
        }
        catch (RuntimeException runtimeException) {
            try {
                mediaMetadataRetriever.release();
                return null;
            }
            catch (RuntimeException var1_3) {
                return null;
            }
        }
        catch (Throwable throwable) {
            try {
                mediaMetadataRetriever.release();
            }
            catch (RuntimeException var2_7) {
                throw throwable;
            }
            throw throwable;
        }
    }

    private void init(Context context) {
        context = context.getResources();
        this.mDefaultThumbnailMaskDrawable = context.getDrawable(2130838013);
        this.mThumbnailTransparentDrawable = context.getDrawable(2130838014);
    }

    private void setAudioName(String string) {
        this.mAudioName = string;
    }

    private void setImageBitmap(Bitmap bitmap, boolean bl) {
        this.setImageDrawable((Drawable)new BitmapDrawable(this.getContext().getResources(), bitmap), bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setImageDrawable(Drawable drawable, boolean bl) {
        this.mImageView.setBackgroundDrawable(this.mDefaultThumbnailMaskDrawable, 0);
        if (drawable == null) {
            this.mImageView.setImageResource(2130837759, true);
        } else {
            this.mImageView.setMaskDrawable(null, this.mThumbnailTransparentDrawable);
            this.mImageView.setImageDrawable(drawable, true);
            MessageUtils.setAttachmentAnimation(this.mImageView, drawable);
        }
        if (bl) {
            this.mPlayIcon.setVisibility(0);
            return;
        }
        this.mPlayIcon.setVisibility(8);
    }

    private void setPanelResid(int n) {
        this.mPanel = (FloatPanelView)((LayoutInflater)this.getContext().getSystemService("layout_inflater")).inflate(n, null);
        for (n = 0; n < EDIT_BUTTONS.length; ++n) {
            View view = this.mPanel.findViewById(EDIT_BUTTONS[n]);
            if (view == null) continue;
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    Message.obtain((Handler)AttachmentView.this.mHandler, (int)view.getId()).sendToTarget();
                    AttachmentView.this.mHandler.post(new Runnable(){

                        @Override
                        public void run() {
                            AttachmentView.this.dismissPopup();
                        }
                    });
                }

            });
        }
    }

    public void dismissPopup() {
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
            this.getViewTreeObserver().removeGlobalOnLayoutListener(this.mLayoutListener);
        }
    }

    public boolean onBackPressed() {
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.dismissPopup();
            return true;
        }
        return false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dismissPopup();
    }

    protected void onFinishInflate() {
        this.mImageView = (ThumbnailView)this.findViewById(2131820791);
        this.mImageView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
        this.mPlayIcon = (ImageView)this.findViewById(2131820619);
        this.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                AttachmentView.this.showPopup();
            }
        });
    }

    public void setAudioNameView(TextView textView) {
        this.mAudioNameView = textView;
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void showPopup() {
        int n;
        if (this.mPanel == null) {
            return;
        }
        if (this.mPopupWindow == null) {
            this.mPopupWindow = new PopupWindow(this.getContext(), null, 16843464);
            this.mPopupWindow.setWindowLayoutType(1002);
            this.mPopupWindow.setOutsideTouchable(true);
            this.mPopupWindow.setBackgroundDrawable((Drawable)new ColorDrawable(this.getContext().getResources().getColor(17170445)));
            this.mPopupWindow.setInputMethodMode(2);
        }
        int[] arrn = new int[2];
        int[] arrn2 = new int[2];
        this.getLocationOnScreen(arrn);
        int n2 = arrn[1];
        View view = (View)this.getParent();
        while (view.getParent() instanceof View) {
            view.getLocationOnScreen(arrn2);
            n = n2;
            if (arrn2[1] > n2) {
                n = arrn2[1];
            }
            view = (View)view.getParent();
            n2 = n;
        }
        if (n2 >= arrn[1] + this.mImageView.getHeight()) {
            this.dismissPopup();
            return;
        }
        this.mPanel.measure(0, 0);
        n = this.mPanel.getMeasuredHeight();
        int n3 = arrn[0] + this.mImageView.getWidth() / 2;
        int n4 = Math.max((int)0, (int)(n3 - this.mPanel.getMeasuredWidth() / 2));
        this.mPanel.setArrowPos(n3 - n4);
        if (this.mPopupWindow.getContentView() != this.mPanel) {
            this.mPopupWindow.setContentView((View)this.mPanel);
        }
        if (!this.mPopupWindow.isShowing()) {
            this.mPopupWindow.showAtLocation((View)this, 0, 0, 0);
            this.getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        }
        this.mPopupWindow.update(n4, n2 - n, this.mPanel.getMeasuredWidth(), this.mPanel.getMeasuredHeight());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void update(WorkingMessage object) {
        this.setAudioName(null);
        if (!object.hasAttachment()) {
            this.setVisibility(8);
            this.updateAudioName();
            return;
        }
        this.mImageView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
        this.mSlideshow = object.getSlideshow();
        if (!object.hasSlideshow()) {
            if (this.mSlideshow.sizeOfFilesAttach() == 1) {
                if (!((FileAttachmentModel)this.mSlideshow.getAttachFiles().get(0)).isVCard()) {
                    throw new IllegalStateException("Unsupported attachment type");
                }
                this.mImageView.setImageResource(2130837633);
                this.setPanelResid(2130968717);
                this.mPlayIcon.setVisibility(8);
                this.setVisibility(0);
            } else {
                object = this.mSlideshow.get(0);
                if (Build.IS_CM_CUSTOMIZATION_TEST && object.hasAudio() && object.hasImage()) {
                    this.setAudioName(object.getAudio().getSrc());
                }
                if (object.hasImage()) {
                    this.setImageDrawable(object.getImage().getDrawable(), false);
                    this.setPanelResid(2130968627);
                    this.mPlayIcon.setVisibility(8);
                    this.setVisibility(0);
                } else if (object.hasVideo()) {
                    this.setImageBitmap(this.createVideoThumbnail(object.getVideo().getUri()), true);
                    this.setPanelResid(2130968718);
                    this.mPlayIcon.setVisibility(0);
                    this.setVisibility(0);
                } else if (object.hasAudio()) {
                    object = object.getAudio();
                    this.mImageView.setImageResource(2130837711);
                    this.setPanelResid(2130968579);
                    this.mPlayIcon.setVisibility(8);
                    this.setVisibility(0);
                    this.setAudioName(object.getSrc());
                }
            }
        } else {
            this.mImageView.setImageResource(2130837773, false);
            this.setPanelResid(2130968709);
            this.mPlayIcon.setVisibility(8);
            this.setVisibility(0);
        }
        this.updateAudioName();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void updateAudioName() {
        if (!Build.IS_CM_CUSTOMIZATION_TEST || this.mAudioNameView == null) return;
        if (!TextUtils.isEmpty((CharSequence)this.mAudioName)) {
            this.mAudioNameView.setText((CharSequence)this.mAudioName);
            this.mAudioNameView.setVisibility(0);
            return;
        }
        this.mAudioNameView.setVisibility(8);
    }

}

