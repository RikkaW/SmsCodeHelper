/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.android.mms.dom.events;

import android.util.Log;
import com.android.mms.dom.events.EventImpl;
import java.util.ArrayList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class EventTargetImpl
implements EventTarget {
    private ArrayList<EventListenerEntry> mListenerEntries;
    private EventTarget mNodeTarget;

    public EventTargetImpl(EventTarget eventTarget) {
        this.mNodeTarget = eventTarget;
    }

    @Override
    public void addEventListener(String string, EventListener eventListener, boolean bl) {
        if (string == null || string.equals((Object)"") || eventListener == null) {
            return;
        }
        this.removeEventListener(string, eventListener, bl);
        if (this.mListenerEntries == null) {
            this.mListenerEntries = new ArrayList();
        }
        this.mListenerEntries.add((Object)new EventListenerEntry(string, eventListener, bl));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean dispatchEvent(Event event) throws EventException {
        if (!(event = (EventImpl)event).isInitialized()) {
            throw new EventException(0, "Event not initialized");
        }
        if (event.getType() == null || event.getType().equals((Object)"")) {
            throw new EventException(0, "Unspecified even type");
        }
        event.setTarget(this.mNodeTarget);
        event.setEventPhase(2);
        event.setCurrentTarget(this.mNodeTarget);
        if (!event.isPropogationStopped() && this.mListenerEntries != null) {
            for (int i = 0; i < this.mListenerEntries.size(); ++i) {
                EventListenerEntry eventListenerEntry = (EventListenerEntry)this.mListenerEntries.get(i);
                if (eventListenerEntry.mUseCapture || !eventListenerEntry.mType.equals((Object)event.getType())) continue;
                try {
                    eventListenerEntry.mListener.handleEvent(event);
                    continue;
                }
                catch (Exception var3_4) {
                    Log.w((String)"EventTargetImpl", (String)"Catched EventListener exception", (Throwable)var3_4);
                }
            }
        }
        if (event.getBubbles()) {
            // empty if block
        }
        return event.isPreventDefault();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void removeEventListener(String string, EventListener eventListener, boolean bl) {
        if (this.mListenerEntries == null) {
            return;
        }
        int n = 0;
        while (n < this.mListenerEntries.size()) {
            EventListenerEntry eventListenerEntry = (EventListenerEntry)this.mListenerEntries.get(n);
            if (eventListenerEntry.mUseCapture == bl && eventListenerEntry.mListener == eventListener && eventListenerEntry.mType.equals((Object)string)) {
                this.mListenerEntries.remove(n);
                return;
            }
            ++n;
        }
    }

    static class EventListenerEntry {
        final EventListener mListener;
        final String mType;
        final boolean mUseCapture;

        EventListenerEntry(String string, EventListener eventListener, boolean bl) {
            this.mType = string;
            this.mListener = eventListener;
            this.mUseCapture = bl;
        }
    }

}

