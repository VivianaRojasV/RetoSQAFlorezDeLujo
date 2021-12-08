package pages;

import org.openqa.selenium.By;

public class FlorezDeLujoPage {

    By txtBuscador = By.xpath("//input[contains(@placeholder,'Buscar productos')]");
    By btnBuscador = By.xpath("//button[@class='dgwt-wcas-search-submit']");
    By txtElementoBusqueda;
    By btnVolver = By.xpath("//div[@class='et_pb_button_module_wrapper et_pb_button_0_tb_body_wrapper  et_pb_module ']//a[@class='et_pb_button et_pb_button_0_tb_body et_pb_bg_layout_light']");

    public By getTxtBuscador() {
        return txtBuscador;
    }

    public By getBtnBuscador() {
        return btnBuscador;
    }

    public By getTxtElementoBusqueda() {
        return txtElementoBusqueda;
    }

    public void setTxtElementoBusqueda(String producto) {
        this.txtElementoBusqueda = By.xpath("//h1[contains(text(),'"+producto+"')]");
    }

    public By getBtnVolver() {
        return btnVolver;
    }
}
