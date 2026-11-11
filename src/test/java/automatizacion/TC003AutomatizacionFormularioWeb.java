package automatizacion;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC003AutomatizacionFormularioWeb {
//Script para validar el Login Page
   private WebDriver driver;
   private WebDriver wait;

//Acciones que se ejecutaran antes de cada test
    @BeforeEach 
        public void  inicializarNavegador(){
        //Inicializador driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

}

//Test 1: validar ingreso al formulario
@Test 
public void validarTituloDeLaPagina(){
    //Abrir el formulario
    driver.get("https://www.automationtesting.co.uk/contactForm.html");

   //Validar nombre del formulario
WebElement nombFormulario = driver.findElement(By.cssSelector("#content"));
String tituloFormulario = nombFormulario.getText();

//Uso de condicional para validar el login exitoso
if (tituloFormulario.contains("Contact Form Test")) {
    System.out.println("Estas en Formulario de Contacto");    
}else{
    System.out.println("No estas en el formulario esperado");
    Assertions.fail("Se esperaba el titulo de Formulario: Contact Form Test! "
        + "\n Pero se encontró: " + tituloFormulario
    );
}

}

//Test 2: Validar un login exitoso
    @Test 
    public void loginExitoso(){
         //Abrir nuestro navegador
    driver.get("https://www.automationtesting.co.uk/contactForm.html");

    //Llenado de formulario
        driver.findElement(By.name("first_name"))
                .sendKeys("Karla");

        driver.findElement(By.name("last_name"))
                .sendKeys("Ferman");
        
        driver.findElement(By.name("email"))
                .sendKeys("karlaferman987@gmail.com");

        driver.findElement(By.name("message"))
                .sendKeys("Validación de creación de formulario web");

          //Validación de URL
        String urlEsperada = "https://www.automationtesting.co.uk/contactForm.html";
        
        Assertions. assertEquals(urlEsperada, 
            driver.getCurrentUrl(),
            "La url encontrada no coincide con la esperada");

        driver.findElement(By.cssSelector("input[value='SUBMIT']"))
                .click();

//Validar mensaje de formulario llenado con exito
        WebElement formularioCompletado = driver.findElement(By.cssSelector("div[class='col-8 col-12-small'] h3"));
        String mensajeExitoso = formularioCompletado.getText();

//Uso de condicional para validar el login exitoso
        if(mensajeExitoso.equals("Thank you for your mail!")){
            System.out.println("Formulario llenado exitosamente");
       }else{
            System.out.println("No estas en el formulario esperado");
            Assertions.fail("Se esperaba el titulo de Formulario: Contact Form Test! "
        + "\n Pero se encontró: " + mensajeExitoso);
        }

}

//Acciones que se van a ejecutar despues de cada test
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }
     
}