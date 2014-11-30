<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <link href="${pageContext.request.contextPath}/resources/login_styles.css" rel="stylesheet">
    </head>
    <body>


        <div id="Login">
            <form action="/Freight/j_spring_security_check" method="post">

                <c:set var="response" value="${type}"></c:set>
                <c:if test="${response != null}">
                    <c:out value="${type}"></c:out>
                </c:if>
                <input type="text" placeholder="Login" name="j_username" />
                <input type="password" placeholder="Password" name="j_password" />
                <input type="submit" value="Enter"/>
            </form>
        </div>
    </body>
</html>