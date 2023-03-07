// 세션값에서 사용자 ID 가져오기
const userId = sessionStorage.getItem('userId');

// AJAX 요청 보내기
const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
  if (xhr.readyState === XMLHttpRequest.DONE) {
    if (xhr.status === 200) {
      // 요청이 성공했을 때 결과 처리
      const product = JSON.parse(xhr.responseText);
      if (product) {
        // 프로덕트가 존재하는 경우
        const productRow = `
          <ul class="board_row">
            <li class="w70">${product.id}</li>
            <li class="w500">${product.productName}</li>
            <li class="w120">${product.investPrice}</li>
            <li class="w100">${product.totalPrice}</li>
            <li class="w100">${product.productStatus}</li>
          </ul>
        `;
        document.querySelector('.product_list').innerHTML = productRow;
      } else {
        // 프로덕트가 존재하지 않는 경우
        document.querySelector('.no_product').style.display = 'block';
      }
    } else {
      // 요청이 실패했을 때 처리
      console.error('AJAX 요청이 실패했습니다.');ㄹ
    }
  }
};
xhr.open('GET', `/myfavorite?userId=${userId}`);
xhr.send();