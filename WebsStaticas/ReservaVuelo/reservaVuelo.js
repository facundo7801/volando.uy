document.getElementById("aerolinea").addEventListener("change", function () {
  const aerolineaSeleccionada = this.value;
  const rutaVueloSelect = document.getElementById("rutaVuelo");
  const vueloSelect = document.getElementById("vuelo");
  const detallesVuelo = document.getElementById("detallesVuelo");
  const datosReserva = document.getElementById("datosReserva");

  // Limpiar campos anteriores
  rutaVueloSelect.innerHTML = '<option value="">Selecciona una ruta</option>';
  vueloSelect.innerHTML = '<option value="">Selecciona un vuelo</option>';
  detallesVuelo.style.display = "none";
  datosReserva.style.display = "none";

  // Rutas y vuelos (simulados)
  const rutas = {
    ar: ["Buenos Aires - Montevideo", "Montevideo - Buenos Aires"],
  };

  if (rutas[aerolineaSeleccionada]) {
    rutas[aerolineaSeleccionada].forEach(function (ruta) {
      const option = document.createElement("option");
      option.text = ruta;
      rutaVueloSelect.appendChild(option);
    });
    rutaVueloSelect.disabled = false;
  }
});

document.getElementById("rutaVuelo").addEventListener("change", function () {
  const vueloSelect = document.getElementById("vuelo");
  const rutaVueloSelect = document.getElementById("rutaVuelo");
  const detallesVuelo = document.getElementById("detallesVuelo");
  const datosReserva = document.getElementById("datosReserva");

  // Simular vuelos según la ruta seleccionada
  vueloSelect.innerHTML = '<option value="">Selecciona un vuelo</option>';
  if (rutaVueloSelect.value === "Buenos Aires - Montevideo") {
    const vuelos = ["AR1380939", "AR13801059"];
    vuelos.forEach(function (vuelo) {
      const option = document.createElement("option");
      option.text = vuelo;
      vueloSelect.appendChild(option);
    });
    vueloSelect.disabled = false;
  }
});

document.getElementById("vuelo").addEventListener("change", function () {
  const detallesVuelo = document.getElementById("detallesVuelo");
  const datosReserva = document.getElementById("datosReserva");

  const vueloSelect = document.getElementById("vuelo");

  // Mostrar los detalles del vuelo y habilitar los datos de la reserva
  detallesVuelo.style.display = "block";
  datosReserva.style.display = "block";

  if (vueloSelect.value === "AR1380939") {
    // Simulación de los datos del vuelo
    document.getElementById("detalleFecha").textContent = "24/11/2024";
    document.getElementById("detalleDuracion").textContent = "26 min";
    document.getElementById("detalleTurista").textContent = "153";
    document.getElementById("detalleEjecutivo").textContent = "16";
  } else if (vueloSelect.value === "AR13801059") {
    // Simulación de los datos del vuelo
    document.getElementById("detalleFecha").textContent = "30/11/2024";
    document.getElementById("detalleDuracion").textContent = "30 min";
    document.getElementById("detalleTurista").textContent = "162";
    document.getElementById("detalleEjecutivo").textContent = "8";
  }
});

document
  .getElementById("cantidadPasajes")
  .addEventListener("input", function () {
    const cantidadPasajes = parseInt(this.value, 10);
    const infoPasajeros = document.getElementById("infoPasajeros");
    const listaPasajeros = document.getElementById("listaPasajeros");

    listaPasajeros.innerHTML = ""; // Limpiar la lista

    if (cantidadPasajes > 1) {
      infoPasajeros.style.display = "block";
      for (let i = 1; i <= cantidadPasajes; i++) {
        const pasajeroDiv = document.createElement("div");
        pasajeroDiv.className = "mb-2";
        pasajeroDiv.innerHTML = `
        <label for="pasajero${i}Nombre">Pasajero ${i} - Nombre:</label>
        <input type="text" id="pasajero${i}Nombre" class="form-control" required />
        <label for="pasajero${i}Apellido">Pasajero ${i} - Apellido:</label>
        <input type="text" id="pasajero${i}Apellido" class="form-control" required />
      `;
        listaPasajeros.appendChild(pasajeroDiv);
      }
    } else {
      infoPasajeros.style.display = "none";
    }
  });

document.getElementById("metodoPago").addEventListener("change", function () {
  const metodoPago = this.value;
  const vueloSelect = document.getElementById("vuelo");
  const selectPaquete = document.getElementById("pagoPaquete");
  const pagoPaquete = document.getElementById("pagoPaquete");
  if (metodoPago === "paquete" && vueloSelect.value === "AR13801059") {
    pagoPaquete.style.display = "block";
  } else {
    pagoPaquete.style.display = "none";
  }
});
