package Recursos.Utilidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.Select;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Rec_Global {
    WebDriver driver;
    public Rec_Global(WebDriver _driver){
        driver=_driver;
    }
    public void Click(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }


    public void Escribir(String Texto, String xpath){
        driver.findElement(By.xpath(xpath)).sendKeys(Texto);
    }
    public void ListaValor(String valor, String xpath){
        Select lista = new Select(driver.findElement(By.xpath(xpath)));
        lista.selectByValue(valor);
    }
    public String getText(By locator){
        return driver.findElement(locator).getText();
    }
    public String getText2(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }
    public void clearFieldByXPath(String xpath) {
        WebElement inputField = driver.findElement(By.xpath(xpath));
        inputField.sendKeys(Keys.CONTROL + "a");
        inputField.sendKeys(Keys.DELETE);
    }
    public static int generateNumber(int min,int max){
        return (int) Math.round(Math.random()*(max-min)+min) ;
    }

    public static JSONObject loadJSONFile(String fileLocation, int index) throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileLocation));
        JSONArray jsonArray = (JSONArray) obj;
        return (JSONObject) jsonArray.get(index);
//        JSONObject jsonObject= (JSONObject) obj;
//        return jsonObject;
    }
    public static void addJsonList(String firstName, String lastName,String empIdStr, String username,String password,String confirmPassword) throws IOException, ParseException {
        String fileName="C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\OrangeHRMTestAutomation\\src\\main\\java\\Recursos\\Data\\Datos.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONObject userObj = new JSONObject();
        JSONArray jsonArray = (JSONArray) obj;

        userObj.put("firstname",firstName);
        userObj.put("lastname",lastName);
        userObj.put("empIdStr",empIdStr);
        userObj.put("username",username);
        userObj.put("password",password);
        userObj.put("password",confirmPassword);

        jsonArray.add(userObj);

        FileWriter file=new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }
    public static void updateEmp(String fileName, String key, String value, int index) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) object;
        JSONObject jsonObject = (JSONObject) jsonArray.get(index);
        jsonObject.put(key, value);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }
    public static List readJSONArray(String filename) throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        Object object= parser.parse(new FileReader(filename));
        JSONArray jsonArray= (JSONArray) object;
        return jsonArray;
    }

    public static void doScroll(WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

}
