const userId = sessionStorage.getItem('userId');

let currentBlock = 1; // 현재 보여지는 페이지 블록 (기본값 1)
const blockPerPage = 5; // 한 화면에 보여질 페이지 블록의 개수
const productPerPage = 10; // 한 페이지에 보여질 제품의 개수
const totalPage = Math.ceil(product.length / productPerPage); // 전체 페이지 수
const totalBlock = Math.ceil(totalPage / blockPerPage); // 전체 페이지 블록 수

// 다음 페이지 블록 보여주는 함수
function nextBlock() {
  currentBlock++; // 현재 페이지 블록을 1 증가시킴

  if (currentBlock > totalBlock) {
    currentBlock = totalBlock;
  }

  renderProducts(); // 제품 목록을 다시 렌더링함
}

// 이전 페이지 블록 보여주는 함수
function prevBlock() {
  currentBlock--; // 현재 페이지 블록을 1 감소시킴

  if (currentBlock < 1) {
    currentBlock = 1;
  }

  renderProducts(); // 제품 목록을 다시 렌더링함
}

// 제품 목록 렌더링 함수 (현재 보여질 페이지 블록에 해당하는 제품들만 보여짐)
function renderProducts() {
  const start = (currentBlock - 1) * blockPerPage * productPerPage; // 시작 인덱스
  const end = start + productPerPage * blockPerPage; // 끝 인덱스

  // 제품 목록을 보여줄 div 요소를 찾음 여기 수정해야함
  const productList = document.getElementById('productList');

  // 이전에 있던 제품 목록을 모두 지움


  // 현재 페이지 블록에 해당하는 제품들을 보여줌
  for (let i = start; i < end && i < product.length; i++) {
    const item = product[i];

    // 제품 정보를 보여줄 요소들을 생성함
    const productItem = document.createElement('div');
    const productImg = document.createElement('img');
    const productName = document.createElement('h3');
    const productPrice = document.createElement('span');

    // 제품 정보를 요소들에 적용함
    productImg.src = item.imgUrl;
    productName.textContent = item.name;
    productPrice.textContent = item.price;

    // 제품 정보 요소들을 제품 목록 요소에 추가함
    productItem.appendChild(productImg);
    productItem.appendChild(productName);
    productItem.appendChild(productPrice);
    productList.appendChild(productItem);
  }
}

// 페이지 로드 시 제품 목록을 렌더링함
//renderProducts();
