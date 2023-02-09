const head = document.querySelector(".Header");

function createHeader() {
  head.innerHTML = `
  <div class="Head">
    <div class="Menu">
      <ul class="NavigationBar">
        <li><input type="button" class="LinkToInformation" onclick="window.location.href='/information'" value="information"></li>
        <li><input type="button" class="LinkToProduct" onclick="window.location.href='/productMain'" value="product"></li>
        <li><input type="button" class="LinkToService" onclick="window.location.href='/serviceMain'" value="service"></li>
        <li><input type="button" class="LinkToFeedback" onclick="window.location.href='/feedbackMain'" value="feedback"></li>
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

createHeader();