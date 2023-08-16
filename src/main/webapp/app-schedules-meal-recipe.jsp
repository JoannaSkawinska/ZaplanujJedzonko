<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <title>Add Recipe to Plan</title>
</head>

<body>
<h1>Add Recipe to Plan</h1>
<form action="/app/recipe/plan/add" method="post">
    <label for="choosePlan">Choose Plan:</label>
    <select id="choosePlan" name="choosePlan">
        <c:forEach items="${planOptions}" var="plan">
            <option value="${plan.value}">${plan.label}</option>
        </c:forEach>
    </select><br><br>
    <label for="name">Meal Name:</label>
    <input type="text" id="name" name="name"><br><br>
    <label for="number">Meal Number:</label>
    <input type="text" id="number" name="number"><br><br>
    <label for="recipie">Recipe:</label>
    <select id="recipie" name="recipie">
        <c:forEach items="${recipeOptions}" var="recipe">
            <option value="${recipe.value}">${recipe.label}</option>
        </c:forEach>
    </select><br><br>
    <label for="day">Day:</label>
    <select id="day" name="day">
        <c:forEach items="${dayOptions}" var="dayOption">
            <option value="${dayOption.value}">${dayOption.label}</option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Submit">
</form>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
         crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>