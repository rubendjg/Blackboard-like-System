package Users;

import Storage.IdControl;

public class Administrator extends User{
    static private int adminCount;

    public Administrator(String name, String email, String password){
        super(name, email, password, adminCount);
        adminCount += 3;
    }

    public static void initializeAdminCount(){
        adminCount = IdControl.getLastAdminId();
    }
}