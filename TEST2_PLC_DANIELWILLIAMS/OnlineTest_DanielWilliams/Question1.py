##QUESTION1:Write a program that a can split an input into individual inputs or lexemes or statements and acknowledge them as separate tokens? Coding language of your choice!!

import re  # regular expression module

def main(): 
    print("Enter a string to tokenize: ")  # prompt user for input
    string = input() # read input from user
    print("Tokens are: ") # print tokens
    print(re.findall(r'\w+|\S', string)) # print tokens
    ## print number of tokens
    print("Number of tokens: ", len(re.findall(r'\w+|\S', string))) # print number of tokens
    

if __name__ == "__main__":  # if this file is run directly
    main()  # call main function
