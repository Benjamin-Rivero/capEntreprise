<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

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
                    <a href="${currentUrl}" class="btn-link">
                        Reset
                    </a>
            </span>
        </div>
    <table class="table table-striped">
        <thead>
            <td>Image</td>
            <td>Nom</td>
            <td>Editeur</td>
            <td>Operations</td>
        </thead>
        <tbody>
            <c:forEach items="${games.content}" var="game">
                <tr>
                    <td><img class="img-fluid w-25" src=${game.image}></td>
                    <td>${game.name}</td>
                    <td>${game.publisher.name}</td>
                    <td>
                        <a href="/jeu/${game.id}" class="btn btn-link">Voir</a>
                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <a href="/jeu/editer/${game.id}" class="btn btn-link">Modifier</a>
                            <a href="/jeu/${game.id}/upload" class="btn btn-link">Televerser l'image</a>
                            <a href="/jeu/${game.id}/supprimer" class="btn btn-link">Supprimer</a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <c:set var="page" scope="request" value="${games}"/>
    <%@ include file="../component/pagination.jsp" %>





<%@ include file="../footer.jsp" %>