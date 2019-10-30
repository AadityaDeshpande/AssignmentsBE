library(caTools)
library(e1071)

mycsv <- read.csv("201805-capitalbikeshare-tripdata.csv",header = TRUE,sep = ",")

mysub <- mycsv[,c(1,4,6,9)]

summary(mysub)
        
View(mysub)

temfields <- sample.split(mysub,SplitRatio = 0.7)

train <- subset(mysub,temfields = TRUE)

test <- subset(mysub,temfields = FALSE)

model <- naiveBayes(as.factor(train$Member.type)~.,train)
model

pred <- predict(model,test[,-4])
pred

conf <- as.matrix(table(test$Member.type,pred))
conf

accr <- (sum(diag(conf))/sum(conf))*100
accr