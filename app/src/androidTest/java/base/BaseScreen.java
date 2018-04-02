package base;

import android.support.test.espresso.Espresso;

public abstract class BaseScreen<T extends BaseScreen<T>> {

    protected T self;

    public BaseScreen(Class<T> type) {
        self = type.cast(this);
    }

    public T pressBack() {
        Espresso.pressBack();
        return self;
    }
}
