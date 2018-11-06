package com.momo.app2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;

import static java.math.BigDecimal.valueOf;

public class MainActivity extends AppCompatActivity {

    private AtomicInteger counter = new AtomicInteger(1);
    private BigDecimal price = valueOf(1.1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        display(counter.get());
        BigDecimal currentPrice = price.multiply(valueOf(counter.doubleValue()));
        displayPrice(currentPrice);
    }

    public void increment(View view) {
        display(counter.incrementAndGet());
    }

    public void decrement(View view) {
        display(counter.decrementAndGet());
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayPrice(BigDecimal number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
