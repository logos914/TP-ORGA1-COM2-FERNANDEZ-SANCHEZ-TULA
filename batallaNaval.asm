
; Grosero comienzo de este TP
; 

org 100h 
jmp inicio  


presentacionArte db "  ____        _        _ _         _   _                  _  ",  10,13
db                  " | __ )  __ _| |_ __ _| | | __ _  | \ | | __ ___   ____ _| |" ,  10,13 
db                  " |  _ \ / _` | __/ _` | | |/ _` | |  \| |/ _` \ \ / / _` | |" ,  10,13  
db                  " | |_) | (_| | || (_| | | | (_| | | |\  | (_| |\ V / (_| | |" ,  10,13  
db                  " |____/ \__,_|\__\__,_|_|_|\__,_| |_| \_|\__,_| \_/ \__,_|_|" ,  10,13 
db                  " ===========================================================" ,  10,13 ,10,13, 10,13 


  titulo db " Mar jugador 1 || Mar jugador 2 ", 10,13
matriz01 db "***************||***************", 10,13
matriz02 db "***************||***************", 10,13
matriz03 db "***************||***************", 10,13
matriz04 db "***************||***************", 10,13
matriz05 db "***************||***************", 10,13
matriz06 db "***************||***************", 10,13
matriz07 db "***************||***************", 10,13
matriz08 db "***************||***************", 10,13
matriz09 db "***************||***************", 10,13
matriz10 db "***************||***************", 10,13
matriz11 db "***************||***************", 10,13
tablero  db "Puntaje:       ||Puntaje:      $", 10,13 
exitoso   db 73h, "$" 
fracasoso db 6Eh, "$" 


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

filas db 7
ComparaPosicion db 0
MueveJugador db 0




                                                                

; ACA EL PROGRAMA




inicio:
; Pintar de cyan el encabezado ASCII   
mov ah, 09h
mov al, [presentacionArte]
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

; pintar de verde el titulo del tablero     
mov ah, 09h
mov al, [titulo]
mov bh, 0
mov bl, 1010b
mov cx, 80
mov dh, 7
mov dl, 9
int 10h     


; mover cursor donde estaria el el mar    
mov dh, 9
mov dl, 0
mov bh, 0
mov ah, 2
int 10h


; pintar de azul el mar 
mov ah, 09h
mov al, 0
mov bh, 0
mov bl, 1001b
mov cx, 880
mov dh, filas
mov dl, 0
int 10h


;mover cursor al cero para escribir caracteres 
mov dh, 0
mov dl, 0
mov bh, 0
mov ah, 2
int 10h
	

; Mostrar el tablero	
mov dx, OFFSET presentacionArte  
mov bh, 0
mov bl, 03h
mov ah,9
int 21h       


mov dh, 9
mov dl, 0 
mov ah, 02h
int 10h
  
mov dh, 9
mov dl, 0        
mov ah, 1
int 10h 
       
        

jmp mueveJug1


; ESTE LOOP ESCUCHA QUE TECLA PRESIONA EL JUGADOR 1
mueveJug1:   
        mov ah, 00h
        int 16h
                        
        cmp al, 78h     ;Compara que tecla se presiono, si es 's'    
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
 ; !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CAMBIAR LAS SUB DE ESTE
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
                    

 


; CALCULA QUE MOVIMIENTOS SON POSIBLE PARA EL JUGADOR1

   
            
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
        
DisparoJugador1:
        jmp FinDeTurno   



; CALCULA QUE MOVIMIENTOS SON POSIBLE PARA EL JUGADOR2

   
            
DeteccionDerechaJug2:
        mov ComparaPosicion, dl
        sub ComparaPosicion, 31         ; No puede ir a derecha de la columna 31
        JS MoverDerecha                 ; si la diferencia es negativa, se puede mover a derecha
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

DisparoJugador2:
        jmp FinDeTurno





        

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





; Subrutinas de cambio de turno

SigueMoviendo:
    cmp MueveJugador,0
    je  mueveJug1
    jmp mueveJug2
    ret


FinDeTurno:
    cmp MueveJugador,0
    je  AhoraMueveJugador2
    jmp AhoraMueveJugador1
    ret


AhoraMueveJugador1:

    mov MueveJugador,0
    mov dh, 9     ; Ubicar cursor en posicion 0,0 de su matriz
    mov dl, 0        
    mov ah, 02h
    int 10h 
    jmp mueveJug1
    ret
    

AhoraMueveJugador2:
    mov MueveJugador,1
    mov dh, 9     ; Ubicar cursor en posicion 0,11 de su matriz
    mov dl, 17        
    mov ah, 02h
    int 10h
    jmp mueveJug2
    ret