<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>



        <c:if test="${isEdit}">
            <h1>Modification Jeu ${game.name}</h1>
        </c:if>
        <c:if test="${!isEdit}">
            <h1>Creation Jeu</h1>
        </c:if>
        <f:form modelAttribute="game" method="post" action="${action}" cssClass="p-5">
            <div class="mb-3 row">

                <f:label path="publisherId" class="col-sm-2 col-form-label">Editeur : </f:label>
                <div class="col-sm-10">
                    <f:select path="publisherId">
                    <f:option value="0">Editeur</f:option>
                        <c:forEach items="${publishers}" var="publisher">
                            <f:option value="${publisher.id}">
                                ${publisher.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="publisherId" cssClass="invalid-feedback"/>
                </div>

                <f:label path="name" class="col-sm-2 col-form-label">Nom : </f:label>
                <div class="col-sm-10">
                    <f:input type="text" cssClass="form-control" path="name"/>
                    <f:errors path="name" cssClass="invalid-feedback"/>
                </div>

                <f:label class="col-sm-2" path="publishedAt">Date de publication : </f:label>
                <div class="col-sm-10 form-group ${status.error ? 'has-error' : ''}">
                    <f:input type="date" path="publishedAt" class="form-control" value="${game.publishedAt}"/>
                    <f:errors path="publishedAt" cssClass="invalid-feedback"/>
                </div>

                <f:label path="description" class="col-sm-2 col-form-label">Description : </f:label>
                <div class="col-sm-10">
                    <f:input type="text" cssClass="form-control" path="description"/>
                    <f:errors path="description" cssClass="invalid-feedback"/>
                </div>

                <f:label path="genreId" class="col-sm-2 col-form-label">Genre : </f:label>
                <div class="col-sm-10">
                    <f:select path="genreId">
                        <f:option value="0">Genre</f:option>
                        <c:forEach items="${genres}" var="genre">
                            <f:option value="${genre.id}">
                                ${genre.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="genreId" cssClass="invalid-feedback"/>
                </div>

                <f:label path="classificationId" class="col-sm-2 col-form-label">Classification : </f:label>
                <div class="col-sm-10">
                    <f:select path="classificationId">
                        <f:option value="0">Classification</f:option>
                        <c:forEach items="${classifications}" var="classification">
                            <f:option value="${classification.id}">
                                ${classification.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="classificationId" cssClass="invalid-feedback"/>
                </div>

                <f:label path="platformIds" class="col-sm-2 col-form-label">Plateformes : </f:label>
                <div class="col-sm-10">
                    <f:select path="platformIds" multiple="multiple">
                        <f:option value="0">Plateforme</f:option>
                        <c:forEach items="${platforms}" var="platform">
                            <f:option value="${platform.id}">
                                ${platform.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="platformIds" cssClass="invalid-feedback"/>
                </div>

                <f:label path="businessModelId" class="col-sm-2 col-form-label">Modèle économique : </f:label>
                <div class="col-sm-10">
                    <f:select path="businessModelId">
                        <f:option value="0">Modèle Economique</f:option>
                        <c:forEach items="${businessModels}" var="businessModel">
                            <f:option value="${businessModel.id}">
                                ${businessModel.name}
                            </f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="businessModelId" cssClass="invalid-feedback"/>
                </div>

                <f:input type="number" path="moderatorId" hidden="hidden"/>

            </div>
            <f:button class="btn btn-secondary" type="reset">Reset</f:button>
            <f:button class="btn btn-primary">Submit</f:button>
        </f:form>

<%@ include file="../footer.jsp" %>