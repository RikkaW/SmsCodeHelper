/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 *  java.util.Map$Entry
 */
package com.google.common.base;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Joiner {
    private final String separator;

    private Joiner(String string2) {
        this.separator = Preconditions.checkNotNull(string2);
    }

    public static Joiner on(String string2) {
        return new Joiner(string2);
    }

    public <A extends Appendable> A appendTo(A a2, Iterator<?> iterator) throws IOException {
        Preconditions.checkNotNull(a2);
        if (iterator.hasNext()) {
            a2.append(this.toString(iterator.next()));
            while (iterator.hasNext()) {
                a2.append(this.separator);
                a2.append(this.toString(iterator.next()));
            }
        }
        return a2;
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, Iterable<?> iterable) {
        return this.appendTo(stringBuilder, iterable.iterator());
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, Iterator<?> iterator) {
        try {
            this.appendTo((A)stringBuilder, iterator);
            return stringBuilder;
        }
        catch (IOException var1_2) {
            throw new AssertionError((Object)var1_2);
        }
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, Object[] arrobject) {
        return this.appendTo(stringBuilder, Arrays.asList((Object[])arrobject));
    }

    CharSequence toString(Object object) {
        Preconditions.checkNotNull(object);
        if (object instanceof CharSequence) {
            return (CharSequence)object;
        }
        return object.toString();
    }

    public MapJoiner withKeyValueSeparator(String string2) {
        return new MapJoiner(this, string2);
    }

    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner, String string2) {
            this.joiner = joiner;
            this.keyValueSeparator = Preconditions.checkNotNull(string2);
        }

        public <A extends Appendable> A appendTo(A a2, Iterator<? extends Map.Entry<?, ?>> iterator) throws IOException {
            Preconditions.checkNotNull(a2);
            if (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                a2.append(this.joiner.toString(entry.getKey()));
                a2.append(this.keyValueSeparator);
                a2.append(this.joiner.toString(entry.getValue()));
                while (iterator.hasNext()) {
                    a2.append(this.joiner.separator);
                    entry = iterator.next();
                    a2.append(this.joiner.toString(entry.getKey()));
                    a2.append(this.keyValueSeparator);
                    a2.append(this.joiner.toString(entry.getValue()));
                }
            }
            return a2;
        }

        public StringBuilder appendTo(StringBuilder stringBuilder, Iterable<? extends Map.Entry<?, ?>> iterable) {
            return this.appendTo(stringBuilder, iterable.iterator());
        }

        public StringBuilder appendTo(StringBuilder stringBuilder, Iterator<? extends Map.Entry<?, ?>> iterator) {
            try {
                this.appendTo((A)stringBuilder, iterator);
                return stringBuilder;
            }
            catch (IOException var1_2) {
                throw new AssertionError((Object)var1_2);
            }
        }

        public StringBuilder appendTo(StringBuilder stringBuilder, Map<?, ?> map) {
            return this.appendTo(stringBuilder, map.entrySet());
        }
    }

}

