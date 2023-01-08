'''
* The adjacency matrix is an NxN matrix where N is the number of nodes.
* If the weight is 0, node i and j are not directly connected to each other.
* The matrix is symmetric, meaning weith at (i,j) is the same as (j,i)
* network.txt will ONLY contain the adjacency matrix.
* We will assume N=5
* Topology varies
* Node threads are not allowed to look at the entire network topology as DVR is a distributed algorithm.
*The message sending will stop when the shortest paths from each node to all other nodes are found. One way to check that would be to see if DV messages are sent but no changes occur anymore.
* Dx(y) = MINv{Cx,v + Dv(y)}            cost of least-cost path from x to y.
'''
import threading
import os
import time
import socket

nodeA,nodeB,nodeC,nodeD,nodeE = [],[],[],[],[]
nodes = [nodeA,nodeB,nodeC,nodeD,nodeE]
nodeLetters = ['A','B','C','D','E']
nodesThreads = []
serverSocket = [10000,10001,10002,10003,10004]  #List of socket numbers

matrixFile = open("network.txt", "r")

i,j = 0,0
for x in matrixFile:
  for e in range(0,5):
    nodes[j].append(x[e])
    i+=1
  j+=1
  i=0


#Thread class
class thread(threading.Thread):
 
  changed = 0
  
  def __init__(self, thread_name, neighbors):
    threading.Thread.__init__(self)
    self.thread_name = thread_name
    self.neighbors = neighbors
    self.curDv = [] #own vector
    self.dvMatrix = []   #Distance vectors


  def run(self):

    # print("Node name: ",self.thread_name,"\tNeighbors: ",self.neighbors)
   
    i = 0
    for n in range(0,len(nodeLetters)):
      if i<=len(self.neighbors)-1 and nodeLetters[n]==self.neighbors[i][0]:
        self.curDv.insert(n,int(self.neighbors[i][1]))
        i+=1
      elif n==nodeLetters.index(self.thread_name):
        self.curDv.append(0)
      else:
        self.curDv.append('i')
    
      
    for x in range(0,5):
      if nodeLetters[x]==self.thread_name:
        self.dvMatrix.append(self.curDv)
      else:
        self.dvMatrix.append(['i']*5)
    
    
 
    #Socket
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    portNum = int(nodeLetters.index(self.thread_name)+10000)
    # server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR,1)
    server.bind(('127.0.0.1',portNum)) #(local host, port number)
  
    temp = 0
    prevTurn = 0

    while True:
      time.sleep(3)
      if nodeLetters.index(self.thread_name)==0 and temp==0:
        temp = 1
      else:
        server.listen() #Starts listening to clients
        c, address = server.accept()   #ACCEPTING DVS        
      
      '''
      CHECKING TO SEE IF NODE RECEIVED MESSAGE
      '''
      try:               
        #Message will look like ==> e.g. ('1,1,2,0,1/1/2,4')
        dvReceived = c.recv(1024).decode('utf-8')
        print("MESSAGE RECEIVED FOR NODE ",self.thread_name,'. Message: ',dvReceived)
        ar = dvReceived.split('/')  

        cv = ar[0].split(',')  # DV received in message
        cv2 = []
        for e in range(0,len(cv)):
          try:
            cv2.append(int(cv[e]))
          except:
            cv2.append('i')
            pass

        prevTurn = ar[1]  # index of node sending message
        ptNeighs = ar[2]  # neighbors of node sending message
        
        prevTurn = int(prevTurn)
        ptNeighs2 = []
        for e in range(0,len(ptNeighs)):
          ptNeighs2.append(int(ptNeighs[e]))
        
        if nodeLetters.index(self.thread_name) in ptNeighs2:
          #Accept DV if you are one of the neighbors of the sender
          self.dvMatrix[prevTurn-1] = cv2

        #Adjust own DV          
          for e in range(0,len(self.curDv)):
            minOfSums = []
            for x in range(0,len(self.curDv)):
              if self.curDv[x]=='i' or self.dvMatrix[x][e]=='i':
                minOfSums.append(9999)
                continue
              minOfSums.append(self.curDv[x]+self.dvMatrix[x][e])

            if min(minOfSums)==9999:
              self.curDv[e] = 'i'
            else:
              self.curDv[e] = min(minOfSums)         
       
        self.dvMatrix[nodeLetters.index(self.thread_name)] = self.curDv
        print("Node ",self.thread_name," UPDATED DVs==> ",self.dvMatrix)

      except:
        print('No message for ',self.thread_name,'\n')
        pass
      
      time.sleep(3)
      '''
      TURN TO SEND MESSAGE
      '''
      if nodeLetters.index(self.thread_name)-prevTurn+1 == 1 or (prevTurn==5 and nodeLetters.index(self.thread_name)==0):    
        
        print("Round",self.thread_name)             

        for n in range(0, len(nodeLetters)):
          if n==nodeLetters.index(self.thread_name):
            continue
          tempDvForMessage = ""
          client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
          for x in range(0,3):
            try:            
              client.connect(('127.0.0.1', nodeLetters.index(nodeLetters[n])+10000))            
            except:
              continue
                  
          for e in range(0,len(self.curDv)):   
            if e==len(self.curDv)-1:
              tempDvForMessage = tempDvForMessage+(str(self.curDv[e]))
              break
            tempDvForMessage = tempDvForMessage+(str(self.curDv[e]))+','   
           
          tempDvForMessage = tempDvForMessage+'/'
          tempDvForMessage = tempDvForMessage+str(nodeLetters.index(self.thread_name)+1)
          tempDvForMessage = tempDvForMessage+'/'
          for x in self.neighbors:
            tempDvForMessage = tempDvForMessage+str(nodeLetters.index(x[0]))
          for x in range(0,3):
            try:
              client.send(tempDvForMessage.encode('utf-8'))             
              break
            except:
              pass      
   
# This function will create N threads(One for each node). When creating, the function will pass each node thread
# i) Who are the neighbors of the node
# ii) THe link weights to the neighbors
# 
def network_init():
  nodeNeighbors = []
  for i in range(0,5):
    for j in range(0,5):
      if(nodes[i][j] != '0'):
        nei = []
        nei.append(nodeLetters[j])
        nei.append(nodes[i][j])
        nodeNeighbors.append(nei)
    nodesThreads.append(thread(nodeLetters[i],nodeNeighbors))
    nodeNeighbors = []
  nodesThreads[0].start()
  time.sleep(1)
  nodesThreads[1].start()
  time.sleep(1)
  nodesThreads[2].start()
  time.sleep(1)
  nodesThreads[3].start()
  time.sleep(1)
  nodesThreads[4].start()
  





network_init()





