
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
       
        

jmp main

main:   mov ah, 00h
        int 16h
                        
        cmp al, 110     ;Compara que tecla se presiono, si es 'n'    
        je Down
                
        cmp al, 121     ;Compara que tecla se presiono, si es 'y'    
        je Up
                
        cmp al, 103     ;Compara que tecla se presiono, si es 'g'    
        je Left
                
        cmp al, 106     ;Compara que tecla se presiono, si es 'j'    
        je Right
                
       
                
        jmp main        ;Sino, no hace nada y vuelve a pedir una tecla  
                    
      
   
    
Right:
        add dl, 1           ;para reposicionar el cursor columna
        call SetCursor      ;llamo al procedimiento para setear cursor
        jmp main
        ret

Left:
        sub dl, 1           ;para reposicionar el cursor columna
        call SetCursor      ;llamo al procedimiento para setear cursor
        jmp main
        ret

Up:
        sub dh, 1           ;para reposicionar el cursor fila
        call SetCursor      ;llamo al procedimiento para setear cursor
        jmp main
        ret

Down:
        add dh, 1           ;para reposicionar el cursor fila
        call SetCursor      ;llamo al procedimiento para setear cursor
        jmp main
        ret
      
      
      
SetCursor proc              
        mov ah, 02h
        mov bh, 00
        int 10h             ;Analizar esta int, que es la que posiciona el cursor
        ret

SetCursor endp


