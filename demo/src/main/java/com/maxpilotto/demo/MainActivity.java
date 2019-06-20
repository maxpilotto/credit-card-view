package com.maxpilotto.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.maxpilotto.creditcardview.CreditCardView;

public class MainActivity extends AppCompatActivity {

    private CreditCardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card = findViewById(R.id.card);
        card.setNumberVisibility(CreditCardView.NumberVisibility.HIDE);
        card.setCVVVisibility(CreditCardView.CVVVisibility.HIDE);
        card.setFlipOnClick(false);
        card.setFlipOnCVVEdit(false);
        card.setFlipOnFrontDataEdit(false);

        ((Switch)findViewById(R.id.showNumber)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    card.setNumberVisibility(CreditCardView.NumberVisibility.SHOW);
                }else {
                    card.setNumberVisibility(CreditCardView.NumberVisibility.HIDE);
                }
            }
        });

        ((Switch)findViewById(R.id.showCvv)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    card.setCVVVisibility(CreditCardView.CVVVisibility.SHOW);
                }else {
                    card.setCVVVisibility(CreditCardView.CVVVisibility.HIDE);
                }
            }
        });

        ((Switch)findViewById(R.id.flipOnClick)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                card.setFlipOnClick(b);
            }
        });

        ((Switch)findViewById(R.id.flipOnCvvEdit)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                card.setFlipOnCVVEdit(b);
            }
        });

        ((Switch)findViewById(R.id.flipOnFrontEdit)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                card.setFlipOnFrontDataEdit(b);
            }
        });

        ((EditText)findViewById(R.id.groupSize)).addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    Integer size = Integer.parseInt(((EditText)findViewById(R.id.groupSize)).getText().toString());
                    card.setNumberGroupSize(size);
                }catch (Exception e){

                }
            }

            @Override public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.digits)).addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    Integer size = Integer.parseInt(((EditText)findViewById(R.id.digits)).getText().toString());
                    card.setCardDigits(size);
                }catch (Exception e){

                }
            }

            @Override public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.hideChar)).addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                card.setHideChar(((EditText)findViewById(R.id.hideChar)).getText().toString());
            }

            @Override public void afterTextChanged(Editable editable) {

            }
        });

        card.pairInputText((EditText)findViewById(R.id.cardCvv), CreditCardView.InputType.CARD_CVV);
        card.pairInputText((EditText)findViewById(R.id.expireDate), CreditCardView.InputType.CARD_EXPIRY);
        card.pairInputText((EditText)findViewById(R.id.number), CreditCardView.InputType.CARD_NUMBER);
        card.pairInputText((EditText)findViewById(R.id.holder), CreditCardView.InputType.CARD_HOLDER);
    }
}
