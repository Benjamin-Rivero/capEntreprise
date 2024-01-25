<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

    <h1>Page de ${game.name}</h1>
    <div class="col-6 p-5">
        <p>Publié le ${game.publishedAt}</p>
        <c:if test="${game.moderator != null}">
            <p>Modéré par ${game.moderator.username}</p>
        </c:if>
        <p>${game.description}</p>
    </div>
    <div class="col-6">
        <img src="${game.image}" alt="Pas d'image">
    </div>

<%@ include file="../footer.jsp" %>