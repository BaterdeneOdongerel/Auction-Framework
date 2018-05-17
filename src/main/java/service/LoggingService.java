package service;

import controller.Services;
import model.Event;
import props.EmailProp;
import service.communication.CommunicationType;
import service.communication.Option;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/17/18.
 */
public class LoggingService {
    static Logger logger = Logger.getLogger("ServerLog");

    public static void createLog(String name, String content, LogType type) {
        switch (type) {
            case File:
                writeLogToFile(name, content);
                break;
            case Event:
                createEvent(name, content);
                break;
            case Email:
                createEmail(name, content);
                break;
            case Console:
            default:
                System.out.println(String.format("Log name: %s, Log content: %s", name, content));
                break;
        }
    }

    private static void createEmail(String name, String content) {
        String adminEmail = EmailProp.INSTANCE.getProp("admin");
        Option emailOption = new Option.Builder()
                .withTo(adminEmail)
                .withContent(content)
                .withSubject("Exception: " + name)
                .build();
        Services.communicator.send(emailOption, CommunicationType.EMAIL);
    }

    private static void createEvent(String name, String content) {
        Event event = new Event();
        event.setName(name);
        event.setContent(content);
        Services.EventService.create(event);
    }

    private static void writeLogToFile(String name, String content) {
        try {
            FileHandler fh;
            fh = new FileHandler("Log.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info(name + " : " + content);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
