package servlet.user;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class DataBase {
    private Map<Long,User> userMap;
    private AtomicLong currentId = new AtomicLong(0);

    public DataBase() {
        userMap = new HashMap<>();
        userMap.put(currentId.get(),new User("Stepan","Shcherbakov"));
        currentId.getAndIncrement();
    }

    public User getUserById(long id){
        return userMap.get(id);
    }

    public void putUser(User user){
        userMap.put(currentId.get(),user);
        currentId.getAndIncrement();
    }
}
