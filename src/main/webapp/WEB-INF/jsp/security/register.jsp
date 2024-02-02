<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Créer un compte"/>
<jsp:include flush="true" page="../base.jsp"/>


<img class="ouiKevinCestMoche">
<div class="container">
  <f:form method="POST" modelAttribute="userForm" class="form-signin">
    <h2 class="form-signin-heading">Créer un compte</h2>
      <div class="mt-4 form-group ${status.error ? 'has-error' : ''}">
        <label>Email : </label>
        <f:input type="text" path="email" class="form-control" placeholder="Email"
                    autofocus="true"/>
        <f:errors path="email" cssClass="invalid-feedback"/>
      </div>
      <div class="mt-4 form-group ${status.error ? 'has-error' : ''}">
        <label>Pseudo : </label>
        <f:input type="text" path="username" class="form-control" placeholder="Nom du compte"
                    autofocus="true"/>
        <f:errors path="username" cssClass="invalid-feedback"/>
      </div>
      <div class="mt-4 form-group ${status.error ? 'has-error' : ''}">
        <label>Mot de passe : </label>
        <f:input type="password" path="password" class="form-control" placeholder="Mot de passe"/>
        <f:errors path="password" cssClass="invalid-feedback"/>
      </div>
      <div class="mt-4 form-group ${status.error ? 'has-error' : ''}">
        <label>Date de naissance : </label>
        <f:input type="date" path="birthDate" class="form-control" placeholder="Date de naissance dd/MM/yyyy"/>
        <f:errors path="birthDate" cssClass="invalid-feedback"/>
      </div>
      <button class="btn btn-lg btn-success btn-block mt-3" type="submit">Confirmer</button>
      <a href="/" class="btn btn-lg btn-secondary mt-3">Retour</a>
  </f:form>

</div>

<%@ include file="../footer.jsp" %>