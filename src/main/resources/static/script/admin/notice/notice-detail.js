$(function () {
    console.log("notice_details");

    // 이벤트 위임을 사용하여 동적으로 추가된 요소에 이벤트를 바인딩합니다.
    $(document).on("click", "#deleteFileBtn", function (event) {
        console.log("첨부파일 삭제 클릭");
        event.preventDefault();
        const noticeId = $(this).data("noticeId");
        const originalFileName = $("#originalFileName").text();
        console.log(originalFileName)

        if (originalFileName) {
            if (confirm('정말 삭제하시겠습니까?')) {
                $.ajax({
                    type: 'DELETE',
                    url: "/admin/attachedfile/delete/" + noticeId,
                    contentType: 'application/json',
                    data: JSON.stringify({ noticeId: noticeId }),
                    success: function(response) {
                        console.log("Success:", response);
                        alert("첨부파일 삭제.");
                        window.location.reload();
                    },
                    error: function(error) {
                        alert("삭제에 실패했습니다.");
                        console.log(error);
                    }
                });
            }
        } else {
            alert("삭제할 첨부파일이 존재하지 않습니다.");
        }

    });
});