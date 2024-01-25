<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>



        <h1>Nouvel avis</h1>
        <f:form modelAttribute="review" method="post" action="${action}" cssClass="p-5">
            <div class="mb-3 row">

                <f:label path="gameId" class="col-sm-2 col-form-label">Game</f:label>
                <div class="col-sm-10">
                    <select name="gameId">
                        <c:forEach items="${games}" var="game">
                            <option value="${game.id}">
                                ${game.name}
                            </option>
                        </c:forEach>
                    </select>
                    <f:errors path="gameId" cssClass="invalid-feedback"/>
                </div>

                <f:label path="description" class="col-sm-2 col-form-label">Description : </f:label>
                <div class="col-sm-10">
                    <f:input type="text" cssClass="form-control" path="description"/>
                    <f:errors path="description" cssClass="invalid-feedback"/>
                </div>

                <f:label path="rating" class="col-sm-2 col-form-label">Note : </f:label>
                <div class="col-sm-10">
                    <f:input type="number" step="0.5" min="0" max="20" cssClass="form-control" path="rating"/>
                    <f:errors path="rating" cssClass="invalid-feedback"/>
                </div>

                <f:input type="number" path="playerId" hidden="hidden"/>
            </div>
            <f:button class="btn btn-secondary" type="reset">Reset</f:button>
            <f:button class="btn btn-primary">Submit</f:button>
        </f:form>

<%@ include file="../footer.jsp" %>