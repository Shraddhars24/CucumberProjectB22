package utils;


import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;

public class PageInitializer {

    public static LoginPage loginPage;
  public static AddEmployeePage addEmployeePage;
  public static DashboardPage dashboardPage;


    public static void initializerPageObjects(){
        loginPage=new LoginPage();
        addEmployeePage=new AddEmployeePage();
        dashboardPage=new DashboardPage();
    }
}
