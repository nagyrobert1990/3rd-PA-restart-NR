function createDetailsTableBody(details) {
    detailsTableBodyEl = document.createElement('tbody');

    for (let i = 0; i < details.length; i++) {
        const product = details[i];

        //creating id cell
        const idTdEl = document.createElement('td');
        idTdEl.textContent = product.id;

        //creating name cell
        const nameTdEl = document.createElement('td');
        nameTdEl.textContent = product.productName;

        //creating unitPrice cell
        const unitPriceTdEl = document.createElement('td');
        unitPriceTdEl.textContent = product.unitPrice;

        //creating row
        const trEl = document.createElement('tr');
        trEl.appendChild(idTdEl);
        trEl.appendChild(nameTdEl);
        trEl.appendChild(unitPriceTdEl);

        detailsTableBodyEl.appendChild(trEl);
    }
    return detailsTableBodyEl;
}

function createDetailsTableHead(){
    const idTdEl = document.createElement('th');
    idTdEl.textContent = 'Product Id';

    const nameTdEl = document.createElement('th');
    nameTdEl.textContent = 'Product Name';

    const unitPriceTdEl = document.createElement('th');
    unitPriceTdEl.textContent = 'Unit Price';

    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(nameTdEl);
    trEl.appendChild(unitPriceTdEl);

    const theadEl = document.createElement('thead');
    theadEl.appendChild(trEl);
    return theadEl;
}

function onDetailsRecieved() {
    showContents(['orders-button-content', 'products-button-content' , 'details-content', 'details', 'profile-content', 'logout-content']);

    const text = this.responseText;
    const details = JSON.parse(text);

    const detailsEl = document.getElementById('details');
    removeAllChildren(detailsEl);

    const tableEl = document.createElement('table');
    tableEl.appendChild(createDetailsTableHead());
    tableEl.appendChild(createDetailsTableBody(details));

    detailsEl.appendChild(tableEl);
}

function onOrderIdClicked() {
    const orderId = this.dataset.orderId;

    const params = new URLSearchParams();
    params.append('id', orderId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onDetailsRecieved);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/details?' + params.toString());
    xhr.send();
}