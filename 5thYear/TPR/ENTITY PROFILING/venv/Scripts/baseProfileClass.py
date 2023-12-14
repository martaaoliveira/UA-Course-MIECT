import numpy as np
import scipy.stats as stats
import scipy.signal as signal
import matplotlib.mlab as mlab
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MaxAbsScaler
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
from sklearn.cluster import DBSCAN
from scipy.stats import multivariate_normal
from sklearn import svm
import time
import sys
import warnings

warnings.filterwarnings('ignore')


def waitforEnter(fstop=False):
    if fstop:
        if sys.version_info[0] == 2:
            raw_input("Press ENTER to continue.")
        else:
            input("Press ENTER to continue.")
            
## -- 3 -- ##

# features: É a matriz de características (ou atributos) onde cada linha representa uma observação e cada coluna representa um atributo específico.
# oClass: É a matriz de classes correspondentes a cada observação em features.
# f1index e f2index: São os índices dos atributos que você deseja plotar um contra o ou

# f1index representa o índice do atributo que será plotado no eixo x do gráfico.
# f2index representa o índice do atributo que será plotado no eixo y do gráfico.

def plotFeatures(features,oClass,f1index=0,f2index=1):
    nObs,nFea=features.shape
    colors=['b','g','r']
    #blue BROWSING
    #green for YOUTUBE
    #RED for Mining

    for i in range(nObs):
        plt.plot(features[i,f1index],features[i,f2index],'o'+colors[int(oClass[i])])

    # Adicionar nomes aos eixos e título
    plt.xlabel(f'Feature {f1index}')
    plt.ylabel(f'Feature {f2index}')
    plt.title(f'Gráfico de Features {f1index} vs {f2index}')

    plt.show()
    waitforEnter()


def logplotFeatures(features,oClass,f1index=0,f2index=1):
    nObs,nFea=features.shape
    colors=['b','g','r']
    for i in range(nObs):
        plt.loglog(features[i,f1index],features[i,f2index],'o'+colors[int(oClass[i])])

    plt.show()
    waitforEnter()
    
## -- 11 -- ##
def distance(c,p):
    s=0
    n=0
    for i in range(len(c)):
        if c[i]>0:
            s+=np.square((p[i]-c[i])/c[i])
            n+=1
    
    return(np.sqrt(s/n))
        
    #return(np.sqrt(np.sum(np.square((p-c)/c))))

########### Main Code #############


Classes={0:'Browsing',1:'YouTube',2:'Mining'}
plt.ion()
nfig=1

## -- 2 -- ##
features_browsing=np.loadtxt("BrowsingAllF.dat")
features_yt=np.loadtxt("YouTubeAllF.dat")
features_mining=np.loadtxt("MiningAllF.dat")

#It assigns class labels (0 for browsing, 1 for YouTube, and 2 for mining) to the respective datasets
#cada classe vai conter:mean, median and standard deviation  and also the silence periods features(mean median and deviation) for upload and download therefore: 6*2
oClass_browsing=np.ones((len(features_browsing),1))*0
oClass_yt=np.ones((len(features_yt),1))*1
oClass_mining=np.ones((len(features_mining),1))*2

# empilhar esses arrays verticalmente usando np.vstack(), você cria um único conjunto de features que combina todas essas fontes de dados verticalmente. 
#resulta num conjunto de features que contém todos os dados dessas diferentes fontes combinados verticalmente.
features=np.vstack((features_yt,features_browsing,features_mining))
#print("features\n")
#print(features)
#print("\n")
#print("oclass\n")
#um único array oClass que contém todas as classes correspondentes às observações do conjunto de dados combinado features.
oClass=np.vstack((oClass_yt,oClass_browsing,oClass_mining))
#print(oClass)

