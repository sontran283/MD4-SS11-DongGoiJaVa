package com.ra.controller;

import com.ra.model.entity.Employee;
import com.ra.model.service.EmployeeService;
import com.ra.model.service.EmployeeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmployeeController", value = "/Employee")
public class EmployeeController extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showListEmployee(request, response);
        } else {
            switch (action) {
                case "add":
                    response.sendRedirect("views/employee_add.jsp");
                    break;
                case "edit":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Employee employee = employeeService.findById(idEdit);
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("views/employee_edit.jsp").forward(request, response);
                    break;
                case "delete":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    employeeService.delete(idDelete);
                    showListEmployee(request, response);
                    break;
                default:
                    showListEmployee(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Employee employee = new Employee();
            employee.setName(request.getParameter("name"));
            employee.setPhone(request.getParameter("phone"));
            employee.setAddress(request.getParameter("address"));
            employee.setBirthday(Date.valueOf(request.getParameter("birthday")));
            employee.setSex(Boolean.parseBoolean(request.getParameter("sex")));
            employee.setSalary(Double.parseDouble(request.getParameter("salary")));
            if (employeeService.saveOrUpDate(employee, null)) {
                showListEmployee(request, response);
            } else {
                request.getParameter("views/employee_add.jsp");
            }
        }
        if (action.equals("edit")){
            editEmployeePost(request, response);
        }
    }

    private void editEmployeePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEdit = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean sex = Boolean.valueOf(request.getParameter("sex"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        Employee employee = new Employee(idEdit, name, phone, address, birthday, sex, salary);
        if (employeeService.saveOrUpDate(employee, idEdit)) {
            showListEmployee(request, response);
        } else {
            request.getParameter("views/employee_edit.jsp");
        }
    }

    public void showListEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAll();
        request.setAttribute("list_employee", employees);
        request.getRequestDispatcher("views/employee_list.jsp").forward(request, response);
    }
}