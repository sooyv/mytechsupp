//const form = document.getElementById("editPasswordForm");
const passwordInput = document.getElementById("password");
const checkPasswordInput = document.getElementById("checkPassword");


function validatePassword(){
if(passwordInput.value !== checkPasswordInput.value) {
checkPasswordInput.setCustomValidity("비밀번호가 일치하지 않습니다.");
} else {
checkPasswordInput.setCustomValidity('');
}
}

passwordInput.onchange = validatePassword;
checkPasswordInput.onkeyup = validatePassword;
//
//form.addEventListener("submit", event => {
//event.preventDefault();

const password = passwordInput.value;
const checkPassword = checkPasswordInput.value;

$(document).ready(function () {
$.ajax({
type: 'POST',
url: "mypage/editPassword",
data: {
password: password,
checkPassword: checkPassword
},
success: function (response) {
console.log(response);
},
error: function (error) {
console.log(error);
},
});
});
//});

// 비밀번호 일치 여부
function validatePassword(){
    const passwordValue = passwordInput.value.trim();
    const checkPasswordValue = checkPasswordInput.value.trim();
    if (passwordValue !== checkPasswordValue) {
        checkPasswordInput.setCustomValidity("비밀번호가 일치하지 않습니다.");
    } else {
        checkPasswordInput.setCustomValidity('');
    }
}

passwordInput.onchange = validatePassword;
checkPasswordInput.onkeyup = validatePassword;

checkPasswordInput.addEventListener("keyup", validatePassword);