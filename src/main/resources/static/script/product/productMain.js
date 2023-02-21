const paging = document.querySelector('.PagingContainer');
const page = document.querySelectorAll('.PageButton');
const productList = document.querySelector('.ContainerProductList');
const investProductContent = document.querySelector('.ProductContent');
const SingleProduct = document.querySelector('.ContainerProduct');

const url = location.href;
const urlParams = new URL(url).searchParams;
const pageNumber = urlParams.get('page');
// const orderNumberCeil = Math.ceil(urlParams.get('order'));
const urlStringKeyword = `&keyword=`;
const keyword = urlParams.get('keyword');
const orderNumber = urlParams.get('order');
const productNumber = urlParams.get('num');




function gotoProductMain() {
  const mainURL = '/';
  const url = new URL('/productMain', [mainURL]);
  return url;
}
