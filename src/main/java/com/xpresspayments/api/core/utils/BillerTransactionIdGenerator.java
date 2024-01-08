package com.xpresspayments.api.core.utils;

public class BillerTransactionIdGenerator {
    public static long generateTransactionId() {
        return System.currentTimeMillis();
    }
}
