
<@precessChildrenAttribute entityMap "dto" path 0 ; nestedParam>
    ${nestedParam}.addError("Error Info");
</@precessChildrenAttribute>

<#macro precessChildrenAttribute entityMap paramName path index >
    <#if index lt path?size>
        <#local cur = entityMap[path[index]]
                , curCapName = cur.name?cap_first
                , subEntity = "item" + cur.name?cap_first>
        if (${paramName}.get${curCapName}DtoList() != null) {
            for(${cur.type} ${subEntity} : ${paramName}.get${curCapName}DtoList()) {
                if (${subEntity} != null) {
                    <#-- 递归调用宏，调用时声明循环变量 -->
                    <@precessChildrenAttribute entityMap subEntity path index + 1 ; nestedParam>
                        <#-- 嵌套内容使用调用宏时声明的循环变量 -->
                        <#nested nestedParam>
                    </@precessChildrenAttribute>
                }
            }
        }
    <#elseif index == path?size>
        <#-- 最后调用的嵌套内容，传入实际的参数 -->
        <#nested paramName>
    </#if>
</#macro>