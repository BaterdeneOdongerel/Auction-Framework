package service.communication;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import props.EmailProp;
import props.SMSProp;

import java.net.URI;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/14/18.
 */
public class SMSService extends BaseCommunicationService {

    SMSProp prop = SMSProp.INSTANCE;

    @Override
    void _send(Option option, CommunicationType type) {
        String sid = SMSProp.INSTANCE.getProp("ACCOUNT_SID");
        String token = SMSProp.INSTANCE.getProp("AUTH_TOKEN");
        String number = SMSProp.INSTANCE.getProp("NUMBER");
        Twilio.init(sid, token);
        Message.creator(new PhoneNumber(option.getTo()), new PhoneNumber(number), option.getContent()).create();
    }
}
