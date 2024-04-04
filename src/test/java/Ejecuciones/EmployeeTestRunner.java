package Ejecuciones;

import Entidades.Ent_Employee;
import Entidades.Ent_login;
import Escenarios.Esc_Pag_Login;
import Escenarios.Esc_Pag_PIM;
import Recursos.Navegador.Pag_Navegador;
import Recursos.Utilidades.Rec_Global;
import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmployeeTestRunner {
    public static WebDriver driver;
    Ent_login ent_Login;
    Esc_Pag_Login esc_pag_Login;
    Ent_Employee ent_employee;
    Esc_Pag_PIM esc_pag_pim;
    @Before
    public void PreEjecucion(){
        Pag_Navegador pag_navegador=new Pag_Navegador(driver);
        driver=pag_navegador.AbrirNavegador("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");


    }

    @After
    public void PostEjecucion(){
        //driver.close();
    }
    public void InstanciasBasicas() throws IOException {
        ent_Login= new Ent_login();
        esc_pag_Login=new Esc_Pag_Login(driver);
        ent_employee=new Ent_Employee();
        esc_pag_pim=new Esc_Pag_PIM(driver);
        LecturaDatos();
    }

    public void LecturaDatos() throws IOException {
        Faker faker=new Faker();
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\OrangeHRMTestAutomation\\Datos.properties");
        properties.load(fis);

        ent_Login.username=properties.getProperty("username");
        ent_Login.password=properties.getProperty("password");

        ent_employee.firstname= faker.name().firstName();
        ent_employee.middlename=faker.name().firstName();
        ent_employee.lastname= faker.name().lastName();
        int empId = Rec_Global.generateNumber(10000, 99999);
        String employeeId = String.valueOf(empId);
        ent_employee.empid= employeeId;
        ent_employee.username=  "test" + Rec_Global.generateNumber(1000, 9999);;//"username123455";
        ent_employee.password="Amormusical000";
        ent_employee.confirmpassword="Amormusical000";
    }

    @Test
    public void CreateEmployeeWithOutUsername() throws IOException, InterruptedException {
        InstanciasBasicas();
        esc_pag_Login.Login(ent_Login);
        esc_pag_pim.CreateEmployeeWithOutUsername(ent_employee);
        Thread.sleep(1500);
        String header_actual =  driver.findElements(By.className("oxd-text")).get(16).getText();
        String header_expected = "Required";
        org.junit.Assert.assertTrue(header_actual.contains(header_expected));

    }
    @Test
    public void CreateEmployee1() throws IOException, InterruptedException, ParseException {
        InstanciasBasicas();
        esc_pag_Login.Login(ent_Login);
        esc_pag_pim.CreateEmployee(ent_employee);
        Thread.sleep(1500);
        String header_actual= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/h6")).getText();
        String header_expected="Personal Details";
        org.junit.Assert.assertTrue(header_actual.contains(header_expected));
        Rec_Global.addJsonList(ent_employee.firstname, ent_employee.lastname, ent_employee.empid, ent_employee.username,ent_employee.password,ent_employee.confirmpassword);
        //Thread.sleep(1500);
    }
    @Test
    public void CreateEmployee2() throws IOException, InterruptedException, ParseException {
        InstanciasBasicas();
        esc_pag_Login.Login(ent_Login);
        esc_pag_pim.CreateEmployee(ent_employee);
        Thread.sleep(1500);
        String header_actual= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/h6")).getText();
        String header_expected="Personal Details";
        org.junit.Assert.assertTrue(header_actual.contains(header_expected));
        Rec_Global.addJsonList(ent_employee.firstname, ent_employee.lastname, ent_employee.empid, ent_employee.username,ent_employee.password,ent_employee.confirmpassword);
        //Thread.sleep(1500);
    }

    @Test
    public void searchEmployee() throws IOException, InterruptedException, ParseException {
        /*InstanciasBasicas();
        esc_pag_Login.Login(ent_Login);
        esc_pag_pim.CreateEmployee(ent_employee);
        Thread.sleep(1500);
        esc_pag_pim.SearchEmployee(ent_employee);*/
        InstanciasBasicas();
        Rec_Global rec_global=new Rec_Global(driver);
        esc_pag_Login.Login(ent_Login);
        esc_pag_pim.SearchEmployee(ent_employee);
        Rec_Global.doScroll(driver);
        Thread.sleep(1500);
        String userIdToSearch=ent_employee.empid;
        String TableResult="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div";
        rec_global.Click(TableResult);
        Thread.sleep(1500);
        String searchBox="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input";
        WebElement tuElemento = driver.findElement(By.xpath(searchBox));
        String textoExtraido = tuElemento.getText();
        System.out.println("Texto extraído: " + textoExtraido);
       // Assert.assertTrue(userIdToSearch.equals(textoExtraido));
        System.out.println("userIdToSearch: " + userIdToSearch);
        System.out.println("searchBox1: " + textoExtraido);
        Assert.assertTrue("El ID del usuario no coincide", searchBox.equals(userIdToSearch));



        //String searchBox1 =rec_global.getText(By.xpath(searchBox));
       // Assert.assertTrue(userIdToSearch.equals(searchBox1));
/*
        pimPage.empList.click();
        Thread.sleep(3500);
        String actualRecordEmp1 = pimPage.txtInput.get(5).getAttribute("value");
        System.out.println(actualRecordEmp1);
        String expectedRecordEmp1 = firstEmpId;
        Assert.assertTrue(actualRecordEmp1.equals(expectedRecordEmp1));*/


    }

}
