<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${param.titulo} - SistemaApp</title>
    <style>
        :root {
            --primary: #0f62fe; --primary-dk: #0043ce;
            --secondary: #393939; --danger: #da1e28; --success: #198038;
            --bg: #f4f4f4; --surface: #fff; --border: #e0e0e0;
            --text: #161616; --text-light: #6f6f6f;
            --nav-h: 48px; --r: 4px;
        }
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'IBM Plex Sans', Arial, sans-serif; font-size: 14px;
               background: var(--bg); color: var(--text); }
        /* Navbar */
        nav.topbar {
            height: var(--nav-h); background: var(--secondary); color: #fff;
            display: flex; align-items: center; padding: 0 1.5rem; gap: 2rem;
            position: sticky; top: 0; z-index: 100;
        }
        nav.topbar .brand { font-weight: 600; font-size: 15px; }
        nav.topbar a { color: #c6c6c6; text-decoration: none; font-size: 13px;
                        padding: 0 .75rem; height: var(--nav-h); display: flex; align-items: center; }
        nav.topbar a:hover, nav.topbar a.active { background: rgba(255,255,255,.1); color: #fff; }
        nav.topbar .spacer { flex: 1; }
        nav.topbar .user { font-size: 12px; color: #c6c6c6; }
        /* Layout */
        main { padding: 1.5rem; max-width: 1100px; margin: 0 auto; }
        .page-header { display: flex; align-items: center; justify-content: space-between;
                        margin-bottom: 1.5rem; padding-bottom: .75rem; border-bottom: 1px solid var(--border); }
        .page-header h1 { font-size: 1.375rem; font-weight: 600; }
        /* Buttons */
        .btn { display: inline-flex; align-items: center; padding: .5rem 1.25rem;
               border: none; border-radius: var(--r); font-size: 14px; font-weight: 500;
               cursor: pointer; text-decoration: none; font-family: inherit; }
        .btn-primary   { background: var(--primary); color: #fff; }
        .btn-primary:hover { background: var(--primary-dk); }
        .btn-secondary { background: #e0e0e0; color: var(--text); }
        .btn-secondary:hover { background: #c6c6c6; }
        .btn-danger    { background: var(--danger); color: #fff; }
        .btn-sm { padding: .3rem .75rem; font-size: 12px; }
        /* Alerts */
        .alert { padding: .75rem 1rem; border-radius: var(--r); margin-bottom: 1rem; font-size: 13px; }
        .alert-error   { background: #fff1f1; border-left: 4px solid var(--danger); color: #750e13; }
        .alert-success { background: #defbe6; border-left: 4px solid var(--success); color: #044317; }
        /* Table */
        .table-wrap { background: var(--surface); border: 1px solid var(--border);
                      border-radius: var(--r); overflow: hidden; }
        table { width: 100%; border-collapse: collapse; }
        thead tr { background: #e8e8e8; }
        th { padding: .625rem 1rem; text-align: left; font-size: 12px; font-weight: 600;
             text-transform: uppercase; color: var(--text-light); }
        td { padding: .75rem 1rem; border-top: 1px solid var(--border); font-size: 13px; }
        tbody tr:hover { background: #f7f7f7; }
        td.empty { text-align: center; color: var(--text-light); padding: 2rem; }
        td.actions { display: flex; gap: .5rem; }
        /* Badge */
        .badge { display: inline-block; padding: .2rem .5rem; border-radius: 2px;
                 font-size: 11px; font-weight: 600; text-transform: uppercase; }
        .badge-on  { background: #defbe6; color: var(--success); }
        .badge-off { background: #e8e8e8; color: var(--secondary); }
        /* Form */
        .form-card { background: var(--surface); border: 1px solid var(--border);
                     border-radius: var(--r); padding: 1.5rem; max-width: 700px; }
        .form-row  { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
        .form-group { margin-bottom: 1rem; }
        label { display: block; font-size: 12px; font-weight: 600; margin-bottom: .35rem;
                color: var(--text-light); }
        input[type=text], input[type=number], textarea, select {
            width: 100%; padding: .5rem .75rem; border: 1px solid var(--border);
            border-radius: var(--r); font-family: inherit; font-size: 14px; }
        input:focus, textarea:focus { outline: none; border-color: var(--primary);
            box-shadow: 0 0 0 2px rgba(15,98,254,.2); }
        textarea { resize: vertical; }
        .field-error { display: block; font-size: 11px; color: var(--danger); margin-top: .25rem; }
        .form-actions { display: flex; gap: .75rem; justify-content: flex-end;
                        margin-top: 1.5rem; padding-top: 1rem; border-top: 1px solid var(--border); }
    </style>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>

<c:if test="${not empty sessionScope.usuarioWeb}">
<nav class="topbar">
    <span class="brand">&#9643; SistemaApp</span>
    <a href="${pageContext.request.contextPath}/views/producto/listar.action">Productos</a>
    <div class="spacer"></div>
    <span class="user">${sessionScope.usuarioWeb}</span>
    <a href="${pageContext.request.contextPath}/views/auth/logout.action">Salir</a>
</nav>
</c:if>

<main>
    <%-- Mensajes flash de sesion --%>
    <c:if test="${not empty sessionScope.mensajeExito}">
        <div class="alert alert-success">${sessionScope.mensajeExito}</div>
        <c:remove var="mensajeExito" scope="session"/>
    </c:if>
