package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);

        // уникнути програмного показу клавіатури знизу в фокусі
        display.setShowSoftInputOnFocus(false);

//        display.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(getString(R.string.display).equals(display.getText().toString())){
//                    display.setText("");
//                }
//            }
//        });
    }

    private void updateText(String strToAdd){
        String old = display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String left = old.substring(0,cursorPos);
        String right = old.substring(cursorPos);

        //якщо користувач починає вводити символи...
        if(getString(R.string.display).equals(display.getText().toString())){
        //if(display.getText().toString().isEmpty()){
            // перший введений символ...
            display.setText(strToAdd);
            display.setSelection(cursorPos+1);
        }
        // якщо на дисплеї вже є введені символи,тобто ми продовжуємо вводити число...
        // або у вже раніше введену цифру треба посередині доставити ще одну,ставши курсором...
        else{
            display.setText(String.format("%s%s%s",left,strToAdd,right));
            display.setSelection(cursorPos + 1);
        }

    }

    public void zeroBtn(View view){
        updateText("0");

    }

    public void oneBtn(View view){
        updateText("1");
    }

    public void twoBtn(View view){
        updateText("2");
    }

    public void threeBtn(View view){
        updateText("3");
    }

    public void fourBtn(View view){
        updateText("4");
    }

    public void fiveBtn(View view){
        updateText("5");
    }

    public void sixBtn(View view){
        updateText("6");
    }

    public void sevenBtn(View view){
        updateText("7");
    }

    public void eightBtn(View view){
        updateText("8");
    }

    public void nineBtn(View view){
        updateText("9");
    }

    public void divideBtn(View view){
        updateText("÷");
    }

    public void clearBtn(View view){
        display.setText("");
    }

    public void multiplyBtn(View view){
        updateText("*");
    }

    public void addBtn(View view){
        updateText("+");
    }

    public void subtractBtn(View view){
        updateText("-");
    }

    public void dotBtn(View view){
        updateText(".");
    }

    public void plusMinusBtn(View view){
        updateText("-");
    }

    public void equalBtn(View view){

    }

    // логіка побудови відкритих або закритих дужок грунтується на кількості вже введених відкритих
    // (закритих)дужок,тому що кнопка для дужок одна.І відповідно від кількості набраних тих чи
    // інших дужок буде обиратись наступна ДОПУСТИМА варіація дужки.
    public void bracketsBtn(View view){
        //визначила позицію,де знаходиться курсор...
        int pos=display.getSelectionStart();
        //кількість відкритих і закритих дужок...
        int open=0;
        int close=0;
        int len=display.getText().length();
        //циклом від початку строчки до позиції курсору рахуємо кількість дужок відповідного виду...
        for (int i = 0; i < pos; i++) {

            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                open++;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                close++;
            }
        }
        //якщо рівна кількість відповідних дужок або нам треба ввести (( - тобто дві підряд дужки
        // і відповідно закриваючої бути не може або
        //остання введена дужка відкриваюча,то закриваючої логічно бути не може теж...
        if(open==close || display.getText().toString().substring(len-1,len).equals("(")){
            updateText("(");

        }
       else if(close<open && !display.getText().toString().substring(len-1,len).equals("(")){
            updateText(")");

        }
        display.setSelection(pos+1);
    }

    public void backspaceBtn(View view){
        //визначила позицію,де знаходиться курсор...
        int pos=display.getSelectionStart();
        //визначаю довжину введеного тексту...
        int len = display.getText().length();
        //перевірка,щоб не вилізти за межі...
        if(pos!=0 && len!=0){
            //перевела текст в стрінгбілдер,щоб потім здійснювати з ним операції...
            StringBuilder stringBuilder= new StringBuilder(display.getText().toString());
            stringBuilder.replace(pos-1,pos,"");
            display.setText(stringBuilder);
            display.setSelection(pos-1);
        }
    }


}