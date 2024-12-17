document.getElementById("rutaVuelo").addEventListener("change", function () {
  const vueloSection = document.getElementById("vueloSection");
  const reservasSection = document.getElementById("reservasSection");
  const detallesReservaSection = document.getElementById(
    "detallesReservaSection"
  );

  // Limpiar resultados previos
  document.getElementById("resultadosVuelo").innerHTML = "";

  vueloSection.style.display = "none";
  reservasSection.style.display = "none";
  detallesReservaSection.style.display = "none";

  const vueloSelect = document.getElementById("vuelo");
  vueloSelect.innerHTML = '<option value="">Selecciona un vuelo</option>';

  // Simular vuelos según la ruta seleccionada
  const vuelos = ["AR1380939", "AR13801059"];
  vuelos.forEach(function (vuelo) {
    const option = document.createElement("option");
    option.text = vuelo;
    vueloSelect.appendChild(option);
  });

  vueloSelect.disabled = false;
  if (
    document.getElementById("rutaVuelo").value === "Buenos Aires - Montevideo"
  ) {
    document.getElementById("vueloSection").style.display = "block";
  } else {
    document.getElementById("vueloSection").style.display = "none";
  }
});








document.getElementById("vuelo").addEventListener("change", function () {
  const reservasSection = document.getElementById("reservasSection");
  const detallesReservaSection = document.getElementById(
    "detallesReservaSection"
  );

  // Limpiar resultados previos
  document.getElementById("resultadosVuelo").innerHTML = "";

  const vuelo = document.getElementById("vuelo");
  if (vuelo.value === "AR13801059") {
    mostrarReservas();
    reservasSection.style.display = "block";
  } else {
    reservasSection.style.display = "none";
  }
});

document.getElementById("reservaVuelo").addEventListener("change", function () {
  const reserva = document.getElementById("reservaVuelo");
  const reservaDetalles = document.getElementById("detallesReservaSection");
  if (reserva.value === "ejstar") {
    mostrarDetallesReserva();
    reservaDetalles.style.display = "block";
  } else {
    // Limpiar resultados previos
    document.getElementById("resultadosVuelo").innerHTML = "";
    reservaDetalles.style.display = "none";
  }
});




function mostrarDetallesReserva() {
  document.getElementById("detalleTipoAsiento").textContent = "Ejecutivo";
  document.getElementById("detalleCantidadPasajeros").textContent = "8";
  document.getElementById("detalleEquipaje").textContent = "5";
  document.getElementById("detalleFechaReserva").textContent = "28/08/2024";
  document.getElementById("detalleCosto").textContent = "2870";
  document.getElementById("detalleTipoReserva").textContent = "General";

  const pasajeros = [{
    nombre: "Emily",
    apellido: "Johnson"
  },
  {
    nombre: "Jack",
    apellido: "Jhonson"
  },
  {
    nombre: "Liberty",
    apellido: "Trent"
  },
  {
    nombre: "Marc",
    apellido: "Ruffalo"
  },
  {
    nombre: "Jessica",
    apellido: "Landon"
  },
  {
    nombre: "Robert",
    apellido: "Shank"
  },
  {
    nombre: "Frank",
    apellido: "Trent"
  },
  {
    nombre: "Lucy",
    apellido: "Felton"
  }];

  // Limpiar resultados previos
  document.getElementById("resultadosVuelo").innerHTML = "";

  pasajeros.forEach((pasajero) => {

    // Crear una fila de resultados
    const nuevaFila = `
  <tr>
    <td>${pasajero.nombre}</td>
    <td>${pasajero.apellido}</td>
  </tr>
`;

    // Agregar la fila a la tabla
    document.getElementById("resultadosVuelo").innerHTML += nuevaFila;
  });
}


function mostrarReservas() {
  const reserva = document.getElementById("reservaVuelo");
  reserva.innerHTML =
    '<option value="">Selecciona una reserva</option>';
  const reservas = ["ejstar"];
  reservas.forEach(function (reservaItem) {
    const option = document.createElement("option");
    option.text = reservaItem;
    reserva.appendChild(option);
  });
}