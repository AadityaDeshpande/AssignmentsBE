library(e1071)
library(caTools)

mycsv <- read.csv("PimaIndiansDiabetes.csv",header = TRUE,sep = ",")

mycsv

temp_fields <- sample.split(mycsv,SplitRatio = 0.7)

temp_fields

train <- subset(mycsv,temp_fields == TRUE)
test <- subset(mycsv,temp_fields == FALSE)

summary(train)
summary(test)

model <- naiveBayes(as.factor(train$Class)~.,train)
model

pred <- predict(model,test[,-9])
pred

#generate confusion matrix
con = as.matrix(table(pred,test$Class,dnn=c("predicted","Actual data")))

Accr <- (sum(diag(con))/sum(con))*100

sprintf("Accuracy is: %f",Accr)

# to save the prediction
output<-cbind(test,pred)
View(output)
