package servlet;

import com.google.gson.Gson;
import dao.DAOCity;
import dao.DAOTarif;
import entities.City;
import entities.Tarif;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TarifServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getQueryString() == null){
            req.getRequestDispatcher("tarif.jsp").forward(req, resp);
            return;
        }
        switch (req.getParameter("action")) {
            case "get":
                actionGet(resp);
                break;
            case "getCity":
                actionGetCity(resp);
                break;
            case "add":
                actionAdd(resp, req.getParameter("city"), req.getParameter("startPeriod"), req.getParameter("finishPeriod"), req.getParameter("minCost"));
                break;
            case "delete":
                actionDelete(resp, req.getParameter("nameCity"), req.getParameter("startPeriod"), req.getParameter("finishPeriod"));
                break;
            case "sort":
                actionSort(resp,req.getParameter("value"), req.getParameter("field"));
                break;
            case "filter":
                actionFilter(resp, req.getParameter("city"), req.getParameter("startPeriod"), req.getParameter("finishPeriod"), req.getParameter("minCost"));
                break;
        }
    }

    private void actionGet(HttpServletResponse resp) {
        DAOTarif daoTarif = new DAOTarif();
        try {
            List<Tarif> tarifs = daoTarif.select();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(tarifs));
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
            /*List<City> names = new ArrayList<>();
            for(int i=0; i<cities.size(); i++){
                names.add(new City (cities.get(i).getNameCity()));
            }*/
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(cities));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionAdd(HttpServletResponse resp, String city, String startPeriod, String finishPeriod, String minCost) {
        DAOTarif daoTarif = new DAOTarif();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoTarif.insert(new Tarif(startPeriod, finishPeriod, Double.valueOf(minCost), city));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionDelete(HttpServletResponse resp, String nameCity, String startPeriod, String finishPeriod) {
        DAOTarif daoTarif = new DAOTarif();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                startPeriod = startPeriod + ":00";
                finishPeriod = finishPeriod + ":00";
                daoTarif.delete(new Tarif(nameCity, startPeriod, finishPeriod));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionSort(HttpServletResponse resp, String value, String field) {
        DAOTarif daoTarif = new DAOTarif();
        try {
            List<Tarif> tarifs = daoTarif.sort(value, field);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(tarifs));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionFilter(HttpServletResponse resp, String city, String startPeriod, String finishPeriod, String minCost) {
        DAOTarif daoTarif = new DAOTarif();
        try {
            if(minCost.isEmpty())
                minCost = "-1";
            List<Tarif> tarifs = daoTarif.filter(new Tarif(startPeriod, finishPeriod, Double.valueOf(minCost), city));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(tarifs));
        } catch (SQLException e) {
            e.printStackTrace();
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
