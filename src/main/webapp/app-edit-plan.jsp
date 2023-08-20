<%@ include file="header.jsp" %>

<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <ul class="nav flex-column long-bg">
      <li class="nav-item">
        <a class="nav-link" href="/DashboardServlet">
          <span>Pulpit</span>
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/app/recipe/list/">
          <span>Przepisy</span>
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/app/plan/list">
          <span>Plany</span>
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/EditUserDataServlet">
          <span>Edytuj dane</span>
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="/EditPasswordServlet">
          <span>Zmień hasło</span>
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/SuperAdminServlet">
          <span>Użytkownicy</span>
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
    </ul>

    <div class="m-4 p-3 width-medium">
      <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form method="post" action="/app/plan/edit">
          <input type="hidden" name="planId" value="${plan.id}">
          <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
              <h3 class="color-header text-uppercase">EDYTUJ PLAN</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2">
              <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
            </div>
          </div>

          <div class="schedules-content mt-3">
            <div class="form-group row">
              <label for="planName" class="col-sm-2 label-size col-form-label">Nazwa planu</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="planName" value="${plan.name}" placeholder="Nazwa planu">
              </div>
            </div>
            <div class="form-group row">
              <label for="planDescription" class="col-sm-2 label-size col-form-label">Opis planu</label>
              <div class="col-sm-10">
                <textarea class="form-control" rows="5" name="planDescription" placeholder="Opis planu">${plan.description}</textarea>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</section>

<%@ include file="footer.jsp" %>
