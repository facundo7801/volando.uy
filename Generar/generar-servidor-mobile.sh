#!/bin/bash
# Navega al directorio de ServidorMobile
cd ../ServidorMobile

# Ejecuta Maven clean y package para generar el archivo .war
mvn clean package

# Regresa al directorio original
cd ..
