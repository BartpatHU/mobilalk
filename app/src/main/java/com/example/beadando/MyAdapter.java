package com.example.beadando;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beadando.models.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ShowActivity activity;
    private List<Customer> mList;
    private FirebaseFirestore db =FirebaseFirestore.getInstance();

    public MyAdapter(ShowActivity activity, List<Customer> mList){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        Customer item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uName", item.getName());
        bundle.putString("uHref", item.getHref());
        bundle.putString("uStatus", item.getStatus());
        bundle.putString("uStatusReason", item.getStatusReason());

        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void deleteData(int position){
        Customer item = mList.get(position);
        db.collection("Document").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.href.setText((mList.get(position).getHref()));
        holder.status.setText((mList.get(position).getStatus()));
        holder.statusreason.setText((mList.get(position).getStatusReason()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, href, status, statusreason;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            href = itemView.findViewById(R.id.href_text);
            status = itemView.findViewById(R.id.status_text);
            statusreason = itemView.findViewById(R.id.statusreason_text);
        }
    }
}
