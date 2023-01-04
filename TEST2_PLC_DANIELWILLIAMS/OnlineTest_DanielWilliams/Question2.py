## Question 2 Create a program to acknowledge the difference a language that only had number and word were characters are only wordsss: a. Is. `12435652175327183 dghsajdghsajdsak ahdhsgajk12r61753217653217` translates into
##b. `12435652175327183 dghsajdghsajdsak ahdhsgajk 12r61753217653217

import re  # regular expression module
## import lambda


## EXAMPLE: determine 12435652175327183 dghsajdghsajdsak ahdhsgajk12r61753217653217` translates into 12435652175327183 dghsajdghsajdsak ahdhsgajk 12 r 61753217653217

## take in input from user:
print("Enter a string to tokenize: ")  # prompt user for input
string = input() # read input from user
## ENTER STRING: 12435652175327183 dghsajdghsajdsak ahdhsgajk12r61753217653217

print ("you've entered the following string: ", str(string)) # print string
 
# using sub() to solve the problem, lambda used tp add spaces
res = re.sub("[A-Za-z]+", lambda ele: " " + ele[0] + " ", string)   # add spaces to string  using lambda function  and sub() function to add spaces to string   
 
# printing result
print("The space added string : " + str(res)) # print string with spaces added
## RESULT : The space added string : 12435652175327183 dghsajdghsajdsak ahdhsgajk 12 r 61753217653217
