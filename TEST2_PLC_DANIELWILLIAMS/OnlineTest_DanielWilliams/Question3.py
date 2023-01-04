
## QUESTION 3 Write a program that accepts string that has an odd number of a’s and b’s

## aaaa (not accepted)
## bbbb (not accepted)
## aabbb (accepted)
## bbaaa (accepted)

## string accepted by DFA or not

def isAccepted(s):
    
    ## stroes intial state of DFA
    ##intial state is 0
    state = 0
    
    ## final state of DFA
    final_state  = 3
    
    ## loop to traverse the input string
    for i in range(len(s)):
            
            ## if state is 0 and input is 'a' then state becomes 1
            if state == 0 and s[i] == 'a':
                state = 1
                
            ## if state is 0 and input is 'b' then state becomes 2
            elif state == 0 and s[i] == 'b':
                state = 2
                
            ## if state is 1 and input is 'a' then state becomes 1
            elif state == 1 and s[i] == 'a':
                state = 1
                
            ## if state is 1 and input is 'b' then state becomes 3
            elif state == 1 and s[i] == 'b':
                state = 3
                
            ## if state is 2 and input is 'a' then state becomes 3
            elif state == 2 and s[i] == 'a':
                state = 3
                
            ## if state is 2 and input is 'b' then state becomes 2
            elif state == 2 and s[i] == 'b':
                state = 2
                
            ## if state is 3 and input is 'a' then state becomes 3
            elif state == 3 and s[i] == 'a':
                state = 3
                
            ## if state is 3 and input is 'b' then state becomes 3
            elif state == 3 and s[i] == 'b':
                state = 3
                
                
    ## if state is equal to final state then string is accepted
    
    if state == final_state:
                return True
    else:    
        return False




## Driver code
if __name__ == "__main__":
    
    ## input string
    s = "abaabb"
    
    ## if string is accepted by DFA
    if isAccepted(s):
        print("acceptable ")
    else:
        print("Not acceptable")
        


##def isAccepted(string):
  ##  if string == "a":
    ##    return True
    ##elif string == "b":
      ##  return True
    ##elif string == "ab":
     ##   return True
    ##elif string == "ba":
     ##   return True
    ##elif string == "aa":
     ##   return True
   ## elif string == "bb":
     ##   return True
    ##elif string == "aaa":
      ##  return True
   ## elif string == "bbb":
     ##   return True
    ##elif string == "aab":
      ##  return True
    ##elif string == "aba":
      ##  return True
    ##elif string == "baa":
      ##  return True
    ##elif string == "bba":
      ##  return True
    ##elif string == "abb":
     ##   return True
    ##elif string == "bab":
      ##  return True
   ## elif string == "abb":
     ##   return True
    ##elif string == "bab":
        