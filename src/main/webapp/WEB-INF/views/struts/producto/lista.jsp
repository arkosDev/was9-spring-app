<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ph">
    <h1>Productos</h1>
    <a href="${pageContext.request.contextPath}/views/producto/nuevo.action" class="btn btn-pri">+ Nuevo</a>
</div>

<s:if test="hasActionErrors()">
    <div class="alert a-err"><s:actionerror/></div>
</s:if>

<div class="tw">
    <table>
        <thead>
            <tr>
                <th>ID</th><th>Nombre</th><th>Categoría</th>
                <th>Precio</th><th>Stock</th><th>Estado</th><th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty productos}">
                    <tr><td colspan="7" class="empty">No hay productos</td></tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="p" items="${productos}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.nombre}</td>
                            <td>${not empty p.categoria ? p.categoria : '-'}</td>
                            <td>$&nbsp;<fmt:formatNumber value="${p.precio}" pattern="#,##0.00"/></td>
                            <td><span class="badge ${p.stock > 0 ? 'b-on' : 'b-off'}">${p.stock}</span></td>
                            <td><span class="badge ${p.activo ? 'b-on' : 'b-off'}">${p.activo ? 'Activo' : 'Inactivo'}</span></td>
                            <td class="ac">
                                <a href="${pageContext.request.contextPath}/views/producto/editar.action?id=${p.id}"
                                   class="btn btn-sm btn-sec">Editar</a>
                                <a href="${pageContext.request.contextPath}/views/producto/eliminar.action?id=${p.id}"
                                   class="btn btn-sm btn-dan"
                                   onclick="return confirm('¿Eliminar ${p.nombre}?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>
