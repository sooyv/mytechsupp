package techsuppDev.techsupp.domain;

public enum QuestionStatus {
    PENDING("답변대기"),
    ANSWERED("답변완료");

    private final String answer;

    QuestionStatus(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

}
