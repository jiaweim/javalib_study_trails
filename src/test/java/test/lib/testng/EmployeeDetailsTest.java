package test.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao on 2017.07.17
 * @since 1.0-SNAPSHOT
 */
public class EmployeeDetailsTest
{
    EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
    EmployeeDetails employee = new EmployeeDetails();

    @Test
    public void testCalculateAppriasal()
    {
        employee.setName("Rajeev");
        employee.setAge(25);
        employee.setMonthlySalary(8000);

        double appraisal = empBusinessLogic.calculateAppraisal(employee);
        assertEquals(500, appraisal, 0.0, "500");
    }

    // Test to check yearly salary
    @Test
    public void testCalculateYearlySalary()
    {
        employee.setName("Rajeev");
        employee.setAge(25);
        employee.setMonthlySalary(8000);

        double salary = empBusinessLogic.calculateYearlySalary(employee);
        assertEquals(96000, salary, 0.0, "8000");
    }
}