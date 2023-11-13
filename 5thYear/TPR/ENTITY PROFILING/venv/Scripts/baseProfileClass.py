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
def plotFeatures(features,oClass,f1index=0,f2index=1):
    nObs,nFea=features.shape
    colors=['b','g','r']
    for i in range(nObs):
        plt.plot(features[i,f1index],features[i,f2index],'o'+colors[int(oClass[i])])

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

oClass_browsing=np.ones((len(features_browsing),1))*0
oClass_yt=np.ones((len(features_yt),1))*1
oClass_mining=np.ones((len(features_mining),1))*2

features=np.vstack((features_yt,features_browsing,features_mining))
oClass=np.vstack((oClass_yt,oClass_browsing,oClass_mining))

print('Train Silence Features Size:',features.shape)
plt.figure(2)
plotFeatures(features,oClass,4,10)
plt.figure(3)
plotFeatures(features,oClass,0,7)
plt.figure(4)
plotFeatures(features,oClass,2,8)


## -- 3 -- ##
#:i
percentage=0.5
pB=int(len(features_browsing)*percentage)
trainFeatures_browsing=features_browsing[:pB,:]
pYT=int(len(features_yt)*percentage)
trainFeatures_yt=features_yt[:pYT,:]
pM=int(len(features_mining)*percentage)
trainFeatures_mining=features_mining[:pYT,:]

i2train=np.vstack((trainFeatures_browsing,trainFeatures_yt))
o2trainClass=np.vstack((oClass_browsing[:pB],oClass_yt[:pYT]))

#:ii
i3Ctrain=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
o3trainClass=np.vstack((oClass_browsing[:pB],oClass_yt[:pYT],oClass_mining[:pM]))

#:iii
testFeatures_browsing=features_browsing[pB:,:]
testFeatures_yt=features_yt[pYT:,:]
testFeatures_mining=features_mining[pM:,:]

i3Atest=np.vstack((testFeatures_browsing,testFeatures_yt,testFeatures_mining))
o3testClass=np.vstack((oClass_browsing[pB:],oClass_yt[pYT:],oClass_mining[pM:]))

## -- 4 -- ##
print('\n-- Clustreing with K-Means --')
kmeans = KMeans(n_clusters=3, random_state=0, n_init="auto")
i3Ctrain=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
#i3Ctrain = StandardScaler().fit_transform(i3Ctrain)
labels= kmeans.fit_predict(i3Ctrain)

for i in range(len(labels)):
    print('Obs: {:2} ({}): K-Means Cluster Label: -> {}'.format(i,Classes[o3testClass[i][0]],labels[i]))
    
## -- 5 -- ##
print('\n-- Clustreing with DBSCAN --')
i3Ctrain=np.vstack((trainFeatures_browsing,trainFeatures_yt,trainFeatures_mining))
i3Ctrain = StandardScaler().fit_transform(i3Ctrain)
db = DBSCAN(eps=0.5, min_samples=10).fit(i3Ctrain)
labels = db.labels_

for i in range(len(labels)):
    print('Obs: {:2} ({}): DBSCAN Cluster Label: -> {}'.format(i,Classes[o3testClass[i][0]],labels[i]))

## -- 7 -- ##

i2train=np.vstack((trainFeatures_browsing,trainFeatures_yt))
#scaler = MaxAbsScaler().fit(i2train)
#i2train=scaler.transform(i2train)

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
