package service.communication;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/14/18.
 */
public abstract class BaseCommunicationService implements CanCommunicate {

    @Override
    public void send(Option option, CommunicationType type) {
        // Do some logging before sending out
        _send(option, type);
    }

    abstract void _send(Option option, CommunicationType type);

}
