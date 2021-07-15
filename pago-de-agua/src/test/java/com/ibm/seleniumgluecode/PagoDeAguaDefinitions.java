package com.ibm.seleniumgluecode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class PagoDeAguaDefinitions {

    private ChromeDriver driver;

    @Given("Ingresar a la siguiente URL: www.sacmex.cdmx.gob.mx")
    public void ingresarALaSiguienteURLWwwSacmexCdmxGobMx() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver");
        driver = new ChromeDriver();
        //Acceder a la URL
        driver.get("https://www.sacmex.cdmx.gob.mx/");
        //Maximizar la ventana
        driver.manage().window().maximize();
        //Validar el acceso a la URL www.sacmex.cdmx.gob.mx
        Assert.assertEquals("Sistema de Aguas de la Ciudad de México", driver.getTitle());
    }

    @When("Seleccionar el recuadro de la opcion Pago de Agua")
    public void seleccionarElRecuadroDeLaOpcionPagoDeAgua() {
        //Guardar el ID de la ventana original
        String originalWindow = driver.getWindowHandle();
        //Validamos que no tengamos otras ventanas abiertas
        assert driver.getWindowHandles().size() == 1;
        //Al seleccionar el recuadro de la opcion Pago de Agua se abre una nueva pestaña
        driver.findElementById("banner_1").click();
        //Iteramos hasta encontrar el manejador de la nueva pestaña
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @Then("Ingresar en el campo Número de Cuenta a {int} dígitos un numero de cuenta invalida sin adeudo y seleccionar el boton Consultar")
    public void ingresarEnElCampoNúmeroDeCuentaADígitosUnNumeroDeCuentaInvalidaSinAdeudoYSeleccionarElBotonConsultar(Integer int1) {
        //Validamos que estemos en la pantalla de Pago de Agua
        Assert.assertEquals("Pago de Agua - SACMEX", driver.getTitle());
        //Llenamos el formulario con el numero de cuenta invalido
        driver.findElementById("cuenta").sendKeys("1829605956010101");
        //Seleccionar el recuadro de la opcion Consultar
        driver.findElementById("envia-cuenta").click();
        // Inicializamos y esperamos hasta que el elemento tenga resultados - timeout de 10 segundos
        WebElement result = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.className("txtImportante")));
        // Validamos el mensaje de error
        Assert.assertEquals("La cuenta no esta registrada en el padrón.", result.getText());
        //driver.quit();
    }

    @Then("Ingresar en el campo Número de Cuenta a {int} dígitos un numero de cuenta valida sin adeudo y seleccionar el boton Consultar")
    public void ingresarEnElCampoNúmeroDeCuentaADígitosUnNumeroDeCuentaValidaSinAdeudoYSeleccionarElBotonConsultar(Integer int1) {
        //Validamos que estemos en la pantalla de Pago de Agua
        Assert.assertEquals("Pago de Agua - SACMEX", driver.getTitle());
        //Llenamos el formulario con el numero de cuenta valido
        driver.findElementById("cuenta").sendKeys("1829605956010105");
        //Seleccionar el recuadro de la opcion Consultar
        driver.findElementById("envia-cuenta").click();
        // Inicializamos y esperamos hasta que el elemento tenga resultados - timeout de 10 segundos
        WebElement result = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='tbd2 tbdb generalGris']")));
        // Validamos la consulta al numero de cuenta valido
        Assert.assertEquals("1829605956010105", result.getText());
        //driver.quit();
    }


}
