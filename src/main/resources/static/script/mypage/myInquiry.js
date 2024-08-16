document.addEventListener('DOMContentLoaded', function() {
    const questionAccordions = document.querySelectorAll('.question-accordion');

    questionAccordions.forEach(accordion => {
        const accordionBtn = accordion.querySelector('.accordion-btn');
        const answerAccordion = accordion.querySelector('.accordion-answer');

        accordionBtn.addEventListener('click', function() {
            console.log("아코디언 버튼 클릭");
            if (answerAccordion.style.display === 'none' || answerAccordion.style.display === '') {
                answerAccordion.style.display = 'block';
                accordionBtn.display.hide();
                accordionBtn.innerHTML = '<i class="fa-solid fa-chevron-up"></i>'; // 화살표 아이콘 변경
            } else {
                answerAccordion.style.display = 'none';
                accordionBtn.display.hide();
                accordionBtn.innerHTML = '<i class="fa-solid fa-chevron-down"></i>'; // 화살표 아이콘 변경
            }
        });
    });
});
