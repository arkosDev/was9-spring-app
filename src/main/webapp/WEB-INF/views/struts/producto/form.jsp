<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/views/struts/common/layout-header.jsp" %>

<div class="ph">
    <h1><s:if test="producto.id != null">Editar Producto</s:if><s:else>Nuevo Producto</s:else></h1>
    <a href="${pageContext.request.contextPath}/views/producto/listar.action" class="btn btn-sec">&larr; Volver</a>
</div>

<s:if test="hasActionErrors()">
    <div class="alert a-err"><s:actionerror/></div>
</s:if>

<div class="fc">
    <s:form action="guardar" method="post" theme="simple">

        <s:hidden name="producto.id"/>

        <div class="fr">
            <div class="fg">
                <label>Nombre *</label>
                <s:textfield name="producto.nombre"/>
                <s:fielderror fieldName="producto.nombre" cssClass="fe"/>
            </div>
            <div class="fg">
                <label>Categoría</label>
                <s:textfield name="producto.categoria"/>
            </div>
        </div>

        <div class="fg">
            <label>Descripción</label>
            <s:textarea name="producto.descripcion" rows="3"/>
        </div>

        <div class="fr">
            <div class="fg">
                <label>Precio *</label>
                <s:textfield name="producto.precio"/>
                <s:fielderror fieldName="producto.precio" cssClass="fe"/>
            </div>
            <div class="fg">
                <label>Stock *</label>
                <s:textfield name="producto.stock"/>
                <s:fielderror fieldName="producto.stock" cssClass="fe"/>
            </div>
        </div>

        <div class="fa">
            <a href="${pageContext.request.contextPath}/views/producto/listar.action" class="btn btn-sec">Cancelar</a>
            <s:submit cssClass="btn btn-pri">
                <s:if test="producto.id != null">Actualizar</s:if><s:else>Crear</s:else>
            </s:submit>
        </div>

    </s:form>
</div>

<%@ include file="/WEB-INF/views/struts/common/layout-footer.jsp" %>
