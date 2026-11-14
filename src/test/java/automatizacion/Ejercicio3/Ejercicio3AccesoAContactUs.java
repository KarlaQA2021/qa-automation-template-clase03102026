package automatizacion.Ejercicio3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Ejercicio3AccesoAContactUs {
    
WebDriver driver;
WebDriverWait wait;

@BeforeEach
public void prepararNavegador() {

    driver = new ChromeDriver();
    driver.manage().window().maximize();

    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.get("https://teststore.automationtesting.co.uk/index.php");
}

@Test
public void validarAccesoContactUs() {

    // Localizar enlace Contact Us
    WebElement contacto = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.cssSelector("a[href*='controller=contact']")
        )
    );

    // Hacer clic
    contacto.click();

    // Validar navegación
    wait.until(ExpectedConditions.urlContains("controller=contact"));

    String urlActual = driver.getCurrentUrl();

    Assertions.assertNotNull(
    urlActual,
    "La URL actual no debe ser null"
    );

    Assertions.assertTrue(
    urlActual.contains("controller=contact"),
    "No se navegó a Contact Us"
    );

    // Validar campos del formulario
    Assertions.assertTrue(
        driver.findElement(By.id("id_contact")).isDisplayed()
    );

    Assertions.assertTrue(
        driver.findElement(By.id("email")).isDisplayed()
    );

    Assertions.assertTrue(
    driver.findElement(By.id("contactform-message")).isDisplayed(),
    "El campo Message no está visible"
    );  

    Assertions.assertTrue(
    driver.findElement(By.name("submitMessage")).isDisplayed(),
    "El botón Submit no está visible"
    );
}

@AfterEach
public void cerrarNavegador() {

    if (driver != null) {
        driver.quit();
    }
}

}
