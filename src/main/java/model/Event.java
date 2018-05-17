package model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private long id;
    private String name;
    private String content;
    private Date created;
}
