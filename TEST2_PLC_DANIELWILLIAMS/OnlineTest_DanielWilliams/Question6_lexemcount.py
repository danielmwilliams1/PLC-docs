import re

## open file
file = open("Question6.py", "r")

## read file
data = file.read()
    
## close file
file.close()

##find code lexemes
lexemes = re.findall(r'[a-zA-Z0-9]+', data)


## print lexemes
print(lexemes)

## print number of lexemes
print("Number of lexemes: ", len(lexemes))

## output
    
            