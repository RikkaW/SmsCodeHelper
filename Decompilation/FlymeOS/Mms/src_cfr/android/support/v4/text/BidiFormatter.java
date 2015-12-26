/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package android.support.v4.text;

import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.text.TextUtilsCompat;
import java.util.Locale;

public final class BidiFormatter {
    private static final int DEFAULT_FLAGS = 2;
    private static final BidiFormatter DEFAULT_LTR_INSTANCE;
    private static final BidiFormatter DEFAULT_RTL_INSTANCE;
    private static TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = '\u202a';
    private static final char LRM = '\u200e';
    private static final String LRM_STRING;
    private static final char PDF = '\u202c';
    private static final char RLE = '\u202b';
    private static final char RLM = '\u200f';
    private static final String RLM_STRING;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    static {
        DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        LRM_STRING = Character.toString((char)'\u200e');
        RLM_STRING = Character.toString((char)'\u200f');
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }

    private BidiFormatter(boolean bl2, int n2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.mIsRtlContext = bl2;
        this.mFlags = n2;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
    }

    private static int getEntryDir(String string2) {
        return new DirectionalityEstimator(string2, false).getEntryDir();
    }

    private static int getExitDir(String string2) {
        return new DirectionalityEstimator(string2, false).getExitDir();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    public static BidiFormatter getInstance(boolean bl2) {
        return new Builder(bl2).build();
    }

    private static boolean isRtlLocale(Locale locale) {
        if (TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1) {
            return true;
        }
        return false;
    }

    private String markAfter(String string2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean bl2 = textDirectionHeuristicCompat.isRtl(string2, 0, string2.length());
        if (!this.mIsRtlContext && (bl2 || BidiFormatter.getExitDir(string2) == 1)) {
            return LRM_STRING;
        }
        if (this.mIsRtlContext && (!bl2 || BidiFormatter.getExitDir(string2) == -1)) {
            return RLM_STRING;
        }
        return "";
    }

    private String markBefore(String string2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean bl2 = textDirectionHeuristicCompat.isRtl(string2, 0, string2.length());
        if (!this.mIsRtlContext && (bl2 || BidiFormatter.getEntryDir(string2) == 1)) {
            return LRM_STRING;
        }
        if (this.mIsRtlContext && (!bl2 || BidiFormatter.getEntryDir(string2) == -1)) {
            return RLM_STRING;
        }
        return "";
    }

    public boolean getStereoReset() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isRtl(String string2) {
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(string2, 0, string2.length());
    }

    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }

