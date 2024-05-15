import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EDTInvocationHandler2 implements InvocationHandler {

    private Object invocationResult = null;

    private UITasks uiTasks;

    public EDTInvocationHandler2(UITasks uiTasks) {
        this.uiTasks = uiTasks;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (SwingUtilities.isEventDispatchThread()) {
            invocationResult = method.invoke(uiTasks, args);
        } else {
            Runnable shell = () -> {
                try {
                    invocationResult = method.invoke(uiTasks, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            };
            SwingUtilities.invokeAndWait(shell);
        }
        return invocationResult;
    }
}
