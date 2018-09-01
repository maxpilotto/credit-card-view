package com.maxpilotto.creditcardview;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Project: credit-card-view
 * Created by: Max
 * Date: 19/08/2018 @ 00:29
 * Package: com.maxpilotto.creditcardview
 */
public class CreditCard {
    public enum CardType {
        GENERIC,
        VISA("^4[0-9]{12}(?:[0-9]{3}){0,2}$"),
        MASTERCARD("^(?:5[1-5]|2(?!2([01]|20)|7(2[1-9]|3))[2-7])\\d{14}$"),
        AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
        DINERS_CLUB("^3(?:0[0-5]\\d|095|6\\d{0,2}|[89]\\d{2})\\d{12,15}$"),
        DISCOVER("^6(?:011|[45][0-9]{2})[0-9]{12}$"),
        JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
        CHINA_UNIONPAY("^62[0-9]{14,17}$");

        private Pattern pattern;

        CardType() {
            this.pattern = null;
        }

        CardType(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        public static CardType getType(String number) {
            if (StringUtils.isEmpty(number)){
                return GENERIC;
            }

            for (CardType type : values()) {
                if (type.pattern != null && type.pattern.matcher(number).matches()) {
                    return type;
                }
            }

            return GENERIC;
        }
    }

    private String cardHolder;
    private String cardNumber;
    private String CVV;
    private String expireDate;
    private CardType cardType;
    private int digits;

    public CreditCard(){
        this(null,null,null,null,CardType.GENERIC,16);
    }

    public CreditCard(String cardHolder, String cardNumber, String CVV, String expireDate) {
        this(cardHolder,cardNumber,CVV,expireDate,CardType.getType(cardNumber),16);
    }

    public CreditCard(String cardHolder, String cardNumber, String CVV, String expireDate, int digits) {
        this(cardHolder,cardNumber,CVV,expireDate,CardType.getType(cardNumber),digits);
    }

    private CreditCard(String cardHolder, String cardNumber, String CVV, String expireDate, CardType cardType, int digits) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.expireDate = expireDate;
        this.cardType = cardType;
        this.digits = digits;
    }

    /**
     * Checks if the card number is valid
     * @return True if it contains only numbers (0-9), False otherwise
     */
    public boolean hasValidNumber(){
        if (cardNumber.matches("[0-9]+")){
            return true;
        }
        return false;
    }

    /**
     * Checks if the expiry has a valid month
     * @return True if the month is in the range (1-12), False otherwise
     */
    public boolean hasValidMonth(){
        Integer month = Integer.valueOf(expireDate.substring(0, 2));

        if (!(month >= 1 && month <= 12)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the expiry has a valid year
     * @return True if the year is greater or equal to the current year
     */
    public boolean hasValidYear(){
        Integer year = Integer.valueOf(expireDate.substring(2, 4));
        Calendar calendar = Calendar.getInstance();

        if (year < calendar.get(Calendar.YEAR) % 100) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the CVV is valid
     * @return True if it only contains numbers (0-9), False otherwise
     */
    public boolean hasValidCVV(){
        if (CVV.matches("[0-9]+")){
            return true;
        }
        return false;
    }

    /**
     * Returns the card number's length
     * @return Card number's length
     */
    public int getDigits() {
        return digits;
    }

    /**
     * Sets the card number's length
     * @param digits Card number length
     */
    public void setDigits(int digits) {
        this.digits = digits;
    }

    /**
     * Returns the card holder
     * @return Card holder
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Sets the card holder
     * @param cardHolder Card holder
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * Returns the card number
     * @return Card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number
     * @param cardNumber Card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        this.cardType = CardType.getType(cardNumber);
    }

    /**
     * Returns the CVV
     * @return CVV
     */
    public String getCVV() {
        return CVV;
    }

    /**
     * Sets the CVV
     * @param CVV CVV
     */
    public void setCvv(String CVV) {
        this.CVV = CVV;
    }

    /**
     * Returns the expiry
     * @return Expiry
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the expiry
     * @param expireDate Expiry
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Returns the card type
     * @return Card type
     */
    public CardType getCardType() {
        return cardType;
    }
}
