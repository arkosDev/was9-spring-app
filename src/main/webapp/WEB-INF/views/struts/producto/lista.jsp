<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/struts/common/layout-header.jsp" %>

<div class="page-header">
    <h1>Productos</h1>
    <a href="${pageContext.request.contextPath}/views/producto/nuevo.action"
       class="btn btn-primary">+ Nuevo Producto</a>
</div>

<%-- Errores de accion --%>
<s:if test="hasActionErrors()">
    <div class="alert alert-error"><s:actionerror/></div>
</s:if>

<div class="table-wrap">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty productos}">
                    <tr><td colspan="7" class="empty">No hay productos registrados</td></tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="p" items="${productos}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.nombre}</td>
                            <td>${not empty p.categoria ? p.categoria : '-'}</td>
                            <td>$&nbsp;<fmt:formatNumber value="${p.precio}" pattern="#,##0.00"/></td>
                            <td>
                                <span class="badge ${p.stock > 0 ? 'badge-on' : 'badge-off'}">
                                    ${p.stock}
                                </span>
                            </td>
                            <td>
                                <span class="badge ${p.activo ? 'badge-on' : 'badge-off'}">
                                    ${p.activo ? 'Activo' : 'Inactivo'}
                                </span>
                            </td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/views/producto/editar.action?id=${p.id}"
                                   class="btn btn-sm btn-secondary">Editar</a>

                                <a href="${pageContext.request.contextPath}/views/producto/eliminar.action?id=${p.id}"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('¿Eliminar producto ${p.nombre}?')">
                                    Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/views/struts/common/layout-footer.jsp" %>
