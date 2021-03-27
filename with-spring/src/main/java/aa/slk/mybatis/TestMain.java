package aa.slk.mybatis;

import aa.slk.mybatis.domain.Users;
import aa.slk.mybatis.mappers.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames = "testMain")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = SpringConfig.class)
public class TestMain {
    @Autowired
    UsersMapper usersMapper;

    @Test
    @Transactional
    public void testCache() {
        Thread thread = Thread.currentThread();
        System.out.println(findById(1));
        System.out.println(findById(1));
    }

    public Users findById(Integer id) {
        return usersMapper.findById(id);
    }

}
