package automatizacion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC002ValidacionDeLogin {
//Script para validar el Login Page
    WebDriver driver;


//Acciones que se ejecutaran antes de cada test
    @BeforeEach 
        public void  inicializarNavegador(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//Test 1: validar el titulo de la pagina antes de realizar el login
@Test 
public void validarTituloDeLaPagina(){
    //Abrir nuestro navegador
    driver.get("https://practicetestautomation.com/practice-test-login/");

    //Obtener titulo del sitio web
    String tituloActual = driver.getTitle();
    String tituloEsperado = "Test Login | Practice Test Automation";

    //Validar el titulo del sitio web
    Assertions.assertEquals(
        tituloEsperado, 
        tituloActual, 
        "El titulo encontrado no coincide con lo esperado");

        System.out.println("El titulo de la pagina fue validado correctamente");
}

//Test 2: Validar un login exitoso
    @Test 
    public void loginExitoso(){
         //Abrir nuestro navegador
    driver.get("https://practicetestautomation.com/practice-test-login/");

        //ingresar las credenciales de autenticación
        driver.findElement(By.id("username"))
                .sendKeys("Student");

        driver.findElement(By.id("password"))
                .sendKeys("Password123");

        driver.findElement(By.id("submit"))
                .click();
        
    }

//Acciones que se van a ejecutar despues de cada test
//@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }
    
}
