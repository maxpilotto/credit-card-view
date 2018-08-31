# credit-card-view [WIP]

[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

A fully customizable Android view that can display credit card's informations

# Usage
### XML
```xml
<!-- American express card -->
 <com.maxpilotto.creditcardview.CreditCardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:number="341635298762981"
        app:numberVisibility="show"
        app:numberHint="****************"
        app:hideChar="*"
        app:cardHolder="John Smith"
        app:expireDate="0219"
        app:cvv="123"
        app:digits="15"
        app:cardNumberGroupSize="3"/>

<!-- Visa card -->
<com.maxpilotto.creditcardview.CreditCardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:number="4601412314141414"
        app:numberVisibility="show"
        app:numberHint="****************"
        app:hideChar="*"
        app:cardHolder="John Smith"
        app:expireDate="0219"
        app:cvv="123"/>

<!-- Visa with custom background -->
 <com.maxpilotto.creditcardview.CreditCardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:number="4601412314141414"
        app:numberVisibility="show"
        app:numberHint="****************"
        app:hideChar="*"
        app:cardHolder="John Smith"
        app:expireDate="0219"
        app:cvv="123"
        app:visaCardBackground="@android:color/red"/>  
```

### Java
```Java

```

# Documentation

Xml Attribute | Value/Type | Description 
--------------|------------|------------
genericCardBackground | color/reference |Card background for when the number is not known
visaCardBackground| color/reference |Card background for when the number belongs to Visa
mastercardCardBackground| color/reference |Card background for when the number belongs to Mastercard
americanExpressCardBackground|color/reference |Card background for when the number belongs to American Express
dinersClubCardBackground| color/reference |Card background for when the number belongs to Diners Club
discoverCardBackground| color/reference |Card background for when the number belongs to Discover
jcbCardBackground| color/reference |Card background for when the number belongs to JCB
chinaUnionpayCardBackground| color/reference |Card background for when the number belongs to China UnionPay 
labelsTextColor| color/reference |Text color of the labels
cardHolderLabel|string|Text value of the card holder's label
cardExpiryLabel|string|Text value of the expiry's label
genericCardLogo|reference|Card logo for when the number is not known
visaCardLogo|reference|Card logo for when the number belongs to Visa
mastercardCardLogo|reference|Card logo for when the number belongs to Mastercard
americanExpressCardLogo|reference|Card logo for when the number belongs to American Express
dinersClubCardLogo|reference|Card logo for when the number belongs to Diners Club
discoverCardLogo|reference|Card logo for when the number belongs to Discover
jcbCardLogo|reference|Card logo for when the number belongs to JCB
chinaUnionpayCardLogo|reference|Card logo for when the number belongs to China UnionPay
cardNumberGroupSize|integer|Number of characters to which the card number is divided, spaces will be added between the character groups E.g. groupSize = 2, number = "123456", result = "12 34 56"
magneticStripColor|color/reference|Back view magnetic strip color
errorColor|color/reference|Text color for all the errors TextViews
flipOnCVVEdit|boolean|Flip the card when the CVV is being edited
flipOnFrontDataEdit|boolean|Flip the card when any of the front data is being edited
errorsEnabled|boolean|Allows errors report, you need to setup a listener if you want to be able to see errors. Disabled by default
cvvVisibility|show/hide|Visibility level of the CVV
digits|integer|Length of the card number
flipped|boolean|Flipped value, False means that the front side is showing, True means that the back side is showing
hideChar|string|Character that replaces the card number's characters when they are hidden
flipOnClick|boolean|Allows the card to flip when it's pressed, True to enable, False otherwise. By default it's disabled
numberVisibility|show/showLastFour/hide|Visibility level of the card number
number|string|Card number value, the number shouldn't have spaces
expireDate|string|Expiry value, the string shouldn't have spaces,dashes or slashes since the slash is automatically added
cardHolder|string|Card holder value, it can be anything
cvv|string|CVV value, max length 3
numberColor|color/reference|Card number text color
cardHolderColor|color/reference|Card holder text color
expireDateColor|color/reference|Expiry text color 
cvvColor|color/reference|CVV text color
numberHint|string|Card number hint text value
cardHolderHint|string|Card holder hint text value
expireDateHint|string|Expiry hint text value
cvvHint|string|CVV hint text value
numberHintColor|color/reference|Card number hint text color
cardHolderHintColor|color/reference|Card holder hint text color
expireDateHintColor|color/reference|Expiry hint text color
cvvHintColor|color/reference|CVV hint text color

Note: Raw colors are not supported, use references instead (E.g. "R.color.red" instead of "#ff0000")

</br>JavaDoc is available [here](https://maxpilotto.github.io/credit-card-view)

# License
This is made available under the terms of the GPLv3. <br/>
See the [LICENSE](https://github.com/maxpilotto/toggle-image-view/blob/master/LICENSE) file that accompanies this distribution for the full text of the license.
