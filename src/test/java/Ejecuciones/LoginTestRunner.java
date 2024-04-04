package Ejecuciones;
import Entidades.Ent_login;
import Escenarios.Esc_Pag_Login;
import Recursos.Navegador.Pag_Navegador;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class LoginTestRunner {
    public static WebDriver driver;
    Ent_login ent_login;
    Esc_Pag_Login esc_pag_Login;
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
        ent_login=new Ent_login();
        esc_pag_Login=new Esc_Pag_Login(driver);
        LecturaDatos();
    }

    public void LecturaDatos() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\OrangeHRMTestAutomation\\Datos.properties");
        properties.load(fis);

        ent_login.username= properties.getProperty("username");
        ent_login.password=properties.getProperty("password");

    }

    @Test
    public void RealizarLogin() throws IOException {
        InstanciasBasicas();
        esc_pag_Login.Login(ent_login);

    }



}
