<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>


    <div class="main">
        <div class="row game background">
            <h1 class="p-2 ms-4">${game.name}</h1>
            <div class="col-md-1"></div>
            <div class="col-md-5 col-sm-12">
                <p>Disponible sur
                    <p>
                        <c:forEach items="${game_logos}" var="logo">
                            <span>${logo}</span>
                        </c:forEach>
                    </p>
                </p>
                <p>Publié le ${game.publishedAt}</p>
                <p>Editeur : ${game.publisher.name}</p>
                <p>Genre : ${game.genre.name}</p>
                <p>Classification : ${game.classification.name}</p>
                <p>Modèle économique : ${game.businessModel.name}</p>

                <p>${game.description}</p>
            </div>

            <div class="col-md-5 col-sm-12 mb-5 ms-4">
                <img src="${game.image}" class="img-fluid w-75">
            </div>
            <div class="col-md-1"></div>
        </div>
        <div class="separator game-review"></div>
        <script src="/js/game/hide-form.js"></script>
        <div class="row">
            <h2 class="fs-2">Commentaires
                    <security:authorize access="!hasRole('MODERATOR')">
                        <button class="ms-2 btn btn-link"
                                title="Ajouter un commentaire"
                                data-hide-show-button="formReview"
                        >
                            <i class="fa fa-pen fa-2x"></i>
                        </button>
                    </security:authorize>
                </h2>
                <div class="col-2"></div>
                <div class="my-3 p-4 col-8 d-none div-review-game"
                     data-hide-show-container="formReview"
                >
                    <f:form cssClass="col-sm-12 mx-auto"
                            action="${currentUrl}"
                            method="post"
                            modelAttribute="reviewDto"
                    >
                        <div class="mb-3 row">
                            <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
                            <div class="col-sm-10">
                                <f:textarea cssClass="form-control" path="description"/>
                                <f:errors path="description" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="my-3 row">
                            <f:label path="rating" class="col-sm-2 col-form-label">Note</f:label>
                            <div class="col-sm-10">
                                <f:input type="number" step="0.5" cssClass="form-control" path="rating"/>
                                <f:errors path="rating" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <f:button type="submit" class="btn btn-success">
                            <i class="fa fa-check"></i> Ajouter
                        </f:button>
                    </f:form>
                </div>
                <div class="col-2"></div>
        </div>
        <div class="row ms-5 mb-4" id="reviews">

        <c:set var="scroll" value="#reviews"/>
                <c:forEach items="${reviews.content}" var="review">
                    <c:if test="${review.moderator != null}">
                        <a href="/avis/${review.id}" class="link-if col-4" not-hovered>
                            <div class="review-card card border-white bg-dark me-3 mt-4">
                                <div class="card-header">
                                    <div class="d-flex justify-content-between">
                                        <span><strong>${review.player.username}</strong></span>
                                        <span class="${jspUtils.getCssClas(review.rating)}">${jspUtils.getStringRating(review.rating)}/20</span>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <c:out value="${jspUtils.excerpt(review.description,180)}" escapeXml="false"/>
                                </div>
                            </div>
                        </a>
                    </c:if>
                </c:forEach>
        </div>
    </div>
    <c:set var="page" scope="request" value="${reviews}"/>
        <%@ include file="../component/pagination.jsp" %>


<%@ include file="../footer.jsp" %>
