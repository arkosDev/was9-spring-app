<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/struts/common/layout-header.jsp" %>

<div class="page-header">
    <h1>${not empty producto.id ? 'Editar Producto' : 'Nuevo Producto'}</h1>
    <a href="${pageContext.request.contextPath}/views/producto/listar.action"
       class="btn btn-secondary">&larr; Volver</a>
</div>

<%-- Errores globales de accion --%>
<s:if test="hasActionErrors()">
    <div class="alert alert-error"><s:actionerror/></div>
</s:if>

<div class="form-card">
    <%--
        Struts 2: <s:form> genera el <form> HTML apuntando al action "guardar"
        Los <s:textfield> bindean directamente con los campos del ProductoAction
        usando OGNL: "producto.nombre" -> action.getProducto().getNombre()
    --%>
    <s:form action="guardar" method="post" theme="simple">

        <%-- ID oculto para diferenciar crear vs actualizar --%>
        <s:hidden name="producto.id"/>

        <div class="form-row">
            <div class="form-group">
                <label for="nombre">Nombre *</label>
                <s:textfield id="nombre" name="producto.nombre" cssClass="form-control"/>
                <s:fielderror fieldName="producto.nombre" cssClass="field-error"/>
            </div>

            <div class="form-group">
                <label for="categoria">Categoría</label>
                <s:textfield id="categoria" name="producto.categoria" cssClass="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <s:textarea id="descripcion" name="producto.descripcion" rows="3" cssClass="form-control"/>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="precio">Precio *</label>
                <s:textfield id="precio" name="producto.precio" cssClass="form-control"/>
                <s:fielderror fieldName="producto.precio" cssClass="field-error"/>
            </div>

            <div class="form-group">
                <label for="stock">Stock *</label>
                <s:textfield id="stock" name="producto.stock" cssClass="form-control"/>
                <s:fielderror fieldName="producto.stock" cssClass="field-error"/>
            </div>
        </div>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/views/producto/listar.action"
               class="btn btn-secondary">Cancelar</a>
            <s:submit value="${not empty producto.id ? 'Actualizar' : 'Crear'}"
                      cssClass="btn btn-primary"/>
        </div>

    </s:form>
</div>

<%@ include file="/WEB-INF/views/struts/common/layout-footer.jsp" %>
