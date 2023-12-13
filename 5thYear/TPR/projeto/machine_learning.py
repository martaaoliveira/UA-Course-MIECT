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
    #blue Marta
    #green Bruno
    #RED for Dns tunneling

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


Classes={0:'Marta',1:'Bruno',2:'DNS'}
plt.ion()
nfig=1

## -- 2 -- ##
features_bruno=np.loadtxt("features_bruno.dat")
features_marta=np.loadtxt("features_marta.dat")
features_dns=np.loadtxt("features_dns_tunneling.dat")

#It assigns class labels (0 for browsing, 1 for YouTube, and 2 for mining) to the respective datasets
#cada classe vai conter:mean, median and standard deviation  and also the silence periods features(mean median and deviation) for upload and download therefore: 6*2
oClass_bruno=np.ones((len(features_bruno),1))*0
oClass_marta=np.ones((len(features_marta),1))*1
oClass_dns=np.ones((len(features_dns),1))*2

# empilhar esses arrays verticalmente usando np.vstack(), você cria um único conjunto de features que combina todas essas fontes de dados verticalmente.
#resulta num conjunto de features que contém todos os dados dessas diferentes fontes combinados verticalmente.
features=np.vstack((features_marta,features_bruno,features_dns))
# print("features\n")
# print(features_dns)
# print("\n")
print("oclass\n")
#um único array oClass que contém todas as classes correspondentes às observações do conjunto de dados combinado features.
oClass=np.vstack((oClass_marta,oClass_bruno,oClass_dns))
#print(oClass)

# nPktUp BytesUp nPktDown BytesDown
#0..10   11..21  22..32   33..43


#nPktUp
#MAXIMO MEDIA MEDIANA DESVIO SI SI SI PER PER PER PER
# 0      1      2      3      4  5  6  7   8   9   10

#BytesUp
#MAXIMO MEDIA MEDIANA DESVIO SI SI SI PER PER PER PER
# 11      12     13      14  15 16 17 18  19   20  21

#nPktDown
#MAXIMO MEDIA MEDIANA DESVIO SI SI SI PER PER PER PER
# 22      23     24      25  26 27 28 29  30   31  32

#BytesDown
#MAXIMO MEDIA MEDIANA DESVIO SI SI SI PER PER PER PER
# 33      34     35      36  37 38 39 40  41   42 43

#UP DOWN
# 0      1        2    3  4  5 |   6   7         8    9  10 11
# MEDIA MEDIANA DESVIO SI SI SI| MEDIA MEDIANA DESVIO SI SI SI

# Upload           Silencio  | Download     Silencio
# m  m  d           m  m  d    m   m  d     m  m d
# 169263 0 488981   62 2 1    4912 0 13927  62 2 1


#print('Train Silence Features Size:',features.shape)
plt.figure(2)
plotFeatures(features,oClass,0,22) #maximo  nPktUp vs maximo  nPktDown


plt.figure(4)
plotFeatures(features,oClass,1,23) #media nPktUp e media nPktDown

plt.figure(5)
plotFeatures(features,oClass,5,27) #Comparar media silencio nPktUp com media silencio nPktDown

plt.figure(6)
plotFeatures(features,oClass,3,25) ##Comparar desvio nPktUp vs desvio nPktDown

plt.figure(7)
plotFeatures(features,oClass,15,37) ##Comparar size silence nPktUp vs size silence nPktDown


plt.figure(8)
plotFeatures(features,oClass,12,34) #media BytesUp e media BytesDown

plt.figure(9)
plotFeatures(features,oClass,21,43) #percentis 98 BytesUp e percentis 98 BytesDown




#divisão do conjunto de dados em dados de treino e de teste.

## -- 3 -- ##
#:i
#Define a percentagem dos dados originais que serão usados para TREINO (50% neste caso).
percentage=0.5
#pB, pYT, pM: Calculam o tamanho do conjunto de treino para cada categoria (Browsing, YouTube e Mining) com base na percentagem definida.
pB=int(len(features_bruno)*percentage)
trainFeatures_bruno=features_bruno[:pB,:]
pYT=int(len(features_marta)*percentage)
trainFeatures_marta=features_marta[:pYT,:]
pM=int(len(features_dns)*percentage)
trainFeatures_dns=features_dns[:pYT,:]

#i2train: Concatena os conjuntos de TREINO das categorias de Browsing e YouTube (SEM MINING) para serem usados em modelos de classificação.
i2train=np.vstack((trainFeatures_bruno,trainFeatures_marta))
o2trainClass=np.vstack((oClass_bruno[:pB],oClass_marta[:pYT]))

