#devolve o nr de vezes que aparece um elemento na lista, e essa mesma
#lista sem esse elemento
def g(x,y):
    if y==[]:
        return(0,[])
    (z,t)=g(x,y[1:])
    #print(z,t)
    if y[0]==x:
        return(z+1,t)
    return (z,[y[0]]+t)
  
x=3
y=[1,2,3,4,5]
#print(y[:])
res=g(x,y)
print(res)

