package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_UserDao {
  public abstract long login(final Map map);
  
  public abstract int searchId(final String username);
  
  public abstract String searchEmail(final String username);
  
  public abstract int insertNewPassword(final Map map);
  
  public abstract String searchPhoto(final String username);
  
  public abstract int addUser(final Map map);
  
  public abstract void addUserLevel();
  
  public abstract void updatePhoto(final Map map);
  
  public abstract long existIf(final String username);
}
