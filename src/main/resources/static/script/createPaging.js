const paging = document.querySelector('.PagingContainer');
const page = document.querySelectorAll('.PageButton');

// db에서 데이터 갯수만 가져와서
// 그거를 이용해서 페이지 이동 버튼을 산술식으로 생성

// 구현 해야하는 것 
// 버튼을 눌렀을때 그 값을 url에 담아서 db로 보내주는게 필요함
function createPaging(numberOfdata) {
  paging.innerHTML = `<ul class="PagingList">`;
  console.log(numberOfdata);
  for (let i = 1; i <= (numberOfdata / 5); i++) {
    paging.innerHTML += `
    <li class="Page">
      <input type="button" class="PageButton" onclick="createFiveProduct(${i-1})" value="${i}">
    </li>
    `
  }
  paging.innerHTML += `</ul>`;
}

// 페이지 버튼 눌렀을때 값을 db로 보내서 새로운 json 받는 것
// 각 버튼에 이벤트를 추가하고
// 버튼을 눌렀을때의 값을 설정해서
// 그걸 서버에 요청





function getNumbersOfProductToCreatePaging() {
  fetch(`/api/paging`)
  .then(response => response.json())
  .then(data => createPaging(data))
}

getNumbersOfProductToCreatePaging();
