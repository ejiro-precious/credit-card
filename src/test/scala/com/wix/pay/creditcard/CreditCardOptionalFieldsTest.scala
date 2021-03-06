/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2015, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay.creditcard


import org.specs2.matcher.{AlwaysMatcher, Matcher}
import org.specs2.matcher.MustMatchers._
import org.specs2.mutable.SpecWithJUnit
import java.util.Locale


/** Unit-Test for the [[CreditCardOptionalFields]] class.
  *
  * @author <a href="mailto:ohadr@wix.com">Raz, Ohad</a>
  */
class CreditCardOptionalFieldsTest extends SpecWithJUnit {
  val csc = "csc"

  def beCreditCardFields(csc: Matcher[Option[String]] = AlwaysMatcher(),
                         holderId: Matcher[Option[String]] = AlwaysMatcher(),
                         holderName: Matcher[Option[String]] = AlwaysMatcher(),
                         billingAddress: Matcher[Option[String]] = AlwaysMatcher(),
                         billingPostalCode: Matcher[Option[String]] = AlwaysMatcher(),
                         billingAddressDetailed: Matcher[Option[AddressDetailed]]  = AlwaysMatcher()): Matcher[CreditCardOptionalFields] = {
    csc ^^ { (_: CreditCardOptionalFields).csc aka "csc" } and
      holderId ^^ { (_: CreditCardOptionalFields).holderId aka "holder ID" } and
      holderName ^^ { (_: CreditCardOptionalFields).holderName aka "holder name" } and
      billingAddress ^^ { (_: CreditCardOptionalFields).billingAddress aka "billing address" } and
      billingPostalCode ^^ { (_: CreditCardOptionalFields).billingPostalCode aka "billing postal code" } and
      billingAddressDetailed ^^ { (_: CreditCardOptionalFields).billingAddressDetailed aka "billing address detailed"}
  }

  "specifying fields" should {
    "create the credit card fields correctly" in {
      val holderName = "holder name"

      CreditCardOptionalFields.withFields(csc = Some(csc), holderName = Some(holderName)) must
        beCreditCardFields(csc = beSome(csc), holderName = beSome(holderName))
    }

    "does not create public fields, if not specified" in {
      CreditCardOptionalFields.withFields(csc = Some(csc)).publicFields must beNone
    }
  }

