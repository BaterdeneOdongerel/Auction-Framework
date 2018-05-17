package model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Event {
    private long id;
    private String name;
    private String status;
    private String content;
    private DateTime created;
}
