<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://www.mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${className}Mapper">
    <!-- 根据Id获取数据 -->
    <select id="get${className}ById" parameterType="DataMap" resultType="DataMap">
        SELECT
            ${classNameLower}.${classNameUpper}_ID,
            <#list datas as data>
            <#if data.isDic == '是'>
            ${classNameLower}.${data.propertyNameUpper},
            dic${data_index}.NAME AS ${data.propertyNameUpper}_VALUE<#if data_has_next>,</#if>
            <#else>
            ${classNameLower}.${data.propertyNameUpper}<#if data_has_next>,</#if>
            </#if>
            </#list>
        FROM
        ${tableFront}${classNameUpper} ${classNameLower}
        <#list datas as data>
            <#if data.isDic == '是'>
        LEFT JOIN SYS_DICTIONARIES dic${data_index} ON dic${data_index}.DIC_ID = ${classNameLower}.${data.propertyNameUpper}
            </#if>
        </#list>
        WHERE ${classNameUpper}_ID = ${r"#{"}${classNameUpper}_ID${r"}"}
    </select>

    <!-- 获取${showName}分页数据 -->
    <select id="get${className}s" parameterType="Page" resultType="DataMap">
        SELECT
        ${classNameLower}.${classNameUpper}_ID,
        <#list datas as data>
            <#if data.isDic == '是'>
        ${classNameLower}.${data.propertyNameUpper},
            dic${data_index}.NAME AS ${data.propertyNameUpper}_VALUE<#if data_has_next>,</#if>
            <#else>
        ${classNameLower}.${data.propertyNameUpper}<#if data_has_next>,</#if>
            </#if>
        </#list>
        FROM
        ${tableFront}${classNameUpper} ${classNameLower}
        <#list datas as data>
            <#if data.isDic == '是'>
        LEFT JOIN SYS_DICTIONARIES dic${data_index} ON dic${data_index}.DIC_ID = ${classNameLower}.${data.propertyNameUpper}
            </#if>
        </#list>
        WHERE 1 = 1
        <#list datas as data>
            <#if data.isSearch == '是'>
        ${r"<if test='"}params.${data.propertyNameUpper}${r" != null'>"}
            <#if data.searchCondition == 'like'>
            AND ${classNameLower}.${data.propertyNameUpper} LIKE ${r"CONCAT(CONCAT('%', #{"}params.${data.propertyNameUpper}${r"}), '%')"}
            </#if>
            <#if data.searchCondition == '='>
            AND ${classNameLower}.${data.propertyNameUpper} = ${r"#{"}params.${data.propertyNameUpper}${r"}"}
            </#if>
            <#if data.searchCondition == '>'>
            AND ${classNameLower}.${data.propertyNameUpper} ${r"&gt;"} ${r"#{"}params.${data.propertyNameUpper}${r"}"}
            </#if>
            <#if data.searchCondition == '<'>
            AND ${classNameLower}.${data.propertyNameUpper} ${r"&lt;"} ${r"#{"}params.${data.propertyNameUpper}${r"}"}
            </#if>
            <#if data.searchCondition == '<='>
            AND ${classNameLower}.${data.propertyNameUpper} ${r"&lt;"}= ${r"#{"}params.${data.propertyNameUpper}${r"}"}
            </#if>
            <#if data.searchCondition == '>='>
            AND ${classNameLower}.${data.propertyNameUpper} ${r"&gt;"}= ${r"#{"}params.${data.propertyNameUpper}${r"}"}
            </#if>
        ${r"</if>"}
            </#if>
        </#list>
    </select>

    <!-- 批量删除${showName} -->
    <delete id="batchRemoves" parameterType="java.util.List">
        DELETE FROM
            ${tableFront}${classNameUpper}
        WHERE
            ${classNameUpper}_ID
        IN
        ${r"<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>"}
        ${r"#{item}"}
        ${r"</foreach>"}
    </delete>

    <!-- 新增${showName} -->
    <insert id="add${className}" parameterType="DataMap">
        INSERT INTO
            ${tableFront}${classNameUpper}
        (
        ${classNameUpper}_ID,
        <#list datas as data>
        ${data.propertyNameUpper}<#if data_has_next>,</#if>
        </#list>
        )
        VALUES
        (
        ${r"#{"}${classNameUpper}_ID${r"}"},
        <#list datas as data>
        ${r"#{"}${data.propertyNameUpper}${r"}"}<#if data_has_next>,</#if>
        </#list>
        )
    </insert>

    <!-- 编辑删除${showName} -->
    <update id="edit${className}" parameterType="DataMap">
        UPDATE
            ${tableFront}${classNameUpper}
        SET
            <#list datas as data>
                <#if data.isFront == '是'>
                    <#if data.isRequired == '是'>
            ${data.propertyNameUpper} = ${r"#{"}${data.propertyNameUpper}${r"}"}<#if data_index <= maxIsFrontIndex>,</#if>
                    <#else>
            ${r"<if test='"}${data.propertyNameUpper}${r" != null'>"}
            ${data.propertyNameUpper} = ${r"#{"}${data.propertyNameUpper}${r"}"}<#if data_index <= maxIsFrontIndex>,</#if>
            ${r"</if>"}
                    </#if>
                </#if>
            </#list>
        WHERE
            ${classNameUpper}_ID = ${r"#{"}${classNameUpper}_ID${r"}"}
    </update>
    <#if (maxIsNotFrontIndex > -1)>
    <!-- 编辑${showName}中的非前台录入属性 -->
    <update id="editNotFrontOf${className}" parameterType="DataMap">
        UPDATE
            ${tableFront}${classNameUpper}
        SET
            <#list datas as data>
                <#if data.isFront == '否'>
            ${r"<if test='"}${data.propertyNameUpper}${r" != null'>"}
            ${data.propertyNameUpper} = ${r"#{"}${data.propertyNameUpper}${r"}"}<#if data_index <= maxIsNotFrontIndex>,</#if>
            ${r"</if>"}
                </#if>
            </#list>
        WHERE
            ${classNameUpper}_ID = ${r"#{"}${classNameUpper}_ID${r"}"}
    </update>
    </#if>
</mapper>