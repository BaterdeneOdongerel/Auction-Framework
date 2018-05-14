package service.communication;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/14/18.
 */
public class Option {
    private String to;
    private String name;
    private String subject;
    private String content;

    public static class Builder {
        private String to;
        private String name;
        private String subject;
        private String content;

        public Builder() {
        }
        public Builder withTo(String to){
            this.to = to;
            return this;
        }
        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withSubject(String subject){
            this.subject = subject;
            return this;
        }
        public Builder withContent(String content){
            this.content = content;
            return this;
        }
        public Option build(){
            Option option = new Option();
            option.to = this.to;
            option.content = this.content;
            option.subject = this.subject;
            option.name = this.name;
            return option;
        }
    }
    private Option() {
    }

    public String getName() {
        return name;
    }


    public String getSubject() {
        return subject;
    }


    public String getContent() {
        return content;
    }


    public String getTo() {
        return to;
    }
}
