<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - SistemaApp</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        :root { --primary:#0f62fe; --primary-dk:#0043ce; --danger:#da1e28;
                --text:#161616; --text-light:#6f6f6f; --border:#e0e0e0; }
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'IBM Plex Sans', sans-serif; font-size: 14px;
               min-height: 100vh; display: flex; align-items: center; justify-content: center;
               background: linear-gradient(135deg, #161616 0%, #393939 100%); }
        .card { background: #fff; padding: 2.5rem; border-radius: 4px;
                width: 100%; max-width: 380px; box-shadow: 0 8px 32px rgba(0,0,0,.3); }
        h2 { font-size: 1.375rem; font-weight: 600; margin-bottom: .25rem; }
        .sub { color: var(--text-light); font-size: 13px; margin-bottom: 1.5rem; }
        .form-group { margin-bottom: 1rem; }
        label { display: block; font-size: 12px; font-weight: 600; margin-bottom: .35rem; color: var(--text-light); }
        input { width: 100%; padding: .5rem .75rem; border: 1px solid var(--border);
                border-radius: 4px; font-family: inherit; font-size: 14px; }
        input:focus { outline: none; border-color: var(--primary); box-shadow: 0 0 0 2px rgba(15,98,254,.2); }
        .btn { width: 100%; padding: .625rem; border: none; border-radius: 4px;
               background: var(--primary); color: #fff; font-size: 14px; font-weight: 500;
               cursor: pointer; font-family: inherit; margin-top: .5rem; }
        .btn:hover { background: var(--primary-dk); }
        .alert-error { background: #fff1f1; border-left: 4px solid var(--danger);
                       color: #750e13; padding: .75rem 1rem; border-radius: 4px;
                       margin-bottom: 1rem; font-size: 13px; }
        .field-error { display: block; font-size: 11px; color: var(--danger); margin-top: .25rem; }
    </style>
</head>
<body>
<div class="card">
    <h2>Iniciar Sesión</h2>
    <p class="sub">Panel de Administración</p>

    <%-- Errores de accion Struts --%>
    <s:if test="hasActionErrors()">
        <div class="alert-error">
            <s:actionerror/>
        </div>
    </s:if>

    <%-- Formulario Struts 2: action=login.action method=POST --%>
    <s:form action="login" method="post" theme="simple">

        <div class="form-group">
            <label for="username">Usuario</label>
            <s:textfield id="username" name="username" cssClass="form-control"/>
            <s:fielderror fieldName="username" cssClass="field-error"/>
        </div>

        <div class="form-group">
            <label for="password">Contraseña</label>
            <s:password id="password" name="password" cssClass="form-control"/>
            <s:fielderror fieldName="password" cssClass="field-error"/>
        </div>

        <s:submit value="Ingresar" cssClass="btn"/>
    </s:form>
</div>
</body>
</html>
