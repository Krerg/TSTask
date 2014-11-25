package com.tsystems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsystems.daos.UserDAO;
import com.tsystems.model.User;
import com.tsystems.Util.HibernateUtil;

/**
 * Created by User on 21.10.2014.
 */
@Service
public class LoginService {

    @Autowired
    private UserDAO uDAO;

    private static LoginService ls;

    public LoginService()
    {

    }

    public static LoginService getService()
    {
        if(ls==null)
            ls = new LoginService();
        return ls;
    }

    public String validateLogin(String name, String password)
    {
        User tempUser = uDAO.getUser(name);
        if(tempUser==null)
        {
            return null;
        }
        if(!tempUser.getPassword().equals(password))
        {
            return null;
        }
        return tempUser.getType();
    }

}
