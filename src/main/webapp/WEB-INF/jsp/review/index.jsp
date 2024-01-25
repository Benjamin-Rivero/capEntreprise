<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste des jeux"/>
<jsp:include flush="true" page="../base.jsp"/>

    ${userLogged.getClass().name}
    <div>
        <p>
            page ${reviews.number + 1} sur ${reviews.totalPages}
        </p>
    </div>
    <table class="table table-striped">
        <thead>
            <td>Date d'envoi</td>
            <td>Jeu</td>
            <td>Pseudo</td>
            <td>Note</td>
            <td>Statut</td>
            <td>Operations</td>
        </thead>
        <tbody>
            <c:forEach items="${reviews.content}" var="review">
            <c:if test="${userLogged.admin || userLogged.equals(review.player) || review.moderator != null}">
                <tr>
                    <td>Le ${dateUtils.convertLocalDateTimeToFormat(review.createdAt,"dd/MM/yyyy")}</td>
                    <td>${review.game.name}</td>
                    <td>${review.player.username}</td>
                    <td>${review.rating}</td>
                    <td>
                        <c:if test="${review.moderator!=null}">
                            Modéré par ${review.moderator.username}
                        </c:if>
                        <c:if test="${review.moderator==null}">
                            A modérer
                        </c:if>
                    </td>
                    <td>
                        <a class="btn btn-secondary mt-3 mb-3 w-50" role="button" href="${s:mvcUrl('AppReview#show').arg(0,review.id).build()}">Voir</a>
                        <security:authorize access="hasRole('MODERATOR')">
                            <c:if test="${review.moderator==null}">
                                <a class="btn btn-success w-50" href="/avis/${review.id}/validate">Valider</a>
                                <a class="btn btn-danger w-50" href="/avis/${review.id}/refuse">Refuser</a>
                            </c:if>
                        </security:authorize>
                    </td>
                </tr>
            </c:if>
            </c:forEach>
        </tbody>
    </table>


    <c:set var="page" scope="request" value="${reviews}"/>
    <%@ include file="../component/pagination.jsp" %>


    <a href="${s:mvcUrl('AppReview#new').build()}">Nouvel avis</a>
    <a href="${s:mvcUrl('AppGame#list').build()}">Liste des jeux</a>

<%@ include file="../footer.jsp" %>