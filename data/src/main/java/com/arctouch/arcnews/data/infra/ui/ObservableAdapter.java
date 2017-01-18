package com.arctouch.arcnews.data.infra.ui;

import rx.Observable;
import rx.Subscription;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.widget.ArrayAdapter;

import java.io.Closeable;
import java.util.Collection;

/**
 * Created by andresato on 1/17/17.
 */

public class ObservableAdapter<T> extends ArrayAdapter<T> implements Closeable {
  private Subscription subscription;

  public ObservableAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
    super(context, resource, textViewResourceId);
  }

  @Override
  public void close() {
    subscription.unsubscribe();
  }

  public void setObservable(Observable<? extends Collection<? extends T>> observable) {
    if (subscription != null) {
      subscription.unsubscribe();
    }

    subscription = observable.subscribe(newItems -> {
      this.clear();
      this.addAll(newItems);
    });
  }
}
