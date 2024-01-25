<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

    <h1>Index des jeux</h1>
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
                    <td><img src=${game.image}></td>
                    <td>${game.name}</td>
                    <td>${game.publisher.name}</td>
                    <td>
                        <a href="/jeu/${game.id}">Voir</a>
                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <a href="/jeu/editer/${game.id}">Modifier</a>
                            <a href="/jeu/${game.id}/upload">Televerser l'image</a>
                            <a href="/jeu/${game.id}/supprimer">Supprimer</a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <c:set var="page" scope="request" value="${games}"/>
    <%@ include file="../component/pagination.jsp" %>


    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a href="${s:mvcUrl('AppGame#new').build()}">Ajouter un jeu</a>
    </security:authorize>
    <a href="${s:mvcUrl('AppReview#list').build()}">Liste des avis</a>

<%@ include file="../footer.jsp" %>