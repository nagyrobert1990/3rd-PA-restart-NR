function createOrdersTableBody(orders) {
    ordersTableBodyEl = document.createElement('tbody');

    for (let i = 0; i < orders.length; i++) {
        const order = orders[i];

        //creating link for id
        const aEl = document.createElement('a');
        aEl.textContent = order.id;
        aEl.href = 'javascript:void(0);';
        aEl.dataset.orderId = order.id;
        aEl.addEventListener('click', onOrderIdClicked);

        //creating id cell
        const idTdEl = document.createElement('td');
        idTdEl.appendChild(aEl);

        //creating numOfProductsOrdered cell
        const numOfProductsOrderedTdEl = document.createElement('td');
        numOfProductsOrderedTdEl.textContent = order.numOfProductsOrdered;

        //creating totalPrice cell
        const totalPriceTdEl = document.createElement('td');
        totalPriceTdEl.textContent = order.totalPrice;

        //creating row
        const trEl = document.createElement('tr');
        trEl.appendChild(idTdEl);
        trEl.appendChild(numOfProductsOrderedTdEl);
        trEl.appendChild(totalPriceTdEl);

        ordersTableBodyEl.appendChild(trEl);
    }
    return ordersTableBodyEl;
}

function createOrdersTableHead(){
    const idTdEl = document.createElement('th');
    idTdEl.textContent = 'Order Id';

    const numOfProductsOrderedTdEl = document.createElement('th');
    numOfProductsOrderedTdEl.textContent = 'Number Of Products Ordered';

    const totalPriceTdEl = document.createElement('th');
    totalPriceTdEl.textContent = 'Total Price';

    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(numOfProductsOrderedTdEl);
    trEl.appendChild(totalPriceTdEl);

    const theadEl = document.createElement('thead');
    theadEl.appendChild(trEl);
    return theadEl;
}

function onOrdersRecieved() {
    showContents(['orders-button-content', 'products-button-content' , 'orders-content', 'orders', 'profile-content', 'logout-content']);

    const text = this.responseText;
    const orders = JSON.parse(text);

    const ordersEl = document.getElementById('orders');
    removeAllChildren(ordersEl);

    const tableEl = document.createElement('table');
    tableEl.appendChild(createOrdersTableHead());
    tableEl.appendChild(createOrdersTableBody(orders));

    ordersEl.appendChild(tableEl);
}

function onOrdersButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onOrdersRecieved);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/orders');
    xhr.send();
}