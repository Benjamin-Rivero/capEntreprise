<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

    <h1 class="p-2 ms-4">Page de ${game.name}</h1>
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-5 col-sm-12">
            <p>Publié le ${game.publishedAt}</p>

            <c:forEach items="${game_logos}" var="logo">
                <span>${logo}</span>
            </c:forEach>

            <c:if test="${game.moderator != null}">
                <p>Modéré par ${game.moderator.username}</p>
            </c:if>

            <p>${game.description}</p>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-5 col-sm-12">
            <img src="${game.image}" class="img-fluid">
        </div>
    </div>
    <div class="separator game-review"></div>
    <div class="row ms-5 mb-4">
            <c:forEach items="${game.reviews}" var="review">
                <c:if test="${review.moderator != null}">
                    <a href="/avis/${review.id}" class="link-if col-4">
                        <div class="card border-white bg-transparent me-3 mt-4 review-card">
                            <div class="card-header">
                                <div class="d-flex justify-content-between">
                                    <span><strong>${review.player.username}</strong></span>
                                    <span>${review.rating}/20</span>
                                </div>
                            </div>
                            <div class="card-body">
                                <p class="card-text">${review.description}</p>
                            </div>
                        </div>
                    </a>
                </c:if>
            </c:forEach>
    </div>


<%@ include file="../footer.jsp" %>
