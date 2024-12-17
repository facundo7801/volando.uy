@echo off
rem Navega al directorio de ServidorMobile
cd ..\ServidorMobile

rem Ejecuta Maven clean y package para generar el archivo .war
mvn clean package

rem Regresa al directorio original
cd ..
