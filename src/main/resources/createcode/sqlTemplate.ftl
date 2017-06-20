SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ${tableFront}${classNameUpper}
-- ----------------------------
DROP TABLE IF EXISTS ${r"`"}${tableFront}${classNameUpper}${r"`"};
CREATE TABLE ${r"`"}${tableFront}${classNameUpper}${r"`"} (
${r"`"}${classNameUpper}_ID${r"`"} varchar(255) NOT NULL,
<#list datas as data>
    <#if data.dataType == 'String'>
${r"`"}${data.propertyNameUpper}${r"`"} varchar(255) <#if data.isRequired == '是'>NOT<#else>DEFAULT</#if> NULL COMMENT '${data.remark}',
    </#if>
    <#if data.dataType == 'Integer'>
${r"`"}${data.propertyNameUpper}${r"`"} int(11) <#if data.isRequired == '是'>NOT<#else>DEFAULT</#if> NULL COMMENT '${data.remark}',
    </#if>
    <#if data.dataType == 'Double'>
${r"`"}${data.propertyNameUpper}${r"`"} double <#if data.isRequired == '是'>NOT<#else>DEFAULT</#if> NULL COMMENT '${data.remark}',
    </#if>
    <#if data.dataType == 'Date'>
${r"`"}${data.propertyNameUpper}${r"`"} date <#if data.isRequired == '是'>NOT<#else>DEFAULT</#if> NULL COMMENT '${data.remark}',
    </#if>
    <#if data.dataType == 'Datetime'>
${r"`"}${data.propertyNameUpper}${r"`"} datetime <#if data.isRequired == '是'>NOT<#else>DEFAULT</#if> NULL COMMENT '${data.remark}',
    </#if>
    <#if data.dataType == 'Boolean'>
${r"`"}${data.propertyNameUpper}${r"`"} tinyint(1) <#if data.isRequired == '是'>NOT<#else>DEFAULT</#if> NULL COMMENT '${data.remark}',
    </#if>
</#list>
PRIMARY KEY (${r"`"}${classNameUpper}_ID${r"`"})
) ENGINE=InnoDB DEFAULT CHARSET=utf8;