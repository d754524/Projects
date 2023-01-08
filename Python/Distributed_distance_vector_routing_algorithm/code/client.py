import socket

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(('127.0.0.1',1))
str = '20101/1/1,3'
# 20101 would be the vector
# 1 would be the index of the sender
# 1,3 the nodes that should add the vector to their matrix 
client.send(str.encode('utf-8'))
# print(client.recv(1024).decode('utf-8'))