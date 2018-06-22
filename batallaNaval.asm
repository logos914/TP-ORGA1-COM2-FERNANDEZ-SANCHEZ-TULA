org 100h 
jmp inicio  


presentacionArte db "  ____        _        _ _         _   _                  _  ",  10,13
db                  " | __ )  __ _| |_ __ _| | | __ _  | \ | | __ ___   ____ _| |" ,  10,13 
db                  " |  _ \ / _` | __/ _` | | |/ _` | |  \| |/ _` \ \ / / _` | |" ,  10,13  
db                  " | |_) | (_| | || (_| | | | (_| | | |\  | (_| |\ V / (_| | |" ,  10,13  
db                  " |____/ \__,_|\__\__,_|_|_|\__,_| |_| \_|\__,_| \_/ \__,_|_|" ,  10,13 
db                  " ===========================================================" ,  10,13 ,10,13, 10,13 


  titulo db "== Jugador 1 ==||== Jugador 2 ==", 10,13
tablero  db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
         db "***************||***************", 10,13
puntaje  db "Puntaje:00     ||Puntaje:00    $", 10,13 

exitoso   db 53h  ; Caracteres que representa el exito   "S"
fracasoso db 4Eh  ; Caracteres que representa el fracaso "N"
barquito db "#"   ; Caracteres que un fragmento de barco "#" 

ComparaPosicion db 0


PuntajeJugador1 db "00$"
PuntajeJugador2 db "00$"

MensajeVictoriaCadena db "Ha ganado el jugador $"
Jugando db 1
MueveJugador db 0











botes_Jug1 db "**######*******" 
           db "***************" 
           db "****##*********" 
           db "***************" 
           db "************#**" 
           db "************#**" 
           db "*#**********#**"  
           db "*#**********#**" 
           db "*#*****###*****" 
           db "*#*************" 
           db "*#*************" 


botes_Jug2 db "**##***********" 
           db "***************" 
           db "***************" 
           db "****#**********" 
           db "****#*****####*" 
           db "#***#**********" 
           db "#******#*******"  
           db "#******#*******" 
           db "#******#*******" 
           db "#******#*******" 
           db "#******#*******" 






                                                                



















; ACA EL PROGRAMA
inicio:
; Pintar de cyan usando un carac NULL los espacios que ocuparia el encabezado ASCII   
mov ah, 09h
mov al, 00h
mov bh, 0
mov bl, 1011b 
mov cx, 480
int 10h       
         
; mover cursor donde estaria el titulo del tablero    
mov dh, 8
mov dl, 0
mov bh, 0
mov ah, 2
int 10h

; pintar de verde usando un carac NULL los espacios que ocuparia el titulo del tablero     
mov ah, 09h
mov al, 00h
mov bh, 0
mov bl, 1010b
mov cx, 80
mov dh, 7
mov dl, 9
int 10h     


; mover cursor donde estaria el mar    
mov dh, 9
mov dl, 0
mov bh, 0
mov ah, 2
int 10h


; pintar de azul usando un carac NULL los espacios que ocuparia el mar 
mov ah, 09h
mov al, 0
mov bh, 0
mov bl, 1001b
mov cx, 880
mov dh, 7
mov dl, 0
int 10h





;mover cursor al cero para escribir caracteres que necesitamos que sean visibles
mov dh, 0
mov dl, 0
mov bh, 0
mov ah, 2
int 10h
	

; Mostrar el arte ascii, el titulo y el tablero completo	
mov dx, offset[presentacionArte]  
mov bh, 0
mov bl, 03h
mov ah,9
int 21h       


mov dh, 14   
mov dl, 7       
call setCursor 

;Hacer que el cursor parpadee en la posicion en la que se encuentre
mov ch, 0
mov cl, 7 
mov bl, 1010b           
mov ah, 1
int 10h 
       
        

jmp mueveJug1




















; ESTE LOOP ESCUCHA QUE TECLA PRESIONA EL JUGADOR 1
mueveJug1:   
        mov ah, 00h
        int 16h
                        
        cmp al, 78h     ;Compara que tecla se presiono, si es 'x'    
        je DetectarAbajoJug1
                
        cmp al, 77h     ;Compara que tecla se presiono, si es 'w'    
        je DetectarArribaJug1
                
        cmp al, 61h     ;Compara que tecla se presiono, si es 'a'    
        je DeteccionIzquierdaJug1
                
        cmp al, 64h     ;Compara que tecla se presiono, si es 'd'    
        je DeteccionDerechaJug1
        
        cmp al, 73h     ;Compara que tecla se presiono, si es 's'    
        je DisparoJugador1
                
       
                
        jmp mueveJug1        ;Sino, no hace nada y vuelve a pedir una tecla  
                    
      
  
 ; ESTE LOOP ESCUCHA QUE TECLA PRESIONA EL JUGADOR 2   

