package techsuppDev.techsupp.domain;

public enum QuestionCategory {
    PRODUCT_INQUIRY("상품문의"),
    DELIVERY_INQUIRY("배송문의"),
    OTHER_INQUIRY("기타문의");

    private final String inquiry;

    QuestionCategory(String inquiry) {
        this.inquiry = inquiry;
    }

    public String getInquiry() {
        return inquiry;
    }
}
