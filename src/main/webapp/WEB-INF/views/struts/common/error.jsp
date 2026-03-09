<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="ph"><h1>Error</h1></div>
<div class="alert a-err">
    <s:if test="hasActionErrors()"><s:actionerror/></s:if>
    <s:else>Ha ocurrido un error inesperado.</s:else>
</div>
<a href="${pageContext.request.contextPath}/views/producto/listar.action" class="btn btn-sec">&larr; Volver</a>
