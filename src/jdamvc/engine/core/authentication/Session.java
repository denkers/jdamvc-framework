//====================================
//	Kyle Russell
//	jdamvc
//	Session
//====================================

package jdamvc.engine.core.authentication;

import jdamvc.engine.model.UserModel;

//---------------------------------------
//             SESSION
//---------------------------------------
//- Session holds the auth User
//- Agent stores an active session of logged in users
//- Logged in users are accessible from session by getUser()

public class Session
{
    //The sessions auth user
    //User is logged in
    private final UserModel authUser;
    
    //Create a session for user authUser
    public Session(UserModel authUser)
    {
        this.authUser    =   authUser;
    }
    
    //Returns the sessions auth user
    public UserModel getUser()
    {
        return authUser;
    }
}
