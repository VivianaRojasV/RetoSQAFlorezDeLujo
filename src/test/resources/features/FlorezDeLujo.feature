Feature: HU-001 Buscar arreglo en Flores De Lujo
  Yo como usuario de FL
  Quiero buscar un arreglo floral en la plataforma
  Para ver el nombre del producto en pantalla

  Scenario: Buscar arreglo floral
    Given que me encuentro en la pagina de FlorezDeLujo
    When busque el producto
    Then podre ver producto en pantalla
