#---------------------------------------------------------------------------------------------#
print_list:
# Function to traverse the linked list and print the values
# Inputs: a1 = head of the linked list

beq a1, zero, print_empty_list

    # Loop through the linked list
print_loop:
    lw t0, 0(a1)          
    print_int(t0)         
    print_str(comma_space)

    lw a1, 4(a1)          
    bne a1, zero, print_loop  

    # End of list
    print_str(endoflistmsg)
    print_str(newline)
    j print_done

print_empty_list:
    print_str(empty_list_msg)

print_done:
    ret