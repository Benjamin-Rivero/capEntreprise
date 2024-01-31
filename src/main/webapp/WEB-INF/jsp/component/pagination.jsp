<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${scroll==null}">
    <c:set var="scroll" value=""/>
</c:if>
<core:set var="currentPage" value="${page.number + 1}"/>
<div class="navigation d-flex justify-content-center my-4">
<div class="navigation d-flex justify-content-center my-4 me-5">
    <div class="pagination pagination-div">
        <core:if test="${!page.first}">
            <core:set var="firstPage" value="page=1"/>
            <%-- currentUrl => http://localhost:8080/ (l'url de la page courante)--%>
            <%-- currentQuery => ?sort=moderator,desc (les queryParam actuels) --%>
            <%-- firstPage => page=1 (les queryParam à ajouter) --%>
            <a class="first" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, firstPage)}${scroll}">
                &lt;&lt;
            </a>
        </core:if>
        <core:if test="${page.hasPrevious()}">
            <core:set var="previousPage" value="page=${(currentPage - 1)}"/>
            <a class="previous" rel="prev" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, previousPage)}${scroll}">
                &lt;
            </a>
        </core:if>
        <span class="current">${currentPage}</span>
        <core:if test="${page.hasNext()}">
            <core:set var="nextPage" value="page=${(currentPage + 1)}"/>
            <a class="next" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, nextPage)}${scroll}">
                &gt;
            </a>
        </core:if>
        <core:if test="${!page.last}">
            <core:set var="lastPage" value="page=${page.totalPages}"/>
            <a class="last" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, lastPage)}${scroll}">
                &gt;&gt;
            </a>
        </core:if>