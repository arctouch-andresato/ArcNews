package arctouch.com.data.infra;

import rx.Observable;
import rx.Observable.Transformer;
import rx.schedulers.Schedulers;

//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ObservableUtil {

    private final Transformer<Observable, Observable> schedulersTransformer = observable -> observable.subscribeOn(Schedulers.immediate())
            .observeOn(Schedulers.immediate());

    @SuppressWarnings("unchecked")
    public <T> Transformer<T, T> defaultSchedulers() {
        return (Transformer<T, T>) schedulersTransformer;
    }

}
