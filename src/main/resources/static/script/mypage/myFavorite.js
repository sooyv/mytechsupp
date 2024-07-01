//const productList = document.querySelector(".notice_board ul");
//const productList = document.querySelectorAll(".notice_board ul.board_row:not(.title_row) li");
const products = document.querySelectorAll('.board_row:not(.title_row)')
//console.log(productList)
//const products = Array.from(productList.querySelectorAll("ul.board_row"));


const pageSize = 10;
let currentPage = 1;
const totalProducts = products.length;
const totalPages = Math.ceil(totalProducts / pageSize);

console.log(totalProducts);

function loadProducts() {
  // 현재 페이지에 해당하는 상품 인덱스 계산
  const startIndex = (currentPage - 1) * pageSize;
  const endIndex = Math.min(startIndex + pageSize, products.length);

  // 현재 페이지에 해당하는 상품들만 표시
  for (let i = 0; i < products.length; i++) {
    if (i >= startIndex && i < endIndex) {
      products[i].style.display = "block";
    } else {
      products[i].style.display = "none";
    }
  }
}

function nextBlock() {
  currentPage = Math.min(currentPage + 1, totalPages);
  currentPage = currentPage < 1 ? 1 : currentPage;
  loadProducts();
  generatePageBlock();
  history.pushState({}, '', `?page=${currentPage}`);
}

function prevBlock() {
  currentPage = Math.max(currentPage - 1, 1);
  loadProducts();
  generatePageBlock();
  history.pushState({}, '', `?page=${currentPage}`);
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
      loadProducts();
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
      pageBtn.classList.add("active");
    }
    pageBtn.addEventListener("click", () => {
      currentPage = i;
      loadProducts();
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
      loadProducts();
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
} else if (window.location.pathname === "/myfavorite") { // myfavorite 페이지에 처음 진입한 경우 currentPage를 1로 설정
  currentPage = 1;
  history.replaceState({}, '', '/user/myfavorite'); // 쿼리스트링을 추가하지 않고 경로만 변경
}
for (let i = 0; i < products.length; i++) {
  products[i].addEventListener('click', () => {
    const productId = products[i].querySelector('li:nth-child(1)').textContent;
    const productUrl = `http://localhost:8080/productSelect/product/?&num=${productId}`;
    location.href = productUrl;
  });
}


window.addEventListener("load", loadProducts);



loadProducts();
generatePageBlock();
if (currentPage !== 1) { // currentPage가 1일 때는 쿼리스트링을 추가하지 않음
  history.pushState({}, '', `?page=${currentPage}`);
}


// function goMyPage() {
//     location.href = "mypage";
// }
// function goMyFavorite() {
//     location.href = "myfavorite";
// }
// function goMyPassword() {
//     location.href = "editpassword";
// }
// function goMyCheckPassword() {
//     location.href = "checkpassword";
// }
// function goMyEdit() {
//     location.href = "edituser";
// }
