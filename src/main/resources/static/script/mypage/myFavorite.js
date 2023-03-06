/*
totalPage : 총 게시글 수
page_num : 한 페이지 당 출력되는 게시글 갯수
block_num : 한번에 출력될 수 있는 최대 블록 수
total_block : 블록의 총 수
current_block : 현재 블록 위치
data: 게시글 데이터를 담고 있는 객체 배열
post_data_print(block) : 게시글 데이터 출력하기 / 매개변수 : 선택 블럭
block_print(front_block) : 블럭 출력하기 / 매개변수 : 가장 앞에 오는 블럭
*/
        //    총 게시글 수
        let totalPage = 1000;
        //  한 페이지 당 출력되는 게시글 갯수
        let page_num = 20;
        //   한번에 출력될 수 있는 최대 블록 수
        // ex ) [1][2][3][4][5] -> 블록
        let block_num = 10;
        // 블록의 총 수를 계산한다.
        let total_block = totalPage%20 == 0 ? totalPage/20 : totalPage/20+1 ;
        // 현재 블록 위치를 알려준다
        let current_block = 1;
        /*
        게시글 데이터를 담고 있는 객체 배열
        번호 : data[게시글 번호].notice_num
        제목 : data[게시글 번호].title
        작성자 : data[게시글 번호].writer
        작성일 : data[게시글 번호].date_created
        조회 : data[게시글 번호].Lookkup_num
        좋아요 : data[게시글 번호].like
        */
        let data = new Array();

        //    게시글 데이터를 담고 있는 객체를 1000개 추가한다.
        for(let i=1; i<=totalPage; i++){
            data[i] = {
                notice_num : i,
                title:"제품명"+i,
                writer:"목표투자액"+i,
                date_created:"2023-03-06",
                Lookkup_num : i,
                like : i
            }
        }
        // 게시글 데이터 출력하기
        // 매개변수 : 선택 블럭
       function post_data_print(block){

            // 초기화
            // 게시글 title 제외하고 모두 제거
            let post_list = document.querySelectorAll(".data_row");
            post_list.forEach(function(item){
                item.remove();
            })

            // 게시글 출력 공간
            let notice_board = document.querySelector(".notice_board");
            // 출력 첫 페이지 번호
            let start = totalPage-page_num*(block-1);
            for(let i=start;i>=1&&i>start-page_num;i--){

                // 게시글 데이터 가져와서 게시글 요소 생성 및 추가
                // 번호, 제목, 작성자, 작성일, 조회수, 좋아요
                // data[i].notice_num data[i].title data[i].writer data[i].date_created data[i].Lookkup_num data[i].like

                let post = document.createElement("ul");
                post.className = "board_row";
                post.className = "data_row";

                let classname = ["w70","w500","w120","w100","w100","w100"]

                let post_data = [data[i].notice_num, data[i].title, data[i].writer, data[i].date_created, data[i].Lookkup_num, data[i].like];

                //게시글 생성
                for(let j=0;j<classname.length;j++){
                    let li = document.createElement("li");
                    li.className = classname[j];
                    li.textContent = post_data[j];
                    post.appendChild(li);
                }

                // 게시글 추가
                notice_board.appendChild(post);

            }

            }
function block_print(front_block) {
  /*
    1. 이전, 다음 블럭 속성 처리
    2. 기존 블럭 모두 삭제
    3. 범위 안의 블럭 생성 및 추가
  */

  // 이전으로 갈 블럭이 없으면
  if (front_block <= 1) {
    document.querySelector(".before_move").style.visibility = "hidden";
  } else {
    document.querySelector(".before_move").style.visibility = "visible";
  }

  // 다음으로 갈 블럭이 없으면
  if (front_block + block_num >= total_block) {
    document.querySelector(".next_move").style.visibility = "hidden";
  } else {
    document.querySelector(".next_move").style.visibility = "visible";
  }

  // 블럭을 추가할 공간
  let block_box = document.querySelector(".block");
  // 기존 블럭 모두 삭제
  block_box.replaceChildren();

  // 새로운 블럭 추가
  for (let i = 1; i <= total_block; i++) {
    if (i >= front_block && i < front_block + block_num) {
      let block = document.createElement("span");
      block.className = "block_num";
      block.textContent = i;

      if (i === current_block) {
        block.classList.add("current");
      }

      block.addEventListener("click", function () {
        current_block = i; // i를 사용해서 current_block 값을 저장
        post_data_print(i);
        block_print(Math.floor((i - 1) / block_num) * block_num + 1); // 수정된 부분: i를 사용해서 front_block 계산
      });

      block_box.appendChild(block);
    }
  }
}

        // 이전 또는 다음 버튼을 클릭 시 사용자 입력을 처리하는 코드가 없어서 게시판 기능이 제한됨. 문제    그 ... 이전 페이지로 이동을 어케하지
        function before(){
            block_print(current_block-block_num)
            console.log("이전");
        }

        function next(){
            block_print(current_block+block_num)
            console.log("다음");

        }
        // 화면 로드 시 실행되는 이벤트
        window.onload = function(){

            // 게시글 데이터 출력하기
            post_data_print(1)

           // 블럭 출력하기
           block_print(1)
        }
    </script>