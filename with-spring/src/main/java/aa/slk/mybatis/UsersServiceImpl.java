package aa.slk.mybatis;

import aa.slk.mybatis.domain.Users;
import aa.slk.mybatis.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UsersServiceImpl implements UsersService {

    @Autowired
    @SuppressWarnings("all")
    UsersMapper usersMapper;

    @Transactional
    public void testCache() {
        Thread thread = Thread.currentThread();
        System.out.println(findById(1));
        System.out.println(findById(1));
    }

    public Users findById(Integer id) {
        return usersMapper.findById(id);
    }

    @Transactional
    @Override
    public void update(Integer id) {
        Users users1 = new Users(id, "xx1", 0);
        Users users2 = new Users(id + 1, "xx2", 0);
        usersMapper.updateUsersIfNecessary(users1);

        usersMapper.updateUsersIfNecessary(users2);
    }
}
