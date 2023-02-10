const product = document.querySelector(".ContainerProduct");

// 컨텍스트 페스 
// 이거 한번 알아보자


function getProducts() {
  fetch("/api")
  .then(reponse => reponse.json())
  .then(data => showProduct(data));
}

getProducts();


// 함수명 수정 필요
// innerhtml으로 받아온 json 넣는거 수정해야함
// 들어오는것은 확인됨
function showProduct(products) {
  product.innerHTML = "";

  product.innerHTML = `  
    <div class="ContainerPictureInvestmentAmountInformation">
      <div>
        <div class="ProductPicture" placeholder ="asf">
        </div>
        <div class="ProductInvestmentAmount" placeholder ="products.invesment" >
          목표 투자액
        </div>
        <div class="ProductInformation"placeholder ="asdf">
          제품 상세 설명
        </div>
      </div>
    </div>
    <div class="ContainerNameLimitDatePrecentageInvesting">
      <div class="ProductNameWish">
        <span>제품명</span>
        <button>즐겨찾기</button>
      </div>
      <div class="ProductLimitDate"placeholder ="products.limitdate">
        투자 마감일
      </div>
      <div class="ProductPercentage"placeholder ="products.percent">
        투자율
      </div>
      <div class="ProductInvesting"placeholder ="asdf">
        개인 투자 금액(투자 결제 페이지 이동)
      </div>
    </div>
  `
}



// 상품 목록 페이지
// 정렬 : 제목, 조회수, 즐겨찾기, 투자율
// 검색 : 제목에 키워드가 있는지 검색
// 즐겨찾기 : 누르면 이벤트 발생해서 db에 자동적으로 생성-수정 되도록
// 페이징 : 알고리즘 (검색 필요)

// 특정 상품 페이지
// 투자 결제 페이지 만들어야함

// 투자 결제 페이지
// db에서 값 가지고 와서 배치
// 투자 확정 버튼
//   회원 정보 테이블에서 기존에 있던 데이터와 비교
//    다를 경우 저장할지 확인하는 팝업 창(디자인 해야함)

// 투자 완료 페이지
// 디자인 해야함
// 다른 url로 이동하는 기능 



//피드백 페이지
// 디자인 필요
// 위에 기능이 구현 된다면 여기는 그거 다 가져오기만 하면 됨
// 정렬: 내가 투자한 상품 목록만 filter 해서 보여주기

// 특정 피드백 페이지
// 디자인 필요
// 그 상품에 관련된 모든 피드백 가져와서 보여주기
// 내가 구매한 상품 페이지일 경우
// 최상단에 작성한 페이지 보여주기
// 

// 피드백 작성 페이지
// 점수 입력란, 사진 업로드, 후기글 작성하는 form 필요
// 작성 누르면 특정 피드백 페이지로 넘어감
 



getProducts();

// 콘솔 창으로 가지고 온 값 확인하는 테스트 함수

function getProducts() {
  fetch("/api")
  .then(reponse => reponse.json())
  .then(data => console.log(data));
}