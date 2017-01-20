package com.demoapp.john.johndemoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demoapp.john.johndemoapp.models.Product;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ProductViewHolder> {
    Context context;
    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout cv;
        TextView productName;
        TextView productPrice;
        ImageView productImage;


        ProductViewHolder(View itemView) {
            super(itemView);
            cv = (RelativeLayout)itemView.findViewById(R.id.cv);
            productName = (TextView)itemView.findViewById(R.id.name);
            productPrice = (TextView)itemView.findViewById(R.id.price);
            productImage = (ImageView)itemView.findViewById(R.id.image);
        }
    }

    List<Product> products;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    RVAdapter(List<Product> products){
        this.products = products;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.productName.setText(products.get(i).getName());
        productViewHolder.productPrice.setText("GH₵ "+products.get(i).getPrice());
        Glide.with(getContext()).load(products.get(i).getImage()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(productViewHolder.productImage);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
