#---------------------------------------------------------------------------------------------#
newNode:
# Function to dynamically allocate memory for a new node
# Inputs: a1 = value of the node key
# Outputs: a0 = pointer to the allocated node

    #things to do...
    #1. ecall to allocate memory (sbrk)
    #2. Store the node value
    #3. Store the null pointer as the next pointer (initialize to NULL)
  
    addi sp, sp, -8
    sw ra, 4(sp)
    sw s0, 0(sp) 

    li a7, 9        
    li a0, 8        
    ecall
    mv s0, a0       

    sw a1, 0(s0)    

    li t0, 0
    sw t0, 4(s0)

  
    mv a0, s0

    lw ra, 4(sp)
    lw s0, 0(sp)
    addi sp, sp, 8
    ret

    
