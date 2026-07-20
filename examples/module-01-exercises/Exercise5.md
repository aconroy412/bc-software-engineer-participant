The code creates data and assigns it to the stack frame. For the string it adds that to the heap.
The program then pops the main() stack frame and garbage collector evicts the string object.