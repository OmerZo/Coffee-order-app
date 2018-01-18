package com.learn.zohky.coffeeorderapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final int COFFEE_PRICE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void subClick(View view) {
        TextView tvQuantity = (TextView)findViewById(R.id.tvQuantity);
        int quantity = Integer.valueOf(tvQuantity.getText().toString());
        if(quantity != 0){
            tvQuantity.setText(String.valueOf(quantity - 1));
        }
    }

    public void addClick(View view) {
        TextView tvQuantity = (TextView)findViewById(R.id.tvQuantity);
        int quantity = Integer.valueOf(tvQuantity.getText().toString());
        tvQuantity.setText(String.valueOf(quantity + 1));
    }

    public void orderClick(View view) {
        int quantity = Integer.valueOf(((TextView)findViewById(R.id.tvQuantity)).getText().toString());
        if(quantity != 0) {
            String orderName = ((EditText) findViewById(R.id.etOrderName)).getText().toString();
            boolean withCream = ((CheckBox) findViewById(R.id.cbWhippedCream)).isChecked();
            boolean withChocolate = ((CheckBox) findViewById(R.id.cbChocolate)).isChecked();
            String cream = getResources().getString(R.string.without_cream);
            String chocolate = getResources().getString(R.string.without_chocolate);
            int totalPrice = COFFEE_PRICE * quantity;
            if (withCream) {
                totalPrice += quantity;
                cream = getResources().getString(R.string.with_cream);
            }
            if (withChocolate) {
                totalPrice += quantity;
                chocolate = getResources().getString(R.string.with_chocolate);
            }
//            String orderStr = getString(R.string.quantity_summary,quantity) + cream + chocolate + " at the price of " + totalPrice + "$";
            String numberFormat = NumberFormat.getCurrencyInstance().format(totalPrice);
            String orderStr = getResources().getString(R.string.order_summary,quantity,cream,chocolate,numberFormat);
//            composeEmail(getResources().getString(R.string.Coffee_order_for, orderName),orderStr);
            Toast.makeText(this, orderStr, Toast.LENGTH_LONG).show();
        }
    }

    public void composeEmail(String subject, String emailBody) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
