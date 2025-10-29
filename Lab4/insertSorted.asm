#---------------------------------------------------------------------------------------------#
insertSorted:
# Function to insert a new node into the given linkedlist in ascending order
# Inputs: a1 = value of the node key
#	a0 = address of head of linked list; so if a0=0x10040000 points to empty list, then mem[0x10040000]=0
# Outputs: a0 = pointer to the final node in current sorted linked list

    addi sp, sp, -16
    sw ra, 12(sp)
    sw s0, 8(sp)
    sw s1, 4(sp)
    sw s2, 0(sp)


    # Create a new node with 'key'
    mv s1, a0          
    mv s2, a1         
    call newNode       
    mv s0, a0         

    #current and prev pointers
    lw a0, 0(s1)      
    li a1, 0           

loop:
    # Check if current is NULL 
    beq a0, zero, insert 
    lw t0, 0(a0)        
    bge t0, s2, insert  

    # Move prev and current forward
    mv a1, a0           
    lw a0, 4(a0)        
    j loop              

insert:
    # If prev is NULL, insert at beginning
    beq a1, zero, insert_at_head

    # Insert in middle or end
    sw s0, 4(a1)        
    sw a0, 4(s0)        
    j done

insert_at_head:
    sw a0, 4(s0)        
    sw s0, 0(s1)       

done:
    # Restore saved registers and return
    lw ra, 12(sp)
    lw s0, 8(sp)
    lw s1, 4(sp)
    lw s2, 0(sp)
    addi sp, sp, 16
    ret

    
