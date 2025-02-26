#encoding: utf8
#Marta Oliveira 97613
#Comentado com: David Raposo - 93395

from typing import Counter
from semantic_network import *
from bayes_net import *
from itertools import product
import math 

class MySemNet(SemanticNetwork):
    def __init__(self):
        SemanticNetwork.__init__(self)
        # IMPLEMENT HERE (if needed)
        pass

    def source_confidence(self,user):
        # IMPLEMENT HERE
        correct = 0
        wrong = 0
        declarations = list(set([d.relation for d in self.query_local(user=user) if d.relation.__class__.__name__== "AssocOne"]))
        for d in declarations:
            lst = list([e.relation.entity2 for e in self.query_local(e1=d.entity1,relname = d.name)])
            common = Counter(lst)
            mostcommon = list([k for k,m in common.items() if m == max(common.values())])
            if d.entity2 in mostcommon:
                correct+=1
            else:
                wrong+=1
        confidence = (1-(math.pow(0.75,correct)))*math.pow(0.75,wrong)
        return confidence

    def compute_confidence(self,nr_occurrences,t):
        aux1 = 1-math.pow(0.95, nr_occurrences)
        aux2=math.pow(0.95, t - nr_occurrences)
        result = (nr_occurrences / (2 * t)) + ((1 - nr_occurrences / (2 * t) )*aux1*aux2)
        return result

    def query_with_confidence(self,entity,assoc):
        parents =  {d.relation.entity2 for d in self.query_local(e1=entity) if isinstance(d.relation, Member) or isinstance(d.relation, Subtype) }
        confidence={}
        declarations = list(set([d.relation.entity2 for d in self.query_local(e1=entity, relname=assoc) if isinstance(d.relation, AssocOne) or isinstance(d.relation, AssocSome) ]))
        t = len(declarations)
        n = Counter(declarations)
        local_results = {e: self.compute_confidence(n.get(e), t) for e in declarations }
        for p in parents:
            parent_conf = self.query_with_confidence(p, assoc)
            for declarations in parent_conf:
                if declarations not in confidence:
                    confidence[declarations] = parent_conf[declarations]
                else:
                    confidence[declarations] += parent_conf[declarations]
        if  not parents:
            return local_results       

        avg = {p:confidence[p]/len(parents) for p in confidence}

        if not local_results:
            confidence = {c:avg[c]*0.9 for c in confidence}
            return confidence

        return local_results 

        
class MyBN(BayesNet):

    def __init__(self):
        BayesNet.__init__(self)
        # IMPLEMENT HERE (if needed)
        pass
    
    def dependants(self, var):
        lista = [v for (v,i) in list(self.dependencies[var].keys())[0]]
        for i in lista:
            lista += self.dependants(i)
        return list(set(lista))

    def set_conjunction(self, lvars):
        l = product([True,False], repeat=len(lvars))
        return list(map(lambda c : list(zip(lvars, c)),l))

    def individual_probability(self,var,val):
        probability = 0
        descendant = self.set_conjunction(self.dependants(var))
        for conjunction in descendant:
            probability += self.jointProb(conjunction + [(var, val)])
        return probability

    def individual_probabilities(self):
        # IMPLEMENT HERE
        dic = {}
        for d in self.dependencies.keys():
            dic[d] = (self.individual_probability(d, True))
        return dic
    
  

 

