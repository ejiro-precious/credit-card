/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2015, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay.creditcard


/** Container that holds optional fields of a Public Credit Card (e.g., holder ID, billing address).
  *
  * @author <a href="mailto:ohadr@wix.com">Raz, Ohad</a>
  */
case class PublicCreditCardOptionalFields(override val holderId: Option[String] = None,
                                          override val holderName: Option[String] = None,
                                          override val billingAddress: Option[String] = None,
                                          override val billingPostalCode: Option[String] = None)
  extends Serializable with CommonPublicCreditCardFields