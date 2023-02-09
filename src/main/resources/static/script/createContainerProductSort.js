const productSortSearch = document.querySelector('.ContainerProductSort');

function createProductSortSearch() {
  productSortSearch.innerHTML = `
  <ul class="SortKeyword">
    <li><button>제목</button></li>
    <li><button>조회수</button></li>
    <li><button>투자율</button></li>
    <li><button>즐겨찾기 순</button></li>
    <li><button>작성일</button></li>
  </ul>
  <div>
    <input class="ProductSearch" type="text" placeholder="검색창">
  </div>
  `
}

createProductSortSearch();