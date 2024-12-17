@echo off
rem Navega al directorio de ServidorCentral
cd ..\ServidorCentral

rem Ejecuta Maven clean y package para generar el archivo .jar
mvn clean package

rem Regresa al directorio original
cd..\Generar
