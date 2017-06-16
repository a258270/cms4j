package com.crm4j.base.util;

import java.util.UUID;

/**
 * description: 获取短位uuid
 *
 * @author: zmj
 * @create: 2017/5/31
 */
public class ShortUUID {
    public static String randomUUID() {
        return toShortString(UUID.randomUUID());
    }

    private static String toShortString(UUID u) {
        return UUIDtoString(u);
    }

    private static String UUIDtoString(UUID u) {
        long mostSigBits = u.getMostSignificantBits();
        long leastSigBits = u.getLeastSignificantBits();
        return digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4) + digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12);
    }

    private static String digits(long val, int digits) {
        long hi = 1L << digits * 2;
        return Long.toString(hi | val & hi - 1L, 36).substring(1);
    }
}
