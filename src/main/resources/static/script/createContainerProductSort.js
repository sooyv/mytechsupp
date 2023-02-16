const productSortSearch = document.querySelector('.ContainerProductSort');

function createProductSortSearch() {
  productSortSearch.innerHTML = `
  <ul class="SortByKeywordAndSearch">
    <li><input class="Keyword" type="button" value="product" placeholder="제목"></li>
    <li><input class="Keyword" type="button" value="view" placeholder=조회수"></li>
    <li><input class="Keyword" type="button" value="investment" placeholder=투자율"></li>
    <li><input class="Keyword" type="button" value="wish" placeholder=즐겨찾기 순"></li>
    <li><input class="Keyword" type="button" value="date" placeholder=최신 순"></li>
  </ul>
  <div>
    <input class="ProductSearch" type="text" placeholder="검색창">
    <input type="submit">
  </div>
  `;
  // const keyword = document.querySelectorAll('.Keyword');
  // const url = location.href;

  // for (let i = 0; i < keyword.length; i++) {
  //   const urlKeyword = url.concat(`&keyword=${keyword[i].value}`
  //   keyword[i].addEventListener('click', 
  //     window.location = urlKeyword))
  // }
}
// 클릭 했을때 url 변경하고
// 그거를 db에서 읽는 형식으로 가야함
// 키워드가 뭔지 판별해야함


createProductSortSearch();