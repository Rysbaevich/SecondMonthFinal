package com.company.dao.daoutil;

import com.company.dao.CrudDao;
import com.company.dao.impl.StudentDaoImpl;

public abstract class DaoContext {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static StudentDaoImpl studentDaoImpl;

    public static CrudDao<?> autowired(String qualifier, String scope) {
        if (!scope.equals("prototype") && !scope.equals("singleton")) {
            throw new RuntimeException("Incorrect scope " + scope);
        }

        switch (qualifier) {
            case "StudentDao":
                return getStudentDaoImpl(scope);
            default:
                throw new RuntimeException("Incorrect qualifier " + qualifier);
        }
    }

    private static StudentDaoImpl getStudentDaoImpl(String scope) {
        if (scope.equals("prototype")) {
            return new StudentDaoImpl();
        }
        if (studentDaoImpl == null) {
            studentDaoImpl = new StudentDaoImpl();
        }
        return studentDaoImpl;
    }
}
