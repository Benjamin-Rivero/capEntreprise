<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Ajout de jeu"/>
<jsp:include flush="true" page="../base.jsp"/>



        <c:if test="${isEdit}">
            <h1>Modification Jeu ${game.name}</h1>
        </c:if>
        <c:if test="${!isEdit}">
            <h1>Creation Jeu</h1>
        </c:if>
        <f:form modelAttribute="game" method="post" action="${action}" cssClass="p-5">
            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:select cssClass="form-select" path="publisherId">
                    <f:option value="0">Editeur</f:option>
                        <c:forEach items="${publishers}" var="publisher">
                            <f:option value="${publisher.id}">
                                ${publisher.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="publisherId" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:input type="text" cssClass="form-control" path="name" placeholder="Nom"/>
                    <f:errors path="name" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10 form-group ${status.error ? 'has-error' : ''}">
                    <f:input type="date" path="publishedAt" class="form-control" value="${game.publishedAt}"/>
                    <f:errors path="publishedAt" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:textarea cssClass="form-control" path="description" placeholder="Description"/>
                    <f:errors path="description" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:select cssClass="form-select" path="genreId">
                        <f:option value="0">Genre</f:option>
                        <c:forEach items="${genres}" var="genre">
                            <f:option value="${genre.id}">
                                ${genre.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="genreId" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:select  cssClass="form-select" path="classificationId">
                        <f:option value="0">Classification</f:option>
                        <c:forEach items="${classifications}" var="classification">
                            <f:option value="${classification.id}">
                                ${classification.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="classificationId" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:select  cssClass="form-select" path="platformIds" multiple="multiple">
                        <f:option value="0">Plateforme</f:option>
                        <c:forEach items="${platforms}" var="platform">
                            <f:option value="${platform.id}">
                                ${platform.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="platformIds" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="mt-2 row">
                <div class="col-sm-10">
                    <f:select  cssClass="form-select" path="businessModelId">
                        <f:option value="0">Modèle Economique</f:option>
                        <c:forEach items="${businessModels}" var="businessModel">
                            <f:option value="${businessModel.id}">
                                ${businessModel.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="businessModelId" cssClass="invalid-feedback"/>
                </div>
            </div>

            <f:input type="number" path="moderatorId" hidden="hidden"/>
            <f:input type="text" path="image" hidden="hidden"/>
            <f:button class="btn btn-success my-5">Submit</f:button>
            <f:button class="btn btn-secondary my-5" type="reset">Reset</f:button>

        </f:form>

<%@ include file="../footer.jsp" %>