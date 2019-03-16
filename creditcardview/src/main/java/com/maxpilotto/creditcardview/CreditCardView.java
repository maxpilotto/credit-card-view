package com.maxpilotto.creditcardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maxpilotto.creditcardview.utils.Utility;

import org.apache.commons.lang3.StringUtils;

/**
 * Project: credit-frontView-view
 * Created by: Max
 * Date: 29/07/2018 @ 22:20
 * Package: com.maxpilotto.creditcardview
 */
public class CreditCardView extends LinearLayout {
    protected static final int DEFAULT_CARD_HOLDER_COLOR = android.R.color.white;
    protected static final boolean DEFAULT_FLIP_ON_CLICK_VALUE = true;
    protected static final int DEFAULT_GENERIC_COLOR = R.color.genericCardBackground;
    protected static final int DEFAULT_VISA_COLOR = R.color.visaCardBackground;
    protected static final int DEFAULT_MASTERCARD_COLOR = R.color.mastercardCardBackground;
    protected static final int DEFAULT_AMERICAN_EXPRESS_COLOR = R.color.americanExpressCardBackground;
    protected static final int DEFAULT_DINERS_CLUB_COLOR = R.color.dinersClubCardBackground;
    protected static final int DEFAULT_DISCOVER_COLOR = R.color.discoverCardBackground;
    protected static final int DEFAULT_JCB_COLOR = R.color.jcbCardBackground;
    protected static final int DEFAULT_CHINA_UNIONPAY_COLOR = R.color.chinaUnionpayCardBackground;
    protected static final int DEFAULT_GENERIC_LOGO = R.drawable.logo_generic;
    protected static final int DEFAULT_VISA_LOGO = R.drawable.logo_visa;
    protected static final int DEFAULT_MASTERCARD_LOGO = R.drawable.logo_mastercard;
    protected static final int DEFAULT_AMERICAN_EXPRESS_LOGO = R.drawable.logo_american_express;
    protected static final int DEFAULT_DINERS_CLUB_LOGO = R.drawable.logo_diners_club;
    protected static final int DEFAULT_DISCOVER_LOGO = R.drawable.logo_discover;
    protected static final int DEFAULT_JCB_LOGO = R.drawable.logo_jcb;
    protected static final int DEFAULT_CHINA_UNIONPAY_LOGO = R.drawable.logo_china_unionpay;
    protected static final NumberVisibility DEFAULT_NUMBER_VISIBILITY = NumberVisibility.SHOW_LAST_FOUR;
    protected static final int DEFAULT_NUMBER_TEXT_COLOR = android.R.color.white;
    protected static final String DEFAULT_CARD_NUMBER_HINT = "XXXXXXXXXXXXXXXX";
    protected static final int DEFAULT_NUMBER_HINT_COLOR = android.R.color.white;
    protected static final int DEFAULT_LABELS_TEXT_COLOR = android.R.color.darker_gray;
    protected static final String DEFAULT_CARD_HOLDER_LABEL = "CARD HOLDER";
    protected static final String DEFAULT_CARD_EXPIRY_LABEL = "EXPIRY";
    protected static final int DEFAULT_CARD_HOLDER_HINT_COLOR = android.R.color.darker_gray;
    protected static final String DEFAULT_CARD_HOLDER_HINT = "John Doe";
    protected static final String DEFAULT_EXPIRY_HINT = "MM/YY";
    protected static final int DEFAULT_EXPIRY_COLOR = android.R.color.white;
    protected static final int DEFAULT_EXPY_HINT_COLOR = android.R.color.darker_gray;
    protected static final boolean DEFAULT_FLIPPED_VALUE = false;
    protected static final int DEFAULT_DIGITS = 16;
    protected static final char DEFAULT_CARD_NUMBER_HIDE_CHAR = 'X';
    protected static final int DEFAULT_CVV_COLOR = android.R.color.black;
    protected static final int DEFAULT_CVV_HINT_COLOR = android.R.color.darker_gray;
    protected static final int DEFAULT_CARD_NUMBER_GROUP_SIZE = 4;
    protected static final int DEFAULT_MAGNETIC_STRIP_COLOR = android.R.color.black;
    protected static final int DEFAULT_ERROR_COLOR = android.R.color.holo_red_light;
    protected static final CvvVisibility DEFAULT_CVV_VISIBILITY = CvvVisibility.SHOW;
    protected static final boolean DEFAULT_FLIP_ON_FRONT_DATA_EDIT = false;
    protected static final boolean DEFAULT_FLIP_ON_CVV_EDIT = false;
    protected static final boolean DEFAULT_ERRORS_ENABLED_VALUE = false;

    public enum InputType {
        CARD_NUMBER,
        CARD_HOLDER,
        CARD_CVV,
        CARD_EXPIRY
    }

    public enum Error {
        NO_ERROR,
        EXPIRY_INVALID_MONTH,
        EXPIRY_INVALID_YEAR,
        EXPIRY_MISSING_DIGITS,
        EXPIRY_EMPTY,
        CARD_NUMBER_MISSING_DIGITS,
        CARD_NUMBER_EMPTY,
        CARD_HOLDER_EMPTY,
        CVV_EMPTY,
        CVV_MISSING_DIGITS,
        CVV_INVALID,
        CARD_NUMBER_INVALID
    }

    public enum NumberVisibility {
        SHOW(0),
        SHOW_LAST_FOUR(1),
        HIDE(2);

        private int value;

        NumberVisibility(int value) {
            this.value = value;
        }

        public static NumberVisibility fromValue(int value) {
            for (NumberVisibility visibility : values()) {
                if (visibility.value == value) {
                    return visibility;
                }
            }

            return SHOW;
        }
    }

    public enum CvvVisibility {
        SHOW(0),
        HIDE(1);

        private int value;

        CvvVisibility(int value) {
            this.value = value;
        }

        public static CvvVisibility fromValue(int value) {
            for (CvvVisibility visibility : values()) {
                if (visibility.value == value) {
                    return visibility;
                }
            }

            return SHOW;
        }
    }

    public interface CreditCardViewListener {
        void onError(String defaultMessage, Error error);

        void onClick();
    }

