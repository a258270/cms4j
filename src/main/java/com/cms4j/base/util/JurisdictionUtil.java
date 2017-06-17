package com.cms4j.base.util;

import java.math.BigInteger;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/5
 */
public class JurisdictionUtil {

    public static final String QX = "QX";//权限的key值
    public static final String ADD_QX = "ADD_QX";//增权限
    public static final String DEL_QX = "DEL_QX";//删权限
    public static final String EDIT_QX = "EDIT_QX";//改权限
    public static final String QUERY_QX = "QUERY_QX";//查权限

    /**
     * 判断是否有该权限
     * 权限类型共有4种，详情查看本类种四个static变量
     * @param jurisdictionType 权限类型
     * @param menu_id 菜单id
     * @return
     */
    public static boolean hasJurisdiction(String jurisdictionType, String menu_id){
        DataMap role = SessionUtil.getRoleFromSession();
        if(role == null)
            return false;
        boolean isAdmin = Const.SUADMIN.equals(role.getString("ROLE_ID"));
        if(isAdmin)
            return true;

        String queryJurisdiction = role.getString(jurisdictionType);

        return queryJurisdiction(queryJurisdiction, menu_id);
    }

    public static boolean queryJurisdiction(String jurisdictionStr, String menu_id) {
        if(menu_id == null)
            return false;

        try {
            return RightsHelper.testRights(jurisdictionStr, menu_id);
        }
        catch (Exception e){
            return false;
        }
    }

    public static BigInteger setJurisdiction(String[] rights) {
        return RightsHelper.sumRights(rights);
    }


}
