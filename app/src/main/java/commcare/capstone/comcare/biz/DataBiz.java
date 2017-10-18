package commcare.capstone.comcare.biz;

import android.content.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.model.LastLogin;
import commcare.capstone.comcare.model.User;
import commcare.capstone.comcare.sqlhelper.DatabaseHelper;
import commcare.capstone.comcare.util.AeSimpleSHA1;
import commcare.capstone.comcare.view.LoginActivity;
import commcare.capstone.comcare.webservice.ComCareService;
import commcare.capstone.comcare.webservice.DataRequest;
import commcare.capstone.comcare.webservice.DataResponse;
import commcare.capstone.comcare.webservice.LoginRequest;
import commcare.capstone.comcare.webservice.LoginResponse;
import commcare.capstone.comcare.webservice.VectorHouseVisit;

public class DataBiz {
    Logger LOG = LoggerFactory.getLogger(DataBiz.class);

    private static DataBiz dataBiz = new DataBiz();
    private transient DatabaseHelper helper = null;
    transient ComCareService ws = null;
    private HouseVisit selectedHV = null;
    String url = "http://13.229.131.55/ComCare/services/ComCareService.ComCareServiceHttpsSoap12Endpoint/";
    String localurl = "http://192.168.1.117:8080/ComCare/services/ComCareService.ComCareServiceHttpsSoap12Endpoint/";

    public static DataBiz getInstance() {
        return dataBiz;
    }

    public static DataBiz getInstance(DataBiz ndataBiz) {
        dataBiz = ndataBiz;
        return dataBiz;
    }

    private DataBiz() {

    }

    public DatabaseHelper getDb(Context c) {
        if (helper == null) {
            helper = new DatabaseHelper(c);
        }
        return helper;
    }

    public DatabaseHelper getDb() {
        return helper;
    }

    public ComCareService getWs() {
        ws = new ComCareService();
        if (EnviromentBiz.getInstance().isTest())
        {
            ws.setUrl(localurl);
        }
        else
        {
            ws.setUrl(url);
        }

        return ws;
    }

    public User login(String user, String pw) throws Exception{
       if (ws == null)
       {
           ws = getWs();
       }
        LoginRequest req = new LoginRequest();
        req.login = user;
        req.password = AeSimpleSHA1.SHA1(pw);
        //req.requestDate = new Date().toString();
        req.interfaceVersion = "1";
        LoginResponse rep =  ws.login(req);
        if (rep != null && rep.token != 0l)
        {
            User u = new User();
            u.setLogin(user);
            u.setPassword(pw);
            u.setRole(User.ROLE_USER);
            u.setToken(rep.token);
            return u;
        }
        return null;
    }

    public LastLogin getLoggedIn() {
        List<LastLogin> lastlogin = DataBiz.getInstance().getDb().getLastLoginRuntimeDAO().queryForAll();
        if (lastlogin.size()>0)
        {
            if (lastlogin.get(0).isLoggedIn())
            {
                return lastlogin.get(0);
            }
        }
        return null;
    }

    public void logout() {
        LOG.info("User Logged out.");
        List<LastLogin> lastlogin = DataBiz.getInstance().getDb().getLastLoginRuntimeDAO().queryForAll();
        if (lastlogin.size()>0)
        {
            lastlogin.get(0).setLoggedIn(false);
        }
        DataBiz.getInstance().getDb().getLastLoginRuntimeDAO().createOrUpdate(lastlogin.get(0));
    }

    public void setSelectedHV(HouseVisit houseVisit) {
        selectedHV = houseVisit;
    }

    public HouseVisit getSelectedHV() {
        return selectedHV;
    }

    public ArrayList<HouseVisit> getHVList() {
        ArrayList<HouseVisit> vis = (ArrayList<HouseVisit>)helper.getHouseVisitRuntimeDAO().queryForAll();
        for (HouseVisit vi : vis)
        {
            getDb().getHouseVisitRuntimeDAO().refresh(vi);
        }
        return vis;

    }

    public void getHouseVisits() throws Exception{
        LOG.debug("getHouseVisits");
        if (ws == null)
        {
            ws = getWs();
        }
        DataRequest req = new DataRequest();
        LastLogin ll = getLoggedIn();
        req.login = ll.getName();
        req.token = ll.getToken();
        req.interfaceVersion = "1";
        DataResponse rep =  ws.getVisits(req);
        LOG.debug("gotHouseVisits");
        if (rep != null)
        {
            LOG.debug("rep != null");
            helper.getResidentRuntimeDAO().delete(helper.getResidentRuntimeDAO().queryForAll());
            helper.getGenogramObjRuntimeDAO().delete(helper.getGenogramObjRuntimeDAO().queryForAll());
            helper.getAssistancesRuntimeDAO().delete(helper.getAssistancesRuntimeDAO().queryForAll());
            helper.getIssuesRuntimeDAO().delete(helper.getIssuesRuntimeDAO().queryForAll());
            helper.getDataCollectionFormRuntimeDAO().delete(helper.getDataCollectionFormRuntimeDAO().queryForAll());
            helper.getHouseVisitRuntimeDAO().delete(helper.getHouseVisitRuntimeDAO().queryForAll());
            helper.getUserRuntimeDAO().delete(helper.getUserRuntimeDAO().queryForAll());

            VectorHouseVisit vis = rep.visits;
            if (vis != null) {
                for (commcare.capstone.comcare.webservice.HouseVisit vi : vis) {
                    HouseVisit hv = new HouseVisit();
                    hv.importhv(vi);
                    helper.getHouseVisitRuntimeDAO().createOrUpdate(hv);
                    helper.getResidentRuntimeDAO().createOrUpdate(hv.getResident());
                    helper.getUserRuntimeDAO().createOrUpdate(hv.getAssignedTo());
                    helper.getUserRuntimeDAO().createOrUpdate(hv.getCreatedBy());
                }
            }
        }
        LOG.debug("rep Done");
    }
}
