##Evaluate and create a visual representation ( Linked List Style ) of how the following program is stored in LISP:

##(defun factorial (N)
##"Compute the factorial of N."
##(if (= N 1)
##1
##(* N (factorial (- N 1))))

def factorial(N):
    "Compute the factorial of N."
    if N == 1:
        return 1
    else:
        return N * factorial(N-1)
    
print(factorial(1))
print(factorial(2))
print (factorial(3))
print (factorial(4))
print (factorial(5))