# Features
# [[1.69263e+05 0.00000e+00 4.88981e+05 ... 6.20000e+01 2.00000e+00
#   1.00000e+00]
#  [1.67420e+05 0.00000e+00 4.84743e+05 ... 5.90000e+01 3.00000e+00
#   1.00000e+00]
#  [1.55259e+05 0.00000e+00 4.63472e+05 ... 5.80000e+01 3.00000e+00
#   1.00000e+00]
#  ...
#  [9.60000e+01 0.00000e+00 3.93000e+02 ... 4.50000e+01 5.00000e+00
#   2.00000e+00]
#  [9.60000e+01 0.00000e+00 3.93000e+02 ... 4.60000e+01 5.00000e+00
#   2.00000e+00]
#  [9.30000e+01 0.00000e+00 3.90000e+02 ... 4.60000e+01 5.00000e+00
#   2.00000e+00]]

# oclass

# [[1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#  [1.]
#.....

# Upload           Silencio  | Download     Silencio   
# m  m  d           m  m  d    m   m  d     m  m d 
# 169263 0 488981   62 2 1    4912 0 13927  62 2 1
#....
#...
print('Train Silence Features Size:',features.shape)
plt.figure(2)
plotFeatures(features,oClass,4,10) #mediana silencio upload vs mediana silencio download
#Youtube tem poucos periodos de silencio. Poucos pontos, medianas proximas umas das outras
#Browsing, muitos pontos variadas, muita descrepancia valores silencio entre janelas de obs
#Mining alguma (nao tanto como browsing) descrepancia valores silencio


#plt.figure(4)
#plotFeatures(features,oClass,2,8) #desvio upload e download
#Youtube tem poucos periodos de silencio. Poucos pontos, medias proximas umas das outras
#Browsing, muitos pontos variadas, muita descrepancia valores silencio
#Mining alguma (nao tanto como browsing) descrepancia valores silencio

plt.figure(5)
plotFeatures(features,oClass,3,9) #Comparar media silencio upload com media silencio download

plt.figure(6)                       
plotFeatures(features,oClass,0,6) ##Comparar media upload vs media download


#divisão do conjunto de dados em dados de treino e de teste. 

## -- 3 -- ##
#:i
#Define a percentagem dos dados originais que serão usados para TREINO (50% neste caso).
percentage=0.5
#pB, pYT, pM: Calculam o tamanho do conjunto de treino para cada categoria (Browsing, YouTube e Mining) com base na percentagem definida.
pB=int(len(features_browsing)*percentage)
trainFeatures_browsing=features_browsing[:pB,:]
pYT=int(len(features_yt)*percentage)
trainFeatures_yt=features_yt[:pYT,:]
pM=int(len(features_mining)*percentage)
trainFeatures_mining=features_mining[:pYT,:]

#i2train: Concatena os conjuntos de TREINO das categorias de Browsing e YouTube (SEM MINING) para serem usados em modelos de classificação.
i2train=np.vstack((trainFeatures_browsing,trainFeatures_yt))
o2trainClass=np.vstack((oClass_browsing[:pB],oClass_yt[:pYT]))

#:ii
#Concatena os dados de TREINO das categorias de Browsing, YouTube E MINING para serem usados em algum modelo de classificação.
i3Ctrain=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
o3trainClass=np.vstack((oClass_browsing[:pB],oClass_yt[:pYT],oClass_mining[:pM]))

#:iii
testFeatures_browsing=features_browsing[pB:,:]
testFeatures_yt=features_yt[pYT:,:]
testFeatures_mining=features_mining[pM:,:]
#Fornecer ao modelos os dados de TESTE
i3Atest=np.vstack((testFeatures_browsing,testFeatures_yt,testFeatures_mining))
o3testClass=np.vstack((oClass_browsing[pB:],oClass_yt[pYT:],oClass_mining[pM:]))

# -- 4 -- ##
# O K-Means é um algoritmo de agrupamento muito comum e simples, usado para particionar um conjunto de dados em clusters. 
# Ele opera de maneira iterativa para atribuir cada ponto de dados a um dos K clusters, onde K é um número pré-definido pelo usuário.
# Deviamos usar 4 clusters para separar melhor os comportamentos 

