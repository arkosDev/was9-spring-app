<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="layout-header.jsp" %>

<div class="page-header">
    <h1>Error</h1>
</div>

<div class="alert alert-error">
    <s:if test="hasActionErrors()">
        <s:actionerror/>
    </s:if>
    <s:else>
        Ha ocurrido un error inesperado. Por favor intente más tarde.
    </s:else>
</div>

<a href="${pageContext.request.contextPath}/views/producto/listar.action" class="btn btn-secondary">
    &larr; Volver al inicio
</a>

<%@ include file="layout-footer.jsp" %>
