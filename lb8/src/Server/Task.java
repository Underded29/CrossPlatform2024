package Server;

import java.io.Serializable;

public interface Task<T> extends Serializable {
    T execute();
}
