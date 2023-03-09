const productList = document.querySelector(".notice_board ul.board_row:not(.title_row)");
const products = productList.getElementsByTagName("li");
const pageSize = 10;
let currentPage = 1;
const totalPages = Math.ceil(products.length / pageSize);

function nextBlock() {
  currentPage = Math.min(currentPage + 10, totalPages);
  generatePageBlock();
}

function prevBlock() {
  currentPage = Math.max(currentPage - 10, 1);
  generatePageBlock();
}

function generatePageBlock() {
  const startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
  const endPage = Math.min(startPage + 9, totalPages);
  const block = document.querySelector(".pagination .block");
  block.innerHTML = "";
  if (startPage > 1) {
    const beforeBtn = document.createElement("button");
    beforeBtn.className = "page_btn before_move";
    beforeBtn.innerText = "이전";
    beforeBtn.addEventListener("click", () => {
      currentPage = startPage - 1;
      generatePageBlock();
      history.pushState({}, '', `?page=${currentPage}`);
    });
    block.appendChild(beforeBtn);
  }
  for (let i = startPage; i <= endPage; i++) {
    const pageBtn = document.createElement("button");
    pageBtn.className = "page_btn";
    pageBtn.innerText = i;
    if (i === currentPage) {
      pageBtn.className += " active";
    }
    pageBtn.addEventListener("click", () => {
      currentPage = i;
      generatePageBlock();
      history.pushState({}, '', `?page=${currentPage}`);
    });
    block.appendChild(pageBtn);
  }
  if (endPage < totalPages) {
    const nextBtn = document.createElement("button");
    nextBtn.className = "page_btn next_move";
    nextBtn.innerText = "다음";
    nextBtn.addEventListener("click", () => {
      currentPage = endPage + 1;
      generatePageBlock();
      history.pushState({}, '', `?page=${currentPage}`);
    });
    block.appendChild(nextBtn);
  }
}

// 페이지 로드 시 쿼리스트링에 따라 currentPage 설정
const urlParams = new URLSearchParams(window.location.search);
const pageParam = urlParams.get('page');
if (pageParam !== null) {
  currentPage = parseInt(pageParam);
}

generatePageBlock();
history.pushState({}, '', `?page=${currentPage}`); // 페이지 로드 시 쿼리스트링 추가
