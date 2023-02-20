// const paging = document.querySelector('.PagingContainer');
// const page = document.querySelectorAll('.PageButton');

// const url = location.href;
// const urlParams = new URL(url).searchParams;
// const pageNumber = urlParams.get('page');
// const orderNumber = Math.ceil(urlParams.get('order'));
// const urlStringKeyword = `&keyword=`;
// const keyword = urlParams.get('keyword');

function createPaging(numberOfdata) {
  let urlStringPage = `/productMain/product?page=`;
  let urlStringOrder = `&order=`;
  let pagingLimit = Math.ceil(numberOfdata / 5);
  console.log("=======")
  console.log(numberOfdata)
  console.log(pagingLimit)
  let prevPage = pageNumber;
  (pageNumber - 1 < 0 ? prevPage = 0 : --prevPage);
  let prevPageLocation = urlStringPage + prevPage  + urlStringOrder + (prevPage * 10)
  
  function pageNumberLocationString(i) {
    return urlStringPage + +pageNumber + urlStringOrder + (+pageNumber * 10 + i - 1);
  }

  paging.innerHTML = `
    <ul class="PagingList">
      <li class="Page">
        <input type="button" class="PageButton" onclick=window.location.href="${prevPageLocation}" value="${"<"}">
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
      input.setAttribute('onclick', `window.location.href="${pageNumberLocationString(i)}"`);
      input.setAttribute('value', `${(+pageNumber * 10 + i)}`);

    li.appendChild(input);
    pagingList.appendChild(li);
  }
  let nextPage = +pageNumber;
  (document.querySelectorAll('.Page').length == 11 ? ++nextPage : nextPage);

  let nextPageLocation = urlStringPage + nextPage + urlStringOrder + (+nextPage * 10)

  let li = document.createElement("li");
    li.classList.add('Page');

  let input = document.createElement("input");
    input.classList.add('PageButton');
    input.setAttribute('type', 'button');
    input.setAttribute('onclick', `window.location.href="${nextPageLocation}"`);
    input.setAttribute('value', `>`);

  li.appendChild(input);
  pagingList.appendChild(li);

  
  
  if(keyword != null ) {
    let keywordURLQuery = urlStringKeyword + keyword;
    let pageButtonList = document.querySelectorAll('.PageButton');
    pageButtonList[0].setAttribute('onclick', `window.location.href="${prevPageLocation + keywordURLQuery}"`);
    for (let i = 1; i < pageButtonList.length - 1; i++){
      pageButtonList[i].setAttribute('onclick', `window.location.href="${pageNumberLocationString(i) + keywordURLQuery}"`);
    }
    pageButtonList[pageButtonList.length -1].setAttribute('onclick', `window.location.href="${nextPageLocation + keywordURLQuery}"`);
  }; 
}

function getNumbersOfProductToCreatePaging(pageNumber, keyword) {  
  fetch(`/api/paging/product?page=${pageNumber}&order=0&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => createPaging(data))
}

getNumbersOfProductToCreatePaging(pageNumber, keyword)




