document.addEventListener('DOMContentLoaded', function() {
    const questionAccordions = document.querySelectorAll('.question-accordion');

    questionAccordions.forEach(accordion => {
        const accordionBtn = accordion.querySelector('.accordion-btn');
        const answerAccordion = accordion.querySelector('.accordion-answer');

        accordionBtn.addEventListener('click', function() {
            if (answerAccordion.style.display === 'none' || answerAccordion.style.display === '') {
                answerAccordion.style.display = 'block';
                accordionBtn.classList.remove('fa-chevron-down');
                accordionBtn.classList.add('fa-chevron-up');
            } else {
                answerAccordion.style.display = 'none';
                accordionBtn.classList.remove('fa-chevron-up');
                accordionBtn.classList.add('fa-chevron-down');
            }
        });
    });
});

$(document).ready(function() {
    $(".deleteConfirm").on("click", function(event) {
        event.preventDefault();
        const questionId = $(this).data("questionId");

        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                type: 'DELETE',
                url: "/user/inquiry/delete/" + questionId,
                contentType: 'application/json',
                data: {
                    questionId: questionId
                },
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
