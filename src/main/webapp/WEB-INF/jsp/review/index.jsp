<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste des jeux"/>
<jsp:include flush="true" page="../base.jsp"/>

    ${userLogged.getClass().name}
    <div class="d-flex justify-content-between">
    <div class="d-flex">
        <!-- Label à afficher -->
        <c:set var="label" scope="request" value="Date"/>
        <!-- Sur quelle propriété de l'objet on souhaite trier -->
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Note"/>
        <c:set var="sortable" value="rating"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Jeu"/>
        <c:set var="sortable" value="game.name"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Joueur"/>
        <c:set var="sortable" value="player.username"/>
        <%@ include file="../component/sortable.jsp" %>

        <span class="mt-auto mb-2">
                <a href="${currentUrl}" class="btn-link">
                    Reset
                </a>
        </span>
    </div>
    <div  class="mt-auto mb-2">
            <span>
                page ${reviews.number + 1} sur ${reviews.totalPages}
            </span>
        </div>
    </div>
    <security:authorize access="hasRole('PLAYER')">
        <a href="${s:mvcUrl('AppReview#new').build()}" class="btn btn-secondary w-25">Nouvel avis</a>
    </security:authorize>
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
                        <a class="btn btn-secondary mt-3 mb-3 d-block w-50" role="button" href="${s:mvcUrl('AppReview#show').arg(0,review.id).build()}">Voir</a>
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




<%@ include file="../footer.jsp" %>