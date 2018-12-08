package Service.Impl;

import DAO.Impl.UserDaoImpl;
import DAO.Impl.letterDaoImpl;
import DAO.UserDao;
import DAO.letterDao;
import Model.User;
import Model.letter;
import Service.UserService;
import Service.letterService;
import Util.UUIDUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */
public class letterServiceImpl implements letterService {
    private letterDao dao=new letterDaoImpl();
    @Override
    public boolean sendLetter(String from_id, String to_id, String message) {
        List<String> defys=getDefyByToId(to_id);
        for (String s:defys){
            if(s.equals(from_id)){
                return false;
            }
        }
        letter l=new letter();
        l.setLetter_id(UUIDUtils.getId());
        l.setFrom_id(from_id);
        l.setTo_id(to_id);
        l.setMessage(message);
        l.setSend_date(LocalDate.now());
        return dao.insert(l);
    }

    @Override
    public void defyUser(String id, String defyId) {
        UserDao udao=new UserDaoImpl();
        udao.insert(UUIDUtils.getId(),id,defyId);
    }

    @Override
    public List<String> getDefyByToId(String to_id) {
        UserDao udao=new UserDaoImpl();
        return udao.selectDefy(to_id);
    }

    @Override
    public List<letter> getLetter(String id) {
        List<letter> res=dao.selectByToId(id);
        List<String> defy=getDefyByToId(id);
        for (letter l:res){
            if(defy.contains(l.getFrom_id())){
                defy.remove(l);
            }
        }
        return res;
    }

    @Override
    public List<letter> selectByToId(String to_id) {
        return dao.selectByToId(to_id);
    }

    @Override
    public String selectCountById(String id) {
        UserDao udao=new UserDaoImpl();
        return udao.selectCountById(id);
    }

    @Override
    public List<letter> selectByFromId(String fromId) {
        return dao.selectByFromId(fromId);
    }

    @Override
    public boolean deleteLetter(String letter_id) {
        return dao.deleteById(letter_id);
    }
}
