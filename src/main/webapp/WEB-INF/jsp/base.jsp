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
        <link href="${contextPath}/css/main.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <script type="text/javascript" src="${contextPath}/js/main.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row mt-3">
                <div class="col-4">
                    <a href="/avis" class="btn btn-secondary">Avis</a>
                    <a href="/jeu" class="btn btn-secondary">Jeux</a>
                </div>
                <div class="col-8">
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
            </div>
        </div>
        <div class="container">