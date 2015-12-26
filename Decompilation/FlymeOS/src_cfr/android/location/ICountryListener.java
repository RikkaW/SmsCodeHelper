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
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public interface ICountryListener
extends IInterface {
    public void onCountryDetected(Country var1);

    public static abstract class Stub
    extends Binder
    implements ICountryListener {
        private static final String DESCRIPTOR = "android.location.ICountryListener";
        static final int TRANSACTION_onCountryDetected = 1;

        public Stub() {
            this.attachInterface((IInterface)this, "android.location.ICountryListener");
        }

        public static ICountryListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("android.location.ICountryListener");
            if (iInterface != null && iInterface instanceof ICountryListener) {
                return (ICountryListener)iInterface;
            }
            return new Proxy(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTransact(int n2, Parcel object, Parcel parcel, int n3) {
            switch (n2) {
                default: {
                    return super.onTransact(n2, (Parcel)object, parcel, n3);
                }
                case 1598968902: {
                    parcel.writeString("android.location.ICountryListener");
                    return true;
                }
                case 1: 
            }
            object.enforceInterface("android.location.ICountryListener");
            object = object.readInt() != 0 ? (Country)Country.CREATOR.createFromParcel((Parcel)object) : null;
            this.onCountryDetected((Country)object);
            return true;
        }

        static class Proxy
        implements ICountryListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.location.ICountryListener";
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void onCountryDetected(Country country) {
                Parcel parcel = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("android.location.ICountryListener");
                    if (country != null) {
                        parcel.writeInt(1);
                        country.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
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

