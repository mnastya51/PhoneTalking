package servlet;

import com.google.gson.Gson;
import dao.DAOAbonent;
import dao.DAOCity;
import dao.DAOTalking;
import entities.Abonent;
import entities.City;
import entities.Talking;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TalkingServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getQueryString() == null){
            req.getRequestDispatcher("talking.jsp").forward(req, resp);
            return;
        }
        switch (req.getParameter("action")) {
            case "get":
                actionGet(resp);
                break;
            case "getCity":
                actionGetCity(resp);
                break;
            case "getPhone":
                actionGetPhone(resp);
                break;
            case "add":
                actionAdd(resp, req.getParameter("phone"), req.getParameter("city"), req.getParameter("min"),
                        req.getParameter("date"), req.getParameter("time"), req.getParameter("cost"));
                break;
            case "delete":
                actionDelete(resp, req.getParameter("value"));
                break;
            case "update":
                actionUpdate(resp, req.getParameter("phone"), req.getParameter("city"), req.getParameter("min"),
                        req.getParameter("date"), req.getParameter("time"), req.getParameter("cost"), req.getParameter("id"));
                break;
        }
    }

    private void actionGet(HttpServletResponse resp) {
        DAOTalking daoTalking = new DAOTalking();
        try {
            List<Talking> talking = daoTalking.select();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(talking));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionGetCity(HttpServletResponse resp) {
        DAOCity daoCity = new DAOCity();
        try {
            List<City> cities = daoCity.select();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(cities));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionGetPhone(HttpServletResponse resp) {
        DAOAbonent daoAbonent = new DAOAbonent();
        try {
            List<Abonent> abonents = daoAbonent.select();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(abonents));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionAdd(HttpServletResponse resp, String phone, String city, String min, String date, String time, String cost) {
        DAOTalking daoTalking = new DAOTalking();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoTalking.insert(new Talking(phone, city, Integer.valueOf(min), date, time, Double.valueOf(cost)));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionUpdate(HttpServletResponse resp, String phone, String city, String min, String date, String time, String cost, String id) {
        DAOTalking daoTalking = new DAOTalking();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoTalking.update(new Talking(Integer.valueOf(id), phone, city, Integer.valueOf(min), date, time, Double.valueOf(cost)));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionDelete(HttpServletResponse resp, String value) {
        DAOTalking daoTalking = new DAOTalking();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoTalking.delete(new Talking(Integer.valueOf(value)));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
