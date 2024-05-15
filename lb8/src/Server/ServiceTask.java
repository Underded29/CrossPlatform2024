package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceTask extends Remote {


    <T> T executeTask(UserRecord t) throws RemoteException;
}
