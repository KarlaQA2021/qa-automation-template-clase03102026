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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class Ejercicio3EnvioExitosoDelFormulario {
    
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
    public void validarEnvioExitosoFormulario() {

        // Aquí agregaremos las acciones del formulario

    }

    @AfterEach
    public void cerrarNavegador() {

        if (driver != null) {
            driver.quit();
        }
    }
}