#:ii
i3Ctrain=np.vstack((trainFeatures_bruno,trainFeatures_marta,trainFeatures_dns))
o3trainClass=np.vstack((oClass_bruno[:pB],oClass_marta[:pYT],oClass_dns[:pM]))

#:iii
testFeatures_bruno=features_bruno[pB:,:]
testFeatures_marta=features_marta[pYT:,:]
testFeatures_dns=features_dns[pM:,:]
#Fornecer ao modelos os dados de TESTE
i3Atest=np.vstack((testFeatures_bruno,testFeatures_marta,testFeatures_dns))
o3testClass=np.vstack((oClass_bruno[pB:],oClass_marta[pYT:],oClass_dns[pM:]))


# ## -- 4 -- ##
# # O K-Means é um algoritmo de agrupamento muito comum e simples, usado para particionar um conjunto de dados em clusters.
# # Ele opera de maneira iterativa para atribuir cada ponto de dados a um dos K clusters, onde K é um número pré-definido pelo utilizador.
# print('\n-- Clustreing with K-Means --')
# num_classes = len(np.unique(o3testClass))
# kmeans = KMeans(n_clusters=100, random_state=0, n_init="auto")
# i3Ctrain=np.vstack((trainFeatures_bruno,trainFeatures_marta,trainFeatures_dns))
# i3Ctrain = StandardScaler().fit_transform(i3Ctrain)
# labels= kmeans.fit_predict(i3Ctrain)

# # Print the lengths of test labels and test class to check if they match
# print("Length of test labels:", len(labels))
# print("Length of o3testClass:", len(o3testClass))
# print("Training data shape:", i3Ctrain.shape)
# print("Test data shape:", i3Atest.shape)

# # Check unique values or any discrepancies in features
# print("Unique values in training data class:", np.unique(o3trainClass))
# print("Unique values in test data class:", np.unique(o3testClass))

# #Browsing está no Cluster 1, YouTube no Cluster 0 e 2 e Mining no Cluster 1
# for i in range(len(labels)):
#     print('Obs: {:2} ({}): K-Means Cluster Label: -> {}'.format(i,Classes[o3testClass[i][0]],labels[i]))

# ## -- 5 -- ##
# print('\n-- Clustreing with DBSCAN --')
# i3Ctrain=np.vstack((trainFeatures_bruno,trainFeatures_marta,trainFeatures_dns))
# i3Ctrain = StandardScaler().fit_transform(i3Ctrain)
# db = DBSCAN(eps=0.5, min_samples=10).fit(i3Ctrain)
# labels = db.labels_

# for i in range(len(labels)):
#     print('Obs: {:2} ({}): DBSCAN Cluster Label: -> {}'.format(i,Classes[o3testClass[i][0]],labels[i]))

# ## -- 7 -- ##

# i2train=np.vstack((trainFeatures_bruno,trainFeatures_marta,trainFeatures_dns))
# scaler = MaxAbsScaler().fit(i2train)
# i2train=scaler.transform(i2train)

# centroids={}
# for c in range(2):  # Only the first two classes
#     pClass=(o2trainClass==c).flatten()
#     centroids.update({c:np.mean(i2train[pClass,:],axis=0)})
# print('All Features Centroids:\n',centroids)

# i3Atest=np.vstack((testFeatures_bruno,testFeatures_marta,testFeatures_dns))
# #i3Atest=scaler.transform(i3Atest)

# AnomalyThreshold=10

# print('\n-- Anomaly Detection based on Centroids Distances --')
# nObsTest,nFea=i3Atest.shape
# for i in range(nObsTest):
#     x=i3Atest[i]
#     dists=[distance(x,centroids[0]),distance(x,centroids[1])]
#     if min(dists)>AnomalyThreshold:
#         result="Anomaly"
#     else:
#         result="OK"

#     print('Obs: {:2} ({}): Normalized Distances to Centroids: [{:.4f},{:.4f}] -> Result -> {}'.format(i,Classes[o3testClass[i][0]],*dists,result))


## -- 8 -- ##

print('\n-- Anomaly Detection based on One Class Support Vector Machines--')
i2train=np.vstack((trainFeatures_marta,trainFeatures_bruno))
i3Atest=np.vstack((testFeatures_bruno,testFeatures_marta,testFeatures_dns))

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

i3train=np.vstack((trainFeatures_bruno,trainFeatures_marta,trainFeatures_dns))
i3Ctest=np.vstack((testFeatures_bruno,testFeatures_marta,testFeatures_dns))


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
i3train=np.vstack((trainFeatures_bruno,trainFeatures_marta,trainFeatures_dns))
i3Ctest=np.vstack((testFeatures_bruno,testFeatures_marta,testFeatures_dns))

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