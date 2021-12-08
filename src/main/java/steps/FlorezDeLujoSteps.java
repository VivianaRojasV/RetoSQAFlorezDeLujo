package steps;

import files.LecturaExcel;
import drivers.GoogleChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.FlorezDeLujoPage;

import java.io.IOException;

public class FlorezDeLujoSteps {

    FlorezDeLujoPage florezDeLujoPage = new FlorezDeLujoPage();
    files.LecturaExcel lecturaExcel = new LecturaExcel();

    public void abrirPagina(){
        GoogleChromeDriver.chromeWebDriver("https://floresdelujo.co/");
    }

    public void buscarElementoEnFloresDeLujo(String producto) {
        GoogleChromeDriver.driver.findElement(florezDeLujoPage.getTxtBuscador()).sendKeys(producto);
        GoogleChromeDriver.driver.findElement(florezDeLujoPage.getBtnBuscador()).click();
    }

    public void validarElementoEnPantalla(String producto){
        florezDeLujoPage.setTxtElementoBusqueda(producto);
        Assert.assertEquals(producto,GoogleChromeDriver.driver.findElement(florezDeLujoPage.getTxtElementoBusqueda()).getText());

    }

    public void busquedaYValidacionDeProductos () throws IOException {
        for (int i = 0; i < lecturaExcel.leerDatosDeHojaDeExcel("busqueda reto.xlsx", "Hoja1").size(); i++) {
            buscarElementoEnFloresDeLujo(lecturaExcel.leerDatosDeHojaDeExcel("busqueda reto.xlsx", "Hoja1").get(i).get("Productos"));
            validarElementoEnPantalla(lecturaExcel.leerDatosDeHojaDeExcel("busqueda reto.xlsx", "Hoja1").get(i).get("Productos"));
            volverBusqueda();
        }
    }
    public void volverBusqueda(){
        GoogleChromeDriver.driver.findElement(florezDeLujoPage.getBtnVolver()).click();
    }
        public void cerrarPagina(){
            GoogleChromeDriver.driver.quit();
        }
}
