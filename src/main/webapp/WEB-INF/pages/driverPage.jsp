<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>index</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui.css">
    <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/resources/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jquery-1.11.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/driver.js"  type="text/javascript"> </script>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

</head>
<body>
<div class="min_width">
<div class="main">
<!--header -->
<div style="margin-top:12px; margin-left:76%" id="upsideBar">
    <h3>Hi,
        <c:set var="UserName" value="${UserName}"></c:set>
        <font color="#2f4f4f">
            <c:out value="${UserName}"></c:out>
        </font>

        <div style="margin-left: 180px;margin-top: -22px;">
            <form>
                <input value="Logout" class="logOutButton" type="submit" action="/Freight/j_spring_security_logout">
                </button>
            </form>
        </div>
    </h3>
</div>
<div id="header" style="background: url(${pageContext.request.contextPath}/resources/images/bg_header.jpg.gif) no-repeat">
    <div class="indent">
        <div class="logo" style="background-image: url(${pageContext.request.contextPath}/resources/images/bg_logo.gif)">
            <h1><a href="#">Freight Control System</a></h1>
        </div>
    </div>

</div>
<!--header end-->
<!--content -->
<div class="content">
<div class="indent">
<div class="w100">
<div class="column_center">
<div class="indent_center">
<div class="title">
    <div class="bg_title"><h3><a href="#">
        <c:set var="resultwagonAdd" value="${operationResult}"></c:set>
        <c:if test="${operationResult != null}">
        <c:if test="${operationResult.isSeccesful()}">
        <font color="#adff2f">
            <c:out value="${operationResult}"></c:out>
        </font>
        </c:if>
        <c:if test="${!operationResult.isSeccesful()}">
        <font color="#dc143c">
            <c:out value="${operationResult}"></c:out>
        </font>
        </c:if>
        </c:if>
    </h3>
        </a>
    </div>
</div>

<div class="text_box" id="driverInfo">
    <h1> Get driver info </h1>
    <br>
    <form action="/Freight/Driver/GetInfo" method="post">
        <br>
        <br>
        <h3>
            Enter driver license:
        <input type="text" placeholder="Driver License" name="driverLicense" />
        </h3>
        <input type="submit" class="submitbutton" value="Get info">
    </form>
</div>

    <div class="text_box" id="deliverItem">
        <h1> Deliver item </h1>
        <form action="/Freight/Driver/DeliverItem" method="post">
            <h3>
                Enter order number:
                <input type="text" placeholder="Order Number" name="orderNumber">
            </h3>
            <h3>
                Enter itemID:
                <input type="text" placeholder="Item ID" name="itemID">
            </h3>
            <h3>
                Enter driver license:
                <input type="text" placeholder="Driver License" name="driverLicense">
            </h3>
                <input type="submit" class="submitbutton" value="Deliver Item">
        </form>
    </div>

    <div class="text_box" id="changeStatus">
        <br>
        <form action="/Freight/Driver/ChangeStatus" method="post">
            <h1> Change driver Status </h1>
            <h3>
                Enter driver license:
                <input type="text" placeholder="Driver License" name="driverLicense">
            </h3>
            <h3>
                Choose Status:
                <select name="status">
                <option value="At work">At work</option>
                <option value="Driving">Driving</option>
                </select>
             </h3>
            <input type="hidden" name="operation" value="changeStatus">
        </form>

    </div>


</div>
</div>
<div class="side_bar">
    <div class="widget_style" id="meta">
        <div class="inside">
            <div class="left_top">
                <div class="right_top">
                    <div class="left_bot">
                        <div class="right_bot">
                            <div style = "background-image:url(${pageContext.request.contextPath}/resources/images/bull_2.gif); background-repeat: no-repeat; background-position: 7px 8px" class="inside_title_box"><h2>Order panel</h2></div>
                        </div>
                    </div>
                </div>
            </div>
            <ul>
                <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openDriverInfo();">Driver's info</a></li>
                <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openDeliverItem();">Deliver Item</a></li>
                <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openChangeStatus();">Change status</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clear"></div>
</div>
</div>
</div>
<!--content end-->
</div>
</div>
</body>
</html>
