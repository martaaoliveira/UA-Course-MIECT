#!/usr/bin/python3

import sys
import socket
import select
import json
import base64
from Crypto.Hash import SHA256
from Crypto.Cipher import AES

from common_comm import send_dict, recv_dict, sendrecv_dict

procs = {}

#
# Handler do pedido de criação de um processo de ordenação
#
def new_process( process_id, sock ):

    if process_id in procs.keys():
        return { 'error':'Sorting process already exists' }
    else:
        procs[process_id] = { 'endpoint': sock, 'ids': {} } 
        return { 'error':'' }

#
# Handler do pedido de inclusão de um novo candidato no processo de ordenação
#
def new_client( process_id, client_id, sock ):

    if process_id in procs:
        proc = procs[process_id]  # Selected sorting process

        if client_id in proc['ids']: # Client already belongs to it?
            return { 'error':'Client already registered in sorting process' }
        else:                        # New client
            proc['ids'][client_id] = { 'endpoint': sock }
            return { 'error':'' }

    else:
        return { 'error':'Sorting process no found' }

#
# Handler do pedido de listagem de clientes inscritos num processo de ordenação
#
def list_clients( process_id, sock ):
    if process_id in procs:
        proc = procs[process_id]  # Selected sorting process

        ids = []
        for i in proc['ids']:
            ids.append( i )

        return { 'ids':ids, 'error':'' }
    else:
        return { 'error':'Sorting process no found' }

#
# Handler do pedido de inicio da ordenação
#
def start_sorting( process_id, sock ):
    if process_id not in procs.keys():
        return { 'error':'Sorting process not found' }
    else:
        proc = procs[process_id]     # Selected sorting process
        if sock != proc['endpoint']: # Registered address is not the requester
            return { 'error':'Not authorized, you are not the process manager' }
        else:
            return run_sorting( proc )

def clean_proc( process_id ):
    clients = procs[process_id]['ids']

    for c in clients.values():
        c['endpoint'].close()

    del(procs[process_id])


def dump_proc( process_id ):
   proc = procs[process_id]

def clean_client ( sock ):
    for k,v in procs.items():
        if v['endpoint'] == sock:
            clean_proc ( k )
            return

#
# Processo de ordenação
#
def run_sorting( proc ): 
    
    def buscarlista(dict): #Faz retorno da lista que tem as chaves 
   
        return list(dict.keys())  #vai retornar a lista das chaves do dicionario  

    clients_lista = buscarlista(proc['ids']) #anteriormente criamos uma função para ir buscar uma lista que contem as chaves (incluidas no dicionario)
    
    engines = []  #a lista das chaves 
    lista = []  #a lista que vai para os clientes (vai conter as chaves)


    clients_lista = buscarlista(proc['ids'])
    print(clients_lista)  #Debug
    

    #codificar / descodificar--criar uma lista de 1 ate N
    for message in range(len(clients_lista)): #a mensagem funciona como uma index associado a cada cliente
        message_bytes = message.encode('utf8')
        base64_bytes = base64.b64encode(message_bytes)
        utf = base64.decode('utf8')  # codificar para utf para que possa ser enviado para a função sendrecv 
        lista.append(utf)
       

    assert len(lista) == len(clients_lista), "The list given to customers must contain the same number of customers" #condição necessaria

    dict = {"list": lista, "engines": engines,"clients_id": clients_lista}  # dicionario (N clientes) que contem as chaves que vao ser usadas + seus ids

    #criar um ciclo for para fazer com que o dicionario passar por todos os clientes
    for i in clients_lista:
        socket_client = proc['ids'][i]['endpoint'] #Vai conter a informação dos clientes
        NDict = sendrecv_dict(socket_client, dict)
        dict.update(NDict) #faz um update do dicionario que vai ser enviado
        send_dict(socket_client, dict)
      
    return
    

#
# Message structure:
# { op: NEW, proc: proc_id }
# { op: ADD, proc = proc_id, id: client_id }
# { op: LIST, proc: proc_id }
# { op: START, proc: proc_id }
#

def new_msg( client ):
    request = recv_dict( client )
    print( 'Command: %s' % (str(request)) )

    if request['op'] == 'NEW':     # Create a new sorting process
        resp = new_process( request['proc'], client )
    elif request['op'] == 'ADD':   # Add a client to a sorting process
        resp = new_client( request['proc'], request['id'], client )
    elif request['op'] == 'LIST':   # List clients in a sorting process
        resp = list_clients( request['proc'], client )
    elif request['op'] == 'START': # Start the sorting process
        start_sorting( request['proc'], client )
        resp = dump_proc( request['proc'] )
        clean_proc( request['proc'] )
    else:
        resp = { 'error':'Wrong request "' + request['op'] + '"' }

    send_dict( client, resp )

def main():
    # Validate the program parameters
    if len(sys.argv)!=2:
        print("ERROR: Invalid parameters <server port> <optional server ip>")
        sys.exit(1)
    if len(sys.argv)==3:
        ip = str(sys.argv[2])
    else:
        ip = "localhost"

    # Set the server's TCP address from the command args

    try:  #Verificar se o inserido é valido
        address = ( ip , int(sys.argv[1]) )
        if int(sys.argv[1]) <0:
            raise ValueError("Server port must be a number >0 ")
    except ValueError as erro:
        print("ERROR: " + str(erro))
        sys.exit(1)


    s = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
    s.bind( address )
    s.listen()

    clients = []

    while True:
        try:
            available = select.select( [ s ] + clients, [], [] )[0]
        except ValueError:
            # Sockets may have been closed, check for that
            for c in clients:
                if c.fileno() == -1: # closed
                    clients.remove( c )
            continue # Reiterate select

        for c in available:
             # New client?
             if c is s:
                new, addr = s.accept()
                clients.append( new )
             # Or a client
             else:
                # See if client sent a message
                if len(c.recv( 1, socket.MSG_PEEK )) != 0:
                    new_msg( c )
                else: # or just disconnected
                    clients.remove( c )
                    clean_client( c )
                    c.close()
                    break # Reiterate select

if __name__ == "__main__":
    main()
