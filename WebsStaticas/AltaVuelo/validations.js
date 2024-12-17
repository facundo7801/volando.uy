document
  .getElementById("altaVueloForm")
  .addEventListener("submit", function (event) {
    const nombre = document.getElementById("nombre").value;
    const fecha = new Date(document.getElementById("fecha").value);
    const duracion = document.getElementById("duracion").value;
    const maxTurista = document.getElementById("maxTurista").value;
    const maxEjecutivo = document.getElementById("maxEjecutivo").value;

    const hoy = new Date();
    const nombresProhibidos = ["AR1380939", "AR13801059"];

    // Validar que el nombre no esté en la lista de nombres prohibidos
    if (nombresProhibidos.includes(nombre)) {
      alert("El nombre del vuelo ya se encuentra registrado.");
      event.preventDefault();
      return; // Detiene el proceso si el nombre es inválido
    }

    if (fecha < hoy) {
      alert("La fecha del vuelo debe ser futura.");
      event.preventDefault();
    }

    if (duracion <= 0) {
      alert("La duración debe ser mayor que cero.");
      event.preventDefault();
    }

    if (maxTurista <= 0 || maxEjecutivo <= 0) {
      alert("El número de asientos debe ser mayor que cero.");
      event.preventDefault();
    }
  });
