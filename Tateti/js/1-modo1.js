const celdas = document.querySelectorAll(".celda")
const inputJugador1 = document.getElementById("jugador1")
const inputJugador2 = document.getElementById("jugador2")
const botonEmpezar = document.getElementById("empezar");
const infoTurno = document.getElementById("indicadorTurno");
const seccionTurno = document.getElementById("InfoTurno");
const tabla = document.querySelector("table");
const btnJugador1X = document.getElementById("jugador1X");
const btnJugador1Y = document.getElementById("jugador1Y");
const btnJugador2X = document.getElementById("jugador2X");
const btnJugador2Y = document.getElementById("jugador2Y");

let juegoIniciado = false;
let juegoTerminado = false;
let turnoJ = true;
let turno = "X";
let tablero = [" ", " ", " " , " ", " ", " ", " ", " ", " "]


function imprimirTurno() {
    if(turnoJ)
    {
        infoTurno.innerText = `${inputJugador1.value} (${turno})`;
    }else
    {
        infoTurno.innerText = `${inputJugador2.value} (${turno})`;
    }
}

function iniciarJuego()
{

    if(btnJugador1X.disabled)
    {
        turnoJ = true;
    }else
    {
        turnoJ = false;
    }

    imprimirTurno();
}



function manjearJugada(evento)
{
    if (!juegoIniciado || juegoTerminado) return;

    const celdaClickeada = evento.target;

    const idCelda = celdaClickeada.id; 
    const indice = parseInt(idCelda.slice(5)) - 1; 

    if (tablero[indice] !== " ") {
        return;
    }
    
    tablero[indice] = turno;

    celdaClickeada.innerText = turno;

    verificarGanador();
    if(juegoTerminado)
    {
        return;
    }

    if(!tablero.includes(" "))
    {
        juegoTerminado = true;
        infoTurno.innerText = "¡Empate! El juego ha terminado.";
        return;
    }
    
    turnoJ = !turnoJ; 
    if (turno === "X") {
        turno = "O";
    } else {
        turno = "X";
    }
    
    imprimirTurno();
}

function verificarGanador() {
  const combinacionesGanadoras = [
    [0, 1, 2], [3, 4, 5], [6, 7, 8], 
    [0, 3, 6], [1, 4, 7], [2, 5, 8], 
    [0, 4, 8], [2, 4, 6]             
  ];

  for(combinacion of combinacionesGanadoras)
  {
    const [a,b,c] = combinacion;
    if(tablero[a]===turno && tablero[b]===turno && tablero[c]===turno)
    {   
        let celda1 = celdaIndice(a);
        let celda2 = celdaIndice(b);
        let celda3 = celdaIndice(c);
        
        celda1.style.background = "green";
        celda2.style.background = "green";
        celda3.style.background = "green";

        juegoTerminado = true;
        if(turnoJ)
        {
            infoTurno.innerText = `${inputJugador1.value} (${turno}) ha ganado!`;
        }
        else
        {
            infoTurno.innerText = `${inputJugador2.value} (${turno}) ha ganado!`;
        }
        
        return;
    }
  }

}

function celdaIndice(indice)
{
    let celda;
    celdas.forEach((elemento)=>{
        if(elemento.id === `celda${indice + 1}`) {
            celda = elemento;
        }
    })

    return celda;
}

function reiniciar()
{
    tablero = [" ", " ", " " , " ", " ", " ", " ", " ", " "];
    juegoTerminado = false;
    turnoJ = true;
    turno = "X";
    infoTurno.innerText = "";
    celdas.forEach(celda => {
        celda.innerText = "";
        celda.style.background = "";
    });
}

document.addEventListener("DOMContentLoaded", () => {

  botonEmpezar.addEventListener("click", (event)=>{
    event.preventDefault();

    if (juegoIniciado) {
        reiniciar();
        iniciarJuego();
    }else{
        let jugador1 =inputJugador1.value.trim();
        let jugador2 = inputJugador2.value.trim();

        if(!jugador1 || !jugador2){
            alert("Por favor, ingrese los nombres de ambos jugadores.")
        }else{
            
            console.log("Jugadores:", jugador1, jugador2);
            alert(`¡Bienvenidos ${jugador1} y ${jugador2}! Comencemos a jugar.`);
        
            inputJugador1.disabled=true;
            inputJugador2.disabled=true;

            botonEmpezar.innerText = "Revancha";
            juegoIniciado = true;
            seccionTurno.classList.remove("oculto");

            iniciarJuego();
        }
    }
    });

    tabla.addEventListener("click", manjearJugada);

    btnJugador1X.addEventListener("click", () => {
        btnJugador1X.disabled =true;
        btnJugador2Y.disabled =true;
        btnJugador2X.disabled =false;
        btnJugador1Y.disabled =false;
    });
    btnJugador1Y.addEventListener("click", () => {
        btnJugador1X.disabled =false;
        btnJugador2Y.disabled =false;
        btnJugador2X.disabled =true;
        btnJugador1Y.disabled =true;
    });
    btnJugador2X.addEventListener("click", () => {
        btnJugador1X.disabled =false;
        btnJugador2Y.disabled =false;
        btnJugador2X.disabled =true;
        btnJugador1Y.disabled =true;
    });
    btnJugador2Y.addEventListener("click", () => {
        btnJugador1X.disabled =true;
        btnJugador2Y.disabled =true;
        btnJugador2X.disabled =false;
        btnJugador1Y.disabled =false;
    });
});


btnJugador1X.disabled =true;
btnJugador2Y.disabled =true;