print('\n-- Clustreing with K-Means --')
kmeans = KMeans(n_clusters=3, random_state=0, n_init="auto")
i3Ctrain=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
i3Ctrain = StandardScaler().fit_transform(i3Ctrain)
labels= kmeans.fit_predict(i3Ctrain)



#Browsing está no Cluster 1, YouTube no Cluster 0 e 2 e Mining no Cluster 1 -> 
for i in range(len(labels)):
    print('Obs: {:2} ({}): K-Means Cluster Label: -> {}'.format(i,Classes[o3testClass[i][0]],labels[i]))



## -- 5 -- ##


print('\n-- Clustreing with DBSCAN --')
#é um algoritmo de agrupamento baseado em densidade que é especialmente útil quando os clusters têm diferentes densidades ou formatos. 
# Ele identifica clusters com base na densidade dos pontos em torno deles.
# Eps (epsilon): É a distância máxima entre dois pontos para que sejam considerados no mesmo cluster.
# MinPts: É o número mínimo de pontos dentro do raio eps para formar um cluster.

#Os pontos de dados podem ter densidades diferentes dentro de cada tipo de pacote. 
# Por exemplo, no YouTube, pode haver áreas mais densas de pontos (mais observações em determinadas regiões), o que leva a mais clusters.

#browsing{0,1}
#Youtube{1,4,3,-1,5}
#Mining {6}


i3Ctrain=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
i3Ctrain = StandardScaler().fit_transform(i3Ctrain) #NORMALIZAÇÂO DE DADOS
db = DBSCAN(eps=0.5, min_samples=10).fit(i3Ctrain)
labels = db.labels_

for i in range(len(labels)):
    print('Obs: {:2} ({}): DBSCAN Cluster Label: -> {}'.format(i,Classes[o3testClass[i][0]],labels[i]))

## -- 7 -- ##

# No contexto de clustering, um centróide é o ponto médio ou representativo de um cluster. 
# Para calcular um centróide, são utilizadas as features dos pontos que pertencem a esse cluster, e o centróide é definido como o ponto médio dessas features.

i2train=np.vstack((trainFeatures_browsing,trainFeatures_yt))
#scaler = MaxAbsScaler().fit(i2train)
#i2train=scaler.transform(i2train)

#
#  calcuclar e representar os "centros" dos clusters das duas primeiras classes(yotube e browsing) no conjunto de treinamento. 
# Esses centróides podem ser usados posteriormente em análises, como detecção de anomalias ou para visualização da distribuição dos clusters nos dados.

#  {0: array([7.78770423e+03, 0.00000000e+00, 3.75927958e+04, 3.75563380e+01,
#        6.73943662e+00, 7.50704225e+00, 6.59063380e+02, 0.00000000e+00,
#        3.00565493e+03, 3.77253521e+01, 6.76056338e+00, 7.47887324e+00]), 1: array([1.42990317e+05, 4.17605634e+00, 3.92501880e+05, 5.42253521e+01,
#        2.83802817e+00, 2.04225352e+00, 4.50634507e+03, 3.23239437e+00,
#        1.20347887e+04, 5.43309859e+01, 2.82394366e+00, 2.02816901e+00])}

#
#
centroids={}
for c in range(2):  # Only the first two classes
    pClass=(o2trainClass==c).flatten()
    centroids.update({c:np.mean(i2train[pClass,:],axis=0)})
print('All Features Centroids:\n',centroids)

i3Atest=np.vstack((testFeatures_browsing,testFeatures_yt,testFeatures_mining))
#i3Atest=scaler.transform(i3Atest)

AnomalyThreshold=10

