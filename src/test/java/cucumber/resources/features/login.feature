Feature: Inicio de sesión con un usuario normal

  Scenario: Inicio de sesión con Juana Alonso
    Given la lista de usuarios:
      | name                 | password |
      | jualo@participant.es | jualo123 |
    When introduzco el usuario "jualo@participant.es" y la contraseña "jualo123"
    Then entro en la pantalla de sugerencias