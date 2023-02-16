let a = {

  testname: "anameOK",
  testphone: 1234,
  testaddress: "addressOK"

}

let b = {

  testname: "bnameOK",
  testphone: 12345,
  testaddress: "baddressOK"

}

let c = { a:
  {

    testname: "anameOK",
    testphone: 1234,
    testaddress: "addressOK"
  
  },
b:
  {

  testname: "bnameOK",
  testphone: 12345,
  testaddress: "baddressOK"

}}
let paging = document.querySelector("asdf");
paging.innerHTML = `
<ul class="PagingList">
  <li class="Page">
    <input type="button" class="PageButton" onclick="location.href='${urlString + 0}'" value="${"<"}">
  </li>
`;
for (let i = 1; i < pagingLimit; i++) {
  paging.innerHTML += `
  <li class="Page">
    <input type="button" class="PageButton" onclick="location.href='${urlString + (i-1)}'" value="${i}">
  </li>
  `
}
paging.innerHTML += `
<li class="Page">
    <input type="button" class="PageButton" onclick="location.href='${urlString + pagingLimit}'" value="${">"}">
  </li>
</ul>`;


let li = document.createElement("li");

li.classList.add('Page');

let input = document.createElement("input");
input.classList.add('PageButton');
input.setAttribute('type', button);
input.setAttribute('onclick', "location.href='${urlString + 0}'");
input.setAttribute('value', `${"<"}`);


b.appendChild(li);
li.appendChild(input);

let b = document.querySelector('.PagingList');