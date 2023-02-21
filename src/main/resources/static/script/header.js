const header = document.getElementById("header");

    header.innerHTML =`
    <nav class="py-2 bg-light border-bottom">
    <div class="container d-flex flex-wrap">
      <ul class="nav me-auto">
        <!-- <li class="nav-item"><a href="#" class="nav-link link-dark px-2 active" aria-current="page">Home</a></li> -->
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">서비스 소개</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">펀딩</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">고객센터</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">후기</a></li>
      </ul>
      <ul class="nav">
        <li class="nav-item"><a href="/login" class="nav-link link-dark px-2">로그인</a></li>
        <li class="nav-item"><a href="/signUp" class="nav-link link-dark px-2">회원가입</a></li>
      </ul>
    </div>
  </nav>
  <div class="py-3 mb-4 border-bottom">
    <div class="container d-flex flex-wrap justify-content-center">
      <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
        <span class="fs-4">TECHSUPP</span>
      </a>
      <form class="col-12 col-lg-auto mb-3 mb-lg-0" role="search">
        <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
      </form>
    </div>
  </div>`


