package service.communication;

import service.LogType;
import service.LoggingService;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/14/18.
 */
public abstract class BaseCommunicationService implements CanCommunicate {

    @Override
    public void send(Option option, CommunicationType type) {
        LoggingService.createLog("Email Sent: " + option.getTo(), option.getContent(), LogType.Event);
        _send(option, type);
    }

    abstract void _send(Option option, CommunicationType type);

}
