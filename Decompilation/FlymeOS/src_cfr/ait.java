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
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface ait
extends IInterface {
    public void a(int var1, String var2);

    public static abstract class ait$a
    extends Binder
    implements ait {
        public ait$a() {
            this.attachInterface((IInterface)this, "com.meizu.flymesms.interfaces.IFlymeSMSCallback");
        }

        public static ait a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
            if (iInterface != null && iInterface instanceof ait) {
                return (ait)iInterface;
            }
            return new a(iBinder);
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
                    parcel2.writeString("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
                    return true;
                }
                case 1: 
            }
            parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
            this.a(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements ait {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override
            public void a(int n2, String string2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
                    parcel.writeInt(n2);
                    parcel.writeString(string2);
                    this.a.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }
        }

    }

}

