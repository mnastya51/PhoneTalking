package servlet;
import com.google.gson.Gson;
import dao.DAOAbonent;
import entities.Abonent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AbonentServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() == null) {
            req.getRequestDispatcher("abonent.jsp").forward(req, resp);
            return;
        }
        switch (req.getParameter("action")) {
            case "get":
                actionGet(resp);
                break;
            case "add":
                actionAdd(resp, req.getParameter("fio"), req.getParameter("phone"), req.getParameter("address"), Boolean.valueOf(req.getParameter("facility")));
                break;
            case "delete":
                actionDelete(resp, req.getParameter("value"));
                break;
            case "update":
                actionUpdate(resp, req.getParameter("fio"), req.getParameter("phone"), req.getParameter("address"), Boolean.valueOf(req.getParameter("facility")), req.getParameter("id"));
                break;
        }
    }

    private void actionGet(HttpServletResponse resp) {
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

    private void actionAdd(HttpServletResponse resp, String fio, String phone, String address, boolean facility) {
        DAOAbonent daoAbonent = new DAOAbonent();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoAbonent.insert(new Abonent(phone, fio, address, facility));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionDelete(HttpServletResponse resp, String value) {
        DAOAbonent daoAbonent = new DAOAbonent();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoAbonent.delete(new Abonent(Integer.valueOf(value)));
                resp.getWriter().write(gson.toJson(0));
            } catch (SQLException e) {
                resp.getWriter().write(gson.toJson(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionUpdate(HttpServletResponse resp, String fio, String phone, String address, boolean facility, String id) {
        DAOAbonent daoAbonent = new DAOAbonent();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try {
                daoAbonent.update(new Abonent(Integer.valueOf(id), phone, fio, address, facility));
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