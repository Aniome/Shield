package org.shield.service;

import java.util.List;

public class GenerateId {
    public static Long getId(List<Long> listId){
        long i;
        for (i = 0L; i < Long.MAX_VALUE; i++) {
            if (!listId.contains(i)) {
                break;
            }
        }
        return i;
    }
}
