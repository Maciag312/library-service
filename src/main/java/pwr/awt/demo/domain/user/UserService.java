package pwr.awt.demo.domain.user;


import pwr.awt.demo.api.dto.UserDetailsDTO;

public interface UserService {
    User createUser(Command.CreateUser createUserCommand);
    String login(Query.Login login);
    User whoami();
    UserDetailsDTO getUserDetails();


    interface Command {
        interface CreateUser extends Command {
            String getEmail();
            String getPassword();
            String getName();
        }

    }
    interface  Query {
        interface Login extends Query{
            String getEmail();
            String getPassword();
        }
        interface Search extends  Query{
            String getEmail();
        }
    }
}
