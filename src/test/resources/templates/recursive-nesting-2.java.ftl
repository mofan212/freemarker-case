<#-- nestedPosition 作为嵌套内容的标识 -->
<@precessChildrenAttribute entityMap "dto" path 0 ; param, subParam, nestedPosition>
    <#-- 标识为 if 时生成下述内容 -->
    <#if nestedPosition == "if">
        ${param}.addError("Error Info");
    <#else >
        <#-- 否则生成下述内容 -->
        ${subParam}.addError("Error Info");
    </#if>
</@precessChildrenAttribute>

<#macro precessChildrenAttribute entityMap paramName path index >
    <#if index lt path?size>
        <#local cur = entityMap[path[index]]
                , curCapName = cur.name?cap_first
                , subEntity = "item" + cur.name?cap_first>
        if (CollectionUtils.isEmpty(${paramName}.get${curCapName}DtoList())) {
            <#-- 嵌套内容标识在定义宏声明 <#nested> 时传入 -->
            <#nested paramName subEntity "if" >
        } else {
            for (${cur.type} ${subEntity} : ${paramName}.get${curCapName}DtoList()) {
                if (${subEntity} != null) {
                    <#-- 递归调用宏时，循环变量一个不能少 -->
                    <@precessChildrenAttribute entityMap subEntity path index + 1 ;  param, subParam, nestedPosition>
                        <#nested param, subParam, nestedPosition>
                    </@precessChildrenAttribute>
                }
            }
        }
    <#elseif index == path?size>
        <#-- 嵌套内容标识在定义宏声明 <#nested> 时传入 -->
        <#nested "" paramName "">
    </#if>
</#macro>