package org.example.ponycafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreMainActivity extends AppCompatActivity {

    private ArrayList<MenuModal2> foodList2 = new ArrayList<MenuModal2>();
    DatabaseReference dbref;
    GridView gridView;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);

        gridView = findViewById(R.id.idGRV);
        menuAdapter = new MenuAdapter(foodList2, this);
        gridView.setAdapter(menuAdapter);

        dbref = FirebaseDatabase.getInstance().getReference("menu");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MenuModal2 menu = dataSnapshot.getValue(MenuModal2.class);
                    foodList2.add(menu);
                }
                menuAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_buscar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("TAG", "new text ==> " + newText);
                menuAdapter.getFilter().filter(newText);
                menuAdapter.notifyDataSetChanged();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping_cart:
                lanzarShoppingCart();
                break;
            case R.id.user_profile:
                lanzarUserProfile();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lanzarUserProfile() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
        finish();
    }

    private void lanzarShoppingCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }

    public class MenuAdapter extends BaseAdapter implements Filterable {
        private ArrayList<MenuModal2> foodList2 = new ArrayList<MenuModal2>();
        private ArrayList<MenuModal2> foodListFilter = new ArrayList<MenuModal2>();
        private Context context;

        public MenuAdapter(ArrayList<MenuModal2> foodList2, Context context) {
            this.foodList2 = foodList2;
            this.foodListFilter = foodList2;
            this.context = context;
        }

        @Override
        public int getCount() {
            return foodList2.size();
        }

        @Override
        public Object getItem(int i) {
            return foodList2.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View view1 = view;
            MenuModal2 itemsModal = foodList2.get(i);

            if (view1 == null){
                view1 = LayoutInflater.from(context).inflate(R.layout.gridview_food_item, viewGroup, false);
            }

            ImageView imageName = view1.findViewById(R.id.idIvGrid);
            TextView tvName = view1.findViewById(R.id.idTvGrid);

            String name = itemsModal.getName();
            String desc = itemsModal.getDesc();
            String img = itemsModal.getImg();
            int cost = itemsModal.getCost();

            Picasso.get().load(img).into(imageName);
            tvName.setText(name);

            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("CLICKED ITEM", "===> " + itemsModal.getName());
                    startActivity(new Intent(StoreMainActivity.this, SelectedItemActivity.class).putExtra("data", itemsModal));
                }
            });

            return view1;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if (charSequence == null || charSequence.length() == 0){
                        filterResults.count = foodListFilter.size();
                        filterResults.values = foodListFilter;
                    }else {
                        String searchChr = charSequence.toString().toLowerCase();
                        ArrayList<MenuModal2> searchResult = new ArrayList<MenuModal2>();

                        for(MenuModal2 menuModal2:foodListFilter){
                            if(menuModal2.getName().toLowerCase().contains(searchChr) || menuModal2.getDesc().toLowerCase().contains(searchChr)){
                                searchResult.add(menuModal2);
                            }
                        }
                        filterResults.count = searchResult.size();
                        filterResults.values = searchResult;
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    foodList2 = (ArrayList<MenuModal2>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}