    protected TextWatcher cardHolderWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            setCardHolder(charSequence.toString());
        }

        @Override public void afterTextChanged(Editable editable) {
        }
    };
    protected TextWatcher cardExpiryWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            setExpiry(charSequence.toString());
        }

        @Override public void afterTextChanged(Editable editable) {
        }
    };
    protected TextWatcher cardCVVWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            setCVV(charSequence.toString());
        }

        @Override public void afterTextChanged(Editable editable) {
        }
    };
    protected TextWatcher cardNumberWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            setCardNumber(charSequence.toString());
        }

        @Override public void afterTextChanged(Editable editable) {
        }
    };

    /**
     * View's components
     */
    private RelativeLayout frontView;
    private LinearLayout backView;
    private TextView cardNumber;
    private TextView cardHolder;
    private TextView cardHolderLabel;
    private TextView cardExpiryLabel;
    private ImageView cardLogo;
    private TextView expiry;
    private TextView cvv;
    private View magneticStrip;
    private ImageView backLogo;
    private TextView cardNumberError;
    private TextView cardHolderError;
    private TextView expiryError;
    private TextView cvvError;

    private CreditCard creditCard;
    private CreditCardViewListener listener;
    private NumberVisibility numberVisibility = DEFAULT_NUMBER_VISIBILITY;
    private CvvVisibility cvvVisibility = DEFAULT_CVV_VISIBILITY;
    private char hideChar = DEFAULT_CARD_NUMBER_HIDE_CHAR;
    private boolean flipOnClick = DEFAULT_FLIP_ON_CLICK_VALUE;
    private boolean flipped = DEFAULT_FLIPPED_VALUE;
    private int cardNumberGroupSize = DEFAULT_CARD_NUMBER_GROUP_SIZE;
    private boolean flipOnCVVEdit = DEFAULT_FLIP_ON_CVV_EDIT;
    private boolean flipOnFrontDataEdit = DEFAULT_FLIP_ON_FRONT_DATA_EDIT;
    private boolean errorsEnabled = DEFAULT_ERRORS_ENABLED_VALUE;
    private Drawable genericCardBackground;
    private Drawable visaCardBackground;
    private Drawable mastercardCardBackground;
    private Drawable americanExpressCardBackground;
    private Drawable dinersClubCardBackground;
    private Drawable discoverCardBackground;
    private Drawable jcbCardBackground;
    private Drawable chinaUnionpayCardBackground;
    private int genericCardLogo;
    private int visaCardLogo;
    private int mastercardCardLogo;
    private int americanExpressCardLogo;
    private int dinersClubCardLogo;
    private int discoverCardLogo;
    private int jcbCardLogo;
    private int chinaUnionpayCardLogo;

    public CreditCardView(Context context) {
        this(context, null, 0);
    }

    public CreditCardView(Context context, CreditCard creditCard) {
        this(context, null, 0);
        this.creditCard = creditCard;
    }

    public CreditCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreditCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    /**
     * Creates a CreditCardView with the same attributes as the passed one
     * This will only copy all the colors and style's relative stuff
     * @param card CreditCardView to copy
     * @return new CreditCardView object
     */
    public static CreditCardView copyFrom(CreditCardView card){
        CreditCardView copy = new CreditCardView(card.getContext());

        copy.init(null);

        copy.setGenericCardBackground(card.getGenericCardBackground());
        copy.setVisaCardBackground(card.getVisaCardBackground());
        copy.setMastercardCardBackground(card.getMastercardCardBackground());
        copy.setAmericanExpressCardBackground(card.getAmericanExpressCardBackground());
        copy.setDinersClubCardBackground(card.getDinersClubCardBackground());
        copy.setDiscoverCardBackground(card.getDiscoverCardBackground());
        copy.setJcbCardBackground(card.getJcbCardBackground());
        copy.setChinaUnionpayCardBackground(card.getChinaUnionpayCardBackground());
        copy.setGenericCardLogo(card.getGenericCardLogo());
        copy.setVisaCardLogo(card.getVisaCardLogo());
        copy.setMastercardCardLogo(card.getMastercardCardLogo());
        copy.setAmericanExpressCardLogo(card.getAmericanExpressCardLogo());
        copy.setDinersClubCardLogo(card.getDinersClubCardLogo());
        copy.setDiscoverCardLogo(card.getDiscoverCardLogo());
        copy.setJcbCardLogo(card.getJcbCardLogo());
        copy.setChinaUnionpayCardLogo(card.getChinaUnionpayCardLogo());
        copy.setErrorsEnabled(card.errorsEnabled);
        copy.setFlipOnCVVEdit(card.flipOnCVVEdit);
        copy.setFlipOnFrontDataEdit(card.flipOnFrontDataEdit);
        copy.setCardNumberGroupSize(card.cardNumberGroupSize);
        copy.setHideChar(card.hideChar);
        copy.setCardDigits(card.getDigits());
        copy.setCardNumberHint(card.cardNumber.getHint().toString().replace(" ",""));
        copy.setCardHolderLabel(card.cardHolderLabel.getText().toString());
        copy.setCardExpiryLabel(card.cardExpiryLabel.getText().toString());
        copy.setExpiryHint(card.expiry.getHint().toString().replace("/",""));
        copy.setCardHolderHint(card.cardHolder.getHint().toString());
        copy.setCvvHint(card.cvv.getHint().toString());
        copy.setFlipOnClick(card.flipOnClick);
        copy.setCVVVisibility(card.getCvvVisibility());
        copy.setCardNumberVisibility(card.getNumberVisibility());
        copy.setCardNumberColor(new ColorDrawable(card.cardNumber.getCurrentTextColor()));
        copy.setCardNumberHintColor(new ColorDrawable(card.cardNumber.getCurrentHintTextColor()));
        copy.setLabelsTextColor(new ColorDrawable(card.cardHolderLabel.getCurrentTextColor()));
        copy.setCardHolderColor(new ColorDrawable(card.cardHolder.getCurrentTextColor()));
        copy.setCardHolderHintColor(new ColorDrawable(card.cardHolder.getCurrentHintTextColor()));
        copy.setExpiryColor(new ColorDrawable(card.expiry.getCurrentTextColor()));
        copy.setExpiryHintColor(new ColorDrawable(card.expiry.getCurrentHintTextColor()));
        copy.setCvvColor(new ColorDrawable(card.cvv.getCurrentTextColor()));
        copy.setCvvHintColor(new ColorDrawable(card.cvv.getCurrentHintTextColor()));
        copy.setBackViewStripColor((ColorDrawable) card.magneticStrip.getBackground());
        copy.setErrorColor(new ColorDrawable(card.cardNumberError.getCurrentTextColor()));
        copy.flipWithoutAnimation(card.isFlipped(), false);

        return copy;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (flipOnClick) {
            flip();
        }

        if (listener != null) {
            listener.onClick();
        }

        return super.onTouchEvent(event);
    }

    /**
     * Initializes the view
     *
     * @param attrs
     */
    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_credit_card, this, true);

        creditCard = new CreditCard();
        frontView = findViewById(R.id.cardFrontView);
        backView = findViewById(R.id.cardBackView);
        cardNumber = findViewById(R.id.cardNumber);
        cardHolder = findViewById(R.id.cardHolder);
        cardHolderLabel = findViewById(R.id.cardHolderLabel);
        cardExpiryLabel = findViewById(R.id.cardExpiryLabel);
        cardLogo = findViewById(R.id.cardLogo);
        expiry = findViewById(R.id.expiry);
        cvv = findViewById(R.id.cvv);
        magneticStrip = findViewById(R.id.magneticStrip);
        expiryError = findViewById(R.id.expiryError);
        backLogo = findViewById(R.id.backLogo);
        cvvError = findViewById(R.id.cvvError);
        cardHolderError = findViewById(R.id.cardHolderError);
        cardNumberError = findViewById(R.id.cardNumberError);

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CreditCardView, 0, 0);

            setGenericCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_genericCardBackground, DEFAULT_GENERIC_COLOR));
            setVisaCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_visaCardBackground, DEFAULT_VISA_COLOR));
            setMastercardCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_mastercardCardBackground, DEFAULT_MASTERCARD_COLOR));
            setAmericanExpressCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_americanExpressCardLogo, DEFAULT_AMERICAN_EXPRESS_COLOR));
            setDinersClubCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_dinersClubCardLogo, DEFAULT_DINERS_CLUB_COLOR));
            setDiscoverCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_discoverCardBackground, DEFAULT_DISCOVER_COLOR));
            setJcbCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_jcbCardBackground, DEFAULT_JCB_COLOR));
            setChinaUnionpayCardBackground(getCardBackgroundResource(array, R.styleable.CreditCardView_chinaUnionpayCardBackground, DEFAULT_CHINA_UNIONPAY_COLOR));
            setGenericCardLogo(array.getResourceId(R.styleable.CreditCardView_genericCardLogo, DEFAULT_GENERIC_LOGO));
            setVisaCardLogo(array.getResourceId(R.styleable.CreditCardView_visaCardLogo, DEFAULT_VISA_LOGO));
            setMastercardCardLogo(array.getResourceId(R.styleable.CreditCardView_mastercardCardLogo, DEFAULT_MASTERCARD_LOGO));
            setAmericanExpressCardLogo(array.getResourceId(R.styleable.CreditCardView_americanExpressCardLogo, DEFAULT_AMERICAN_EXPRESS_LOGO));
            setDinersClubCardLogo(array.getResourceId(R.styleable.CreditCardView_dinersClubCardLogo, DEFAULT_DINERS_CLUB_LOGO));
            setDiscoverCardLogo(array.getResourceId(R.styleable.CreditCardView_discoverCardLogo, DEFAULT_DISCOVER_LOGO));
            setJcbCardLogo(array.getResourceId(R.styleable.CreditCardView_jcbCardLogo, DEFAULT_JCB_LOGO));
            setChinaUnionpayCardLogo(array.getResourceId(R.styleable.CreditCardView_dinersClubCardLogo, DEFAULT_CHINA_UNIONPAY_LOGO));
            setErrorsEnabled(array.getBoolean(R.styleable.CreditCardView_errorsEnabled, DEFAULT_ERRORS_ENABLED_VALUE));
            setFlipOnCVVEdit(array.getBoolean(R.styleable.CreditCardView_flipOnCVVEdit, DEFAULT_FLIP_ON_CVV_EDIT));
            setFlipOnFrontDataEdit(array.getBoolean(R.styleable.CreditCardView_flipOnFrontDataEdit, DEFAULT_FLIP_ON_FRONT_DATA_EDIT));
            setCardDigits(array.getInt(R.styleable.CreditCardView_digits, DEFAULT_DIGITS));
            setCardNumberGroupSize(array.getInt(R.styleable.CreditCardView_cardNumberGroupSize, DEFAULT_CARD_NUMBER_GROUP_SIZE));
            setHideChar(array.getString(R.styleable.CreditCardView_hideChar));
            setCardNumber(array.getString(R.styleable.CreditCardView_number));
            setCardHolder(array.getString(R.styleable.CreditCardView_cardHolder));
            setExpiry(array.getString(R.styleable.CreditCardView_expireDate));
            setCVV(array.getString(R.styleable.CreditCardView_cvv));
            setCardNumberHint(array.getString(R.styleable.CreditCardView_numberHint));
            setCardHolderLabel(array.getString(R.styleable.CreditCardView_cardHolderLabel));
            setCardExpiryLabel(array.getString(R.styleable.CreditCardView_cardExpiryLabel));
            setExpiryHint(array.getString(R.styleable.CreditCardView_expireDateHint));
            setCardHolderHint(array.getString(R.styleable.CreditCardView_cardHolderHint));
            setCvvHint(array.getString(R.styleable.CreditCardView_cvvHint));
            setFlipOnClick(array.getBoolean(R.styleable.CreditCardView_flipOnClick, DEFAULT_FLIP_ON_CLICK_VALUE));
            setCVVVisibility(CvvVisibility.fromValue(array.getInt(R.styleable.CreditCardView_cvvVisibility, DEFAULT_CVV_VISIBILITY.value)));
            setCardNumberVisibility(NumberVisibility.fromValue(array.getInt(R.styleable.CreditCardView_numberVisibility, DEFAULT_NUMBER_VISIBILITY.value)));
            setCardNumberColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_numberColor, DEFAULT_NUMBER_TEXT_COLOR));
            setCardNumberHintColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_numberHintColor, DEFAULT_NUMBER_HINT_COLOR));
            setLabelsTextColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_labelsTextColor, DEFAULT_LABELS_TEXT_COLOR));
            setCardHolderColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_cardHolderColor, DEFAULT_CARD_HOLDER_COLOR));
            setCardHolderHintColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_cardHolderHintColor, DEFAULT_CARD_HOLDER_HINT_COLOR));
            setExpiryColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_expireDateColor, DEFAULT_EXPIRY_COLOR));
            setExpiryHintColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_expireDateHintColor, DEFAULT_EXPY_HINT_COLOR));
            setCvvColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_cvvColor, DEFAULT_CVV_COLOR));
            setCvvHintColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_cvvHintColor, DEFAULT_CVV_HINT_COLOR));
            setBackViewStripColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_magneticStripColor, DEFAULT_MAGNETIC_STRIP_COLOR));
            setErrorColor((ColorDrawable) getDrawableAttribute(array, R.styleable.CreditCardView_errorColor, DEFAULT_ERROR_COLOR));
            flipWithoutAnimation(array.getBoolean(R.styleable.CreditCardView_flipped, DEFAULT_FLIPPED_VALUE), false);

            array.recycle();
        } else {
            setGenericCardBackground(getCardBackgroundResource(DEFAULT_GENERIC_COLOR));
            setVisaCardBackground(getCardBackgroundResource(DEFAULT_VISA_COLOR));
            setMastercardCardBackground(getCardBackgroundResource(DEFAULT_MASTERCARD_COLOR));
            setAmericanExpressCardBackground(getCardBackgroundResource(DEFAULT_AMERICAN_EXPRESS_COLOR));
            setDinersClubCardBackground(getCardBackgroundResource(DEFAULT_DINERS_CLUB_COLOR));
            setDiscoverCardBackground(getCardBackgroundResource(DEFAULT_DISCOVER_COLOR));
            setJcbCardBackground(getCardBackgroundResource(DEFAULT_JCB_COLOR));
            setChinaUnionpayCardBackground(getCardBackgroundResource(DEFAULT_CHINA_UNIONPAY_COLOR));
            setGenericCardLogo(DEFAULT_GENERIC_LOGO);
            setVisaCardLogo(DEFAULT_VISA_LOGO);
            setMastercardCardLogo(DEFAULT_MASTERCARD_LOGO);
            setAmericanExpressCardLogo(DEFAULT_AMERICAN_EXPRESS_LOGO);
            setDinersClubCardLogo(DEFAULT_DINERS_CLUB_LOGO);
            setDiscoverCardLogo(DEFAULT_DISCOVER_LOGO);
            setJcbCardLogo(DEFAULT_JCB_LOGO);
            setChinaUnionpayCardLogo(DEFAULT_CHINA_UNIONPAY_LOGO);
            setErrorsEnabled(DEFAULT_ERRORS_ENABLED_VALUE);
            setFlipOnCVVEdit(DEFAULT_FLIP_ON_CVV_EDIT);
            setFlipOnFrontDataEdit(DEFAULT_FLIP_ON_FRONT_DATA_EDIT);
            setCardDigits(DEFAULT_DIGITS);
            setCardNumberGroupSize(DEFAULT_CARD_NUMBER_GROUP_SIZE);
            setHideChar(DEFAULT_CARD_NUMBER_HIDE_CHAR);
            setCardNumber("");
            setCardHolder("");
            setExpiry("");
            setCVV("");
            setCardNumberHint(DEFAULT_CARD_NUMBER_HINT);
            setCardHolderLabel(DEFAULT_CARD_HOLDER_HINT);
            setCardExpiryLabel(DEFAULT_CARD_EXPIRY_LABEL);
            setExpiryHint(DEFAULT_EXPIRY_HINT);
            setCardHolderHint(DEFAULT_CARD_HOLDER_HINT);
            setCvvHint("");
            setFlipOnClick(DEFAULT_FLIP_ON_CLICK_VALUE);
            setCVVVisibility(CvvVisibility.fromValue(DEFAULT_CVV_VISIBILITY.value));
            setCardNumberVisibility(NumberVisibility.fromValue(DEFAULT_NUMBER_VISIBILITY.value));
            setCardNumberColor((ColorDrawable) getDrawableAttribute(DEFAULT_NUMBER_TEXT_COLOR));
            setCardNumberHintColor((ColorDrawable) getDrawableAttribute(DEFAULT_NUMBER_HINT_COLOR));
            setLabelsTextColor((ColorDrawable) getDrawableAttribute(DEFAULT_LABELS_TEXT_COLOR));
            setCardHolderColor((ColorDrawable) getDrawableAttribute(DEFAULT_CARD_HOLDER_COLOR));
            setCardHolderHintColor((ColorDrawable) getDrawableAttribute(DEFAULT_CARD_HOLDER_HINT_COLOR));
            setExpiryColor((ColorDrawable) getDrawableAttribute(DEFAULT_EXPIRY_COLOR));
            setExpiryHintColor((ColorDrawable) getDrawableAttribute(DEFAULT_EXPY_HINT_COLOR));
            setCvvColor((ColorDrawable) getDrawableAttribute(DEFAULT_CVV_COLOR));
            setCvvHintColor((ColorDrawable) getDrawableAttribute(DEFAULT_CVV_HINT_COLOR));
            setBackViewStripColor((ColorDrawable) getDrawableAttribute(DEFAULT_MAGNETIC_STRIP_COLOR));
            setErrorColor((ColorDrawable) getDrawableAttribute(DEFAULT_ERROR_COLOR));
            flipWithoutAnimation(DEFAULT_FLIPPED_VALUE, false);
        }

        cardNumber.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (flipOnFrontDataEdit) {
                    flip(false);
                }

                if (errorsEnabled && listener != null) {
                    if (StringUtils.isEmpty(charSequence)) {
                        listener.onError("No card number", Error.CARD_NUMBER_EMPTY);
                    }
                    if (creditCard.getCardNumber().length() < creditCard.getDigits()) {
                        listener.onError("Card number incomplete", Error.CARD_NUMBER_MISSING_DIGITS);
                    }
                    if (!creditCard.hasValidNumber()) {
                        listener.onError("Card number invalid", Error.CARD_NUMBER_INVALID);
                    }
                }
            }

            @Override public void afterTextChanged(Editable editable) {
            }
        });

        expiry.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (flipOnFrontDataEdit) {
                    flip(false);
                }

                if (errorsEnabled && listener != null) {
                    if (StringUtils.isEmpty(creditCard.getExpireDate())) {
                        listener.onError("No expire date", Error.EXPIRY_EMPTY);
                    }
                    if (!creditCard.hasValidMonth()) {
                        listener.onError("Invalid month", Error.EXPIRY_INVALID_MONTH);
                    }
                    if (!creditCard.hasValidYear()) {
                        listener.onError("Invalid year", Error.EXPIRY_INVALID_YEAR);
                    }
                    if (creditCard.getExpireDate().length() < 4) {
                        listener.onError("Expire date incomplete", Error.EXPIRY_MISSING_DIGITS);
                    }
                }
            }

            @Override public void afterTextChanged(Editable editable) {
            }
        });

        cardHolder.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (flipOnFrontDataEdit) {
                    flip(false);
                }
                if (errorsEnabled && listener != null) {
                    if (StringUtils.isEmpty(creditCard.getCardHolder())) {
                        listener.onError("No card holder", Error.CARD_HOLDER_EMPTY);
                    }
                }
            }

            @Override public void afterTextChanged(Editable editable) {
            }
        });

        cvv.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (flipOnCVVEdit) {
                    flip(true);
                }

                if (errorsEnabled && listener != null) {
                    if (StringUtils.isEmpty(creditCard.getCVV())) {
                        listener.onError("No CVV", Error.CVV_EMPTY);
                    }
                    if (creditCard.getCVV().length() < 3) {
                        listener.onError("CVV missing digits", Error.CVV_MISSING_DIGITS);
                    }
                    if (!creditCard.hasValidCVV()) {
                        listener.onError("Invalid CVV", Error.CVV_INVALID);
                    }
                }
            }

            @Override public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * Sets the CVV visibility
     *
     * @param cvvVisibility Must be one of {@link CvvVisibility}
     */
    public void setCVVVisibility(CvvVisibility cvvVisibility) {
        this.cvvVisibility = cvvVisibility;
        cvv.setText(formatCVV(creditCard.getCVV()));
    }

    /**
     * Sets the CreditCardView's listener
     *
     * @param listener {@link CreditCardViewListener}
     */
    public void setListener(CreditCardViewListener listener) {
        this.listener = listener;
    }

    /**
     * Sets the color to all the TextViews that display errors
     *
     * @param drawable color
     */
    public void setErrorColor(ColorDrawable drawable) {
        expiryError.setTextColor(drawable.getColor());
        cvvError.setTextColor(drawable.getColor());
        cardNumberError.setTextColor(drawable.getColor());
        cardHolderError.setTextColor(drawable.getColor());
    }

    /**
     * Sets the color of the back view's magnetic strip
     *
     * @param drawable color
     */
    public void setBackViewStripColor(ColorDrawable drawable) {
        magneticStrip.setBackgroundDrawable(drawable);
    }

    /**
     * Sets the CVV hint text color
     *
     * @param drawable color
     */
    public void setCvvHintColor(ColorDrawable drawable) {
        cvv.setHintTextColor(drawable.getColor());
    }

    /**
     * Sets the CVV hint text
     *
     * @param string Text
     */
    public void setCvvHint(String string) {
        cvv.setHint(string);
    }

    /**
     * Sets the CVV text color
     *
     * @param drawable color
     */
    public void setCvvColor(ColorDrawable drawable) {
        cvv.setTextColor(drawable.getColor());
    }

    /**
     * Sets the hide character that will replace the card number's character that needs to be hidden
     *
     * @param string Text
     */
    public void setHideChar(String string) {
        if (StringUtils.isEmpty(string)) {
            hideChar = DEFAULT_CARD_NUMBER_HIDE_CHAR;
        } else {
            hideChar = string.charAt(0);
        }
        setCardNumber(creditCard.getCardNumber());
        setCVV(creditCard.getCVV());
    }

    /**
     * Sets the card number's length
     *
     * @param digits Length
     */
    public void setCardDigits(int digits) {
        creditCard.setDigits(digits);
        setCardNumber(creditCard.getCardNumber());
    }

    /**
     * Sets the expiry hint text color
     *
     * @param drawable color
     */
    public void setExpiryHintColor(ColorDrawable drawable) {
        expiry.setHintTextColor(drawable.getColor());
    }

    /**
     * Sets the expiry hint text
     *
     * @param string Text
     */
    public void setExpiryHint(String string) {
        if (string == null) {
            expiry.setHint(DEFAULT_EXPIRY_HINT);
        } else {
            expiry.setHint(formatExpiry(string));
        }
    }

    /**
     * Sets the expiry text color
     *
     * @param drawable color
     */
    public void setExpiryColor(ColorDrawable drawable) {
        expiry.setTextColor(drawable.getColor());
    }

    /**
     * Sets the expiry text, this will also format it
     *
     * @param string Text
     */
    public void setExpiry(String string) {
        creditCard.setExpireDate(string);
        expiry.setText(formatExpiry(string));
    }

    /**
     * Sets the card holder hint text color
     *
     * @param drawable color
     */
    public void setCardHolderHintColor(ColorDrawable drawable) {
        cardHolder.setHintTextColor(drawable.getColor());
    }

    /**
     * Sets the card holder hint text
     *
     * @param string Text
     */
    public void setCardHolderHint(String string) {
        if (string == null) {
            cardHolder.setHint(DEFAULT_CARD_HOLDER_HINT);
        } else {
            cardHolder.setHint(string);
        }
    }

    /**
     * Sets the card holder text color
     *
     * @param drawable color
     */
    public void setCardHolderColor(ColorDrawable drawable) {
        cardHolder.setTextColor(drawable.getColor());
    }

    /**
     * Sets the card number hint text color
     *
     * @param drawable color
     */
    public void setCardNumberHintColor(ColorDrawable drawable) {
        cardNumber.setHintTextColor(drawable.getColor());
    }

    /**
     * Sets the card number hint text
     *
     * @param string Text
     */
    public void setCardNumberHint(String string) {
        if (string == null) {
            cardNumber.setHint(DEFAULT_CARD_NUMBER_HINT);
        } else {
//            cardNumber.setHint(formatCardNumber(string));
            cardNumber.setHint(string);
        }
    }

    /**
     * Sets the card background based on the custom values (if available) or the default ones
     * This method should be called only after the card number is set, since the type is set after this call
     *
     * @param cardType Must be one of {@link CreditCard.CardType}
     */
    private void paintCardBackground(CreditCard.CardType cardType) {
        Drawable background = null;
        int logo = 0;

        switch (cardType) {
            case JCB:
                logo = jcbCardLogo;
                background = jcbCardBackground;
                break;

            case GENERIC:
                logo = genericCardLogo;
                background = genericCardBackground;
                break;

            case VISA:
                logo = visaCardLogo;
                background = visaCardBackground;
                break;

            case MASTERCARD:
                logo = mastercardCardLogo;
                background = mastercardCardBackground;
                break;

            case AMERICAN_EXPRESS:
                logo = americanExpressCardLogo;
                background = americanExpressCardBackground;
                break;

            case DINERS_CLUB:
                logo = dinersClubCardLogo;
                background = dinersClubCardBackground;
                break;

            case DISCOVER:
                logo = discoverCardLogo;
                background = discoverCardBackground;
                break;

            case CHINA_UNIONPAY:
                logo = chinaUnionpayCardLogo;
                background = chinaUnionpayCardBackground;
                break;
        }

        frontView.setBackgroundDrawable(background);
        backView.setBackgroundDrawable(background);
        cardLogo.setImageResource(logo);
        backLogo.setImageResource(logo);
    }

    /**
     * Sets the card number visibility
     *
     * @param visibility Must be one of {@link NumberVisibility}
     */
    public void setCardNumberVisibility(NumberVisibility visibility) {
        this.numberVisibility = visibility;
        cardNumber.setText(formatCardNumber(creditCard.getCardNumber()));
    }

    /**
     * Sets the card number text color
     *
     * @param drawable color
     */
    public void setCardNumberColor(ColorDrawable drawable) {
        cardNumber.setTextColor(drawable.getColor());
    }

    /**
     * Sets the text color of the labels (Card holder and Expiry)
     *
     * @param drawable color
     */
    public void setLabelsTextColor(ColorDrawable drawable) {
        cardExpiryLabel.setTextColor(drawable.getColor());
        cardHolderLabel.setTextColor(drawable.getColor());
    }

    /**
     * Sets the card holder label text
     *
     * @param text Text
     */
    public void setCardHolderLabel(String text) {
        if (text == null) {
            cardHolderLabel.setText(DEFAULT_CARD_HOLDER_LABEL);
        } else {
            cardHolderLabel.setText(text);
        }
    }

    /**
     * Sets the expiry label text
     *
     * @param text Text
     */
    public void setCardExpiryLabel(String text) {
        if (text == null) {
            cardExpiryLabel.setText(DEFAULT_CARD_EXPIRY_LABEL);
        } else {
            cardExpiryLabel.setText(text);
        }
    }

    /**
     * Returns the background resource that will be applied to the card
     * This will return either a GradientDrawable or a Drawable that is the tinted rounded card background
     *
     * @param array        TypedArray
     * @param index        Index of the resource (from attrs.xml)
     * @param defaultValue Default value, must be a color reference (E.g. R.color.red) and not a raw color (#ff0000)
     * @return Tinted background
     */
    private Drawable getCardBackgroundResource(TypedArray array, int index, int defaultValue) {
        Drawable drawable = getDrawableAttribute(array, index, defaultValue);
        Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.credit_card_round_bg);

        if (drawable instanceof ColorDrawable) {
            Utility.tintDrawable(background, ((ColorDrawable) drawable).getColor());
        } else if (drawable instanceof GradientDrawable) {
            background = drawable;
        }

        return background;
    }

    /**
     * Returns the background resource that will be applied to the card
     * This will return either a GradientDrawable or a Drawable that is the tinted rounded card background
     * This must be used only when the view is initialized from code and it takes the default resId
     *
     * @param resId Default resId
     * @return tintedBackground
     */
    private Drawable getCardBackgroundResource(int resId) {
        Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.credit_card_round_bg);

        Utility.tintDrawable(background, ((ColorDrawable) ContextCompat.getDrawable(getContext(), resId)).getColor());

        return background;
    }

    /**
     * Fetches a drawable attribute from the TypedArray or returns the defaultValue as a ColorDrawable
     * This should be used when a color needs to be loaded, this will return a color as a ColorDrawable
     *
     * @param array        TypedArray
     * @param index        Index of the resource (from attrs.xml)
     * @param defaultValue Default value, must be a color reference (E.g. R.color.red) and not a raw color (#ff0000)
     * @return Drawable attribute
     */
    private Drawable getDrawableAttribute(TypedArray array, int index, int defaultValue) {
        if (array != null) {
            int resId = array.getResourceId(index, defaultValue);
            return getDrawableAttribute(resId);
        } else {
            return getDrawableAttribute(defaultValue);
        }
    }

    /**
     * Fetches a drawable attribute with the given id
     *
     * @param defaultValue Default value, must be a color reference (E.g. R.color.red) and not a raw color (#ff0000)
     * @return Drawable attribute
     */
    private Drawable getDrawableAttribute(int defaultValue) {
        return ContextCompat.getDrawable(getContext(), defaultValue);
    }

    /**
     * Formats the card number based on the values of numberVisibility,hideChar,groupSize and the length of the card number
     *
     * @param string Original string
     * @return Formatted string
     */
    protected String formatCardNumber(String string) {
        if (StringUtils.isEmpty(string)) {
            return "";
        }

        StringBuilder builder, result;

        builder = new StringBuilder(string);
        result = new StringBuilder();

        if (builder.length() < creditCard.getDigits()) {
            int diff = creditCard.getDigits() - builder.length();

            for (int i = 0; i < diff; i++) {
                builder.append(hideChar);
            }
        }

        if (builder.length() >= creditCard.getDigits()) {
            builder = new StringBuilder(builder.subSequence(0, creditCard.getDigits()));
        }

        switch (numberVisibility) {
            case SHOW_LAST_FOUR:
                if (builder.length() > creditCard.getDigits() - 4) {
                    int length = builder.length() - 4;

                    for (int i = 0; i < length; i++) {
                        if (builder.charAt(i) != Utility.SPACE) {
                            builder.setCharAt(i, hideChar);
                        }
                    }
                }
                break;

            case HIDE:
                for (int i = 0; i < builder.length(); i++) {
                    builder.setCharAt(i, hideChar);
                }
                break;
        }

        for (int i = 0; i < builder.length(); i++) {
            if (i % cardNumberGroupSize == 0 && i > 0) {
                result.append(Utility.SPACE);
            }
            result.append(builder.charAt(i));
        }

        return result.toString();
    }

    /**
     * Formats the expiry so it matches the format MM/YY
     *
     * @param string Original string
     * @return Formatted string
     */
    protected String formatExpiry(String string) {
        if (StringUtils.isEmpty(string)) {
            return "";
        }

        StringBuilder expiry = new StringBuilder(string);

        if (expiry.length() > 4) {
            expiry.delete(4, expiry.length());
        }

        if (expiry.length() >= 3) {
            expiry.insert(2, "/");
        }

        return expiry.toString();
    }

    /**
     * Formats the CVV based on the values of cvvVisibility and hideChar
     *
     * @param string CVV
     * @return Formatted string
     */
    protected String formatCVV(String string) {
        if (StringUtils.isEmpty(string)) {
            return "";
        }

        StringBuilder cvv = new StringBuilder(string);

        if (cvv.length() > 3) {
            cvv.delete(3, cvv.length());
        }

        if (cvvVisibility == CvvVisibility.SHOW) {
            if (cvv.length() < 3) {
                int diff = 3 - cvv.length();

                for (int i = 0; i < diff; i++) {
                    cvv.append(hideChar);
                }
            }
        } else if (cvvVisibility == CvvVisibility.HIDE) {
            for (int i = 0; i < cvv.length(); i++) {
                cvv.setCharAt(i, hideChar);
            }
        }

        return cvv.toString();
    }

    /**
     * Returns the numberVisibility
     *
     * @return One of {@link NumberVisibility}
     */
    public NumberVisibility getNumberVisibility() {
        return numberVisibility;
    }

    /**
     * Sets the numberVisibility
     *
     * @param numberVisibility Must be one of {@link NumberVisibility}
     */
    public void setNumberVisibility(NumberVisibility numberVisibility) {
        this.numberVisibility = numberVisibility;
        cardNumber.setText(formatCardNumber(creditCard.getCardNumber()));
    }

    /**
     * Returns the CVV visibility
     *
     * @return One of {@link CvvVisibility}
     */
    public CvvVisibility getCvvVisibility() {
        return cvvVisibility;
    }

    /**
     * Returns the hide char
     *
     * @return Char
     */
    public char getHideChar() {
        return hideChar;
    }

    /**
     * Sets the hide char
     *
     * @param c Character
     */
    public void setHideChar(Character c) {
        setHideChar(c.toString());
    }

    /**
     * Returns the card number group size
     *
     * @return Group size
     */
    public int getCardNumberGroupSize() {
        return cardNumberGroupSize;
    }

    /**
     * Sets the card number group size
     *
     * @param groupSize Group size, it should be greater than 0 and a divisor of the card number's length
     */
    public void setCardNumberGroupSize(int groupSize) {
        this.cardNumberGroupSize = groupSize;
        setCardNumber(creditCard.getCardNumber());
    }

    /**
     * Sets the expiry error, can be cleared using {@link CreditCardView#hideAllErrors()},
     * setExpiryError(null) will only display an error with no text
     *
     * @param error Error text
     */
    public void setExpiryError(String error) {
        expiryError.setVisibility(View.VISIBLE);
        expiryError.setText(error);
    }

    /**
     * Sets the card number error, can be cleared using {@link CreditCardView#hideAllErrors()},
     * setCardNumberError(null) will only display and error with no text
     *
     * @param error Error text
     */
    public void setCardNumberError(String error) {
        cardNumberError.setVisibility(View.VISIBLE);
        cardNumberError.setText(error);
    }

    /**
     * Sets the CVV error, can be cleared using {@link CreditCardView#hideAllErrors()},
     * setCVVError(null) will only display and error with no text
     *
     * @param error Error text
     */
    public void setCVVError(String error) {
        cvvError.setVisibility(View.VISIBLE);
        cvvError.setText(error);
    }

    /**
     * Sets the card holder error, can be cleared using {@link CreditCardView#hideAllErrors()},
     * setCardHolderError(null) will only display and error with no text
     *
     * @param error Error text
     */
    public void setCardHolderError(String error) {
        cardHolderError.setVisibility(View.VISIBLE);
        cardHolderError.setText(error);
    }

    /**
     * Returns whether error logging is enabled or not (This errors are not meant for debugging but to show the users errors when they type incorrect data),
     * errors can be checked using the listener
     *
     * @return True if error logging is enabled, False otherwise
     */
    public boolean errorsEnabled() {
        return errorsEnabled;
    }

    /**
     * Enables/Disables error logging
     *
     * @param errorsEnabled True to enable error loggins, False otherwise
     */
    public void setErrorsEnabled(boolean errorsEnabled) {
        this.errorsEnabled = errorsEnabled;
    }

    /**
     * Hides all errors and their TextViews, this should be called instead of calling and passing null to the error method
     */
    public void hideAllErrors() {
        expiryError.setText(null);
        cardHolderError.setText(null);
        cardNumberError.setText(null);
        cvvError.setText(null);
        expiryError.setVisibility(View.GONE);
        cardHolderError.setVisibility(View.GONE);
        cardNumberError.setVisibility(View.GONE);
        cvvError.setVisibility(View.GONE);
    }

    /**
     * Enables/Disables flip on click
     *
     * @param flipOnClick True to enable, False otherwise
     */
    public void setFlipOnClick(boolean flipOnClick) {
        this.flipOnClick = flipOnClick;
    }

    /**
     * Returns the card number's max length
     *
     * @return Card number max length
     */
    public int getDigits() {
        return creditCard.getDigits();
    }

    /**
     * Returns the card holder
     *
     * @return Card holder
     */
    public String getCardHolder() {
        return creditCard.getCardHolder();
    }

    /**
     * Sets the card holder
     *
     * @param string Card holder
     */
    public void setCardHolder(String string) {
        creditCard.setCardHolder(string);
        cardHolder.setText(string);
    }

    /**
     * Returns the card number
     *
     * @return Card number
     */
    public String getCardNumber() {
        return creditCard.getCardNumber();
    }

    /**
     * Sets the card number and the relative logo and background
     *
     * @param number Card number
     */
    public void setCardNumber(String number) {
        creditCard.setCardNumber(number);
        cardNumber.setText(formatCardNumber(number));

        paintCardBackground(creditCard.getCardType());
    }

    /**
     * Returns the CVV
     *
     * @return CVV
     */
    public String getCVV() {
        return creditCard.getCVV();
    }

    /**
     * Sets the CVV
     *
     * @param string CVV
     */
    public void setCVV(String string) {
        creditCard.setCvv(string);
        cvv.setText(formatCVV(string));
    }

    /**
     * Returns the expiry
     *
     * @return Expiry
     */
    public String getExpiry() {
        return creditCard.getExpireDate();
    }

    /**
     * Returns the credit card's type
     *
     * @return One of {@link CreditCard.CardType}
     */
    public CreditCard.CardType getCardType() {
        return creditCard.getCardType();
    }

    /**
     * Returns the credit card view's front view
     *
     * @return Front view
     */
    public View getFrontView() {
        return frontView;
    }

    /**
     * Returns the credit card view's back view
     *
     * @return Back view
     */
    public View getBackView() {
        return backView;
    }

    /**
     * Returns the magnetic strip view
     *
     * @return Magnetic strip view
     */
    public View getMagneticStrip() {
        return magneticStrip;
    }

    /**
     * Returns the front card logo view
     *
     * @return Card logo view
     */
    public ImageView getCardLogo() {
        return cardLogo;
    }

    /**
     * Returns the back card logo view
     *
     * @return Card logo view
     */
    public ImageView getBackLogo() {
        return backLogo;
    }

    /**
     * Returns the credit card's data as a CreditCard object.
     * See {@link CreditCard}
     *
     * @return A CreditCard object
     */
    public CreditCard getCreditCardData() {
        return creditCard;
    }

    /**
     * Sets the credit card's data and updates the view
     *
     * @param data CreditCard object
     */
    public void setCreditCardData(CreditCard data) {
        this.creditCard = data;

        setCardHolder(data.getCardHolder());
        setCVV(data.getCVV());
        setExpiry(data.getExpireDate());
        setCardNumber(data.getCardNumber());
    }

    /**
     * Enables/Disable flip when the CVV is being edited.
     * If the card is not flipped and the CVV is being edited, the card will flip back
     *
     * @param flipOnCVVEdit True to enable, False otherwise
     */
    public void setFlipOnCVVEdit(boolean flipOnCVVEdit) {
        this.flipOnCVVEdit = flipOnCVVEdit;
    }

    /**
     * Enables/Disables flip when any of the front TextView is being edited.
     * If the card is flipped and any of the front TextView is being edited, the card will flip to the front side
     *
     * @param flipOnFrontDataEdit True to enable, False otherwise
     */
    public void setFlipOnFrontDataEdit(boolean flipOnFrontDataEdit) {
        this.flipOnFrontDataEdit = flipOnFrontDataEdit;
    }

    /**
     * Returns whether the card is flipped or not
     *
     * @return True if it is flipped, False otherwise
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Flips the card, using a smooth animation and from right to left
     */
    public void flip() {
        flip(!flipped, true, false);
    }

    /**
     * Flips the card based on the passed value, using a smooth animation and from right to left
     *
     * @param flipped True if the back must be shown, False otherwise
     */
    public void flip(boolean flipped) {
        flip(flipped, true, false);
    }

    /**
     * Flips the card based based on the passed values
     *
     * @param flipped     True if the back must be shown, False otherwise
     * @param smoothFlip  True if the animation must be shown, False otherwise
     * @param leftToRight True if the animation must start from left and go to right, False otherwise
     */
    public void flip(boolean flipped, boolean smoothFlip, boolean leftToRight) {   //todo add flip bottom to top / top to bottom
        if (this.flipped == flipped) {
            return;
        }

        this.flipped = flipped;

        if (flipped) {
            CreditCardViewAnimator.animate(frontView, backView, smoothFlip);
        } else {
            CreditCardViewAnimator.animate(backView, frontView, smoothFlip);
        }
    }

    /**
     * Flips the card without using any animation, it just sets the flipped state to the views
     * This should only be used for the preview in Android Studio
     *
     * @param flipped True if the back must be shown, False otherwise
     */
    private void flipWithoutAnimation(boolean flipped, boolean leftToRight) {
        if (this.flipped == flipped) {
            return;
        }

        this.flipped = flipped;

        if (flipped) {
            frontView.setScaleX(0f);
            backView.setScaleX(1f);
        } else {
            frontView.setScaleX(1f);
            backView.setScaleX(0f);
        }
    }

    /**
     * Pairs the given TextView with the specified card's component
     * Whenever the passed TextView content will be edited, it will be written in the specified card's component
     * This will save time and make you write less boilerplate code
     *
     * @param inputView Input TextView (can be any of the TextView's children, for instance an EditText or a TextInputEditText)
     * @param inputType Must be one of {@link InputType}
     */
    public void pairInputText(TextView inputView, InputType inputType) {
        if (inputView == null) {
            return;
        }

        switch (inputType) {
            case CARD_NUMBER:
                inputView.addTextChangedListener(cardNumberWatcher);
                break;

            case CARD_HOLDER:
                inputView.addTextChangedListener(cardHolderWatcher);
                break;

            case CARD_CVV:
                inputView.addTextChangedListener(cardCVVWatcher);
                break;

            case CARD_EXPIRY:
                inputView.addTextChangedListener(cardExpiryWatcher);
                break;
        }
    }

    /**
     * Returns the generic card background
     *
     * @return Background drawable
     */
    public Drawable getGenericCardBackground() {
        return genericCardBackground;
    }

    /**
     * Sets the generic card background
     *
     * @param genericCardBackground Background drawable
     */
    public void setGenericCardBackground(Drawable genericCardBackground) {
        this.genericCardBackground = genericCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.GENERIC) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the visa card background
     *
     * @return Background drawable
     */
    public Drawable getVisaCardBackground() {
        return visaCardBackground;
    }

    /**
     * Sets the visa card background
     *
     * @param visaCardBackground Background drawable
     */
    public void setVisaCardBackground(Drawable visaCardBackground) {
        this.visaCardBackground = visaCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.VISA) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the mastercard card background
     *
     * @return Background drawable
     */
    public Drawable getMastercardCardBackground() {
        return mastercardCardBackground;
    }

    /**
     * Sets the mastercard card background
     *
     * @param mastercardCardBackground Background drawable
     */
    public void setMastercardCardBackground(Drawable mastercardCardBackground) {
        this.mastercardCardBackground = mastercardCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.MASTERCARD) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the american express card background
     *
     * @return Background drawable
     */
    public Drawable getAmericanExpressCardBackground() {
        return americanExpressCardBackground;
    }

    /**
     * Sets the america express card background
     *
     * @param americanExpressCardBackground
     */
    public void setAmericanExpressCardBackground(Drawable americanExpressCardBackground) {
        this.americanExpressCardBackground = americanExpressCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.AMERICAN_EXPRESS) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the diners club card background
     *
     * @return Background drawable
     */
    public Drawable getDinersClubCardBackground() {
        return dinersClubCardBackground;
    }

    /**
     * Sets the diners club card background
     *
     * @param dinersClubCardBackground Background drawable
     */
    public void setDinersClubCardBackground(Drawable dinersClubCardBackground) {
        this.dinersClubCardBackground = dinersClubCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.DINERS_CLUB) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the discover card background
     *
     * @return Background drawable
     */
    public Drawable getDiscoverCardBackground() {
        return discoverCardBackground;
    }

    /**
     * Sets the discover card background
     *
     * @param discoverCardBackground Background drawable
     */
    public void setDiscoverCardBackground(Drawable discoverCardBackground) {
        this.discoverCardBackground = discoverCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.DISCOVER) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the jcb card background
     *
     * @return Background drawable
     */
    public Drawable getJcbCardBackground() {
        return jcbCardBackground;
    }

    /**
     * Sets the jcb card background
     *
     * @param jcbCardBackground Background drawable
     */
    public void setJcbCardBackground(Drawable jcbCardBackground) {
        this.jcbCardBackground = jcbCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.JCB) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the china unionpay card background
     *
     * @return  Background drawable
     */
    public Drawable getChinaUnionpayCardBackground() {
        return chinaUnionpayCardBackground;
    }

    /**
     * Sets the china unionpay card background
     *
     * @param chinaUnionpayCardBackground Background drawable
     */
    public void setChinaUnionpayCardBackground(Drawable chinaUnionpayCardBackground) {
        this.chinaUnionpayCardBackground = chinaUnionpayCardBackground;

        if (creditCard.getCardType() == CreditCard.CardType.CHINA_UNIONPAY) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the generic logo
     *
     * @return Logo resId
     */
    public int getGenericCardLogo() {
        return genericCardLogo;
    }

    /**
     * Sets the generic logo
     *
     * @param genericCardLogo Logo resId
     */
    public void setGenericCardLogo(int genericCardLogo) {
        this.genericCardLogo = genericCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.GENERIC) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the visa logo
     *
     * @return Logo resId
     */
    public int getVisaCardLogo() {
        return visaCardLogo;
    }

    /**
     * Sets the visa logo
     *
     * @param visaCardLogo Logo resId
     */
    public void setVisaCardLogo(int visaCardLogo) {
        this.visaCardLogo = visaCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.VISA) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the mastercard logo
     *
     * @return Logo resId
     */
    public int getMastercardCardLogo() {
        return mastercardCardLogo;
    }

    /**
     * Sets the mastercard logo
     *
     * @param mastercardCardLogo Logo resId
     */
    public void setMastercardCardLogo(int mastercardCardLogo) {
        this.mastercardCardLogo = mastercardCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.MASTERCARD) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the american express logo
     *
     * @return Logo resId
     */
    public int getAmericanExpressCardLogo() {
        return americanExpressCardLogo;
    }

    /**
     * Sets the american express logo
     *
     * @param americanExpressCardLogo Logo resId
     */
    public void setAmericanExpressCardLogo(int americanExpressCardLogo) {
        this.americanExpressCardLogo = americanExpressCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.AMERICAN_EXPRESS) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the diners club logo
     *
     * @return Logo resId
     */
    public int getDinersClubCardLogo() {
        return dinersClubCardLogo;
    }

    /**
     * Sets the diners club logo
     *
     * @param dinersClubCardLogo Logo resId
     */
    public void setDinersClubCardLogo(int dinersClubCardLogo) {
        this.dinersClubCardLogo = dinersClubCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.DINERS_CLUB) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the discover logo
     *
     * @return Logo resId
     */
    public int getDiscoverCardLogo() {
        return discoverCardLogo;
    }

    /**
     * Sets the discover logo
     *
     * @param discoverCardLogo Logo resId
     */
    public void setDiscoverCardLogo(int discoverCardLogo) {
        this.discoverCardLogo = discoverCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.DISCOVER) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the jcb logo
     *
     * @return Logo resId
     */
    public int getJcbCardLogo() {
        return jcbCardLogo;
    }

    /**
     * Sets the jcb logo
     *
     * @param jcbCardLogo Logo resId
     */
    public void setJcbCardLogo(int jcbCardLogo) {
        this.jcbCardLogo = jcbCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.JCB) {
            paintCardBackground(creditCard.getCardType());
        }
    }

    /**
     * Returns the china unionpay logo
     *
     * @return Logo resId
     */
    public int getChinaUnionpayCardLogo() {
        return chinaUnionpayCardLogo;
    }

    /**
     * Sets the china unionpay logo
     *
     * @param chinaUnionpayCardLogo Logo resId
     */
    public void setChinaUnionpayCardLogo(int chinaUnionpayCardLogo) {
        this.chinaUnionpayCardLogo = chinaUnionpayCardLogo;

        if (creditCard.getCardType() == CreditCard.CardType.CHINA_UNIONPAY) {
            paintCardBackground(creditCard.getCardType());
        }
    }
}
