const head = document.querySelector(".Header");

function createHeaderHtml() {
  head.innerHTML = `
  <div class="Head">
    <div class="Menu">
      <ul class="NavigationBar">
        <li><input type="button" class="LinkToInformation" onclick="window.location.href='/information/?page=0'" value="information"></li>
        <li><input type="button" class="LinkToProduct" onclick="window.location.href='/productMain/product?page=0&order=0'" value="product"></li>
        <li><input type="button" class="LinkToService" onclick="window.location.href='/serviceMain/?page=0'" value="service"></li>
        <li><input type="button" class="LinkToFeedback" onclick="window.location.href='/feedbackMain/?page=0'" value="feedback"></li>
      </ul>
      <div class="MainPage">
        <input type="button" class="LinkToMain" onclick="window.location.href='/'" value="TECHSUPP">
      </div>
    </div>
    <ul class="UserControl">
      <li><button href="logout.html">logout</button></li>
      <li><button href="myPage.html">myPage</button></li>
    </ul>
  </div>`;
}

createHeaderHtml();

// 변수로 사용하지 말고
// url 값 그대로 사용하는 방식으로 변경