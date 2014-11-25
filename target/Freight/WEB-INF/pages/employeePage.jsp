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
    <script src="${pageContext.request.contextPath}/resources/employee.js"  type="text/javascript"> </script>
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

                            <div class="text_box" id="driverList">
                                <h1> Driver list:</h1>
                                <br>
                                <c:forEach items="${Drivers}" var="item">
                                    <h3>>
                                    <c:out value="${item}"></c:out>
                                    </h3>
                                        <br>
                                </c:forEach>
                            </div>

                            <div class="text_box" id="addDriver">
                                <form action="/Freight/AddDriver/" method="post">
                                    <h1> Add new driver</h1>
                                    <h3>
                                    Enter driver license:
                                    <input type="text" placeholder="Driver License" name="driverLicense" />
                                    </h3>
                                    <h3>
                                        Enter driver's name:
                                        <input type="text" placeholder="Name" name="name"/>
                                    </h3>
                                    <h3>
                                        Enter driver's surname:
                                        <input type="text" placeholder="Surname" name="surname"/>
                                    </h3>
                                    <h3>
                                        Enter driver's patronymic:
                                        <input type="text" placeholder="Patronymic" name="patronymic"/>
                                    </h3>
                                        <input type="submit" class="submitbutton" value="Add"/>
                                </form>
                            </div>
                            <div class="text_box" id="wagonList">
                                <h1> Wagon list:</h1>
                                <br>
                                <c:forEach items="${Wagons}" var="item">
                                    <h3>>
                                    <c:out value="${item}"></c:out>
                                    </h3>
                                        <br>
                                </c:forEach>
                            </div>

                            <div class="text_box" id="addWagon">
                                <form action="/Freight/AddWagon" method="post">
                                    <h1>Add new wagon</h1>
                                    <h3>
                                    Enter registration number:
                                    <input type="text" placeholder="Registration Number" name="registrationNumber" />
                                    </h3>
                                    <h3>
                                    Enter capacity class:
                                    <input type="text" placeholder="Capacity class (1,2,3)" name="capacityClass"/>
                                    </h3>
                                    <h3>
                                    Enter number of drivers:
                                    <input type="text" placeholder="Number of Drivers" name="numberDrivers"/>
                                    </h3>
                                    <input type="submit" class="submitbutton" value="Add" />
                                </form>
                            </div>

                            <div class="text_box" id="createNewOrder">
                                <form action="/Employee" method="post">
                                    <h1>Create new order</h1>
                                    <h3>
                                    Enter order's number:
                                    <input type="text" placeholder="Order number" name="orderNumber">
                                    </h3>
                                        <input type="submit" class="submitbutton" value="Add Order">
                                    <input type="hidden" name="operation" value="addOrder">
                                </form>
                            </div>

                            <div class="text_box" id="addDriverToWagon">
                                <form action="/Employee" method="post">
                                    <h1>Add driver to wagon</h1>
                                    <h3>
                                        Choose driver:
                                    <select name="driverLicense">
                                        <c:forEach items="${Drivers}" var="item">
                                            <option value=
                                                        <c:out value="${(item.driverLicense)}"></c:out>
                                                    >
                                                <c:out value="${(item.driverLicense)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </h3>
                                    <h3>
                                        Choose wagon:
                                    <select name="wagonNumber">
                                        <c:forEach items="${Wagons}" var="item">
                                            <option value=
                                                        <c:out value="${(item.registrationNumber)}"></c:out>
                                                    >
                                                <c:out value="${(item.registrationNumber)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </h3>
                                    <input type="submit" class="submitbutton" value="Add Driver to Wagon">
                                </form>

                            </div>

                            <div class="text_box" id="addWagonToOrder">
                                <form action="/Freight/Employee/AddWagonToOrder" method="post">
                                    <h1> Add wagon to order</h1>
                                    <h3>
                                        Choose wagon:
                                    <select name="wagonNumber">
                                        <c:forEach items="${Wagons}" var="item">
                                            <option value=
                                                        <c:out value="${(item.registrationNumber)}"></c:out>
                                                    >
                                                <c:out value="${(item.registrationNumber)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </h3>
                                    <h3>
                                        Choose order
                                    <select name="orderNumber">
                                        <c:forEach items="${Orders}" var="item">
                                            <option value=
                                                        <c:out value="${(item.number)}"></c:out>
                                                    >
                                                <c:out value="${(item.number)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </h3>
                                    <input type="submit" class="submitbutton" value="Add Wagon to order">
                                </form>
                            </div>

                            <div class="text_box" id="addItemToOrder">
                                <form action="/Freight/AddItemToOder" method="post">
                                    <h1> Add item to order</h1>
                                    <c:set var="resultItemAddToOrder" value="${resultItemAddToOrder}"></c:set>
                                    <h3>
                                        Choose item:
                                    <select name="itemName">
                                        <c:forEach items="${Items}" var="item">
                                            <option value=
                                                        <c:out value="${(item.itemName)}"></c:out>
                                                    >
                                                <c:out value="${(item.itemName)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </h3>
                                    <h3>
                                        Choose order:
                                    <select name="orderNumber">
                                        <c:forEach items="${Orders}" var="item">
                                            <option value=
                                                        <c:out value="${(item.number)}"></c:out>
                                                    >
                                                <c:out value="${(item.number)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </h3>
                                    <input type="submit" class="submitbutton" value="Add Item to Order">
                                </form>
                            </div>

                            <div class="text_box" id="closeOrder">
                                <form action="/Freight/Employee/CloseOrder" method="post">
                                    <h1>Close order</h1>
                                    <h3>
                                        Choose order:
                                    <select name="orderNumber">

                                        <c:forEach items="${Orders}" var="item">
                                            <option value=
                                                        <c:out value="${(item.number)}"></c:out>
                                                    >
                                                <c:out value="${(item.number)}"></c:out>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    </h3>
                                    <input type="submit" class="submitbutton" value="Close order">
                                </form>

                            </div>

                            <div class="text_box" id="createItem">
                                <h1>Create new item</h1>
                                <form action="/Freight/Employee/AddItem" method="post">
                                    <h3>
                                        Enter item's name:
                                        <input type="text" placeholder="Item name" name="itemName" />
                                    </h3>
                                    <h3>
                                        Enter item's weight:
                                        <input type="text" placeholder="Weight" name="weight"/>
                                    </h3>
                                        <input type="submit" class="submitbutton" value="Add"/>
                                </form>
                            </div>

                            <div class="text_box" id="itemList">
                                <h1> Item list </h1>
                                <h3>
                                <c:forEach items="${Items}" var="item">
                                    >
                                    <c:out value="${item}"></c:out>
                                    <br>
                                </c:forEach>
                                    </h3>
                            </div>

                            <div class="text_box" id="orderList">
                                <h1>Order List</h1>
                                <h3>
                                <c:forEach items="${Orders}" var="item">
                                    >
                                    <c:out value="${item}"></c:out>
                                    <br>
                                </c:forEach>
                                </h3>
                            </div>


                        </div>
                    </div>
                    <div class="side_bar">
                            <div class="widget_style" id="ccategories">
                       			<div class="inside">
                                    <div class="left_top">
                                        <div class="right_top">
                                            <div class="left_bot">
                                                <div class="right_bot">
                                                    <div style = "background-image:url(${pageContext.request.contextPath}/resources/images/bull_2.gif); background-repeat: no-repeat; background-position: 7px 8px" class="inside_title_box"><h2>Driver Panel</h2></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <ul>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" id="sf" href="#" onclick="openDriverList();">Driver List</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" id="f"  href="#" onclick="openAddDriver();">Add Driver</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div style = "width: 100%" class="widget_style" id="darchives">
                                <div class="inside">
                                	<div class="left_top">
                                        <div class="right_top">
                                            <div class="left_bot">
                                                <div class="right_bot">
                                                    <div style = "background-image:url(${pageContext.request.contextPath}/resources/images/bull_2.gif); background-repeat: no-repeat; background-position: 7px 8px" class="inside_title_box"><h2>Wagon Panel</h2></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <ul>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openWagonList();">Wagon List</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openAddWagon();">Add Wagon</a></li>
                                    </ul>
                                </div>
                            </div>
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
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openCreateOrder();">Create new order</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openOrderList();">Order List</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openAddDriverToWagon();">Add driver to wagon</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openAddWagonToOrder();">Add wagon to order</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openAddItemToOrder();">Add item to order</a></li>
                                        <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openCloseOrder();">Close order</a></li>
                                    </ul>
                                </div>
                        </div>
                        <div class="widget_style" id="metad">
                            <div class="inside">
                                <div class="left_top">
                                    <div class="right_top">
                                        <div class="left_bot">
                                            <div class="right_bot">
                                                <div style = "background-image:url(${pageContext.request.contextPath}/resources/images/bull_2.gif); background-repeat: no-repeat; background-position: 7px 8px" class="inside_title_box"><h2>Item panel</h2></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <ul>
                                    <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openCreateItem();">Create new item</a></li>
                                    <li><a style = "background-image:url(${pageContext.request.contextPath}/resources/images/arr.gif)" href="#" onclick="openItemList();">Item list</a></li>
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
