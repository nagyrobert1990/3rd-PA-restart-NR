const userTypeEl = document.getElementById('user-type-label');

function createProductsTableBody(products) {
    productsTableBodyEl = document.createElement('tbody');

    for (let i = 0; i < products.length; i++) {
        const product = products[i];

        //creating id cell
        const idTdEl = document.createElement('td');
        idTdEl.textContent = product.id;

        //creating name cell
        const nameTdEl = document.createElement('td');
        nameTdEl.textContent = product.productName;

        //creating unitPrice cell
        const unitPriceTdEl = document.createElement('td');
        unitPriceTdEl.textContent = product.unitPrice;

        //creating unitsInStock cell
        const unitsInStockTdEl = document.createElement('td');
        unitsInStockTdEl.textContent = product.unitsInStock;

        //creating categoryName cell
        const categoryNameTdEl = document.createElement('td');
        categoryNameTdEl.textContent = product.categoryName;

        //creating supplierName cell
        const supplierNameTdEl = document.createElement('td');
        supplierNameTdEl.textContent = product.supplierName;

        //creating row
        const trEl = document.createElement('tr');
        trEl.appendChild(idTdEl);
        trEl.appendChild(nameTdEl);
        trEl.appendChild(unitPriceTdEl);
        trEl.appendChild(unitsInStockTdEl);
        trEl.appendChild(categoryNameTdEl);
        trEl.appendChild(supplierNameTdEl);

        productsTableBodyEl.appendChild(trEl);
    }
    return productsTableBodyEl;
}

function createProductsTableHead(){
    const idTdEl = document.createElement('th');
    idTdEl.textContent = 'Product Id';

    const nameTdEl = document.createElement('th');
    nameTdEl.textContent = 'Product Name';

    const unitPriceTdEl = document.createElement('th');
    unitPriceTdEl.textContent = 'Unit Price';

    const unitsInStockTdEl = document.createElement('th');
    unitsInStockTdEl.textContent = 'Units In Stock';

    const categoryNameTdEl = document.createElement('th');
    categoryNameTdEl.textContent = 'Category Name';

    const supplierNameTdEl = document.createElement('th');
    supplierNameTdEl.textContent = 'Supplier Name';

    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(nameTdEl);
    trEl.appendChild(unitPriceTdEl);
    trEl.appendChild(unitsInStockTdEl);
    trEl.appendChild(categoryNameTdEl);
    trEl.appendChild(supplierNameTdEl);

    const theadEl = document.createElement('thead');
    theadEl.appendChild(trEl);
    return theadEl;
}

function onProductsRecieved() {
    if (userTypeEl == "Employee"){
        showContents(['products-content', 'products', 'profile-content', 'logout-content']);
    }
    else {
        showContents(['orders-button-content', 'order-button-content', 'products-content', 'products', 'profile-content', 'logout-content']);
    }

    const text = this.responseText;
    const products = JSON.parse(text);

    const productsEl = document.getElementById('products');
    removeAllChildren(productsEl);

    const tableEl = document.createElement('table');
    tableEl.appendChild(createProductsTableHead());
    tableEl.appendChild(createProductsTableBody(products));

    productsEl.appendChild(tableEl);
}

function onProductsButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onProductsRecieved);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/products');
    xhr.send();
}