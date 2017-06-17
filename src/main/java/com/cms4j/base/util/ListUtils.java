package com.cms4j.base.util;

import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/14.
 */
public class ListUtils {

    public static boolean isEmptyList(List list) {
        if(list != null && list.size() > 0)
            return false;

        return true;
    }
}
