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
