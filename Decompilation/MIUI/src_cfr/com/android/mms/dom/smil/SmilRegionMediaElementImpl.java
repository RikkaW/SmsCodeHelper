/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import com.android.mms.dom.smil.SmilDocumentImpl;
import com.android.mms.dom.smil.SmilMediaElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRegionMediaElement;

public class SmilRegionMediaElementImpl
extends SmilMediaElementImpl
implements SMILRegionMediaElement {
    private SMILRegionElement mRegion;

    SmilRegionMediaElementImpl(SmilDocumentImpl smilDocumentImpl, String string) {
        super(smilDocumentImpl, string);
    }

    @Override
    public SMILRegionElement getRegion() {
        if (this.mRegion == null) {
            NodeList nodeList = ((SMILDocument)this.getOwnerDocument()).getLayout().getElementsByTagName("region");
            for (int i = 0; i < nodeList.getLength(); ++i) {
                SMILRegionElement sMILRegionElement = (SMILRegionElement)nodeList.item(i);
                if (!sMILRegionElement.getId().equals((Object)this.getAttribute("region"))) continue;
                this.mRegion = sMILRegionElement;
            }
        }
        return this.mRegion;
    }

    @Override
    public void setRegion(SMILRegionElement sMILRegionElement) {
        this.setAttribute("region", sMILRegionElement.getId());
        this.mRegion = sMILRegionElement;
    }
}

