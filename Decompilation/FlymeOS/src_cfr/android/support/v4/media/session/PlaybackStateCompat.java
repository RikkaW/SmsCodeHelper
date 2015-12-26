/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.os.SystemClock
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat$1;
import android.support.v4.media.session.PlaybackStateCompat$CustomAction$1;
import android.support.v4.media.session.PlaybackStateCompatApi21;
import android.support.v4.media.session.PlaybackStateCompatApi22;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class PlaybackStateCompat
implements Parcelable {
    public static final long ACTION_FAST_FORWARD = 64;
    public static final long ACTION_PAUSE = 2;
    public static final long ACTION_PLAY = 4;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048;
    public static final long ACTION_PLAY_PAUSE = 512;
    public static final long ACTION_REWIND = 8;
    public static final long ACTION_SEEK_TO = 256;
    public static final long ACTION_SET_RATING = 128;
    public static final long ACTION_SKIP_TO_NEXT = 32;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096;
    public static final long ACTION_STOP = 1;
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new PlaybackStateCompat$1();
    public static final long PLAYBACK_POSITION_UNKNOWN = -1;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    private final long mActions;
    private final long mActiveItemId;
    private final long mBufferedPosition;
    private List<CustomAction> mCustomActions;
    private final CharSequence mErrorMessage;
    private final Bundle mExtras;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;
    private Object mStateObj;
    private final long mUpdateTime;

    private PlaybackStateCompat(int n2, long l2, long l3, float f2, long l4, CharSequence charSequence, long l5, List<CustomAction> list, long l6, Bundle bundle) {
        this.mState = n2;
        this.mPosition = l2;
        this.mBufferedPosition = l3;
        this.mSpeed = f2;
        this.mActions = l4;
        this.mErrorMessage = charSequence;
        this.mUpdateTime = l5;
        this.mCustomActions = new ArrayList(list);
        this.mActiveItemId = l6;
        this.mExtras = bundle;
    }

    /* synthetic */ PlaybackStateCompat(int n2, long l2, long l3, float f2, long l4, CharSequence charSequence, long l5, List list, long l6, Bundle bundle, PlaybackStateCompat$1 playbackStateCompat$1) {
        this(n2, l2, l3, f2, l4, charSequence, l5, list, l6, bundle);
    }

    private PlaybackStateCompat(Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.mBufferedPosition = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mCustomActions = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.mActiveItemId = parcel.readLong();
        this.mExtras = parcel.readBundle();
    }

    /* synthetic */ PlaybackStateCompat(Parcel parcel, PlaybackStateCompat$1 playbackStateCompat$1) {
        this(parcel);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static PlaybackStateCompat fromPlaybackState(Object object) {
        ArrayList arrayList;
        if (object == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Object object2 = PlaybackStateCompatApi21.getCustomActions(object);
        Object object3 = null;
        if (object2 != null) {
            arrayList = new ArrayList(object2.size());
            object2 = object2.iterator();
            do {
                object3 = arrayList;
                if (!object2.hasNext()) break;
                arrayList.add(CustomAction.fromCustomAction(object2.next()));
            } while (true);
        }
        arrayList = Build.VERSION.SDK_INT >= 22 ? PlaybackStateCompatApi22.getExtras(object) : null;
        object3 = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(object), PlaybackStateCompatApi21.getPosition(object), PlaybackStateCompatApi21.getBufferedPosition(object), PlaybackStateCompatApi21.getPlaybackSpeed(object), PlaybackStateCompatApi21.getActions(object), PlaybackStateCompatApi21.getErrorMessage(object), PlaybackStateCompatApi21.getLastPositionUpdateTime(object), (List<CustomAction>)object3, PlaybackStateCompatApi21.getActiveQueueItemId(object), (Bundle)arrayList);
        object3.mStateObj = object;
        return object3;
    }

    public int describeContents() {
        return 0;
    }

    public long getActions() {
        return this.mActions;
    }

    public long getActiveQueueItemId() {
        return this.mActiveItemId;
    }

    public long getBufferedPosition() {
        return this.mBufferedPosition;
    }

    public List<CustomAction> getCustomActions() {
        return this.mCustomActions;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    public long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }

    public float getPlaybackSpeed() {
        return this.mSpeed;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object getPlaybackState() {
        if (this.mStateObj != null || Build.VERSION.SDK_INT < 21) {
            return this.mStateObj;
        }
        ArrayList arrayList = null;
        if (this.mCustomActions != null) {
            ArrayList arrayList2 = new ArrayList(this.mCustomActions.size());
            Iterator<CustomAction> iterator = this.mCustomActions.iterator();
            do {
                arrayList = arrayList2;
                if (!iterator.hasNext()) break;
                arrayList2.add(iterator.next().getCustomAction());
            } while (true);
        }
        if (Build.VERSION.SDK_INT >= 22) {
            this.mStateObj = PlaybackStateCompatApi22.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, arrayList, this.mActiveItemId, this.mExtras);
            do {
                return this.mStateObj;
                break;
            } while (true);
        }
        this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, arrayList, this.mActiveItemId);
        return this.mStateObj;
    }

    public long getPosition() {
        return this.mPosition;
    }

    public int getState() {
        return this.mState;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PlaybackState {");
        stringBuilder.append("state=").append(this.mState);
        stringBuilder.append(", position=").append(this.mPosition);
        stringBuilder.append(", buffered position=").append(this.mBufferedPosition);
        stringBuilder.append(", speed=").append(this.mSpeed);
        stringBuilder.append(", updated=").append(this.mUpdateTime);
        stringBuilder.append(", actions=").append(this.mActions);
        stringBuilder.append(", error=").append(this.mErrorMessage);
        stringBuilder.append(", custom actions=").append(this.mCustomActions);
        stringBuilder.append(", active item id=").append(this.mActiveItemId);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int n2) {
        parcel.writeInt(this.mState);
        parcel.writeLong(this.mPosition);
        parcel.writeFloat(this.mSpeed);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.mBufferedPosition);
        parcel.writeLong(this.mActions);
        TextUtils.writeToParcel((CharSequence)this.mErrorMessage, (Parcel)parcel, (int)n2);
        parcel.writeTypedList(this.mCustomActions);
        parcel.writeLong(this.mActiveItemId);
        parcel.writeBundle(this.mExtras);
    }

    public static final class Builder {
        private long mActions;
        private long mActiveItemId = -1;
        private long mBufferedPosition;
        private final List<CustomAction> mCustomActions = new ArrayList();
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder() {
        }

        public Builder(PlaybackStateCompat playbackStateCompat) {
            this.mState = playbackStateCompat.mState;
            this.mPosition = playbackStateCompat.mPosition;
            this.mRate = playbackStateCompat.mSpeed;
            this.mUpdateTime = playbackStateCompat.mUpdateTime;
            this.mBufferedPosition = playbackStateCompat.mBufferedPosition;
            this.mActions = playbackStateCompat.mActions;
            this.mErrorMessage = playbackStateCompat.mErrorMessage;
            if (playbackStateCompat.mCustomActions != null) {
                this.mCustomActions.addAll(playbackStateCompat.mCustomActions);
            }
            this.mActiveItemId = playbackStateCompat.mActiveItemId;
            this.mExtras = playbackStateCompat.mExtras;
        }

        public Builder addCustomAction(CustomAction customAction) {
            if (customAction == null) {
                throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
            }
            this.mCustomActions.add(customAction);
            return this;
        }

        public Builder addCustomAction(String string2, String string3, int n2) {
            return this.addCustomAction(new CustomAction(string2, string3, n2, null, null));
        }

        public PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras, null);
        }

        public Builder setActions(long l2) {
            this.mActions = l2;
            return this;
        }

        public Builder setActiveQueueItemId(long l2) {
            this.mActiveItemId = l2;
            return this;
        }

        public Builder setBufferedPosition(long l2) {
            this.mBufferedPosition = l2;
            return this;
        }

        public Builder setErrorMessage(CharSequence charSequence) {
            this.mErrorMessage = charSequence;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setState(int n2, long l2, float f2) {
            return this.setState(n2, l2, f2, SystemClock.elapsedRealtime());
        }

        public Builder setState(int n2, long l2, float f2, long l3) {
            this.mState = n2;
            this.mPosition = l2;
            this.mUpdateTime = l3;
            this.mRate = f2;
            return this;
        }
    }

    public static final class CustomAction
    implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new PlaybackStateCompat$CustomAction$1();
        private final String mAction;
        private Object mCustomActionObj;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        private CustomAction(Parcel parcel) {
            this.mAction = parcel.readString();
            this.mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.mIcon = parcel.readInt();
            this.mExtras = parcel.readBundle();
        }

        /* synthetic */ CustomAction(Parcel parcel, PlaybackStateCompat$1 playbackStateCompat$1) {
            this(parcel);
        }

        private CustomAction(String string2, CharSequence charSequence, int n2, Bundle bundle) {
            this.mAction = string2;
            this.mName = charSequence;
            this.mIcon = n2;
            this.mExtras = bundle;
        }

        /* synthetic */ CustomAction(String string2, CharSequence charSequence, int n2, Bundle bundle, PlaybackStateCompat$1 playbackStateCompat$1) {
            this(string2, charSequence, n2, bundle);
        }

        public static CustomAction fromCustomAction(Object object) {
            if (object == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(object), PlaybackStateCompatApi21.CustomAction.getName(object), PlaybackStateCompatApi21.CustomAction.getIcon(object), PlaybackStateCompatApi21.CustomAction.getExtras(object));
            customAction.mCustomActionObj = object;
            return customAction;
        }

        public int describeContents() {
            return 0;
        }

        public String getAction() {
            return this.mAction;
        }

        public Object getCustomAction() {
            if (this.mCustomActionObj != null || Build.VERSION.SDK_INT < 21) {
                return this.mCustomActionObj;
            }
            this.mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(this.mAction, this.mName, this.mIcon, this.mExtras);
            return this.mCustomActionObj;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public int getIcon() {
            return this.mIcon;
        }

        public CharSequence getName() {
            return this.mName;
        }

        public String toString() {
            return "Action:mName='" + this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + (Object)this.mExtras;
        }

        public void writeToParcel(Parcel parcel, int n2) {
            parcel.writeString(this.mAction);
            TextUtils.writeToParcel((CharSequence)this.mName, (Parcel)parcel, (int)n2);
            parcel.writeInt(this.mIcon);
            parcel.writeBundle(this.mExtras);
        }

        public static final class Builder {
            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String string2, CharSequence charSequence, int n2) {
                if (TextUtils.isEmpty((CharSequence)string2)) {
                    throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
                }
                if (TextUtils.isEmpty((CharSequence)charSequence)) {
                    throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
                }
                if (n2 == 0) {
                    throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
                }
                this.mAction = string2;
                this.mName = charSequence;
                this.mIcon = n2;
            }

            public CustomAction build() {
                return new CustomAction(this.mAction, this.mName, this.mIcon, this.mExtras, null);
            }

            public Builder setExtras(Bundle bundle) {
                this.mExtras = bundle;
                return this;
            }
        }

    }

}

