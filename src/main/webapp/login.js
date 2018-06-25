function onLoginResponse() {
    if (this.status === OK) {
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        onProfileLoad(user);
    } else {
        onOtherResponse(loginContentDivEl, this);
    }
}

function onLoginButtonClicked() {
    const loginFormEl = document.forms['login-form'];

    const idInputEl = loginFormEl.querySelector('input[name="id"]');

    const userId = idInputEl.value;

    const params = new URLSearchParams();
    params.append('id', userId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'login');
    xhr.send(params);
}