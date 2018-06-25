function onAddProductClicked() {
    const orderFormEl = document.getElementById('input-fields');

    const productIdInputEl = document.createElement("input");
    productIdInputEl.setAttribute('type', 'text');
    productIdInputEl.setAttribute('name', 'product-id');
    productIdInputEl.setAttribute('placeholder', 'Product Id');

    const quantityInputEl = document.createElement("input");
    quantityInputEl.setAttribute('type', 'text');
    quantityInputEl.setAttribute('name', 'quantity');
    quantityInputEl.setAttribute('placeholder', 'Quantity');

    const br = document.createElement("br");

    orderFormEl.appendChild(productIdInputEl);
    orderFormEl.appendChild(quantityInputEl);
    orderFormEl.appendChild(br);
}

function onOrderResponse(){
    if (this.status === OK) {
        clearMessages();
        showContents(['orders-button-content', 'order-button-content','products-content', 'products', 'profile-content', 'logout-content']);
        alert("Order successful!");
    } else {
        alert("Product id or quantity is missing.");
    }
}

function onOrderClicked() {
    const orderFormEl = document.getElementById('input-fields');

    const productIdInputArrEls = document.getElementsByName('product-id');
    const quantityInputArrEls = document.getElementsByName('quantity');

    var productIds = new Array();
    var quantities = new Array();

    for (var i = 0; i < productIdInputArrEls.length; i++) {
        productIds.push(productIdInputArrEls[i].value);
        quantities.push(quantityInputArrEls[i].value);
    }

    const params = new URLSearchParams();
    params.append('productIds', productIds);
    params.append('quantities', quantities);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onOrderResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/order');
    xhr.send(params);
}

function onOrderButtonClicked() {
    showContents(['order-form-content', 'orders-button-content', 'products-content', 'products', 'profile-content', 'logout-content']);
}