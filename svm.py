import numpy as np # 快速操作结构数组的工具
from numpy.matlib import repmat
from sklearn import svm  # svm支持向量机
import matplotlib.pyplot as plt # 可视化绘图
from sklearn.metrics import confusion_matrix

from helperfunctions import visboundary

train_target=[]
train_data=[]
temp=[]

data_set=np.loadtxt("wdbc.txt",str)
for i in range(len(data_set)):
    train_target.append(data_set[i].split(",")[1])
    for j in range(2,len(data_set[i].split(","))):
        temp.append(float(data_set[i].split(",")[j]))
    train_data.append(temp)
    temp=[]

print(train_data)
print(train_target)
test_data = train_data[:85]
test_target = train_target[:85]



clf = svm.SVC(kernel='linear', C=1.5)
clf.fit(X=train_data, y=train_target,sample_weight=None)

w = clf.coef_
b = clf.intercept_

#print (w)
#print (b)
result = clf.predict(test_data)
print(clf.score(test_data,test_target))
C=confusion_matrix(test_target, result)
print(C)
