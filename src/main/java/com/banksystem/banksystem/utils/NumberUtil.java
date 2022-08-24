package com.banksystem.banksystem.utils;

import org.apache.commons.lang3.StringUtils;

public class NumberUtil {

    public static boolean isNumber(String value) {
        return StringUtils.isNotBlank(value) && value.matches("\\d+");
    }

}
