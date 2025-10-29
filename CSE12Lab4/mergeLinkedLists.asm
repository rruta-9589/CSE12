#---------------------------------------------------------------------------------------------#
mergeLinkedLists:
#Function to merge the 2 sorted lists together and the resultant list is also sorted
#Inputs: a0 = address of head of 1st linked list; so if a0=0x10040000 points to empty list, then mem[0x10040000]=0
#	a1 = = address of head of 2nd linked list;
#Output: the first linked contains the combined elements of 1st and second lists sorted

    addi sp, sp, -16   
    sw ra, 12(sp)      
    sw s0, 8(sp)       
    sw s1, 4(sp)       
    sw s2, 0(sp)      

    mv s0, a0          
    mv s1, a1          

    lw s1, 0(s1)       
    beq s1, zero, merge_done  

merge_loop:
    beq s1, zero, merge_done  

    lw a1, 0(s1)       
    mv a0, s0          
    call insertSorted  

    lw s1, 4(s1)       
    j merge_loop       

merge_done:
    lw ra, 12(sp)      
    lw s0, 8(sp)       
    lw s1, 4(sp)
    lw s2, 0(sp)
    addi sp, sp, 16   
    ret