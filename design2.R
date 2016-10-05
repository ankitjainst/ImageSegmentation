ppi0 <- read.csv("/home/java/ankit/srep/Analytics/preprocessedImage.csv", header = FALSE)
ppi0CC <- ppi0[complete.cases(ppi0),]
ppi <- ppi0CC[1:36]
#ppiCC <- ppi[complete.cases(ppi),]
kResult <- kmeans(ppi,3)

finalResult <- cbind(ppi0CC[37:38],kResult$cluster)

write.csv(finalResult, file="/home/java/ankit/srep/Analytics/result.csv")