  "merge" should {
    val holderId = "holder ID"
    val holderName = "holder name"
    val billingAddress = "billing address"
    val billingPostalCode = "billing postal code"
    val billingAddressDetailed = Some(AddressDetailed(Some("Street"),Some("City"),Some("State"),Some("PostalCode"),Some(new Locale("US"))))
    val billingAddressDetailedIgnored = Some(AddressDetailed(Some("StreetIgnored"),None,None,None,None))

    "use specified missing csc" in {
      val other = CreditCardOptionalFields(csc = Some(csc))

      CreditCardOptionalFields(csc = None, publicFields = None).fillMissing(other) must
        beCreditCardFields(csc = beSome(csc))
    }

    "use original csc, if not missing" in {
      val other = CreditCardOptionalFields(csc = Some("to be ignored"))

      CreditCardOptionalFields(csc = Some(csc)).fillMissing(other) must
        beCreditCardFields(csc = beSome(csc))
    }

    "set None as csc, if missing, but not specified in override" in {
      val other = CreditCardOptionalFields(csc = None)

      CreditCardOptionalFields(csc = None).fillMissing(other) must
        beCreditCardFields(csc = beNone)
    }

    "use specified missing holder ID" in {
      val other = CreditCardOptionalFields.withFields(holderId = Some(holderId))

      CreditCardOptionalFields(publicFields = None).fillMissing(other) must
        beCreditCardFields(holderId = beSome(holderId))
    }

    "use original holder ID, if not missing" in {
      val other = CreditCardOptionalFields(publicFields = None)

      CreditCardOptionalFields.withFields(holderId = Some(holderId)).fillMissing(other) must
        beCreditCardFields(holderId = beSome(holderId))
    }

    "use original holder ID, if not missing (even if specified in override)" in {
      val other = CreditCardOptionalFields.withFields(holderId = Some("to be ignored"))

      CreditCardOptionalFields.withFields(holderId = Some(holderId)).fillMissing(other) must
        beCreditCardFields(holderId = beSome(holderId))
    }

    "set None as holder ID, if missing, but not specified in override" in {
      val other = CreditCardOptionalFields.withFields(holderId = None)

      CreditCardOptionalFields.withFields(holderId = None).fillMissing(other) must
        beCreditCardFields(holderId = beNone)
    }

    "use specified missing holder name" in {
      val other = CreditCardOptionalFields.withFields(holderName = Some(holderName))

      CreditCardOptionalFields(publicFields = None).fillMissing(other) must
        beCreditCardFields(holderName = beSome(holderName))
    }

    "use original holder name, if not missing" in {
      val other = CreditCardOptionalFields(publicFields = None)

      CreditCardOptionalFields.withFields(holderName = Some(holderName)).fillMissing(other) must
        beCreditCardFields(holderName = beSome(holderName))
    }

    "use original holder name, if not missing (even if specified in override)" in {
      val other = CreditCardOptionalFields.withFields(holderName = Some("to be ignored"))

      CreditCardOptionalFields.withFields(holderName = Some(holderName)).fillMissing(other) must
        beCreditCardFields(holderName = beSome(holderName))
    }

    "set None as holder name, if missing, but not specified in override" in {
      val other = CreditCardOptionalFields.withFields(holderName = None)

      CreditCardOptionalFields.withFields(holderName = None).fillMissing(other) must
        beCreditCardFields(holderName = beNone)
    }

    "use specified missing billing address" in {
      val other = CreditCardOptionalFields.withFields(billingAddress = Some(billingAddress))

      CreditCardOptionalFields(publicFields = None).fillMissing(other) must
        beCreditCardFields(billingAddress = beSome(billingAddress))
    }

    "use original billing address, if not missing" in {
      val other = CreditCardOptionalFields(publicFields = None)

      CreditCardOptionalFields.withFields(billingAddress = Some(billingAddress)).fillMissing(other) must
        beCreditCardFields(billingAddress = beSome(billingAddress))
    }

    "use original billing address, if not missing (even if specified in override)" in {
      val other = CreditCardOptionalFields.withFields(billingAddress = Some("to be ignored"))

      CreditCardOptionalFields.withFields(billingAddress = Some(billingAddress)).fillMissing(other) must
        beCreditCardFields(billingAddress = beSome(billingAddress))
    }

    "set None as billing address, if missing, but not specified in override" in {
      val other = CreditCardOptionalFields.withFields(billingAddress = None)

      CreditCardOptionalFields.withFields(billingAddress = None).fillMissing(other) must
        beCreditCardFields(billingAddress = beNone)
    }

    "use specified missing billing detailed address" in {
      val other = CreditCardOptionalFields.empty().withBillingAddressDetailed(billingAddressDetailed)

      CreditCardOptionalFields(publicFields = None).fillMissing(other) must
        beCreditCardFields(billingAddressDetailed = ===(billingAddressDetailed))
    }

    "use original billing address detailed, if not missing" in {
      val other = CreditCardOptionalFields(publicFields = None)

      CreditCardOptionalFields.empty().withBillingAddressDetailed(billingAddressDetailed).fillMissing(other) must
        beCreditCardFields(billingAddressDetailed = ===(billingAddressDetailed))
    }

    "use original billing address detailed, if not missing (even if specified in override)" in {
      val other = CreditCardOptionalFields.empty().withBillingAddressDetailed(billingAddressDetailedIgnored)

      CreditCardOptionalFields.empty().withBillingAddressDetailed(billingAddressDetailed).fillMissing(other) must
        beCreditCardFields(billingAddressDetailed = ===(billingAddressDetailed))
    }

    "set None as billing address detailed, if missing, but not specified in override" in {
      val other = CreditCardOptionalFields.empty()

      CreditCardOptionalFields.empty().fillMissing(other) must
        beCreditCardFields(billingAddressDetailed = beNone)
    }

    "have billing address composed of billing address detailed " in {
      val fields = CreditCardOptionalFields.empty().withBillingAddressDetailed(billingAddressDetailed)

      fields must  beCreditCardFields(billingAddress = beSome(billingAddressDetailed.get.composedAddress))
    }

    "use specified missing billing postal code" in {
      val other = CreditCardOptionalFields.withFields(billingPostalCode = Some(billingPostalCode))

      CreditCardOptionalFields(publicFields = None).fillMissing(other) must
        beCreditCardFields(billingPostalCode = beSome(billingPostalCode))
    }


    "use original billing postal code, if not missing" in {
      val other = CreditCardOptionalFields(publicFields = None)

      CreditCardOptionalFields.withFields(billingPostalCode = Some(billingPostalCode)).fillMissing(other) must
        beCreditCardFields(billingPostalCode = beSome(billingPostalCode))
    }

    "use original billing postal code, if not missing (even if specified in override)" in {
      val other = CreditCardOptionalFields.withFields(billingPostalCode = Some("to be ignored"))

      CreditCardOptionalFields.withFields(billingPostalCode = Some(billingPostalCode)).fillMissing(other) must
        beCreditCardFields(billingPostalCode = beSome(billingPostalCode))
    }

    "set None as billing postal code, if missing, but not specified in override" in {
      val other = CreditCardOptionalFields.withFields(billingPostalCode = None)

      CreditCardOptionalFields.withFields(billingPostalCode = None).fillMissing(other) must
        beCreditCardFields(billingPostalCode = beNone)
    }
  }
}
