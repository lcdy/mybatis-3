package aa.slk.mybatis;


import aa.slk.mybatis.domain.Users;
import aa.slk.mybatis.mappers.UsersMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(SpringConfig.class, UsersServiceImpl.class);
        ac.refresh();
        UsersMapper usersMapper = ac.getBean(UsersMapper.class);
        Users users = usersMapper.findById(1);
        System.out.println(users);

        UsersService usersService = ac.getBean(UsersService.class);
        usersService.update(6);
    }
}
