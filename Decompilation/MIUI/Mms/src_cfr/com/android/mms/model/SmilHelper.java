/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.PduPart
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Arrays
 *  miui.os.Build
 *  org.xml.sax.SAXException
 */
package com.android.mms.model;

import android.text.TextUtils;
import android.util.Log;
import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.parser.SmilXmlParser;
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
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import miui.os.Build;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.events.EventListener;
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

public class SmilHelper {
    private SmilHelper() {
    }

    static void addMediaElementEventListeners(EventTarget eventTarget, MediaModel mediaModel) {
        eventTarget.addEventListener("SmilMediaStart", mediaModel, false);
        eventTarget.addEventListener("SmilMediaEnd", mediaModel, false);
        eventTarget.addEventListener("SmilMediaPause", mediaModel, false);
        eventTarget.addEventListener("SmilMediaSeek", mediaModel, false);
    }

    public static SMILParElement addPar(SMILDocument sMILDocument) {
        SMILParElement sMILParElement = (SMILParElement)sMILDocument.createElement("par");
        sMILParElement.setDur(8.0f);
        sMILDocument.getBody().appendChild((Node)sMILParElement);
        return sMILParElement;
    }

    static void addParElementEventListeners(EventTarget eventTarget, SlideModel slideModel) {
        eventTarget.addEventListener("SmilSlideStart", slideModel, false);
        eventTarget.addEventListener("SmilSlideEnd", slideModel, false);
    }

