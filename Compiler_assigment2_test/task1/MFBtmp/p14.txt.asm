p14_entry:
	move $fp $sp
	sw $ra 0($sp)
	addiu $sp $sp -4
	sw $fp 0($sp)
	addiu $sp $sp -4
	li $a0 10
	sw $a0 0($sp)
	addiu $sp $sp -4
	jal f_entry
	lw $ra 4($sp)
	addiu $sp $sp 8
	lw $fp 0($sp)
	jr $ra
f_entry:
	lw $a0 4($fp)
