from tree_search import *
from cidades import *


#Marta Oliveira 97613
#General theory Discussed with:
#João Eduardo da Silva Figueiredo - 93250
#Joana Cunha - 98189
#David Raposo - 93395


class MyNode(SearchNode):
    def __init__(self,state,parent,cost,heuristic,depth=None):
        super().__init__(state,parent)
        self.state=state
        self.cost=cost
        self.depth=depth
        self.heuristic=heuristic
        self.eval=0
        self.children=[]
        
class MyTree(SearchTree):

    def __init__(self,problem, strategy='breadth',seed=0): 
        super().__init__(problem,strategy,seed)
        self.problem = problem
        self.state=None
        root = MyNode(problem.initial,None,0,self.problem.domain.heuristic(problem.initial, problem.goal), 0)
        self.all_nodes = [root]
        self.terminals=0
        self.non_terminals=0
        self.solution_tree=[]
        #self.used_shortcuts =[] # se descomentar esta linha de codigo o pytest deixa de "funcionar" => aparece "no tests ran"

    def astar_add_to_open(self,lnewnodes):
        #self.open_nodes.sort(key = lambda node: node.problem.domain.heuristic(node.state,node.goal) + node.heuristic(node.state,node.goal))
        #self.open_nodes.extend(lnewnodes)
        self.open_nodes=sorted(self.open_nodes + lnewnodes, key =lambda pos:(self.all_nodes[pos]).heuristic+(self.all_nodes[pos]).cost)
    
    #    
    def propagate_eval_upwards(self,node):
        #IMPLEMENT HERE
        if node.children == []:
            return self.propagate_eval_upwards(self.all_nodes[node.parent]) #Nó pai que nao tem filhos
        child_eval=[]
        for x in node.children:
            child_eval.append(self.all_nodes[x].eval)
           
        child_eval.sort(key = lambda eval: eval) #ordenar => "contain the lowest value of the evaluation function"
        node.eval=child_eval.pop(0) 
            

        if node.parent == None: 
            return None

        return self.propagate_eval_upwards(self.all_nodes[node.parent]) 

    def search2(self,atmostonce=False):
        # if atmostonce: #se os nos foram visitados(atmostonce == true) adicionar 
        #     visited = []   
        while self.open_nodes != []:
            nodeID = self.open_nodes.pop(0)
            # if atmostonce:
            #   if node.state is visited:
            #         continue
            #   visited.append(node.state)
            node = self.all_nodes[nodeID]
            if self.problem.goal_test(node.state):
                self.solution = node
                self.terminals = len(self.open_nodes)+1
                return self.get_path(node)
            lnewnodes = []
            self.non_terminals += 1
            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state,a)
                if newstate not in self.get_path(node):
                    new_cost =  self.problem.domain.cost(node.state, a) + node.cost
                    #newnode = MyNode(newstate,nodeID,node.cost+self.problem.domain.cost(node.state,a),node.depth+1,self.problem.domain.heuristic(newstate,self.problem.goal))
                    newnode = MyNode(newstate, nodeID, new_cost , self.problem.domain.heuristic(newstate, self.problem.goal))
                    #self.problem.domain.cost(node.state,a)
                    newnode.cost = node.cost + self.problem.domain.cost(node.state,a)
                    newnode.eval = newnode.cost + newnode.heuristic 
                    node.children.append(len(self.all_nodes))
                    self.all_nodes.append(newnode)
                    self.propagate_eval_upwards(newnode)
                    lnewnodes.append(len(self.all_nodes)-1)
            self.add_to_open(lnewnodes) 
        return None

    def repeated_random_depth(self,maxattempts=3,atmostonce=False):
        minCost = float('inf') #valor infinito => usamos para comparar no if para conseguirmos encontrar a melhor arvore em termos de custo
        for i in range(0,maxattempts):
            tree = MyTree(self.problem, 'rand_depth',i)
            tree.search2()
            if tree.solution.cost< minCost:
                self.solution_tree=tree
                minCost=tree.solution.cost
        return self.solution_tree.get_path(self.solution_tree.solution)



    def make_shortcuts(self):
        #IMPLEMENT HERE
        caminho = self.get_path(self.solution)
        i = 0
        while i < len(caminho)-1:
            ligacoes = self.problem.domain.actions(caminho[i])
            for d in ligacoes:
                l = len(caminho)-1
                while l > 1:
                    if self.problem.domain.result(caminho[i],d) == caminho[l] and l-i > 1 :
                        self.used_shortcuts += [(caminho[i],caminho[l])] #nao consigo inicializar o self.used_shortcuts na classe => no pytest dá "no tests_ran" 
                    l-=1
                i+=1
        return list(caminho)
        
        



class MyCities(Cidades):

    def maximum_tree_size(self,depth):   # assuming there is no loop prevention
        average_nr_neighbors = 0
        t_depth = 0
        for cidades in self.coordinates:
            for connections in self.connections:
                if cidades in connections: 
                    average_nr_neighbors += 1
        for d in range(0, depth+1):
            t_depth += (average_nr_neighbors / len(self.coordinates)) ** d #media é nos dada por nr de vizinhos / nr de cidades

        return t_depth