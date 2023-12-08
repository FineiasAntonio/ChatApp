package Server.Entity;

public class MessageEntity {
    private String user;
    private String message;

    public MessageEntity(String user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public String toString(){
        return String.format("%s: %s", this.user, this.message);
    }
}
