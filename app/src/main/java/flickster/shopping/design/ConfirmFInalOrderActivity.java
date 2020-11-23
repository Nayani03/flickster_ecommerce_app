package flickster.shopping.design;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmFInalOrderActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, addessEditText, cityEditText;
    private Button confirmOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_f_inal_order);


        confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);
        nameEditText = (EditText)  findViewById(R.id.shipment_name);
        phoneEditText = (EditText)  findViewById(R.id.shipment_phone);
        addessEditText = (EditText)  findViewById(R.id.shipment_address);
        cityEditText = (EditText)  findViewById(R.id.shipment_city);

    }
}