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
  temp = x.split(',') 
  temp[len(temp)-1] = temp[len(temp)-1].strip()
  for e in range(0,5): 
    nodes[j].append(temp[e])
    i+=1
  j+=1
  i=0

#Thread class
class thread(threading.Thread):
   
  def __init__(self, thread_name, neighbors):
    threading.Thread.__init__(self)
    self.thread_name = thread_name
    self.neighbors = neighbors
    self.curDv = [] #own vector
    self.dvMatrix = []   #Distance vectors
    self.ptNeighs2 = []
    self.counter = nodeLetters.index(self.thread_name)+1
    self.doneFlags = []
    
  def run(self):
   
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
    server.bind(('127.0.0.1',portNum)) #(local host, port number)
  
    temp = 0
    prevTurn = 0
    lastDv = []
    for e in self.curDv:
      lastDv.append(e)
    neis = []
    for e in self.neighbors:
      neis.append(nodeLetters.index(e[0]))
    while True:
      if nodeLetters.index(self.thread_name)==0 and temp==0:
        temp = 1
      else:
        try:         
          server.listen() #Starts listening to clients    
          server.settimeout(10)     
          c, address = server.accept()   #ACCEPTING DVS        
        except:
          str2 = ""
          client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
          client.connect(('127.0.0.1', 11111))
          for e in range (0, len(self.curDv)):
            if e==len(self.curDv)-1:
              str2 = str2+str(self.curDv[e])
              break
            str2 = str2+str(self.curDv[e])+','      
          str2 = str2+'/'+str(self.thread_name)
          client.send(str2.encode('utf-8')) 
          client.close()
          return 


      '''
      CHECKING TO SEE IF NODE RECEIVED MESSAGE
      '''
      try:       

        #Message will look like ==> e.g. ('1,1,2,0,1/1/2,4') or ('1,1,2,0,1/1/2,4/done') if a node is not updating anymore
        dvReceived = c.recv(1024).decode('utf-8')
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
        self.ptNeighs2 = []
        for e in range(0,len(ptNeighs)):
          self.ptNeighs2.append(int(ptNeighs[e]))

        if nodeLetters.index(self.thread_name) in self.ptNeighs2:         
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
        
        if nodeLetters.index(self.thread_name) in self.ptNeighs2:
          time.sleep(1)
          print("Node ",self.thread_name,' received DV from ',nodeLetters[prevTurn-1])
          print("Updating DV at node ",self.thread_name)
          print("New DV at node ",self.thread_name,'= ',self.curDv)
          print('\n')  
        
        # Check to see if all nodes are done
        doneFlag = ar[3]
        if doneFlag=='done':
          self.doneFlags.append(doneFlag)
        if len(self.doneFlags)==4:    
          str1 = ""
          client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
          client.connect(('127.0.0.1', 11111))
          for e in range (0, len(self.curDv)):
            if e==len(self.curDv)-1:
              str1 = str1+str(self.curDv[e])
              break
            str1 = str1+str(self.curDv[e])+','      
          str1 = str1+'/'+str(self.thread_name)
          client.send(str1.encode('utf-8')) 
          client.close()
          return  
      except:
        pass     

      '''
      TURN TO SEND MESSAGE
      '''
      if nodeLetters.index(self.thread_name)-prevTurn+1 == 1 or (prevTurn==5 and nodeLetters.index(self.thread_name)==0):    
        time.sleep(3)
        print("-------------------\nRound ",self.counter,': ',self.thread_name) 
        print('Current DV = ',self.curDv)
        print('Last DV = ',lastDv)   
        notUpdated = 0
        if lastDv!=self.curDv:
          print("Updated from the last DV or the same? Updated")
        else:
          notUpdated+=1
          print("Updated from the last DV or the same? Not updated")         
        print("\n")
        lastDv = []
        for e in self.curDv:
          lastDv.append(e)
        for n in range(0, len(nodeLetters)):
          time.sleep(0.5)
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
          #check
          if notUpdated==1 and nodeLetters.index(self.thread_name)!=0 and temp!=1:
            tempDvForMessage=tempDvForMessage+'/done'
            self.doneFlags.append('done')
          elif notUpdated==1 and nodeLetters.index(self.thread_name)==0 and temp==1:
            temp+=1

          for x in range(0,3):
            try:
              client.send(tempDvForMessage.encode('utf-8'))  
              
              if n in neis: 
                print("Sending DV to node ",nodeLetters[n])
                time.sleep(1)
              break
            except:
              pass  
        self.counter+=5


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

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
portNum = 11111
server.bind(('127.0.0.1',portNum)) #(local host, port number)

shortestPaths = [[]]*5
counter = 0
while True:
  #message look like 0,2,6,2,1/A
  server.listen(5) #Starts listening to clients    
  c, address = server.accept()   #ACCEPTING DVS
  somePath = c.recv(1024).decode('utf-8')
  ar = somePath.split('/')
  nodePath = ar[0].split(',') # 0,2,6,2,1
  node = ar[1]  # A
  path = []
  for x in nodePath:
    path.append(int(x))
  shortestPaths[nodeLetters.index(node)]=path
  counter+=1
  if counter==5:
    break
  

print('\nFinal output:\n')
for e in range (0,len(shortestPaths)):
  print('Node ', nodeLetters[e],' DV = ', shortestPaths[e])

