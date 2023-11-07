#Author : Riste Popov
#Date : 28 september 2023
#
#This program calculates pi with a precision
#of 60 decimals using Taylor series.

term = 0 #counts each term thats added and substracted

#numerator and denominator for the fractions in Taylor series
num = 0
den = 1 #starting at 0/1 to be able to add it to the next fraction

decimal = 60 #number of decimals of precison of pi 
interval = 10 #interval of the terms where pi will be printed

#oldPi and pi used to compare when the precison is enough
oldPi = ''
pi = 'different' #pi is different so the loop can start

while oldPi != pi:

    k = 2*term+1 #k pattern in Taylor series

    #Use Taylor series and alternate between adding 
    #and substracting using (-1) to the term.
    #Store the next fraction before adding it back
    newNum = (4*3**k+4*2**k)*(-1)**term 
    newDen = k*2**k*3**k

    #add two fractions without using float
    num = num*newDen+newNum*den
    den = den*newDen
    oldPi = pi #assignment to break loop once precision is achieved
    term += 1 #increment to find next fraction

    #Modified code from TP of the 28 september 2023
    #to convert the fraction of pi into decimal form
    largeNumber = (10**decimal*num)//den #used for fraction without the '.'
    index = -decimal
    pi = '' 
    while True : #increment until the first digit and add the '.'
        if index == 0:
            pi = str(largeNumber) + '.' + pi
            break
        pi = str(largeNumber % 10) + pi
        largeNumber = largeNumber // 10
        index += 1


    #print the approximate value of pi at each 10 terms     
    if term % interval == 0:
        print(str(term) + " termes: " + str(pi) + "...")

#adjust extra term because precision was already reached        
print(decimal, "décimales de précision obtenues après", term-1, "termes")
    
print("pi = " + str(pi) + "...")  
