<jsp:include page="header.jsp"/>

<section class="dashboard-section">
  <div class="row dashboard-nowrap">

    <jsp:include page="side-menu.jsp"/>

    <div class="m-4 p-3 width-medium text-color-darker">
      <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form action="/app/recipe/add" method="post">
          <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
              <div class="col"><h3 class="color-header text-uppercase">Nowy przepis</h3></div>
              <div class="col d-flex justify-content-end mb-2">
                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
              </div>
            </div>
            <input type="hidden" id="admin_id" name="admin_id" value="${admin.id}">
            <table class="table borderless">
              <tbody>
              <tr class="d-flex">
                <th scope="row" class="col-2">Nazwa Przepisu</th>
                <td class="col-7">
                  <input name="name" class="w-100 p-1" value="">
                </td>
              </tr>
              <tr class="d-flex">
                <th scope="row" class="col-2">Opis przepisu</th>
                <td class="col-7">
                  <textarea name="description" class="w-100 p-1" rows="5"></textarea>
                </td>
              </tr>
              <tr class="d-flex">
                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                <td class="col-3">
                  <input name="preparationTime" class="p-1" type="number" value="">
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
                <textarea name="preparation" class="w-100 p-1" rows="10"></textarea>
              </div>
              <div class="col-2"></div>
              <div class="col-5 p-4">
                <textarea name="ingredients" class="w-100 p-1" rows="10"></textarea>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</section>

<footer>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>

</html>
