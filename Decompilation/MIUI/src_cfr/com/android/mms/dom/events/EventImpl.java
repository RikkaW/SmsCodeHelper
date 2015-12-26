/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.dom.events;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

public class EventImpl
implements Event {
    private boolean mCanBubble;
    private boolean mCancelable;
    private EventTarget mCurrentTarget;
    private short mEventPhase;
    private String mEventType;
    private boolean mInitialized;
    private boolean mPreventDefault;
    private int mSeekTo;
    private boolean mStopPropagation;
    private EventTarget mTarget;
    private final long mTimeStamp = System.currentTimeMillis();

    public boolean getBubbles() {
        return this.mCanBubble;
    }

    public int getSeekTo() {
        return this.mSeekTo;
    }

    @Override
    public String getType() {
        return this.mEventType;
    }

    @Override
    public void initEvent(String string, boolean bl, boolean bl2) {
        this.mEventType = string;
        this.mCanBubble = bl;
        this.mCancelable = bl2;
        this.mInitialized = true;
    }

    public void initEvent(String string, boolean bl, boolean bl2, int n) {
        this.mSeekTo = n;
        this.initEvent(string, bl, bl2);
    }

    boolean isInitialized() {
        return this.mInitialized;
    }

    boolean isPreventDefault() {
        return this.mPreventDefault;
    }

    boolean isPropogationStopped() {
        return this.mStopPropagation;
    }

    void setCurrentTarget(EventTarget eventTarget) {
        this.mCurrentTarget = eventTarget;
    }

    void setEventPhase(short s) {
        this.mEventPhase = s;
    }

    void setTarget(EventTarget eventTarget) {
        this.mTarget = eventTarget;
    }
}

