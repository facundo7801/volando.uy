@echo off

rem Guarda el directorio actual
set CURRENT_DIR=%cd%

rem Generar ServidorCentral
echo Generando ServidorCentral...
call "%CURRENT_DIR%\generar-servidor-central.bat"
cd %CURRENT_DIR%

rem Generar ServidorWeb
echo Generando ServidorWeb...
call "%CURRENT_DIR%\generar-servidor-web.bat"
cd %CURRENT_DIR%

rem Generar ServidorMobile
echo Generando ServidorMobile...
call "%CURRENT_DIR%\generar-servidor-mobile.bat"
cd %CURRENT_DIR%

echo Proceso completo.
pause

