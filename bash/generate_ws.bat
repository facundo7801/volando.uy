@echo off
setlocal enabledelayedexpansion

REM Definir la ruta base del proyecto y el directorio de archivos Java
REM Navegar hacia el directorio padre del script actual
set "BASE_DIR=%~dp0.."
set "JAVA_PATH=%BASE_DIR%\ServidorCentral\src\webServices\uy\edu\fing\volandouy\webServices"
set "OUTPUT_DIR=%BASE_DIR%\ServidorWeb\src\main\java"
set "OUTPUT_DIR_MOBILE=%BASE_DIR%\ServidorMobile\src\main\java"
set "BASE_URL=http://localhost:9130"

REM Verificar si el directorio existe
if not exist "%JAVA_PATH%" (
    echo No se pudo encontrar el directorio: %JAVA_PATH%
    pause
    exit /b 1
)

REM Crear el directorio de salida si no existe
if not exist "%OUTPUT_DIR%" (
    mkdir "%OUTPUT_DIR%"
)

REM Crear el directorio de salida si no existe
if not exist "%OUTPUT_DIR_MOBILE%" (
    mkdir "%OUTPUT_DIR_MOBILE%"
)

REM Recorrer los archivos Java y ejecutar wsimport para cada uno, excepto LoginPublicador.java
for %%f in ("%JAVA_PATH%\*.java") do (
    REM Extraer el nombre del archivo sin la extensión
    set "FILENAME=%%~nf"
    
    REM Ignorar LoginPublicador.java
    if /i "!FILENAME!"=="LoginPublicador" (
        echo Ignorando el archivo %%f...
    ) else (
        set "SERVICE_NAME=!FILENAME!?wsdl"
        set "FULL_URL=%BASE_URL%/!SERVICE_NAME!"
        
        REM Imprimir la URL generada para depuración
        echo Ejecutando wsimport para !FULL_URL!...
        
        REM Ejecutar wsimport y especificar el directorio de salida
        call wsimport -keep -d "%OUTPUT_DIR%" "!FULL_URL!"
        call wsimport -keep -d "%OUTPUT_DIR_MOBILE%" "!FULL_URL!"
    )
)

endlocal
pause
