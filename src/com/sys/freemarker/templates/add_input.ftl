<#assign i = 0>
<tr>
<#list params as model>
	<#assign i = i+1>
	<#if i  = 1>
	<tr>
	</#if>
	
		<td class="tdEditLabel">${model.label}</td>
		<td class="tdEditContent">
			<#if model.type == "TEXTAREA" >
				<textarea rows="5" cols="30" name="${model.properName}" >${model.value}</textarea>
				<#else>
					<input type="${model.type}" name="${model.properName}" value="${model.value}" 
						<#list model.conditions as con>
							${con}="true"
						</#list>
						<#if model.type != "FILE" >
							maxLength=${model.maxLength}
						</#if>
						<#if model.type != "FILE" >
							label=${model.label}
						</#if>
					/>
			</#if>
		</td>
	<#if i = 2 || !field_has_next>
	</tr>
	<#assign i = 0>
	</#if>
</#list>

