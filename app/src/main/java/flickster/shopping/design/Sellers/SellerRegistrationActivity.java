package flickster.shopping.design.Sellers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import flickster.shopping.design.Buyer.MainActivity;
import flickster.shopping.design.Buyer.RegisterActivity;
import flickster.shopping.design.R;

public class SellerRegistrationActivity extends AppCompatActivity {
private Button sellerLoginBegin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);

sellerLoginBegin= findViewById(R.id.seller_already_have_account_btn);


sellerLoginBegin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SellerRegistrationActivity.this, SellerLoginActivity.class);
        startActivity(intent);
    }
});





    }



}