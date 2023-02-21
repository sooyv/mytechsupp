const feedbackSortSearch = document.querySelector('.ContainerFeedbackSort');

function createFeedbackSortSearch() {
  feedbackSortSearch.innerHTML = `
    <ul class="SortByKeywordAndSearch">
      <li><button>제목</button></li>
      <li><button>조회수</button></li>
      <li><button>즐겨찾기 순</button></li>
      <li><button>작성일</button></li>
      <li><button>투자성공/실패</button></li>
    </ul>
    <div>
      <input class="ProductSearch" type="text" placeholder="검색창">
    </div>
  `;
}

createFeedbackSortSearch();