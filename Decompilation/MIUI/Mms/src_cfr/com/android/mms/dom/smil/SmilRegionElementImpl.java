/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.SmilElementImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SmilRegionElementImpl
extends SmilElementImpl
implements SMILRegionElement {
    SmilRegionElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int parseRegionLength(String string, boolean bl) {
        if (string.endsWith("px")) {
            return Integer.parseInt((String)string.substring(0, string.indexOf("px")));
        }
        if (!string.endsWith("%")) return Integer.parseInt((String)string);
        double d2 = 0.01 * (double)Integer.parseInt((String)string.substring(0, string.length() - 1));
        if (bl) {
            d2 *= (double)((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getWidth();
            do {
                return (int)Math.round((double)d2);
                break;
            } while (true);
        }
        d2 *= (double)((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getHeight();
        return (int)Math.round((double)d2);
    }

    @Override
    public String getBackgroundColor() {
        return this.getAttribute("backgroundColor");
    }

    @Override
    public String getFit() {
        String string = this.getAttribute("fit");
        if ("fill".equalsIgnoreCase(string)) {
            return "fill";
        }
        if ("meet".equalsIgnoreCase(string)) {
            return "meet";
        }
        if ("scroll".equalsIgnoreCase(string)) {
            return "scroll";
        }
        if ("slice".equalsIgnoreCase(string)) {
            return "slice";
        }
        return "hidden";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public int getHeight() {
        try {
            int n;
            int n2 = n = this.parseRegionLength(this.getAttribute("height"), false);
            if (n != 0) return n2;
            return ((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getHeight();
        }
        catch (NumberFormatException var3_5) {
            int n;
            int n3;
            n = ((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getHeight();
            try {
                n3 = this.parseRegionLength(this.getAttribute("top"), false);
                n -= n3;
            }
            catch (NumberFormatException var3_7) {}
            try {
                n3 = this.parseRegionLength(this.getAttribute("bottom"), false);
                n -= n3;
                return n;
            }
            catch (NumberFormatException var3_6) {
                return n;
            }
        }
    }

    @Override
    public String getId() {
        return this.getAttribute("id");
    }

    @Override
    public int getLeft() {
        try {
            int n = this.parseRegionLength(this.getAttribute("left"), true);
            return n;
        }
        catch (NumberFormatException var4_3) {
            try {
                int n = ((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getWidth();
                int n2 = this.parseRegionLength(this.getAttribute("right"), true);
                int n3 = this.parseRegionLength(this.getAttribute("width"), true);
                return n - n2 - n3;
            }
            catch (NumberFormatException var4_4) {
                return 0;
            }
        }
    }

    @Override
    public int getTop() {
        try {
            int n = this.parseRegionLength(this.getAttribute("top"), false);
            return n;
        }
        catch (NumberFormatException var4_3) {
            try {
                int n = ((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getHeight();
                int n2 = this.parseRegionLength(this.getAttribute("bottom"), false);
                int n3 = this.parseRegionLength(this.getAttribute("height"), false);
                return n - n2 - n3;
            }
            catch (NumberFormatException var4_4) {
                return 0;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public int getWidth() {
        try {
            int n;
            int n2 = n = this.parseRegionLength(this.getAttribute("width"), true);
            if (n != 0) return n2;
            return ((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getWidth();
        }
        catch (NumberFormatException var3_5) {
            int n;
            int n3;
            n = ((SMILDocument)this.getOwnerDocument()).getLayout().getRootLayout().getWidth();
            try {
                n3 = this.parseRegionLength(this.getAttribute("left"), true);
                n -= n3;
            }
            catch (NumberFormatException var3_7) {}
            try {
                n3 = this.parseRegionLength(this.getAttribute("right"), true);
                n -= n3;
                return n;
            }
            catch (NumberFormatException var3_6) {
                return n;
            }
        }
    }

    @Override
    public void setBackgroundColor(String string) throws DOMException {
        this.setAttribute("backgroundColor", string);
    }

    @Override
    public void setFit(String string) throws DOMException {
        if (string.equalsIgnoreCase("fill") || string.equalsIgnoreCase("meet") || string.equalsIgnoreCase("scroll") || string.equalsIgnoreCase("slice")) {
            this.setAttribute("fit", string.toLowerCase());
            return;
        }
        this.setAttribute("fit", "hidden");
    }

    @Override
    public void setHeight(int n) throws DOMException {
        this.setAttribute("height", String.valueOf((int)n) + "px");
    }

    @Override
    public void setId(String string) throws DOMException {
        this.setAttribute("id", string);
    }

    @Override
    public void setLeft(int n) throws DOMException {
        this.setAttribute("left", String.valueOf((int)n));
    }

    @Override
    public void setTop(int n) throws DOMException {
        this.setAttribute("top", String.valueOf((int)n));
    }

    @Override
    public void setWidth(int n) throws DOMException {
        this.setAttribute("width", String.valueOf((int)n) + "px");
    }

    public String toString() {
        return super.toString() + ": id=" + this.getId() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", left=" + this.getLeft() + ", top=" + this.getTop();
    }
}

