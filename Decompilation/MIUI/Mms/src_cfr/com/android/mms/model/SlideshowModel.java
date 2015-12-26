/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.media.MediaDrmException
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.ContentType
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.MultimediaMessagePdu
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.PduPart
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  miui.os.Build
 */
package com.android.mms.model;

import android.content.ContentResolver;
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
import com.android.mms.model.ContentRestrictionFactory;
import com.android.mms.model.FileAttachmentModel;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.MediaModelFactory;
import com.android.mms.model.Model;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SmilHelper;
import com.android.mms.model.TextModel;
import com.android.mms.model.VCardModel;
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
import org.w3c.dom.Node;
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
implements List<SlideModel>,
IModelChangedObserver {
    private final ArrayList<FileAttachmentModel> mAttachFiles;
    private Context mContext;
    private int mCurrentMessageSize;
    private SMILDocument mDocumentCache;
    private final LayoutModel mLayout;
    private PduBody mPduBodyCache;
    private final ArrayList<SlideModel> mSlides;
    private int mTotalMessageSize;

    private SlideshowModel(Context context) {
        this.mLayout = new LayoutModel();
        this.mSlides = new ArrayList();
        this.mAttachFiles = new ArrayList();
        this.mContext = context;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mCurrentMessageSize = 110;
        }
    }

    private SlideshowModel(LayoutModel object, ArrayList<SlideModel> object22, ArrayList<FileAttachmentModel> arrayList, SMILDocument sMILDocument, PduBody pduBody, Context context) {
        this.mLayout = object;
        this.mSlides = object22;
        this.mAttachFiles = arrayList;
        this.mContext = context;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mCurrentMessageSize = 110;
        }
        this.mDocumentCache = sMILDocument;
        this.mPduBodyCache = pduBody;
        for (Object object22 : this.mSlides) {
            this.increaseMessageSize(object22.getSlideSize());
            object22.setParent(this);
        }
        object = this.mAttachFiles.iterator();
        while (object.hasNext()) {
            this.increaseMessageSize(((FileAttachmentModel)object.next()).getAttachSize());
        }
    }

    public static SlideshowModel createFromMessageUri(Context context, Uri uri) throws MmsException {
        return SlideshowModel.createFromPduBody(context, SlideshowModel.getPduBody(context, uri));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static SlideshowModel createFromPduBody(Context var0, PduBody var1_1) throws MmsException {
        var10_2 = SmilHelper.getDocument(var1_1);
        var12_3 = var10_2.getLayout();
        var9_4 = var12_3.getRootLayout();
        var4_5 = var9_4.getWidth();
        var5_6 = var9_4.getHeight();
        if (var4_5 == 0) ** GOTO lbl-1000
        var3_7 = var5_6;
        if (var5_6 == 0) lbl-1000: // 2 sources:
        {
            var4_5 = LayoutManager.getInstance().getLayoutParameters().getWidth();
            var3_7 = LayoutManager.getInstance().getLayoutParameters().getHeight();
            var9_4.setWidth(var4_5);
            var9_4.setHeight(var3_7);
        }
        var9_4 = new RegionModel(null, 0, 0, var4_5, var3_7);
        var11_8 = new ArrayList();
        var12_3 = var12_3.getRegions();
        var4_5 = var12_3.getLength();
        for (var3_7 = 0; var3_7 < var4_5; ++var3_7) {
            var13_9 /* !! */  = (SMILRegionElement)var12_3.item(var3_7);
            var11_8.add((Object)new RegionModel(var13_9 /* !! */ .getId(), var13_9 /* !! */ .getFit(), var13_9 /* !! */ .getLeft(), var13_9 /* !! */ .getTop(), var13_9 /* !! */ .getWidth(), var13_9 /* !! */ .getHeight(), var13_9 /* !! */ .getBackgroundColor()));
        }
        var11_8 = new LayoutModel((RegionModel)var9_4, (ArrayList<RegionModel>)var11_8);
        var9_4 = var10_2.getBody().getChildNodes();
        var7_10 = var9_4.getLength();
        var12_3 = new ArrayList(var7_10);
        var3_7 = 0;
        var4_5 = 0;
        block8 : do {
            if (var4_5 >= var7_10) break;
            var13_9 /* !! */  = (SMILParElement)var9_4.item(var4_5);
            var14_14 = var13_9 /* !! */ .getChildNodes();
            var8_13 = var14_14.getLength();
            var15_15 = new ArrayList(var8_13);
            var5_6 = 0;
            do {
                if (var5_6 < var8_13) {
                    var16_16 = (SMILMediaElement)var14_14.item(var5_6);
                    try {
                        var17_20 = MediaModelFactory.getMediaModel((Context)var0, (SMILMediaElement)var16_16, (LayoutModel)var11_8, var1_1);
                        if (!MmsConfig.getSlideDurationEnabled()) {
                            var6_12 = var17_20.getDuration();
                            var2_11 = var13_9 /* !! */ .getDur();
                            if (var2_11 == 0.0f) {
                                var6_12 = MmsConfig.getMinimumSlideElementDuration() * 1000;
                                var17_20.setDuration(var6_12);
                            }
                            if ((float)(var6_12 / 1000) != var2_11) {
                                var18_21 = var16_16.getTagName();
                                if (ContentType.isVideoType((String)var17_20.mContentType) || var18_21.equals((Object)"video") || ContentType.isAudioType((String)var17_20.mContentType) || var18_21.equals((Object)"audio")) {
                                    var13_9 /* !! */ .setDur((float)var6_12 / 1000.0f + 1.0f);
                                } else if ((float)(var6_12 / 1000) < var2_11) {
                                    var17_20.setDuration((int)var2_11 * 1000);
                                } else if ((int)var2_11 != 0) {
                                    var6_12 = (int)var2_11;
                                    var17_20.setDuration(var6_12 * 1000);
                                } else {
                                    var2_11 = (float)var6_12 / 1000.0f;
                                    var13_9 /* !! */ .setDur(var2_11);
                                }
                            }
                        }
                        SmilHelper.addMediaElementEventListeners((EventTarget)var16_16, (MediaModel)var17_20);
                        var15_15.add(var17_20);
                        var3_7 = var6_12 = var3_7 + var17_20.getMediaSize();
                    }
                    catch (MediaDrmException var16_17) {
                        Log.e((String)"Mms/slideshow", (String)var16_17.getMessage(), (Throwable)var16_17);
                    }
                    catch (IOException var16_18) {
                        Log.e((String)"Mms/slideshow", (String)var16_18.getMessage(), (Throwable)var16_18);
                    }
                    catch (IllegalArgumentException var16_19) {
                        Log.e((String)"Mms/slideshow", (String)var16_19.getMessage(), (Throwable)var16_19);
                    }
                } else {
                    var14_14 = new SlideModel((int)(var13_9 /* !! */ .getDur() * 1000.0f), (ArrayList<MediaModel>)var15_15);
                    var14_14.setFill(var13_9 /* !! */ .getFill());
                    SmilHelper.addParElementEventListeners((EventTarget)var13_9 /* !! */ , (SlideModel)var14_14);
                    var12_3.add(var14_14);
                    ++var4_5;
                    continue block8;
                }
                ++var5_6;
            } while (true);
            break;
        } while (true);
        var6_12 = var1_1.getPartsNum();
        var13_9 /* !! */  = new ArrayList();
        var4_5 = 0;
        var5_6 = var3_7;
        do {
            if (var4_5 >= var6_12) {
                var0 = new SlideshowModel((LayoutModel)var11_8, (ArrayList<SlideModel>)var12_3, (ArrayList<FileAttachmentModel>)var13_9 /* !! */ , var10_2, var1_1, (Context)var0);
                var0.mTotalMessageSize = var5_6;
                var0.registerModelChangedObserver((IModelChangedObserver)var0);
                return var0;
            }
            var14_14 = var1_1.getPart(var4_5);
            var9_4 = var14_14.getContentLocation();
            var16_16 = var14_14.getName();
            var17_20 = var14_14.getContentId();
            var18_21 = var14_14.getFilename();
            var15_15 = var14_14.getData();
            if (var9_4 == null) ** GOTO lbl101
            var9_4 = new String((byte[])var9_4);
            ** GOTO lbl109
lbl101: // 1 sources:
            if (var16_16 == null) ** GOTO lbl104
            var9_4 = new String((byte[])var16_16);
            ** GOTO lbl109
lbl104: // 1 sources:
            if (var17_20 == null) ** GOTO lbl107
            var9_4 = new String((byte[])var17_20);
            ** GOTO lbl109
lbl107: // 1 sources:
            if (var18_21 == null) ** GOTO lbl114
            var9_4 = new String((byte[])var18_21);
lbl109: // 4 sources:
            if (!FileAttachmentModel.isVCard((PduPart)var14_14)) ** GOTO lbl117
            var9_4 = new VCardModel((Context)var0, "text/x-vCard", (String)var9_4, var14_14.getDataUri());
            var13_9 /* !! */ .add(var9_4);
            var3_7 = var5_6 + var9_4.getAttachSize();
            ** GOTO lbl126
lbl114: // 1 sources:
            LogTag.error("Cannot decide file name for part " + var4_5, new Object[0]);
            var3_7 = var5_6;
            ** GOTO lbl126
lbl117: // 1 sources:
            if (var15_15 != null && new String((byte[])var15_15).startsWith("BEGIN:VCARD")) {
                var3_7 = var5_6;
                if (new String((byte[])var15_15).startsWith("BEGIN:VCARD")) {
                    var9_4 = new VCardModel((Context)var0, "text/x-vCard", (String)var9_4 + ".vcf", var14_14.getDataUri());
                    var13_9 /* !! */ .add(var9_4);
                    var3_7 = var5_6 + var9_4.getAttachSize();
                }
            } else {
                LogTag.error("Unrecognized attachment part " + var4_5, new Object[0]);
                var3_7 = var5_6;
            }
lbl126: // 4 sources:
            ++var4_5;
            var5_6 = var3_7;
        } while (true);
    }

    public static SlideshowModel createNew(Context context) {
        return new SlideshowModel(context);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getLocationFromSrc(String string) {
        if (string == null) {
            return null;
        }
        if (string.startsWith("cid:")) {
            string = string.substring("cid:".length());
        }
        int n = string.lastIndexOf("/");
        String string2 = string;
        if (-1 == n) return string2;
        string2 = string;
        if (n >= string.length()) return string2;
        return string.substring(n + 1);
    }

    public static PduBody getPduBody(Context context, Uri uri) throws MmsException {
        int n = (context = MiuiPduPersister.getPduPersister((Context)context).load(uri)).getMessageType();
        if (n == 128 || n == 132) {
            return ((MultimediaMessagePdu)context).getBody();
        }
        throw new MmsException();
    }

    private PduBody makePduBody(SMILDocument sMILDocument) {
        return this.makePduBody(sMILDocument, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private PduBody makePduBody(SMILDocument object, boolean bl) {
        MediaModel mediaModel;
        SlideModel slideModel /* !! */ ;
        Object object2;
        PduBody pduBody = new PduBody();
        int n = 0;
        do {
            if (n >= this.mSlides.size()) break;
            slideModel /* !! */  = (SlideModel)this.mSlides.get(n);
            for (int i = 0; i < slideModel /* !! */ .size(); ++i) {
                mediaModel = slideModel /* !! */ .get(i);
                PduPart pduPart = new PduPart();
                if (mediaModel.isText()) {
                    object2 = (TextModel)mediaModel;
                    if (TextUtils.isEmpty((CharSequence)object2.getText())) continue;
                    pduPart.setCharset(object2.getCharset());
                }
                pduPart.setContentType(mediaModel.getContentType().getBytes());
                String string = mediaModel.getSrc();
                object2 = this.getLocationFromSrc(string);
                pduPart.setContentLocation(object2.getBytes());
                if (string.startsWith("cid:")) {
                    pduPart.setContentId(object2.getBytes());
                } else {
                    int n2 = object2.lastIndexOf(".");
                    if (n2 != -1) {
                        object2 = object2.substring(0, n2);
                    }
                    pduPart.setContentId(object2.getBytes());
                }
                if (mediaModel.isText()) {
                    pduPart.setData(((TextModel)mediaModel).getText().getBytes());
                } else if (mediaModel.isImage() || mediaModel.isVideo() || mediaModel.isAudio()) {
                    pduPart.setDataUri(mediaModel.getUri());
                } else {
                    Log.w((String)"Mms/slideshow", (String)("Unsupport media: " + mediaModel));
                }
                pduBody.addPart(pduPart);
            }
            ++n;
        } while (true);
        object2 = new ByteArrayOutputStream();
        SmilXmlSerializer.serialize((SMILDocument)object, (OutputStream)object2);
        object = new PduPart();
        object.setContentId("smil".getBytes());
        object.setContentLocation("smil.xml".getBytes());
        object.setContentType("application/smil".getBytes());
        object.setData(object2.toByteArray());
        pduBody.addPart(0, (PduPart)object);
        object2 = this.mAttachFiles.iterator();
        while (object2.hasNext()) {
            slideModel /* !! */  = (FileAttachmentModel)object2.next();
            mediaModel = new PduPart();
            mediaModel.setContentType(slideModel /* !! */ .getContentType().toLowerCase().getBytes());
            object = slideModel /* !! */ .getSrc();
            bl = object.startsWith("cid:");
            if (bl) {
                object = object.substring("cid:".length());
            }
            mediaModel.setContentLocation(object.getBytes());
            if (bl) {
                mediaModel.setContentId(object.getBytes());
            } else {
                n = object.lastIndexOf(".");
                if (n != -1) {
                    object = object.substring(0, n);
                }
                mediaModel.setContentId(object.getBytes());
            }
            mediaModel.setName(slideModel /* !! */ .getSrc().getBytes());
            mediaModel.setFilename(slideModel /* !! */ .getSrc().getBytes());
            if (slideModel /* !! */ .isVCard()) {
                mediaModel.setDataUri(slideModel /* !! */ .getUri());
            } else {
                LogTag.warn("Unsupport file attachment: %s", slideModel /* !! */ );
            }
            pduBody.addPart((PduPart)mediaModel);
        }
        return pduBody;
    }

    @Override
    public void add(int n, SlideModel slideModel) {
        if (slideModel != null) {
            int n2 = slideModel.getSlideSize();
            this.checkMessageSize(n2);
            this.mSlides.add(n, (Object)slideModel);
            this.increaseMessageSize(n2);
            slideModel.registerModelChangedObserver(this);
            Iterator iterator = this.mModelChangedObservers.iterator();
            while (iterator.hasNext()) {
                slideModel.registerModelChangedObserver((IModelChangedObserver)iterator.next());
            }
            this.notifyModelChanged(true);
        }
    }

    @Override
    public boolean add(SlideModel slideModel) {
        if (slideModel != null) {
            int n = slideModel.getSlideSize();
            this.checkMessageSize(n);
            this.mSlides.add((Object)slideModel);
            this.increaseMessageSize(n);
            slideModel.registerModelChangedObserver(this);
            Iterator iterator = this.mModelChangedObservers.iterator();
            while (iterator.hasNext()) {
                slideModel.registerModelChangedObserver((IModelChangedObserver)iterator.next());
            }
            this.notifyModelChanged(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int n, Collection<? extends SlideModel> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    @Override
    public boolean addAll(Collection<? extends SlideModel> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    public boolean addFileAttachment(FileAttachmentModel fileAttachmentModel) {
        if (fileAttachmentModel != null) {
            int n = fileAttachmentModel.getAttachSize();
            this.checkMessageSize(n);
            this.mAttachFiles.add((Object)fileAttachmentModel);
            this.increaseMessageSize(n);
            fileAttachmentModel.registerModelChangedObserver(this);
            Iterator iterator = this.mModelChangedObservers.iterator();
            while (iterator.hasNext()) {
                fileAttachmentModel.registerModelChangedObserver((IModelChangedObserver)iterator.next());
            }
            this.notifyModelChanged(true);
            return true;
        }
        return false;
    }

    public void checkMessageSize(int n) throws ContentRestrictionException {
        ContentRestrictionFactory.getContentRestriction().checkMessageSize(this.mCurrentMessageSize, n, this.mContext.getContentResolver());
    }

    @Override
    public void clear() {
        if (this.mSlides.size() > 0) {
            for (SlideModel slideModel : this.mSlides) {
                slideModel.unregisterModelChangedObserver(this);
                Iterator iterator = this.mModelChangedObservers.iterator();
                while (iterator.hasNext()) {
                    slideModel.unregisterModelChangedObserver((IModelChangedObserver)iterator.next());
                }
            }
            this.mCurrentMessageSize = 0;
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                this.mCurrentMessageSize = 110;
            }
            this.mSlides.clear();
            this.notifyModelChanged(true);
        }
    }

    @Override
    public boolean contains(Object object) {
        return this.mSlides.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.mSlides.containsAll(collection);
    }

    public void decreaseMessageSize(int n) {
        if (n > 0) {
            this.mCurrentMessageSize -= n;
        }
    }

    public void finalResize(Uri uri) throws MmsException, ExceedMessageSizeException {
        Iterator<MediaModel> iterator;
        int n = 0;
        int n2 = 0;
        Iterator iterator2 = this.mAttachFiles.iterator();
        while (iterator2.hasNext()) {
            n2 += ((FileAttachmentModel)iterator2.next()).getAttachSize();
        }
        iterator2 = this.mSlides.iterator();
        int n3 = n2;
        block1 : while (iterator2.hasNext()) {
            iterator = ((SlideModel)iterator2.next()).iterator();
            int n4 = n;
            n2 = n3;
            do {
                n3 = n2;
                n = n4++;
                if (!iterator.hasNext()) continue block1;
                MediaModel mediaModel = iterator.next();
                if (mediaModel.getMediaResizable()) continue;
                n2 += mediaModel.getMediaSize();
            } while (true);
        }
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"Mms/slideshow", (String)("finalResize: original message size: " + this.getCurrentMessageSize() + " getMaxMessageSize: " + MmsConfig.getMaxMessageSize() + " fixedSizeTotal: " + n3));
        }
        if (n > 0) {
            n2 = MmsConfig.getMaxMessageSize() - n3 - 1024;
            if (n2 <= 0) {
                throw new ExceedMessageSizeException("No room for pictures");
            }
            long l = ContentUris.parseId((Uri)uri);
            n2 /= n;
            iterator2 = this.mSlides.iterator();
            while (iterator2.hasNext()) {
                for (MediaModel mediaModel : (SlideModel)iterator2.next()) {
                    if (!mediaModel.getMediaResizable()) continue;
                    mediaModel.resizeMedia(n2, l);
                }
            }
            n2 = 0;
            iterator2 = this.mSlides.iterator();
            block5 : while (iterator2.hasNext()) {
                iterator = ((SlideModel)iterator2.next()).iterator();
                n = n2;
                do {
                    n2 = n;
                    if (!iterator.hasNext()) continue block5;
                    n += iterator.next().getMediaSize();
                } while (true);
            }
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.v((String)"Mms/slideshow", (String)("finalResize: new message size: " + n2));
            }
            if (n2 > MmsConfig.getMaxMessageSize()) {
                throw new ExceedMessageSizeException("After compressing pictures, message too big");
            }
            this.setCurrentMessageSize(n2);
            this.onModelChanged(this, true);
            iterator2 = this.toPduBody();
            MiuiPduPersister.getPduPersister((Context)this.mContext).updateParts(uri, (PduBody)iterator2);
        }
    }

    @Override
    public SlideModel get(int n) {
        if (n >= 0 && n < this.mSlides.size()) {
            return (SlideModel)this.mSlides.get(n);
        }
        return null;
    }

    public ArrayList<FileAttachmentModel> getAttachFiles() {
        return this.mAttachFiles;
    }

    public int getCurrentMessageSize() {
        return this.mCurrentMessageSize;
    }

    public LayoutModel getLayout() {
        return this.mLayout;
    }

    public void increaseMessageSize(int n) {
        if (n > 0) {
            this.mCurrentMessageSize += n;
        }
    }

    @Override
    public int indexOf(Object object) {
        return this.mSlides.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return this.mSlides.isEmpty();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isSimple() {
        SlideModel slideModel;
        if (this.size() != 1 || !((slideModel = this.get(0)).hasImage() ^ slideModel.hasVideo()) || slideModel.hasAudio()) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<SlideModel> iterator() {
        return this.mSlides.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return this.mSlides.lastIndexOf(object);
    }

    @Override
    public ListIterator<SlideModel> listIterator() {
        return this.mSlides.listIterator();
    }

    @Override
    public ListIterator<SlideModel> listIterator(int n) {
        return this.mSlides.listIterator(n);
    }

    @Override
    public void onModelChanged(Model model, boolean bl) {
        if (bl) {
            this.mDocumentCache = null;
            this.mPduBodyCache = null;
        }
    }

    public void prepareForSend() {
        TextModel textModel;
        if (this.size() == 1 && (textModel = this.get(0).getText()) != null) {
            textModel.cloneText();
        }
    }

    @Override
    protected void registerModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
        this.mLayout.registerModelChangedObserver(iModelChangedObserver);
        Iterator iterator = this.mSlides.iterator();
        while (iterator.hasNext()) {
            ((SlideModel)iterator.next()).registerModelChangedObserver(iModelChangedObserver);
        }
    }

    @Override
    public SlideModel remove(int n) {
        SlideModel slideModel = (SlideModel)this.mSlides.remove(n);
        if (slideModel != null) {
            this.decreaseMessageSize(slideModel.getSlideSize());
            slideModel.unregisterAllModelChangedObservers();
            this.notifyModelChanged(true);
        }
        return slideModel;
    }

    @Override
    public boolean remove(Object object) {
        if (object != null && this.mSlides.remove(object)) {
            object = (SlideModel)object;
            this.decreaseMessageSize(object.getSlideSize());
            object.unregisterAllModelChangedObservers();
            this.notifyModelChanged(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    public void removeAllAttachFiles() {
        for (int i = this.mAttachFiles.size() - 1; i >= 0; --i) {
            this.removeAttachFile(i);
        }
    }

    public FileAttachmentModel removeAttachFile(int n) {
        FileAttachmentModel fileAttachmentModel = (FileAttachmentModel)this.mAttachFiles.remove(n);
        if (fileAttachmentModel != null) {
            this.decreaseMessageSize(fileAttachmentModel.getAttachSize());
            fileAttachmentModel.unregisterAllModelChangedObservers();
            this.notifyModelChanged(true);
        }
        return fileAttachmentModel;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public SlideModel set(int n, SlideModel slideModel) {
        SlideModel slideModel2 = (SlideModel)this.mSlides.get(n);
        if (slideModel != null) {
            int n2 = 0;
            int n3 = slideModel.getSlideSize();
            if (slideModel2 != null) {
                n2 = slideModel2.getSlideSize();
            }
            if (n3 > n2) {
                this.checkMessageSize(n3 - n2);
                this.increaseMessageSize(n3 - n2);
            } else {
                this.decreaseMessageSize(n2 - n3);
            }
        }
        if ((slideModel2 = (SlideModel)this.mSlides.set(n, (Object)slideModel)) != null) {
            slideModel2.unregisterAllModelChangedObservers();
        }
        if (slideModel != null) {
            slideModel.registerModelChangedObserver(this);
            Iterator iterator = this.mModelChangedObservers.iterator();
            while (iterator.hasNext()) {
                slideModel.registerModelChangedObserver((IModelChangedObserver)iterator.next());
            }
        }
        this.notifyModelChanged(true);
        return slideModel2;
    }

    public void setCurrentMessageSize(int n) {
        this.mCurrentMessageSize = n;
    }

    @Override
    public int size() {
        return this.mSlides.size();
    }

    public int sizeOfFilesAttach() {
        if (this.mAttachFiles == null) {
            return 0;
        }
        return this.mAttachFiles.size();
    }

    @Override
    public List<SlideModel> subList(int n, int n2) {
        return this.mSlides.subList(n, n2);
    }

    public void sync(PduBody pduBody) {
        Iterator iterator = this.mSlides.iterator();
        while (iterator.hasNext()) {
            for (MediaModel mediaModel : (SlideModel)iterator.next()) {
                PduPart pduPart = pduBody.getPartByContentLocation(this.getLocationFromSrc(mediaModel.getSrc()));
                if (pduPart == null) continue;
                mediaModel.setUri(pduPart.getDataUri());
            }
        }
        for (Object object : this.mAttachFiles) {
            MediaModel mediaModel;
            mediaModel = pduBody.getPartByContentLocation(object.getSrc());
            if (mediaModel == null) continue;
            object.setUri(mediaModel.getDataUri());
            object.setData(mediaModel.getData());
        }
    }

    @Override
    public Object[] toArray() {
        return this.mSlides.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arrT) {
        return this.mSlides.toArray((Object[])arrT);
    }

    public PduBody toPduBody() {
        if (this.mPduBodyCache == null) {
            this.mDocumentCache = SmilHelper.getDocument(this);
            this.mPduBodyCache = this.makePduBody(this.mDocumentCache);
        }
        return this.mPduBodyCache;
    }

    @Override
    protected void unregisterAllModelChangedObserversInDescendants() {
        this.mLayout.unregisterAllModelChangedObservers();
        Iterator iterator = this.mSlides.iterator();
        while (iterator.hasNext()) {
            ((SlideModel)iterator.next()).unregisterAllModelChangedObservers();
        }
    }

    @Override
    protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
        this.mLayout.unregisterModelChangedObserver(iModelChangedObserver);
        Iterator iterator = this.mSlides.iterator();
        while (iterator.hasNext()) {
            ((SlideModel)iterator.next()).unregisterModelChangedObserver(iModelChangedObserver);
        }
    }
}

