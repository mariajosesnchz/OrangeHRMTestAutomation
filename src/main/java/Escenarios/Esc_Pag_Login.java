package Escenarios;

import Entidades.Ent_login;
import Recursos.Utilidades.Rec_Global;
import org.openqa.selenium.WebDriver;


public class Esc_Pag_Login {

    WebDriver driver;
    public Esc_Pag_Login(WebDriver _driver) {
        driver=_driver;
    }
    public void Login(Ent_login entLogin){
        Rec_Global rec_global = new Rec_Global(driver);
        String inp_username= " //*[@name='username']";
        String inp_password= " //*[@name='password']";
        String btn_login= " //*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button";


        rec_global.Escribir(entLogin.username, inp_username);
        rec_global.Escribir(entLogin.password, inp_password);
        rec_global.Click(btn_login);
    }
}
