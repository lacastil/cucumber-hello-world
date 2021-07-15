Feature: Pago de Agua en SACMEX
  Scenario: Validación de acceso al sistema de SACMEX con cuenta valida
    Given Ingresar a la siguiente URL: www.sacmex.cdmx.gob.mx
    When Seleccionar el recuadro de la opcion Pago de Agua
    Then Ingresar en el campo Número de Cuenta a 16 dígitos un numero de cuenta valida sin adeudo y seleccionar el boton Consultar
  Scenario: Validación de acceso al sistema de SACMEX con cuenta invalida.
    Given Ingresar a la siguiente URL: www.sacmex.cdmx.gob.mx
    When Seleccionar el recuadro de la opcion Pago de Agua
    Then Ingresar en el campo Número de Cuenta a 16 dígitos un numero de cuenta invalida sin adeudo y seleccionar el boton Consultar
