package com.arctouch.arcnews.infra;

import com.arctouch.arcnews.R;
import com.arctouch.arcnews.domain.rss.Item;

import rx.Observable;
import rx.Subscription;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andresato on 1/17/17.
 */

public class ObservableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    implements Closeable{

  private Subscription subscription;
  private List<Item> items = new ArrayList<Item>();

  @Override
  public void close() {
    subscription.unsubscribe();
  }

  public void setObservable(Observable<List<Item>> observable) {
    if (subscription != null) {
      subscription.unsubscribe();
    }

    subscription = observable
        .subscribe(newItems -> {
          this.items.addAll(newItems);
          notifyDataSetChanged();
    });
  }

  private class ItemHolder extends RecyclerView.ViewHolder {

    TextView title;
    public ItemHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.titleItem);
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header,parent,false);
    return new ItemHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ItemHolder itemHolder = (ItemHolder)holder;
    itemHolder.title.setText(items.get(position).getTitle());
  }

  @Override
  public int getItemCount() {
    return items.size();
  }
}
