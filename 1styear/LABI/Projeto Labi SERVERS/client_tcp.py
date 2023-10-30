#!/usr/bin/python3
import csv
import random
import os
import sys
import socket
import json 
import base64
from Crypto.Hash import SHA256
from Crypto.Cipher import AES

from common_comm import send_dict, recv_dict, sendrecv_dict

#
# JSON message structures already defined:
#
# 1 - Create an ordering process
#     request: { op: NEW, proc: <proc_id string> }
#     response: { error: <string> } (empty string if no error)
#
# 2 - List clients associated to an ordering process
#     request: { op: LIST, proc: <proc_id string> }
#     response: { ids: <list>, error: <string> } (empty string if no error)
#
# 3 - Start an ordering process
#     request: { op: START, proc: <proc_id string> }
#     response: { <to be defined> }
#
# 4 - Add a client to an ordering process
#     request: { op: ADD, proc: <proc_id string>, id: <client_id string> }
#     response: { error: <string> } (empty string if no error)

def manager( server, proc ):
    msg = { 'op': 'NEW', 'proc': proc }
    resp = sendrecv_dict( server, msg )

    print( resp )

    if resp['error'] != '':
        print( 'Server error: %s' % (resp['error']) )
        sys.exit( 2 )

    while True:
        print( 'Commands for ordering on process "' + proc + '"' )
        print( '\tL or l - list the clients' )
        print( '\tS or s - start ordering' )
        print( '\tQ or q - quit' )

        while True:
            cmd = input( 'prompt: ' )

            if cmd in ['l',  'L']:
                msg = { 'op':'LIST', 'proc': proc }
                resp = sendrecv_dict( server, msg )

                if resp['error'] == '':
                    if len(resp[ 'ids' ]) == 0:
                        print( 'No clients yet!' )
                    else:
                        print( '%d clients:' % (len(resp['ids'])) )
                        for name in resp['ids']:
                            print( '\t%s' % (name) )
                else:
                    print( 'Error: ' + resp['error'] )
                    sys.exit( 2 )
            elif cmd in ['s', 'S']:
                msg = { 'op':'START', 'proc': proc }
                resp = sendrecv_dict( server, msg )
                return resp
            elif cmd in ['q', 'Q']:
                sys.exit( 0 )
            else:
                print( "???" )

def client( server, proc, client ):
    request = {'op':'ADD', 'proc': proc, 'id': client }
    resp = sendrecv_dict( server, request )

    if resp['error'] != '':
        print( 'Server error: ' + resp['error'] )
        sys.exit( 2 )

    # Wait for server orders and show the ordering outcome at the end
    dic = recv_dict(server) # Vai receber o dicionario do server
    #print(dic)
    Lst = dic['lista']  # Vamos receber o que esta no dicionario (1 a n- Sendo N o numero de participantes)
    

    engines = dic['engines']  # vai receber as chaves usadas(que estao no dicionario)(pelos participantes)

    key = os.urandom(16)  # Vamos usar o método .urandom para gerar uma sequencia de bytes adequada para criptografar a lista

    engines.append(AES.new(key, AES.MODE_ECB)) 

    #encode/decode chaves
    for i in range(len(engines)):
        encodifica = base64.b64encode(key)
        str = encodifica.decode('utf8')#Vai decodificar para utf e assim ja pode ser usada em sendrecv_dict
        engines[i] = str
        print(i)

    #encode/decode lista
    for i in range(len(Lst)):
        decodificar = base64.b64decode(Lst[i])  # Vai decofificar um b64 string para bytes # i vai ter len N clientes.

        chaves = engines[len(engines)].encrypt(decodificar)  # vai criptografar as chaves -- estao em bytes

        encodificar = base64.b64encode(chaves)  # vai codificar para b64 string 

        str = encodificar.decode('utf8')  # Vai decodificar para utf e assim ja pode ser usada em sendrecv_dict
        Lst[i] = str 

    random.shuffle(Lst)  # vai ser aleatorio

    
    
    dic = {"list": Lst, "engines": engines} #Vai fazer um update do dicionario com as chaves  + lista aleatoria 
   
    send_dict(server, dic)  #Vai enviar o dicionario atualizado (anterior) 


    dic = recv_dict(server)
    N=random.randint(0, len(dic['list'])) #Vai escolher um numero aleatoro entre 0 e o numero de clientes(tamanho da lista)
    C = base64.b64decode(dic['list'][N])  #O elemento cifrado que cada cliente irá retirar posteriormente ( ja decifrado )
    K = base64.b64decode(dic['engines'])
    #ci?
    #ki?

    #bit commitmment
    hash_f = SHA256.new()

    hash_f.update(K) # hash, para guardar valores -- suporte de segurança 
    hash_f.update(C)

    #dic['assinatura'][client] = [Ci, ki] #iria buscar a assinatura especifica do cliente - elemento cifrado e a sua chave
    #send_dict(server, dic) #no final iriamos ter que enviar outra vez o dicionario 
    #nao conseguimos concluir esta função


     # Verificar se o que esta contido no dicionario contem a assinatura do cliente correta (coincide)
    dic_server = recv_dict(server)
    if dic_server['signature'][client] != dic['signature'][client]:
        server.close()
        print("Client( " + str(client) + " ) signature's does not match the server registered signature")
        sys.exit(1)


def dump_csv(dict_data):
    # Dump a CSV to a file from data received from the server
    csv_file = "Resultados.csv" 
    try:
        with open(csv_file, 'w') as csvfile:
            writer = csv.DictWriter(csvfile, delimiter=",")
            writer.writeheader()
            for data in dict_data:
                writer.writerow(data)
                print(data)
    except IOError:
        print("I/O error")



    

def main():
    # Validate the program parameters
    if len(sys.argv)<4 or len(sys.argv)>5:
        print("ERROR: Invalid parameters <process> <id> <server port> <optional server ip>")
        sys.exit(1)
    
    # Set the process_id and the client_id from the command args
    process_id = sys.argv[1]
    client_id = sys.argv[2]

    try:  #Verificar se o inserido é valido
        server_port = int(sys.argv[3])
        if int(sys.argv[3]) < 0:
            raise ValueError("Server port must be a number >0 ")
    except ValueError as erro:
        print("ERROR: " + str(erro))
        sys.exit(1)
    


    # Se o programa tem 5 argumentos o ultimo vai ser o ip server 
    if len(sys.argv) == 5:
        ip = str(sys.argv[4])
    else:
        ip = "localhost"

    # Set the server's TCP address from the command args
    address = (ip , server_port) 
    
    s = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
    s.connect( address )

    if client_id == '_':
        dump_csv ( manager( s, process_id ) )
    else:
        client( s, process_id, client_id )

if __name__ == "__main__":
    main()
