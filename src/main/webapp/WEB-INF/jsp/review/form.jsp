<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Ajout d'avis"/>
<jsp:include flush="true" page="../base.jsp"/>



        <h1>Nouvel avis</h1>
        <f:form modelAttribute="review" method="post" action="${action}" cssClass="p-5">
            <div class="row mt-3">
                <f:label path="gameId" class="col-sm-2 col-form-label">Jeu</f:label>
                <div class="col-sm-10">
                    <select cssClass="form-select" name="gameId">
                        <c:forEach items="${games}" var="game">
                            <option value="${game.id}">
                                ${game.name}
                            </option>
                        </c:forEach>
                    </select>
                    <f:errors path="gameId" cssClass="invalid-feedback"/>
                </div>
            </div>

                <div class="row mt-3">
                    <f:label path="description" class="col-sm-2 col-form-label">Description : </f:label>
                    <div class="col-sm-10">
                        <f:textarea cssClass="form-control" path="description"/>
                        <f:errors path="description" cssClass="invalid-feedback"/>
                    </div>
                </div>


                <div class="row mt-3">
                    <f:label path="rating" class="col-sm-2 col-form-label">Note attribuée: </f:label>
                    <div class="col-sm-10">
                        <f:input type="number" step="0.5" min="0" max="20" cssClass="form-control" path="rating"/>
                        <f:errors path="rating" cssClass="invalid-feedback"/>
                    </div>
                </div>
            <div class="mt-3">
                <f:input type="number" path="playerId" hidden="hidden"/>
                <f:button class="btn btn-secondary" type="reset">Reset</f:button>
                <f:button class="btn btn-primary">Submit</f:button>
            </div>
        </f:form>

<%@ include file="../footer.jsp" %>