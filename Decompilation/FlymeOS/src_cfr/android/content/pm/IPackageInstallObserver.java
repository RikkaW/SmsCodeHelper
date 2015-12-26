/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  java.lang.Object
 *  java.lang.String
 */
package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IPackageInstallObserver
extends IInterface {
    public void packageInstalled(String var1, int var2);

    public static abstract class Stub
    extends Binder
    implements IPackageInstallObserver {
        private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver";
        static final int TRANSACTION_packageInstalled = 1;

        public Stub() {
            this.attachInterface((IInterface)this, "android.content.pm.IPackageInstallObserver");
        }

        public static IPackageInstallObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("android.content.pm.IPackageInstallObserver");
            if (iInterface != null && iInterface instanceof IPackageInstallObserver) {
                return (IPackageInstallObserver)iInterface;
            }
            return new Proxy(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("android.content.pm.IPackageInstallObserver");
                    return true;
                }
                case 1: 
            }
            parcel.enforceInterface("android.content.pm.IPackageInstallObserver");
            this.packageInstalled(parcel.readString(), parcel.readInt());
            return true;
        }

        static class Proxy
        implements IPackageInstallObserver {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.content.pm.IPackageInstallObserver";
            }

            @Override
            public void packageInstalled(String string2, int n2) {
                Parcel parcel = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver");
                    parcel.writeString(string2);
                    parcel.writeInt(n2);
                    this.mRemote.transact(1, parcel, null, 1);
                    return;
                }
                finally {
                    parcel.recycle();
                }
            }
        }

    }

}

