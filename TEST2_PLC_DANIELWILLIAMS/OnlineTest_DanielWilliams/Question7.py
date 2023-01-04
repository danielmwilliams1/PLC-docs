###Create an FA and Regular Expression for the following language:
##YO(abc)*c(bac)*MAmA

import sys
import re

def main():

    #Create the FA
    FA = {
        'YO': {'a': 'abc', 'b': 'bac', 'c': 'MAmA'},
        'abc': {'a': 'abc', 'b': 'abc', 'c': 'abc'},
        'bac': {'a': 'bac', 'b': 'bac', 'c': 'bac'},
        'MAmA': {'a': 'MAmA', 'b': 'MAmA', 'c': 'MAmA'}
    }
    
    #Create the Regular Expression
    RE = 'YO(abc)*c(bac)*MAmA'

    #Get the input string
    RE = input("Enter a string: ")
    
    
    #Check if the string is in the language
    if RE in FA:
        print("The string is in the language")
    else:
        print("The string is not in the language")

if __name__ == "__main__":
    main()
    