mueveJug2:   
        mov ah, 00h
        int 16h
                        
        cmp al, 78h     ;Compara que tecla se presiono, si es 'x'    
        je DetectarAbajoJug2
                
        cmp al, 77h     ;Compara que tecla se presiono, si es 'w'    
        je DetectarArribaJug2
                
        cmp al, 61h     ;Compara que tecla se presiono, si es 'a'    
        je DeteccionIzquierdaJug2
                
        cmp al, 64h     ;Compara que tecla se presiono, si es 'd'    
        je DeteccionDerechaJug2
        
        cmp al, 73h     ;Compara que tecla se presiono, si es 's'    
        je DisparoJugador2        
       
                
        jmp mueveJug2        ;Sino, no hace nada y vuelve a pedir una tecla  
                 
; CALCULADORA DE QUE MOVIMIENTOS SON POSIBLE PARA EL JUGADOR1       
DeteccionDerechaJug1:
        mov ComparaPosicion, dl
        sub ComparaPosicion, 14 ; No puede ir a derecha de la columna 14
        JS MoverDerecha         ; si la diferencia es negativa, se puede mover a derecha
        jmp mueveJug1
        ret            
            
            

DeteccionIzquierdaJug1:
        cmp dl, 0               ; No puede ir a izquierda si ya esta en columna 0
        je mueveJug1            ; si son iguales, volver al loop
        jmp MoverIzquierda      ; si no son iguales, permite el movimiento
        ret   
    
    
    
DetectarArribaJug1:
        mov ComparaPosicion, dh    ; No puede ir hacia arriba si ya esta en fila 10 (0A)
        sub ComparaPosicion, 10    ; Si la resta es positiva permite subir
        JNS MoverArriba
        jmp mueveJug1
        ret              

 
 
DetectarAbajoJug1:
        mov ComparaPosicion, dh    ; No puede ir hacia abajo si ya esta en fila 19 (0A)
        sub ComparaPosicion, 19    ; Si la resta es negativa permite bajar
        JS MoverAbajo
        jmp mueveJug1
        ret


            
            
            












; CALCULA QUE MOVIMIENTOS SON POSIBLE PARA EL JUGADOR2      
DeteccionDerechaJug2:
        mov ComparaPosicion, dl
        sub ComparaPosicion, 31         ; No puede ir a derecha de la columna 31
        js  MoverDerecha                ; si la diferencia es negativa, se puede mover a derecha
        jmp mueveJug2
        ret            
            
            



DeteccionIzquierdaJug2:
        mov ComparaPosicion, dl         ; No puede ir a izquierda si ya esta en columna 17
        sub ComparaPosicion, 17
        je  mueveJug2                    ; si son iguales, volver al loop
        jmp MoverIzquierda               ; si no son iguales, permite el movimiento
        ret   
        
                
        
DetectarArribaJug2:
        mov ComparaPosicion, dh    ; No puede ir hacia arriba si ya esta en fila 10 (0A)
        sub ComparaPosicion, 10    ; Si la resta es positiva permite subir
        JNS MoverArriba
        jmp mueveJug2
        ret              
       
       
       
DetectarAbajoJug2:
        mov ComparaPosicion, dh    ; No puede ir hacia abajo si ya esta en fila 19 (0A)
        sub ComparaPosicion, 19    ; Si la resta es negativa permite bajar
        JS MoverAbajo
        jmp mueveJug2
        ret














