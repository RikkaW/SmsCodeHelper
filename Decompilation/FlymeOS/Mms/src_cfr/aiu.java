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

public interface aiu
extends IInterface {
    public void a(String var1);

    public void a(String var1, long var2);

    public void a(String var1, long var2, long var4);

    public void a(String var1, String var2);

    public void a(String var1, byte[] var2);

    public void b(String var1);

    public void b(String var1, String var2);

    public void c(String var1);

    public static abstract class aiu$a
    extends Binder
    implements aiu {
        public aiu$a() {
            this.attachInterface((IInterface)this, "com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        }

        public static aiu a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
            if (iInterface != null && iInterface instanceof aiu) {
                return (aiu)iInterface;
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
                    parcel2.writeString("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.a(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.a(parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.a(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.c(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    this.b(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8: 
            }
            parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
            this.a(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements aiu {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override
            public void a(String string2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    this.a.transact(8, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2, long l2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    parcel.writeLong(l2);
                    this.a.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2, long l2, long l3) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    parcel.writeLong(l2);
                    parcel.writeLong(l3);
                    this.a.transact(2, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2, String string3) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    this.a.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2, byte[] arrby) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    parcel.writeByteArray(arrby);
                    this.a.transact(4, parcel, parcel2, 0);
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

            @Override
            public void b(String string2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    this.a.transact(5, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void b(String string2, String string3) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    this.a.transact(7, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void c(String string2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
                    parcel.writeString(string2);
                    this.a.transact(6, parcel, parcel2, 0);
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

