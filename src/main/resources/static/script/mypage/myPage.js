
function goMyPage() {
    location.href = "/user/mypage";
}
function goMyFavorite() {
    location.href = "/user/myfavorite";
}

function checkPwd(path) {
    window.location.href = "/user/checkpassword?path=" + encodeURIComponent(path);
}

// function goMyPassword() {
//     // location.href = "editpassword";
//     location.href = "/user/editPassword";
// }

// function goMyEdit() {
//     location.href = "/user/edituser";
// }
