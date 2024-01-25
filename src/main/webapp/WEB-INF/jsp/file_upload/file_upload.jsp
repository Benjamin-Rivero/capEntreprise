<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<form method="POST" action="${pageContext.request.contextPath}/jeu/${id}/upload/game_image" enctype="multipart/form-data">
    <label>Choose a file :</label>
    <input type="file" name="file" />
    <input type="submit" value="Submit"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<div>
    ${message}
</div>

<%@ include file="../footer.jsp" %>