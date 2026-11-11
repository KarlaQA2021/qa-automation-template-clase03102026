package automatizacion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    driver.get("https://the-internet.herokuapp.com/login");

    //Obtener titulo del sitio web
    String tituloActual = driver.getTitle();
    String tituloEsperado = "The Internet";

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
    driver.get("https://the-internet.herokuapp.com/login");

        //ingresar las credenciales de autenticación
        driver.findElement(By.id("username"))
                .sendKeys("tomsmith");

        driver.findElement(By.id("password"))
                .sendKeys("SuperSecretPassword!");

          //Validación de URL
        String urlEsperada = "https://the-internet.herokuapp.com/login";
        
        Assertions. assertEquals(urlEsperada, 
            driver.getCurrentUrl(),
            "La url encontrada no coincide con la esperada");

        driver.findElement(By.className("radius"))
                .click();



        //Validación de mensaje de login exitoso
        WebElement mensajeLoginExitoso = driver.findElement(By.id("flash"));
        
        String textoMensaje = mensajeLoginExitoso.getText();

        //Uso de condicional para validar el login exitoso
        if(textoMensaje.contains("You logged into a secure area!")){

            System.out.println("Login exitoso validado correctamente");

        }else {
            System.out.println("Error en el mensaje de login encontrado");

            Assertions.fail("Se esperaba el mensaje: You logged into a secure area! " 
                + "\n pero se encontro: " + textoMensaje);
        }
        
        
    }

//Acciones que se van a ejecutar despues de cada test
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }
    
}
