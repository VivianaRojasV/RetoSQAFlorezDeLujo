# RetoSQAFlorezDeLujo
_El presente proyecto es realizado con el fin de poner en practica y ejecución lo aprendido en el Semillero de Automatización de pruebas realizado por la empresa SQA. En se podrá evidenciar el uso de herramientas aplicadas para su aplicación.La practica se realizará en la página https://floresdelujo.co/, la cual es una página para venta de arreglos florares en la ciudad de Medellín y sus alrededores, en ella podemos evidenciar que es bastante estática, lo que quiere decir es que es bastante fácil de usar, amigable y clara para que el cliente pueda ver los productos ofrecidos por empresa. En la página podemos encontrar el cajón de texto para buscar productos y más opciones de búsqueda o categorías para diferentes ocasiones especiales y demás, información de contacto y un enlace para asesoría por WhatsApp._
## Las herramientas usadas para su procedimiento fueron: 

* IDEA IntelliJ 

* Navegador Google Chrome con su chromedriver

* Lenguaje de programación Java

* Marco de pruebas Selenium 

* Patrón de diseño POM

* Automatizador de pruebas Cucumber y su analizador de lenguaje Gherkin

## Requerimiento del reto :bulb:

_El reto impuesto debía cumplir con lo siguiente:_
_Leer un archivo de Excel que contiene como mínimo 5 productos escogidos dentro de la página https://floresdelujo.co/, El archivo de Excel debía ser creado por cada participante del reto._
_La automatización debe buscar cada uno los productos enlistados en el Excel dentro de la página, una vez encontrado dentro de la búsqueda seleccionarlo y realizar la validación del nombre del producto buscado sea igual al relacionado dentro del archivo de Excel._
## Pasos de la automatización :arrow_forward:
_Abrir el navegador y buscar la pagina https://floresdelujo.co/._
_Abrir archivo de Excel y leer el primer nombre del producto._
_Ubicar el cajón de texto “buscar producto” y colocar el nombre de la lista (esta acción se realizará por cada uno de los productos enlistados)._
_Dar click en el botón buscar._
_Seleccionar en el producto encontrado (realiza la validación del nombre)._
_Dar click en “Volver a la tienda” (esta acción se realizará cada vez que nos confirme el producto a buscar)._
_Al finalizar la lectura se realiza el cierre de la página._
_Si la prueba cumple con todos los pasos anteriormente mencionados, podremos decir que la prueba fue realizada con éxito._

## Paquetes, clases y métodos usados.

_Explicaremos el código implementado._

### Paquete Driver

