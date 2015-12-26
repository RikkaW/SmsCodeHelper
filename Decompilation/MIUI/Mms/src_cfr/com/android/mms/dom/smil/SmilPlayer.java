/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Enum
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashSet
 */
package com.android.mms.dom.smil;

import android.util.Log;
import com.android.mms.dom.smil.SmilParElementImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.smil.ElementParallelTimeContainer;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public class SmilPlayer
implements Runnable {
    private static SmilPlayer sPlayer;
    private static final Comparator<TimelineEntry> sTimelineEntryComparator;
    private SmilPlayerAction mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
    private ArrayList<ElementTime> mActiveElements;
    private ArrayList<TimelineEntry> mAllEntries;
    private int mCurrentElement;
    private int mCurrentSlide;
    private long mCurrentTime;
    private Event mMediaTimeUpdatedEvent;
    private Thread mPlayerThread;
    private ElementTime mRoot;
    private SmilPlayerState mState = SmilPlayerState.INITIALIZED;

    static {
        sTimelineEntryComparator = new Comparator<TimelineEntry>(){

            public int compare(TimelineEntry timelineEntry, TimelineEntry timelineEntry2) {
                return Double.compare((double)timelineEntry.getOffsetTime(), (double)timelineEntry2.getOffsetTime());
            }
        };
    }

    private SmilPlayer() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void actionEntry(TimelineEntry timelineEntry) {
        synchronized (this) {
            int n = timelineEntry.getAction();
            switch (n) {
                case 0: {
                    timelineEntry.getElement().beginElement();
                    this.mActiveElements.add((Object)timelineEntry.getElement());
                }
                default: {
                    break;
                }
                case 1: {
                    timelineEntry.getElement().endElement();
                    this.mActiveElements.remove((Object)timelineEntry.getElement());
                }
            }
            return;
        }
    }

    private TimelineEntry actionNext() {
        synchronized (this) {
            this.stopCurrentSlide();
            TimelineEntry timelineEntry = this.loadNextSlide();
            return timelineEntry;
        }
    }

    private void actionPause() {
        synchronized (this) {
            this.pauseActiveElements();
            this.mState = SmilPlayerState.PAUSED;
            this.mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
            return;
        }
    }

    private TimelineEntry actionPrev() {
        synchronized (this) {
            this.stopCurrentSlide();
            TimelineEntry timelineEntry = this.loadPrevSlide();
            return timelineEntry;
        }
    }

    private void actionReload() {
        synchronized (this) {
            this.reloadActiveSlide();
            this.mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
            return;
        }
    }

    private void actionStop() {
        synchronized (this) {
            this.endActiveElements();
            this.mCurrentTime = 0;
            this.mCurrentElement = 0;
            this.mCurrentSlide = 0;
            this.mState = SmilPlayerState.STOPPED;
            this.mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
            return;
        }
    }

    private void beginSmilDocument() {
        synchronized (this) {
            this.actionEntry((TimelineEntry)this.mAllEntries.get(0));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void endActiveElements() {
        synchronized (this) {
            int n = this.mActiveElements.size() - 1;
            while (n >= 0) {
                ((ElementTime)this.mActiveElements.get(n)).endElement();
                --n;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private double getOffsetTime(ElementTime elementTime) {
        synchronized (this) {
            double d2;
            block4 : {
                int n = this.mCurrentSlide;
                while (n < this.mCurrentElement) {
                    TimelineEntry timelineEntry = (TimelineEntry)this.mAllEntries.get(n);
                    if (elementTime.equals((Object)timelineEntry.getElement())) {
                        d2 = timelineEntry.getOffsetTime();
                        d2 *= 1000.0;
                        break block4;
                    }
                    ++n;
                }
                return -1.0;
            }
            return d2;
        }
    }

    private static ArrayList<TimelineEntry> getParTimeline(ElementParallelTimeContainer object, double d2, double d3) {
        int n;
        double d4;
        ArrayList arrayList = new ArrayList();
        double d5 = object.getBegin().item(0).getResolvedOffset() + d2;
        if (d5 > d3) {
            return arrayList;
        }
        arrayList.add((Object)new TimelineEntry(d5, (ElementTime)object, 0));
        d5 = d4 = object.getEnd().item(0).getResolvedOffset() + d2;
        if (d4 > d3) {
            d5 = d3;
        }
        TimelineEntry timelineEntry = new TimelineEntry(d5, (ElementTime)object, 1);
        NodeList nodeList = object.getTimeChildren();
        for (n = 0; n < nodeList.getLength(); ++n) {
            arrayList.addAll(SmilPlayer.getTimeline((ElementTime)((Object)nodeList.item(n)), d2, d5));
        }
        Collections.sort((List)arrayList, sTimelineEntryComparator);
        object = object.getActiveChildrenAt((float)(d5 - d2) * 1000.0f);
        for (n = 0; n < object.getLength(); ++n) {
            arrayList.add((Object)new TimelineEntry(d5, (ElementTime)((Object)object.item(n)), 1));
        }
        arrayList.add((Object)timelineEntry);
        return arrayList;
    }

    public static SmilPlayer getPlayer() {
        if (sPlayer == null) {
            sPlayer = new SmilPlayer();
        }
        return sPlayer;
    }

    private static ArrayList<TimelineEntry> getSeqTimeline(ElementSequentialTimeContainer object, double d2, double d3) {
        int n;
        double d4;
        ArrayList arrayList = new ArrayList();
        double d5 = object.getBegin().item(0).getResolvedOffset() + d2;
        if (d5 > d3) {
            return arrayList;
        }
        arrayList.add((Object)new TimelineEntry(d5, (ElementTime)object, 0));
        d5 = d4 = object.getEnd().item(0).getResolvedOffset() + d2;
        if (d4 > d3) {
            d5 = d3;
        }
        TimelineEntry timelineEntry = new TimelineEntry(d5, (ElementTime)object, 1);
        NodeList nodeList = object.getTimeChildren();
        d3 = d2;
        for (n = 0; n < nodeList.getLength(); ++n) {
            ArrayList<TimelineEntry> arrayList2 = SmilPlayer.getTimeline((ElementTime)((Object)nodeList.item(n)), d3, d5);
            arrayList.addAll(arrayList2);
            d3 = ((TimelineEntry)arrayList2.get(arrayList2.size() - 1)).getOffsetTime();
        }
        object = object.getActiveChildrenAt((float)(d5 - d2));
        for (n = 0; n < object.getLength(); ++n) {
            arrayList.add((Object)new TimelineEntry(d5, (ElementTime)((Object)object.item(n)), 1));
        }
        arrayList.add((Object)timelineEntry);
        return arrayList;
    }

    private static ArrayList<TimelineEntry> getTimeline(ElementTime elementTime, double d2, double d3) {
        double d4;
        int n;
        Time time;
        if (elementTime instanceof ElementParallelTimeContainer) {
            return SmilPlayer.getParTimeline((ElementParallelTimeContainer)elementTime, d2, d3);
        }
        if (elementTime instanceof ElementSequentialTimeContainer) {
            return SmilPlayer.getSeqTimeline((ElementSequentialTimeContainer)elementTime, d2, d3);
        }
        ArrayList arrayList = new ArrayList();
        TimeList timeList = elementTime.getBegin();
        for (n = 0; n < timeList.getLength(); ++n) {
            time = timeList.item(n);
            if (!time.getResolved() || (d4 = time.getResolvedOffset() + d2) > d3) continue;
            arrayList.add((Object)new TimelineEntry(d4, elementTime, 0));
        }
        timeList = elementTime.getEnd();
        for (n = 0; n < timeList.getLength(); ++n) {
            time = timeList.item(n);
            if (!time.getResolved() || (d4 = time.getResolvedOffset() + d2) > d3) continue;
            arrayList.add((Object)new TimelineEntry(d4, elementTime, 1));
        }
        Collections.sort((List)arrayList, sTimelineEntryComparator);
        return arrayList;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isBeginOfSlide(TimelineEntry timelineEntry) {
        synchronized (this) {
            if (timelineEntry.getAction() != 0) return false;
            boolean bl = timelineEntry.getElement() instanceof SmilParElementImpl;
            if (!bl) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isNextAction() {
        synchronized (this) {
            SmilPlayerAction smilPlayerAction = this.mAction;
            SmilPlayerAction smilPlayerAction2 = SmilPlayerAction.NEXT;
            if (smilPlayerAction != smilPlayerAction2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isPauseAction() {
        synchronized (this) {
            SmilPlayerAction smilPlayerAction = this.mAction;
            SmilPlayerAction smilPlayerAction2 = SmilPlayerAction.PAUSE;
            if (smilPlayerAction != smilPlayerAction2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isPrevAction() {
        synchronized (this) {
            SmilPlayerAction smilPlayerAction = this.mAction;
            SmilPlayerAction smilPlayerAction2 = SmilPlayerAction.PREV;
            if (smilPlayerAction != smilPlayerAction2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isReloadAction() {
        synchronized (this) {
            SmilPlayerAction smilPlayerAction = this.mAction;
            SmilPlayerAction smilPlayerAction2 = SmilPlayerAction.RELOAD;
            if (smilPlayerAction != smilPlayerAction2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isStartAction() {
        synchronized (this) {
            SmilPlayerAction smilPlayerAction = this.mAction;
            SmilPlayerAction smilPlayerAction2 = SmilPlayerAction.START;
            if (smilPlayerAction != smilPlayerAction2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isStopAction() {
        synchronized (this) {
            SmilPlayerAction smilPlayerAction = this.mAction;
            SmilPlayerAction smilPlayerAction2 = SmilPlayerAction.STOP;
            if (smilPlayerAction != smilPlayerAction2) return false;
            return true;
        }
    }

    private TimelineEntry loadNextSlide() {
        TimelineEntry timelineEntry;
        int n = this.mAllEntries.size();
        for (int i = this.mCurrentElement; i < n; ++i) {
            timelineEntry = (TimelineEntry)this.mAllEntries.get(i);
            if (!this.isBeginOfSlide(timelineEntry)) continue;
            this.mCurrentElement = i;
            this.mCurrentSlide = i;
            this.mCurrentTime = (long)(timelineEntry.getOffsetTime() * 1000.0);
            return timelineEntry;
        }
        ++this.mCurrentElement;
        timelineEntry = null;
        if (this.mCurrentElement < n) {
            timelineEntry = (TimelineEntry)this.mAllEntries.get(this.mCurrentElement);
            this.mCurrentTime = (long)(timelineEntry.getOffsetTime() * 1000.0);
        }
        return timelineEntry;
    }

    private TimelineEntry loadPrevSlide() {
        int n = -1;
        int n2 = 1;
        for (int i = this.mCurrentSlide; i >= 0; --i) {
            int n3;
            TimelineEntry timelineEntry = (TimelineEntry)this.mAllEntries.get(i);
            if (this.isBeginOfSlide(timelineEntry)) {
                n = i;
                n3 = n2 - 1;
                if (n2 == 0) {
                    this.mCurrentElement = i;
                    this.mCurrentSlide = i;
                    this.mCurrentTime = (long)(timelineEntry.getOffsetTime() * 1000.0);
                    return timelineEntry;
                }
            } else {
                n3 = n2;
            }
            n2 = n3;
        }
        if (n != -1) {
            this.mCurrentElement = n;
            this.mCurrentSlide = n;
            return (TimelineEntry)this.mAllEntries.get(this.mCurrentElement);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void pauseActiveElements() {
        synchronized (this) {
            int n = this.mActiveElements.size() - 1;
            while (n >= 0) {
                ((ElementTime)this.mActiveElements.get(n)).pauseElement();
                --n;
            }
            return;
        }
    }

    private void reloadActiveSlide() {
        synchronized (this) {
            this.mActiveElements.clear();
            this.beginSmilDocument();
            for (int i = this.mCurrentSlide; i < this.mCurrentElement; ++i) {
                this.actionEntry((TimelineEntry)this.mAllEntries.get(i));
            }
            this.seekActiveMedia();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private TimelineEntry reloadCurrentEntry() {
        synchronized (this) {
            if (this.mCurrentElement >= this.mAllEntries.size()) return null;
            return (TimelineEntry)this.mAllEntries.get(this.mCurrentElement);
        }
    }

    private void resumeActiveElements() {
        synchronized (this) {
            int n = this.mActiveElements.size();
            for (int i = 0; i < n; ++i) {
                ((ElementTime)this.mActiveElements.get(i)).resumeElement();
                continue;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void seekActiveMedia() {
        synchronized (this) {
            int n = this.mActiveElements.size() - 1;
            boolean bl;
            ElementTime elementTime;
            while (n >= 0 && !(bl = (elementTime = (ElementTime)this.mActiveElements.get(n)) instanceof SmilParElementImpl)) {
                double d2 = this.getOffsetTime(elementTime);
                if (d2 >= 0.0 && d2 <= (double)this.mCurrentTime) {
                    elementTime.seekElement((float)((double)this.mCurrentTime - d2));
                }
                --n;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void stopCurrentSlide() {
        HashSet hashSet = new HashSet();
        int n = this.mAllEntries.size();
        int n2 = this.mCurrentElement;
        while (n2 < n) {
            TimelineEntry timelineEntry = (TimelineEntry)this.mAllEntries.get(n2);
            int n3 = timelineEntry.getAction();
            if (timelineEntry.getElement() instanceof SmilParElementImpl && n3 == 1) {
                this.actionEntry(timelineEntry);
                this.mCurrentElement = n2;
                return;
            }
            if (n3 == 1 && !hashSet.contains((Object)timelineEntry)) {
                this.actionEntry(timelineEntry);
            } else if (n3 == 0) {
                hashSet.add((Object)timelineEntry);
            }
            ++n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void waitForEntry(long l) throws InterruptedException {
        synchronized (this) {
            long l2 = 0;
            while (l > 0) {
                boolean bl;
                long l3 = System.currentTimeMillis();
                long l4 = Math.min((long)l, (long)200);
                if (l2 < l4) {
                    this.wait(l4 - l2);
                    this.mCurrentTime += l4;
                    l2 = l4;
                } else {
                    l4 = 0;
                    this.mCurrentTime += l2;
                    l2 = l4;
                }
                if (this.isStopAction() || this.isReloadAction() || this.isPauseAction() || this.isNextAction() || (bl = this.isPrevAction())) {
                    return;
                }
                ((EventTarget)((Object)this.mRoot)).dispatchEvent(this.mMediaTimeUpdatedEvent);
                l -= 200;
                l4 = System.currentTimeMillis();
                l2 = l4 - l3 - l2;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void waitForWakeUp() {
        synchronized (this) {
            try {
                while (!(this.isStartAction() || this.isStopAction() || this.isReloadAction() || this.isNextAction() || this.isPrevAction())) {
                    this.wait(200);
                }
                if (this.isStartAction()) {
                    this.mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
                    this.mState = SmilPlayerState.PLAYING;
                }
            }
            catch (InterruptedException var1_1) {
                Log.e((String)"Mms/smil", (String)"Unexpected InterruptedException.", (Throwable)var1_1);
            }
            return;
        }
    }

    public int getCurrentPosition() {
        synchronized (this) {
            long l = this.mCurrentTime;
            int n = (int)l;
            return n;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getDuration() {
        synchronized (this) {
            if (this.mAllEntries == null) return 0;
            if (this.mAllEntries.isEmpty()) return 0;
            double d2 = ((TimelineEntry)this.mAllEntries.get(this.mAllEntries.size() - 1)).mOffsetTime;
            return (int)d2 * 1000;
        }
    }

    public void init(ElementTime elementTime) {
        synchronized (this) {
            this.mRoot = elementTime;
            this.mAllEntries = SmilPlayer.getTimeline(this.mRoot, 0.0, 9.223372036854776E18);
            this.mMediaTimeUpdatedEvent = ((DocumentEvent)((Object)this.mRoot)).createEvent("Event");
            this.mMediaTimeUpdatedEvent.initEvent("mediaTimeUpdated", false, false);
            this.mActiveElements = new ArrayList();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isPausedState() {
        synchronized (this) {
            SmilPlayerState smilPlayerState = this.mState;
            SmilPlayerState smilPlayerState2 = SmilPlayerState.PAUSED;
            if (smilPlayerState != smilPlayerState2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isPlayedState() {
        synchronized (this) {
            SmilPlayerState smilPlayerState = this.mState;
            SmilPlayerState smilPlayerState2 = SmilPlayerState.PLAYED;
            if (smilPlayerState != smilPlayerState2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isPlayingState() {
        synchronized (this) {
            SmilPlayerState smilPlayerState = this.mState;
            SmilPlayerState smilPlayerState2 = SmilPlayerState.PLAYING;
            if (smilPlayerState != smilPlayerState2) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isStoppedState() {
        synchronized (this) {
            SmilPlayerState smilPlayerState = this.mState;
            SmilPlayerState smilPlayerState2 = SmilPlayerState.STOPPED;
            if (smilPlayerState != smilPlayerState2) return false;
            return true;
        }
    }

    public void next() {
        synchronized (this) {
            if (this.isPlayingState() || this.isPausedState()) {
                this.mAction = SmilPlayerAction.NEXT;
                this.notifyAll();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void pause() {
        synchronized (this) {
            if (this.isPlayingState()) {
                this.mAction = SmilPlayerAction.PAUSE;
                this.notifyAll();
            } else {
                Log.w((String)"Mms/smil", (String)"Error State: Playback is not playing!");
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void play() {
        synchronized (this) {
            if (!this.isPlayingState()) {
                this.mCurrentTime = 0;
                this.mCurrentElement = 0;
                this.mCurrentSlide = 0;
                this.mPlayerThread = new Thread((Runnable)this);
                this.mState = SmilPlayerState.PLAYING;
                this.mPlayerThread.start();
            } else {
                Log.w((String)"Mms/smil", (String)"Error State: Playback is playing!");
            }
            return;
        }
    }

    public void prev() {
        synchronized (this) {
            if (this.isPlayingState() || this.isPausedState()) {
                this.mAction = SmilPlayerAction.PREV;
                this.notifyAll();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void reload() {
        synchronized (this) {
            if (this.isPlayingState() || this.isPausedState()) {
                this.mAction = SmilPlayerAction.RELOAD;
                this.notifyAll();
            } else if (this.isPlayedState()) {
                this.actionReload();
            }
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        if (this.isStoppedState()) {
            do {
                return;
                break;
            } while (true);
        }
        var1_1 = this.mAllEntries.size();
        this.mCurrentElement = 0;
        while (this.mCurrentElement < var1_1) {
            var7_6 = (TimelineEntry)this.mAllEntries.get(this.mCurrentElement);
            if (this.isBeginOfSlide(var7_6)) {
                this.mCurrentSlide = this.mCurrentElement;
            }
            var4_3 = (long)(var7_6.getOffsetTime() * 1000.0);
            while (var4_3 > this.mCurrentTime) {
                block17 : {
                    try {
                        this.waitForEntry(var4_3 - this.mCurrentTime);
                        var2_2 = var4_3;
                        var6_4 = var7_6;
                    }
                    catch (InterruptedException var6_5) {
                        Log.e((String)"Mms/smil", (String)"Unexpected InterruptedException.", (Throwable)var6_5);
                        var6_4 = var7_6;
                        var2_2 = var4_3;
                    }
lbl16: // 3 sources:
                    if (!(this.isPauseAction() || this.isStopAction() || this.isReloadAction() || this.isNextAction())) {
                        var7_6 = var6_4;
                        var4_3 = var2_2;
                        if (!this.isPrevAction()) continue;
                    }
                    if (this.isPauseAction()) {
                        this.actionPause();
                        this.waitForWakeUp();
                    }
                    if (this.isStopAction()) {
                        this.actionStop();
                        return;
                    }
                    ** GOTO lbl32
                    ** GOTO lbl16
lbl32: // 1 sources:
                    if (this.isReloadAction()) {
                        this.actionReload();
                        if ((var7_6 = this.reloadCurrentEntry()) == null) ** continue;
                        var6_4 = var7_6;
                        if (this.isPausedState()) {
                            this.mAction = SmilPlayerAction.PAUSE;
                            var6_4 = var7_6;
                        }
                    }
                    var7_6 = var6_4;
                    if (!this.isNextAction()) ** GOTO lbl50
                    var7_6 = this.actionNext();
                    if (var7_6 != null) {
                        var6_4 = var7_6;
                    }
                    if (this.mState != SmilPlayerState.PAUSED) ** GOTO lbl62
                    this.mAction = SmilPlayerAction.PAUSE;
                    this.actionEntry(var6_4);
lbl47: // 2 sources:
                    do {
                        var2_2 = this.mCurrentTime;
                        var7_6 = var6_4;
lbl50: // 2 sources:
                        var6_4 = var7_6;
                        if (!this.isPrevAction()) ** GOTO lbl16
                        var8_7 = this.actionPrev();
                        var6_4 = var7_6;
                        if (var8_7 != null) {
                            var6_4 = var8_7;
                        }
                        if (this.mState != SmilPlayerState.PAUSED) break block17;
                        this.mAction = SmilPlayerAction.PAUSE;
                        this.actionEntry(var6_4);
lbl59: // 2 sources:
                        do {
                            var2_2 = this.mCurrentTime;
                            ** GOTO lbl16
                            break;
                        } while (true);
                        break;
                    } while (true);
lbl62: // 1 sources:
                    this.mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
                    ** continue;
                }
                this.mAction = SmilPlayerAction.NO_ACTIVE_ACTION;
                ** continue;
            }
            this.mCurrentTime = var4_3;
            this.actionEntry(var7_6);
            ++this.mCurrentElement;
        }
        this.mState = SmilPlayerState.PLAYED;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void start() {
        synchronized (this) {
            if (this.isPausedState()) {
                this.resumeActiveElements();
                this.mAction = SmilPlayerAction.START;
                this.notifyAll();
            } else if (this.isPlayedState()) {
                this.play();
            } else {
                Log.w((String)"Mms/smil", (String)"Error State: Playback can not be started!");
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void stop() {
        synchronized (this) {
            if (this.isPlayingState() || this.isPausedState()) {
                this.mAction = SmilPlayerAction.STOP;
                this.notifyAll();
            } else if (this.isPlayedState()) {
                this.actionStop();
            }
            return;
        }
    }

    public void stopWhenReload() {
        synchronized (this) {
            this.endActiveElements();
            return;
        }
    }

    private static enum SmilPlayerAction {
        NO_ACTIVE_ACTION,
        RELOAD,
        STOP,
        PAUSE,
        START,
        NEXT,
        PREV;
        

        private SmilPlayerAction() {
        }
    }

    private static enum SmilPlayerState {
        INITIALIZED,
        PLAYING,
        PLAYED,
        PAUSED,
        STOPPED;
        

        private SmilPlayerState() {
        }
    }

    private static final class TimelineEntry {
        private final int mAction;
        private final ElementTime mElement;
        private final double mOffsetTime;

        public TimelineEntry(double d2, ElementTime elementTime, int n) {
            this.mOffsetTime = d2;
            this.mElement = elementTime;
            this.mAction = n;
        }

        public int getAction() {
            return this.mAction;
        }

        public ElementTime getElement() {
            return this.mElement;
        }

        public double getOffsetTime() {
            return this.mOffsetTime;
        }

        public String toString() {
            return "Type = " + this.mElement + " offset = " + this.getOffsetTime() + " action = " + this.getAction();
        }
    }

}

