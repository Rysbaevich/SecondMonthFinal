package com.company;
import com.company.dao.daoutil.DaoContext;
import com.company.dao.StudentDao;
import com.company.model.Student;

public class Main {

    public static void main(String[] args) {

        StudentDao studentDao = (StudentDao) DaoContext.autowired("StudentDao", "singleton");
        for (Student student:studentDao.findAll()) {
            System.out.println(student);
        }
    }
}
