package com.solid.s.good;

public class EmployeePaymentService {

    private EmployeeAdditionalPaymentService eAPS;

    public EmployeePaymentService(EmployeeAdditionalPaymentService employeeAdditionalPaymentService) {
        this.eAPS = employeeAdditionalPaymentService;
    }

    public int calculatePay(GoodEmployee employee) {
        switch (employee.status) {
            case "A":
                return 1;
            case "B":
                return 2;
            default:
                return 0;
        }
    }
}
