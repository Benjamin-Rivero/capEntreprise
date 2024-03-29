<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste des avis"/>
<jsp:include flush="true" page="../base.jsp"/>
<script type="text/javascript" src="/js/review/filter.js"></script>


    <div class="review-table">
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
                <a href="/avis" class="btn-link">
                    Reset
                </a>
            </span>
            <div class="d-flex mt-4 mx-5">
                <input type="text"
                       class="form-control"
                       placeholder="Search"
                       data-filter
                >
                <a class="mt-2 ms-1">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </a>
            </div>
            <security:authorize access="hasRole('MODERATOR')">
                <div class="d-flex mt-4 mx-4">
                    <div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" btn-selector>
                        Tous les commentaires
                      </button>
                      <ul class="dropdown-menu bg-dark">
                        <li><a class="dropdown-item text-warning" href="/avis" moderationFilter>Tous les commentaires</a></li>
                        <li><a class="dropdown-item text-bg-dark" href="/avis?moderation=1" moderationFilter>Non modérés</a></li>
                        <li><a class="dropdown-item text-bg-dark" href="/avis?moderation=2" moderationFilter>Modérés</a></li>
                      </ul>
                    </div>
                </div>
            </security:authorize>
        </div>
        <div  class="mt-auto mb-2">
            <span>
                page ${reviews.number + 1}/${reviews.totalPages}
            </span>
        </div>
    </div>
    <security:authorize access="hasRole('PLAYER')">
        <a href="${s:mvcUrl('AppReview#new').build()}" class="btn btn-secondary w-10 my-2 align-end">Nouvel avis</a>
    </security:authorize>
    <table class="table table-striped" myTable>
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
                <tr>
                    <td>Le ${dateUtils.convertLocalDateTimeToFormat(review.createdAt,"dd/MM/yyyy")}</td>
                    <td><a class="link-if" href="/jeu/${review.game.id}">${review.game.name}</a></td>
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
                        <div class="d-flex">
                            <a class="btn btn-link" role="button" href="${s:mvcUrl('AppReview#show').arg(0,review.id).build()}">
                                <i class="fa-solid fa-eye fa-2x"></i>
                            </a>
                            <security:authorize access="hasRole('MODERATOR')">
                                <c:if test="${review.moderator==null}">
                                    <a class="btn btn-link mx-2" href="/avis/${review.id}/validate">
                                        <i class="fa-solid fa-circle-check fa-2x rating-20"></i>
                                    </a>
                                    <a class="btn btn-link" href="/avis/${review.id}/refuse">
                                        <i class="fa-solid fa-circle-xmark fa-2x rating-5"></i>
                                    </a>
                                </c:if>
                            </security:authorize>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div>
        <a href="${UrlRoute.URL_EXPORT}" class="btn btn-link">
            <i class="fa-solid fa-file-excel me-1"></i>
            Télécharger export Excel
        </a>
    </div>
    <div class="d-flex justify-content-center">
        <span class="mt-2">Affichage des éléments ${reviews.size*reviews.number+1} à ${(reviews.size*reviews.number)+reviews.numberOfElements} sur ${reviews.totalElements}</span>
    </div>
    </div>

    <c:set var="page" scope="request" value="${reviews}"/>
    <%@ include file="../component/pagination.jsp" %>



<%@ include file="../footer.jsp" %>