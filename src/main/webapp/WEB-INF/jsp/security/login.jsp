<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Connexion"/>
<jsp:include flush="true" page="../base.jsp"/>

<link href="${contextPath}/css/login/login.css" rel="stylesheet">
<img class="loginImg">

<div class="container">
  <div class="login-card-bg-blur"></div>
  <div class="login-card-bg d-flex justify-content-center">
    <form method="POST" action="${UrlRoute.URL_LOGIN}" class="form-signin">
      <div class="login-card header">
          <h2 class="form-heading my-4 fs-2">Connexion</h2>
      </div>
      <div class="login-card body ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <input name="username" type="text" class="form-control" placeholder="Nom du compte"
              autofocus="true"/>
        <input name="password" type="password" class="form-control mt-3" placeholder="Mot de passe"/>
        <p class="invalid-feedback">${error}</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-success btn-block" type="submit">Se connecter</button>
        <h4 class="text-center">
          <a href="${contextPath}/s-inscrire" class="btn-link">
            Créer un compte
          </a>
        </h4>
      </div>
    </form>
  </div>
</div>

<%@ include file="../footer.jsp" %>