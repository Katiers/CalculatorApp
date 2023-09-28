package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// имплементируем данные с помощью View.OnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // числа и опереация
    private EditText etNum1, etNum2, etOperation;
    // результат
    private TextView tvResultText;
    // кнопка
    private Button btnCalculateResult;

    // всплывающее сообщение, сигнализирующее об ошибке
    private Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // поиск данных по идентификатору и дальнейшая инициализация
        etNum1=findViewById(R.id.num1_edit_text);
        etNum2=findViewById(R.id.num2_edit_text);
        etOperation=findViewById(R.id.operation_edit_text);
        tvResultText=findViewById(R.id.result_text_view);
        btnCalculateResult=findViewById(R.id.calculate_result_button);
        btnCalculateResult.setOnClickListener(this);
    }

    // метод факта нажатия на кнопку и описание операций

    @Override
    public void onClick(View v) {
        float num1, num2, result=0;
        boolean correctOperaion=true;
        String opeation="";

        // отлавливаем ошибку
        try {
            // числа,введенные пользователями
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());

            // операция, выбранная пользователем
            String task = etOperation.getText().toString();

            // операции
            switch (task) {
                // сложение
                case "+":
                    result = num1 + num2;
                    break;
                // вычитание
                case "-":
                    result = num1 - num2;
                    break;
                // умножение
                case "*":
                    result = num1 * num2;
                    break;
                // деление
                case "/":
                    if(num2==0)throw new ArithmeticException();
                    result = num1 / num2;
                    break;
                default:
                    correctOperaion=false;
                    break;
            }
        }

        // ошибки
        // ошибка при делении на 0
        catch (ArithmeticException e){
            int duration=Toast.LENGTH_SHORT;
            if(toastError!=null){
                toastError.cancel();
            }
            toastError=Toast.makeText(this,R.string.divide_zero,duration);
            toastError.show();
            return;
        }

        // ошибка при незаполненных полях
        catch (NullPointerException e){
            int duration=Toast.LENGTH_SHORT;
            if(toastError!=null){
                toastError.cancel();
            }
            toastError=Toast.makeText(this,R.string.null_data,duration);
            toastError.show();
            return;
        }

        // ошибка ввода данных
        catch (NumberFormatException e){
            int duration=Toast.LENGTH_SHORT;
            if(toastError!=null){
                toastError.cancel();
            }
            toastError=Toast.makeText(this,R.string.wrong_format,duration);
            toastError.show();
            return;
        }

        // проверка
        if(correctOperaion){
            // отображение результата
            tvResultText.setText(num1+" "+opeation+" "+num2+" = "+result);
        }
        else{
            int duration=Toast.LENGTH_SHORT;
            if(toastError!=null){
                toastError.cancel();
            }
            toastError=Toast.makeText(this,R.string.wrong_format,duration);
            toastError.show();
            return;
        }


    }
}