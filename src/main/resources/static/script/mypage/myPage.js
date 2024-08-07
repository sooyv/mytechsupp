
function goMyPage() {
    location.href = "/user/mypage";
}
function goMyFavorite() {
    location.href = "/user/myfavorite";
}

function checkPwd(path) {
    window.location.href = "/user/checkpassword?path=" + encodeURIComponent(path);
}

document.addEventListener('DOMContentLoaded', function() {
    // 페이지 URL을 기준으로 현재 페이지를 결정
    const urlParams = new URLSearchParams(window.location.search);
    const path = urlParams.get('path');

    const pathname = window.location.pathname;


    // 모든 항목에서 'active' 클래스 제거
    const allItems = document.querySelectorAll('#mypage_side_box .btn_box');
    allItems.forEach(item => {
        item.classList.remove('active');
    });

    if (pathname.endsWith("/user/mypage")) {
        document.getElementById('mypage').classList.add('active');
    } else if (pathname.endsWith("/user/myfavorite")) {
        document.getElementById('myFavorite').classList.add('active');
    } else if (pathname.endsWith("/user/edituser") || (path && path.endsWith("/user/edituser"))) {
        document.getElementById('editUser').classList.add('active');
    } else if (pathname.endsWith("/user/editpassword") || (path && path.endsWith("/user/editpassword"))) {
        document.getElementById('editPwd').classList.add('active');
    }

    // 클릭 이벤트 처리
    document.querySelectorAll('#mypage_side_box .btn_box').forEach(item => {
        item.addEventListener('click', function() {
            // 모든 항목에서 'active' 클래스 제거
            allItems.forEach(i => i.classList.remove('active'));
            // 클릭된 항목에 'active' 클래스 추가
            this.classList.add('active');
        });
    });
});


// function goMyPassword() {
//     // location.href = "editpassword";
//     location.href = "/user/editPassword";
// }

// function goMyEdit() {
//     location.href = "/user/edituser";
// }
