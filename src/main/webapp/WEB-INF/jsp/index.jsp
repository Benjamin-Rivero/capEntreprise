<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Pas censé être là"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

    <div class="separator background">
        <h2 class="fs-2">Commentaires au hasard</h2>
    </div>
    <div class="row d-flex justify-content-center">
        <c:forEach items="${reviews}" var="review">

                <c:if test="${review.moderator != null}">
                    <div class="review-card card border-white bg-dark mx-3 mt-2">
                        <div class="card-header">
                            <div class="d-flex justify-content-between">
                                <span><strong>${review.player.username}</strong></span>
                                <p class="${jspUtils.getCssClas(review.rating)}">${jspUtils.getStringRating(review.rating)}/20</p>
                            </div>
                        </div>
                        <div class="card-body">
                            <c:out value="${jspUtils.excerpt(review.description,180)}" escapeXml="false"/>
                        </div>
                        <div class="card-footer">
                            <a class="link-if" href="/jeu/${review.game.id}">${review.game.name}</a>
                        </div>
                    </div>
                </c:if>

        </c:forEach>
    </div>

    <div class="separator background">
        <h2>Jeux en vedette</h2>
    </div>

    <div id="carouselExampleAutoplaying" class="carousel slide home-carousel mt-2">
      <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="5000">
        <a href="/jeu/${games[0].id}">
          <img src="${games[0].image}" class="d-block w-100 h-100" alt="No image">
          <div class="carousel-caption d-none d-md-block">
            <h5>${games[0].name}</h5>
          </div>
        </a>

        </div>
        <c:forEach var="i" begin="1" end="3" step="1">
            <div class="carousel-item" data-bs-interval="5000">
                <a href="/jeu/${games[i].id}">
                  <img src="${games[i].image}" class="d-block w-100 h-100" alt="No image">
                  <div class="carousel-caption d-none d-md-block">
                    <h5>${games[i].name}</h5>
                  </div>
                </a>

            </div>
        </c:forEach>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

    <div class="separator"></div>

<%@ include file="footer.jsp" %>
