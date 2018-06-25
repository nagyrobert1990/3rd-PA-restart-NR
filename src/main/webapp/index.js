const OK = 200;
const BAD_REQUEST = 400;
const UNAUTHORIZED = 401;
const NOT_FOUND = 404;
const INTERNAL_SERVER_ERROR = 500;

let loginContentDivEl;
let profileContentDivEl;

let productsButtonContentDivEl;
let productsContentDivEl;

let ordersButtonContentDivEl;
let ordersContentDivEl;

let detailsContentDivEl;

let orderButtonContentDivEl;
let orderContentDivEl;

let logoutContentDivEl;

function newInfo(targetEl, message) {
    newMessage(targetEl, 'info', message);
}

function newError(targetEl, message) {
    newMessage(targetEl, 'error', message);
}

function newMessage(targetEl, cssClass, message) {
    clearMessages();

    const pEl = document.createElement('p');
    pEl.classList.add('message');
    pEl.classList.add(cssClass);
    pEl.textContent = message;

    targetEl.appendChild(pEl);
}

function clearMessages() {
    const messageEls = document.getElementsByClassName('message');
    for (let i = 0; i < messageEls.length; i++) {
        const messageEl = messageEls[i];
        messageEl.remove();
    }
}

function showContents(ids) {
    const contentEls = document.getElementsByClassName('content');
    for (let i = 0; i < contentEls.length; i++) {
        const contentEl = contentEls[i];
        if (ids.includes(contentEl.id)) {
            contentEl.classList.remove('hidden');
        } else {
            contentEl.classList.add('hidden');
        }
    }
}

function removeAllChildren(el) {
    while (el.firstChild) {
        el.removeChild(el.firstChild);
    }
}

function onNetworkError(response) {
    document.body.remove();
    const bodyEl = document.createElement('body');
    document.appendChild(bodyEl);
    newError(bodyEl, 'Network error, please try reloading the page');
}

function onOtherResponse(targetEl, xhr) {
    if (xhr.status === NOT_FOUND) {
        newError(targetEl, 'Not found');
        console.error(xhr);
    } else {
        const json = JSON.parse(xhr.responseText);
        if (xhr.status === INTERNAL_SERVER_ERROR) {
            newError(targetEl, `Server error: ${json.message}`);
        } else if (xhr.status === UNAUTHORIZED || xhr.status === BAD_REQUEST) {
            newError(targetEl, json.message);
        } else {
            newError(targetEl, `Unknown error: ${json.message}`);
        }
    }
}

function hasAuthorization() {
    return localStorage.getItem('user') !== null;
}

function setAuthorization(user) {
    return localStorage.setItem('user', JSON.stringify(user));
}

function getAuthorization() {
    return JSON.parse(localStorage.getItem('user'));
}

function setUnauthorized() {
    return localStorage.removeItem('user');
}

function onLoad() {
    loginContentDivEl = document.getElementById('login-content');
    const loginButtonEl = document.getElementById('login-button');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);

    profileContentDivEl = document.getElementById('profile-content');

    productsButtonContentDivEl = document.getElementById('products-button-content');
    productsContentDivEl = document.getElementById('products-content');
    const productsButtonEl = document.getElementById('products-button');
    productsButtonEl.addEventListener('click', onProductsButtonClicked);

    ordersButtonContentDivEl = document.getElementById('orders-button-content');
    ordersContentDivEl = document.getElementById('orders-content');
    const ordersButtonEl = document.getElementById('orders-button');
    ordersButtonEl.addEventListener('click', onOrdersButtonClicked);

    detailsContentDivEl = document.getElementById('details-content');

    orderButtonContentDivEl = document.getElementById('order-button-content');
    orderContentDivEl = document.getElementById('order-form-content');
    const orderButtonEl = document.getElementById('order-button');
    orderButtonEl.addEventListener('click', onOrderButtonClicked);

    logoutContentDivEl = document.getElementById('logout-content');
    const logoutButtonEl = document.getElementById('logout-button');
    logoutButtonEl.addEventListener('click', onLogoutButtonClicked);

    if (hasAuthorization()) {
        onProfileLoad(getAuthorization());
    }
}

document.addEventListener('DOMContentLoaded', onLoad);