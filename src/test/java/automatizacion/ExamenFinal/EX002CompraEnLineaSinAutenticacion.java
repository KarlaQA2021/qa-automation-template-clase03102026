package automatizacion.ExamenFinal;

import java.time.Duration;

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

public class EX002CompraEnLineaSinAutenticacion {
    
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
public void testCompraEnLineaUsuarioAutenticado() {
    //TEST 1: Autenticación de usuario

    //Ingresar al sitio web
    driver.get("https://teststore.automationtesting.co.uk/index.php");

    //Hacer click en el enlace "Sign in"
     wait.until(ExpectedConditions.elementToBeClickable(
            By.linkText("Sign in"))).click();
    
    // Esperar que cargue la página de autenticación
    wait.until(ExpectedConditions.urlContains("controller=authentication"));

   //Validar que estamos en la página correcta

    String urlActual = driver.getCurrentUrl();

    Assertions.assertTrue(
        urlActual != null && urlActual.contains("controller=authentication"),
        "No se ingresó a la página de autenticación"
);

      // Regresar al catálogo
driver.get("https://teststore.automationtesting.co.uk/index.php");

System.out.println("URL: " + driver.getCurrentUrl());
System.out.println("TITULO: " + driver.getTitle());

// Esperar que cargue el catálogo
wait.until(ExpectedConditions.presenceOfElementLocated(
    By.cssSelector(".product")
));


 //Test 2 Seleccionar un producto y agregarlo al carrito
    //Paso 1: Seleccionar el producto desde el catálogo
   
    wait.until(ExpectedConditions.presenceOfElementLocated(
    By.cssSelector(".product")
));

    // Paso 2: Localizar el producto
    By productoHummingbird = By.xpath(
    "//a[contains(translate(normalize-space(.), " +
    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
    "'abcdefghijklmnopqrstuvwxyz')," +
    "'hummingbird printed t-shirt')]"
);

    // Paso 3: Esperar que sea clickeable
    WebElement producto = wait.until(
    ExpectedConditions.elementToBeClickable(productoHummingbird)
);

    // Paso 4: Seleccionar producto
    producto.click();

    // Paso 2: Validar el nombre del producto
    WebElement nombreProducto = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("h1.h1")
    )
);

    Assertions.assertEquals(
    "HUMMINGBIRD PRINTED T-SHIRT",
    nombreProducto.getText().trim(),
    "El producto seleccionado no es el esperado"
);

    // Paso 3: Agregar el producto al carrito
    WebElement botonAgregar = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("button[data-button-action='add-to-cart']")
    )
);

    botonAgregar.click();

    //Paso 4: Validar que se agregó el producto al carrito

    WebElement ventanaCarrito = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("blockcart-modal")
    )
);

    // Obtener texto de la ventana
    String mensajeCarrito = ventanaCarrito.getText();

    // Mostrar mensaje en consola
    System.out.println("MENSAJE DEL CARRITO: " + mensajeCarrito);

    // Validar mensaje de confirmación
    WebElement mensajeConfirmacion = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("#blockcart-modal .modal-header")
    )
);

    Assertions.assertTrue(
    mensajeConfirmacion.getText().contains(
        "Product successfully added to your shopping cart"
    ),
    "No se mostró el mensaje de confirmación"
);

String textoConfirmacion = mensajeConfirmacion.getText();

System.out.println("CONFIRMACION: [" + textoConfirmacion + "]");

Assertions.assertTrue(
    textoConfirmacion.contains("Product successfully added to your shopping cart"),
    "No se mostró el mensaje de confirmación. Texto encontrado: " + textoConfirmacion
);

// TEST 03: Validar el nombre del producto agregado al carrito

WebElement nombreProductoCarrito = ventanaCarrito.findElement(
    By.cssSelector("h6.product-name")
);

Assertions.assertTrue(
    nombreProductoCarrito.getText().trim()
        .equalsIgnoreCase("Hummingbird printed t-shirt"),
    "El producto en el carrito no es el esperado"
);

System.out.println(
    "PRODUCTO ENCONTRADO: [" +
    nombreProductoCarrito.getText().trim() + "]"
);

// TEST 04: Validar el precio del producto agregado al carrito

WebElement precioProductoCarrito = ventanaCarrito.findElement(
    By.cssSelector("p.product-price")
);

Assertions.assertEquals(
    "$19.12",
    precioProductoCarrito.getText().trim(),
    "El precio del producto en el carrito no es el esperado"
);

// TEST 05: Proceder al checkout y validar que se puede completar la compra

    // Paso 1: Localizar el botón de proceder al checkout
    WebElement botonCheckout = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("#blockcart-modal a[href*='controller=cart']")
    )
);

    // Paso 2: Hacer clic
    botonCheckout.click();

    // Paso 3: Validar que se navega al carrito
    wait.until(ExpectedConditions.urlContains("controller=cart"));

    String urlCheckout = driver.getCurrentUrl();

    Assertions.assertTrue(
    urlCheckout != null && urlCheckout.contains("controller=cart"),
    "No se ingresó al carrito de compras"
);

// TEST 06: Continuar al proceso de compra

// Paso 1: Localizar el botón
WebElement botonContinuarCompra = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("a.btn.btn-primary[href*='controller=order']")
    )
);

// Paso 2: Hacer clic
botonContinuarCompra.click();

// Paso 3: Esperar la página de checkout
wait.until(ExpectedConditions.urlContains("controller=order"));

// Paso 4: Validar navegación
String urlOrder = driver.getCurrentUrl();
Assertions.assertTrue(
    urlOrder != null && urlOrder.contains("controller=order"),
    "No se ingresó al proceso de checkout"
);

    // TEST 07: Completar formulario de dirección de envío
    //Completar el campo de dirección de envío
// TEST 07: Completar formulario de dirección

System.out.println("URL ACTUAL: " + driver.getCurrentUrl());
System.out.println("PAGINA ACTUAL: " + driver.getTitle());

System.out.println(
    "CONTENIDO: " +
    driver.findElement(By.tagName("body")).getText()
);

// Buscar el campo de dirección
WebElement campoDireccion = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("field-address1")
    )
);
System.out.println("URL: " + driver.getCurrentUrl());
System.out.println("TITULO: " + driver.getTitle());

System.out.println(
    "PANTALLA ACTUAL: " +
    driver.findElement(By.tagName("body")).getText()
);
campoDireccion.sendKeys("123 Test Street");

WebElement campoCiudad = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("field-city")
    )
);

campoCiudad.sendKeys("Beverly Hills");

// Completar State
WebElement campoEstado = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("field-id_state")
    )
);

Select estado = new Select(campoEstado);
estado.selectByVisibleText("California");


// Completar el campo de código postal
WebElement campoCodigoPostal = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("field-postcode")
    )
);

campoCodigoPostal.sendKeys("90210");


// Continuar después de completar la dirección
WebElement botonContinuarDireccion = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("button[name='confirm-addresses']")
    )
);

botonContinuarDireccion.click();

// TEST 08: Confirmar método de envío

// Paso 1: Localizar el botón Continue
WebElement botonContinuarEnvio = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("button[name='confirmDeliveryOption']")
    )
);

// Paso 2: Hacer clic
botonContinuarEnvio.click();

// Paso 3: Esperar la sección de pago
wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.id("payment-option-1")
));
}

    @AfterEach
     public void tearDown() {
    if (driver != null) {
     driver.quit();
    }
}

}
