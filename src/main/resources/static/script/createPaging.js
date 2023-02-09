const paging = document.querySelector('.PagingContainer');


// 현재는 그냥 10개 생성하는 걸로 했는데
// 나중에 컨텐츠 갯수 파악해서 그것 만큼 생성하는 
// 반복문 작성해줘야함
function createPaging() {
  paging.innerHTML = `    
  <ul class="PagingList">
    <li class="Page"><button>1</button></li>
    <li class="Page"><button>2</button></li>
    <li class="Page"><button>3</button></li>
    <li class="Page"><button>4</button></li>
    <li class="Page"><button>5</button></li>
    <li class="Page"><button>6</button></li>
    <li class="Page"><button>7</button></li>
    <li class="Page"><button>8</button></li>
    <li class="Page"><button>9</button></li>
    <li class="Page"><button>10</button></li>
  </ul>`
}

createPaging();