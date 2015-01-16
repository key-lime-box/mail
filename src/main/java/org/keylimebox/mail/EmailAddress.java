/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.mail;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/


/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       EmailAddress                                                            */
/**
 * A simple email address POJO.
 * <p>
 * @author      etlweather
 * @since       Dec 7, 2014
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public class EmailAddress
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
                /* ATTRIBUTE: name                                                      */
                /**
                 * The name associated with the address.
                 */
                /*======================================================================*/
   private String          name;

                /*======================================================================*/
                /* ATTRIBUTE: address                                                   */
                /**
                 * The actual address.
                 */
                /*======================================================================*/
   private String          address;

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
        /* OPERATION:   EmailAddress                                                    */
        /**
         * The constructor for this class.
         *
         * <p>
         * <p>
         * @since Dec 7, 2014
         */
        /*==============================================================================*/
   public EmailAddress ()
   {
      // no op
   }


        /*==============================================================================*/
        /* OPERATION:   EmailAddress                                                    */
        /**
         * The full constructor for this class.
         *
         * <p>
         * @param aName
         * @param aAddress
         * <p>
         * @since Dec 7, 2014
         */
        /*==============================================================================*/
   public EmailAddress (String aName, String aAddress)
   {
      name     = aName;
      address  = aAddress;
   }


    /*==================================================================================*/
    /* Attribute Get Operations                                                         */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   getDisplay                                                     */
         /**
          * The display address like <code>Joe Blow &lt;joeb@hotmail.com&gt;</code>
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getDisplay ()
   {
      return ((name == null ? "" : name) + " <" + address + ">");
   }

         /*=============================================================================*/
         /* OPERATION:   getAddress                                                     */
         /**
          * The email address.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getAddress ()
   {
      return (address);
   }

         /*=============================================================================*/
         /* OPERATION:   getName                                                        */
         /**
          * The name associated with the address.
          * <p>
          * @return
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public String getName ()
   {
      return (name);
   }

    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   setAddress                                                     */
         /**
          *
          * <p>
          * @param aAddress
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public void setAddress (String aAddress)
   {
      address = aAddress;
   }

         /*=============================================================================*/
         /* OPERATION:   setName                                                        */
         /**
          *
          * <p>
          * @param aName
          * <p>
          * @since Dec 7, 2014
          */
         /*=============================================================================*/
   public void setName (String aName)
   {
      name = aName;
   }

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

// EOF  EmailAddress.java