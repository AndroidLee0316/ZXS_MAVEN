package com.pingan.debug.net;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder>{

  private final List<FunctionItem> functionItems;

  public FunctionAdapter(List<FunctionItem> functionItems) {
    this.functionItems = functionItems;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_function, parent, false);
    final ViewHolder viewHolder = new ViewHolder(itemView);
    viewHolder.functionView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        FunctionItem item = functionItems.get(viewHolder.getAdapterPosition());
        Intent intent = new Intent(v.getContext(), item.clz);
        v.getContext().startActivity(intent);
      }
    });
    return viewHolder;
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    FunctionItem item = functionItems.get(position);
    holder.functionView.setText(item.name);
  }

  @Override public int getItemCount() {
    return functionItems.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.function_view) TextView functionView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
