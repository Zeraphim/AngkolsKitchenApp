package com.example.angkolskitchenapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angkolskitchenapp.R;
import com.example.angkolskitchenapp.model.MyOrderModel;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    List<MyOrderModel> orderModelList;

    public MyOrderAdapter(Context context, List<MyOrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.date.setText(orderModelList.get(position).getCurrentDate());
        holder.time.setText(orderModelList.get(position).getCurrentTime());
        holder.name.setText(orderModelList.get(position).getProductName());
        holder.price.setText(orderModelList.get(position).getProductPrice());
        holder.totalPrice.setText(String.valueOf(orderModelList.get(position).getTotalPrice()));
        holder.quantity.setText(orderModelList.get(position).getTotalQuantity());

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView date, time, name, price, totalPrice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            totalPrice = itemView.findViewById(R.id.total_price);
            quantity = itemView.findViewById(R.id.total_quantity);
        }
    }
}
