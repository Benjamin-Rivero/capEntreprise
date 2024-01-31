<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "Je sais pas trop";
    }
    request.setAttribute("title", title);
%>

<html>
    <head>
        <title>${title}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <link href="${contextPath}/css/main.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <script type="text/javascript" src="${contextPath}/js/main.js"></script>
    </head>
    <body>
        
        <main class="background-img">
            <security:authorize access="isAuthenticated()">
        <div class="navbar">
                <div>
                    <a href="/" class="btn btn-link" link>Accueil</a>
                    <a href="/avis" class="btn btn-link" link>Avis</a>
                    <a href="/jeu" class="btn btn-link" link>Jeux</a>
                </div>

                    <security:authorize access="!isAuthenticated()">
                        <div class="d-flex justify-content-end">
                            <a class="nav-link" href="${contextPath}/register">Register</a>
                        </div>
                        <div class="d-flex justify-content-end">
                            <a class="nav-link" href="${contextPath}/login">Login</a>
                        </div>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <div class="d-flex justify-content-end">
                            <span class="mt-2">Bonjour <security:authentication property="name"/></span>
                            <form method="POST" action="${contextPath}/logout" autocomplete="off">
                                <button type="submit" tabindex="3" class="btn btn-link">Logout</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </security:authorize>

        </div>
    </security:authorize>
        </div>
            <div class="container mt-3">