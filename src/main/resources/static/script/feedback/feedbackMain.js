const paging = document.querySelector('.PagingContainer');
const page = document.querySelectorAll('.PageButton');
const feedbackList = document.querySelector('.ContainerFeedbackList');
const feedbackContentAll = document.querySelectorAll('.FeedbackContent');
const singleFeedback = document.querySelector('.FeedbackContainer');
const writeFormButton = document.querySelector('.FeedbackGotoWrite')


const url = location.href;
const urlParams = new URL(url).searchParams;
const pageNumber = urlParams.get('page');
const urlStringKeyword = `&keyword=`;
const keyword = urlParams.get('keyword');
const orderNumber = urlParams.get('order');
const productNumber = urlParams.get('num');