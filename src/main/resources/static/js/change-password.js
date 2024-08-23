const password = document.getElementById("password"),
    confirm_password = document.getElementById("confirmPassword");

enableSubmitButton();

function validatePassword() {
    if (password.value !== confirm_password.value) {
        confirm_password.setCustomValidity("Пароли не совпадают");
        return false;
    } else {
        confirm_password.setCustomValidity('');
        return true;
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

function enableSubmitButton() {
    document.getElementById('submitButton').disabled = false;
}

function disableSubmitButton() {
    document.getElementById('submitButton').disabled = true;
}

function validateSignupForm() {
    const form = document.getElementById('signupForm');
    for (let i = 0; i < form.elements.length; i++) {
        if (form.elements[i].value === '' && form.elements[i].hasAttribute('required')) {
            return false;
        }
    }
    if (!validatePassword()) {
        return false;
    }
    document.location = '/profile';
}