    public String unicodeWrap(String string2) {
        return this.unicodeWrap(string2, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String string2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return this.unicodeWrap(string2, textDirectionHeuristicCompat, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String unicodeWrap(String string2, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean bl2) {
        boolean bl3 = textDirectionHeuristicCompat.isRtl(string2, 0, string2.length());
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getStereoReset() && bl2) {
            textDirectionHeuristicCompat = bl3 ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR;
            stringBuilder.append(this.markBefore(string2, textDirectionHeuristicCompat));
        }
        if (bl3 != this.mIsRtlContext) {
            char c2 = bl3 ? '\u202b' : '\u202a';
            stringBuilder.append(c2);
            stringBuilder.append(string2);
            stringBuilder.append('\u202c');
        } else {
            stringBuilder.append(string2);
        }
        if (bl2) {
            textDirectionHeuristicCompat = bl3 ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR;
            stringBuilder.append(this.markAfter(string2, textDirectionHeuristicCompat));
        }
        return stringBuilder.toString();
    }

    public String unicodeWrap(String string2, boolean bl2) {
        return this.unicodeWrap(string2, this.mDefaultTextDirectionHeuristicCompat, bl2);
    }

    public static final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            this.initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        public Builder(Locale locale) {
            this.initialize(BidiFormatter.isRtlLocale(locale));
        }

        public Builder(boolean bl2) {
            this.initialize(bl2);
        }

        private static BidiFormatter getDefaultInstanceFromContext(boolean bl2) {
            if (bl2) {
                return DEFAULT_RTL_INSTANCE;
            }
            return DEFAULT_LTR_INSTANCE;
        }

        private void initialize(boolean bl2) {
            this.mIsRtlContext = bl2;
            this.mTextDirectionHeuristicCompat = DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public BidiFormatter build() {
            if (this.mFlags == 2 && this.mTextDirectionHeuristicCompat == DEFAULT_TEXT_DIRECTION_HEURISTIC) {
                return Builder.getDefaultInstanceFromContext(this.mIsRtlContext);
            }
            return new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
            this.mTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
            return this;
        }

        public Builder stereoReset(boolean bl2) {
            if (bl2) {
                this.mFlags |= 2;
                return this;
            }
            this.mFlags &= -3;
            return this;
        }
    }

    static class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = new byte[1792];
        private static final int DIR_TYPE_CACHE_SIZE = 1792;
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final String text;

        static {
            for (int i2 = 0; i2 < 1792; ++i2) {
                DirectionalityEstimator.DIR_TYPE_CACHE[i2] = Character.getDirectionality((int)i2);
            }
        }

        DirectionalityEstimator(String string2, boolean bl2) {
            this.text = string2;
            this.isHtml = bl2;
            this.length = string2.length();
        }

        private static byte getCachedDirectionality(char c2) {
            if (c2 < '\u0700') {
                return DIR_TYPE_CACHE[c2];
            }
            return Character.getDirectionality((char)c2);
        }

        private byte skipEntityBackward() {
            int n2 = this.charIndex;
            while (this.charIndex > 0) {
                int n3;
                String string2 = this.text;
                this.charIndex = n3 = this.charIndex - 1;
                this.lastChar = string2.charAt(n3);
                if (this.lastChar == '&') {
                    return 12;
                }
                if (this.lastChar != ';') continue;
            }
            this.charIndex = n2;
            this.lastChar = 59;
            return 13;
        }

        private byte skipEntityForward() {
            while (this.charIndex < this.length) {
                char c2;
                String string2 = this.text;
                int n2 = this.charIndex;
                this.charIndex = n2 + 1;
                this.lastChar = c2 = string2.charAt(n2);
                if (c2 != ';') continue;
            }
            return 12;
        }

        /*
         * Enabled aggressive block sorting
         */
        private byte skipTagBackward() {
            int n2 = this.charIndex;
            block0 : while (this.charIndex > 0) {
                int n3;
                String string2 = this.text;
                this.charIndex = n3 = this.charIndex - 1;
                this.lastChar = string2.charAt(n3);
                if (this.lastChar == '<') {
                    return 12;
                }
                if (this.lastChar == '>') break;
                if (this.lastChar != '\"' && this.lastChar != '\'') continue;
                n3 = this.lastChar;
                while (this.charIndex > 0) {
                    char c2;
                    int n4;
                    string2 = this.text;
                    this.charIndex = n4 = this.charIndex - 1;
                    this.lastChar = c2 = string2.charAt(n4);
                    if (c2 == n3) continue block0;
                }
            }
            this.charIndex = n2;
            this.lastChar = 62;
            return 13;
        }

        private byte skipTagForward() {
            int n2 = this.charIndex;
            block0 : while (this.charIndex < this.length) {
                String string2 = this.text;
                int n3 = this.charIndex;
                this.charIndex = n3 + 1;
                this.lastChar = string2.charAt(n3);
                if (this.lastChar == '>') {
                    return 12;
                }
                if (this.lastChar != '\"' && this.lastChar != '\'') continue;
                n3 = this.lastChar;
                while (this.charIndex < this.length) {
                    char c2;
                    string2 = this.text;
                    int n4 = this.charIndex;
                    this.charIndex = n4 + 1;
                    this.lastChar = c2 = string2.charAt(n4);
                    if (c2 == n3) continue block0;
                }
            }
            this.charIndex = n2;
            this.lastChar = 60;
            return 13;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        byte dirTypeBackward() {
            byte by2;
            this.lastChar = this.text.charAt(this.charIndex - 1);
            if (Character.isLowSurrogate((char)this.lastChar)) {
                int n2 = Character.codePointBefore((CharSequence)this.text, (int)this.charIndex);
                this.charIndex -= Character.charCount((int)n2);
                return Character.getDirectionality((int)n2);
            }
            --this.charIndex;
            byte by3 = by2 = DirectionalityEstimator.getCachedDirectionality(this.lastChar);
            if (!this.isHtml) return by3;
            if (this.lastChar == '>') {
                return this.skipTagBackward();
            }
            by3 = by2;
            if (this.lastChar != ';') return by3;
            return this.skipEntityBackward();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        byte dirTypeForward() {
            byte by2;
            this.lastChar = this.text.charAt(this.charIndex);
            if (Character.isHighSurrogate((char)this.lastChar)) {
                int n2 = Character.codePointAt((CharSequence)this.text, (int)this.charIndex);
                this.charIndex += Character.charCount((int)n2);
                return Character.getDirectionality((int)n2);
            }
            ++this.charIndex;
            byte by3 = by2 = DirectionalityEstimator.getCachedDirectionality(this.lastChar);
            if (!this.isHtml) return by3;
            if (this.lastChar == '<') {
                return this.skipTagForward();
            }
            by3 = by2;
            if (this.lastChar != '&') return by3;
            return this.skipEntityForward();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        int getEntryDir() {
            int n2;
            int n3;
            int n4;
            block21 : {
                this.charIndex = 0;
                n4 = 0;
                n2 = 0;
                n3 = 0;
                block13 : while (this.charIndex < this.length) {
                    if (n4 == 0) {
                        switch (this.dirTypeForward()) {
                            case 9: {
                                continue block13;
                            }
                            default: {
                                n4 = n3;
                                continue block13;
                            }
                            case 14: 
                            case 15: {
                                ++n3;
                                n2 = -1;
                                continue block13;
                            }
                            case 16: 
                            case 17: {
                                ++n3;
                                n2 = 1;
                                continue block13;
                            }
                            case 18: {
                                --n3;
                                n2 = 0;
                                continue block13;
                            }
                            case 0: {
                                if (n3 == 0) {
                                    return -1;
                                }
                                n4 = n3;
                                continue block13;
                            }
                            case 1: 
                            case 2: 
                        }
                        if (n3 == 0) {
                            return 1;
                        }
                        n4 = n3;
                        continue;
                    }
                    break block21;
                }
                return 0;
            }
            if (n4 == 0) {
                return 0;
            }
            if (n2 != 0) {
                return n2;
            }
            block14 : while (this.charIndex > 0) {
                switch (this.dirTypeBackward()) {
                    default: {
                        continue block14;
                    }
                    case 14: 
                    case 15: {
                        if (n4 == n3) return -1;
                        --n3;
                        continue block14;
                    }
                    case 16: 
                    case 17: {
                        if (n4 == n3) {
                            return 1;
                        }
                        --n3;
                        continue block14;
                    }
                    case 18: 
                }
                ++n3;
            }
            return 0;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        int getExitDir() {
            int n2 = 0;
            this.charIndex = this.length;
            int n3 = 0;
            int n4 = 0;
            block8 : do {
                int n5 = n2;
                if (this.charIndex <= 0) return n5;
                switch (this.dirTypeBackward()) {
                    case 9: {
                        continue block8;
                    }
                    default: {
                        if (n3 != 0) continue block8;
                        n3 = n4;
                        continue block8;
                    }
                    case 0: {
                        if (n4 == 0) {
                            return -1;
                        }
                        if (n3 != 0) continue block8;
                        n3 = n4;
                        continue block8;
                    }
                    case 14: 
                    case 15: {
                        if (n3 == n4) {
                            return -1;
                        }
                        --n4;
                        continue block8;
                    }
                    case 1: 
                    case 2: {
                        if (n4 == 0) {
                            return 1;
                        }
                        if (n3 != 0) continue block8;
                        n3 = n4;
                        continue block8;
                    }
                    case 16: 
                    case 17: {
                        if (n3 == n4) {
                            return 1;
                        }
                        --n4;
                        continue block8;
                    }
                    case 18: 
                }
                ++n4;
            } while (true);
        }
    }

}

