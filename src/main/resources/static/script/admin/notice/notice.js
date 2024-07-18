$(document).ready(function() {
    $(".deleteConfirm").on("click", function(event) {
        event.preventDefault();
        const noticeId = $(this).data("noticeId");

        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                type: 'DELETE',
                url: "/admin/notice/delete/" + noticeId,
                contentType: 'application/json',
                data: { noticeId: noticeId },
                success: function(response) {
                    console.log("Success:", response);
                    alert("삭제했습니다.");
                    location.reload();
                },
                error: function(error) {
                    alert("삭제에 실패했습니다.");
                    console.log(error)
                }
            });
        }
    });
});