#!/bin/bash

# Hacer ejecutables todos los archivos .sh en el directorio actual
echo "Asignando permisos de ejecución a los scripts..."
find "$(pwd)" -name "*.sh" -exec chmod +x {} \;

# Guarda el directorio actual
CURRENT_DIR=$(pwd)

# Generar ServidorWeb
echo "Generando ServidorWeb..."
bash "$CURRENT_DIR/generar-servidor-web.sh"
cd "$CURRENT_DIR"

# Navegar a la carpeta target de ServidorWeb
echo "Preparando ServidorWeb para despliegue..."
cd ../ServidorWeb/target

# Ruta del archivo WAR generado
WEB_WAR_FILE="ServidorWeb.war"

# Ruta del Tomcat (4 carpetas atrás)
TOMCAT_DIR="../../../../apache-tomcat-10.1.26/webapps"

# Limpiar archivos anteriores de ServidorWeb en Tomcat
echo "Limpiando archivos anteriores de ServidorWeb en Tomcat..."
rm -rf "$TOMCAT_DIR/ServidorWeb"
rm -f "$TOMCAT_DIR/ServidorWeb.war"

# Copiar el archivo WAR de ServidorWeb al directorio webapps de Tomcat
echo "Copiando $WEB_WAR_FILE a Tomcat..."
cp "$WEB_WAR_FILE" "$TOMCAT_DIR"

# Regresar al directorio inicial
cd "$CURRENT_DIR"

# Generar ServidorMobile
echo "Generando ServidorMobile..."
bash "$CURRENT_DIR/generar-servidor-mobile.sh"
cd "$CURRENT_DIR"

# Navegar a la carpeta target de ServidorMobile
echo "Preparando ServidorMobile para despliegue..."
cd ../ServidorMobile/target

# Ruta del archivo WAR generado
MOBILE_WAR_FILE="ServidorMobile.war"

# Limpiar archivos anteriores de ServidorMobile en Tomcat
echo "Limpiando archivos anteriores de ServidorMobile en Tomcat..."
rm -rf "$TOMCAT_DIR/ServidorMobile"
rm -f "$TOMCAT_DIR/ServidorMobile.war"

# Copiar el archivo WAR de ServidorMobile al directorio webapps de Tomcat
echo "Copiando $MOBILE_WAR_FILE a Tomcat..."
cp "$MOBILE_WAR_FILE" "$TOMCAT_DIR"

# Regresar al directorio inicial
cd "$CURRENT_DIR"

# Generar ServidorCentral
echo "Generando ServidorCentral..."
bash "$CURRENT_DIR/generar-servidor-central.sh"
cd "$CURRENT_DIR"

# Navegar a la carpeta target de ServidorCentral y ejecutar el comando Java
echo "Ejecutando MainViewV2 desde ServidorCentral/target..."
cd ../ServidorCentral/target
java -cp ServidorCentral-0.0.1-SNAPSHOT-jar-with-dependencies.jar uy.edu.fing.volandouy.gui.MainViewV2

# Regresar al directorio inicial
cd "$CURRENT_DIR"

echo "Proceso completo."
