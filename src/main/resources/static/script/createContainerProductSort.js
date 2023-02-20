const productSortSearch = document.querySelector('.ContainerProductSort');

function createProductSortSearch() {


  productSortSearch.innerHTML = `
  <ul class="SortByKeyword">
    <li><input class="Keyword" type="button" value="product" placeholder="제목"></li>
    <li><input class="Keyword" type="button" value="view" placeholder=조회수"></li>
    <li><input class="Keyword" type="button" value="investment" placeholder=투자율"></li>
    <li><input class="Keyword" type="button" value="wish" placeholder=즐겨찾기 순"></li>
    <li><input class="Keyword" type="button" value="date" placeholder=최신 순"></li>
  </ul>
  <div class="SearchbyKeyword">
    <form action="http://localhost:8080/productMain/product?" method="get">
      <input type="hidden" name="page" value="0">
      <input type="hidden" name="order" value="0">
      <input class="ProductSearch" type="text" placeholder="검색창" name="keyword">
      <input class="ProductSendKeyWord" type="submit">
    </form>
  </div>
  `;
}
// 클릭 했을때 url 변경하고
// 그거를 db에서 읽는 형식으로 가야함
// 키워드가 뭔지 판별해야함




createProductSortSearch();