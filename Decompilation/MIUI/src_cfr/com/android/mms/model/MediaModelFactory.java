/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.MediaDrmException
 *  android.net.Uri
 *  android.util.Log
 *  com.google.android.mms.ContentType
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.PduPart
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.model;

import android.content.Context;
import android.media.MediaDrmException;
import android.net.Uri;
import android.util.Log;
import com.android.mms.MmsConfig;
import com.android.mms.model.AudioModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.RegionModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
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

public class MediaModelFactory {
    private static MediaModel createEmptyTextModel(Context context, RegionModel regionModel) throws IOException {
        return new TextModel(context, "text/plain", null, regionModel);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static PduPart findPart(PduBody pduBody, String string) {
        PduPart pduPart = null;
        String string2 = string;
        if (string != null) {
            if ((string = MediaModelFactory.unescapeXML(string)).startsWith("cid:")) {
                pduPart = pduBody.getPartByContentId("<" + string.substring("cid:".length()) + ">");
                string2 = string;
            } else {
                PduPart pduPart2;
                pduPart = pduPart2 = pduBody.getPartByName(string);
                string2 = string;
                if (pduPart2 == null) {
                    pduPart = pduPart2 = pduBody.getPartByFileName(string);
                    string2 = string;
                    if (pduPart2 == null) {
                        pduPart = pduBody.getPartByContentLocation(string);
                        string2 = string;
                    }
                }
            }
        }
        if (pduPart != null) {
            return pduPart;
        }
        if ((pduBody = MediaModelFactory.findPartWithoutSuffix(pduBody, string2)) != null) {
            return pduBody;
        }
        throw new IllegalArgumentException("No part found for the model.");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static PduPart findPartWithoutSuffix(PduBody pduBody, String string) {
        PduPart pduPart = null;
        boolean bl = false;
        PduPart pduPart2 = pduPart;
        if (string == null) return pduPart2;
        String string2 = string;
        if (string.startsWith("cid:")) {
            string2 = string.substring("cid:".length());
            bl = true;
        }
        int n = string2.lastIndexOf(".");
        pduPart2 = pduPart;
        if (n == -1) return pduPart2;
        string = string2.substring(0, n);
        if (!bl) return pduBody.getPartByContentLocation(string);
        return pduBody.getPartByContentId("<" + string + ">");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static MediaModel getGenericMediaModel(Context var0, String var1_1, String var2_2, SMILMediaElement var3_3, PduPart var4_4, RegionModel var5_5) throws MediaDrmException, IOException, MmsException {
        var9_6 = var4_4.getContentType();
        if (var9_6 == null) {
            throw new IllegalArgumentException("Content-Type of the part may not be null.");
        }
        var9_6 = new String((byte[])var9_6);
        if (var1_1.equals((Object)"text")) {
            var0 = new TextModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getCharset(), var4_4.getData(), var5_5);
        } else if (var1_1.equals((Object)"img")) {
            var0 = new ImageModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getDataUri(), var5_5);
        } else if (var1_1.equals((Object)"video")) {
            var0 = new VideoModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getDataUri(), var5_5);
        } else if (var1_1.equals((Object)"audio")) {
            var0 = new AudioModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getDataUri());
        } else {
            if (var1_1.equals((Object)"ref") == false) throw new IllegalArgumentException("Unsupported TAG: " + var1_1);
            if (ContentType.isTextType((String)var9_6)) {
                var0 = new TextModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getCharset(), var4_4.getData(), var5_5);
            } else if (ContentType.isImageType((String)var9_6)) {
                var0 = new ImageModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getDataUri(), var5_5);
            } else if (ContentType.isVideoType((String)var9_6)) {
                var0 = new VideoModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getDataUri(), var5_5);
            } else if (ContentType.isAudioType((String)var9_6)) {
                var0 = new AudioModel((Context)var0, (String)var9_6, (String)var2_2, var4_4.getDataUri());
            } else {
                Log.d((String)"Mms:media", (String)("[MediaModelFactory] getGenericMediaModel Unsupported Content-Type: " + (String)var9_6));
                var0 = MediaModelFactory.createEmptyTextModel((Context)var0, var5_5);
            }
        }
        var6_7 = 0;
        var2_2 = var3_3.getBegin();
        var7_8 = var6_7;
        if (var2_2 != null) {
            var7_8 = var6_7;
            if (var2_2.getLength() > 0) {
                var7_8 = (int)(var2_2.item(0).getResolvedOffset() * 1000.0);
            }
        }
        var0.setBegin(var7_8);
        var6_7 = var8_9 = (int)(var3_3.getDur() * 1000.0f);
        if (var8_9 > 0) ** GOTO lbl59
        var2_2 = var3_3.getEnd();
        var6_7 = var8_9;
        if (var2_2 == null) ** GOTO lbl59
        var6_7 = var8_9;
        if (var2_2.getLength() <= 0) ** GOTO lbl59
        var2_2 = var2_2.item(0);
        var6_7 = var8_9;
        if (var2_2.getTimeType() == 0) ** GOTO lbl59
        var6_7 = var7_8 = (int)(var2_2.getResolvedOffset() * 1000.0) - var7_8;
        if (var7_8 != 0) ** GOTO lbl59
        if (var0 instanceof AudioModel) ** GOTO lbl-1000
        var6_7 = var7_8;
        if (var0 instanceof VideoModel) lbl-1000: // 2 sources:
        {
            var6_7 = var7_8 = MmsConfig.getMinimumSlideElementDuration();
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.d((String)"Mms:media", (String)("[MediaModelFactory] compute new duration for " + var1_1 + ", duration=" + var7_8));
                var6_7 = var7_8;
            }
        }
