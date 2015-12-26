/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Vector
 */
package com.google.android.mms.pdu;

import com.google.android.mms.pdu.PduPart;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PduBody {
    private Map<String, PduPart> mPartMapByContentId = new HashMap();
    private Map<String, PduPart> mPartMapByContentLocation = new HashMap();
    private Map<String, PduPart> mPartMapByFileName = new HashMap();
    private Map<String, PduPart> mPartMapByName = new HashMap();
    private Vector<PduPart> mParts = new Vector();

    private void putPartToMaps(PduPart pduPart) {
        Object object = pduPart.getContentId();
        if (object != null) {
            this.mPartMapByContentId.put(new String((byte[])object), pduPart);
        }
        if ((object = pduPart.getContentLocation()) != null) {
            object = new String((byte[])object);
            this.mPartMapByContentLocation.put((String)object, pduPart);
        }
        if ((object = pduPart.getName()) != null) {
            object = new String((byte[])object);
            this.mPartMapByName.put((String)object, pduPart);
        }
        if ((object = (Object)pduPart.getFilename()) != null) {
            object = new String((byte[])object);
            this.mPartMapByFileName.put((String)object, pduPart);
        }
    }

    public void addPart(int n, PduPart pduPart) {
        if (pduPart == null) {
            throw new NullPointerException();
        }
        this.putPartToMaps(pduPart);
        this.mParts.add(n, (Object)pduPart);
    }

    public boolean addPart(PduPart pduPart) {
        if (pduPart == null) {
            throw new NullPointerException();
        }
        this.putPartToMaps(pduPart);
        return this.mParts.add((Object)pduPart);
    }

    public PduPart getPart(int n) {
        return (PduPart)this.mParts.get(n);
    }

    public PduPart getPartByContentId(String string) {
        return this.mPartMapByContentId.get(string);
    }

    public PduPart getPartByContentLocation(String string) {
        return this.mPartMapByContentLocation.get(string);
    }

    public PduPart getPartByFileName(String string) {
        return this.mPartMapByFileName.get(string);
    }

    public PduPart getPartByName(String string) {
        return this.mPartMapByName.get(string);
    }

    public int getPartIndex(PduPart pduPart) {
        return this.mParts.indexOf((Object)pduPart);
    }

    public int getPartsNum() {
        return this.mParts.size();
    }

    public void removeAll() {
        this.mParts.clear();
    }

    public PduPart removePart(int n) {
        return (PduPart)this.mParts.remove(n);
    }
}

