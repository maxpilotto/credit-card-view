# credit-card-view

[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

A fully customizable Android view that can display credit card's informations

<img src="https://raw.github.com/maxpilotto/credit-card-view/master/pics/s1.jpg" width=250>  
<img src="https://raw.github.com/maxpilotto/credit-card-view/master/pics/s2.jpg" width=250>  
<img src="https://raw.github.com/maxpilotto/credit-card-view/master/pics/s3.jpg" width=250>  
<img src="https://raw.github.com/maxpilotto/credit-card-view/master/pics/s4.jpg" width=250>  

# Getting started
```gradle 
dependencies {
	implementation 'com.maxpilotto:credit-card-view:5.0'
}
```


# Usage
### XML
```Xml
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
As for the background, you can set a solid color (raw colors not supported) or your own drawable like the following

```Xml
<!-- visa_gradient.xml -->
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <corners android:radius="6dp" />
    
    <gradient
        android:endColor="#ff0000"
        android:startColor="#ff5555"
        android:type="radial"
        android:gradientRadius="500dp"/>

</shape>
```

and then to set it you can do something like this

```Xml
<com.maxpilotto.creditcardview.CreditCardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:visaCardBackground="@drawable/visa_gradient"
        app:masterCardBackground="@drawable/mastercard"
        app:americanExpressCardBackground="@color/dark_yellow"/>  
```

### Java
```Java
// American express card
CreditCardView card = new CreditCardView(this);
card.setCardNumber("341635298762981");
card.setNumberVisibility(CreditCardView.NumberVisibility.SHOW);
card.setCardNumberHint("****************");
card.setHideChar('*');
card.setCardHolder("John Smith");
card.setExpiry("0219");
card.setCVV("123");
card.setCardDigits(15);
card.setCardNumberGroupSize(3);
```
<br/>You can also copy an existing card's style, this will only copy the style (colors,hints,background) and not the values (number,holder,cvv)
```Java
CreditCardView copiedCard = CreditCardView.copyFrom(((CreditCardView)findViewById(R.id.card1)));
card.setCardNumber("341635298762981");
```

# Check out the [Demo](https://github.com/maxpilotto/credit-card-view/releases)

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

JavaDoc is available [here](maxpilotto.com/docs/credit-card-view/index.html)

# License
 Copyright 2018 Max Pilotto

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
