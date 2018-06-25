function onProfileLoad(user) {
    clearMessages();

    const userIdLabelEl = document.getElementById('user-id-label');
    const userTypeLabelEl = document.getElementById('user-type-label');
    const userFieldOneLabelEl = document.getElementById('user-field-1-label');
    const userFieldTwoLabelEl = document.getElementById('user-field-2-label');

    userIdLabelEl.textContent = "Id:\t" + user.id;

    if (!isNaN(user.id)) {
        userTypeLabelEl.textContent = "User Type:\t" + "Employee";
        userFieldOneLabelEl.textContent = "First Name:\t" + user.firstName;
        userFieldTwoLabelEl.textContent = "Last Name:\t" + user.lastName;
        showContents(['products-button-content', 'profile-content', 'logout-content']);
    } else {
        userTypeLabelEl.textContent = "User Type:\t" + "Customer";
        userFieldOneLabelEl.textContent = "Company Name:\t" + user.companyName;
        userFieldTwoLabelEl.textContent = "Contact Name:\t" + user.contactName;
        showContents(['orders-button-content', 'products-button-content' , 'profile-content', 'logout-content']);
    }
}