import asyncio
import getpass
import json
import os
from copy import deepcopy
import websockets
matriz=[[1, 1, 1, 1, 1, 1, 1, 1,1],[0, 0, 0, 1, 0, 0, 0, 0,0],[1, 1, 1, 1, 1, 1, 1, 1,1],[0, 0, 0, 0, 0, 0, 0, 0,0],[0, 0, 0, 0, 0, 0, 0, 0,0]]
game= [[4, 27], [3, 28], [4, 28], [3, 29], [4, 25], [3, 26], [4, 26], [3, 27], [6, 27], [7, 27], [6, 28], [6, 29], [2, 27], [1, 28], [2, 28], [1, 29]]
piece = [[4, 10], [3, 11], [4, 11], [3, 12]]

class Tetris:
    Board_Height=8
    Board_WIDTH=29
    vectores_possibilities=[[0,1],[0,2],[0,3],[0,4],
    [1,0],[1,1],[1,2],[1,3],[1,4],
    [2,0],[2,1],[2,2],[2,3],[2,4],
    [3,0],[3,1],[3,2],[3,3],[3,4],
    [4,0],[4,1],[4,2],[4,3],[4,4],
    [-1,0],[-1,1],[-1,2],[-1,3],[-1,4],
    [-2,0],[-2,1],[-2,2],[-2,3],[-2,4],
    [-3,0],[-3,1],[-3,2],[-3,3],[-3,4],
    [-4,0],[-4,1],[-4,2],[-4,3],[-4,4]]


def get_all_possibilities_positions(piece):
    if(piece==None):
        return None
    possibilities_positions=[]
    for v in Tetris.vectores_possibilities:
        newPiece=[]
        exists=True
        for each_block in piece:
            coordenada_x= each_block[0]+v[0]
            coordenada_y=each_block[1]+v[1]
            #as cordenadas nao podem ser maiores do que estao declarado no shape.py (x entre 1 e 8)
            if(coordenada_x>=8 or coordenada_x<0 or coordenada_y<0):
                exists=False
                break
            else:
                newPiece += [coordenada_x,coordenada_y]
                exists=True

        if(exists):
            possibilities_positions+=[newPiece]

        newPiece=[]
        exists=True
        for each_block in piece:
            coordenada_x= each_block[0]-v[0]
            coordenada_y=each_block[1]-v[1]
            if(coordenada_x>=6 or coordenada_x<0 or coordenada_y<0):
                exists=False
                break
            else:
                newPiece += [coordenada_x,coordenada_y]
                exists=True

        if(exists):
            possibilities_positions+=[newPiece]
    return possibilities_positions


#ao jogar o jogo e fazer um print de cada peça obtemos as suas coordenadas. 
#Depois com a função get_all_possibilities_positions vamos obter as varias possibilidades de como a peça pode iniciar (somando vetores)
I=get_all_possibilities_positions([[2,2],[3,2],[4,2],[5,2]])
L=get_all_possibilities_positions([[2,1],[3,1],[2,2],[2,3]])
S=get_all_possibilities_positions([[2,1],[1,2],[2,2],[1,3]])
T=get_all_possibilities_positions([[4,2],[4,3],[5,3],[4,4]])
J=get_all_possibilities_positions([[2,1],[2,2],[2,3],[3,3]])
O=get_all_possibilities_positions([[3,3],[4,3],[3,4],[4,4]])
Z=get_all_possibilities_positions([[2,1],[2,2],[3,2],[3,3]])

ALL_PIECES=[I,L,S,T,J,O,Z]





def piece_exists(pieces):
    for p in ALL_PIECES:
        if pieces in p:
            if p == I:
                return "I"
            elif p == L:
                return "L"
            elif p==S:
                return "S"
            elif p==T:
                return "T"
            elif p==J:
                return "J"
            elif p==O:
                return "O"
            elif p==Z: 
                return "Z"



   
def rotate(piece, type_piece, numOfrotations):
    #type_piece = get_piece(piece)
    #print(type_piece)
    piece = deepcopy(piece)
    center = [0,0]     
    if type_piece == 'O':
        return piece
    elif type_piece == 'T':
        #center = get_center(piece, type_piece)
        center = piece[1]
        for i in range(numOfrotations):
            for block in piece:
                if block != center:
                    if block[0] == center[0] and block[1] < center[1]:
                        block[0] += 1
                        block[1] += 1
                    elif block[0] > center[0] and block[1] == center[1]:
                        block[0] -= 1
                        block[1] += 1
                    elif block[0] == center[0] and block[1] > center[1]:
                        block[0] -= 1
                        block[1] -= 1
                    elif block[0] < center[0] and block[1] == center[1]:
                        block[0] += 1
                        block[1] -= 1
        return piece
    elif type_piece == 'S':
        #center = get_center(piece, type_piece)
        center = piece[1]
        if numOfrotations % 2 != 0:
            for block in piece:
                if block != center:         # atençao à ordem das peças
                    if block[0] == center[0] and block[1] == center[1] - 1:
                        block[0] -= 1
                        block[1] += 2
                    if block[0] == center[0] + 1 and block[1] == center[1] + 1:
                        block[0] -= 1
        return piece
    elif type_piece == 'Z':
        #center = get_center(piece, type_piece)
        center = piece[2]
        if numOfrotations % 2 != 0:
            for block in piece:
                if block != center:         # atençao à ordem das peças
                    if block[0] == center[0] and block[1] == center[1] - 1:
                        block[0] += 1
                        block[1] += 2
                    if block[0] == center[0] - 1 and block[1] == center[1] + 1:
                        block[0] += 1
        return piece
    elif type_piece == 'L' or type_piece == 'J':
        #center = get_center(piece, type_piece)
        if type_piece == 'L':
            center = piece[1]  
        elif type_piece == 'J':
            center = piece[2]      
        
        for i in range(numOfrotations):
            for block in piece:
                if block != center:
                    if block[0] == center[0] and block[1] < center[1]:
                        block[0] += 1
                        block[1] += 1 
                    elif block[0] > center[0] and block[1] == center[1]:
                        block[0] -= 1 
                        block[1] += 1 
                    elif block[0] == center[0] and block[1] > center[1]:
                        block[0] -= 1
                        block[1] -= 1
                    elif block[0] < center[0] and block[1] == center[1]:
                        block[0] += 1
                        block[1] -= 1
                    else:
                        if block[0] > center[0] and block[1] < center[1]:        # trata se do bloco "cauda", o unico que nao está diretamente conectado ao centro 
                            block[1] += 2
                        elif block[0] > center[0] and block[1] > center[1]:
                            block[0] -= 2
                        elif block[0] < center[0] and block[1] > center[1]:
                            block[1] -= 2
                        elif block[0] < center[0] and block[1] < center[1]:
                            block[0] += 2
        return piece
    elif type_piece == 'I':
        #center = get_center(piece, type_piece)
        center = piece[2]                              
        if numOfrotations % 2 != 0:    
            for block in piece:
                if block != center:
                    if block[0] == center[0] - 2:
                        block[0] += 2
                        block[1] -= 2
                    elif block[0] == center[0] - 1:
                        block[0] += 1
                        block[1] -= 1
                    else:
                        block[0] -= 1
                        block[1] += 1
        return piece
    







print(game)
print("\n")
print(piece)
print("\n")
print(rotate(piece,piece_exists(piece),1))