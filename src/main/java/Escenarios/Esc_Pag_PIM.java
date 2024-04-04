package Escenarios;
import Entidades.Ent_Employee;
import Recursos.Utilidades.Rec_Global;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class Esc_Pag_PIM {

    WebDriver driver;
    public Esc_Pag_PIM(WebDriver _driver) {
        driver=_driver;
    }
    public void CreateEmployeeWithOutUsername(Ent_Employee entEmployee)  {
        Rec_Global rec_global = new Rec_Global(driver);
        String op_PIM= "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span";
        String btn_add= "(//*[@class='orangehrm-paper-container']//*[@Type='button'])[1]";//"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button";
        String inp_firstname= " //*[@name='firstName']";
        String inp_middlename= " //*[@name='middleName']";
        String inp_lastname= " //*[@name='lastName']";
        String inp_empid="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input";
        String btn_save="//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]";
        String btn_toggle= "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span";
        String inp_password= "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input";
        String inp_confirmpassword="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input";//"(//input[@class=\"oxd-input oxd-input--active\" and @type=\"password\"])[2]";
        rec_global.Click(op_PIM);
        rec_global.Click(btn_add);
        rec_global.Escribir(entEmployee.firstname, inp_firstname);
        rec_global.Escribir(entEmployee.middlename, inp_middlename);
        rec_global.Escribir(entEmployee.lastname, inp_lastname);
        rec_global.clearFieldByXPath(inp_empid);
        rec_global.Escribir(entEmployee.empid, inp_empid);
        rec_global.Click(btn_toggle);
        rec_global.Escribir(entEmployee.password, inp_password);
        rec_global.Escribir(entEmployee.confirmpassword,inp_confirmpassword);
        rec_global.Click(btn_save);
    }
    public void CreateEmployee(Ent_Employee entEmployee) {
        Rec_Global rec_global = new Rec_Global(driver);
        String op_PIM = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span";
        String btn_add = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button";
        String inp_firstname = " //*[@name='firstName']";
        String inp_middlename = " //*[@name='middleName']";
        String inp_lastname = " //*[@name='lastName']";
        String inp_empid = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input";
        String btn_save = "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]";
        String btn_toggle = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span";
        String inp_username = "(//input[@class=\"oxd-input oxd-input--active\"])[3]";
        String inp_password = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input";
        String inp_confirmpassword = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input";//"(//input[@class=\"oxd-input oxd-input--active\" and @type=\"password\"])[2]";
        rec_global.Click(op_PIM);
        rec_global.Click(btn_add);
        rec_global.Escribir(entEmployee.firstname, inp_firstname);
        rec_global.Escribir(entEmployee.middlename, inp_middlename);
        rec_global.Escribir(entEmployee.lastname, inp_lastname);
        rec_global.clearFieldByXPath(inp_empid);
        rec_global.Escribir(entEmployee.empid, inp_empid);
        rec_global.Click(btn_toggle);
        rec_global.Escribir(entEmployee.username, inp_username);
        rec_global.Escribir(entEmployee.password, inp_password);
        rec_global.Escribir(entEmployee.confirmpassword, inp_confirmpassword);
        rec_global.Click(btn_save);
    }
    public void SearchEmployee(Ent_Employee entEmployee) throws InterruptedException, IOException, ParseException {
        String randomId="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input";
        String btn_search="//*[@Type='submit']";
        String op_PIM = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span";
        Rec_Global rec_global=new Rec_Global(driver);
        clickOnPIM();
        String fileName = "C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\OrangeHRMTestAutomation\\src\\main\\java\\Recursos\\Data\\Datos.json";
        JSONArray jsonArray = (JSONArray) Rec_Global.readJSONArray(fileName);
        int indexOfFirstEmp = jsonArray.size() - 2;

        JSONObject firstEmp;//new  JSONObject();
        firstEmp = (JSONObject) jsonArray.get(indexOfFirstEmp);
        String firstEmpId = (String) firstEmp.get("empIdStr");
        Thread.sleep(2500);
        rec_global.Escribir(firstEmpId,randomId);
        rec_global.Click(btn_search);
    }
    public void UpdateEmployee(Ent_Employee ent_employee){


    }
    public void clickOnPIM(){
        Rec_Global rec_global=new Rec_Global(driver);
        String op_PIM = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span";
        rec_global.Click(op_PIM);
    }



}
