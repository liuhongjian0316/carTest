package work.aijiu.service;

import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.Log;
import work.aijiu.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> login(String account, String pwd);

    LayuiPageResult userPage(Integer page, Integer limit);

    boolean insertUser(User user);

    boolean checkAccount(String userAccount);

    boolean checkAccount2(String userAccount,String olduserAccount);

    boolean checkName(String userName);
    boolean checkTel(String tel);

    List<User> selById(String userinfoid);

    boolean update(User user);

    boolean delete(String userinfoid);

    LayuiPageResult search(String userAccount, String tel, String departName, Integer page, Integer limit);

    boolean addPermission(String roleid, String userinfoid);

    int countUser();

    int countOrdUser();

    int countAdmin();

    List<Map<String,Object>> selectRoleDate();

    List<Map<String,Object>> selectSexDate();

    List<Map<String,Object>> selectDeaprtmentDate();

    List<User> selAll();

    void savelog(String username,String pwd);

    List<Log> getloginLog();
}
