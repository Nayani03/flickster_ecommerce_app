package flickster.shopping.design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import flickster.shopping.design.Model.Cart;
import flickster.shopping.design.Prevalent.Prevalent;
import flickster.shopping.design.ViewHolder.CartViewHolder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView txtTotalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NextProcessBtn=(Button) findViewById(R.id.next_btn);
        txtTotalAmount =(TextView) findViewById(R.id.total_price);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        final DatabaseReference cartListReference = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options =new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListReference.child("User View")
                .child(Prevalent.currentOnlineUser.getPhone()).child("Products"),Cart.class)
                .build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter =new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull Cart cart) {
                cartViewHolder.txtProductQuantity.setText("Quantity = " +cart.getQuantity());
                cartViewHolder.txtProductPrice.setText("Price "+cart.getPrice());
                cartViewHolder.txtProductName.setText(cart.getPname());

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                CartViewHolder holder =new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}