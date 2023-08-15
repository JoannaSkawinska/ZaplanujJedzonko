<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Recipe to Plan</title>
</head>
<body>
<h1>Add Recipe to Plan</h1>
<form action="/app/recipe/plan/add" method="post">
    <label for="choosePlan">Choose Plan:</label>
    <select id="choosePlan" name="choosePlan">
        <option value="plan1">Plan 1</option>
        <option value="plan2">Plan 2</option>
        <!-- Add more options as needed -->
    </select><br><br>
    <label for="name">Meal Name:</label>
    <input type="text" id="name" name="name"><br><br>
    <label for="number">Meal Number:</label>
    <input type="text" id="number" name="number"><br><br>
    <label for="recipie">Recipe:</label>
    <select id="recipie" name="recipie">
        <option value="recipe1">Recipe 1</option>
        <option value="recipe2">Recipe 2</option>
        <!-- Add more options as needed -->
    </select><br><br>
    <label for="day">Day:</label>
    <select id="day" name="day">
        <option value="monday">Monday</option>
        <option value="tuesday">Tuesday</option>
        <!-- Add more options as needed -->
    </select><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
