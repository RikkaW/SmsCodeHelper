/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.pm.PackageStats
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.content.pm;

import android.content.pm.PackageStats;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public interface IPackageStatsObserver
extends IInterface {
    public void onGetStatsCompleted(PackageStats var1, boolean var2);

    public static abstract class Stub
    extends Binder
    implements IPackageStatsObserver {
        private static final String DESCRIPTOR = "android.content.pm.IPackageStatsObserver";
        static final int TRANSACTION_onGetStatsCompleted = 1;

        public Stub() {
            this.attachInterface((IInterface)this, "android.content.pm.IPackageStatsObserver");
        }

        public static IPackageStatsObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("android.content.pm.IPackageStatsObserver");
            if (iInterface != null && iInterface instanceof IPackageStatsObserver) {
                return (IPackageStatsObserver)iInterface;
            }
            return new Proxy(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("android.content.pm.IPackageStatsObserver");
                    return true;
                }
                case 1: 
            }
            parcel.enforceInterface("android.content.pm.IPackageStatsObserver");
            parcel2 = parcel.readInt() != 0 ? (PackageStats)PackageStats.CREATOR.createFromParcel(parcel) : null;
            boolean bl2 = parcel.readInt() != 0;
            this.onGetStatsCompleted((PackageStats)parcel2, bl2);
            return true;
        }

        static class Proxy
        implements IPackageStatsObserver {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.content.pm.IPackageStatsObserver";
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onGetStatsCompleted(PackageStats packageStats, boolean bl2) {
                int n2 = 1;
                Parcel parcel = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
                    if (packageStats != null) {
                        parcel.writeInt(1);
                        packageStats.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                }
                catch (Throwable var1_2) {
                    parcel.recycle();
                    throw var1_2;
                }
                if (!bl2) {
                    n2 = 0;
                }
                parcel.writeInt(n2);
                this.mRemote.transact(1, parcel, null, 1);
                parcel.recycle();
            }
        }

    }

}

