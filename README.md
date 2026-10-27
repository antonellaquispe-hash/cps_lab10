# cps_lab10 — Construcción y pruebas de software

Laboratorio de pruebas unitarias con JDK 21, JUnit 5 y Log4J 2. Incluye el proyecto Maven independiente de calculadora y la ampliación de `petclinic_test` con operaciones CRUD y pruebas para propietarios (`owners`).

## Calculadora (Maven principal)

- `groupId`: `pe.edu.tecsup`
- `artifactId`: `cps_lab10`
- Paquete: `pe.edu.tecsup.lab10`
- Operaciones: suma, resta, multiplicación y división (con protección ante división entre cero).
- `CalculatorTest` cubre las cuatro operaciones y el caso inválido.
- `CalculatorApp` registra operación y resultado mediante Log4J 2.

Ejecutar con JDK 21:

```bash
./mvnw clean test
./mvnw -q exec:java -Dexec.mainClass=pe.edu.tecsup.lab10.CalculatorApp
```

En Windows, usar `mvnw.cmd` en lugar de `./mvnw`. Para ejecutar la aplicación sin plugin exec:

```bash
./mvnw package
java -cp target/classes;"%USERPROFILE%\.m2\repository\org\apache\logging\log4j\log4j-api\2.24.3\log4j-api-2.24.3.jar";"%USERPROFILE%\.m2\repository\org\apache\logging\log4j\log4j-core\2.24.3\log4j-core-2.24.3.jar" pe.edu.tecsup.lab10.CalculatorApp
```

## PetClinic: CRUD de propietarios

`petclinic_test/` contiene una copia del proyecto indicado en el laboratorio ([jgomezz/petclinic_test](https://github.com/jgomezz/petclinic_test)) y la implementación de `OwnerService` con alta, modificación, búsqueda y eliminación. `OwnerServiceTest` verifica esos casos con el perfil `h2`, así que las pruebas no requieren instalar MySQL.

```bash
cd petclinic_test
../mvnw.cmd -Dtest=com.tecsup.petclinic.services.OwnerServiceTest clean test
```

La suite existente de PetClinic se ejecuta con:

```bash
../mvnw.cmd -Dspring.profiles.active=h2 test
```

### Preparar MySQL

El laboratorio requiere generar la base `PETCLINIC_DB`. Ejecutar en MySQL Workbench, en este orden:

1. `petclinic_test/data/schema-mysql.sql` (este script elimina y vuelve a crear la base).
2. `petclinic_test/data/data-mysql.sql`.

Luego exportar las credenciales antes de iniciar el perfil `test`; no guardar claves en el repositorio:

```powershell
$env:DB_USERNAME = 'tu_usuario_mysql'
$env:DB_PASSWORD = 'tu_clave_mysql'
cd petclinic_test
../mvnw.cmd -Dspring.profiles.active=test -Dtest=com.tecsup.petclinic.services.OwnerServiceTest test
```

El perfil `test` no ejecuta automáticamente scripts: se ejecutan manualmente en MySQL, y así evita que el esquema H2 sobrescriba la base MySQL. La base MySQL es necesaria para el ejercicio manual; las pruebas automatizadas ejecutadas aquí usan H2 y conservan los mismos casos CRUD.

## Observaciones y conclusiones

- Las pruebas unitarias permiten detectar errores de cálculo y validar el comportamiento esperado antes de integrar el código.
- Las trazas de Log4J 2 muestran la operación solicitada y su resultado, lo que facilita seguir la ejecución.
- Las pruebas CRUD de propietarios confirman persistencia y búsqueda; H2 hace la validación repetible y MySQL queda disponible para seguir el procedimiento de laboratorio.

