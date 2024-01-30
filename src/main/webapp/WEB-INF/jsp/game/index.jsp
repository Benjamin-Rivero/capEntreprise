<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>
<script type="text/javascript" src="/js/game/hoverFocus.js"></script>

    <img class="game-index-bg-img">
    <h1>Index des jeux</h1>

    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a href="${s:mvcUrl('AppGame#new').build()}" class="btn btn-secondary w-25">Ajouter un jeu</a>
    </security:authorize>
    <div class="d-flex">
            <!-- Label à afficher -->
            <c:set var="label" scope="request" value="Date"/>
            <!-- Sur quelle propriété de l'objet on souhaite trier -->
            <c:set var="sortable" value="publishedAt"/>
            <%@ include file="../component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Nom"/>
            <c:set var="sortable" value="name"/>
            <%@ include file="../component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Editeur"/>
            <c:set var="sortable" value="publisher.name"/>
            <%@ include file="../component/sortable.jsp" %>



            <span class="mt-auto mb-2">
                    <a href="/jeu" class="btn-link">
                        <i class="fa-solid fa-filter-circle-xmark"></i>
                    </a>
            </span>
        </div>

    <div class="row d-flex justify-content-center ms-4">
    <c:forEach items="${games.content}" var="game">
        <div class="game-card card col-md-3 col-sm-12 bg-dark my-3" not-hovered>
            <a href="/jeu/${game.id}">
            <img src="${game.image}" class="card-img-top img-fluid card-img mt-2" alt="Image needed">
            </a>
            <div class="card-body bg-dark my-auto">
                <a href="/jeu/${game.id}" class="link-if"><h5 class="card-title">${game.name}</h5></a>
                <p class="card-text">${game.publisher.name}</p>
                <security:authorize access="hasRole('MODERATOR')">
                    <a href="/jeu/editer/${game.id}" class="btn btn-game"><i class="fa-solid fa-pen"></i></a>
                    <a href="/jeu/${game.id}/upload" class="btn btn-game"><i class="fa-solid fa-upload"></i></a>
                    <a href="/jeu/${game.id}/supprimer" class="btn btn-game"><i class="fa-solid fa-trash-can"></i></a>
                </security:authorize>
            </div>
        </div>
        <div class="col-md-1"></div>
    </c:forEach>
    </div>

    <c:set var="page" scope="request" value="${games}"/>
    <%@ include file="../component/pagination.jsp" %>





<%@ include file="../footer.jsp" %>