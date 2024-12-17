document.getElementById("aerolinea").addEventListener("change", function () {
  const rutaVueloSelect = document.getElementById("rutaVuelo");
  const vueloSection = document.getElementById("vueloSection");
  const reservasSection = document.getElementById("reservasSection");
  const detallesReservaSection = document.getElementById(
    "detallesReservaSection"
  );

  rutaVueloSelect.innerHTML = '<option value="">Selecciona una ruta</option>';
  vueloSection.style.display = "none";
  reservasSection.style.display = "none";
  detallesReservaSection.style.display = "none";

  // Simular rutas según la aerolínea seleccionada
  const rutas = {
    ar: ["Buenos Aires - Montevideo", "Montevideo - Buenos Aires"],
  };

  const aerolineaSeleccionada = this.value;
  if (rutas[aerolineaSeleccionada]) {
    rutas[aerolineaSeleccionada].forEach(function (ruta) {
      const option = document.createElement("option");
      option.text = ruta;
      rutaVueloSelect.appendChild(option);
    });
    rutaVueloSelect.disabled = false;
    document.getElementById("rutaSection").style.display = "block";
  }
});

document.getElementById("rutaVuelo").addEventListener("change", function () {
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

  // if (actorSeleccionado === "aerolinea") {
  //   reservasSection.style.display = "block";
  //   // Simular reservas del vuelo
  //   const reservaVueloSelect = document.getElementById("reservaVuelo");
  //   reservaVueloSelect.innerHTML =
  //     '<option value="">Selecciona una reserva</option>';
  //   const reservas = ["Reserva 1", "Reserva 2", "Reserva 3"];
  //   reservas.forEach(function (reserva) {
  //     const option = document.createElement("option");
  //     option.text = reserva;
  //     reservaVueloSelect.appendChild(option);
  //   });
  // }
  detallesReservaSection.style.display = "block";
  const vuelo = document.getElementById("vuelo");
  if (vuelo.value === "AR13801059") {
    mostrarDetallesReserva();
  }else if (vuelo.value == "AR1380939"){
    //document.getElementById("detallesReservaSection").innerHTML = "";
    detallesReservaSection.style.display = "none";
  }
  
  
  

});

 document.getElementById("reservaVuelo").addEventListener("change", function () {
   const vuelo = document.getElementById("vueloSection").appendChild("vuelo");
   if (vuelo.value === "AR13801059") {
     mostrarDetallesReserva();
   }
 });




function mostrarDetallesReserva() {
  document.getElementById("detalleTipoAsiento").textContent = "Ejecutivo";
  document.getElementById("detalleCantidadPasajeros").textContent = "8";
  document.getElementById("detalleEquipaje").textContent = "5";
  document.getElementById("detalleFechaReserva").textContent = "28/08/2024";
  document.getElementById("detalleCosto").textContent = "2870";
  document.getElementById("detalleTipoReserva").textContent = "General";
}
