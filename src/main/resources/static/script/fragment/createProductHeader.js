const head = document.querySelector(".Header");

function createProductHeaderHtml(api) {
  if (!url.includes("feedback")) {
    if(api.login == "success") {
      head.innerHTML =`
    <nav class="py-2 bg-light border-bottom">
      <div class="container d-flex flex-wrap">
        <ul class="nav me-auto">
          <li class="nav-item"><a href="/introduce/service" class="nav-link link-dark px-2">서비스 소개</a></li>
          <li class="nav-item"><a href="/productMain/product?page=0&order=0" class="nav-link link-dark px-2">펀딩</a></li>
          <li class="nav-item"><a href="//notice/paging" class="nav-link link-dark px-2">고객센터</a></li>
          <li class="nav-item"><a href="/feedbackMain/product?page=0&order=0" class="nav-link link-dark px-2">후기</a></li>
        </ul>
        <ul class="nav">
          <li class="nav-item"><a href="/member/logout" class="nav-link link-dark px-2">로그아웃</a></li>
          <li class="nav-item"><a href="/user/mypage" class="nav-link link-dark px-2">마이페이지</a></li>
        </ul>
      </div>
    </nav>
    <div class="py-3 mb-4 border-bottom">
      <div class="container d-flex flex-wrap justify-content-center">
        <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
          <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
          <span class="fs-4">TECHSUPP</span>
        </a>
      </div>
      <div class="SearchbyKeyword">
          <form class="col-12 col-lg-auto mb-3 mb-lg-0" action="/productMain/product?" method="get">
            <input type="hidden" name="page" value="0">
            <input type="hidden" name="order" value="0">
            <input type="text submit" class="form-control" placeholder="Search..." name="keyword">
          </form>
        </div>
    </div>`;
    } else {
      head.innerHTML =`
      <nav class="py-2 bg-light border-bottom">
        <div class="container d-flex flex-wrap">
          <ul class="nav me-auto">
            <li class="nav-item"><a href="/introduce/service" class="nav-link link-dark px-2">서비스 소개</a></li>
            <li class="nav-item"><a href="/productMain/product?page=0&order=0" class="nav-link link-dark px-2">펀딩</a></li>
            <li class="nav-item"><a href="/notice/paging" class="nav-link link-dark px-2">고객센터</a></li>
            <li class="nav-item"><a href="/feedbackMain/product?page=0&order=0" class="nav-link link-dark px-2">후기</a></li>
          </ul>
          <ul class="nav">
            <li class="nav-item"><a href="/login" class="nav-link link-dark px-2">로그인</a></li>
            <li class="nav-item"><a href="/signup" class="nav-link link-dark px-2">회원가입</a></li>
          </ul>
        </div>
      </nav>
      <div class="py-3 mb-4 border-bottom">
        <div class="container d-flex flex-wrap justify-content-center">
          <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
            <span class="fs-4">TECHSUPP</span>
          </a>
        </div>
        <div class="SearchbyKeyword">
          <form class="col-12 col-lg-auto mb-3 mb-lg-0" action="/productMain/product?" method="get">
            <input type="hidden" name="page" value="0">
            <input type="hidden" name="order" value="0">
            <input type="text submit" class="form-control" placeholder="Search..." name="keyword">
          </form>
        </div>
      </div>`;
    }
  } else {
    if(api.login == "success") {
      head.innerHTML =`
    <nav class="py-2 bg-light border-bottom">
      <div class="container d-flex flex-wrap">
        <ul class="nav me-auto">
          <li class="nav-item"><a href="/introduce/service" class="nav-link link-dark px-2">서비스 소개</a></li>
          <li class="nav-item"><a href="/productMain/product?page=0&order=0" class="nav-link link-dark px-2">펀딩</a></li>
          <li class="nav-item"><a href="/notice/paging" class="nav-link link-dark px-2">고객센터</a></li>
          <li class="nav-item"><a href="/feedbackMain/product?page=0&order=0" class="nav-link link-dark px-2">후기</a></li>
        </ul>
        <ul class="nav">
          <li class="nav-item"><a href="/member/logout" class="nav-link link-dark px-2">로그아웃</a></li>
          <li class="nav-item"><a href="/user/mypage" class="nav-link link-dark px-2">마이페이지</a></li>
        </ul>
      </div>
    </nav>
    <div class="py-3 mb-4 border-bottom">
      <div class="container d-flex flex-wrap justify-content-center">
        <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
          <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
          <span class="fs-4">TECHSUPP</span>
        </a>
      </div>
      <div class="SearchbyKeyword">
        <form action="/feedbackMain/product?" method="get">
          <input type="hidden" name="page" value="0">
          <input type="hidden" name="order" value="0">
          <input type="text submit" class="ProductSearch" placeholder="Search..." name="keyword">
        </form>
      </div>
    </div>`;
    } else {
      head.innerHTML =`
      <nav class="py-2 bg-light border-bottom">
        <div class="container d-flex flex-wrap">
          <ul class="nav me-auto">
            <li class="nav-item"><a href="/introduce/service" class="nav-link link-dark px-2">서비스 소개</a></li>
            <li class="nav-item"><a href="/productMain/product?page=0&order=0" class="nav-link link-dark px-2">펀딩</a></li>
            <li class="nav-item"><a href="/notice/paging" class="nav-link link-dark px-2">고객센터</a></li>
            <li class="nav-item"><a href="/feedbackMain/product?page=0&order=0" class="nav-link link-dark px-2">후기</a></li>
          </ul>
          <ul class="nav">
            <li class="nav-item"><a href="/login" class="nav-link link-dark px-2">로그인</a></li>
            <li class="nav-item"><a href="/signup" class="nav-link link-dark px-2">회원가입</a></li>
          </ul>
        </div>
      </nav>
      <div class="py-3 mb-4 border-bottom">
        <div class="container d-flex flex-wrap justify-content-center">
          <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
            <span class="fs-4">TECHSUPP</span>
          </a>
        </div>
        <div class="SearchbyKeyword">
          <form action="/feedbackMain/product?" method="get">
            <input type="hidden" name="page" value="0">
            <input type="hidden" name="order" value="0">
            <input type="text submit" class="ProductSearch" placeholder="Search..." name="keyword">
          </form>
        </div>
      </div>`;
    }

  }
}


// createProductHeaderHtml();

function checkLoginSession() {
  fetch(`/api/loginCheck`)
  .then(response => response.json())
  .then(data => createProductHeaderHtml(data))
}

checkLoginSession();


// 변수로 사용하지 말고
// url 값 그대로 사용하는 방식으로 변경