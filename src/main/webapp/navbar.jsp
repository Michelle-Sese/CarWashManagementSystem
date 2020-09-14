<h3>Welcome ${sessionScope.loggedInUser.name} </h3>
<div class="top-navbar">
    <ul>
      <li><a class="active" href="#home">Dashboard</a></li>
      <li><a href="${pageContext.request.contextPath}/department">Departments</a></li>
      <li><a href="${pageContext.request.contextPath}/instructor?action=list">Instructors</a></li>
      <li><a href="${pageContext.request.contextPath}/course">Courses</a></li>
      <li><a href="${pageContext.request.contextPath}/student">Students</a></li>
      <li style="float:right"><a href="logout">Logout</a></li>
    </ul>
</div>