print('\n-- Anomaly Detection based on Centroids Distances --')
nObsTest,nFea=i3Atest.shape
for i in range(nObsTest):
    x=i3Atest[i]
    dists=[distance(x,centroids[0]),distance(x,centroids[1])]
    if min(dists)>AnomalyThreshold:
        result="Anomaly"
    else:
        result="OK"
       
    print('Obs: {:2} ({}): Normalized Distances to Centroids: [{:.4f},{:.4f}] -> Result -> {}'.format(i,Classes[o3testClass[i][0]],*dists,result))


## -- 8 -- ##

# #
# As Support Vector Machines (SVMs) do tipo One-Class 
# são uma variação especial de SVMs que são usadas para problemas de detecção de anomalias ou para aprendizado de uma única classe. 
print('\n-- Anomaly Detection based on One Class Support Vector Machines--')
i2train=np.vstack((trainFeatures_browsing,trainFeatures_yt))
i3Atest=np.vstack((testFeatures_browsing,testFeatures_yt,testFeatures_mining))

nu=0.1
ocsvm = svm.OneClassSVM(gamma='scale',kernel='linear',nu=nu).fit(i2train)  
rbf_ocsvm = svm.OneClassSVM(gamma='scale',kernel='rbf',nu=nu).fit(i2train)  
poly_ocsvm = svm. OneClassSVM(gamma='scale',kernel='poly',nu=nu,degree=2).fit(i2train)  

L1=ocsvm.predict(i3Atest)
L2=rbf_ocsvm.predict(i3Atest)
L3=poly_ocsvm.predict(i3Atest)

AnomResults={-1:"Anomaly",1:"OK"}

nObsTest,nFea=i3Atest.shape
for i in range(nObsTest):
    print('Obs: {:2} ({:<8}): Kernel Linear->{:<10} | Kernel RBF->{:<10} | Kernel Poly->{:<10}'.format(i,Classes[o3testClass[i][0]],AnomResults[L1[i]],AnomResults[L2[i]],AnomResults[L3[i]]))


## -- 10 -- #
print('\n-- Classification based on Support Vector Machines --')

i3train=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
i3Ctest=np.vstack((testFeatures_browsing,testFeatures_yt,testFeatures_mining))


svc = svm.SVC(kernel='linear').fit(i3train, o3trainClass)  
rbf_svc = svm.SVC(kernel='rbf').fit(i3train, o3trainClass)  
poly_svc = svm.SVC(kernel='poly',degree=2).fit(i3train, o3trainClass)  

L1=svc.predict(i3Ctest)
L2=rbf_svc.predict(i3Ctest)
L3=poly_svc.predict(i3Ctest)
print('\n')

nObsTest,nFea=i3Ctest.shape
for i in range(nObsTest):
    print('Obs: {:2} ({:<8}): Kernel Linear->{:<10} | Kernel RBF->{:<10} | Kernel Poly->{:<10}'.format(i,Classes[o3testClass[i][0]],Classes[L1[i]],Classes[L2[i]],Classes[L3[i]]))


## -- 12 -- ##
from sklearn.neural_network import MLPClassifier
print('\n-- Classification based on Neural Networks --')
i3train=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
i3Ctest=np.vstack((testFeatures_browsing,testFeatures_yt,testFeatures_mining))

scaler = MaxAbsScaler().fit(i3train)
i3trainN=scaler.transform(i3train)
i3CtestN=scaler.transform(i3Ctest)


alpha=1
max_iter=100000
clf = MLPClassifier(solver='lbfgs',alpha=alpha,hidden_layer_sizes=(20,),max_iter=max_iter)
clf.fit(i3trainN, o3trainClass) 
LT=clf.predict(i3CtestN) 

nObsTest,nFea=i3CtestN.shape
for i in range(nObsTest):
    print('Obs: {:2} ({:<8}): Classification->{}'.format(i,Classes[o3testClass[i][0]],Classes[LT[i]]))


# Wait for user input before exiting
waitforEnter(fstop=True)