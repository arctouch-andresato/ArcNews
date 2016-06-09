package com.arctouch.arcnews.data.infra;

import javax.inject.Inject;

import lombok.RequiredArgsConstructor;
import rx.Observable;
import rx.Observable.Transformer;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ObservableUtil {

    private final Transformer<Observable, Observable> schedulersTransformer;
//            = observable -> observable.subscribeOn(Schedulers.immediate())
//            .observeOn(Schedulers.immediate());

    @SuppressWarnings("unchecked")
    public <T> Transformer<T, T> defaultSchedulers() {
        return (Transformer<T, T>) schedulersTransformer;
    }

}
