#Marta Oliveira 97613
#Mariana Silva 98392
import asyncio
import getpass
import json
import os
from copy import deepcopy
import websockets


# Next 4 lines are not needed for AI agents, please remove them from your code!
#import pygame
#pygame.init()
#program_icon = pygame.image.load("data/icon2.png")
#pygame.display.set_icon(program_icon)

class Tetris:
    vectores_possibilities=[[0,1],[0,2],[0,3],[0,4],
    [1,0],[1,1],[1,2],[1,3],[1,4],
    [2,0],[2,1],[2,2],[2,3],[2,4],
    [3,0],[3,1],[3,2],[3,3],[3,4],
    [4,0],[4,1],[4,2],[4,3],[4,4],
    [-1,0],[-1,1],[-1,2],[-1,3],[-1,4],
    [-2,0],[-2,1],[-2,2],[-2,3],[-2,4],
    [-3,0],[-3,1],[-3,2],[-3,3],[-3,4],
    [-4,0],[-4,1],[-4,2],[-4,3],[-4,4]]


async def agent_loop(server_address="localhost:8000", agent_name="97613"):
    async with websockets.connect(f"ws://{server_address}/player") as websocket:

        # Receive information about static game properties
        await websocket.send(json.dumps({"cmd": "join", "name": agent_name}))

        while True:
            try:
                state = json.loads(
                    await websocket.recv()
                )  # receive game update, this must be called timely or your game will get out of sync with the server

                piece=None
                if 'piece' in state:
                    piece = state['piece']
                    game =state['game']
              
                new_piece=False
                while piece== None:

                    state = json.loads(
                        await websocket.recv()
                    )
                    if 'piece' in state: 
                        piece = state['piece']
                        game =state['game']
                    #piece_type=piece_exists(piece)
                    #print(piece)
                    type = piece_exists(piece)
                    if(piece!=None):
                        best_position=all_possibilities(piece,game,type)
                        piece_rotaded=rotate(piece,best_position[1],type)
                        list_keys=choose_key(piece_rotaded,best_position[0], best_position[1])
                        new_piece=True
                    #print(list_keys)
                    #print(best_position)
                    #print("\n")

                if new_piece == True:
                    for keys in list_keys:
                        state = json.loads(
                            await websocket.recv()
                        )

                        await websocket.send (
                            json.dumps({"cmd":"key","key":keys})
                        )
                

            except websockets.exceptions.ConnectionClosedOK:
                print("Server has cleanly disconnected us")
                return
                #Testes                
                #print(height(game))
                #print(aggregate_height(game))
                #print(columns_height(game))
                #print(Bumpiness(game))
                #print(number_of_holes(game))

#"matriz"que retorna uma lista que tem 1's em posições que estao ocupadas pelos blocos
def grid(game):
    matriz = [[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],
    [0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],
    [0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],
    [0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],
    [0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],
    [0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],
    [0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0]] # lista vazia
    for i in game:
        x=i[0]
        y=i[1]
        matriz[y][x] = 1 #posição ocupada
        

    for i in range(len(matriz)):
        del matriz[i][0] #eliminar os blocos "azuis" que nao sao jogaveis

    return matriz

          
def get_all_possibilities_positions(piece):
    if(piece == None):
        return None

    positions=[piece]
    for vector in Tetris.vectores_possibilities:
        new_piece=[]
        possible = True
        for square in piece:
            x = square[0] + vector[0]
            y = square[1] + vector[1]
            if(x < 0 or x >= 6 or y >= 6 or y < 0):
                possible = False
                break
            else:
                new_piece+=[[x,y]]
                
        if possible == True:
            positions += [new_piece]

        new_piece=[]
        possible = True
        for square in piece:
            x = square[0] - vector[0]
            y = square[1] - vector[1]
            if(x < 0 or x >= 6 or y >= 6 or y < 0):
                possible = False
                break
            else:
                new_piece+=[[x,y]]
        if possible == True:
            positions += [new_piece]
    return positions

