package mapper;

import data.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xyq on 17/3/31.
 */
public interface UserMapper {

    public int insertUser(User user);

    public int updateUser(User user);

    public User getUser(@Param("userId") String userId);

    public int deleteUser(@Param("userId") String userId);
}
