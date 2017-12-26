import dao.UserDao;
import entity.UserEntity;

public class Main {

    public static void main(String[] args) {
        // write your code here
        UserEntity entity = new UserEntity();
        entity.setEmail("johnnysviva@outlook.com");
        entity.setId(2);
        entity = new UserDao().queryById(entity);
        System.out.println(entity.getEmail()+" "+entity.getPassword()+" "+entity.getId()+" ");
    }
}
