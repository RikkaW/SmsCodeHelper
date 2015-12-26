/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.text.TextUtils
 *  java.io.ByteArrayOutputStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 *  java.util.HashMap
 */
package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduContentTypes;
import com.google.android.mms.pdu.PduHeaders;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;

public class PduComposer {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int END_STRING_FLAG = 0;
    private static final int LENGTH_QUOTE = 31;
    private static final int LONG_INTEGER_LENGTH_MAX = 8;
    private static final int PDU_COMPOSER_BLOCK_SIZE = 1024;
    private static final int PDU_COMPOSE_CONTENT_ERROR = 1;
    private static final int PDU_COMPOSE_FIELD_NOT_SET = 2;
    private static final int PDU_COMPOSE_FIELD_NOT_SUPPORTED = 3;
    private static final int PDU_COMPOSE_SUCCESS = 0;
    private static final int PDU_EMAIL_ADDRESS_TYPE = 2;
    private static final int PDU_IPV4_ADDRESS_TYPE = 3;
    private static final int PDU_IPV6_ADDRESS_TYPE = 4;
    private static final int PDU_PHONE_NUMBER_ADDRESS_TYPE = 1;
    private static final int PDU_UNKNOWN_ADDRESS_TYPE = 5;
    private static final int QUOTED_STRING_FLAG = 34;
    static final String REGEXP_EMAIL_ADDRESS_TYPE = "[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}";
    static final String REGEXP_IPV4_ADDRESS_TYPE = "[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}";
    static final String REGEXP_IPV6_ADDRESS_TYPE = "[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}";
    static final String REGEXP_PHONE_NUMBER_ADDRESS_TYPE = "\\+?[0-9|\\.|\\-]+";
    private static final int SHORT_INTEGER_MAX = 127;
    static final String STRING_IPV4_ADDRESS_TYPE = "/TYPE=IPV4";
    static final String STRING_IPV6_ADDRESS_TYPE = "/TYPE=IPV6";
    static final String STRING_PHONE_NUMBER_ADDRESS_TYPE = "/TYPE=PLMN";
    private static final int TEXT_MAX = 127;
    private static HashMap<String, Integer> mContentTypeMap;
    protected ByteArrayOutputStream mMessage = null;
    private GenericPdu mPdu = null;
    private PduHeaders mPduHeader = null;
    protected int mPosition = 0;
    private final ContentResolver mResolver;
    private BufferStack mStack = null;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !PduComposer.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
        mContentTypeMap = null;
        mContentTypeMap = new HashMap();
        int n = 0;
        while (n < PduContentTypes.contentTypes.length) {
            mContentTypeMap.put((Object)PduContentTypes.contentTypes[n], (Object)n);
            ++n;
        }
    }

    public PduComposer(Context context, GenericPdu genericPdu) {
        this.mPdu = genericPdu;
        this.mResolver = context.getContentResolver();
        this.mPduHeader = genericPdu.getPduHeaders();
        this.mStack = new BufferStack();
        this.mMessage = new ByteArrayOutputStream();
        this.mPosition = 0;
    }

    static /* synthetic */ BufferStack access$100(PduComposer pduComposer) {
        return pduComposer.mStack;
    }

    private EncodedStringValue appendAddressType(EncodedStringValue encodedStringValue) {
        block9 : {
            int n;
            block8 : {
                try {
                    n = PduComposer.checkAddressType(encodedStringValue.getString());
                    encodedStringValue = EncodedStringValue.copy(encodedStringValue);
                    if (1 != n) break block8;
                }
                catch (NullPointerException var1_2) {
                    return null;
                }
                encodedStringValue.appendTextString("/TYPE=PLMN".getBytes());
                break block9;
            }
            if (3 == n) {
                encodedStringValue.appendTextString("/TYPE=IPV4".getBytes());
            } else if (4 == n) {
                encodedStringValue.appendTextString("/TYPE=IPV6".getBytes());
            }
        }
        return encodedStringValue;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int appendHeader(int n) {
        switch (n) {
            default: {
                return 3;
            }
            case 141: {
                this.appendOctet(n);
                n = this.mPduHeader.getOctet(n);
                if (n == 0) {
                    this.appendShortInteger(18);
                    do {
                        return 0;
                        break;
                    } while (true);
                }
                this.appendShortInteger(n);
                return 0;
            }
            case 139: 
            case 152: {
                byte[] arrby = this.mPduHeader.getTextString(n);
                if (arrby == null) {
                    return 2;
                }
                this.appendOctet(n);
                this.appendTextString(arrby);
                return 0;
            }
            case 129: 
            case 130: 
            case 151: {
                EncodedStringValue[] arrencodedStringValue = this.mPduHeader.getEncodedStringValues(n);
                if (arrencodedStringValue == null) {
                    return 2;
                }
                int n2 = 0;
                while (n2 < arrencodedStringValue.length) {
                    EncodedStringValue encodedStringValue = this.appendAddressType(arrencodedStringValue[n2]);
                    if (encodedStringValue == null) {
                        return 1;
                    }
                    this.appendOctet(n);
                    this.appendEncodedString(encodedStringValue);
                    ++n2;
                }
                return 0;
            }
            case 137: {
                this.appendOctet(n);
                EncodedStringValue encodedStringValue = this.mPduHeader.getEncodedStringValue(n);
                if (encodedStringValue == null || TextUtils.isEmpty((CharSequence)encodedStringValue.getString()) || new String(encodedStringValue.getTextString()).equals((Object)"insert-address-token")) {
                    this.append(1);
                    this.append(129);
                    return 0;
                }
                this.mStack.newbuf();
                PositionMarker positionMarker = this.mStack.mark();
                this.append(128);
                encodedStringValue = this.appendAddressType(encodedStringValue);
                if (encodedStringValue == null) {
                    return 1;
                }
                this.appendEncodedString(encodedStringValue);
                n = positionMarker.getLength();
                this.mStack.pop();
                this.appendValueLength(n);
                this.mStack.copy();
                return 0;
            }
            case 134: 
            case 143: 
            case 144: 
            case 145: 
            case 149: 
            case 153: 
            case 155: {
                int n3 = this.mPduHeader.getOctet(n);
                if (n3 == 0) {
                    return 2;
                }
                this.appendOctet(n);
                this.appendOctet(n3);
                return 0;
            }
            case 133: {
                long l = this.mPduHeader.getLongInteger(n);
                if (-1 == l) {
                    return 2;
                }
                this.appendOctet(n);
                this.appendDateValue(l);
                return 0;
            }
            case 150: 
            case 154: {
                EncodedStringValue encodedStringValue = this.mPduHeader.getEncodedStringValue(n);
                if (encodedStringValue == null) {
                    return 2;
                }
                this.appendOctet(n);
                this.appendEncodedString(encodedStringValue);
                return 0;
            }
            case 138: {
                byte[] arrby = this.mPduHeader.getTextString(n);
                if (arrby == null) {
                    return 2;
                }
                this.appendOctet(n);
                if (Arrays.equals((byte[])arrby, (byte[])"advertisement".getBytes())) {
                    this.appendOctet(129);
                    return 0;
                }
                if (Arrays.equals((byte[])arrby, (byte[])"auto".getBytes())) {
                    this.appendOctet(131);
                    return 0;
                }
                if (Arrays.equals((byte[])arrby, (byte[])"personal".getBytes())) {
                    this.appendOctet(128);
                    return 0;
                }
                if (Arrays.equals((byte[])arrby, (byte[])"informational".getBytes())) {
                    this.appendOctet(130);
                    return 0;
                }
                this.appendTextString(arrby);
                return 0;
            }
            case 136: 
        }
        long l = this.mPduHeader.getLongInteger(n);
        if (-1 == l) {
            return 2;
        }
        this.appendOctet(n);
        this.mStack.newbuf();
        PositionMarker positionMarker = this.mStack.mark();
        this.append(129);
        this.appendLongInteger(l);
        n = positionMarker.getLength();
        this.mStack.pop();
        this.appendValueLength(n);
        this.mStack.copy();
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected static int checkAddressType(String string) {
        if (string == null) {
            return 5;
        }
        if (string.matches("[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}")) {
            return 3;
        }
        if (string.matches("\\+?[0-9|\\.|\\-]+")) {
            return 1;
        }
        if (string.matches("[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}")) {
            return 2;
        }
        if (!string.matches("[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}")) return 5;
        return 4;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int makeAckInd() {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        this.appendOctet(140);
        this.appendOctet(133);
        if (this.appendHeader(152) != 0 || this.appendHeader(141) != 0) {
            return 1;
        }
        this.appendHeader(145);
        return 0;
    }

    /*
     * Exception decompiling
     */
    private int makeMessageBody(int var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    /*
     * Enabled aggressive block sorting
     */
    private int makeNotifyResp() {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        this.appendOctet(140);
        this.appendOctet(131);
        if (this.appendHeader(152) != 0 || this.appendHeader(141) != 0 || this.appendHeader(149) != 0) {
            return 1;
        }
        this.appendHeader(145);
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int makeReadRecInd() {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        this.appendOctet(140);
        this.appendOctet(135);
        if (this.appendHeader(141) != 0) {
            return 1;
        }
        if (this.appendHeader(139) != 0) return 1;
        if (this.appendHeader(151) != 0) return 1;
        if (this.appendHeader(137) != 0) return 1;
        this.appendHeader(133);
        if (this.appendHeader(155) != 0) return 1;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int makeSendRetrievePdu(int n) {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        this.appendOctet(140);
        this.appendOctet(n);
        this.appendOctet(152);
        byte[] arrby = this.mPduHeader.getTextString(152);
        if (arrby == null) {
            throw new IllegalArgumentException("Transaction-ID is null.");
        }
        this.appendTextString(arrby);
        if (this.appendHeader(141) != 0) {
            return 1;
        }
        this.appendHeader(133);
        if (this.appendHeader(137) != 0) return 1;
        boolean bl = false;
        if (this.appendHeader(151) != 1) {
            bl = true;
        }
        if (this.appendHeader(130) != 1) {
            bl = true;
        }
        if (this.appendHeader(129) != 1) {
            bl = true;
            if (!bl) return 1;
        }
        this.appendHeader(150);
        this.appendHeader(138);
        this.appendHeader(136);
        this.appendHeader(143);
        this.appendHeader(134);
        this.appendHeader(144);
        if (n == 132) {
            this.appendHeader(153);
            this.appendHeader(154);
        }
        this.appendOctet(132);
        return this.makeMessageBody(n);
    }

    protected void append(int n) {
        this.mMessage.write(n);
        ++this.mPosition;
    }

    protected void appendDateValue(long l) {
        this.appendLongInteger(l);
    }

    protected void appendEncodedString(EncodedStringValue encodedStringValue) {
        if (!$assertionsDisabled && encodedStringValue == null) {
            throw new AssertionError();
        }
        int n = encodedStringValue.getCharacterSet();
        if ((encodedStringValue = (EncodedStringValue)encodedStringValue.getTextString()) == null) {
            return;
        }
        this.mStack.newbuf();
        PositionMarker positionMarker = this.mStack.mark();
        this.appendShortInteger(n);
        this.appendTextString((byte[])encodedStringValue);
        n = positionMarker.getLength();
        this.mStack.pop();
        this.appendValueLength(n);
        this.mStack.copy();
    }

    protected void appendLongInteger(long l) {
        int n;
        long l2 = l;
        for (n = 0; l2 != 0 && n < 8; l2 >>>= 8, ++n) {
        }
        this.appendShortLength(n);
        int n2 = (n - 1) * 8;
        for (int i = 0; i < n; ++i) {
            this.append((int)(l >>> n2 & 255));
            n2 -= 8;
        }
    }

    protected void appendOctet(int n) {
        this.append(n);
    }

    protected void appendQuotedString(String string) {
        this.appendQuotedString(string.getBytes());
    }

    protected void appendQuotedString(byte[] arrby) {
        this.append(34);
        this.arraycopy(arrby, 0, arrby.length);
        this.append(0);
    }

    protected void appendShortInteger(int n) {
        this.append((n | 128) & 255);
    }

    protected void appendShortLength(int n) {
        this.append(n);
    }

    protected void appendTextString(String string) {
        this.appendTextString(string.getBytes());
    }

    protected void appendTextString(byte[] arrby) {
        if ((arrby[0] & 255) > 127) {
            this.append(127);
        }
        this.arraycopy(arrby, 0, arrby.length);
        this.append(0);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void appendUintvarInteger(long var1_1) {
        var5_2 = 127;
        var3_3 = 0;
        do {
            var4_4 = var3_3;
            if (var3_3 >= 5) ** GOTO lbl8
            if (var1_1 < var5_2) {
                var4_4 = var3_3;
lbl8: // 2 sources:
                do {
                    if (var4_4 <= 0) {
                        this.append((int)(var1_1 & 127));
                        return;
                    }
                    this.append((int)((128 | var1_1 >>> var4_4 * 7 & 127) & 255));
                    --var4_4;
                } while (true);
            }
            var5_2 = var5_2 << 7 | 127;
            ++var3_3;
        } while (true);
    }

    protected void appendValueLength(long l) {
        if (l < 31) {
            this.appendShortLength((int)l);
            return;
        }
        this.append(31);
        this.appendUintvarInteger(l);
    }

    protected void arraycopy(byte[] arrby, int n, int n2) {
        this.mMessage.write(arrby, n, n2);
        this.mPosition += n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public byte[] make() {
        int n = this.mPdu.getMessageType();
        switch (n) {
            case 128: 
            case 132: {
                if (this.makeSendRetrievePdu(n) == 0) return this.mMessage.toByteArray();
            }
            {
                default: {
                    return null;
                }
            }
            case 131: {
                if (this.makeNotifyResp() == 0) return this.mMessage.toByteArray();
                return null;
            }
            case 133: {
                if (this.makeAckInd() == 0) return this.mMessage.toByteArray();
                return null;
            }
            case 135: 
        }
        if (this.makeReadRecInd() != 0) return null;
        return this.mMessage.toByteArray();
    }

    private class BufferStack {
        private LengthRecordNode stack;
        int stackSize;
        private LengthRecordNode toCopy;

        private BufferStack() {
            this.stack = null;
            this.toCopy = null;
            this.stackSize = 0;
        }

        void copy() {
            PduComposer.this.arraycopy(this.toCopy.currentMessage.toByteArray(), 0, this.toCopy.currentPosition);
            this.toCopy = null;
        }

        PositionMarker mark() {
            PositionMarker positionMarker = new PositionMarker();
            positionMarker.c_pos = PduComposer.this.mPosition;
            positionMarker.currentStackSize = this.stackSize;
            return positionMarker;
        }

        void newbuf() {
            if (this.toCopy != null) {
                throw new RuntimeException("BUG: Invalid newbuf() before copy()");
            }
            LengthRecordNode lengthRecordNode = new LengthRecordNode();
            lengthRecordNode.currentMessage = PduComposer.this.mMessage;
            lengthRecordNode.currentPosition = PduComposer.this.mPosition;
            lengthRecordNode.next = this.stack;
            this.stack = lengthRecordNode;
            ++this.stackSize;
            PduComposer.this.mMessage = new ByteArrayOutputStream();
            PduComposer.this.mPosition = 0;
        }

        void pop() {
            ByteArrayOutputStream byteArrayOutputStream = PduComposer.this.mMessage;
            int n = PduComposer.this.mPosition;
            PduComposer.this.mMessage = this.stack.currentMessage;
            PduComposer.this.mPosition = this.stack.currentPosition;
            this.toCopy = this.stack;
            this.stack = this.stack.next;
            --this.stackSize;
            this.toCopy.currentMessage = byteArrayOutputStream;
            this.toCopy.currentPosition = n;
        }
    }

    private static class LengthRecordNode {
        ByteArrayOutputStream currentMessage = null;
        public int currentPosition = 0;
        public LengthRecordNode next = null;

        private LengthRecordNode() {
        }
    }

    private class PositionMarker {
        private int c_pos;
        private int currentStackSize;

        private PositionMarker() {
        }

        int getLength() {
            if (this.currentStackSize != PduComposer.access$100((PduComposer)PduComposer.this).stackSize) {
                throw new RuntimeException("BUG: Invalid call to getLength()");
            }
            return PduComposer.this.mPosition - this.c_pos;
        }
    }

}

