#!/bin/bash

# Definir la ruta base del proyecto y el directorio de archivos Java
BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"  # Subir un nivel desde donde está el script
JAVA_PATH="$BASE_DIR/ServidorCentral/src/webServices/uy/edu/fing/volandouy/webServices"
OUTPUT_DIR="$BASE_DIR/ServidorWeb/src/main/java"
OUTPUT_DIR_MOBILE="$BASE_DIR/ServidorMobile/src/main/java"
BASE_URL="http://localhost:9130"

# Verificar si el directorio existe
if [ ! -d "$JAVA_PATH" ]; then
  echo "No se pudo encontrar el directorio: $JAVA_PATH"
  exit 1
fi

# Crear el directorio de salida si no existe
mkdir -p "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR_MOBILE"

# Recorrer los archivos Java y ejecutar wsimport para cada uno, excepto LoginPublicador.java
for java_file in "$JAVA_PATH"/*.java; do
  # Extraer el nombre del archivo sin la extensión
  filename=$(basename -- "$java_file")
  filename_no_ext="${filename%.*}"

  # Ignorar LoginPublicador.java
  if [[ "$filename_no_ext" == "LoginPublicador" ]]; then
    echo "Ignorando el archivo $java_file..."
  else
    # Construir la URL completa y ejecutar wsimport
    service_name="${filename_no_ext}?wsdl"
    full_url="$BASE_URL/$service_name"
    echo "Ejecutando wsimport para $full_url..."
    wsimport -keep -d "$OUTPUT_DIR" "$full_url"
    wsimport -keep -d "$OUTPUT_DIR_MOBILE" "$full_url"
  fi
done
