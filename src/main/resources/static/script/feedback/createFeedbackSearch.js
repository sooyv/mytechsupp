const feedbackSortSearch = document.querySelector('.ContainerFeedbackSort');

function createFeedbackSortSearch() {
  feedbackSortSearch.innerHTML = `
  <ul class="SortByKeyword">
    <li><input class="Keyword" type="button" value="product" placeholder="제목"></li>
    <li><input class="Keyword" type="button" value="view" placeholder=조회수"></li>
    <li><input class="Keyword" type="button" value="investment" placeholder=즐겨찾기 순"></li>
    <li><input class="Keyword" type="button" value="wish" placeholder=작성일"></li>
    <li><input class="Keyword" type="button" value="date" placeholder=투자성공/실패"></li>
  </ul>
  <div class="SearchbyKeyword">
    <form action="http://localhost:8080/feedbackMain/product?" method="get">
      <input type="hidden" name="page" value="0">
      <input type="hidden" name="order" value="0">
      <input type="text submit" class="ProductSearch" placeholder="Search..." name="keyword">
    </form>
  </div>
  `;
}

createFeedbackSortSearch();