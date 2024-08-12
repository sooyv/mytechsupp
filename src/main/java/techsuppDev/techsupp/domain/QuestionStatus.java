package techsuppDev.techsupp.domain;

public enum QuestionStatus {
    PENDING("답변대기"),
    ANSWERED("답변완료");

    private final String answerStatus;

    QuestionStatus(String answer) {
        this.answerStatus = answer;
    }

    public String getAnswer() {
        return answerStatus;
    }

}
