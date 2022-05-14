package com.philips.university.util;

import org.springframework.data.domain.Sort;

public abstract class SortUtil {

    public static Sort.Direction sortDirection(String direction) {
        return direction.equals(Sort.Direction.DESC.toString()) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
