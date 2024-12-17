document.getElementById("aerolinea").addEventListener("change", function () {
  const aerolineaSeleccionada = this.value;
  const rutaVueloSelect = document.getElementById("rutaVuelo");
  const vueloSelect = document.getElementById("vuelo");

  // Limpiar opciones previas
  rutaVueloSelect.innerHTML = '<option value="">Selecciona una ruta</option>';
  vueloSelect.innerHTML = '<option value="">Selecciona un vuelo</option>';
  rutaVueloSelect.disabled = true;
  vueloSelect.disabled = true;

  // Definir las rutas de vuelo con los datos adicionales
  const rutas = {
    ar: [
      {
        value: "RV03",
        text: "AR1380 - Buenos Aires - Montevideo",
        vuelos: [
          {
            value: "AR1380939",
            fecha: "2024-10-05",
            duracion: "120 min",
            asientosTurista: 150,
            asientosEjecutivo: 20,
            fechaAlta: "2024-09-01",
            imagen: "imagen1.jpg",
          },
          {
            value: "AR13801059",
            fecha: "2024-10-10",
            duracion: "130 min",
            asientosTurista: 140,
            asientosEjecutivo: 25,
            fechaAlta: "2024-09-02",
            imagen: "imagen2.jpg",
          },
        ],
      },
      { value: "RV04", text: "AR1381 - Montevideo - Buenos Aires", vuelos: [] },
    ],
  };

  if (aerolineaSeleccionada && rutas[aerolineaSeleccionada]) {
    rutas[aerolineaSeleccionada].forEach(function (ruta) {
      const option = document.createElement("option");
      option.value = ruta.value;
      option.text = ruta.text;
      rutaVueloSelect.appendChild(option);
    });

    rutaVueloSelect.disabled = false;
  }
});

document.getElementById("rutaVuelo").addEventListener("change", function () {
  const rutaSeleccionada = this.value;
  const vueloSelect = document.getElementById("vuelo");

  // Limpiar opciones previas
  vueloSelect.innerHTML = '<option value="">Selecciona un vuelo</option>';
  vueloSelect.disabled = true;

  // Obtener la aerolínea seleccionada
  const aerolineaSeleccionada = document.getElementById("aerolinea").value;

  // Definir los vuelos según la ruta seleccionada
  const rutas = {
    ar: [
      {
        value: "RV03",
        text: "AR1380 - Buenos Aires - Montevideo",
        vuelos: [
          {
            value: "AR1380939",
            fecha: "24/11/2024",
            duracion: "26 min",
            asientosTurista: 153,
            asientosEjecutivo: 16,
            fechaAlta: "26/08/2024",
            imagen: "../../assets/img/VU08.jpg",
          },
          {
            value: "AR13801059",
            fecha: "30/11/2024",
            duracion: "30 min",
            asientosTurista: 162,
            asientosEjecutivo: 8,
            fechaAlta: "27/08/2024",
            imagen: "../../assets/img/VU09.jpg",
          },
        ],
      },
      { value: "RV04", text: "AR1381 - Montevideo - Buenos Aires", vuelos: [] },
    ],
  };

  const ruta = rutas[aerolineaSeleccionada]?.find(
    (r) => r.value === rutaSeleccionada
  );

  if (ruta && ruta.vuelos.length > 0) {
    ruta.vuelos.forEach(function (vuelo) {
      const option = document.createElement("option");
      option.value = vuelo.value;
      option.text = vuelo.value;
      option.dataset.fecha = vuelo.fecha;
      option.dataset.duracion = vuelo.duracion;
      option.dataset.asientosTurista = vuelo.asientosTurista;
      option.dataset.asientosEjecutivo = vuelo.asientosEjecutivo;
      option.dataset.fechaAlta = vuelo.fechaAlta;
      option.dataset.imagen = vuelo.imagen;
      vueloSelect.appendChild(option);
    });

    vueloSelect.disabled = false;
  }
});

document
  .getElementById("consultaVueloForm")
  .addEventListener("submit", function (event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del submit

    // Obtener los valores seleccionados del formulario
    const aerolinea =
      document.getElementById("aerolinea").options[
        document.getElementById("aerolinea").selectedIndex
      ].text;
    const rutaVuelo =
      document.getElementById("rutaVuelo").options[
        document.getElementById("rutaVuelo").selectedIndex
      ].text;
    const vueloSelect =
      document.getElementById("vuelo").options[
        document.getElementById("vuelo").selectedIndex
      ];

    const vuelo = vueloSelect.text;
    const fecha = vueloSelect.dataset.fecha;
    const duracion = vueloSelect.dataset.duracion;
    const asientosTurista = vueloSelect.dataset.asientosTurista;
    const asientosEjecutivo = vueloSelect.dataset.asientosEjecutivo;
    const fechaAlta = vueloSelect.dataset.fechaAlta;
    const imagen = vueloSelect.dataset.imagen;

    // Limpiar resultados previos
    document.getElementById("resultadosVuelo").innerHTML = "";

    // Crear una fila de resultados
    const nuevaFila = `
    <tr>
      <td>${aerolinea}</td>
      <td>${rutaVuelo}</td>
      <td>${vuelo}</td>
      <td>${fecha}</td>
      <td>${duracion}</td>
      <td>${asientosTurista}</td>
      <td>${asientosEjecutivo}</td>
      <td>${fechaAlta}</td>
      <td> <a href="${imagen}">Imagen</a></td>
    </tr>
  `;

    // Agregar la fila a la tabla
    document.getElementById("resultadosVuelo").innerHTML = nuevaFila;
  });
