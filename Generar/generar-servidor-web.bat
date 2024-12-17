@echo off
rem Navega al directorio de ServidorWeb
cd ..\ServidorWeb

rem Ejecuta Maven clean y package para generar el archivo .war
mvn clean package

rem Regresa al directorio original
cd ..
pause