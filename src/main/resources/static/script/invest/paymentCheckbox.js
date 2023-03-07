function checkBox(element) {
  
  const checkboxes 
      = document.getElementsByName("payment");
  
  checkboxes.forEach((cb) => {
    cb.checked = false;
  })
  
  element.checked = true;
}
