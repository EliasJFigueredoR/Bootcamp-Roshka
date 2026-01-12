const celdas = document.querySelectorAll(".celda")
const inputJugador1 = document.getElementById("jugador1")
const botonEmpezar = document.getElementById("empezar");
const infoTurno = document.getElementById("indicadorTurno");
const seccionTurno = document.getElementById("InfoTurno");
const tabla = document.querySelector("table");
const combinacionesGanadoras = [
    [0, 1, 2], [3, 4, 5], [6, 7, 8], 
    [0, 3, 6], [1, 4, 7], [2, 5, 8], 
    [0, 4, 8], [2, 4, 6]             
  ];

let juegoIniciado = false;
let juegoTerminado = false;
let turnoJ = true;
let turno = "X";
let tablero = [" ", " ", " " , " ", " ", " ", " ", " ", " "];
let tableroPuntos = [1, 1, 1, 1, 1, 1, 1, 1, 1];


function imprimirTurno() {
    infoTurno.innerText = `Es tu turno ${inputJugador1.value} (${turno})`;
}

function iniciarJuego()
{
    const azar = Math.random();

    if (azar < 0.5) {
        turnoJ = true;
        tableroPuntos[5] = tableroPuntos + 1;
        
    } else {
        turnoJ = false;
        jugadaMaquina();
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
    tableroPuntos[indice] = 0;
    celdaClickeada.innerText = turno;
    revaluar();
    
    if(verificarGanador())
    {
        return;
    }

    turnoJ = !turnoJ; 
    if (turno === "X") {
        turno = "O";
    } else {
        turno = "X";
    }

    jugadaMaquina();
}

function jugadaMaquina()
{
    let maxPunto = Math.max(...tableroPuntos);
    let indiceMax = tableroPuntos.indexOf(maxPunto);

    const celda = celdaIndice(indiceMax);
    tablero[indiceMax] = turno;
    tableroPuntos[indiceMax] = 0;
    celda.innerText = turno;
    revaluar();

    if(verificarGanador())
    {
        return;
    }

    turnoJ = !turnoJ; 
    if (turno === "X") {
        turno = "O";
    } else {
        turno = "X";
    }
}

function revaluar()
{
    for(combinacion of combinacionesGanadoras)
    {
        const [a,b,c] = combinacion;
        if(tablero[a]===turno && tablero[b]===turno && tablero[c]=== " ")
        {
            tableroPuntos[c]= tableroPuntos[c] + 40;
        }else if(tablero[a]===" " && tablero[b]===turno && tablero[c]===turno)
        {
            tableroPuntos[a]= tableroPuntos[a] + 40;
        }else if(tablero[a]===turno && tablero[b]===" " && tablero[c]===turno)
        {
            tableroPuntos[b]= tableroPuntos[b] + 40;
        }else if(tablero[a]===turno && tablero[b]===" " && tablero[c]=== " ")
        {
            tableroPuntos[b]= tableroPuntos[b] + 10;
            tableroPuntos[c]= tableroPuntos[c] + 10;
        }else if(tablero[a]===" " && tablero[b]===turno && tablero[c]=== " ")
        {
            tableroPuntos[a]= tableroPuntos[a] + 10;
            tableroPuntos[c]= tableroPuntos[c] + 10;
        }else if(tablero[a]===" " && tablero[b]=== " " && tablero[c]=== turno)
        {
            tableroPuntos[a]= tableroPuntos[a] + 10;
            tableroPuntos[b]= tableroPuntos[b] + 10;
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

function verificarGanador() {
  
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
                infoTurno.innerText = `La maquina ha ganado!`;
            }
            return juegoTerminado;
        }
    }

    if(!tablero.includes(" "))
    {
        juegoTerminado = true;
        infoTurno.innerText = "¡Empate! El juego ha terminado.";
        return juegoTerminado;
    }
    
    return juegoTerminado;
}

function reiniciar()
{
    tablero = [" ", " ", " " , " ", " ", " ", " ", " ", " "];
    tableroPuntos = [1, 1, 1, 1, 1, 1, 1, 1, 1];
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

        if(!jugador1){
            alert("Por favor, ingrese su nickname.")
        }else{
            alert(`¡Bienvenido ${jugador1}! Comencemos a jugar.`);
        
            inputJugador1.disabled=true;

            botonEmpezar.innerText = "Revancha";
            juegoIniciado = true;
            seccionTurno.classList.remove("oculto");

            iniciarJuego();
        }
    }
    });

    tabla.addEventListener("click", manjearJugada);
});





