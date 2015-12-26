/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class aaq {
    private final b a;
    private boolean b;

    public aaq(Context context, a a2) {
        context = (SensorManager)context.getSystemService("sensor");
        Sensor sensor = context.getDefaultSensor(8);
        if (sensor == null) {
            this.a = null;
            return;
        }
        this.a = new b((SensorManager)context, sensor, a2);
    }

    public void a() {
        if (this.a != null && !this.b) {
            this.a.b();
            this.b = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl2) {
        if (this.a != null && this.b) {
            if (bl2) {
                this.a.a();
            } else {
                this.a.c();
            }
            this.b = false;
        }
    }

    public static interface a {
        public void a();

        public void b();
    }

    static class b
    implements SensorEventListener {
        private final SensorManager a;
        private final Sensor b;
        private final float c;
        private final a d;
        private c e;
        private boolean f;

        public b(SensorManager sensorManager, Sensor sensor, a a2) {
            this.a = sensorManager;
            this.b = sensor;
            this.c = sensor.getMaximumRange();
            this.d = a2;
            this.e = c.b;
            this.f = false;
        }

        private c a(float f2) {
            float f3 = Math.min((float)this.c, (float)5.0f);
            if (f2 >= 0.0f && f2 < f3) {
                return c.a;
            }
            return c.b;
        }

        private void d() {
            this.a.unregisterListener((SensorEventListener)this);
            this.f = false;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void a() {
            synchronized (this) {
                if (this.e == c.b) {
                    this.d();
                } else {
                    this.f = true;
                }
                return;
            }
        }

        public void b() {
            synchronized (this) {
                this.a.registerListener((SensorEventListener)this, this.b, 2);
                this.f = false;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        public void c() {
            // MONITORENTER : this
            this.d();
            c c2 = this.e;
            this.e = c.b;
            // MONITOREXIT : this
            if (c2 == c.b) return;
            this.d.b();
        }

        public void onAccuracyChanged(Sensor sensor, int n2) {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onSensorChanged(SensorEvent object) {
            Log.d((String)"VoicePlayer", (String)"onSensorChanged()~~~~");
            if (object.values == null || object.values.length == 0) {
                return;
            }
            float f2 = object.values[0];
            Log.d((String)"VoicePlayer", (String)("onSensorChanged() --> value = " + f2));
            object = this.a(f2);
            synchronized (this) {
                if (object == this.e) {
                    return;
                }
                this.e = object;
                if (this.f && this.e == c.b) {
                    this.d();
                }
            }
            switch (.a[object.ordinal()]) {
                default: {
                    return;
                }
                case 1: {
                    this.d.a();
                    return;
                }
                case 2: 
            }
            this.d.b();
        }
    }

    public static enum c {
        a,
        b;
        

        private c() {
        }
    }

}

