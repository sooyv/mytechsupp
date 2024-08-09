$(document).ready(function () {
    // 페이지 로드 시 현재 URL에 따라 활성화할 항목을 결정
    const path = window.location.pathname;
    $("#lnb .swiper-slide").removeClass("active");
    if (path.endsWith("/cs/notice-list")) {
        $("#noticelnb").addClass("active");
    } else if (path.endsWith("/cs/faq-list")) {
        $("#faqlnb").addClass("active");
    } else if (path.endsWith("/cs/question-list")) {
        $("#questionlnb").addClass("active");
    }

    // 클릭 이벤트 처리
    $("#lnb .swiper-slide a").on("click", function () {
        $("#lnb .swiper-slide").removeClass("active");
        $(this).parent().addClass("active");
    });
});
