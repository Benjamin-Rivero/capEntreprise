<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%-- Importe l'objet UrlRoute pour pouvoir l'utiliser partout dans les JSP --%>
<%@ page import="fr.benjamin.cap_entreprise.mapping.UrlRoute" %>

<%-- Imports nécessaires pour récupérer le UserService dans les JSP --%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="fr.benjamin.cap_entreprise.service.UserService" %>
<%@ page import="fr.benjamin.cap_entreprise.entity.User" %>
<%@ page import="fr.benjamin.cap_entreprise.entity.Player" %>
<%@ page import="fr.benjamin.cap_entreprise.entity.Moderator" %>
<%@ page import="fr.benjamin.cap_entreprise.utils.DateUtils" %>

<%--&lt;%&ndash; Renomme de manière plus simple le "pageContext.request.contextPath" &ndash;%&gt;--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
    String path = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
    Object query = request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);

    request.setAttribute("currentUrl", path);
    WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
    if (ctx != null) {
        UserService userService = ctx.getBean(UserService.class);
        if (request.getUserPrincipal() != null) {
            User user = userService.findByUsername(request.getUserPrincipal().getName());
            if(user instanceof Player){
                user = (Player)userService.findByUsername(request.getUserPrincipal().getName());
            }
            if(user instanceof Moderator){
                user = (Moderator)userService.findByUsername(request.getUserPrincipal().getName());
            }
            request.setAttribute("userLogged", user);
            request.setAttribute("dateUtils", ctx.getBean(DateUtils.class));
        }
    }
%>
