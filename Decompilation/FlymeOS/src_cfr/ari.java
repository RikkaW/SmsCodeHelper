/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import java.util.List;

class ari {
    final a a;

    private void a(List<arc.b> list, int n2, int n3) {
        arc.b b2 = list.get(n2);
        arc.b b3 = list.get(n3);
        switch (b3.a) {
            default: {
                return;
            }
            case 1: {
                this.a(list, n2, b2, n3, b3);
                return;
            }
            case 0: {
                this.c(list, n2, b2, n3, b3);
                return;
            }
            case 2: 
        }
        this.b(list, n2, b2, n3, b3);
    }

    private int b(List<arc.b> list) {
        boolean bl2 = false;
        for (int i2 = list.size() - 1; i2 >= 0; --i2) {
            if (list.get((int)i2).a == 3) {
                if (!bl2) continue;
                return i2;
            }
            bl2 = true;
        }
        return -1;
    }

    private void c(List<arc.b> list, int n2, arc.b b2, int n3, arc.b b3) {
        int n4 = 0;
        if (b2.c < b3.b) {
            n4 = -1;
        }
        int n5 = n4;
        if (b2.b < b3.b) {
            n5 = n4 + 1;
        }
        if (b3.b <= b2.b) {
            b2.b += b3.c;
        }
        if (b3.b <= b2.c) {
            b2.c += b3.c;
        }
        b3.b = n5 + b3.b;
        list.set(n2, b3);
        list.set(n3, b2);
    }

    void a(List<arc.b> list) {
        int n2;
        while ((n2 = this.b(list)) != -1) {
            this.a(list, n2, n2 + 1);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void a(List<arc.b> list, int n2, arc.b b2, int n3, arc.b b3) {
        arc.b b4;
        boolean bl2;
        boolean bl3 = false;
        if (b2.b < b2.c) {
            bl2 = b3.b == b2.b && b3.c == b2.c - b2.b;
        } else if (b3.b == b2.c + 1 && b3.c == b2.b - b2.c) {
            bl3 = true;
            bl2 = true;
        } else {
            bl2 = false;
            bl3 = true;
        }
        if (b2.c < b3.b) {
            --b3.b;
        } else if (b2.c < b3.b + b3.c) {
            --b3.c;
            b2.a = 1;
            b2.c = 1;
            if (b3.c != 0) return;
            list.remove(n3);
            this.a.a(b3);
            return;
        }
        if (b2.b <= b3.b) {
            ++b3.b;
            b4 = null;
        } else if (b2.b < b3.b + b3.c) {
            int n4 = b3.b;
            int n5 = b3.c;
            int n6 = b2.b;
            b4 = this.a.a(1, b2.b + 1, n4 + n5 - n6);
            b3.c = b2.b - b3.b;
        } else {
            b4 = null;
        }
        if (bl2) {
            list.set(n2, b3);
            list.remove(n3);
            this.a.a(b2);
            return;
        }
        if (bl3) {
            if (b4 != null) {
                if (b2.b > b4.b) {
                    b2.b -= b4.c;
                }
                if (b2.c > b4.b) {
                    b2.c -= b4.c;
                }
            }
            if (b2.b > b3.b) {
                b2.b -= b3.c;
            }
            if (b2.c > b3.b) {
                b2.c -= b3.c;
            }
        } else {
            if (b4 != null) {
                if (b2.b >= b4.b) {
                    b2.b -= b4.c;
                }
                if (b2.c >= b4.b) {
                    b2.c -= b4.c;
                }
            }
            if (b2.b >= b3.b) {
                b2.b -= b3.c;
            }
            if (b2.c >= b3.b) {
                b2.c -= b3.c;
            }
        }
        list.set(n2, b3);
        if (b2.b != b2.c) {
            list.set(n3, b2);
        } else {
            list.remove(n3);
        }
        if (b4 == null) return;
        list.add(n2, b4);
    }

    /*
     * Enabled aggressive block sorting
     */
    void b(List<arc.b> list, int n2, arc.b b2, int n3, arc.b b3) {
        arc.b b4;
        arc.b b5 = null;
        if (b2.c < b3.b) {
            --b3.b;
            b4 = null;
        } else if (b2.c < b3.b + b3.c) {
            --b3.c;
            b4 = this.a.a(2, b2.b, 1);
        } else {
            b4 = null;
        }
        if (b2.b <= b3.b) {
            ++b3.b;
        } else if (b2.b < b3.b + b3.c) {
            int n4 = b3.b + b3.c - b2.b;
            b5 = this.a.a(2, b2.b + 1, n4);
            b3.c -= n4;
        }
        list.set(n3, b2);
        if (b3.c > 0) {
            list.set(n2, b3);
        } else {
            list.remove(n2);
            this.a.a(b3);
        }
        if (b4 != null) {
            list.add(n2, b4);
        }
        if (b5 != null) {
            list.add(n2, b5);
        }
    }

    static interface a {
        public arc.b a(int var1, int var2, int var3);

        public void a(arc.b var1);
    }

}

