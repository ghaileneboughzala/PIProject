/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author ghail
 */
public class Smsapi {
   
    public static void sendSMS( String msg) {
      //  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(/*num ili bch yjih il msg */new PhoneNumber("+21655904764"),new PhoneNumber("+19593355806"), msg).create();

        System.out.println(message.getSid());

    }
}
