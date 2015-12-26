/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.nlp;

import com.xiaomi.common.StringIntInt;
import com.xiaomi.nlp.ParseType;
import com.xiaomi.nlp.PatternForNER;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseResult
implements Comparable<ParseResult> {
    private Double confidence;
    private int endPositionInSentence;
    public HashMap<String, StringIntInt> knowledge;
    private String parserName;
    private PatternForNER pattern;
    private ArrayList<String> segments;
    private ArrayList<String> segments_pattern;
    private List<String> segments_replace;
    private String source;
    private int startPositionInSentence;
    private String task;
    private ParseType type;

    public ParseResult(ParseType parseType, int n, int n2, PatternForNER patternForNER, String string2, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this.type = parseType;
        this.startPositionInSentence = n;
        this.endPositionInSentence = n2;
        this.pattern = patternForNER;
        this.source = string2;
        this.segments = arrayList;
        this.segments_pattern = arrayList2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(ParseResult parseResult) {
        if (this.startPositionInSentence < parseResult.startPositionInSentence) {
            return -1;
        }
        if (this.startPositionInSentence > parseResult.startPositionInSentence) {
            return 1;
        }
        if (this.endPositionInSentence < parseResult.endPositionInSentence) {
            return 1;
        }
        if (this.endPositionInSentence > parseResult.endPositionInSentence) return -1;
        return 0;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public int getEndPositionInSentence() {
        return this.endPositionInSentence;
    }

    public PatternForNER getPattern() {
        return this.pattern;
    }

    public ArrayList<String> getSegments() {
        return this.segments;
    }

    public ArrayList<String> getSegments_pattern() {
        return this.segments_pattern;
    }

    public List<String> getSegments_replace() {
        return this.segments_replace;
    }

    public String getSource() {
        return this.source;
    }

    public int getStartPositionInSentence() {
        return this.startPositionInSentence;
    }

    public String getTask() {
        return this.task;
    }

    public ParseType getType() {
        return this.type;
    }

    public void setConfidence(Double d2) {
        this.confidence = d2;
    }

    public void setEndPositionInSentence(int n) {
        this.endPositionInSentence = n;
    }

    public void setParserName(String string2) {
        this.parserName = string2;
    }

    public void setSegments_replace(List<String> list) {
        this.segments_replace = list;
    }

    public void setSource(String string2) {
        this.source = string2;
    }

    public void setStartPositionInSentence(int n) {
        this.startPositionInSentence = n;
    }

    public void setTask(String string2) {
        this.task = string2;
    }

    public void setType(ParseType parseType) {
        this.type = parseType;
    }

    public String toString() {
        return (Object)((Object)this.type) + "\t" + this.source + "\t" + this.pattern.leftProduction + " ::= " + this.pattern.pattern + "\t" + this.segments.toString();
    }
}

