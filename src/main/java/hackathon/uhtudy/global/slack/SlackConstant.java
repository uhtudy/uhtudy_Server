package hackathon.uhtudy.global.slack;

public enum SlackConstant {

    ANNOUNCEMENT_CHANNEL("#공지");

    private String channel;

    SlackConstant(final String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
