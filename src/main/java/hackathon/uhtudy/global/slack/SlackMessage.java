package hackathon.uhtudy.global.slack;


import hackathon.uhtudy.domain.study.persistence.Study;

public record SlackMessage(Study study) {

    @Override
    public String toString() {
        return "🗓️신규 공지사항 안내\n" +
                "\n Study Info : " + study.toString();
    }
}
