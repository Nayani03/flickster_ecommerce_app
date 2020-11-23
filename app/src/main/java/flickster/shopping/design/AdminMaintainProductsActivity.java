package flickster.shopping.design;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMaintainProductsActivity extends AppCompatActivity {
private Button applyChanges;
private EditText name,  price, description;
private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);


        applyChanges = findViewById(R.id.ally_changes_btn);
        name = findViewById(R.id.product_name_maintain);
        description = findViewById(R.id.product_description_maintain);
price = findViewById(R.id.product_price_maintain);
imageView = findViewById(R.id.product_image_maintain);

    }
}