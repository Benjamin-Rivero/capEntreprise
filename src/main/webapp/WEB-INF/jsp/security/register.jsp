<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Créer un compte"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <f:form method="POST" modelAttribute="userForm" class="form-signin">
    <h2 class="form-signin-heading">Créer un compte</h2>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label>Email : </label>
        <f:input type="text" path="email" class="form-control" placeholder="Email"
                    autofocus="true"/>
        <f:errors path="email" cssClass="invalid-feedback"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label>Username : </label>
        <f:input type="text" path="username" class="form-control" placeholder="Nom du compte"
                    autofocus="true"/>
        <f:errors path="username" cssClass="invalid-feedback"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label>Password : </label>
        <f:input type="password" path="password" class="form-control" placeholder="Mot de passe"/>
        <f:errors path="password" cssClass="invalid-feedback"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label>Date of birth : </label>
        <f:input type="date" path="birthDate" class="form-control" placeholder="Date de naissance dd/MM/yyyy"/>
        <f:errors path="birthDate" cssClass="invalid-feedback"/>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Confirmer</button>
  </f:form>
</div>

<%@ include file="../footer.jsp" %>