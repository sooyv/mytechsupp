$(document).ready(function() {
    $(".deleteConfirm").on("click", function(event) {
        event.preventDefault();
        const faqId = $(this).data("faqId");

        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                type: 'DELETE',
                url: "/admin/faq/delete/" + faqId,
                contentType: 'application/json',
                data: { faqId: faqId },
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