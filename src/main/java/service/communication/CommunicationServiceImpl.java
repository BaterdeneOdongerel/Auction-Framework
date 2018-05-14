package service.communication;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/14/18.
 */
public class CommunicationServiceImpl implements CanCommunicate{

    EmailService emailService;
    SMSService smsService;

    public CommunicationServiceImpl() {
        this.emailService = new EmailService();
        this.smsService = new SMSService();
    }

    @Override
    public void send(Option option, CommunicationType type) {
        if (CommunicationType.SMS == type) {
            smsService.send(option, type);
        } else {
            emailService.send(option, type);
        }
    }
}