; DISPARO DEL JUGADOR 2 (ES UNA COMPARACION DE CARAC, QUE DETERMINA EL EXITO O EL FRACASO DEL DISPARO)
DisparoJugador1:

        
 

        mov ax, 0                     ; AX en cero (lo necesitamos para acumular o tener resultado de 
                                      ; operaciones matematicas)
       
        mov al, 15                    ; En AL agregamos la cantidad de unidades encontradas en una sola fila 
                                      ; de la matriz de mar individual
        
        sub dh,9                      ; como vamos a comparar la posicion del cursor en el momento actual con 
                                      ; respecto a la posicion absoluta en la matriz, quitamos las 9 posiciones
                                      ; extras que tenemos por culpa del encabezado (el arte + el titulo)
                                        
        mul dh                        ; multiplicamos la cantidad de filas que nos desplazamos hacia abajo.
                                      ; Estamos contando filas usando la posicion. La fila cero es en realidad
                                      ; la fila actual donde estamos parados porque esta incompleta.
                                      ; 
                                      ; De esta forma en AX ahora tenemos la cantidad de elementos de la matriz
                                      ; que estamos corridos con respecto a la fila actual.
        
        add dh,9                      ; Devolvemos el registro del cursor a su posicion original (sumando los 9
                                      ; requeridos por culpa del encabezado (el arte + el titulo).
       
       
        mov bl, dl                    ; Movemos el valor de la posicion de la columna actual del cursor hacia BL
        mov bh,0                      ; Ponemos BH en cero.
                                      ; Hicimos todo lo anterior porque necesitamos sumarle el valor de la posicion
                                      ; actual del cursor al registro AX. Pero BL, DL son registros de 8bits y AX
                                      ; es de 16 bits. Con las dos instruccciones anteriores, convertirmos la pos
                                      ; del cursor en un registro de 16bits
        
        
        add ax, bx                    ; Realizamos la suma y ahora si tenemos la cantidad de elementos de la matriz
                                      ; que estamos corridos con respecto a la fila y columna actual.
        
        mov bx,0                      ; No necesitamos mas a BX, asi que lo ponemos en cero. Nuestro valor esta en AX
        
        
        MOV di,ax                     ; Nuestro valor en AX lo pasamos al registro DI. Porque necesitamos realizar un
                                      ; direccionamiento indirecto
                                      
                                      
        mov al, botes_Jug2[di]        ; Realizamos nuestro direccionamiento indirecto y obtenemos el caracter que se
                                      ; en la matriz de mar individual.
                                      
        cmp al, exitoso
        je SigueMoviendo 
        
        cmp al, fracasoso
        je SigueMoviendo                               
        
        cmp al , barquito             ; Se compara el caracter obtenido con el que corresponde a un segmento de barco.
        je  acertar                   ; Si coinciden salta a la subrutina acertar
        jmp errar                     ; De lo contrario se dirige a la subrutina errar.

        
        
; DISPARO DEL JUGADOR 1 (ES UNA COMPARACION DE CARAC, QUE DETERMINA EL EXITO O EL FRACASO DEL DISPARO)        
DisparoJugador2:




        mov ax, 0
        
        mov al, 15
        sub dh,9
        mul dh
        add dh,9
       
       
        mov bl, dl
        sub bl, 17
        mov bh,0      
        add ax, bx
        
        mov bx,0
        
        
        MOV di,ax
        mov al, botes_Jug1[di]
        
        cmp al, exitoso
        je SigueMoviendo 
        
        cmp al, fracasoso
        je SigueMoviendo
        
        cmp al , barquito 
        je  acertar
        jmp errar

       
       



; APARTADO GRAFICO DEL DISPARO (CAMBIA EL CARAC EN PANTALLA Y SOLICITA EL FINAL DEL TURNO)
acertar:                ; pintar de amarillo el impacto en el marP
mov al, exitoso
mov bl, 1100b
call CambiarValorMatriz
call pintarDisparo
call AgregarPuntaje
jmp FinDeTurno  


errar:                  ; pintar de rojo el fallido en el mar 
mov al, fracasoso
mov bl, 0111b
call CambiarValorMatriz
call pintarDisparo
jmp FinDeTurno       
    

pintarDisparo PROC near ; pintar el disparo en el mar
mov ah, 09h
mov bh, 0
mov cx, 1
int 10h
ret
pintarDisparo endp

























; subrutinas  (o loops) que se encargan del absoluto movimiento del cursor

MoverDerecha:
        add dl, 1           
        call SetCursor           
        jmp SigueMoviendo
        ret 

        
MoverIzquierda:
        sub dl, 1           
        call SetCursor      
        jmp SigueMoviendo
        ret                    
        
MoverArriba:
        sub dh, 1           ;para reposicionar el cursor fila
        call SetCursor      ;llamo al procedimiento para setear cursor
        jmp SigueMoviendo
        ret        
                
MoverAbajo:
        add dh, 1           ;para reposicionar el cursor fila
        call SetCursor      ;llamo al procedimiento para setear cursor
        jmp SigueMoviendo
        ret             

SetCursor proc              
        mov ah, 02h
        mov bh, 00
        int 10h             ;Analizar esta int, que es la que posiciona el cursor
        ret

SetCursor endp 
















; Subrutinas de turnos de jugadores

SigueMoviendo:          ; En el caso de que el movimiento finalizo 
    cmp MueveJugador,0  ; o es invalido pero continua el turno en el jugador actual
    je  mueveJug1
    jmp mueveJug2
    ret


FinDeTurno:
    call CalcularPuntaje
    
    cmp Jugando,0
    je terminarJuego
    
    cmp MueveJugador,0     ; En el caso de que el movimiento finalizo
    je  AhoraMueveJugador2 ; y con ello tambien el turno del jugador actual
    jmp AhoraMueveJugador1
    
    terminarJuego:
    ret


AhoraMueveJugador1:         ;Realiza las acciones para el comienzo del turno del jugador 1
    mov MueveJugador,0
    mov dh, 14     
    mov dl, 7        
    call SetCursor
    jmp mueveJug1
    ret
    

AhoraMueveJugador2:         ;Realiza las acciones para el comienzo del turno del jugador 2
    mov MueveJugador,1
    mov dh, 14     
    mov dl, 24        
    call SetCursor
    jmp mueveJug2
    ret
    
    
    
    







AgregarPuntaje proc 
    cmp MueveJugador,0  
    je  sumarJug1
    jmp sumarJug2
      
    sumarJug1:
    mov ah,offset[PuntajeJugador1+0]
    mov al,offset[PuntajeJugador1+1]
    sub ah,30h     
    sub al,30h
    
    mov dh,ah
    mov ah,0
    mov dl,al
    
    mov ax,10
    mul dh
    mov dh,0
    add ax,dx
    
    cmp ax,9
    je pasarAdiez1
    
    cmp ax,19
    je pasarAveinte1
    
    inc ax
    
    mov ah,offset[PuntajeJugador1+0]
        cmp al,10
    ja restarDiez1
    jmp continuarNoCambiaDecena1                
                 
    restarDIez1:
    sub al,10
    
    continuarNoCambiaDecena1:               
    add al,30h
    jmp escribirPuntosEnMem1
    
    pasarAdiez1:
    mov ah,31h
    mov al,30h
    jmp escribirPuntosEnMem1
    
    pasarAveinte1:
    call MensajeVictoria
    mov ah,32h
    mov al,30h
    jmp escribirPuntosEnMem1
    
escribirPuntosEnMem1:
    mov offset[PuntajeJugador1+0],ah
    mov offset[PuntajeJugador1+1],al
    jmp finalAgregarPuntaje
    
    
    
    sumarJug2:
    mov ah,offset[PuntajeJugador2+0]
    mov al,offset[PuntajeJugador2+1]
    sub ah,30h     
    sub al,30h
    
    mov dh,ah
    mov ah,0
    mov dl,al
    
    mov ax,10
    mul dh
    mov dh,0
    add ax,dx
    
    cmp ax,9
    je pasarAdiez2
    
    cmp ax,19
    je pasarAveinte2
    
    inc ax
    
    mov ah,offset[PuntajeJugador2+0]
    
    cmp al,10
    ja restarDiez2
    jmp continuarNoCambiaDecena2                
                 
    restarDIez2:
    sub al,10
    
    continuarNoCambiaDecena2:               
    add al,30h
    jmp escribirPuntosEnMem2
    
    pasarAdiez2:
    mov ah,31h
    mov al,30h
    jmp escribirPuntosEnMem2
    
    pasarAveinte2:
    call MensajeVictoria
    mov ah,32h
    mov al,30h   
    jmp escribirPuntosEnMem2
    
    
    
    escribirPuntosEnMem2:  
    mov offset[PuntajeJugador2+0],ah
    mov offset[PuntajeJugador2+1],al
    jmp finalAgregarPuntaje
    
    
    
    
    finalAgregarPuntaje:    
    ret
    
AgregarPuntaje endp



CalcularPuntaje proc
    
; Mostrar en el puntaje en el tablero
mov dh, 20   
mov dl, 8       
call setCursor	
mov dx, offset[PuntajeJugador1]  
mov bh, 0
mov bl, 03h
mov ah,9
int 21h     
    
    
; Mostrar en el puntaje en el tablero
mov dh, 20   
mov dl, 25       
call setCursor	
mov dx, offset[PuntajeJugador2]  
mov bh, 0
mov bl, 03h
mov ah,9
int 21h     
    
ret
CalcularPuntaje endp



; APARTADO PARA CAMBIAR LOS VALORES DE MEMORIA DE LAS MATRICES DE MAR ORIGINALES
CambiarValorMatriz proc              
        cmp MueveJugador,0  
        je  cambiaValorJug2
        
        mov botes_Jug1[di],al
        jmp FinCambiarValorMatriz
        
        
        cambiaValorJug2:
        mov botes_Jug2[di],al
        jmp FinCambiarValorMatriz
        
        FinCambiarValorMatriz:
        ret

CambiarValorMatriz endp

cmp Jugando,1
jne finalMensajeVictoria


MensajeVictoria proc
mov di,dx
mov dh, 22   
mov dl, 0       
call setCursor	
mov dx, offset[MensajeVictoriaCadena]  
mov bh, 0
mov bl, 03h
mov ah,9
int 21h

inc MueveJugador
add MueveJugador,30h
mov ah, 09h
mov al, MueveJugador
mov bh, 0
mov cx, 1
int 10h
mov dx,di
call setCursor
sub Jugando,1

finalMensajeVictoria:   
ret    
    
       
MensajeVictoria endp