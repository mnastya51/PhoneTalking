package servlet;

import com.google.gson.Gson;
import dao.DAOCity;
import entities.City;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CityServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getQueryString() == null){
            req.getRequestDispatcher("city.jsp").forward(req, resp);
            return;
        }
        switch (req.getParameter("action")) {
            case "get":
                actionGet(resp);
                break;
            case "add":
                actionAdd(resp, req.getParameter("value"));
                break;
        }

    }

    private void actionGet(HttpServletResponse resp) {
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

    private void actionAdd(HttpServletResponse resp, String value) {
        DAOCity daoCity = new DAOCity();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoCity.insert(new City(value));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
//                resp.getWriter().write("{\"error\"=\"" + e.getMessage() + "\"");
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
