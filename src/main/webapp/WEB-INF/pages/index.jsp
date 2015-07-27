<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Manager</title>
</head>
<body>
  <div>
    <h1>Book Manager</h1>
    <%--якщо модель books  не пусте , тоді створбється таблиця--%>
    <c:if test="$(!emty books}">
      <table>
        <tr>
          <th>Name</th>
          <th>Genre</th>
        </tr>
        <%--за допомогою форіч створюємо книгу в кожному рядку--%>
          <c:forEach items="${books}" var="book">
            <tr>
              <td>${book.name}</td>
              <td>${book.genre}</td>
            </tr>
          </c:forEach>
      </table>
    </c:if>
  </div>
</body>
</html>
