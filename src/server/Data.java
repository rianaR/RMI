package server;

import error_codes.ErrorCode;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by riana-r on 12/12/14.
 */
public class Data implements DataInterface {
    private Map<String,ArrayList<String>> associations;

    public Data() {
        associations = new HashMap<String, ArrayList<String>>();
    }

    public Data(Map<String, ArrayList<String>> associations) {
        this.associations=associations;
    }

    @Override
    public ErrorCode add(String name, String surname) throws RemoteException {
        if ((name != "") && (surname != "")) {
            for (ArrayList<String> list : associations.values()) {
                for (String s : list) {
                    if (s.equals(surname)) {
                        return ErrorCode.SURNAME_EXISTS;
                    }
                }
            }
            if (associations.containsKey(name)) {
                associations.get(name).add(surname);
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(surname);
                associations.put(name, newList);
            }
            return ErrorCode.NONE;
        }
        return ErrorCode.INVALID_ARGUMENT;
    }

    @Override
    public Map<String, List<String>> list() throws RemoteException {
        Map<String,List<String>> res=new HashMap<String, List<String>>();
        String name;
        Set keys = associations.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            name=(String)it.next();
            res.put(name,associations.get(name));
        }
        return res;
    }
}
