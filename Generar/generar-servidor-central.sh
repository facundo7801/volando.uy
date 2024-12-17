#!/bin/bash
# Navega al directorio de ServidorCentral
cd ../ServidorCentral

# Ejecuta Maven clean y package para generar el archivo .jar
mvn clean package

# Regresa al directorio original
cd ..
