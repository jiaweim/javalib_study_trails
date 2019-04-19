package tutorial.lib.rxjava;

import io.reactivex.Observable;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao on 2017.09.21
 * @since 1.0.0
 */
public class CreateObservables
{

    @Test
    public void fromData()
    {
        Observable<String> o = Observable.fromArray("a", "b", "c");
    }

}
