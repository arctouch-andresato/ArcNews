package arctouch.com.data.infra;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.schedulers.Schedulers;

@Module
public class InfraModule {

    @Provides
    public Observable.Transformer<Observable, Observable> providesSchedulingTransformer() {
        return observable -> observable.subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate());
    }
}
