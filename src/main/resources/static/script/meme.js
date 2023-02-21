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



create table feedback (
  seq int not Null auto_increment,
  picture varchar(20),
  product varchar(20),
  information varchar(20),
  score varchar(20),
  limitdate varchar(20),
  status varchar(20)
)

create table userfeedback (
  seq int not null auto_increment,
  userpicture varchar(20),
  userscore varchar(20),
  userid varchar(20),
  usertext varchar(20),
  product varchar(20)
)


