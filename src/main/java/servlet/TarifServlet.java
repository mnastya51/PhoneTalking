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
                //actionAdd(resp, req.getParameter("value"));
                break;
            case "delete":
               // actionDelete(resp, req.getParameter("value"));
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