_Clase GoogleChromeDriver._
_En la clase GoogleChromeDriver se crea un método ChromeDriver con parámetro de String o link, este nos permite abrir el navegador y realizar la búsqueda de la pagina https://floresdelujo.co/._
```
public static void chromeWebDriver(String url){
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--disable-infobars");
    driver = new ChromeDriver(options);
    driver.get(url);
}

```
### Paquete Files
_Clase lectura de Excel._
_En la clase LecturaExcel creamos un método de lectura que tiene como ejercicio leer una archivo de Excel. Contamos dos parámetros a leer String rutaDeExcel y Strin hojaDeExcel. En el parámetro de ruta se indicará en lugar donde se encuentra el archivo que contiene la lista de productos y con el parámetro de hoja de Excel se da el nombre de la hoja del archivo “búsqueda reto.xlxs”._
_Retornando la información en una arraylist._
```
public class LecturaExcel {
    public static ArrayList<Map<String, String>> leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel) throws IOException {
        ArrayList<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList<Map<String, String>>();
        Map<String, String> informacionProyecto = new HashMap<String, String>();
        File file = new File(rutaDeExcel);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
        Iterator<Row> rowIterator = newSheet.iterator();
        Row titulos = rowIterator.next();
        while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.getColumnIndex();
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), String.valueOf((long) cell.getNumericCellValue()));
                        break;
                    case BLANK:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), "");
                        break;
                    default:
                }
            }
            arrayListDatoPlanTrabajo.add(informacionProyecto);
            informacionProyecto = new HashMap<String, String>();
        }
        return arrayListDatoPlanTrabajo;
    }
```
## Paquete Pages
_Clase Page_
_En está realizamos las relacionamos la xpath que son los identificadores del campo de texto “buscar producto” y del botón “buscar” dentro de la pagina https://floresdelujo.co/._
_También contamos con los métodos Get para hacer los llamados a los elementos y realizar el retorno a la tienda, esto se realiza por cada producto._
_El método Set nos permite realizar la validación del producto encontrado en la página por el producto a buscar._
```
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
```
## Paquete Steps
_Clase Step._
_Aquí encontraremos los métodos del paso a paso de la prueba._
_Abrir Pagina._
```
public void abrirPagina(){
    GoogleChromeDriver.chromeWebDriver("https://floresdelujo.co/");
}
```
_Buscar Elemento._
```
public void buscarElementoEnFloresDeLujo(String producto) {
    GoogleChromeDriver.driver.findElement(florezDeLujoPage.getTxtBuscador()).sendKeys(producto);
    GoogleChromeDriver.driver.findElement(florezDeLujoPage.getBtnBuscador()).click();
}
```
_Validar elemento en pantalla._
```
public void validarElementoEnPantalla(String producto){
    florezDeLujoPage.setTxtElementoBusqueda(producto);
    Assert.assertEquals(producto,GoogleChromeDriver.driver.findElement(florezDeLujoPage.getTxtElementoBusqueda()).getText());
}
```
_Búsqueda y validación de productos en el archivo Excel con los datos a buscar y se realiza con un ciclo para leer toda la lista._
```
public void busquedaYValidacionDeProductos () throws IOException {
    for (int i = 0; i < lecturaExcel.leerDatosDeHojaDeExcel("busqueda reto.xlsx", "Hoja1").size(); i++) {
        buscarElementoEnFloresDeLujo(lecturaExcel.leerDatosDeHojaDeExcel("busqueda reto.xlsx", "Hoja1").get(i).get("Productos"));
        validarElementoEnPantalla(lecturaExcel.leerDatosDeHojaDeExcel("busqueda reto.xlsx", "Hoja1").get(i).get("Productos"));
        volverBusqueda();
    }
```
_Botón de regreso a la tienda para encontrar nuevamente el campo de texto para realización del siguiente producto a buscar._
```
public void volverBusqueda(){
    GoogleChromeDriver.driver.findElement(florezDeLujoPage.getBtnVolver()).click();
}
```
_Al finalizar la búsqueda del último producto, se debe hacer el método de cerrar página._
```
public void cerrarPagina(){
    GoogleChromeDriver.driver.quit();
}
```
## Paquete Runner
_Clase Runner_
_En este paquete encontramos las herramientas para realizar la prueba (son quienes ejecutaran la prueba)._
_Usaremos Cucumber con Serenity._
```
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\FlorezDeLujo.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class FlorezDeLujoRunners {
}
```
## Paquete Features
_FloresDeLujo.features._
_En este paquete encontramos la información del escenario o prueba a realizar, en donde:_
_Given = Recibe el contexto._
_When= En donde se realizan las acciones._
_Then= Los resultados de las acciones se comparan con las expectativas._
_En los Step Definion explicaremos su función._
```
Feature: HU-001 Buscar arreglo en Flores De Lujo
  Yo como usuario de FL
  Quiero buscar un arreglo floral en la plataforma
  Para ver el nombre del producto en pantalla

  Scenario: Buscar arreglo floral
    Given que me encuentro en la pagina de FlorezDeLujo
    When busque el producto
    Then podre ver producto en pantalla
```
## Paquete StepDefinitions
_Paquete StepDefinitions._
_Aquí podemos especificar el del sistema de pruebas, en la cual se dividen en tres:_
_@Given_
_Condiciones previas a la prueba (abrir pagina https://floresdelujo.co/)_
```
@Given("^que me encuentro en la pagina de FlorezDeLujo$")
public void queMeEncuentroEnLaPaginaDeFlorezDeLujo() {
     florezDeLujoSteps.abrirPagina();
}
```
_@When_
_Este realiza la acción que está en prueba (buscar producto)_
```
@When("^busque el producto$")
public void busqueElProducto() throws IOException {
     florezDeLujoSteps.busquedaYValidacionDeProductos();
}
```
_@Then_
_Comprueba que se cumple la condición solicitada. Esto suele ser en forma de afirmación de valores o verificación de la interacción con simulacros._
```
@Then("^podre ver producto en pantalla$")
public void podreVerProductoEnPantalla() {
     //florezDeLujoSteps.validarElementoEnPantalla("Amor Eterno");
     florezDeLujoSteps.cerrarPagina();
}
```
