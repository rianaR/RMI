package server;

import error_codes.ErrorCode;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by riana-r on 12/12/14.
 */
public interface DataInterface extends Remote {
    public ErrorCode add(String name, String surname) throws RemoteException;

    public Map<String, List<String>> list() throws RemoteException;
}
