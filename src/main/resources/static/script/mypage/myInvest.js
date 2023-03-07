// 총 페이지 수와 현재 페이지 변수
var totalPages = 10;
var currentPage = 1;

// 이전 버튼 클릭 시 이벤트 처리
document.getElementById("prevBtn").addEventListener("click", function() {
  if (currentPage > 1) {
    currentPage--;
    displayPagination();
  }
});

// 다음 버튼 클릭 시 이벤트 처리
document.getElementById("nextBtn").addEventListener("click", function() {
  if (currentPage < totalPages) {
    currentPage++;
    displayPagination();
  }
});

// 페이지 버튼 생성 함수
function displayPagination() {
  var pagination = document.querySelector(".pagination");
  pagination.innerHTML = "";

  // 이전 버튼 생성
  var prevButton = document.createElement("li");
  prevButton.innerHTML = "<a href='#'>&laquo;</a>";
  prevButton.addEventListener("click", function() {
    if (currentPage > 1) {
      currentPage--;
      displayPagination();
    }
  });

  // 다음 버튼 생성
  var nextButton = document.createElement("li");
  nextButton.innerHTML = "<a href='#'>&raquo;</a>";
  nextButton.addEventListener("click", function() {
    if (currentPage < totalPages) {
      currentPage++;
      displayPagination();
    }
  });

  // 페이지 버튼 생성
  for (var i = 1; i <= totalPages; i++) {
    var pageButton = document.createElement("li");
    pageButton.innerHTML = "<a href='#'>" + i + "</a>";
    pageButton.addEventListener("click", function() {
      currentPage = parseInt(this.innerText);
      displayPagination();
    });

    // 현재 페이지 버튼 스타일 적용
    if (i === currentPage) {
      pageButton.classList.add("active");
    }

    pagination.appendChild(pageButton);
  }

  // 이전 버튼, 다음 버튼 추가
  pagination.insertBefore(prevButton, pagination.firstChild);
  pagination.appendChild(nextButton);
}

// 페이지 버튼 생성 함수 호출
displayPagination();