#ao jogar o jogo e fazer um print de cada peça obtemos as suas coordenadas. 
#Depois com a função get_all_possibilities_positions vamos obter as varias possibilidades de como a peça pode iniciar (somando vetores)
I = get_all_possibilities_positions([[2,2],[3,2],[4,2],[5,2]])
J = get_all_possibilities_positions([[2,1],[3,1],[2,2],[2,3]])
L = get_all_possibilities_positions([[2,1],[2,2],[2,3],[3,3]])
O = get_all_possibilities_positions([[3,3],[4,3],[3,4],[4,4]])
S = get_all_possibilities_positions([[2,1],[2,2],[3,2],[3,3]])
T = get_all_possibilities_positions([[4,2],[4,3],[5,3],[4,4]])
Z = get_all_possibilities_positions([[2,1],[1,2],[2,2],[1,3]])

ALL_PIECES=[I,L,S,T,J,O,Z]

def piece_exists(pieces):
    for tetrominoes in ALL_PIECES:
        if pieces in tetrominoes:
            if tetrominoes == I:
                return "I"
            elif tetrominoes==J:
                return "J"
            elif tetrominoes == L:
                return "L"
            elif tetrominoes==O:
                return "O"
            elif tetrominoes ==S:
                return "S"
            elif tetrominoes==T:
                return "T"
            elif tetrominoes==Z: 
                return "Z"


def columns_height(game):
    matriz=grid(game)
    colunas=0
    columns_height=[0, 0, 0, 0, 0, 0, 0, 0]
    for i in range(8): #eixo x
        for j in range(30): #eixo y
            if matriz[29-j][i]==1:
                colunas = j+1
            columns_height[i] = colunas
    return columns_height 


#We’ll want to minimize this value, because a lower aggregate height means that we can drop 
# more pieces into the grid before hitting the top of the grid.
def aggregate_height(game):
    aggregate_height=sum(columns_height(game))
    return aggregate_height
                
        
def number_of_holes(game):
    matrix=grid(game)
    columns_h=columns_height(game)
    nr_holes=0
    for i in range(8): #percorre o eixo x para cada coluna
        altura=columns_h[i]
        for j in range(altura,30):# para cima da altura maxima de cada coluna eliminar os 0's pq nao sao considerados buracos
            matrix[29-j][i]=-5 #espaços que nao são considerados buracos => por isso pode ser colocado um valor arbitario                            

    for i in range(8):
        for j in range(30):
            if matrix[29-j][i]==0: #tudo os outros 0's são buracos
                nr_holes+=1
        
    return nr_holes

#the the number of complete lines in a grid.
def complete_lines(game):
    matriz=grid(game)
    nr_lines=0
    for line in matriz:
        if sum(line)==9: #matriz grid tudo a 1's => linha completa no jogo
            nr_lines+=1
    return nr_lines

def Bumpiness(game):
    columns_h=columns_height(game)
    result=0
    for i in range(7):
        result+=abs(columns_h[i]-columns_h[i+1])
        
    return result

#para o calculo desta função usufruímos de informações deste website:
#https://codemyroad.wordpress.com/2013/04/14/tetris-ai-the-near-perfect-player/?fbclid=IwAR0ysYYxA2_lOfirvRJ5etTZ6UsEEGKM_c9XfKmimWM9h3hd-NvICGDkTts
def calculate_heuristic(linhas,bumpiness,nr_holes,aggregateheight):
    a=-0.510066
    b=0.76066
    c=-0.35663
    d=-0.18443

    heuristic_value=a* aggregateheight +b*linhas +c*nr_holes +d*bumpiness
    
    return heuristic_value

def translate(piece,translate):
    piece_copy=deepcopy(piece)
    for square in piece_copy:
        square[0]=square[0]+translate
    return piece_copy


