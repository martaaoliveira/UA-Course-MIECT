# Load the sampled time sequences

import numpy as np
import matplotlib.pyplot as plt

datFile='outFile.txt'
data=np.loadtxt(datFile,dtype=int)
plt.plot(data[:,1],'k')
plt.show()
plt.plot(data[:,3],'b')
plt.show()
