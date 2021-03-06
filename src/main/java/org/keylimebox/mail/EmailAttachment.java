/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.mail;


/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.mail.Part;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       EmailAttachment                                                         */
/**
 * A simple wrapper for an email attachment.
 * <p>
 * @author      etiennel
 * @since       Dec 7, 2014
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public class EmailAttachment
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
                /* ATTRIBUTE: part                                                      */
                /**
                 * The part.
                 */
                /*======================================================================*/
   private Part               part;

                /*======================================================================*/
                /* ATTRIBUTE: partNumber                                                */
                /**
                 * The part number.
                 */
                /*======================================================================*/
   private int                partNumber;


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
        /* OPERATION:   EmailAttachment                                                 */
        /**
         * The constructor for this class.
         *
         * <p>
         * @param aPart
         * @param aPartNumber
         * <p>
         * @since Dec 7, 2014
         */
        /*==============================================================================*/
   public EmailAttachment (Part aPart, int aPartNumber)
   {
      part        = aPart;
      partNumber  = aPartNumber;
   }

    /*==================================================================================*/
    /* Attribute Get Operations                                                         */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   getPartNumber                                                  */
         /**
          * The part number within the email.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public int getPartNumber ()
   {
      return (partNumber);
   }


         /*=============================================================================*/
         /* OPERATION:   getFileName                                                    */
         /**
          * The attachment's file name if possible.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getFileName ()
   {
      try {
         return ((part.getFileName () == null) ? "No name" : part.getFileName ());
      }
      catch (Throwable myThrowable) {
         return "Error getting filename: " + myThrowable.getMessage ();
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getType                                                        */
         /**
          * The attachment's content type.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getType ()
   {
      try {
         if (part.getContentType () == null) {
            return "blank content type";
         }
         else {
            return part.getContentType ();
         }
      }
      catch (Throwable myThrowable) {
         return "Error getting content type: " + myThrowable.getMessage ();
      }
   }

   public boolean isType (String aType)
   {
      try {
         return part.isMimeType (aType);
      }
      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
         return false;
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getDescription                                                 */
         /**
          * The attachment's description (if any).
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getDescription ()
   {
      try {
         return Utils.nullAsBlank (part.getDescription ());
      }
      catch (Throwable myThrowable) {
         return "Error getting description: " + myThrowable.getMessage ();
      }
   }


         /*=============================================================================*/
         /* OPERATION:   getDisposition                                                 */
         /**
          * The attachment's disposition (if any).
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getDisposition ()
   {
      try {
         return Utils.nullAsBlank (part.getDisposition ());
      }
      catch (Throwable myThrowable) {
         return "Error getting disposition: " + myThrowable.getMessage ();
      }
   }

         /*=============================================================================*/
         /* OPERATION:   getAsBytes                                                    */
         /**
          * Returns the attachment itself as a byte array.
          * <p>
          * @return
          * <p>
          * @since Dec 8, 2014
          */
         /*=============================================================================*/
   public byte[] getAsBytes ()
   {
      try {
         InputStream             myInputStream  = part.getInputStream ();
         ByteArrayOutputStream   myOutputStream = new ByteArrayOutputStream ();
         int                     myReads        = myInputStream.read ();

         while (myReads != -1) {
            myOutputStream.write (myReads);
            myReads = myInputStream.read ();
         }

         return myOutputStream.toByteArray ();
      }
      catch (Throwable myThrowable) {
         throw new RuntimeException ("Unable to retrieve the attachment bytes: " + myThrowable.getMessage ());
      }
   }

    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Private Operations                                                               */
    /*==================================================================================*/

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

// EOF  EmailAttachment.java
