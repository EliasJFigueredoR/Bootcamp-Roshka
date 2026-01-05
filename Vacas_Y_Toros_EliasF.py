import random

def convertirString(Lista):
    Numero = ""
    for i in range(4):
        Numero= Numero + str(Lista[i])
    return Numero

def generar_numero():
    Lista = []
    while len(Lista) != 4:
        A = random.randint(0,9)
        if A in Lista:
            continue
        if (A == 0) and len(Lista) <= 3: 
            continue
        Lista.append(A)
    print(Lista)
    return Lista

def validar_Numero(Numero):
    if not Numero.isdigit() or len(Numero) != 4:
        return False
    
    for d1 in Numero:
        contador = 0 
        for d2 in Numero:
            if d1 == d2:
                contador = contador + 1
        if contador > 1:
            return False
        
    return True

def vacas_toros(Lista, NumeroJugador):
    Ntoros = 0 
    NVacas = 0

    for i in range(4):
        for j in range(4):
            if Lista[i] == int(NumeroJugador[j]) and i==j:
                Ntoros= Ntoros + 1 
                continue
            elif Lista[i] == int(NumeroJugador[j]):
                NVacas= NVacas + 1

    print(f"{NVacas} vacas, {Ntoros} Toros")

    return Ntoros

    

Ntoros = 0 
Lista = generar_numero()
#Lista = [1,2,3,4]
Numero = convertirString(Lista)

print("\nAcabo de pensar en un numero, adivina cual es...")
NumeroJugador = input("Ingrese el numero:")
if validar_Numero(NumeroJugador) and vacas_toros(Lista, NumeroJugador) == 4:
    print("\n¡Felicidades! El número secreto era:", Numero)
else:
    while Ntoros < 4:
        NumeroJugador = input("\nPor favor, intente de nuevo:")
        if validar_Numero(NumeroJugador): 
            Ntoros = vacas_toros(Lista, NumeroJugador)
            if Ntoros == 4:
                print("\n¡Felicidades! El número secreto era:", Numero)
    