#faz uma simulação da queda da peça
def fall_piece(game,piece):
    game_copy=deepcopy(game)
    piece_copy=deepcopy(piece)
    fall=game_copy #simulate fall
    #condição do y nao ultrupassar as dimensoes do jogo
    while piece_copy[0][1]<29 and piece_copy[1][1]<29 and piece_copy[2][1]<29 and piece_copy[3][1]<29 : #dimensoes do jogo 10*30 
        for square in piece_copy:
            square[1]=square[1]+1 #incrementar o y para a peça cair 
    
        for square in piece_copy: #este ciclos servem para verificar se existe ja uma peça na "base"
            if square in game: 
                for square in piece_copy: #colidiu: regredir na "queda" (uma iteração)
                    square[1]=square[1]-1 #interseta peça ja existente por isso ocupa o valor y anterior

                fall+= piece_copy #adicionar peça
                return fall 

    #caso nao haja colisao "bate" na base do jogo
    fall+= piece_copy
    return fall 

#quantas vezes a peça pode vir a rodar
def rotate_possibilities(type):
    nr_of_rotations=0
    if type == 'I':
        nr_of_rotations=2 #estado original + rotações possiveis
    elif type=='L'or type=='J' or type=='T':
        nr_of_rotations=4
    elif type == 'S'or type == 'Z':
        nr_of_rotations=2
    elif type == 'O':
        nr_of_rotations=1
    return nr_of_rotations


def rotate(piece, numOfrotations, type_piece):
    piece = deepcopy(piece)
    center = [0,0]     
    if type_piece == 'O':
        return piece
    
    elif type_piece == 'S':
        center = piece[1]
        if numOfrotations % 2 != 0:
            for square in piece:
                if square != center:        
                    if square[0] == center[0] and square[1] == center[1] - 1:
                        square[0] -= 1
                        square[1] += 2
                    if square[0] == center[0] + 1 and square[1] == center[1] + 1:
                        square[0] -= 1
        return piece
  
    elif type_piece == 'T':
        center = piece[1]
        for i in range(numOfrotations):
            for square in piece:
                if square != center:
                    if square[0] == center[0] and square[1] < center[1]:
                        square[0] += 1
                        square[1] += 1
                    elif square[0] > center[0] and square[1] == center[1]:
                        square[0] -= 1
                        square[1] += 1
                    elif square[0] == center[0] and square[1] > center[1]:
                        square[0] -= 1
                        square[1] -= 1
                    elif square[0] < center[0] and square[1] == center[1]:
                        square[0] += 1
                        square[1] -= 1
        return piece

    

    elif type_piece == 'Z':
        center = piece[2]
        if numOfrotations % 2 != 0:
            for square in piece:
                if square != center:        
                    if square[0] == center[0] and square[1] == center[1] - 1:
                        square[0] += 1
                        square[1] += 2
                    if square[0] == center[0] - 1 and square[1] == center[1] + 1:
                        square[0] += 1
        return piece
   


    elif type_piece == 'I':
        center = piece[2]                              
        if numOfrotations % 2 != 0:    
            for square in piece:
                if square != center:
                    if square[0] == center[0] - 2:
                        square[0] += 2
                        square[1] -= 2
                    elif square[0] == center[0] - 1:
                        square[0] += 1
                        square[1] -= 1
                    else:
                        square[0] -= 1
                        square[1] += 1
        return piece

    elif type_piece == 'L' or type_piece == 'J':
        if type_piece == 'L':
            center = piece[1]  
        elif type_piece == 'J':
            center = piece[2]      
        
        for i in range(numOfrotations):
            for square in piece:
                if square != center:
                    if square[0] == center[0] and square[1] < center[1]:
                        square[0] += 1
                        square[1] += 1 
                    elif square[0] > center[0] and square[1] == center[1]:
                        square[0] -= 1 
                        square[1] += 1 
                    elif square[0] == center[0] and square[1] > center[1]:
                        square[0] -= 1
                        square[1] -= 1
                    elif square[0] < center[0] and square[1] == center[1]:
                        square[0] += 1
                        square[1] -= 1
                    else:
                        if square[0] > center[0] and square[1] < center[1]:        # bloco que nao está diretamente ligado ao centro 
                            square[1] += 2
                        elif square[0] > center[0] and square[1] > center[1]:
                            square[0] -= 2
                        elif square[0] < center[0] and square[1] > center[1]:
                            square[1] -= 2
                        elif square[0] < center[0] and square[1] < center[1]:
                            square[0] += 2
        return piece
    
    

