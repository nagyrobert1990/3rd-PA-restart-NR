<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/index.js" var="indexScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/profile.js" var="profileScriptUrl"/>
        <c:url value="/products.js" var="productsScriptUrl"/>
        <c:url value="/orders.js" var="ordersScriptUrl"/>
        <c:url value="/order-details.js" var="detailsScriptUrl"/>
        <c:url value="/order.js" var="orderScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${indexScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${profileScriptUrl}"></script>
        <script src="${productsScriptUrl}"></script>
        <script src="${ordersScriptUrl}"></script>
        <script src="${detailsScriptUrl}"></script>
        <script src="${orderScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <title>WebApp</title>
    </head>
    <body>
        <div id="login-content" class="content">
            <h1>Login</h1>
            <form id="login-form" onsubmit="return false;">
                <input type="text" name="id">
                <button id="login-button">Login</button>
            </form>
        </div>

        <div id="profile-content" class="hidden content">
            <h1>Profile</h1>
            <p id="user-id-label"></p>
            <p id="user-type-label"></p>
            <p id="user-field-1-label"></p>
            <p id="user-field-2-label"></p>
        </div>
        
        <div id="products-button-content" class="hidden content">
            <button id="products-button">Products</button>
        </div>

        <div id="orders-button-content" class="hidden content">
            <button id="orders-button">Orders</button>
        </div>

        <div id="order-button-content" class="hidden content">
            <button id="order-button">Order</button>
        </div>

        <div id="order-form-content" class="hidden content">
            <h2>Order products</h2>
            <form id="order-form" onsubmit="return false;">
                <div id="input-fields">
                    <input type="text" name="product-id" placeholder="Product Id">
                    <input type="text" name="quantity" placeholder="Quantity"><br>
                </div>
                <button onclick="onAddProductClicked();">Add product</button>
                <button onclick="onOrderClicked();">Order</button>
            </form>
        </div>

        <div id="products-content" class="hidden content">
            <h3>Available products</h3>
            <div id="products" class="hidden content">

            </div>
        </div>

        <div id="orders-content" class="hidden content">
            <h3>My Orders</h3>
            <div id="orders" class="hidden content">

            </div>
        </div>

        <div id="details-content" class="hidden content">
            <h3>Order Details</h3>
            <div id="details" class="hidden content">

            </div>
        </div>

        <div id="logout-content" class="hidden content">
            <button id="logout-button">Logout</button>
        </div>
    </body>
</html>
