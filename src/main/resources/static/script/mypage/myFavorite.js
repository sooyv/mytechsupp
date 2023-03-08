const userId = sessionStorage.getItem('userId');

let currentBlock = 1; // 현재 보여지는 페이지 블록 (기본값 1)
const blockPerPage = 5; // 한 화면에 보여질 페이지 블록의 개수
const productPerPage = 10; // 한 페이지에 보여질 제품의 개수
