package com.ra.model.dao;

import com.ra.model.entity.Employee;
import com.ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public int LIMIT = 3;
    public int totalPage = 0;
    @Override
    public List<Employee> findAll(int noPage) {
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PAGI_EMPLOYEE(?,?,?)}");
            callableStatement.setInt(1,LIMIT);
            callableStatement.setInt(2,noPage);
            callableStatement.setInt(3, Types.INTEGER); //tham so out
            ResultSet resultSet = callableStatement.executeQuery();
            this.totalPage = callableStatement.getInt(3);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setBirthday(resultSet.getDate("birthday"));
                employee.setSex(resultSet.getBoolean("sex"));
                employee.setSalary(resultSet.getDouble("salary"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        return null;
    }

    @Override
    public List<Employee> sortByName(String name) {
        return null;
    }

    @Override
    public Employee findById(Integer integer) {
        if (integer == null){
            return null;
        }
        Connection connection = null;
        Employee employee = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL FIND_BY_ID(?)}");
            callableStatement.setInt(1, integer);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setBirthday(resultSet.getDate("birthday"));
                employee.setSex(resultSet.getBoolean("sex"));
                employee.setSalary(resultSet.getDouble("salary"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return employee;
    }

    @Override
    public boolean saveOrUpDate(Employee employee, Integer integer) {
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            if (findById(integer) == null) {
                CallableStatement callableStatement = connection.prepareCall("{CALL ADD_EMPLOYEE(?,?,?,?,?,?)}");
                callableStatement.setString(1, employee.getName());
                callableStatement.setString(2, employee.getPhone());
                callableStatement.setString(3, employee.getAddress());
                callableStatement.setDate(4, employee.getBirthday());
                callableStatement.setBoolean(5, employee.getSex());
                callableStatement.setDouble(6, employee.getSalary());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL EDIT_EMPLOYEE(?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, integer);
                callableStatement.setString(2, employee.getName());
                callableStatement.setString(3, employee.getPhone());
                callableStatement.setString(4, employee.getAddress());
                callableStatement.setDate(5, employee.getBirthday());
                callableStatement.setBoolean(6, employee.getSex());
                callableStatement.setDouble(7, employee.getSalary());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL DELETE_EMPLOYEE(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
