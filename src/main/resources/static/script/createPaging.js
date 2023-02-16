const paging = document.querySelector('.PagingContainer');
const page = document.querySelectorAll('.PageButton');

const url = location.href;
const urlParams = new URL(url).searchParams;
const pageNumber = urlParams.get('page');
const orderNumber = Math.ceil(urlParams.get('order'));


// db에서 데이터 갯수만 가져와서
// 그거를 이용해서 페이지 이동 버튼을 산술식으로 생성
// 페이지 개수 10개로 제한
// 구현 해야하는 것 
// 버튼을 눌렀을때 그 값을 url에 담아서 db로 보내주는게 필요함
// 10개가 넘어갔을때 11 로 넘어가거나 다시 10 이하의 페이지로 돌아가는 기능 필요함
// 굳이 다 받을 필요 없이 서버에서 50개 넘어가면 잘라서 보내주면 되지 않을까?

                     // < >     <> 안의 페이지 버튼 숫자
// /productMain/product?page=0&order=0
function createPaging(numberOfdata) {

  let urlStringPage = `/productMain/product?page=`;
  let urlStringOrder = `&order=`;
  let pagingLimit = Math.ceil(numberOfdata / 5);
  let prevPage = pageNumber;
  (pageNumber - 1 < 0 ? prevPage = 0 : prevPage = pageNumber - 1);

  paging.innerHTML = `
    <ul class="PagingList">
      <li class="Page">
        <input type="button" class="PageButton" onclick=window.location.href="${urlStringPage + prevPage  + urlStringOrder + 0}" value="${"<"}">
      </li>
    </ul>
    `;
  const pagingList = document.querySelector('.PagingList');
  for (let i = 1; i <= pagingLimit; i++) {
    let li = document.createElement("li");
    li.classList.add('Page');

    let input = document.createElement("input");
    input.classList.add('PageButton');
    input.setAttribute('type', 'button');
    input.setAttribute('onclick', `window.location.href="${urlStringPage + +pageNumber + urlStringOrder + (+pageNumber * 10 + i - 1)}"`);
    input.setAttribute('value', `${(+pageNumber * 10 + i)}`);

    li.appendChild(input);
    pagingList.appendChild(li);
  }
  let nextPage = +pageNumber;
  (document.querySelectorAll('.Page').length == 11 ? ++nextPage : nextPage);

  let li = document.createElement("li");
  li.classList.add('Page');

  let input = document.createElement("input");
  input.classList.add('PageButton');
  input.setAttribute('type', 'button');
  input.setAttribute('onclick', `window.location.href="${urlStringPage + nextPage + urlStringOrder + (+nextPage * 10)}"`);
  input.setAttribute('value', `>`);

  li.appendChild(input);
  pagingList.appendChild(li);
}

// 먼저 해야하는 것
//  > 눌렀을때 page 번호가 + 10되어야함
// 그러면 그 url을 페이징 함수가 읽어서 새롭게 만들어줘야함
// 그렇다면 > 을 눌렀을때 이동 되도독 하는 함수를 작성
// < 도 마찬가지



// 페이지 버튼 눌렀을때 값을 db로 보내서 새로운 json 받는 것
// 각 버튼에 이벤트를 추가하고
// 버튼을 눌렀을때의 값을 설정해서
// 그걸 서버에 요청

// 모든 상품을 조회해서 가져와서 paging 생성하는 함수


function getNumbersOfProductToCreatePaging(value) {
  fetch(`/api/paging/${value}`)
  .then(response => response.json())
  .then(data => createPaging(data))
}

getNumbersOfProductToCreatePaging(pageNumber);


// function getNumbersOfProductToCreatePaging(value) {
//   fetch(`/api/paging/${value}`)
//   .then(response => response.json())
//   .then(data => {
//     createPaging(data);
//   })
//   .catch(error => {
//     console.error(error);
//   });
// }

// getNumbersOfProductToCreatePaging(pageNumber);