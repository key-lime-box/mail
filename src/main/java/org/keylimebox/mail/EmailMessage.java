/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.mail;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       EmailMessage                                                            */
/**
 * Wrapper around an email message which makes it easier to read the data from the
 * Java mail objects.
 * <p>
 * @author      etlweather
 * @since       Dec 7, 2014
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public class EmailMessage
{

    /*==================================================================================*/
    /*===================================            ===================================*/
    /*=================================== Attributes ===================================*/
    /*===================================            ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Protected Attributes                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Private Attributes                                                               */
    /*==================================================================================*/

                /*======================================================================*/
                /* ATTRIBUTE: message                                                   */
                /**
                 * The message that is wrapped.
                 */
                /*======================================================================*/
   private MimeMessage            message;

                /*======================================================================*/
                /* ATTRIBUTE: messageText                                               */
                /**
                 * The text only version of the content.
                 */
                /*======================================================================*/
   private String                 messageText = null;

                /*======================================================================*/
                /* ATTRIBUTE: messageHtml                                               */
                /**
                 * The body of the message.
                 */
                /*======================================================================*/
   private String                 messageBody = null;

                /*======================================================================*/
                /* ATTRIBUTE: htmlBody                                                  */
                /**
                 * Whether the message body is HTML or not.
                 */
                /*======================================================================*/
   private boolean                htmlBody = false;

                /*======================================================================*/
                /* ATTRIBUTE: attachments                                               */
                /**
                 * The attachments.
                 */
                /*======================================================================*/
   private EmailAttachment[]      attachments = null;

    /*==================================================================================*/
    /* Class Attributes                                                                 */
    /*==================================================================================*/
        /*==============================================================================*/
        /* Constants                                                                    */
        /*==============================================================================*/

        /*==============================================================================*/
        /* Variables                                                                    */
        /*==============================================================================*/

    /*==================================================================================*/
    /*===================================            ===================================*/
    /*=================================== Operations ===================================*/
    /*===================================            ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Static initializer                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Constructors                                                                     */
    /*==================================================================================*/

        /*==============================================================================*/
        /* OPERATION:   EmailMessage                                                    */
        /**
         * The constructor for this class.
         *
         * <p>
         * @param aMessage
         *           The message to be wrapped.
         * <p>
         * @since Dec 7, 2014
         */
        /*==============================================================================*/
   public EmailMessage (Message aMessage)
   {
      message = (MimeMessage) aMessage;
   }

    /*==================================================================================*/
    /* Attribute Get Operations                                                         */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   hasBcc                                                         */
         /**
          * Whether it has BCC or not.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public boolean hasBcc ()
   {
      return hasAddresses (Message.RecipientType.BCC);
   }

         /*=============================================================================*/
         /* OPERATION:   hasCc                                                          */
         /**
          * Whether it has CCs or not.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public boolean hasCc ()
   {
      return hasAddresses (Message.RecipientType.CC);
   }

         /*=============================================================================*/
         /* OPERATION:   hasTo                                                          */
         /**
          * Whether it has TOs or not.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public boolean hasTo ()
   {
      return hasAddresses (Message.RecipientType.TO);
   }

         /*=============================================================================*/
         /* OPERATION:   getBcc                                                         */
         /**
          * The BCCs.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public EmailAddress[] getBcc ()
   {
      return getAddresses (Message.RecipientType.BCC);
   }

         /*=============================================================================*/
         /* OPERATION:   getCc                                                          */
         /**
          * The CCs.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public EmailAddress[] getCc ()
   {
      return getAddresses (Message.RecipientType.CC);
   }

         /*=============================================================================*/
         /* OPERATION:   getTo                                                          */
         /**
          * The TOs.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public EmailAddress[] getTo ()
   {
      return getAddresses (Message.RecipientType.TO);
   }

         /*=============================================================================*/
         /* OPERATION:   getReplyTo                                                     */
         /**
          * The reply to address.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public EmailAddress[] getReplyTo ()
   {
      try {
         return getAddresses (message.getReplyTo ());
      }
      catch (MessagingException myException) {
         return new EmailAddress[] {new EmailAddress ("Error reading Reply To: " + myException.getMessage (), "")};
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getFrom                                                        */
         /**
          * The from address.
          * <p>
          * @return
          * <p>
          * @since Dec 8, 2014
          */
         /*=============================================================================*/
   public EmailAddress[] getFrom ()
   {
      try {
         return getAddresses (message.getFrom ());
      }
      catch (MessagingException myException) {
         return new EmailAddress[] {new EmailAddress ("Error reading From: " + myException.getMessage (), "")};
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getMessageId                                                   */
         /**
          * The message ID.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getMessageId ()
   {
      try {
         return message.getMessageID ();
      }
      catch (MessagingException myException) {
         return "Error getting the message ID: " + myException.getMessage ();
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getSentDate                                                    */
         /**
          * The date the message was sent.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public Date getSentDate ()
   {
      try {
         return message.getSentDate ();
      }
      catch (MessagingException myException) {
         myException.printStackTrace();
         return null;
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getReceivedDate                                                */
         /**
          * The date the message was received.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public Date getReceivedDate ()
   {
      try {
         return message.getReceivedDate ();
      }
      catch (MessagingException myException) {
         myException.printStackTrace();
         return null;
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getDate                                                        */
         /**
          * The date the message was sent, or the date it was received if the sent date
          * is null.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public Date getDate ()
   {
      Date myDate = getSentDate ();
      if (myDate == null) {
         myDate = getReceivedDate ();
      }
      return myDate;
   }

         /*=============================================================================*/
         /* OPERATION:   hasAttachments                                                 */
         /**
          * Whether it has attachments or not.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public boolean hasAttachments ()
   {
      try {
         return (getAttachments ().length > 0);
      }
      catch (Throwable myException) {
         myException.printStackTrace ();
         return false;
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getAttachments                                                 */
         /**
          * The attachments.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public EmailAttachment[] getAttachments ()
   {
      if (attachments == null) {
         try {
            if (message.isMimeType ("multipart/*")) {
               Multipart myMp    = (Multipart) message.getContent ();
               int       myTotal = myMp.getCount ();
               List<EmailAttachment>
                         myList  = new ArrayList<EmailAttachment> ();

               for (int i = 0; i < myTotal; i++) {

                  EmailAttachment   myAtt       = new EmailAttachment (myMp.getBodyPart (i), i);

                  if (myAtt.isType ("multipart/alternative")) {
                     continue;
                  }
                  else if (myAtt.isType ("text/*") && "attachment".equalsIgnoreCase (myAtt.getDisposition ()) == false) {
                     continue;
                  }
                  else {
                     myList.add (myAtt);
                  }
               }
               attachments = myList.toArray (new EmailAttachment [0]);
            }
            else {
               attachments = new EmailAttachment[0];
            }
         }
         catch (Throwable myThrowable) {
            myThrowable.printStackTrace ();
         }
      }
      return attachments;
   }

         /*=============================================================================*/
         /* OPERATION:   getSubject                                                     */
         /**
          * The subject.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getSubject ()
   {
      try {
         return message.getSubject ();
      }
      catch (MessagingException myException) {
         return "Error reading the subject: " + myException.getMessage ();
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getTextMessage                                                 */
         /**
          * Returns the body plain text version.
          * <p>
          * @return
          * <p>
          * @since Jun 26, 2013
          */
         /*=============================================================================*/
   public String getTextBody ()
   {
      if (messageText == null) {
         try
         {
            MimeMessage myMessage         = message;

            Object      myContent         = myMessage.getContent ();

            if (myContent instanceof String) {
               messageText                = (String) myContent;
            }

            else if (myContent instanceof Multipart) {
               Multipart myMultipart      = (Multipart) myContent;

               messageText                = (String) getContentFromMultipart (myMultipart, "text/plain");
            }

            if (messageText == null) {
               messageText                = "";
            }

         }

         catch (Exception myException) {
            messageText = "Error getting the text message: " + myException.getMessage ();
         }
      }
      return messageText;
   }


         /*=============================================================================*/
         /* OPERATION:   getBody                                                        */
         /**
          * The body of the message (in HTML or plain text, favoring HTML).
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getBody ()
   {
      if (messageBody == null) {
         try {
            messageBody = getText (message);
         }
         catch (Throwable myThrowable) {
            messageBody = "Error getting the body: " + myThrowable.getMessage ();
         }
      }
      return messageBody;
   }

         /*=============================================================================*/
         /* OPERATION:   isHtmlBody                                                     */
         /**
          * Whether or not the body is HTML.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public boolean isHtmlBody ()
   {
      getBody ();
      return htmlBody;
   }



    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Private Operations                                                               */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   getAddresses                                                   */
         /**
          * Reads the addresses and convert them into a usable form.
          * <p>
          * @param aRecipientType
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   private EmailAddress[] getAddresses (RecipientType aRecipientType)
   {
      try {
         Address[]      myRecipients   = message.getRecipients (aRecipientType);
         return getAddresses (myRecipients);
      }
      catch (MessagingException myException) {
         return new EmailAddress[] {new EmailAddress ("Error reading recipient: " + myException.getMessage (), "")};
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getAddresses                                                   */
         /**
          * Reads the addresses and convert them into a usable form.
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   private EmailAddress[] getAddresses (Address[] aAddresses)
   {
      Address[]      myRecipients   = aAddresses;
      if (myRecipients == null) {
         return new EmailAddress[0];
      }
      EmailAddress[] myAddresses    = new EmailAddress[myRecipients.length];
      for (int i = 0; i < myRecipients.length; i++) {
         InternetAddress myAddress = (InternetAddress) myRecipients[i];
         myAddresses[i] = new EmailAddress (myAddress.getPersonal (), myAddress.getAddress ());
      }
      return myAddresses;
   }

         /*=============================================================================*/
         /* OPERATION:   hasAddresses                                                   */
         /**
          * Returns whether or not there are addresses for a given type of recipient.
          * <p>
          * @param aRecipientType
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   private boolean hasAddresses (RecipientType aRecipientType)
   {
      try {
         return (message.getRecipients (aRecipientType) != null);
      }
      catch (MessagingException myException) {
         return false;
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getContentFromMultipart                                        */
         /**
          * Extracts text of a specific type from a multi part.
          * <p>
          * @param aMultipart
          * @param aType
          * @return
          * <p>
          * @throws IOException
          * @throws MessagingException
          * <p>
          * @since Jun 27, 2013
          */
         /*=============================================================================*/
   private Object getContentFromMultipart(Multipart aMultipart, String aType) throws IOException, MessagingException
   {
      for (int i = 0; i < aMultipart.getCount (); i++) {
         BodyPart myBodyPart        = aMultipart.getBodyPart (i);
         ContentType myContentType  = new ContentType (myBodyPart.getContentType ());

         if (myContentType.match (aType)) {
            return myBodyPart.getContent ();
         }
         else if (myContentType.match ("multipart/alternative")) {
            Object myContent        = getContentFromMultipart ((Multipart)myBodyPart.getContent (), aType);

            if (myContent != null)             {
               return myContent;
            }
         }
      }

      return null;
   }


         /*=============================================================================*/
         /* OPERATION:   getText                                                        */
         /**
          * Return the primary text content of the message, favoring HTML over Plain Text.
          * <p>
          * <b>Note:</b> Code taken from Java Mail API FAQ.
          * <p>
          * @param aPart
          * @return
          * @throws MessagingException
          * @throws IOException
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   private String getText (Part aPart)
                        throws MessagingException, IOException
   {
      if (aPart.isMimeType ("text/*")) {
         String myContent  = (String) aPart.getContent ();
         htmlBody          = aPart.isMimeType ("text/html");
         return myContent;
      }

      if (aPart.isMimeType ("multipart/alternative")) {

         Multipart   myMultiPart = (Multipart) aPart.getContent ();
         String      myText      = null;

         for (int i = 0; i < myMultiPart.getCount (); i++) {
            Part     myBodyPart  = myMultiPart.getBodyPart (i);

            if (myBodyPart.isMimeType ("text/plain")) {
               if (myText == null) {
                  myText = getText (myBodyPart);
               }
               continue;
            }
            else if (myBodyPart.isMimeType ("text/html")) {
               String myString   = getText (myBodyPart);
               if (myString != null) {
                  htmlBody = myBodyPart.isMimeType ("text/html");
                  return myString;
               }
            }
            else {
               return getText (myBodyPart);
            }
         }
         return myText;

      }
      else if (aPart.isMimeType ("multipart/*")) {
         Multipart   myMultipart = (Multipart) aPart.getContent ();
         for (int i = 0; i < myMultipart.getCount (); i++) {
            String   myString    = getText (myMultipart.getBodyPart (i));
            if (myString != null) {
               return myString;
            }
         }
      }

      return null;

    }


    /*==================================================================================*/
    /* Protected Operations                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Package Operations                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Public Operations                                                                */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Abstract Operations (definitions)                                                */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Abstract Operations (implementations)                                            */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Class (static) Operations                                                        */
    /*==================================================================================*/
}

// EOF  EmailMessage.java
