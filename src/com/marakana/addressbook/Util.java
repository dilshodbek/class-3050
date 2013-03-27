
package com.marakana.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Util {
    private Util() {

    }

    public static <T> T notNull(T o, String what) {
        if (o == null) {
            throw new NullPointerException(what + " must not be null");
        }
        return o;
    }

    public static <T> T getLast(List<T> list) {
        return list == null || list.isEmpty() ? null : list.get(list.size() - 1);
    }

    public static <T> List<T> asList(T e) {
        List<T> list = new ArrayList<>(1);
        list.add(e);
        return list;
    }

    public static <K, V> Map<K, V> asMap(K k, V v) {
        Map<K, V> map = new HashMap<>(1);
        map.put(k, v);
        return map;
    }

    public static <T extends Comparable<T>> int compare(T o1, T o2) {
        return o1 == null ? (o2 == null ? 0 : -1) : o2 == null ? 1 : o1.compareTo(o2);
    }

    public static <T> List<T> add(List<T> list, T e) {
        list.add(e);
        return list;
    }
}
