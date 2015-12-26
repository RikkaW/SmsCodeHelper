/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.lang.ThreadLocal
 *  java.net.Socket
 */
package android.support.v4.net;

import android.os.Build;
import android.support.v4.net.TrafficStatsCompat$BaseTrafficStatsCompatImpl$1;
import android.support.v4.net.TrafficStatsCompatIcs;
import java.net.Socket;

public class TrafficStatsCompat {
    private static final TrafficStatsCompatImpl IMPL = Build.VERSION.SDK_INT >= 14 ? new IcsTrafficStatsCompatImpl() : new BaseTrafficStatsCompatImpl();

    public static void clearThreadStatsTag() {
        IMPL.clearThreadStatsTag();
    }

    public static int getThreadStatsTag() {
        return IMPL.getThreadStatsTag();
    }

    public static void incrementOperationCount(int n2) {
        IMPL.incrementOperationCount(n2);
    }

    public static void incrementOperationCount(int n2, int n3) {
        IMPL.incrementOperationCount(n2, n3);
    }

    public static void setThreadStatsTag(int n2) {
        IMPL.setThreadStatsTag(n2);
    }

    public static void tagSocket(Socket socket) {
        IMPL.tagSocket(socket);
    }

    public static void untagSocket(Socket socket) {
        IMPL.untagSocket(socket);
    }

    static class BaseTrafficStatsCompatImpl
    implements TrafficStatsCompatImpl {
        private ThreadLocal<SocketTags> mThreadSocketTags;

        BaseTrafficStatsCompatImpl() {
            this.mThreadSocketTags = new TrafficStatsCompat$BaseTrafficStatsCompatImpl$1(this);
        }

        @Override
        public void clearThreadStatsTag() {
            ((SocketTags)this.mThreadSocketTags.get()).statsTag = -1;
        }

        @Override
        public int getThreadStatsTag() {
            return ((SocketTags)this.mThreadSocketTags.get()).statsTag;
        }

        @Override
        public void incrementOperationCount(int n2) {
        }

        @Override
        public void incrementOperationCount(int n2, int n3) {
        }

        @Override
        public void setThreadStatsTag(int n2) {
            ((SocketTags)this.mThreadSocketTags.get()).statsTag = n2;
        }

        @Override
        public void tagSocket(Socket socket) {
        }

        @Override
        public void untagSocket(Socket socket) {
        }

        static class SocketTags {
            public int statsTag = -1;

            private SocketTags() {
            }
        }

    }

    static class IcsTrafficStatsCompatImpl
    implements TrafficStatsCompatImpl {
        IcsTrafficStatsCompatImpl() {
        }

        @Override
        public void clearThreadStatsTag() {
            TrafficStatsCompatIcs.clearThreadStatsTag();
        }

        @Override
        public int getThreadStatsTag() {
            return TrafficStatsCompatIcs.getThreadStatsTag();
        }

        @Override
        public void incrementOperationCount(int n2) {
            TrafficStatsCompatIcs.incrementOperationCount(n2);
        }

        @Override
        public void incrementOperationCount(int n2, int n3) {
            TrafficStatsCompatIcs.incrementOperationCount(n2, n3);
        }

        @Override
        public void setThreadStatsTag(int n2) {
            TrafficStatsCompatIcs.setThreadStatsTag(n2);
        }

        @Override
        public void tagSocket(Socket socket) {
            TrafficStatsCompatIcs.tagSocket(socket);
        }

        @Override
        public void untagSocket(Socket socket) {
            TrafficStatsCompatIcs.untagSocket(socket);
        }
    }

    static interface TrafficStatsCompatImpl {
        public void clearThreadStatsTag();

        public int getThreadStatsTag();

        public void incrementOperationCount(int var1);

        public void incrementOperationCount(int var1, int var2);

        public void setThreadStatsTag(int var1);

        public void tagSocket(Socket var1);

        public void untagSocket(Socket var1);
    }

}

