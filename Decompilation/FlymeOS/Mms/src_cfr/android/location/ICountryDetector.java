/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 */
package android.location;

import android.location.Country;
import android.location.ICountryListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public interface ICountryDetector
extends IInterface {
    public void addCountryListener(ICountryListener var1);

    public Country detectCountry();

    public void removeCountryListener(ICountryListener var1);

    public static abstract class Stub
    extends Binder
    implements ICountryDetector {
        private static final String DESCRIPTOR = "android.location.ICountryDetector";
        static final int TRANSACTION_addCountryListener = 2;
        static final int TRANSACTION_detectCountry = 1;
        static final int TRANSACTION_removeCountryListener = 3;

        public Stub() {
            this.attachInterface((IInterface)this, "android.location.ICountryDetector");
        }

        public static ICountryDetector asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("android.location.ICountryDetector");
            if (iInterface != null && iInterface instanceof ICountryDetector) {
                return (ICountryDetector)iInterface;
            }
            return new Proxy(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int n2, Parcel object, Parcel parcel, int n3) {
            switch (n2) {
                default: {
                    return super.onTransact(n2, (Parcel)object, parcel, n3);
                }
                case 1598968902: {
                    parcel.writeString("android.location.ICountryDetector");
                    return true;
                }
                case 1: {
                    object.enforceInterface("android.location.ICountryDetector");
                    object = this.detectCountry();
                    parcel.writeNoException();
                    if (object != null) {
                        parcel.writeInt(1);
                        object.writeToParcel(parcel, 1);
                        return true;
                    }
                    parcel.writeInt(0);
                    return true;
                }
                case 2: {
                    object.enforceInterface("android.location.ICountryDetector");
                    this.addCountryListener(ICountryListener.Stub.asInterface(object.readStrongBinder()));
                    parcel.writeNoException();
                    return true;
                }
                case 3: 
            }
            object.enforceInterface("android.location.ICountryDetector");
            this.removeCountryListener(ICountryListener.Stub.asInterface(object.readStrongBinder()));
            parcel.writeNoException();
            return true;
        }

        static class Proxy
        implements ICountryDetector {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void addCountryListener(ICountryListener iCountryListener) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("android.location.ICountryDetector");
                    iCountryListener = iCountryListener != null ? iCountryListener.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)iCountryListener);
                    this.mRemote.transact(2, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public Country detectCountry() {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("android.location.ICountryDetector");
                    this.mRemote.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    Country country = parcel2.readInt() != 0 ? (Country)Country.CREATOR.createFromParcel(parcel2) : null;
                    return country;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "android.location.ICountryDetector";
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void removeCountryListener(ICountryListener iCountryListener) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("android.location.ICountryDetector");
                    iCountryListener = iCountryListener != null ? iCountryListener.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)iCountryListener);
                    this.mRemote.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }
        }

    }

}