lbl59: // 10 sources:
        var0.setDuration(var6_7);
        if (!MmsConfig.getSlideDurationEnabled()) {
            var0.setFill(1);
            return var0;
        }
        var0.setFill(var3_3.getFill());
        return var0;
    }

    public static MediaModel getMediaModel(Context context, SMILMediaElement sMILMediaElement, LayoutModel layoutModel, PduBody pduBody) throws MediaDrmException, IOException, IllegalArgumentException, MmsException {
        String string = sMILMediaElement.getTagName();
        String string2 = sMILMediaElement.getSrc();
        pduBody = MediaModelFactory.findPart(pduBody, string2);
        if (sMILMediaElement instanceof SMILRegionMediaElement) {
            return MediaModelFactory.getRegionMediaModel(context, string, string2, (SMILRegionMediaElement)sMILMediaElement, layoutModel, (PduPart)pduBody);
        }
        return MediaModelFactory.getGenericMediaModel(context, string, string2, sMILMediaElement, (PduPart)pduBody, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static MediaModel getRegionMediaModel(Context context, String string, String string2, SMILRegionMediaElement sMILRegionMediaElement, LayoutModel layoutModel, PduPart pduPart) throws MediaDrmException, IOException, MmsException {
        void var5_7;
        Object object = sMILRegionMediaElement.getRegion();
        if (object != null) {
            RegionModel regionModel = layoutModel.findRegionById(object.getId());
            if (regionModel == null) throw new IllegalArgumentException("Region not found or bad region ID.");
            return MediaModelFactory.getGenericMediaModel(context, string, string2, sMILRegionMediaElement, (PduPart)var5_7, regionModel);
        } else {
            object = string.equals((Object)"text") ? "Text" : "Image";
            RegionModel regionModel = layoutModel.findRegionById((String)object);
            if (regionModel == null) throw new IllegalArgumentException("Region not found or bad region ID.");
            return MediaModelFactory.getGenericMediaModel(context, string, string2, sMILRegionMediaElement, (PduPart)var5_7, regionModel);
        }
    }

    private static String unescapeXML(String string) {
        return string.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&amp;", "&");
    }
}