    public static SMILMediaElement createMediaElement(String object, SMILDocument sMILDocument, String string) {
        object = (SMILMediaElement)sMILDocument.createElement((String)object);
        object.setSrc(SmilHelper.parseSrc(string));
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static SMILDocument createSmilDocument(SlideshowModel var0) {
        var5_1 = new SmilDocumentImpl();
        var4_2 = (SMILElement)var5_1.createElement("smil");
        var5_1.appendChild((Node)var4_2);
        var7_3 = (SMILElement)var5_1.createElement("head");
        var4_2.appendChild((Node)var7_3);
        var6_4 = (SMILLayoutElement)var5_1.createElement("layout");
        var7_3.appendChild((Node)var6_4);
        var7_3 = (SMILRootLayoutElement)var5_1.createElement("root-layout");
        var8_5 = var0.getLayout();
        var7_3.setWidth(var8_5.getLayoutWidth());
        var7_3.setHeight(var8_5.getLayoutHeight());
        var9_6 = var8_5.getBackgroundColor();
        if (!TextUtils.isEmpty((CharSequence)var9_6)) {
            var7_3.setBackgroundColor((String)var9_6);
        }
        var6_4.appendChild((Node)var7_3);
        var8_5 = var8_5.getRegions();
        var7_3 = new ArrayList();
        var8_5 = var8_5.iterator();
        while (var8_5.hasNext()) {
            var9_6 = (RegionModel)var8_5.next();
            var10_7 = (SMILRegionElement)var5_1.createElement("region");
            var10_7.setId(var9_6.getRegionId());
            var10_7.setLeft(var9_6.getLeft());
            var10_7.setTop(var9_6.getTop());
            var10_7.setWidth(var9_6.getWidth());
            var10_7.setHeight(var9_6.getHeight());
            var10_7.setFit(var9_6.getFit());
            var7_3.add(var10_7);
        }
        var4_2.appendChild((Node)((SMILElement)var5_1.createElement("body")));
        var8_5 = var0.iterator();
        block1 : do {
            if (var8_5.hasNext() == false) return var5_1;
            var0 = var8_5.next();
            var3_10 = false;
            var2_9 = false;
            var9_6 = SmilHelper.addPar(var5_1);
            var9_6.setDur((float)var0.getDuration() / 1000.0f);
            SmilHelper.addParElementEventListeners((EventTarget)var9_6, (SlideModel)var0);
            var10_7 = var0.iterator();
            do {
                if (!var10_7.hasNext()) continue block1;
                var11_11 = (MediaModel)var10_7.next();
                var4_2 = var11_11.getSrc();
                var1_8 = var4_2.lastIndexOf("/");
                var0 = var4_2;
                if (-1 != var1_8) {
                    var0 = var4_2;
                    if (var1_8 < var4_2.length()) {
                        var0 = var4_2.substring(var1_8 + 1);
                    }
                }
                if (!(var11_11 instanceof TextModel)) ** GOTO lbl56
                if (TextUtils.isEmpty((CharSequence)((TextModel)var11_11).getText())) continue;
                var0 = SmilHelper.createMediaElement("text", var5_1, (String)var0);
                var3_10 = SmilHelper.setRegion((SMILRegionMediaElement)var0, var7_3, var6_4, "Text", var3_10);
                ** GOTO lbl66
lbl56: // 1 sources:
                if (!(var11_11 instanceof ImageModel)) ** GOTO lbl60
                var0 = SmilHelper.createMediaElement("img", var5_1, (String)var0);
                var2_9 = SmilHelper.setRegion((SMILRegionMediaElement)var0, var7_3, var6_4, "Image", var2_9);
                ** GOTO lbl66
lbl60: // 1 sources:
                if (!(var11_11 instanceof VideoModel)) ** GOTO lbl64
                var0 = SmilHelper.createMediaElement("video", var5_1, (String)var0);
                var2_9 = SmilHelper.setRegion((SMILRegionMediaElement)var0, var7_3, var6_4, "Image", var2_9);
                ** GOTO lbl66
lbl64: // 1 sources:
                if (var11_11 instanceof AudioModel) {
                    var0 = SmilHelper.createMediaElement("audio", var5_1, (String)var0);
lbl66: // 4 sources:
                    if (!Build.IS_CM_CUSTOMIZATION_TEST) {
                        var1_8 = var11_11.getBegin();
                        if (var1_8 != 0) {
                            var0.setAttribute("begin", String.valueOf((int)(var1_8 / 1000)));
                        }
                        if ((var1_8 = var11_11.getDuration()) != 0) {
                            var0.setDur((float)var1_8 / 1000.0f);
                        }
                    }
                    var9_6.appendChild((Node)var0);
                    SmilHelper.addMediaElementEventListeners((EventTarget)var0, var11_11);
                    continue;
                }
                Log.w((String)"Mms/smil", (String)("Unsupport media: " + var11_11));
            } while (true);
            break;
        } while (true);
    }

    /*
     * Exception decompiling
     */
    private static SMILDocument createSmilDocument(PduBody var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:412)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:219)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:619)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:45)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:669)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public static String escapeXML(String string) {
        return string.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
    }

    private static SMILRegionElement findRegionElementById(ArrayList<SMILRegionElement> object, String string) {
        object = object.iterator();
        while (object.hasNext()) {
            SMILRegionElement sMILRegionElement = (SMILRegionElement)object.next();
            if (!sMILRegionElement.getId().equals((Object)string)) continue;
            return sMILRegionElement;
        }
        return null;
    }

    private static PduPart findSmilPart(PduBody pduBody) {
        int n = pduBody.getPartsNum();
        for (int i = 0; i < n; ++i) {
            PduPart pduPart = pduBody.getPart(i);
            if (!Arrays.equals((byte[])pduPart.getContentType(), (byte[])"application/smil".getBytes())) continue;
            return pduPart;
        }
        return null;
    }

    public static SMILDocument getDocument(SlideshowModel slideshowModel) {
        return SmilHelper.createSmilDocument(slideshowModel);
    }

    public static SMILDocument getDocument(PduBody pduBody) {
        Object object = SmilHelper.findSmilPart(pduBody);
        SMILDocument sMILDocument = null;
        if (object != null) {
            sMILDocument = SmilHelper.getSmilDocument((PduPart)object);
        }
        object = sMILDocument;
        if (sMILDocument == null) {
            object = SmilHelper.createSmilDocument(pduBody);
        }
        return object;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static SMILDocument getSmilDocument(PduPart object) {
        try {
            object = object.getData();
            if (object == null) return null;
            object = new ByteArrayInputStream((byte[])object);
            return SmilHelper.validate(new SmilXmlParser().parse((InputStream)object));
        }
        catch (IOException var0_1) {
            Log.e((String)"Mms/smil", (String)"Failed to parse SMIL document.", (Throwable)var0_1);
        }
        return null;
        catch (SAXException sAXException) {
            Log.e((String)"Mms/smil", (String)"Failed to parse SMIL document.", (Throwable)sAXException);
            return null;
        }
        catch (MmsException mmsException) {
            Log.e((String)"Mms/smil", (String)"Failed to parse SMIL document.", (Throwable)mmsException);
            return null;
        }
    }

    private static String parseSrc(String string) {
        String string2 = string;
        if (string.startsWith("cid:")) {
            String string3 = string.substring("cid:".length());
            string2 = string;
            if (string3.startsWith("<")) {
                string2 = string;
                if (string3.endsWith(">")) {
                    string2 = "cid:" + string3.substring(1, string3.length() - 1);
                }
            }
        }
        return SmilHelper.escapeXML(string2);
    }

    private static boolean setRegion(SMILRegionMediaElement sMILRegionMediaElement, ArrayList<SMILRegionElement> object, SMILLayoutElement sMILLayoutElement, String string, boolean bl) {
        object = SmilHelper.findRegionElementById(object, string);
        if (!bl && object != null) {
            sMILRegionMediaElement.setRegion((SMILRegionElement)object);
            sMILLayoutElement.appendChild((Node)object);
            return true;
        }
        return false;
    }

    private static SMILDocument validate(SMILDocument sMILDocument) {
        return sMILDocument;
    }
}

