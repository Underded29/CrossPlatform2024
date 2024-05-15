package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceTaskImpl extends UnicastRemoteObject implements ServiceTask {
    protected ServiceTaskImpl() throws RemoteException {
    }

    @Override
    public <T> T executeTask(UserRecord t) throws RemoteException {
        ConferenceServer.getInstance().registerUser((UserRecord) t);
        return null;
    }
}
