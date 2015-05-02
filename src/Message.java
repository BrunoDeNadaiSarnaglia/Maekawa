/**
 * Created by Bruno on 5/1/2015.
 */
public class Message {
    Integer from;
    Integer to;
    String content;

    public Message(Integer from, Integer to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                '}';
    }
}
