<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/> - SistemaApp</title>
    <style>
        :root{--pri:#0f62fe;--pri-dk:#0043ce;--sec:#393939;--danger:#da1e28;
              --ok:#198038;--bg:#f4f4f4;--sur:#fff;--bdr:#e0e0e0;--tx:#161616;
              --txl:#6f6f6f;--nh:48px;--r:4px;}
        *,*::before,*::after{box-sizing:border-box;margin:0;padding:0;}
        body{font-family:Arial,sans-serif;font-size:14px;background:var(--bg);color:var(--tx);}
        nav{height:var(--nh);background:var(--sec);color:#fff;display:flex;align-items:center;
            padding:0 1.5rem;gap:2rem;position:sticky;top:0;z-index:100;}
        nav .brand{font-weight:600;font-size:15px;}
        nav a{color:#c6c6c6;text-decoration:none;font-size:13px;padding:0 .75rem;
              height:var(--nh);display:flex;align-items:center;}
        nav a:hover{background:rgba(255,255,255,.1);color:#fff;}
        main{padding:1.5rem;max-width:1100px;margin:0 auto;}
        footer.sf{text-align:center;padding:1.5rem;font-size:12px;color:var(--txl);
                  border-top:1px solid var(--bdr);margin-top:2rem;}
        .ph{display:flex;align-items:center;justify-content:space-between;
            margin-bottom:1.5rem;padding-bottom:.75rem;border-bottom:1px solid var(--bdr);}
        .ph h1{font-size:1.375rem;font-weight:600;}
        .btn{display:inline-flex;align-items:center;padding:.5rem 1.25rem;border:none;
             border-radius:var(--r);font-size:14px;font-weight:500;cursor:pointer;
             text-decoration:none;font-family:inherit;}
        .btn-pri{background:var(--pri);color:#fff;} .btn-pri:hover{background:var(--pri-dk);}
        .btn-sec{background:#e0e0e0;color:var(--tx);} .btn-sec:hover{background:#c6c6c6;}
        .btn-dan{background:var(--danger);color:#fff;}
        .btn-sm{padding:.3rem .75rem;font-size:12px;}
        .alert{padding:.75rem 1rem;border-radius:var(--r);margin-bottom:1rem;font-size:13px;}
        .a-err{background:#fff1f1;border-left:4px solid var(--danger);color:#750e13;}
        .a-ok {background:#defbe6;border-left:4px solid var(--ok);color:#044317;}
        .tw{background:var(--sur);border:1px solid var(--bdr);border-radius:var(--r);overflow:hidden;}
        table{width:100%;border-collapse:collapse;}
        thead tr{background:#e8e8e8;}
        th{padding:.625rem 1rem;text-align:left;font-size:12px;font-weight:600;
           text-transform:uppercase;color:var(--txl);}
        td{padding:.75rem 1rem;border-top:1px solid var(--bdr);font-size:13px;}
        tbody tr:hover{background:#f7f7f7;}
        td.empty{text-align:center;color:var(--txl);padding:2rem;}
        td.ac{display:flex;gap:.5rem;}
        .badge{display:inline-block;padding:.2rem .5rem;border-radius:2px;font-size:11px;font-weight:600;text-transform:uppercase;}
        .b-on{background:#defbe6;color:var(--ok);} .b-off{background:#e8e8e8;color:var(--sec);}
        .fc{background:var(--sur);border:1px solid var(--bdr);border-radius:var(--r);padding:1.5rem;max-width:700px;}
        .fr{display:grid;grid-template-columns:1fr 1fr;gap:1rem;}
        .fg{margin-bottom:1rem;}
        label{display:block;font-size:12px;font-weight:600;margin-bottom:.35rem;color:var(--txl);}
        input[type=text],textarea{width:100%;padding:.5rem .75rem;
            border:1px solid var(--bdr);border-radius:var(--r);font-family:inherit;font-size:14px;}
        input:focus,textarea:focus{outline:none;border-color:var(--pri);}
        textarea{resize:vertical;}
        .fe{display:block;font-size:11px;color:var(--danger);margin-top:.25rem;}
        .fa{display:flex;gap:.75rem;justify-content:flex-end;margin-top:1.5rem;
            padding-top:1rem;border-top:1px solid var(--bdr);}
    </style>
</head>
<body>
<tiles:insertAttribute name="navbar"/>
<main>
<tiles:insertAttribute name="content"/>
</main>
<tiles:insertAttribute name="footer"/>
</body>
</html>
