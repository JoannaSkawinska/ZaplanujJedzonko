<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 16.08.2023
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>App Edit Recipe</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>
<header class="page-header">
  <nav class="navbar navbar-expand-lg justify-content-between">
    <a href="/" class="navbar-brand main-logo main-logo-smaller">
      Zaplanuj <span>Jedzonko</span>
    </a>
    <div class="d-flex justify-content-around">
      <h4 class="text-light mr-3">Imię</h4>
      <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
    </div>
  </nav>
</header>

<section class="dashboard-section">
  <div class="row dashboard-nowrap">

    <jsp:include page="side-menu.jsp"/>

    <div class="m-4 p-3 width-medium text-color-darker">
      <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">

          <form action="/app/recipe/update" method="post">
            <div class="row border-bottom border-3">
              <div class="col"><h3 class="color-header text-uppercase">Edycja przepisu</h3></div>
              <div class="col d-flex justify-content-end mb-2"><button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button></div>
            </div>
            <input type="hidden" id="recipeId" name="recipeId" value="${recipeId}">
            <input type="hidden" id="oldRecipeName" name="oldRecipeName" value="${oldRecipeName}">
            <input type="hidden" id="oldRecipeDescription" name="oldRecipeDescription" value="${oldRecipeDescription}">
            <input type="hidden" id="oldRecipePreparationTime" name="oldRecipePreparationTime" value="${oldRecipePreparationTime}">
            <input type="hidden" id="oldRecipePreparation" name="oldRecipePreparation" value="${oldRecipePreparation}">
            <input type="hidden" id="oldRecipeIngredients" name="oldRecipeIngredients" value="${oldRecipeIngredients}">

            <table class="table borderless">
              <tbody>
              <tr class="d-flex">
                <th scope="row" class="col-2">Nazwa Przepisu</th>
                <td class="col-7">
                  <input class="w-100 p-1" name="newRecipeName" value="${oldRecipeName}">
                </td>
              </tr>
              <tr class="d-flex">
                <th scope="row" class="col-2">Opis przepisu</th>
                <td class="col-7"> <textarea class="w-100 p-1" name="newRecipeDescription" rows="5"><c:out value="${oldRecipeDescription}"/></textarea></td>
              </tr>
              <tr class="d-flex">
                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                <td class="col-3">
                  <input class="p-1" type="number" name="newRecipePreparationTime" value="${oldRecipePreparationTime}">
                </td>
              </tr>
              </tbody>
            </table>

            <div class="row d-flex">
              <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
              <div class="col-2"></div>
              <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
            </div>
            <div class="row d-flex">
              <div class="col-5 p-4">
                <textarea class="w-100 p-1" name="newRecipePreparation" rows="10"><c:out value="${oldRecipePreparation}"/></textarea>
              </div>
              <div class="col-2"></div>

              <div class="col-5 p-4">
                <textarea class="w-100 p-1" name="newRecipeIngredients" rows="10"><c:out value="${oldRecipeIngredients}"/></textarea>
              </div>
            </div>
          </form>
        </div>

      </div>
    </div>
  </div>
</section>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
