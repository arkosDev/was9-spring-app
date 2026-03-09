<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="ph">
    <h1><s:if test="form.id != null">Editar Producto</s:if><s:else>Nuevo Producto</s:else></h1>
    <a href="${pageContext.request.contextPath}/views/producto/listar.action" class="btn btn-sec">&larr; Volver</a>
</div>

<s:if test="hasActionErrors()">
    <div class="alert a-err"><s:actionerror/></div>
</s:if>

<div class="fc">
    <s:form action="guardar" method="post" theme="simple">

        <s:hidden name="form.id"/>

        <div class="fr">
            <div class="fg">
                <label>Nombre *</label>
                <s:textfield name="form.nombre"/>
                <s:fielderror fieldName="form.nombre" cssClass="fe"/>
            </div>
            <div class="fg">
                <label>Categoría</label>
                <s:textfield name="form.categoria"/>
            </div>
        </div>

        <div class="fg">
            <label>Descripción</label>
            <s:textarea name="form.descripcion" rows="3"/>
        </div>

        <div class="fr">
            <div class="fg">
                <label>Precio *</label>
                <s:textfield name="form.precio"/>
                <s:fielderror fieldName="form.precio" cssClass="fe"/>
            </div>
            <div class="fg">
                <label>Stock *</label>
                <s:textfield name="form.stock"/>
                <s:fielderror fieldName="form.stock" cssClass="fe"/>
            </div>
        </div>

        <div class="fa">
            <a href="${pageContext.request.contextPath}/views/producto/listar.action" class="btn btn-sec">Cancelar</a>
            <s:submit cssClass="btn btn-pri">
                <s:if test="form.id != null">Actualizar</s:if><s:else>Crear</s:else>
            </s:submit>
        </div>

    </s:form>
</div>
