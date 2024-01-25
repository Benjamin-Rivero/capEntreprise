<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Review de ${review.player.username}"/>
<jsp:include flush="true" page="../base.jsp"/>

    <div>
        <h1>Avis sur ${review.game.name} par ${review.player.username}</h1>
        <p>${review.description}</p>
        <p>${review.rating}/20</p>
        <c:if test="${review.moderator != null}">
            <p>Modéré par ${review.moderator.username}</p>
        </c:if>
    </div>

<%@ include file="../footer.jsp" %>