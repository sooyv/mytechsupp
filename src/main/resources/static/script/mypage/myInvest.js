function getProducts(page) {
  $.ajax({
    url: '/api/products',
    type: 'GET',
    data: { page: page },
    success: function(data) {
      // 서버에서 상품 리스트를 가져왔을 때 처리하는 코드
      // data 변수에는 서버에서 가져온 상품 리스트가 들어있습니다.
      showProducts(page, data.products);
      showPagination(page, data.totalPages);
    },
    error: function(xhr, status, error) {
      // 서버에서 상품 리스트를 가져오지 못했을 때 처리하는 코드
    }
  });
}

function showProducts(page, products) {
  var numProductsPerPage = 3;
  var startIdx = (page - 1) * numProductsPerPage;
  var endIdx = startIdx + numProductsPerPage;
  var productsToShow = products.slice(startIdx, endIdx);

  // productsToShow 변수에는 해당 페이지에 보여줄 상품 리스트가 들어있습니다.
  // 이 상품 리스트를 화면에 보여주는 코드를 작성합니다.
}

function showPagination(page, totalPages) {
  var pagination = $('.pagination');


  // 이전 페이지 버튼
  if (page > 1) {
    pagination.append('<button class="page-btn" data-page="' + (page - 1) + '">이전</button>');
  }

  // 페이지 버튼들
  for (var i = 1; i <= totalPages; i++) {
    var activeClass = (i === page) ? 'active' : '';
    pagination.append('<button class="page-btn ' + activeClass + '" data-page="' + i + '">' + i + '</button>');
  }

  // 현재 페이지 표시
  pagination.find('.page-btn[data-page="' + page + '"]').addClass('active');

  // 다음 페이지 버튼
  if (page < totalPages) {
    pagination.append('<button class="page-btn" data-page="' + (page + 1) + '">다음</button>');
  }
}

// 페이지 버튼 클릭 이벤트 등록
$(document).on('click', '.page-btn', function() {
  var page = $(this).data('page');
  getProducts(page);
});
