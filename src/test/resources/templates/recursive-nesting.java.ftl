
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
                    <@precessChildrenAttribute entityMap subEntity path index + 1 ; nestedParam>
                        <#nested nestedParam>
                    </@precessChildrenAttribute>
                }
            }
        }
    <#elseif index == path?size>
        <#nested paramName>
    </#if>
</#macro>