def all_possibilities(piece,game, type):  #estudar possibilidades da peça atual => cada posição que cada peça pode tomar e ver quais sao os melhores valores
    if piece == None:
        return None

    piece_original= piece
    nr_possibilities_rotate=rotate_possibilities(type)
    
    heuristics=[]
    piece_copy=[]

    #print(nr_possibilities_rotate)
    for i in range(nr_possibilities_rotate):
        heuristics_for_rotation =[] #cada possibilidade de rotação + peça original
        if i>0:
            piece=rotate(piece_original,i,type)
        x_min=10 
        #descobrir o menor valor de x
        for square in piece:
            if square[0]<x_min:
                x_min=square[0]
        
        translate_piece= 1 -x_min #saber a distancia ate ao "canto esquerdo" => o quao temos de andar para a esquerda
        #colocar peça no canto do jogo 
        piece=translate(piece,translate_piece)
        piece_copy.append(piece)
        while piece[0][0]<9 and piece[1][0]<9 and piece[2][0]<9 and piece[3][0]<9:#rodado uma vez para cada peça #cada possibilidade de queda
            #inicializar as nossas funções
            game_try=fall_piece(game,piece)
            nr_lines_complete=complete_lines(game_try)
            bump=Bumpiness(game_try)
            nr_holes=number_of_holes(game_try)
            agg_height=aggregate_height(game_try)
            points=calculate_heuristic(nr_lines_complete,bump,nr_holes,agg_height)
            heuristics_for_rotation.append(points)
            piece=translate(piece,1) #criar nova possibilidade, mover a peça para a direita 
            #para cada lista da heuristics => piece copy
        heuristics.append(heuristics_for_rotation) #[[......,.......,...........,.....],[.........,..........]] #Para a peça I p.e se fosse T=> 4 listas
    
    auxiliary=[]
    for lista in heuristics: #lista => heuristic[i]
        auxiliary.append(max(lista)) #[[max],[max]...]

    i=auxiliary.index(max(auxiliary))#[230,300..] => 1 => qual lista está o maximo 
    best_possibility=heuristics[i]
    maximo = auxiliary[i]
    best_position=best_possibility.index(maximo) #descobrir o indice do maximo => vê quanto a peça precisa de se movimentar para a direita: o maior valor do calculo da heuristica => corresponde à melhor posição onde a peça pode cair
    piece_copy=translate(piece_copy[i],best_position) #move a peça para a melhor posição que podem ter sido rodadas ou nao//i=1 peça rodou uma vez
    #i=> diz nos diretamente se a peça foi rodada e quantas vezes
    nr_rotates=i 

    return [piece_copy, nr_rotates]


#comparar peça atual com as cordenadas da "melhor posição"
#ajuda nos para a função keys, que movimento a peça tem de fazer
def compare_pieces(piece,best_position_piece):
    move=best_position_piece[0][0]-piece[0][0]
    return move

#que movimento a peça deve fazer para ir para a sua melhor posição
def choose_key(piece, best_position, nr_rotacoes):
    #[   T           T'    ]
    move = compare_pieces(piece, best_position)
    lista_keys=[]
    for i in range(nr_rotacoes):
        lista_keys += "w"
    
    if move < 0:
        key = "a"
    else:
        key = "d"
    
    for i in range(abs(move)):
        lista_keys += key
    
    lista_keys+='s'
    return lista_keys


# DO NOT CHANGE THE LINES BELLOW
# You can change the default values using the command line, example:
# $ NAME='arrumador' python3 client.py
loop = asyncio.get_event_loop()
SERVER = os.environ.get("SERVER", "localhost")
PORT = os.environ.get("PORT", "8000")
NAME = os.environ.get("NAME", getpass.getuser())
loop.run_until_complete(agent_loop(f"{SERVER}:{PORT}", NAME))
