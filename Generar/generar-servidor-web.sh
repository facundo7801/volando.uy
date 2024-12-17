#!/bin/bash
# Navega al directorio de ServidorWeb
cd ../ServidorWeb

# Ejecuta Maven clean y package para generar el archivo .war
mvn clean package

# Regresa al directorio original
cd ..
