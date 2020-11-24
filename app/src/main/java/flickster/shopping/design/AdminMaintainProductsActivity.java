package flickster.shopping.design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainProductsActivity extends AppCompatActivity {
private Button applyChanges , deleteBtn;
private EditText name,  price, description;
private ImageView imageView;
private DatabaseReference productsRef;

    private String ProductID ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);
        ProductID=getIntent().getStringExtra("pid");

productsRef = FirebaseDatabase.getInstance().getReference().child("Products").child(ProductID);
        applyChanges = findViewById(R.id.apply_changes_btn);
        name = findViewById(R.id.product_name_maintain);
        description = findViewById(R.id.product_description_maintain);
price = findViewById(R.id.product_price_maintain);
imageView = findViewById(R.id.product_image_maintain);
deleteBtn =findViewById(R.id.delete_product_btn);

displaySpecificProductInfo();
applyChanges.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        applyChanges();
    }
});

deleteBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        deleteThisProduct();
    }
});
    }

    private void deleteThisProduct() {
        productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminCategoryActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AdminMaintainProductsActivity.this, "The Product is deleted successfully .", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void applyChanges() {


        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pDescription = description.getText().toString();

        if(pName.equals(""))
        {
            Toast.makeText(this, "Please fill the Product Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(pPrice.equals(""))
        {
            Toast.makeText(this, "Please fill the Product Price field.", Toast.LENGTH_SHORT).show();
        }
       else  if(pDescription.equals(""))
        {
            Toast.makeText(this, "Please fill the Product Description field.", Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", ProductID);
            productMap.put("description", pDescription);
            productMap.put("price", pPrice);
            productMap.put("pname", pName);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AdminMaintainProductsActivity.this, "Changes applied successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }

    }

    private void displaySpecificProductInfo() {

        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String pname = snapshot.child("pname").getValue().toString();
                    String pprice = snapshot.child("price").getValue().toString();
                    String pdescription = snapshot.child("description").getValue().toString();
                    String pimage= snapshot.child("image").getValue().toString();

name.setText(pname);
price.setText(pprice);
description.setText(pdescription);
                    Picasso.get().load(pimage).into(imageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}