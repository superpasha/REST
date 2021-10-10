package builders;

import request.entities.user.Data;
import request.entities.user.User;

public class UserBuilder {
    User user = new User();
    Data data = new Data();

    public UserBuilder withId(int id){
        data.setId(id);
        return this;
    }

    public UserBuilder withName(String name){
        data.setName(name);
        return this;
    }

    //Add setters for other user fields

    public User build(){
        return user;
    }



}
